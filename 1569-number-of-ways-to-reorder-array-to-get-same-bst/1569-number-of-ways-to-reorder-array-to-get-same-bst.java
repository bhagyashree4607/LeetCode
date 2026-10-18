import java.util.*;

class Solution {
    static final long MOD = 1_000_000_007L;
    long[][] C;

    public int numOfWays(int[] nums) {
        int n = nums.length;

        C = new long[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            C[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                C[i][j] = (C[i - 1][j - 1] + C[i - 1][j]) % MOD;
            }
        }

        List<Integer> list = new ArrayList<>();
        for (int x : nums) list.add(x);

        return (int) ((count(list) - 1 + MOD) % MOD);
    }

    private long count(List<Integer> a) {
        int m = a.size();
        if (m <= 2) return 1;

        int root = a.get(0);
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        for (int i = 1; i < m; i++) {
            if (a.get(i) < root) left.add(a.get(i));
            else right.add(a.get(i));
        }

        long ways = C[m - 1][left.size()];
        ways = ways * count(left) % MOD;
        ways = ways * count(right) % MOD;
        return ways;
    }
}