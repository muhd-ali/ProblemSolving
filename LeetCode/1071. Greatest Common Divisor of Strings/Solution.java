import java.math.BigInteger;
import java.util.Arrays;

class Solution {
    public String gcdOfStrings(String str1, String str2) {
        int lengthsGCD = gcd(str1.length(), str2.length());
        if (doesGCDExist(str1, str2, lengthsGCD)) {
            return str1.substring(0, lengthsGCD);
        }
        return "";
    }

    public static int gcd(int n1, int n2) {
        return BigInteger.valueOf(n1).gcd(BigInteger.valueOf(n2)).intValue();
    }

    public static int lcm(int n1, int n2, int gcdOfArgs) {
        return (n1 * n2) / gcdOfArgs;
    }

    String multiply(String str, int num) {
        StringBuilder stringBuilder;
        stringBuilder = new StringBuilder(str);
        for (int i = 0; i < num - 1; i++) {
            stringBuilder.append(str);
        }
        return stringBuilder.toString();
    }

    Boolean doesGCDExist(String str1, String str2, int lengthsGCD) {
        int lengthsLCM = lcm(str1.length(), str2.length(), lengthsGCD);
        String str1Multiplied = multiply(str1, lengthsLCM / str1.length());
        String str2Multiplied = multiply(str2, lengthsLCM / str2.length());
        return str1Multiplied.equals(str2Multiplied);
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String result;

        result = sol.gcdOfStrings("abababab", "abab");
        System.out.println(result);

        result = sol.gcdOfStrings("abcabc", "abc");
        System.out.println(result);

        result = sol.gcdOfStrings("leet", "code");
        System.out.println(result);
    }
}