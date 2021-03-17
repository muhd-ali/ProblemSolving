package venom;

import java.util.Scanner;

/**
 * TicTacToe
 */
public class TicTacToe {

    private char[][] grid;
    private char EMPTY_SPACE = '-';

    TicTacToe() {
        grid = new char[3][3];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                grid[i][j] = EMPTY_SPACE;
            }
        }
    }

    void addToken(char token, Position position) {
        grid[position.i][position.j] = token;
    }

    private String gridToString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                sb.append(grid[i][j]);
                if (j < grid.length - 1) {
                    sb.append("|");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    boolean isGridFull() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[i][j] == EMPTY_SPACE) {
                    return false;
                }
            }
        }
        return true;
    }

    void aiMove() throws Exception {
        if (!isGridFull()) {
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid.length; j++) {
                    if (grid[i][j] == EMPTY_SPACE) {
                        grid[i][j] = 'O';
                        return;
                    }
                }
            }
        } else {
            throw new Exception("Grid is Full");
        }
    }

    void printGrid() {
        System.out.println(gridToString());
    }

    static Position parseInput(String input) {
        String[] words = input.split(",");
        int i = Integer.parseInt(words[0]);
        int j = Integer.parseInt(words[0]);
        // if (i >= 0 && i <= 2 && )
        return null;
    }

    void humanMove(Scanner scanner) {
        System.out.println("i,j");
        String input = scanner.next();
        Position humanPos = parseInput(input);
        addToken('X', humanPos);
    }
}