import java.math.BigInteger;
import java.util.Scanner;

class Solution {
	static class CaseInstance {
		int caseNumber;
		String amount;
		CaseInstance(int caseNumber, String amount) {
			this.caseNumber = caseNumber;
			this.amount = amount;
		}

		BigInteger createNumFor(int index, int numLength) {
			String num = "1";
			for (int i = 0; i < (numLength - index - 1); i++) {
				num += "0";
			}
			return new BigInteger(num);
		}

		BigInteger solve() {
			BigInteger oneVal = new BigInteger("0");
			
			char[] numString = amount.toCharArray();
			for (int i = 0; i < numString.length; i++) {
				if (numString[i] == '4') {
					oneVal = oneVal.add(createNumFor(i, numString.length));
				}
			}

			return oneVal;
		}

		@Override
		public String toString() {
			BigInteger oneVal = solve();
			return String.format("Case #%d: %d %d", caseNumber, new BigInteger(amount).subtract(oneVal), oneVal);
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