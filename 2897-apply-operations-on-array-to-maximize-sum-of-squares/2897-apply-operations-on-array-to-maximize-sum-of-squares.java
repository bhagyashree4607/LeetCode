class Solution {
    public int maxSum(List<Integer> nums, int k) {
        final int MOD = 1_000_000_007;
        int[] bitCount = new int[31];

        // Step 1: count how many numbers have each bit set
        for (int num : nums) {
            for (int b = 0; b < 31; b++) {
                if ((num & (1 << b)) != 0) {
                    bitCount[b]++;
                }
            }
        }

        long result = 0;

        // Step 2: build k numbers greedily, each as large as possible
        for (int t = 0; t < k; t++) {
            long cur = 0;
            for (int b = 0; b < 31; b++) {
                if (bitCount[b] > 0) {
                    cur |= (1L << b);
                    bitCount[b]--;
                }
            }
            if (cur == 0) break;
            result = (result + (cur % MOD) * (cur % MOD)) % MOD;
        }

        return (int) result;
    }
}