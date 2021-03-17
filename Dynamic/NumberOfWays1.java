class NumberOfWays1 {
    // https://www.geeksforgeeks.org/number-of-ways-to-arrange-n-items-under-given-constraints/

    // method returns number of ways with which items  
    // can be arranged  
    static int solve(int N, int K, int[] k)
    { 
        int[][] C = new int[N + 1][N + 1]; 
        int i, j; 
  
        // Calculate value of Binomial Coefficient in  
        // bottom up manner  
        for (i = 0; i <= N; i++)  
        { 
            for (j = 0; j <= i; j++)  
            { 
  
                // Base Cases  
                if (j == 0 || j == i)  
                { 
                    C[i][j] = 1; 
                }  
                  
                // Calculate value using previously  
                // stored values  
                else 
                { 
                    C[i][j] = (C[i - 1][j - 1] + C[i - 1][j]); 
                } 
            } 
        } 
  
        // declare dp array to store result up to ith  
        // colored item  
        int[] dp = new int[K + 1]; 
  
        // variable to keep track of count of items  
        // considered till now  
        int count = 0; 
  
        dp[0] = 1; 
  
        // loop over all different colors  
        for (i = 0; i < K; i++)  
        { 
  
            // populate next value using current value  
            // and stated relation  
            dp[i + 1] = (dp[i] * C[count + k[i] - 1][k[i] - 1]); 
            count += k[i]; 
        } 
  
        // return value stored at last index  
        return dp[K]; 
    } 
    
    static int solve_1(int N, int[] k) {
        return solve_1_helper(N, k, k.length - 1);
    }

    static int solve_1_helper(int N, int[] k, int hi) {
        if (hi < 1) {
            return 1;
        }

        int inner = solve_1_helper(N - k[hi], k, hi - 1);
        int sum = inner * numberOfWaysToCombine(
            N - k[hi],
            k[hi] - 1
        );
        return sum;
    }

    public static long choose(long total, long choose){
        if(total < choose)
            return 0;
        if(choose == 0 || choose == total)
            return 1;
        return choose(total-1,choose-1)+choose(total-1,choose);
    }

    static int numberOfWaysToCombine(int str1Length, int str2Length) {
        if (str1Length <= 0 || str2Length <= 0) {
            return 1;
        }

        return numberOfWaysToCombine(str1Length-1, str2Length) + numberOfWaysToCombine(str1Length, str2Length-1);
    }

    static int numberOfWaysToCombine_1(int n, int k) {
        return (int)choose(n + k, k);
    }

    // Driver code  
    public static void main(String[] args)  
    { 
        System.out.println(numberOfWaysToCombine(8, 4)); 
        System.out.println(numberOfWaysToCombine_1(8, 4)); 
        // int N = 10;
        // int[] k = new int[]{2, 2, 2, 2, 2};
        // System.out.println(solve(N, k.length, k)); 
        // System.out.println(solve_1(N, k)); 
    }
}