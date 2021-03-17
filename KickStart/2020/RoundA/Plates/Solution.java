import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Solution {
	static class CaseSolver {
		int caseNumber, result;
		int[][] stack;
		int numPlates;

		CaseSolver(int caseNumber, int[][] stack, int numPlates) {
			this.caseNumber = caseNumber;
			this.stack = stack;
			this.numPlates = numPlates;
		}

		Map<String, Integer> memory;
		int maxBeauty(int currStack, int[][] stack, int numPlates) {
			if (currStack >= stack.length || numPlates == 0) {
				return 0;
			}
			String key = String.format("%d,%d", currStack, numPlates);
			if (memory.containsKey(key)) {
				return memory.get(key);
			}
			int result = maxBeauty(currStack + 1, stack, numPlates), currBeauty = 0;
			for (int i = 0; i < stack[currStack].length; i++) {
				if(i + 1 <= numPlates) {
					currBeauty += stack[currStack][i];
					result = Math.max(
						result,
						currBeauty + 
						maxBeauty(currStack + 1, stack, numPlates - (i + 1))
					);
				} else {
					break;
				}
			}
			memory.put(key, result);
			return result;
		}

		void solve() {
			memory = new HashMap<>();
			result = maxBeauty(0, stack, numPlates);
		}

		@Override
		public String toString() {
			return String.format("Case #%d: %s", caseNumber, result);
		}

	}

	static class InputSolver {
		int numberOfCases;

		CaseSolver readCase(int caseNumber, Scanner scanner) {
			String[] input1 = scanner.nextLine().split(" ");
			int inputN = Integer.parseInt(input1[0]);
			int inputK = Integer.parseInt(input1[1]);
			int inputP = Integer.parseInt(input1[2]);
			int[][] grid = new int[inputN][inputK];
			for (int i = 0; i < inputN; i++) {
				String[] input2 = scanner.nextLine().split(" ");
				grid[i] = Arrays.stream(input2).mapToInt(x -> Integer.parseInt(x)).toArray();
			}
			return new CaseSolver(caseNumber, grid, inputP);
		}

		void solve() {
			Scanner scanner = new Scanner(System.in);
			numberOfCases = Integer.parseInt(scanner.nextLine());
			for (int i = 0; i < numberOfCases; i++) {
				CaseSolver caseSolver = readCase(i + 1, scanner);
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