import java.util.Scanner;

public class Priority {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Enter number of processes:");
        int N = in.nextInt();

        int[] arrivalTime = new int[N];
        int[] burstTime = new int[N];
        int[] priority = new int[N];
        int[] completionTime = new int[N];
        int[] waitingTime = new int[N];
        int[] turnAroundTime = new int[N];
        boolean[] isCompleted = new boolean[N];  // Track completed processes

        // Input for each process: Arrival Time, Burst Time, and Priority
        for (int i = 0; i < N; i++) {
            System.out.println("Enter arrival time, burst time, and priority for process " + (i + 1) + ":");
            arrivalTime[i] = in.nextInt();
            burstTime[i] = in.nextInt();
            priority[i] = in.nextInt();
        }

        calculateTimes(arrivalTime, burstTime, priority, N, completionTime, waitingTime, turnAroundTime, isCompleted);
        in.close();
    }

    public static void calculateTimes(int[] arrivalTime, int[] burstTime, int[] priority, int N, 
                                      int[] completionTime, int[] waitingTime, 
                                      int[] turnAroundTime, boolean[] isCompleted) {
        int currentTime = 0, completed = 0;
        float sumWT = 0, sumTAT = 0;

        System.out.println("\nP.No.\tAT\tBT\tPriority\tCT\tTAT\tWT");

        // Loop until all processes are completed
        while (completed < N) {
            int highestPriority = Integer.MAX_VALUE;
            int index = -1;

            // Find the process with the highest priority (lower number = higher priority)
            for (int i = 0; i < N; i++) {
                if (!isCompleted[i] && arrivalTime[i] <= currentTime && priority[i] < highestPriority) {
                    highestPriority = priority[i];
                    index = i;
                }
            }

            if (index == -1) {
                currentTime++;  // No available process, increment time
                continue;
            }

            // Execute the process
            currentTime += burstTime[index];
            completionTime[index] = currentTime;
            turnAroundTime[index] = completionTime[index] - arrivalTime[index];
            waitingTime[index] = turnAroundTime[index] - burstTime[index];

            // Mark the process as completed
            isCompleted[index] = true;
            completed++;

            // Print the metrics for the current process
            System.out.printf("%d\t%d\t%d\t%d\t\t%d\t%d\t%d\n", 
                              index + 1, arrivalTime[index], burstTime[index], 
                              priority[index], completionTime[index], 
                              turnAroundTime[index], waitingTime[index]);

            sumWT += waitingTime[index];
            sumTAT += turnAroundTime[index];
        }

        // Calculate and print average waiting time and turn-around time
        float avgWT = sumWT / N;
        float avgTAT = sumTAT / N;

        System.out.println("\nAverage Waiting Time: " + avgWT);
        System.out.println("Average Turn-Around Time: " + avgTAT);
    }
}
