class Solution {
    static final long MOD = 1_000_000_007L;
    long[] b1, b2;
    int n;

    public int sumCounts(int[] nums) {
        n = nums.length;
        b1 = new long[n + 2];
        b2 = new long[n + 2];
        int[] last = new int[100001]; // 1-indexed positions, 0 = not seen

        long cur = 0;   // sum of d[l]^2 for the current right end
        long total = 0;

        for (int r = 1; r <= n; r++) {
            int p = last[nums[r - 1]];
            // d[l] += 1 for l in (p, r]
            long s = rangeSum(p + 1, r);        // sum of old d[l] on that range
            cur += 2 * s + (r - p);             // (d+1)^2 - d^2 = 2d + 1
            rangeAdd(p + 1, r, 1);
            total = (total + cur) % MOD;
            last[nums[r - 1]] = r;
        }
        return (int) total;
    }

    private void upd(long[] b, int i, long x) {
        for (; i <= n + 1; i += i & -i) b[i] += x;
    }

    private long qry(long[] b, int i) {
        long s = 0;
        for (; i > 0; i -= i & -i) s += b[i];
        return s;
    }

    private void rangeAdd(int l, int r, long x) {
        upd(b1, l, x);
        upd(b1, r + 1, -x);
        upd(b2, l, x * (l - 1));
        upd(b2, r + 1, -x * r);
    }

    private long prefix(int i) {
        return qry(b1, i) * i - qry(b2, i);
    }

    private long rangeSum(int l, int r) {
        return prefix(r) - prefix(l - 1);
    }
}