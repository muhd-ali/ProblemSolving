import java.util.*;

class NumberOfWays2 {
    // https://www.geeksforgeeks.org/ways-sum-n-using-array-elements-repetition-allowed/

    static int solve(int N, int[] arr) {
        int[] dp = new int[N+1];
        dp[0] = 1;
        Arrays.sort(arr);
        for(int i = 1; i <= N; i++) {
            if (i < arr[0]) {
                dp[i] = 0;
            } else {
                for(int j = 0; j < arr.length; j++) {
                    int inner = i - arr[j];
                    dp[i] += inner >= 0 ? dp[inner] : 0;
                }
            }
        }
        // System.out.println(Arrays.toString(arr));
        // System.out.println(Arrays.toString(dp));
        return dp[N];
    }
    
    // Driver code  
    public static void main(String[] args)  
    { 
        int N = 14;
        int[] arr = new int[]{12, 3, 1, 9};
        System.out.println(solve(N, arr)); 
    }
}