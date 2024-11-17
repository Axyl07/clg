import java.util.Scanner;

public class BankersAlgorithm {
    static int processes;   // Number of processes
    static int resources;   // Number of resources

    static boolean isSafe(int[] available, int[][] max, int[][] allocation) {
        int[][] need = new int[processes][resources];
        for (int i = 0; i < processes; i++) {
            for (int j = 0; j < resources; j++) {
                need[i][j] = max[i][j] - allocation[i][j];
            }
        }

        boolean[] finished = new boolean[processes];
        int[] safeSequence = new int[processes];
        int[] work = available.clone();

        int count = 0;
        while (count < processes) {
            boolean found = false;
            for (int i = 0; i < processes; i++) {
                if (!finished[i]) {
                    boolean canAllocate = true;
                    for (int j = 0; j < resources; j++) {
                        if (need[i][j] > work[j]) {
                            canAllocate = false;
                            break;
                        }
                    }

                    if (canAllocate) {
                        for (int j = 0; j < resources; j++) {
                            work[j] += allocation[i][j];
                        }
                        safeSequence[count++] = i;
                        finished[i] = true;
                        found = true;
                    }
                }
            }

            if (!found) {
                System.out.println("System is not in a safe state.");
                return false;
            }
        }

        System.out.println("System is in a safe state. Safe sequence is:");
        for (int i = 0; i < processes; i++) {
            System.out.print("P" + safeSequence[i] + " ");
        }
        System.out.println();
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of processes: ");
        processes = scanner.nextInt();

        System.out.print("Enter the number of resources: ");
        resources = scanner.nextInt();

        int[][] max = new int[processes][resources];
        int[][] allocation = new int[processes][resources];
        int[] available = new int[resources];

        System.out.println("Enter the Max matrix:");
        for (int i = 0; i < processes; i++) {
            for (int j = 0; j < resources; j++) {
                max[i][j] = scanner.nextInt();
            }
        }

        System.out.println("Enter the Allocation matrix:");
        for (int i = 0; i < processes; i++) {
            for (int j = 0; j < resources; j++) {
                allocation[i][j] = scanner.nextInt();
            }
        }

        System.out.println("Enter the Available resources:");
        for (int j = 0; j < resources; j++) {
            available[j] = scanner.nextInt();
        }

        isSafe(available, max, allocation);

        scanner.close();
    }
}
