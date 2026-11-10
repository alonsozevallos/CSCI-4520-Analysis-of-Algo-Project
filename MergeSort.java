public class MergeSort {
    public static void sort(int[] arr) {
        int n = arr.length;
        int[] temp = new int[n];
        for (long size = 1; size < n; size *= 2) {
            for (long left = 0; left < n - size; left += 2 * size) {
                int mid = (int) (left + size);
                int right = (int) Math.min(left + 2 * size, n);
                int i = (int) left;
                int j = mid;
                int k = (int) left;
                while (i < mid && j < right) {
                    if (arr[i] <= arr[j]) {
                        temp[k++] = arr[i++];
                    } else {
                        temp[k++] = arr[j++];
                    }
                }
                while (i < mid) {
                    temp[k++] = arr[i++];
                }
                while (j < right) {
                    temp[k++] = arr[j++];
                }
                for (int x = (int) left; x < right; x++) {
                    arr[x] = temp[x];
                }
            }
        }
    }
}