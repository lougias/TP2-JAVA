import javax.swing.*;
import java.awt.*;

public class GameRender extends JPanel {

    private Dungeon dungeon;
    private Hero hero;

    public GameRender(Dungeon dungeon, Hero hero) {
        this.dungeon = dungeon;
        this.hero = hero;

        this.setPreferredSize(new Dimension(640, 480)); // taille fenêtre
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dessiner chaque élément du donjon
        for (Things t : dungeon.getRenderList()) {
            g.drawImage(t.getImage(), t.getX(), t.getY(), null);
        }

        // Dessiner le héros
        g.drawImage(hero.getImage(), hero.getX(), hero.getY(), null);
    }
}
