# Dungeon Game - Jeu de Donjon en Java

## Description

Jeu de donjon en 2D développé en Java avec Swing. Le joueur contrôle un héros qui peut se déplacer dans un donjon, éviter les obstacles et interagir avec l'environnement.

## Caractéristiques

### Graphismes
- **Rendu pixel-art** : Tuiles 16x16 pixels affichées avec un zoom x3
- **Technique de rendu** : Canvas intermédiaire pour un rendu pixel-perfect
- **Tilesheet** : 1024x1024 pixels (64x64 tuiles)
- **Animation directionnelle** : Le héros change de sprite selon sa direction

### Gameplay
- **Déplacement** : ZQSD ou flèches directionnelles
- **Taille tuile** : 16 pixels par tuile
- **Mouvement** : 16 pixels par touche (1 tuile)
- **Collisions** : Système de hitbox pour les murs et objets
- **Carte** : 40x27 tuiles chargées depuis `level1.txt`

## Architecture du Code

### Classes Principales

#### `Main.java`
Point d'entrée du programme. Initialise :
- Le `TileManager` (gestion des tuiles)
- Le `Dungeon` (chargement et gestion du niveau)
- Le `Hero` (personnage joueur en Singleton)
- La `MainInterface` (fenêtre de jeu)

```java
// Chemins des ressources
String tileSheetPath = "dongon/img/tileSheet.png";
String levelPath = "dongon/src/level1.txt";
```

#### `GameRender.java`
Gère le rendu graphique avec la technique du **canvas intermédiaire** :
1. Dessine tout à taille native (16x16) sur un `BufferedImage`
2. Scale ce canvas entier x3 avec nearest neighbor
3. Affiche le résultat final

```java
// Canvas à taille native
private BufferedImage canvas;
private static final int SCALE = 3;
private static final int CANVAS_WIDTH = 640;   // 40 tuiles * 16px
private static final int CANVAS_HEIGHT = 432;  // 27 tuiles * 16px
```

#### `TileManager.java`
Charge et découpe le tilesheet en tuiles individuelles.

```java
// Découpage du tilesheet
tiles[x][y] = tileSheet.getSubimage(x * width, y * height, width, height);
```

#### `Dungeon.java`
Charge la carte depuis un fichier texte et crée les objets du jeu.

**Mapping des caractères** :
- `W` : Mur extérieur (0, 8)
- `I` : Mur intérieur (1, 8)
- `C` : Coffre (8, 7)
- `B` : Tonneau (8, 8)
- ` ` : Sol (1, 1)

```java
// Ajout systématique du sol sous les objets (sauf murs pleins)
if (c != 'W') {
    renderList.add(new Things(x, y, tileManager.getTile(1, 1)));
}
```

### Hiérarchie des Classes

```
Things (classe de base)
  ├── SolidThings (objets avec collision)
  │     ├── AnimatedThings (objets animés)
  │     │     └── DynamicThings (objets mobiles)
  │     │           └── Hero (personnage joueur - Singleton)
  │     └── HitBox (gestion des collisions)
```

#### `Things.java`
Classe de base pour tous les objets du jeu.

```java
// Dessine l'objet avec overlap pour éviter les gaps
public void draw(Graphics g) {
    g2d.setColor(new Color(128, 128, 128)); // Fond gris
    g2d.fillRect(x, y, width + 1, height + 1);
    g.drawImage(image, x, y, width + 1, height + 1, null);
}
```

#### `SolidThings.java`
Objets solides avec hitbox pour les collisions.

```java
// La hitBox a la même taille que l'objet
this.hitBox = new HitBox(x, y, this.width, this.height);
```

#### `DynamicThings.java`
Objets pouvant se déplacer avec détection de collisions.

```java
public void moveIfPossible(double dx, double dy, Dungeon dungeon) {
    // Détection de direction pour le héros
    if (this instanceof Hero) {
        Hero hero = (Hero) this;
        if (dx < 0) hero.setDirection(Hero.Direction.LEFT);
        else if (dx > 0) hero.setDirection(Hero.Direction.RIGHT);
        // ...
    }
    
    // Test de collision avant déplacement
    this.getHitBox().move(dx, dy);
    // Vérification avec tous les objets solides
    // ...
}
```

#### `Hero.java`
Personnage joueur avec pattern **Singleton** et animation directionnelle.

```java
// Enum pour les directions
public enum Direction {
    DOWN(0),  // Face (vers le bas)
    UP(1),    // Dos (vers le haut)
    LEFT(2),  // Gauche
    RIGHT(3); // Droite
}

// Change le sprite selon la direction
private void updateSprite() {
    this.image = tileManager.getTile(currentDirection.getTileX(), 4);
}
```

#### `HitBox.java`
Gestion des collisions rectangulaires.

```java
// Détection d'intersection entre deux hitbox
public boolean intersect(HitBox other) {
    return (this.x < other.x + other.width &&
            this.x + this.width > other.x &&
            this.y < other.y + other.height &&
            this.y + this.height > other.y);
}
```

#### `MainInterface.java`
Fenêtre principale avec gestion des inputs clavier.

```java
// Contrôles clavier
case KeyEvent.VK_LEFT:
case KeyEvent.VK_Q:
    hero.moveIfPossible(-16, 0, dungeon);
    break;
// ... autres directions

// Boucle de jeu : 20 FPS
Timer timer = new Timer(50, (e) -> panel.repaint());
```

## Mapping des Tuiles

### Coordonnées dans le Tilesheet

| Objet | Coordonnées (x, y) | Ligne |
|-------|-------------------|-------|
| Sol | (1, 1) | 2 |
| Mur extérieur | (0, 8) | 9 |
| Mur intérieur | (1, 8) | 9 |
| Coffre | (8, 7) | 8 |
| Tonneau | (8, 8) | 9 |
| Héros BAS | (0, 4) | 5 |
| Héros HAUT | (1, 4) | 5 |
| Héros GAUCHE | (2, 4) | 5 |
| Héros DROITE | (3, 4) | 5 |

## Compilation et Exécution

### Depuis le terminal

```bash
# Compiler
cd "c:\Users\melvy\Downloads\donjon project\dongon\src"
javac *.java

# Exécuter
java Main
```

### Depuis VSCode

Exécuter depuis le répertoire parent :
```bash
cd "c:\Users\melvy\Downloads\donjon project"
```

## Structure des Fichiers

```
donjon project/
└── dongon/
    ├── img/
    │   └── tileSheet.png          # Tilesheet 1024x1024
    └── src/
        ├── level1.txt             # Carte du niveau
        ├── Main.java              # Point d'entrée
        ├── GameRender.java        # Rendu graphique
        ├── TileManager.java       # Gestion des tuiles
        ├── Dungeon.java           # Gestion du niveau
        ├── MainInterface.java     # Fenêtre et inputs
        ├── Things.java            # Classe de base
        ├── SolidThings.java       # Objets solides
        ├── AnimatedThings.java    # Objets animés
        ├── DynamicThings.java     # Objets mobiles
        ├── Hero.java              # Personnage joueur
        └── HitBox.java            # Gestion collisions
```

## Techniques de Rendu

### Canvas Intermédiaire

Pour obtenir un rendu pixel-perfect sans artefacts :

1. **Création d'un canvas** à taille native (640x432)
2. **Dessin de tout le jeu** sur ce canvas à 16x16
3. **Scaling unique** du canvas entier x3 avec nearest neighbor
4. **Affichage** du résultat final

```java
// 1. Dessiner sur canvas natif
Graphics2D canvasG = canvas.createGraphics();
// ... dessiner tout ...
canvasG.dispose();

// 2. Scaler et afficher
g2d.setRenderingHint(KEY_INTERPOLATION, VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
g2d.drawImage(canvas, 0, 0, CANVAS_WIDTH * SCALE, CANVAS_HEIGHT * SCALE, null);
```

### Élimination des Gaps

Pour masquer les micro-gaps entre tuiles :
- **Fond gris** sous chaque tuile
- **Overlap de +1 pixel** lors du dessin

```java
g2d.setColor(new Color(128, 128, 128));
g2d.fillRect(x, y, width + 1, height + 1);
g.drawImage(image, x, y, width + 1, height + 1, null);
```

## Patterns de Conception

### Singleton (Hero)
Le héros est unique dans le jeu, implémenté avec Double-Checked Locking.

```java
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
```

### Héritage
Hiérarchie claire pour réutiliser le code et organiser les fonctionnalités.

## Contrôles

| Touche | Action |
|--------|--------|
| ↑ ou Z | Monter |
| ↓ ou S | Descendre |
| ← ou Q | Gauche |
| → ou D | Droite |

## Format du Fichier Level

Le fichier `level1.txt` utilise des caractères ASCII :

```
WWWWWWWWWWW
W         W
W  C   B  W
W         W
WWWWWWWWWWW
```

- `W` = Mur extérieur
- `I` = Mur intérieur
- `C` = Coffre
- `B` = Tonneau
- ` ` = Sol vide

## Améliorations Futures

- [ ] Ajouter des ennemis
- [ ] Système d'inventaire
- [ ] Plusieurs niveaux
- [ ] Effets sonores
- [ ] Animation de marche (plusieurs frames)
- [ ] Migrer vers LibGDX pour un meilleur rendu

## Auteur

Projet de jeu de donjon en Java - 2025
