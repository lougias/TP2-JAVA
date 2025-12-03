import javax.swing.*;
import java.awt.*;

public class MainInterface extends JFrame {

    private Dungeon dungeon;
    private Hero hero;
    private GameRender gameRender;

    public MainInterface() {
        super("Donjon - TP2 JAVA");

        // === 1. Chargement du tileset ===
        TileManager tileManager = new TileManager(
                32,            // largeur d’une tuile
                32,            // hauteur d’une tuile
                "C:/JAVA/TP/TP2_JAVA_BOUBEKEUR_DURU/untitled/src/tileset.png"
        );

        // === 2. Création du Donjon ===
        dungeon = new Dungeon(20, 12, tileManager);

        // === 3. Chargement du héros ===
        Image heroImg = tileManager.getTile(1, 1); // choisi une tuile pour le héros
        hero = Hero.getInstance(64, 64, heroImg);

        // === 4. Création du panneau d'affichage ===
        gameRender = new GameRender(dungeon, hero);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(new Dimension(800, 600));
        this.setVisible(true);

        this.getContentPane().add(gameRender);
    }

    public static void main(String[] args) {
        new MainInterface();
    }
}
