public class NumberOfDecimalNums {
    static int DP_s = 9;
    static int solve(int len)  
    { 
        // DP[i][j] is going to store monotone 
        // numbers of length i+1 considering 
        // j+1 digits (1, 2, 3, ..9) 
        int[][] DP = new int[len][DP_s]; 
      
        // Unit length numbers 
        for (int i = 0; i < DP_s; ++i) 
        DP[0][i] = i + 1; 
      
        // Building dp[] in bottom up 
        for (int i = 1; i < len; ++i) 
             for (int j = 1; j < DP_s; ++j) 
                DP[i][j] = DP[i - 1][j - 1]  
                             + DP[i][j - 1]; 
      
        return DP[len - 1][DP_s - 1]; 
    }

    static int solve_1(int n) {
        int[][] dp = new int[n][9];
        for(int j = 1; j < 9; j++) {
            dp[1][j] = 9 - j;
        }

        for (int i = 2; i < n; i++) {
            for (int j = 1; j < 9; j++) {
                int sum = 0;
                for (int k = j + 1; k < 9; k++) {
                    sum += dp[i - 1][k];
                }
                dp[i][j] = sum;
            }
        }

        int sum = 0;
        for (int j = 1; j < 9; j++) {
            sum += dp[n-1][j];
        }
        return sum;
    }
    
    public static void main(String[] args)  
    { 
        int n = 5; 
        System.out.println(solve(n)); 
        System.out.println(solve_1(n)); 
    } 
}