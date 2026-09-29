class Solution {
    public boolean hasValidPath(char[][] grid) {
        int n = grid.length, m = grid[0].length;

        // Path length is fixed: n + m - 1. It must be even to balance.
        if ((n + m - 1) % 2 != 0) return false;
        if (grid[0][0] == ')' || grid[n - 1][m - 1] == '(') return false;

        // dp[i][j][k] = true if some path to (i,j) leaves balance k
        // balance = (count of '(') - (count of ')') so far
        boolean[][][] dp = new boolean[n][m][n + m + 1];
        dp[0][0][1] = true; // grid[0][0] is '(' so balance starts at 1

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 && j == 0) continue;

                int delta = (grid[i][j] == '(') ? 1 : -1;

                for (int k = 0; k <= n + m; k++) {
                    boolean fromTop = (i > 0) && dp[i - 1][j][k];
                    boolean fromLeft = (j > 0) && dp[i][j - 1][k];

                    if (fromTop || fromLeft) {
                        int newBalance = k + delta;
                        if (newBalance >= 0 && newBalance <= n + m) {
                            dp[i][j][newBalance] = true;
                        }
                    }
                }
            }
        }

        return dp[n - 1][m - 1][0];
    }
}