import java.util.Arrays;

public class RiskThresholdLookup {

    // ===================== LINEAR SEARCH =====================

    static int linearComparisons = 0;

    public static int linearSearch(int[] arr, int target) {

        linearComparisons = 0;

        for (int i = 0; i < arr.length; i++) {
            linearComparisons++;
            if (arr[i] == target) {
                return i;
            }
        }

        return -1;
    }

    // ===================== BINARY SEARCH INSERTION =====================

    static int binaryComparisons = 0;

    public static int binaryInsertionPoint(int[] arr, int target) {

        int low = 0;
        int high = arr.length - 1;

        binaryComparisons = 0;

        while (low <= high) {

            int mid = (low + high) / 2;
            binaryComparisons++;

            if (arr[mid] == target)
                return mid;

            else if (arr[mid] < target)
                low = mid + 1;

            else
                high = mid - 1;
        }

        return low; // insertion point
    }

    // ===================== FLOOR =====================

    public static Integer floor(int[] arr, int target) {

        int low = 0, high = arr.length - 1;
        Integer result = null;

        while (low <= high) {

            int mid = (low + high) / 2;

            if (arr[mid] == target)
                return arr[mid];

            else if (arr[mid] < target) {
                result = arr[mid];
                low = mid + 1;
            } else
                high = mid - 1;
        }

        return result;
    }

    // ===================== CEILING =====================

    public static Integer ceiling(int[] arr, int target) {

        int low = 0, high = arr.length - 1;
        Integer result = null;

        while (low <= high) {

            int mid = (low + high) / 2;

            if (arr[mid] == target)
                return arr[mid];

            else if (arr[mid] > target) {
                result = arr[mid];
                high = mid - 1;
            } else
                low = mid + 1;
        }

        return result;
    }

    // ===================== MAIN =====================

    public static void main(String[] args) {

        int[] risks = {10, 25, 50, 100};
        int threshold = 30;

        System.out.println("Sorted Risk Bands:");
        System.out.println(Arrays.toString(risks));

        // Linear Search
        int linearIndex = linearSearch(risks, threshold);

        System.out.println("\nLinear Search:");
        System.out.println("Threshold = " + threshold);
        System.out.println("Index: " + linearIndex);
        System.out.println("Comparisons: " + linearComparisons);

        // Binary Search
        int insertPoint = binaryInsertionPoint(risks, threshold);
        Integer floor = floor(risks, threshold);
        Integer ceiling = ceiling(risks, threshold);

        System.out.println("\nBinary Search:");
        System.out.println("Insertion Point: " + insertPoint);
        System.out.println("Floor (" + threshold + "): " + floor);
        System.out.println("Ceiling (" + threshold + "): " + ceiling);
        System.out.println("Comparisons: " + binaryComparisons);

        // Complexity
        System.out.println("\nTime Complexity:");
        System.out.println("Linear Search: O(n)");
        System.out.println("Binary Search: O(log n)");
    }
}