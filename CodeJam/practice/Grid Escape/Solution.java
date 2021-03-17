import java.util.Scanner;
import java.util.stream.Stream;

// https://codejam.withgoogle.com/2018/challenges/0000000000050fc5/dashboard

public class Solution {
	class Grid {
		class Pair {
			int i, j;

			Pair(int i, int j) {
				this.i = i;
				this.j = j;
			}

			@Override
			public String toString() {
				return String.format("(%d, %d)", i, j);
			}
		}

		int rows, cols;
		char[][] arrangement;

		Grid(int rows, int cols) {
			this.rows = rows;
			this.cols = cols;
			initArrangement();
		}

		void initArrangement() {
			arrangement = new char[rows][cols];
			for (char[] row : arrangement) {
				for (int i = 0; i < row.length; i++) {
					row[i] = 'N';
				}
			}
		}

		@Override
		public String toString() {
			String str = "";
			for (char[] row : arrangement) {
				for (char chr : row) {
					str += chr;
				}
				str += "\n";
			}
			return str;
		}

		Pair makePairFor(int numEscape) {
			int i = numEscape / cols;
			int j = numEscape - i * cols;
			return new Pair(i, j);
		}

		boolean isPossible(Pair pair) {
			if (cols - pair.j == 1 && rows - pair.i - 1 == 0) {
				return false;
			}
			return true;
		}

		void fillBlackHolesUsing(Pair pair) {
			for (int i = pair.i; i < rows; i++) {
				int jStart = 0;
				boolean isFirstRow = i == pair.i;
				if (isFirstRow) {
					jStart = pair.j;
				}
				for (int j = jStart; j < cols; j++) {
					if (isFirstRow && (rows - pair.i - 1) > 0) {
						arrangement[i][j] = 'S';
					} else if (j == cols - 1) {
						arrangement[i][j] = 'W';
					} else {
						arrangement[i][j] = 'E';
					}
				}
			}
		}
	}

	String solveCase(int rows, int cols, int numEscape) {
		Grid grid = new Grid(rows, cols);
		Grid.Pair pair = grid.makePairFor(numEscape);
		if (grid.isPossible(pair)) {
			grid.fillBlackHolesUsing(pair);
			return grid.toString();
		} else {
			return "";
		}
	}

	String solveCaseNumber(int rows, int cols, int numEscape, int caseNumber) {
		String solution = solveCase(rows, cols, numEscape);
		String caseString = String.format("Case #%d: ", caseNumber);
		if (solution.equals("")) {
			return caseString + "IMPOSSIBLE\n";
		} else {
			return caseString + "POSSIBLE\n" + solution;
		}
	}

	void solveCasesFromSTDIN() {
		Scanner scanner = new Scanner(System.in);
		String line = scanner.nextLine();
		int numCases = Integer.parseInt(line);
		int[] input;
		String output;
		for (int i = 0; i < numCases; i++) {
			line = scanner.nextLine();
			input = Stream.of(line.split(" ")).mapToInt(elt -> Integer.parseInt(elt)).toArray();
			output = solveCaseNumber(input[0], input[1], input[2], i + 1);
			System.out.print(output);
		}
	}

	void makeTests() {
		for (int i = 1; i < 9; i++) {
			for (int j = 1; j < 9; j++) {
				for (int k = 0; k < i * j; k++) {
					System.out.println(i + " " + j + " " + k);
				}
			}
		}
	}

	public static void main(String[] args) {
		Solution sol = new Solution();
		sol.solveCasesFromSTDIN();
		// sol.makeTests();
		// String result = sol.solveCase(4, 4, 11);
		// sol.test();
		// System.out.println(result);
	}
}