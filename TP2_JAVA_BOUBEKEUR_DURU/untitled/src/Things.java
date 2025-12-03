import java.awt.Image;

/* Classe Things : représente un élément affichable du décor
   Elle a une position et une taille (en pixels).
*/
public class Things {

    protected int x;      // position horizontale
    protected int y;      // position verticale
    protected int width;  // largeur de l'élément
    protected int height; // hauteur de l'élément

    protected Image image; // l'image associée à l'objet

    /* Constructeur : initialise la position et la taille du Things (sans image) */
    public Things(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.image = null;
    }

    /* Constructeur : initialise la position et l'image du Things
       Les dimensions width/height sont lues depuis l'image.
    */
    public Things(int x, int y, Image image) {
        this.x = x;
        this.y = y;
        this.image = image;

        if (image != null) {
            this.width = image.getWidth(null);
            this.height = image.getHeight(null);
        } else {
            this.width = 0;
            this.height = 0;
        }
    }

    /* Getters permettant d'accéder aux valeurs */
    public int getX() { return x; }
    public int getY() { return y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public Image getImage() { return image; }

    /* Méthode draw minimale : dessine l'image si elle existe
       (utile si tu veux centraliser le dessin dans les objets) */
    public void draw(java.awt.Graphics g) {
        if (image != null) {
            g.drawImage(image, x, y, null);
        } else {
            // si aucune image, dessiner un rectangle de secours
            g.drawRect(x, y, width, height);
        }
    }
}
