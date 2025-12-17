import java.awt.Image;

public class DynamicThings extends AnimatedThings {
    private double speedX;
    private double speedY;

    public DynamicThings(int x, int y, Image image) {
        super(x, y, image);
        this.speedX = 0;
        this.speedY = 0;
    }

    public void moveIfPossible(double dx, double dy, Dungeon dungeon) {
        // Déterminer la direction du mouvement et mettre à jour le sprite du héros
        if (this instanceof Hero) {
            Hero hero = (Hero) this;
            if (dx < 0) {
                hero.setDirection(Hero.Direction.LEFT);
            } else if (dx > 0) {
                hero.setDirection(Hero.Direction.RIGHT);
            } else if (dy < 0) {
                hero.setDirection(Hero.Direction.UP);
            } else if (dy > 0) {
                hero.setDirection(Hero.Direction.DOWN);
            }
        }

        boolean canMove = true;

        // On vérifie la collision avec les objets solides du donjon
        // On déplace temporairement la hitbox pour tester
        this.getHitBox().move(dx, dy);

        for (Things thing : dungeon.getThings()) {
            if (thing instanceof SolidThings && thing != this) {
                if (((SolidThings) thing).getHitBox().intersect(this.getHitBox())) {
                    canMove = false;
                    break;
                }
            }
        }

        if (canMove) {
            this.x += dx;
            this.y += dy;
        } else {
            // Si on ne peut pas bouger, on annule le mouvement de la hitbox
            this.getHitBox().move(-dx, -dy);
        }
    }

    public double getSpeedX() {
        return speedX;
    }

    public void setSpeedX(double speedX) {
        this.speedX = speedX;
    }

    public double getSpeedY() {
        return speedY;
    }

    public void setSpeedY(double speedY) {
        this.speedY = speedY;
    }
}
