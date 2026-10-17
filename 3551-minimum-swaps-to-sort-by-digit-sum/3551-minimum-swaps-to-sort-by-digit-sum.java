class Solution {
    public int minSwaps(int[] nums) {
        int n = nums.length;
        int[][] a = new int[n][3];

        for (int i = 0; i < n; i++) {
            int x = nums[i], s = 0;
            while (x > 0) {
                s += x % 10;
                x /= 10;
            }
            a[i][0] = s;
            a[i][1] = nums[i];
            a[i][2] = i;
        }

        Arrays.sort(a, (p, q) -> p[0] != q[0] ? p[0] - q[0] : p[1] - q[1]);

        int[] pos = new int[n];
        for (int i = 0; i < n; i++) {
            pos[a[i][2]] = i;
        }

        boolean[] visited = new boolean[n];
        int swaps = 0;
        for (int i = 0; i < n; i++) {
            if (visited[i] || pos[i] == i) continue;
            int len = 0, j = i;
            while (!visited[j]) {
                visited[j] = true;
                j = pos[j];
                len++;
            }
            swaps += len - 1;
        }

        return swaps;
    }
}