import java.util.ArrayList;
import java.util.List;

public class TransactionApp {

    // Transaction Class
    static class Transaction {
        String id;
        double fee;
        String timestamp;

        public Transaction(String id, double fee, String timestamp) {
            this.id = id;
            this.fee = fee;
            this.timestamp = timestamp;
        }

        @Override
        public String toString() {
            return id + ":" + fee + "@" + timestamp;
        }
    }

    // Bubble Sort (Stable)
    public static void bubbleSort(List<Transaction> list) {
        int n = list.size();
        boolean swapped;
        int passes = 0;
        int swaps = 0;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            passes++;

            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j).fee > list.get(j + 1).fee) {
                    // Swap
                    Transaction temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                    swapped = true;
                    swaps++;
                }
            }

            if (!swapped) break;
        }

        System.out.println("\nBubble Sort Result:");
        printList(list);
        System.out.println("Passes: " + passes + ", Swaps: " + swaps);
    }

    // Insertion Sort (Stable)
    public static void insertionSort(List<Transaction> list) {
        int passes = 0;

        for (int i = 1; i < list.size(); i++) {
            Transaction key = list.get(i);
            int j = i - 1;
            passes++;

            // Sort by fee + timestamp
            while (j >= 0 && compare(list.get(j), key) > 0) {
                list.set(j + 1, list.get(j));
                j--;
            }

            list.set(j + 1, key);
        }

        System.out.println("\nInsertion Sort Result:");
        printList(list);
        System.out.println("Passes: " + passes);
    }

    // Comparison (Fee + Timestamp)
    private static int compare(Transaction t1, Transaction t2) {
        if (t1.fee != t2.fee) {
            return Double.compare(t1.fee, t2.fee);
        }
        return t1.timestamp.compareTo(t2.timestamp);
    }

    // High Fee Outliers
    public static void findHighFee(List<Transaction> list) {
        System.out.println("\nHigh-Fee Outliers (> $50):");

        boolean found = false;

        for (Transaction t : list) {
            if (t.fee > 50) {
                System.out.println(t);
                found = true;
            }
        }

        if (!found) {
            System.out.println("None");
        }
    }

    // Print List
    public static void printList(List<Transaction> list) {
        for (Transaction t : list) {
            System.out.println(t);
        }
    }

    // Main Method
    public static void main(String[] args) {

        ArrayList<Transaction> transactions = new ArrayList<>();

        // Sample Input
        transactions.add(new Transaction("id1", 10.5, "10:00"));
        transactions.add(new Transaction("id2", 25.0, "09:30"));
        transactions.add(new Transaction("id3", 5.0, "10:15"));

        System.out.println("Original Transactions:");
        printList(transactions);

        // Choose Sorting Based on Size
        if (transactions.size() <= 100) {
            bubbleSort(new ArrayList<>(transactions));
        }

        if (transactions.size() <= 1000) {
            insertionSort(new ArrayList<>(transactions));
        }

        findHighFee(transactions);
    }
}
