package triplebyte;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * 
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        SpreadSheet ss = new SpreadSheet(4, 3);
        ss.updateCell(0, 0, "bob");
        ss.updateCell(0, 1, "10");
        ss.updateCell(0, 2, "foo");
        ss.updateCell(1, 0, "alice");
        ss.updateCell(1, 0, "a");
        ss.updateCell(1, 1, "5");
        ss.printPretty();
        ss.evaluateSum("sum(0:0-1:2)");
        System.out.println(ss.evaluateSum("sum(0:0-1:2)"));
    }
}
