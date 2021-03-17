import java.util.Scanner;
import java.util.stream.Stream;

class Solution {
	static class CaseSolver {
		int caseNumber;
		char[][] grid;
		
		CaseSolver(int caseNumber, char[][] grid) {
			this.caseNumber = caseNumber;
			this.grid = grid;
		}

		String gridToString(char[][] grid) {
			String[] rows = Stream.of(grid).map(r -> new String(r)).toArray(String[]::new);
			return String.join("\n", rows);
		}

		void horizontalFiling() {
			for (int i = 0; i < grid.length; i++) {
				char lastRead = '?';
				for (int j = 0; j < grid[0].length; j++) {
					if (grid[i][j] != '?') {
						if (lastRead == '?') {
							for (int k = j; k >= 0; k--) {
								grid[i][k] = grid[i][j];
							}
						}
						lastRead = grid[i][j];
					} else {
						grid[i][j] = lastRead;
					}
				}
			}
		}

		void verticalFiling() {
			for (int j = 0; j < grid[0].length; j++) {
				char lastRead = '?';
				for (int i = 0; i < grid.length; i++) {
					if (grid[i][j] != '?') {
						if (lastRead == '?') {
							for (int k = i; k >= 0; k--) {
								grid[k][j] = grid[i][j];
							}
						}
						lastRead = grid[i][j];
					} else {
						grid[i][j] = lastRead;
					}
				}
			}
		}

		void solve() {
			verticalFiling();
			horizontalFiling();
		}

		@Override
		public String toString() {
			return String.format("Case #%d: %s%s", caseNumber, System.lineSeparator(), gridToString(grid));
		}
	}
	
	static class InputSolver {
		int numberOfCases;

		CaseSolver readCase(int caseNumber, Scanner scanner) {
			String[] dims_str = scanner.nextLine().split(" ");
			int[] dims = Stream.of(dims_str).mapToInt(d -> Integer.parseInt(d)).toArray();
			char[][] grid = new char[dims[0]][dims[1]];
			for (int i = 0; i < dims[0]; i++) {
				String[] row = scanner.nextLine().split("");
				for (int j = 0; j < dims[1]; j++) {
					grid[i][j] = row[j].toCharArray()[0];
				}
			}
			return new CaseSolver(caseNumber, grid);
		}
		
		void solve() {
			Scanner scanner = new Scanner(System.in);
			numberOfCases = Integer.parseInt(scanner.nextLine());
			for (int i = 0; i < numberOfCases; i++) {
				CaseSolver caseSolver = readCase(i+1, scanner);
				caseSolver.solve();
				System.out.println(caseSolver);
			}
			scanner.close();
		}
	}
	
	public static void main(String[] args) {
		InputSolver solver = new InputSolver();
		solver.solve();
	}
}