import java.io.File;
import java.awt.Image; // Ajouté pour corriger l'erreur de compilation

public class Main {
    public static void main(String[] args) {
        int tileWidth = 16;
        int tileHeight = 16;

        TileManager tileManager = new TileManager(tileWidth, tileHeight);

        // Chemin relatif pour le tilesheet (depuis donjon project/)
        String tileSheetPath = "dongon/img/tileSheet.png";

        File f = new File(tileSheetPath);
        if (!f.exists()) {
            System.err.println("ERREUR: tileSheet.png introuvable à : " + tileSheetPath);
            System.err.println("Vérifiez que le fichier existe bien !");
        }

        tileManager.setTiles(tileWidth, tileHeight, tileSheetPath);

        // Chemin du level (depuis donjon project/)
        String levelPath = "dongon/src/level1.txt";
        Dungeon dungeon = new Dungeon(levelPath, tileManager);

        // Création du héros AVEC image (ligne 5 = y=4, sprites directionnels)
        Image heroImage = tileManager.getTile(0, 4);
        if (heroImage == null) {
            System.err.println("ERREUR: Image héros non chargée !");
        } else {
            System.out.println(
                    "DEBUG: Image héros dimensions = " + heroImage.getWidth(null) + "x" + heroImage.getHeight(null));
        }

        Hero hero = Hero.getInstance(32, 32, heroImage, tileManager);
        System.out.println("DEBUG: Hero width=" + hero.getWidth() + ", height=" + hero.getHeight());

        // Lancement
        new MainInterface(dungeon, hero);
    }
}
