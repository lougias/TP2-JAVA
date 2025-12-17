import java.awt.Image;

public class SolidThings extends Things {
    private HitBox hitBox;

    public SolidThings(int x, int y, Image image) {
        super(x, y, image);
        // La taille de la hitBox est la même que la taille de Things (déduite de
        // l'image)
        this.hitBox = new HitBox(x, y, this.width, this.height);
    }

    public HitBox getHitBox() {
        return hitBox;
    }
}
