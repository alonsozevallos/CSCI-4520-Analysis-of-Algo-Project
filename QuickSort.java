public class QuickSort {
    public static void sort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    private static void quickSort(int[] array, int low, int high) {
        if (low >= high) {
            return;
        }

        int left = low;
        int right = high;
        int pivot = array[low + (high - low) / 2];

        while (left <= right) {
            while (array[left] < pivot) {
                left++;
            }
            while (array[right] > pivot) {
                right--;
            }

            if (left <= right) {
                int value = array[left];
                array[left] = array[right];
                array[right] = value;
                left++;
                right--;
            }
        }

        if (low < right) {
            quickSort(array, low, right);
        }
        if (left < high) {
            quickSort(array, left, high);
        }
    }
}