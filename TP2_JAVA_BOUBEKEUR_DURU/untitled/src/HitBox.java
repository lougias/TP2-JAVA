/* Classe HitBox permettant de modéliser un rectangle pour gérer les collisions */
public class HitBox{
    private int x; // position horizontale
    private int y; // position verticale
    private int width; // largeur hitbox
    private int height;  // hauteur hitbox
    
    /* Constructeur HitBox */
    public HitBox(int x, int y, int width, int height) {
        this.x = x; // initialise x
        this.y = y; // initialise y
        this.width = width; // initialise largeur
       	this.height = height; // initialise hauteur
    }
    
    /* Méthode intersect : vérifie si deux hitbox se chevauchent */
    public boolean intersect(HitBox anotherHitBox){
        // détermine le rectangle le plus à gauche
        HitBox left = this.x < anotherHitBox.x ? this : anotherHitBox;
        // le rectangle le plus à droite
        HitBox right = (left == this) ? anotherHitBox : this;
        
        // distance horizontale entre les deux rectangle
        int distX = right.x - left.x;
        
        // test si les 2 rectangles se chevauchent sur l'axe x
        boolean xOverlap = left.width > distX;
        
        // détermine le rectangle le plus haut
        HitBox top = this.y < anotherHitBox.y ? this : anotherHitBox;
    
        /* si le rectangle du haut est 'this', alors celle du bas est 'anotherHitBox'
           si le rectangle du haut est 'anotherHitBox', alors celle du bas est 'this' */
        
        // celui qui est en dessous
        HitBox bottom = (top == this) ? anotherHitBox : this;

        // distance verticale entre les 2 rectangles
        int distY = bottom.y - top.y;
        
        // test si les 2 rectangles se chevauchent sur l'axe y
        boolean yOverlap = top.height > distY;
        
        // intersection = chevauchement horizontal ET vertical
        return xOverlap && yOverlap;
    }

    /* Getters permettant d'accéder aux valeurs privées de la hitbox */
    public int getX() { return x; }
    public int getY() { return y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
}
