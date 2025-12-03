import java.awt.Image;

public class Hero extends DynamicThings {

    private static Hero instance;

    private Hero(int x, int y, Image img) {
        super(x, y, img);
    }

    public static Hero getInstance(int x, int y, Image img) {
        if (instance == null) {
            instance = new Hero(x, y, img);
        }
        return instance;
    }

    public boolean moveIfPossible(int dx, int dy, Dungeon dungeon) {

        int newX = x + dx;
        int newY = y + dy;

        for (Things t : dungeon.getRenderList()) {
            if (t instanceof SolidThings) {

                if (newX < t.getX() + t.getWidth() &&
                        newX + getWidth() > t.getX() &&
                        newY < t.getY() + t.getHeight() &&
                        newY + getHeight() > t.getY()) {
                    return false;
                }
            }
        }

        x = newX;
        y = newY;
        return true;
    }
}
