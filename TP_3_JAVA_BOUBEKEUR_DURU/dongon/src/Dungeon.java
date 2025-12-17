import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;

public class Dungeon {
    private int width;
    private int height;
    private TileManager tileManager;
    private char[][] map;
    private ArrayList<Things> renderList;

    public Dungeon(String mapFile, TileManager tileManager) {
        this.tileManager = tileManager;
        this.renderList = new ArrayList<>();

        try {
            readMap(mapFile);
        } catch (IOException e) {
            System.err.println("ERREUR lecture map: " + e.getMessage());
            this.width = 10;
            this.height = 10;
            this.map = new char[height][width];
        }

        respawnListOfThings();
    }

    private void readMap(String fileName) throws IOException {
        ArrayList<String> lines = new ArrayList<>();
        File f = new File(fileName);
        if (!f.exists())
            f = new File("src/" + fileName);

        BufferedReader br = new BufferedReader(new FileReader(f));
        String line;
        while ((line = br.readLine()) != null) {
            lines.add(line);
        }
        br.close();

        if (lines.isEmpty())
            return;

        this.height = lines.size();
        this.width = 0;

        // Trouver la largeur maximale
        for (String l : lines) {
            if (l.length() > this.width) {
                this.width = l.length();
            }
        }

        this.map = new char[height][width];

        for (int i = 0; i < height; i++) {
            String l = lines.get(i);
            for (int j = 0; j < width; j++) {
                if (j < l.length())
                    map[i][j] = l.charAt(j);
                else
                    map[i][j] = ' ';
            }
        }

        System.out.println("Carte chargée : " + width + "x" + height);
    }

    public ArrayList<Things> getRenderList() {
        return renderList;
    }

    // AJOUT : Méthode pour DynamicThings
    public ArrayList<Things> getThings() {
        return renderList;
    }

    private void respawnListOfThings() {
        renderList.clear();
        int tileWidth = tileManager.getWidth(); // 16
        int tileHeight = tileManager.getHeight(); // 16

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                char c = map[i][j];
                int x = j * tileWidth;
                int y = i * tileHeight;

                // AJOUT SYSTÉMATIQUE DU SOL SOUS LES OBJETS (sauf murs pleins)
                if (c != 'W') {
                    renderList.add(new Things(x, y, tileManager.getTile(1, 1)));
                }

                switch (c) {
                    case 'W': // Mur Extérieur (0, 8)
                        renderList.add(new SolidThings(x, y, tileManager.getTile(0, 8)));
                        break;
                    case 'I': // Mur Intérieur (1, 8)
                        renderList.add(new SolidThings(x, y, tileManager.getTile(1, 8)));
                        break;
                    case 'C': // Coffre (8, 7)
                        renderList.add(new SolidThings(x, y, tileManager.getTile(8, 7)));
                        break;
                    case 'B': // Tonneau (8, 8)
                        renderList.add(new SolidThings(x, y, tileManager.getTile(8, 8)));
                        break;
                    // Le cas ' ' (Espace) est géré par l'ajout systématique du sol ci-dessus
                }
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public char[][] getMap() {
        return map;
    }
}
