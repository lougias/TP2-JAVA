public class TestTiles {
    public static void main(String[] args) {

        // TON CHEMIN EXACT VERS tileset.png
        String path = "C:/JAVA/TP/TP2_JAVA_BOUBEKEUR_DURU/untitled/src/tileset.png";

        TileManager tm = new TileManager(32, 32, path);

        if (tm.getTileSheet() != null) {
            System.out.println("OK : Image chargée avec succès !");
        } else {
            System.out.println("ERREUR : L'image n'a PAS été chargée !");
        }
    }
}
