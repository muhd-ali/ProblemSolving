class NumberOfWays {
    // https://www.geeksforgeeks.org/different-ways-sum-n-using-numbers-greater-equal-m/
    
    // Return number of ways to which numbers 
    // that are greater than given number can 
    // be added to get sum. 
    static int solve(int n, int m) 
    { 
        int dp[][]=new int[n+2][n+2]; 
          
        dp[0][n + 1] = 1; 
       
        // Filling the table. k is for numbers 
        // greater than or equal that are allowed. 
        for (int k = n; k >= m; k--) { 
       
            // i is for sum 
            for (int i = 0; i <= n; i++) { 
       
                // initializing dp[i][k] to number 
                // ways to get sum using numbers 
                // greater than or equal k+1 
                dp[i][k] = dp[i][k + 1]; 
       
                // if i > k 
                if (i - k >= 0) 
                    dp[i][k] = (dp[i][k] + dp[i - k][k]); 
            } 
        } 
       
        return dp[n][m]; 
    } 

    static int solve_1(int n, int m) {
        if (n == 0) {
            return 1;
        }
        int sum = 0;
        for(int i = m; i <= n; i++) {
            sum += solve_1(n - i, i);
        }
        return sum;
    }

    static int solve_2(int n, int m) { // wrong
        int[][] dp = new int[n+1][n+1];
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                int sum = 0;
                for (int k = j; k < i; k++) {
                    sum += dp[i-k][k];
                }
                dp[i][j] = sum;
            }
        }

        return dp[n][m];
    }

    // Driver Program 
    public static void main(String args[]) 
    { 
        int n = 100, m = 20; 
        System.out.println(solve(n, m)); 
        // System.out.println(solve_1(n, m)); 
        System.out.println(solve_2(n, m)); 
    } 
}