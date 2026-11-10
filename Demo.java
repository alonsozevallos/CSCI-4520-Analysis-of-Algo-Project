import java.util.Arrays;
import java.util.Random;

public class Demo {
    private static final int INITIAL_SIZE = 25_000;
    private static final int MAX_SIZE = 250_000;
    private static final int SIZE_INCREMENT = 25_000;
    private static final int TRIALS = 10;
    private static final int MAX_RANDOM_VALUE = 300_000;

    public static void main(String[] args) {
        Random random = new Random();

        System.out.printf(
                "Average sort time over %d random trials per size (values 0 to %,d):%n",
                TRIALS,
                MAX_RANDOM_VALUE);
        for (int size = INITIAL_SIZE; size <= MAX_SIZE; size += SIZE_INCREMENT) {
            int[][] unsortedInputs = new int[TRIALS][size];
            int[][] sortedInputs = new int[TRIALS][size];
            for (int trial = 0; trial < TRIALS; trial++) {
                for (int i = 0; i < size; i++) {
                    unsortedInputs[trial][i] = random.nextInt(MAX_RANDOM_VALUE + 1);
                }
                sortedInputs[trial] = unsortedInputs[trial].clone();
                Arrays.sort(sortedInputs[trial]);
            }

            System.out.printf("%nInput size: %,d%n", size);
            reportTimes("Insertion sort", unsortedInputs, sortedInputs, InsertionSort::sort);
            reportTimes("Selection sort", unsortedInputs, sortedInputs, SelectionSort::sort);
            reportTimes("Quick sort", unsortedInputs, sortedInputs, QuickSort::sort);
        }
    }

    private static void reportTimes(
            String name, int[][] unsortedInputs, int[][] sortedInputs, SortAlgorithm algorithm) {
        System.out.println("  " + name + ":");
        reportTime("unsorted", unsortedInputs, name, algorithm);
        reportTime("sorted", sortedInputs, name, algorithm);
    }

    private static void reportTime(
            String inputType, int[][] inputs, String name, SortAlgorithm algorithm) {
        long totalNanoseconds = 0;
        System.out.printf("    %s: ", inputType);
        System.out.flush();

        for (int[] input : inputs) {
            int[] values = input.clone();
            long startTime = System.nanoTime();
            algorithm.sort(values);
            totalNanoseconds += System.nanoTime() - startTime;

            if (!isSorted(values)) {
                throw new IllegalStateException(name + " did not sort the input correctly");
            }
        }

        double averageMilliseconds = totalNanoseconds / (double) inputs.length / 1_000_000;
        System.out.printf("%.3f ms average%n", averageMilliseconds);
    }

    private static boolean isSorted(int[] values) {
        for (int i = 1; i < values.length; i++) {
            if (values[i - 1] > values[i]) {
                return false;
            }
        }
        return true;
    }

    @FunctionalInterface
    private interface SortAlgorithm {
        void sort(int[] values);
    }
}
