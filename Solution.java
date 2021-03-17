import java.util.*;

public class Solution {

    public static void main(String[] args) {
        int result;
        String[] numsStr = args[0].split("");
        int[] nums = Arrays.stream(numsStr).mapToInt(c -> Integer.parseInt(c)).toArray();
        result = waysToDecode(nums);
        System.out.println(result);
    }    

    static int waysToDecode(int[] str) {
        System.out.println(Arrays.toString(str));
        int[] dp = new int[str.length];
        int last1 = str[str.length - 1];
        dp[str.length - 1] = last1 > 0 ? 1 : 0;
        int last2 = 10*str[str.length - 1] + str[str.length - 1];
        dp[str.length - 2] = dp[str.length - 1];
        if (0 < last2 && last2 < 27) {
            dp[str.length - 2]++;
        }
        for(int i = str.length - 3; i >= 0; i--) {
            int count = 0;
            if (str[i] > 0) {
                count += 1 * dp[i+1];
            }
            int digit2 = 10*str[i] + str[i+1];
            if (0 < digit2 && digit2 < 27) {
                count += 1 * dp[i+2];
            }
            dp[i] = count;
        }

        System.out.println(Arrays.toString(dp));
        return dp[0];
    }
}