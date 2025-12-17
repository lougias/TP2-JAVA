import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

public class Things {
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected Image image;

    public Things(int x, int y, Image image) {
        this.x = x;
        this.y = y;
        this.image = image;
        // On récupère la taille depuis l'image si elle n'est pas nulle
        if (image != null) {
            this.width = image.getWidth(null);
            this.height = image.getHeight(null);
        }
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void draw(Graphics g) {
        // Dessiner avec dimensions explicites pour éviter les artefacts de rendu
        if (image != null) {
            Graphics2D g2d = (Graphics2D) g;

            // Remplir le fond avec une couleur grise (couleur du sol) pour masquer les gaps
            g2d.setColor(new java.awt.Color(128, 128, 128)); // Gris moyen
            g2d.fillRect(x, y, width + 1, height + 1); // +1 pour overlap

            // Dessiner l'image avec un léger overlap pour éviter les gaps
            g.drawImage(image, x, y, width + 1, height + 1, null);
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
