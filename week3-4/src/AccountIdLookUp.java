import java.util.Arrays;

public class AccountIdLookUp {

    // ===================== LINEAR SEARCH =====================

    static int linearComparisons = 0;

    public static int linearFirst(String[] arr, String target) {
        linearComparisons = 0;

        for (int i = 0; i < arr.length; i++) {
            linearComparisons++;
            if (arr[i].equals(target)) {
                return i;
            }
        }
        return -1;
    }

    public static int linearLast(String[] arr, String target) {
        int index = -1;

        for (int i = 0; i < arr.length; i++) {
            linearComparisons++;
            if (arr[i].equals(target)) {
                index = i;
            }
        }
        return index;
    }

    // ===================== BINARY SEARCH =====================

    static int binaryComparisons = 0;

    public static int binarySearch(String[] arr, String target) {

        int low = 0;
        int high = arr.length - 1;

        binaryComparisons = 0;

        while (low <= high) {

            int mid = (low + high) / 2;
            binaryComparisons++;

            int cmp = arr[mid].compareTo(target);

            if (cmp == 0)
                return mid;
            else if (cmp < 0)
                low = mid + 1;
            else
                high = mid - 1;
        }

        return -1;
    }

    // ===================== COUNT OCCURRENCES =====================

    public static int countOccurrences(String[] arr, String target) {

        int first = firstOccurrence(arr, target);
        if (first == -1)
            return 0;

        int last = lastOccurrence(arr, target);

        return last - first + 1;
    }

    private static int firstOccurrence(String[] arr, String target) {

        int low = 0, high = arr.length - 1;
        int result = -1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (arr[mid].equals(target)) {
                result = mid;
                high = mid - 1;
            } else if (arr[mid].compareTo(target) < 0)
                low = mid + 1;
            else
                high = mid - 1;
        }

        return result;
    }

    private static int lastOccurrence(String[] arr, String target) {

        int low = 0, high = arr.length - 1;
        int result = -1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (arr[mid].equals(target)) {
                result = mid;
                low = mid + 1;
            } else if (arr[mid].compareTo(target) < 0)
                low = mid + 1;
            else
                high = mid - 1;
        }

        return result;
    }

    // ===================== MAIN =====================

    public static void main(String[] args) {

        String[] logs = {"accB", "accA", "accB", "accC"};

        System.out.println("Original Logs:");
        System.out.println(Arrays.toString(logs));

        // Linear Search
        int first = linearFirst(logs, "accB");
        int last = linearLast(logs, "accB");

        System.out.println("\nLinear Search:");
        System.out.println("First accB index: " + first);
        System.out.println("Last accB index: " + last);
        System.out.println("Comparisons: " + linearComparisons);

        // Sort for Binary Search
        Arrays.sort(logs);

        System.out.println("\nSorted Logs:");
        System.out.println(Arrays.toString(logs));

        // Binary Search
        int index = binarySearch(logs, "accB");
        int count = countOccurrences(logs, "accB");

        System.out.println("\nBinary Search:");
        System.out.println("accB index: " + index);
        System.out.println("Count: " + count);
        System.out.println("Comparisons: " + binaryComparisons);

        // Complexity
        System.out.println("\nTime Complexity:");
        System.out.println("Linear Search: O(n)");
        System.out.println("Binary Search: O(log n)");
    }
}
