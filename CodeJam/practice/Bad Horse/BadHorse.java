import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;

/**
 * @author Zhenyi 2013 Dec 21, 2013 11:37:20 AM
 */
public class BadHorse {
	static String[] st;
	static int M;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader(
				args[0]));
		FileWriter out = new FileWriter(
				args[1]);
		// BufferedReader in = new BufferedReader(new
		// FileReader("C:/Users/Zhenyi/Downloads/A-small-practice-2.in"));
		// FileWriter out = new
		// FileWriter("C:/Users/Zhenyi/Downloads/A-small-practice-2.out");

		int T = new Integer(in.readLine());

		for (int cases = 1; cases <= T; cases++) {
			M = new Integer(in.readLine());
			boolean result = false;
			st = new String[M];
			for (int i = 0; i < M; i++) {
				st[i] = in.readLine();
			}

			HashSet<String> st1 = new HashSet<String>();
			HashSet<String> st2 = new HashSet<String>();
			result = travesal(M, st1, st2);

			if (result) {
				out.write("Case #" + cases + ": " + "Yes" + "\n");
			} else {
				out.write("Case #" + cases + ": " + "No" + "\n");
			}
		}
		in.close();
		out.flush();
		out.close();
	}

	private static boolean travesal(int m, HashSet<String> st1,
			HashSet<String> st2) {
		// TODO Auto-generated method stub
		if (m == 0) {
			return true;
		}
		int n = M - m;
		String[] s = st[n].split("\\s");
		if (!st1.contains(s[1]) && !st2.contains(s[0])) {
			int tmp1 = 0;
			int tmp2 = 0;
			if (st1.contains(s[0]))
				tmp1 = 1;
			if (st2.contains(s[1]))
				tmp2 = 1;
			st1.add(s[0]);
			st2.add(s[1]);
			if (travesal(m - 1, st1, st2)) {
				return true;
			}
			if (tmp1 == 0) {
				st1.remove(s[0]);
			}
			if (tmp2 == 0) {
				st2.remove(s[1]);
			}
		}

		if (!st1.contains(s[0]) && !st2.contains(s[1])) {
			int tmp1 = 0;
			int tmp2 = 0;
			if (st1.contains(s[1]))
				tmp1 = 1;
			if (st2.contains(s[0]))
				tmp2 = 1;
			st1.add(s[1]);
			st2.add(s[0]);
			if (travesal(m - 1, st1, st2)) {
				return true;
			}
			if (tmp1 == 0) {
				st1.remove(s[1]);
			}
			if (tmp2 == 0) {
				st2.remove(s[0]);
			}
		}
		return false;

	}
}
