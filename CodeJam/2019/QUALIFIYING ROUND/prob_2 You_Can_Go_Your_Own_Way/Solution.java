import java.math.BigInteger;
import java.util.Scanner;

class Solution {
	static class CaseInstance {
		int caseNumber;
		String path;
		CaseInstance(int caseNumber, String path) {
			this.caseNumber = caseNumber;
			this.path = path;
		}

		String solve() {
			String result = "";
			for (char chr : path.toCharArray()) {
				if (chr == 'E') {
					result += 'S';
				} else {
					result += 'E';
				}
			}
			return result;
		}

		@Override
		public String toString() {
			String result = solve();
			return String.format("Case #%d: %s", caseNumber, result);
		}
	}

	static class CaseDriver {
		Scanner scanner = new Scanner(System.in);
		int numberOfCases;

		CaseDriver() {	
			readFirstLine();
		}

		void readFirstLine() {
			numberOfCases = Integer.parseInt(scanner.nextLine());
		}

		void go() {
			for (int i = 0; i < numberOfCases; i++) {
				scanner.nextLine(); // drop input size
				String input = scanner.nextLine();
				CaseInstance caseInstance = new CaseInstance(i + 1, input);
				System.out.println(caseInstance);
			}
		}
	}
	
	public static void main(String[] args) {
		// CaseInstance icase;

		// int testInput = 940;
		// icase = new CaseInstance(1, testInput);
		// int oneVal = icase.solve();
		// System.out.println(testInput - oneVal + ", " + oneVal);

		CaseDriver driver = new CaseDriver();
		driver.go();
	}
}