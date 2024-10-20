import java.util.Arrays;
import java.util.Scanner;

public class SJF {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter number of processes:");

        int N = in.nextInt();
        if (N <= 0) {
            System.out.println("Number of processes should be positive.");
            return;
        }

        int[] bt = new int[N];
        int[] pIndex = new int[N];
        System.out.println("Enter burst times:");

        for (int i = 0; i < N; i++) {
            System.out.print("Enter time for process " + (i + 1) + ": ");
            bt[i] = in.nextInt();
            pIndex[i] = i + 1; // Store the original process index
            if (bt[i] < 0) {
                System.out.println("Burst time should be positive.");
                return;
            }
        }

        // Sort the burst times along with their original process indices
        sortBurstTimes(bt, pIndex);

        calculateTime(bt, pIndex, N);
        in.close();
    }

    public static void sortBurstTimes(int[] bt, int[] pIndex) {
        // Create an array of pairs (burst time, process index)
        Integer[][] processes = new Integer[bt.length][2];

        for (int i = 0; i < bt.length; i++) {
            processes[i][0] = bt[i];   // Burst time
            processes[i][1] = pIndex[i]; // Process index
        }

        // Sort the array based on burst times (first element of each pair)
        Arrays.sort(processes, (a, b) -> a[0].compareTo(b[0]));

        // Unpack the sorted burst times and process indices back into the arrays
        for (int i = 0; i < bt.length; i++) {
            bt[i] = processes[i][0];
            pIndex[i] = processes[i][1];
        }
    }

    public static void calculateTime(int[] bt, int[] pIndex, int N) {
        int[] wt = new int[N];
        int[] tt = new int[N];

        wt[0] = 0;
        tt[0] = bt[0] + wt[0];

        System.out.printf("%-10s%-15s%-15s%-15s%n", "P.No.", "Burst Time", "Waiting Time", "Turn-Around Time");
        System.out.printf("%-10d%-15d%-15d%-15d%n", pIndex[0], bt[0], wt[0], tt[0]);

        for (int i = 1; i < N; i++) {
            wt[i] = bt[i - 1] + wt[i - 1];
            tt[i] = bt[i] + wt[i];
            System.out.printf("%-10d%-15d%-15d%-15d%n", pIndex[i], bt[i], wt[i], tt[i]);
        }

        float sumWT = 0, sumTT = 0;
        for (int i = 0; i < N; i++) {
            sumWT += wt[i];
            sumTT += tt[i];
        }

        float avgWT = sumWT / N;
        float avgTT = sumTT / N;

        System.out.println("Average waiting time is " + avgWT);
        System.out.println("Average turn-around time is " + avgTT);
    }
}
