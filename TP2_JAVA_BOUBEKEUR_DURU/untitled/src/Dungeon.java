import java.awt.Image;
import java.util.ArrayList;

/* Classe Dungeon
   Représente le donjon sous forme de grille de caractères et,
   une liste d'objets Things correspondant aux éléments du décor (sol ou mur).
*/
public class Dungeon {

    private final int width;    // largeur du donjon (en cases)
    private final int height;   // hauteur du donjon (en cases)
    private char[][] map;       // carte du donjon sous forme de tableau 2D

    private TileManager tileManager; // gestionnaire de tuiles

    // Liste destinée au rendu graphique (TP 3.2.4)
    private ArrayList<Things> renderList;

    /* Constructeur du donjon :
       width = nb de colonnes, height = nb de lignes, tileManager = gestionnaire d'images
    */
    public Dungeon(int width, int height, TileManager tileManager) {

        this.width = width;
        this.height = height;
        this.tileManager = tileManager;

        map = new char[height][width];
        renderList = new ArrayList<>();

        // Remplissage simple de la carte : murs tout autour et sol à l'intérieur
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (x == 0 || y == 0 || x == width - 1 || y == height - 1) {
                    map[y][x] = 'W';  // mur
                } else {
                    map[y][x] = ' ';  // sol
                }
            }
        }

        // Génération initiale de la liste pour le rendu (appel demandé par le TP)
        respawnListOfThings();
    }

    /* Méthode respawnListOfThings
       Vide renderList puis génère un Things ou SolidThings pour chaque case.
       Associe une image sol et une image mur à partir du TileManager.
    */
    public void respawnListOfThings() {

        // Vide la liste d'affichage
        renderList.clear();

        // Récupération des images dans le tileset.
        // Ajuste ces coordonnées si tu veux d'autres tuiles du tileset.
        Image solImage = tileManager.getTile(0, 4); // exemple : sol (col 0, ligne 4)
        Image wallImage = tileManager.getTile(0, 0); // exemple : mur (col 0, ligne 0)

        // Parcours de la map et création des objets graphiques
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {

                int pixelX = x * tileManager.getWidth();
                int pixelY = y * tileManager.getHeight();

                if (map[y][x] == ' ') {
                    // case vide -> sol
                    Things t = new Things(pixelX, pixelY, solImage);
                    renderList.add(t);
                } else if (map[y][x] == 'W') {
                    // mur -> SolidThings
                    SolidThings s = new SolidThings(pixelX, pixelY, wallImage);
                    renderList.add(s);
                } else {
                    // cas général : on met du sol par défaut
                    Things t = new Things(pixelX, pixelY, solImage);
                    renderList.add(t);
                }
            }
        }
    }

    /* Getter pour récupérer la renderList (utilisé par GameRender) */
    public ArrayList<Things> getRenderList() {
        return renderList;
    }

    /* Getter pour la map (si utile pour tests ou logique) */
    public char[][] getMap() {
        return map;
    }

    /* Eventuel setter pour modifier une case et régénérer la renderList */
    public void setTileAt(int tileX, int tileY, char value) {
        if (tileX >= 0 && tileX < width && tileY >= 0 && tileY < height) {
            map[tileY][tileX] = value;
            respawnListOfThings();
        }
    }
}
