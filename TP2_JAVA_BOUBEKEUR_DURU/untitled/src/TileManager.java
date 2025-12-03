import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

/*
   Classe TileManager
   Sert à gérer les tuiles du jeu (images 32x32, 48x48, etc.)
   Permet de charger un tileset PNG et de le découper en petites cases.
*/
public class TileManager {

    private final int width;   // largeur d'une tuile
    private final int height;  // hauteur d'une tuile

    private Image[][] tiles;   // tableau 2D des tuiles découpées
    private BufferedImage tileSheet; // image complète du tileset

    public TileManager(int width, int height, String fileName) {
        this.width = width;
        this.height = height;

        setTiles(width, height, fileName);
    }

    // Charge le tileset et découpe les tuiles
    private void setTiles(int width, int height, String fileName) {

        try {
            tileSheet = ImageIO.read(new File(fileName));
            System.out.println("Tileset chargé : " + fileName);
        } catch (Exception e) {
            System.out.println("ERREUR : impossible de charger " + fileName);
            e.printStackTrace();
            return;
        }

        int cols = tileSheet.getWidth() / width;
        int rows = tileSheet.getHeight() / height;

        tiles = new Image[rows][cols];

        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                tiles[y][x] = tileSheet.getSubimage(
                        x * width,
                        y * height,
                        width,
                        height
                );
            }
        }
    }

    // Récupère une tuile
    public Image getTile(int tileX, int tileY) {
        return tiles[tileY][tileX];
    }

    // Getter ajouté pour les tests
    public BufferedImage getTileSheet() {
        return tileSheet;
    }

    public int getWidth() { return width; }
    public int getHeight() { return height; }
}
