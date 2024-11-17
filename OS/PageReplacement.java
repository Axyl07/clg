import java.util.Scanner;

public class PageReplacement {

    // FIFO Page Replacement Algorithm
    static void fifo(int pages[], int frames) {
        int[] memory = new int[frames];
        int pageFaults = 0, index = 0;

        for (int i = 0; i < frames; i++) {
            memory[i] = -1; // Initialize memory slots as empty
        }

        for (int page : pages) {
            boolean pageFound = false;
            for (int j = 0; j < frames; j++) {
                if (memory[j] == page) {
                    pageFound = true;
                    break;
                }
            }

            if (!pageFound) {
                memory[index] = page;
                index = (index + 1) % frames; // Move to the next slot in circular fashion
                pageFaults++;
            }
        }

        System.out.println("FIFO Page Faults: " + pageFaults);
    }

    // LRU Page Replacement Algorithm
    static void lru(int pages[], int frames) {
        int[] memory = new int[frames];
        int[] lastUsed = new int[frames]; // To store the time when each page was last used
        int pageFaults = 0;

        for (int i = 0; i < frames; i++) {
            memory[i] = -1; // Initialize memory slots as empty
            lastUsed[i] = -1;
        }

        for (int i = 0; i < pages.length; i++) {
            int page = pages[i];
            boolean pageFound = false;
            int lruIndex = 0;

            for (int j = 0; j < frames; j++) {
                if (memory[j] == page) {
                    pageFound = true;
                    lastUsed[j] = i; // Update the last used time for the found page
                    break;
                }
                if (memory[j] == -1 || lastUsed[j] < lastUsed[lruIndex]) {
                    lruIndex = j; // Track the least recently used page
                }
            }

            if (!pageFound) {
                memory[lruIndex] = page;
                lastUsed[lruIndex] = i; // Update the time for the newly loaded page
                pageFaults++;
            }
        }

        System.out.println("LRU Page Faults: " + pageFaults);
    }

    // Optimal Page Replacement Algorithm
    static void optimal(int pages[], int frames) {
        int[] memory = new int[frames];
        int pageFaults = 0;

        for (int i = 0; i < frames; i++) {
            memory[i] = -1; // Initialize memory slots as empty
        }

        for (int i = 0; i < pages.length; i++) {
            int page = pages[i];
            boolean pageFound = false;

            for (int j = 0; j < frames; j++) {
                if (memory[j] == page) {
                    pageFound = true;
                    break;
                }
            }

            if (!pageFound) {
                int replaceIndex = -1;
                int farthest = i + 1;

                for (int j = 0; j < frames; j++) {
                    if (memory[j] == -1) {
                        replaceIndex = j;
                        break;
                    }
                    int nextUse = Integer.MAX_VALUE;
                    for (int k = i + 1; k < pages.length; k++) {
                        if (pages[k] == memory[j]) {
                            nextUse = k;
                            break;
                        }
                    }
                    if (nextUse > farthest) {
                        farthest = nextUse;
                        replaceIndex = j;
                    }
                }

                memory[replaceIndex] = page;
                pageFaults++;
            }
        }

        System.out.println("Optimal Page Faults: " + pageFaults);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of pages: ");
        int n = scanner.nextInt();
        int[] pages = new int[n];
        System.out.println("Enter page references:");
        for (int i = 0; i < n; i++) {
            pages[i] = scanner.nextInt();
        }

        System.out.print("Enter number of frames: ");
        int frames = scanner.nextInt();

        fifo(pages, frames);
        lru(pages, frames);
        optimal(pages, frames);

        scanner.close();
    }
}
