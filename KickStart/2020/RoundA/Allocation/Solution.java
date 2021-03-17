import java.util.Arrays;
import java.util.Scanner;

public class Solution {
	static class CaseSolver {
		int caseNumber, result;
		int[] price;
		int budget;

		CaseSolver(int caseNumber, int[] price, int budget) {
			this.caseNumber = caseNumber;
			this.price = price;
			this.budget = budget;
		}

		int maxHouses(int[] price, int budget) {
			Arrays.sort(price);
			int count = 0, currInd = 0;
			while (currInd < price.length && price[currInd] <= budget) {
				count++;
				budget -= price[currInd];
				currInd++;
			}
			return count;
		}

		void solve() {
			result = maxHouses(price, budget);
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
			int budget = Integer.parseInt(input1[1]);
			String[] input2 = scanner.nextLine().split(" ");
			return new CaseSolver(caseNumber, Arrays.stream(input2).mapToInt(x -> Integer.parseInt(x)).toArray(), budget);
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