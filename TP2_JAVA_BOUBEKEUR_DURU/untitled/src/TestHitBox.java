/* Classe permettant de tester la HitBox et la méthode intersect */
public class TestHitBox {

    public static void main(String[] args) {

        // Création de 3 hitbox
        HitBox h1 = new HitBox(0, 0, 50, 50);       // hitBox de référence
        HitBox h2 = new HitBox(25, 25, 50, 50);     // chevauche h1
        HitBox h3 = new HitBox(100, 100, 50, 50);   // ne touche pas h1

        // Tests d’intersection
        System.out.println("h1 intersect h2 = " + h1.intersect(h2)); // true
        System.out.println("h1 intersect h3 = " + h1.intersect(h3)); // false
        System.out.println("h2 intersect h3 = " + h2.intersect(h3)); // false
    }
}
