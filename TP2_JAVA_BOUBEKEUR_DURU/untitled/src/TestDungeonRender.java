public class TestDungeonRender {
    public static void main(String[] args) {

        TileManager tm = new TileManager(32, 32, "tileSheet.png");
        Dungeon d = new Dungeon(10, 10, tm);

        System.out.println("Render list size : " + d.getRenderList().size());
    }
}
