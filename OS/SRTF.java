import java.util.Scanner;

public class SRTF {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Enter number of processes:");
        int N = in.nextInt();

        int[] arrivalTime = new int[N];
        int[] burstTime = new int[N];
        int[] remainingTime = new int[N];

        // Input process arrival and burst times
        for (int i = 0; i < N; i++) {
            System.out.println("Enter arrival time for process " + (i + 1) + ":");
            arrivalTime[i] = in.nextInt();
            System.out.println("Enter burst time for process " + (i + 1) + ":");
            burstTime[i] = in.nextInt();
            remainingTime[i] = burstTime[i]; // Initialize remaining time
        }

        calculateTimes(arrivalTime, burstTime, remainingTime, N);
        in.close();
    }

    public static void calculateTimes(int[] arrivalTime, int[] burstTime, int[] remainingTime, int N) {
        int[] completionTime = new int[N];
        int[] waitingTime = new int[N];
        int[] turnAroundTime = new int[N];

        int completed = 0, currentTime = 0, minRemainingTime;
        int shortestProcess = -1;
        boolean foundProcess;

        // Run until all processes are completed
        while (completed < N) {
            minRemainingTime = Integer.MAX_VALUE;
            foundProcess = false;

            // Find the process with the smallest remaining time
            for (int i = 0; i < N; i++) {
                if (arrivalTime[i] <= currentTime && remainingTime[i] > 0 && remainingTime[i] < minRemainingTime) {
                    minRemainingTime = remainingTime[i];
                    shortestProcess = i;
                    foundProcess = true;
                }
            }

            if (!foundProcess) {
                currentTime++; // No process found, increment time
                continue;
            }

            // Execute the process for 1 unit of time
            remainingTime[shortestProcess]--;
            currentTime++;

            // If the process finishes, record completion time
            if (remainingTime[shortestProcess] == 0) {
                completed++;
                completionTime[shortestProcess] = currentTime;

                turnAroundTime[shortestProcess] = completionTime[shortestProcess] - arrivalTime[shortestProcess];
                waitingTime[shortestProcess] = turnAroundTime[shortestProcess] - burstTime[shortestProcess];
            }
        }

        // Print the results
        System.out.println("\nP.No.\tAT\tBT\tCT\tTAT\tWT");
        for (int i = 0; i < N; i++) {
            System.out.printf("%d\t%d\t%d\t%d\t%d\t%d\n", 
                (i + 1), arrivalTime[i], burstTime[i], 
                completionTime[i], turnAroundTime[i], waitingTime[i]);
        }

        // Calculate and print average waiting and turn-around times
        float avgWT = 0, avgTAT = 0;
        for (int i = 0; i < N; i++) {
            avgWT += waitingTime[i];
            avgTAT += turnAroundTime[i];
        }
        avgWT /= N;
        avgTAT /= N;

        System.out.println("\nAverage Waiting Time: " + avgWT);
        System.out.println("Average Turn-Around Time: " + avgTAT);
    }
}
