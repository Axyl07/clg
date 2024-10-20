import java.util.Scanner;

public class RoundRobin {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Enter number of processes:");
        int N = in.nextInt();

        int[] arrivalTime = new int[N];
        int[] burstTime = new int[N];
        int[] remainingTime = new int[N];
        int[] completionTime = new int[N];
        int[] waitingTime = new int[N];
        int[] turnAroundTime = new int[N];

        // Input for each process: Arrival Time and Burst Time
        for (int i = 0; i < N; i++) {
            System.out.println("Enter arrival time and burst time for process " + (i + 1) + ":");
            arrivalTime[i] = in.nextInt();
            burstTime[i] = in.nextInt();
            remainingTime[i] = burstTime[i];  // Initialize remaining time
        }

        System.out.println("Enter the time quantum:");
        int timeQuantum = in.nextInt();

        // Call the function to calculate the scheduling metrics
        calculateTimes(arrivalTime, burstTime, remainingTime, N, timeQuantum, completionTime, waitingTime, turnAroundTime);
        in.close();
    }

    public static void calculateTimes(int[] arrivalTime, int[] burstTime, int[] remainingTime, int N, 
                                      int timeQuantum, int[] completionTime, 
                                      int[] waitingTime, int[] turnAroundTime) {
        int currentTime = 0, completed = 0;
        boolean processExecuted;

        System.out.println("\nP.No.\tAT\tBT\tCT\tTAT\tWT");

        // Repeat until all processes are completed
        while (completed < N) {
            processExecuted = false;

            // Traverse through the processes in a round-robin manner
            for (int i = 0; i < N; i++) {
                if (remainingTime[i] > 0 && arrivalTime[i] <= currentTime) {
                    processExecuted = true;

                    // Execute the process for a time slice (time quantum)
                    if (remainingTime[i] > timeQuantum) {
                        currentTime += timeQuantum;
                        remainingTime[i] -= timeQuantum;
                    } else {
                        // If process completes within the time quantum
                        currentTime += remainingTime[i];
                        remainingTime[i] = 0;
                        completionTime[i] = currentTime;
                        turnAroundTime[i] = completionTime[i] - arrivalTime[i];
                        waitingTime[i] = turnAroundTime[i] - burstTime[i];
                        completed++;

                        // Print the metrics for the completed process
                        System.out.printf("%d\t%d\t%d\t%d\t%d\t%d\n",
                                (i + 1), arrivalTime[i], burstTime[i],
                                completionTime[i], turnAroundTime[i], waitingTime[i]);
                    }
                }
            }

            // If no process was executed, increment time
            if (!processExecuted) {
                currentTime++;
            }
        }

        // Calculate and print average waiting time and turn-around time
        float sumWT = 0, sumTAT = 0;
        for (int i = 0; i < N; i++) {
            sumWT += waitingTime[i];
            sumTAT += turnAroundTime[i];
        }
        System.out.println("\nAverage Waiting Time: " + (sumWT / N));
        System.out.println("Average Turn-Around Time: " + (sumTAT / N));
    }
}
