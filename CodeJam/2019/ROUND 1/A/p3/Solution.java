import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

class Solution {
	static class CaseSolver {
		static class ReverseTrie {
			static class TrieNode {
				char chr = '0';
				int count = 1;
				HashMap<Character, TrieNode> children = new HashMap<>();

				void increment() {
					count += 1;
				}
			}

			TrieNode head = new TrieNode();
			
			ReverseTrie(String[] words) {
				for (String word : words) {
					add(new StringBuilder(word).reverse().toString());
				}
			}

			void add(TrieNode node, char[] word, int currentIndex, int endPoint) {
				if (currentIndex == endPoint) {
					return;
				}
				if (node.children.containsKey(word[currentIndex])) {
					node.children.get(word[currentIndex]).increment();
				} else {
					TrieNode newNode = new TrieNode();
					newNode.chr = word[currentIndex];
					node.children.put(word[currentIndex], newNode);
				}
				add(node.children.get(word[currentIndex]), word, currentIndex + 1, endPoint);
			}

			void add(String word) {
				char[] chrs = word.toCharArray();
				add(head, chrs, 0, chrs.length);
			}

			int rhymeCount() {
				int count = 0;
				for (Map.Entry<Character, TrieNode> entry : head.children.entrySet()) {
					count += rhymeCount(entry.getValue());
				}
				return count;
			}
			
			private int rhymeCount(TrieNode node) {
				int count = 0;
				for (Map.Entry<Character, TrieNode> entry : node.children.entrySet()) {
					if (entry.getValue().count != 1) {
						count += rhymeCount(entry.getValue());
					}
				}
				count += Math.min(2, (node.count - count));
				if (count % 2 != 0) {
					count -= 1;
				}
				return count;
			}

			String toString(TrieNode node) {
				Queue<TrieNode> queue = new LinkedList<>();
				queue.add(node);
				String string = "";
				TrieNode temp;
				while (!queue.isEmpty()) {
					temp = queue.remove();
					if (temp.chr != '[' && temp.chr != ']') {
						string += String.format(", {%c:%d}", temp.chr, temp.count);
					} else {
						string += temp.chr;
					}
					TrieNode left = new TrieNode();
					if (temp.children.entrySet().size() > 0) {
						left.chr = '[';
						queue.add(left);
						for (Map.Entry<Character, TrieNode> entry : temp.children.entrySet()) {
							queue.add(entry.getValue());
						}
						TrieNode right = new TrieNode();
						right.chr = ']';
						queue.add(right);
					}

				}
				return string;
			}

			@Override
			public String toString() {
				return toString(head);
			}
		}
		
		int caseNumber;
		String[] words;
		int count;	
		CaseSolver(int caseNumber, String[] words) {
			this.caseNumber = caseNumber;
			this.words = words;
		}

		void solve() {
			ReverseTrie trie = new ReverseTrie(words);
			count = trie.rhymeCount();
		}

		@Override
		public String toString() {
			return String.format("Case #%d: %d", caseNumber, count);
		}
	}
	
	static class InputSolver {
		int numberOfCases;

		CaseSolver readCase(int caseNumber, Scanner scanner) {
			int wordCount = Integer.parseInt(scanner.nextLine());
			String[] words = new String[wordCount];
			for (int i = 0; i < wordCount; i++) {
				words[i] = scanner.nextLine();
			}
			return new CaseSolver(caseNumber, words);
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