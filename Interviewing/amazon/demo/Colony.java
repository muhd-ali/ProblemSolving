// IMPORT LIBRARY PACKAGES NEEDED BY YOUR PROGRAM
// SOME CLASSES WITHIN A PACKAGE MAY BE RESTRICTED
// DEFINE ANY CLASS AND METHOD NEEDED
// CLASS BEGINS, THIS CLASS IS REQUIRED
public class Colony
{
  //METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
  public static int[] cellCompete(int[] cells, int days)
  {
    int[] mutated = new int[cells.length];
    System.arraycopy(cells, 0, mutated, 0, cells.length);
    for (int j = 0; j < days; j++) {
      for (int i = 0; i < cells.length; i++) {
        if (i == 0) {
          if (cells[1] == 0) {
            mutated[0] = 0;
          } else {
            mutated[0] = 1;
          }
        } else if (i == cells.length - 1) {
          if (cells[cells.length - 2] == 0) {
            mutated[cells.length - 1] = 0;
          } else {
            mutated[cells.length - 1] = 1;
          }
        } else {
          if (cells[i-1] == cells[i + 1]) {
            mutated[i] = 0;
          } else {
            mutated[i] = 1;
          }
        }
      }
      System.arraycopy(mutated, 0, cells, 0, cells.length);
    }
    return mutated;
  }
  // METHOD SIGNATURE ENDS
}