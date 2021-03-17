import java.lang.Math;
import java.util.Arrays;

class Solution {
  public static void main(String[] args) {
    var dividend = 10;
    var divisor = 10;
    var obj = new Solution();
    var output = obj.maxIncreaseKeepingSkyline(new int[][] {
        new int[] {3, 0, 8, 4},
        new int[] {2, 4, 5, 7},
        new int[] {9, 2, 6, 3},
        new int[] {0, 3, 1, 0},
      });
    System.out.println(output);
  }

  public int maxIncreaseKeepingSkyline(int[][] grid) {
    int[] colMax = new int[grid.length];
    int[] rowMax = new int[grid[0].length];

    for (int i=0; i<grid.length; i++) {
      rowMax[i] = grid[i][0];
      for (int j=0; j<grid[i].length; j++) {
        if (i == 0) {
          colMax[j] = grid[0][j];
        } else {
          if (grid[i][j] > colMax[j]) {
            colMax[j] = grid[i][j];
          }
        }
        if (grid[i][j] > rowMax[i]) {
          rowMax[i] = grid[i][j];
        }
      }
    }

    int diff = 0;
    for (int i=0; i<grid.length; i++) {
      for (int j=0; j<grid[i].length; j++) {
        diff += Math.min(rowMax[i], colMax[j]) - grid[i][j];
      }
    }
    return diff;
  }
}
