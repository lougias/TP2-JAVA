import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class TileManager {
    private int width;
    private int height;
    private BufferedImage tileSheet;
    private Image[][] tiles;

    public TileManager(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void setTiles(int width, int height, String fileName) {
        try {
            System.out.println("Tentative chargement image depuis : " + fileName);
            File f = new File(fileName);
            System.out.println("Chemin absolu tenté : " + f.getAbsolutePath());

            tileSheet = ImageIO.read(f);

            if (tileSheet == null) {
                System.err.println("ERREUR : Image chargée est NULL (format non supporté ?)");
                return;
            }

            System.out.println("Image chargée ! Dimensions : " + tileSheet.getWidth() + "x" + tileSheet.getHeight());

            // On initialise le tableau de tuiles
            int numTilesX = tileSheet.getWidth() / width;
            int numTilesY = tileSheet.getHeight() / height;

            System.out.println("Découpage en grille : " + numTilesX + " colonnes, " + numTilesY + " lignes.");

            tiles = new Image[numTilesX][numTilesY];

            for (int x = 0; x < numTilesX; x++) {
                for (int y = 0; y < numTilesY; y++) {
                    tiles[x][y] = tileSheet.getSubimage(x * width, y * height, width, height);
                }
            }
            System.out.println("Découpage terminé sans erreur.");

        } catch (IOException e) {
            System.err.println("ERREUR IO lors du chargement de l'image (" + fileName + ") :");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("ERREUR inattendue dans setTiles :");
            e.printStackTrace();
        }
    }

    public Image getTile(int x, int y) {
        if (tiles == null) {
            return null;
        }
        if (x < 0 || x >= tiles.length || y < 0 || y >= tiles[0].length) {
            System.err.println("Index hors bornes demandé : " + x + ", " + y);
            return null;
        }
        return tiles[x][y];
    }

    public BufferedImage getTileSheet() {
        return tileSheet;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
