public class TradeVolumeAnalysis {

    // Trade Class
    static class Trade {
        String tradeId;
        int volume;

        Trade(String tradeId, int volume) {
            this.tradeId = tradeId;
            this.volume = volume;
        }

        @Override
        public String toString() {
            return tradeId + ":" + volume;
        }
    }

    // ===================== MERGE SORT (ASCENDING) =====================

    public static void mergeSort(Trade[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;

            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);

            merge(arr, left, mid, right);
        }
    }

    private static void merge(Trade[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        Trade[] L = new Trade[n1];
        Trade[] R = new Trade[n2];

        for (int i = 0; i < n1; i++)
            L[i] = arr[left + i];

        for (int j = 0; j < n2; j++)
            R[j] = arr[mid + 1 + j];

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            if (L[i].volume <= R[j].volume) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k++] = L[i++];
        }

        while (j < n2) {
            arr[k++] = R[j++];
        }
    }

    // ===================== QUICK SORT (DESCENDING) =====================

    public static void quickSort(Trade[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);

            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(Trade[] arr, int low, int high) {
        int pivot = arr[high].volume;
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j].volume > pivot) { // DESCENDING
                i++;

                Trade temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        Trade temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    // ===================== MERGE TWO SORTED ARRAYS =====================

    public static Trade[] mergeSortedTrades(Trade[] arr1, Trade[] arr2) {

        int i = 0, j = 0, k = 0;
        Trade[] result = new Trade[arr1.length + arr2.length];

        while (i < arr1.length && j < arr2.length) {
            if (arr1[i].volume <= arr2[j].volume) {
                result[k++] = arr1[i++];
            } else {
                result[k++] = arr2[j++];
            }
        }

        while (i < arr1.length)
            result[k++] = arr1[i++];

        while (j < arr2.length)
            result[k++] = arr2[j++];

        return result;
    }

    // ===================== TOTAL VOLUME =====================

    public static long totalVolume(Trade[] trades) {
        long total = 0;

        for (Trade t : trades) {
            total += t.volume;
        }

        return total;
    }

    // ===================== PRINT =====================

    public static void printTrades(String message, Trade[] trades) {
        System.out.println("\n" + message);
        for (Trade t : trades) {
            System.out.print(t + " ");
        }
        System.out.println();
    }

    // ===================== MAIN =====================

    public static void main(String[] args) {

        Trade[] trades = {
                new Trade("trade3", 500),
                new Trade("trade1", 100),
                new Trade("trade2", 300)
        };

        printTrades("Original", trades);

        // Merge Sort Ascending
        mergeSort(trades, 0, trades.length - 1);
        printTrades("Merge Sort (Ascending)", trades);

        // Quick Sort Descending
        quickSort(trades, 0, trades.length - 1);
        printTrades("Quick Sort (Descending)", trades);

        // Morning Session
        Trade[] morning = {
                new Trade("tradeA", 100),
                new Trade("tradeB", 300)
        };

        // Afternoon Session
        Trade[] afternoon = {
                new Trade("tradeC", 200),
                new Trade("tradeD", 400)
        };

        mergeSort(morning, 0, morning.length - 1);
        mergeSort(afternoon, 0, afternoon.length - 1);

        Trade[] merged = mergeSortedTrades(morning, afternoon);
        printTrades("Merged Morning + Afternoon", merged);

        // Total Volume
        long total = totalVolume(merged);
        System.out.println("\nTotal Volume: " + total);
    }
}