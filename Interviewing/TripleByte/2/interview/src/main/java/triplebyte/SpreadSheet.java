package triplebyte;

public class SpreadSheet {

    int rows, cols;
    String[][] grid;

    SpreadSheet(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.grid = new String[rows][cols];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                this.grid[i][j] = "";
            }
        }
    }

    void updateCell(int row, int col, String string) {
        this.grid[row][col] = string;
    }

    void printUgly() {
        for (int i = 0; i < this.grid.length; i++) {
            for (int j = 0; j < this.grid[i].length; j++) {
                System.out.print(this.grid[i][j]);
                if (j < this.grid[i].length - 1) {
                    System.out.print("|");
                }
            }
            System.out.print("\n");
        }
    }

    int[] calcMaxLengthForCol() {
        int[] result = new int[grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                result[j] = Math.max(result[j], grid[i][j].length());
            }
        }
        return result;
    }

    void printPretty() {
        int[] maxes = calcMaxLengthForCol();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (this.grid[i][j] != null) {
                    System.out.print(this.grid[i][j]);
                }
                for (int k = 0; k < maxes[j] - this.grid[i][j].length(); k++) {
                    System.out.print(" ");
                }
                if (j < this.grid[i].length - 1) {
                    System.out.print("|");
                }
            }
            System.out.print("\n");
        }
    }

    int evaluateSum(String input) {
        String numString = input.substring(4, input.length() - 1);
        String[] numStrings = numString.split("-");
        int sum = 0;
        for (int i = Integer.parseInt(numStrings[0].split(":")[0]); i < Integer
                .parseInt(numStrings[1].split(":")[0]); i++) {
            for (int j = Integer.parseInt(numStrings[0].split(":")[1]); j < Integer
                    .parseInt(numStrings[1].split(":")[1]); j++) {
                try {
                    int num = Integer.parseInt(this.grid[i][j]);
                    sum += num;
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
        }
        return sum;
    }
}