import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.Timer;

public class MainInterface extends JFrame {

    public MainInterface(Dungeon dungeon, Hero hero) {
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setTitle("Dungeon Game");

        // Taille adaptée pour 40x30 tuiles de 16px (640x480) + marges
        this.setSize(new Dimension(800, 600));

        GameRender panel = new GameRender(dungeon, hero);
        this.setContentPane(panel);

        // Ajout du contrôleur clavier
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                    case KeyEvent.VK_Q:
                        hero.moveIfPossible(-16, 0, dungeon);
                        break;
                    case KeyEvent.VK_RIGHT:
                    case KeyEvent.VK_D:
                        hero.moveIfPossible(16, 0, dungeon);
                        break;
                    case KeyEvent.VK_UP:
                    case KeyEvent.VK_Z:
                        hero.moveIfPossible(0, -16, dungeon);
                        break;
                    case KeyEvent.VK_DOWN:
                    case KeyEvent.VK_S:
                        hero.moveIfPossible(0, 16, dungeon);
                        break;
                }
                // Le redessin est géré par le Timer
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        this.setVisible(true);

        // Boucle de jeu : rafraîchissement toutes les 50ms (20 FPS)
        Timer timer = new Timer(50, (e) -> panel.repaint());
        timer.start();
    }
}
