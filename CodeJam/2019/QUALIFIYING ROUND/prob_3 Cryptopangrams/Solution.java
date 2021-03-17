import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Solution {
	static String getAlphabets() {
		String str = "";
		for(char c = 'A'; c <= 'Z'; c++) {
			str += c;
		}
		return str;
	}

	static class CaseInstance {
		abstract static class RecoveryPropagator {
			static class Node {
				BigInteger value, left = BigInteger.ONE, right = BigInteger.ONE;
				Boolean visited = false;
	
				Node(BigInteger value) {
					this.value = value;
				}
	
				@Override
				public String toString() {
					return String.format("l:%d, v:%d, r:%d", left, value, right);
				}
			}
			Node[] nodes;
			int firstIndex;
			BigInteger firstFactor;
			RecoveryPropagator(int firstIndex, BigInteger[] codes, BigInteger firstFactor) {
				nodes = Stream.of(codes).map(c -> new Node(c)).toArray(Node[]::new);
				this.firstIndex = firstIndex;
				this.firstFactor = firstFactor;
				process();
			}

			BigInteger[] recovered() {
				List<BigInteger> list = new LinkedList<>();
				list.add(nodes[0].left);
				for (Node node : nodes) {
					list.add(node.right);
				}
				return list.toArray(new BigInteger[list.size()]);
			}

			abstract void process();
		}
		
		static class RecoveryPropagator1 extends RecoveryPropagator {
			RecoveryPropagator1(int firstIndex, BigInteger[] codes, BigInteger firstFactor) {
				super(firstIndex, codes, firstFactor);
			}
			
			void setFirstLeftAndRight(int index, BigInteger factor1, Node[] arr) {
				BigInteger factor2 = arr[index].value.divide(factor1);
				arr[index].left = factor1;
				arr[index].right = factor2;
				if (
					(index - 1 >= 0 && arr[index - 1].value.mod(factor2).equals(BigInteger.ZERO)) ||
					(index + 1 < arr.length && arr[index + 1].value.mod(factor1).equals(BigInteger.ZERO))
				) {
					arr[index].left = factor2;
					arr[index].right = factor1;
				}
			}

			void propagate(int index, Node[] arr) {
				arr[index].visited = true;
				// System.out.println(index);
				if (index - 1 >= 0 && arr[index - 1].visited == false) {
					arr[index - 1].right = arr[index].left;
					arr[index - 1].left = arr[index - 1].value.divide(arr[index].left);
					propagate(index - 1, arr);
				}

				if (index + 1 < arr.length && arr[index + 1].visited == false) {
					arr[index + 1].left = arr[index].right;
					// System.out.println(arr[index + 1]);
					arr[index + 1].right = arr[index + 1].value.divide(arr[index].right);
					// System.out.println(arr[index + 1]);
					propagate(index + 1, arr);
				}
			}

			void process() {
				setFirstLeftAndRight(firstIndex, firstFactor, nodes);
				propagate(firstIndex, nodes);
			}
		}

		static class RecoveryPropagator2 extends RecoveryPropagator {
			RecoveryPropagator2(int firstIndex, BigInteger[] codes, BigInteger firstFactor) {
				super(firstIndex, codes, firstFactor);
			}
			
			@Override
			void process() {
				if (nodes[1].value.mod(firstFactor).equals(BigInteger.ZERO)) {
					nodes[0].left = nodes[0].value.divide(firstFactor);
					nodes[0].right = firstFactor;
					nodes[1].left = nodes[0].right;
				} else {
					nodes[0].right = nodes[0].value.divide(firstFactor);
					nodes[0].left = firstFactor;
					nodes[1].left = nodes[0].right;
				}
				for (int i = 1; i < nodes.length; i++) {
					// System.out.println(Arrays.toString(nodes));
					nodes[i].right = nodes[i].value.divide(nodes[i].left);
					if (i + 1 < nodes.length) {
						nodes[i+1].left = nodes[i].right;
					}
				}

			}
		}

		static class StringGenerator {
			BigInteger[] codes;
			StringGenerator(BigInteger[] codes) {
				this.codes = codes;
			}

			@Override
			public String toString() {
				List<BigInteger> list = Stream.of(codes).distinct().sorted().collect(Collectors.toList());	
				HashMap<BigInteger, String> map = new HashMap<>();
				char[] cs = getAlphabets().toCharArray();
				for (int i = 0; i < 26; i++) {
					map.put(list.get(i), new Character(cs[i]).toString());
				}
				return Stream.of(codes).map(c -> map.get(c)).reduce("", (a, b) -> a + b);
			}
		}

		int caseNumber;
		BigInteger limit;
		BigInteger[] codes;
		CaseInstance(int caseNumber, BigInteger limit, String[] codes) {
			this.caseNumber = caseNumber;
			this.limit = limit;
			this.codes = Stream
						.of(codes)
						.map(c -> new BigInteger(c))
						.toArray(BigInteger[]::new);
		}

		static class FactorWithIndex {
			int index;
			BigInteger factor;
			FactorWithIndex(int index, BigInteger factor) {
				this.index = index;
				this.factor = factor;
			}

			@Override
			public String toString() {
				return String.format("(%d, %d)", index, factor);
			}
		}

		FactorWithIndex factorize1() {
			FactorWithIndex factor = null;
			boolean isFound = false;
			for (BigInteger i = new BigInteger("2"); i.compareTo(limit) != 1; i = i.add(BigInteger.ONE)) {
				for (int j = 0; j < codes.length; j++) {
					if (codes[j].mod(i).equals(BigInteger.ZERO)) {
						factor = new FactorWithIndex(j, i);
						isFound = true;
						break;
					}
				}
				if (isFound)
					break;
			}
			return factor;
		}

		FactorWithIndex factorize2() {
			FactorWithIndex factor = null;
			for (BigInteger i = new BigInteger("2"); i.compareTo(limit) != 1; i = i.add(BigInteger.ONE)) {
				if (codes[0].mod(i).equals(BigInteger.ZERO)) {
					factor = new FactorWithIndex(0, i);
					break;
				}
			}
			return factor;
		}

		String solve() {
			String result = "";
			FactorWithIndex factor = factorize2();
			RecoveryPropagator rp = new RecoveryPropagator2(factor.index, codes, factor.factor);
			StringGenerator g = new StringGenerator(rp.recovered());
			result = g.toString();
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
				String line1 = scanner.nextLine();
				String line2 = scanner.nextLine();
				CaseInstance caseInstance = new CaseInstance(i + 1, new BigInteger(line1.split(" ")[0]), line2.split(" "));
				System.out.println(caseInstance);
			}
		}
	}

	static class TestGenerator {
		int numberOfCases;
		
		TestGenerator(int numberOfCases) {
			this.numberOfCases = numberOfCases;
		}

		List<Character> generateString() {
			String alphabets = getAlphabets();
			List<Character> list = new ArrayList<>();
			int maxLen = 101;
			while (list.size() < maxLen) {
				for (char chr : alphabets.toCharArray()) {
					list.add(chr);
					if (list.size() == maxLen) {
						break;
					}
				}
			}
			Collections.reverse(list);
			return list;
		}

		String[] generateCodesFrom(int caseNumber, List<BigInteger> primes) {
			List<Character> charList = generateString();
			System.out.printf("Case #%d: %s\n", caseNumber, charList.stream().map(e->e.toString()).reduce((acc, e) -> acc + e).get());
			List<BigInteger> encodings = charList.stream().map(c -> primes.get((int)c - (int)'A')).collect(Collectors.toList());
			List<String> codes = new ArrayList<>();
			for (int i = 1; i < encodings.size(); i++) {
				codes.add(encodings.get(i).multiply(encodings.get(i - 1)).toString());
			}
			return codes.toArray(new String[codes.size()]);
		}

		void runTest(int caseNumber) {
			BigInteger num = new BigInteger("3");
			List<BigInteger> list = new ArrayList<>();

			while (list.size() < 101) {
				num = num.nextProbablePrime();
				if (Math.random() > 0.99) {
					list.add(num);
				}
			}

			num = num.nextProbablePrime();
			String[] codes = generateCodesFrom(caseNumber, list);
			System.out.println(num + " " + codes.length);
			System.out.println(String.join(" ", codes));
		}
		
		void go() {
			System.out.println(numberOfCases);
			for (int i = 0; i < numberOfCases; i++) {
				runTest(i + 1);
			}
		}
	}
	
	public static void main(String[] args) {
		CaseDriver driver = new CaseDriver();
		driver.go();

		// TestGenerator tGen = new TestGenerator(1);
		// tGen.go();
	}
}