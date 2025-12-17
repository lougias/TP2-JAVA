import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class GameRender extends JPanel {
    private Dungeon dungeon;
    private Hero hero;
    private BufferedImage canvas;
    private static final int SCALE = 3;
    private static final int CANVAS_WIDTH = 640; // 40 tuiles * 16px
    private static final int CANVAS_HEIGHT = 432; // 27 tuiles * 16px

    public GameRender(Dungeon dungeon, Hero hero) {
        this.dungeon = dungeon;
        this.hero = hero;
        // Canvas à taille native (16x16 par tuile)
        this.canvas = new BufferedImage(CANVAS_WIDTH, CANVAS_HEIGHT, BufferedImage.TYPE_INT_RGB);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // 1. Dessiner tout sur le canvas à taille native
        Graphics2D canvasG = canvas.createGraphics();

        // Fond noir
        canvasG.setColor(Color.BLACK);
        canvasG.fillRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);

        // Dessiner le donjon
        if (dungeon != null && dungeon.getRenderList() != null) {
            for (Things thing : dungeon.getRenderList()) {
                thing.draw(canvasG);
            }
        }

        // Dessiner le héros
        if (hero != null) {
            hero.draw(canvasG);
        }

        canvasG.dispose();

        // 2. Scaler le canvas entier pour l'affichage (UN SEUL SCALING!)
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(java.awt.RenderingHints.KEY_INTERPOLATION,
                java.awt.RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
        g2d.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING,
                java.awt.RenderingHints.VALUE_ANTIALIAS_OFF);

        // Dessiner le canvas scalé
        g2d.drawImage(canvas, 0, 0, CANVAS_WIDTH * SCALE, CANVAS_HEIGHT * SCALE, null);
    }
}
