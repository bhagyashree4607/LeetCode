class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        
        // Step 1: Merge both arrays
        int m = nums1.length, n = nums2.length;
        int[] merged = new int[m + n];
        
        int i = 0, j = 0, k = 0;
        
        while (i < m && j < n) {
            if (nums1[i] <= nums2[j])
                merged[k++] = nums1[i++];
            else
                merged[k++] = nums2[j++];
        }
        
        // Copy remaining elements
        while (i < m) merged[k++] = nums1[i++];
        while (j < n) merged[k++] = nums2[j++];
        
        // Step 2: Find median
        int total = m + n;
        
        if (total % 2 == 1) {
            return merged[total / 2];          // Odd → middle element
        } else {
            return (merged[total / 2 - 1] 
                  + merged[total / 2]) / 2.0;  // Even → average of two middles
        }
    }
}