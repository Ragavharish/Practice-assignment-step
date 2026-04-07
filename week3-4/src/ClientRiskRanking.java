public class ClientRiskRanking {

    static class Client {
        String name;
        int riskScore;
        double accountBalance;

        Client(String name, int riskScore, double accountBalance) {
            this.name = name;
            this.riskScore = riskScore;
            this.accountBalance = accountBalance;
        }

        @Override
        public String toString() {
            return name + ":" + riskScore;
        }
    }

    // Bubble Sort - Ascending Risk Score
    public static void bubbleSort(Client[] clients) {
        int n = clients.length;
        int swapCount = 0;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {

                if (clients[j].riskScore > clients[j + 1].riskScore) {

                    // Swap
                    Client temp = clients[j];
                    clients[j] = clients[j + 1];
                    clients[j + 1] = temp;

                    swapCount++;

                    // Visualization
                    System.out.println("Swap: " + clients[j] + " <-> " + clients[j + 1]);
                }
            }
        }

        System.out.println("Total Swaps: " + swapCount);
    }

    // Insertion Sort - Descending RiskScore + AccountBalance
    public static void insertionSort(Client[] clients) {

        for (int i = 1; i < clients.length; i++) {
            Client key = clients[i];
            int j = i - 1;

            while (j >= 0 &&
                    (clients[j].riskScore < key.riskScore ||
                            (clients[j].riskScore == key.riskScore &&
                                    clients[j].accountBalance < key.accountBalance))) {

                clients[j + 1] = clients[j];
                j--;
            }

            clients[j + 1] = key;
        }
    }

    // Print Top N Risk Clients
    public static void printTopRisk(Client[] clients, int topN) {

        System.out.println("\nTop " + topN + " Highest Risk Clients:");

        for (int i = 0; i < Math.min(topN, clients.length); i++) {
            System.out.println(
                    clients[i].name +
                            " (Risk: " + clients[i].riskScore +
                            ", Balance: " + clients[i].accountBalance + ")"
            );
        }
    }

    public static void main(String[] args) {

        Client[] clients = {
                new Client("clientC", 80, 5000),
                new Client("clientA", 20, 2000),
                new Client("clientB", 50, 3000),
                new Client("clientD", 90, 4000),
                new Client("clientE", 70, 1000)
        };

        System.out.println("Original Data:");
        for (Client c : clients) {
            System.out.println(c);
        }

        // Bubble Sort
        System.out.println("\nBubble Sort (Ascending Risk Score):");
        bubbleSort(clients);

        for (Client c : clients) {
            System.out.println(c);
        }

        // Insertion Sort
        System.out.println("\nInsertion Sort (Descending Risk Score + Balance):");
        insertionSort(clients);

        for (Client c : clients) {
            System.out.println(c);
        }

        // Top 10
        printTopRisk(clients, 10);
    }
}
