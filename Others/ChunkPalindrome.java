class ChunkPalindrome {
	public static void main(String[] args) {
		ChunkPalindrome solver = new ChunkPalindrome();
		int result = solver.solve("valve");
		System.out.println(result);

		result = solver.solve("voabcvo");
		System.out.println(result);

		result = solver.solve("vovo");
		System.out.println(result);

		result = solver.solve("volvolvo");
		System.out.println(result);

		result = solver.solve("volvol");
		System.out.println(result);

		result = solver.solve("aaaaaa");
		System.out.println(result);

		result = solver.solve("aaaaaalaaaaaalaaaaaa");
		System.out.println(result);
	}
	
	int solve(String word) {
		if (word.length() == 0) return 0;
		int matchedFrom = getMatchFrom(word);
		if (matchedFrom > 0) {
			int length = word.length() - matchedFrom;
			return 2 + solve(word.substring(length, matchedFrom));
		} else {
			return 1;
		}
	}

	int solve1(String word) {
		int i = 0;
		int j = word.length()-1;
		StringBuilder sb1 = new StringBuilder();
		StringBuilder sb2 = new StringBuilder();
		int chunks = 0;
		while(i<j) {
			sb1.append(word.charAt(i));
			sb2.append(word.charAt(j));
			if(sb1.toString().equals(sb2.reverse().toString())) {
				chunks+=2;
				sb1.setLength(0);
				sb2.setLength(0);
			}
			i++; j--;
		}
		return sb1.length() == 0 ? chunks : chunks+1;
	}


	int getMatchFrom(String word) {
		int matchedFrom = -1;
		for (int i = word.length() - 1; i >= word.length() / 2; i--) {
			if (word.charAt(0) == word.charAt(i)) {
				if (doSubstringsMatch(word, 0, i, word.length() - i)) {
					matchedFrom = i;
				}
				break;
			}
		}
		return matchedFrom;
	}

	boolean doSubstringsMatch(String string, int lo1, int lo2, int length) {
		for (int i = 0; i < length; i++) {
			if (string.charAt(lo1) != string.charAt(lo2)) {
				return false;
			}
			lo1++;
			lo2++;
		}

		return true;
	}
}