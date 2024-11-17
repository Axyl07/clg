import java.util.Scanner;

public class MemoryAllocation {
    static void firstFit(int blockSize[], int m, int processSize[], int n) {
        int allocation[] = new int[n];
        for (int i = 0; i < n; i++) {
            allocation[i] = -1;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (blockSize[j] >= processSize[i]) {
                    allocation[i] = j;
                    blockSize[j] -= processSize[i];
                    break;
                }
            }
        }

        printAllocation("First Fit", allocation, processSize);
    }

    static void bestFit(int blockSize[], int m, int processSize[], int n) {
        int allocation[] = new int[n];
        for (int i = 0; i < n; i++) {
            allocation[i] = -1;
        }

        for (int i = 0; i < n; i++) {
            int bestIdx = -1;
            for (int j = 0; j < m; j++) {
                if (blockSize[j] >= processSize[i]) {
                    if (bestIdx == -1 || blockSize[bestIdx] > blockSize[j]) {
                        bestIdx = j;
                    }
                }
            }

            if (bestIdx != -1) {
                allocation[i] = bestIdx;
                blockSize[bestIdx] -= processSize[i];
            }
        }

        printAllocation("Best Fit", allocation, processSize);
    }

    static void worstFit(int blockSize[], int m, int processSize[], int n) {
        int allocation[] = new int[n];
        for (int i = 0; i < n; i++) {
            allocation[i] = -1;
        }

        for (int i = 0; i < n; i++) {
            int worstIdx = -1;
            for (int j = 0; j < m; j++) {
                if (blockSize[j] >= processSize[i]) {
                    if (worstIdx == -1 || blockSize[worstIdx] < blockSize[j]) {
                        worstIdx = j;
                    }
                }
            }

            if (worstIdx != -1) {
                allocation[i] = worstIdx;
                blockSize[worstIdx] -= processSize[i];
            }
        }

        printAllocation("Worst Fit", allocation, processSize);
    }

    static void printAllocation(String method, int allocation[], int processSize[]) {
        System.out.println("\n" + method + " Allocation:");
        System.out.println("Process No.\tProcess Size\tBlock No.");
        for (int i = 0; i < allocation.length; i++) {
            System.out.print(" " + (i + 1) + "\t\t" + processSize[i] + "\t\t");
            if (allocation[i] != -1) {
                System.out.print(allocation[i] + 1);
            } else {
                System.out.print("Not Allocated");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of blocks: ");
        int m = scanner.nextInt();
        int[] blockSize = new int[m];
        System.out.println("Enter block sizes:");
        for (int i = 0; i < m; i++) {
            blockSize[i] = scanner.nextInt();
        }

        System.out.print("Enter number of processes: ");
        int n = scanner.nextInt();
        int[] processSize = new int[n];
        System.out.println("Enter process sizes:");
        for (int i = 0; i < n; i++) {
            processSize[i] = scanner.nextInt();
        }

        firstFit(blockSize.clone(), m, processSize, n);
        bestFit(blockSize.clone(), m, processSize, n);
        worstFit(blockSize.clone(), m, processSize, n);

        scanner.close();
    }
}
