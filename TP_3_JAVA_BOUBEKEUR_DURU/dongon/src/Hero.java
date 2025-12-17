import java.awt.Image;

public final class Hero extends DynamicThings {
    // Enum pour les directions avec coordonnées des tuiles correspondantes
    public enum Direction {
        DOWN(0), // Face (vers le bas)
        UP(1), // Dos (vers le haut)
        LEFT(2), // Gauche
        RIGHT(3); // Droite

        private final int tileX;

        Direction(int tileX) {
            this.tileX = tileX;
        }

        public int getTileX() {
            return tileX;
        }
    }

    private static volatile Hero instance = null;
    private Direction currentDirection = Direction.DOWN;
    private TileManager tileManager;

    private Hero(int x, int y, Image image, TileManager tileManager) {
        super(x, y, image);
        this.tileManager = tileManager;
    }

    public static Hero getInstance(int x, int y, Image image, TileManager tileManager) {
        if (instance == null) {
            synchronized (Hero.class) {
                if (instance == null) {
                    instance = new Hero(x, y, image, tileManager);
                }
            }
        }
        return instance;
    }

    public void setDirection(Direction dir) {
        if (this.currentDirection != dir) {
            this.currentDirection = dir;
            System.out.println("DEBUG: Hero direction changed to " + dir + " (tile x=" + dir.getTileX() + ", y=4)");
            updateSprite();
        }
    }

    private void updateSprite() {
        this.image = tileManager.getTile(currentDirection.getTileX(), 4);
        if (image != null) {
            this.width = image.getWidth(null);
            this.height = image.getHeight(null);
        }
    }

    public void setImage(Image image) {
        this.image = image;
        if (image != null) {
            this.width = image.getWidth(null);
            this.height = image.getHeight(null);
        }
    }
}
