package venom;

import java.util.Scanner;

/**
 * Hello world!
 */
public final class App {
    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                game.humanMove(scanner);
                game.printGrid();
                game.aiMove();
                game.printGrid();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                break;
            }
        }
        System.out.println("Game Ended");
    }
}
