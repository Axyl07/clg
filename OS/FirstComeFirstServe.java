import java.util.Scanner;

public class FirstComeFirstServe {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter number of processes");
        
        int N = in.nextInt();
        System.out.println("Enter burst times");
        
        int[] bt = new int[N];
        for (int i = 0; i < N; i++) {
            System.out.println("Enter time for process : " + (i + 1)); // Changed index to (i + 1) for 1-based process numbers
            bt[i] = in.nextInt();
        }     
        calculateTime(bt, N);
        in.close();
    }
    
    public static void calculateTime(int[] bt, int N) {
        int[] wt = new int[N];
        int[] tt = new int[N];
        
        // Initialize waiting time of the first process
        wt[0] = 0; 
        tt[0] = bt[0] + wt[0]; // Correctly set Turn-Around Time for the first process
        
        // Print title headers
        System.out.print("P.No.\t\tBurst Time\tWaiting Time\tTurn-Around Time\n");
        System.out.print("1\t\t" + bt[0] + "\t\t" + wt[0] + "\t\t" + tt[0] + "\n");
        
        // Calculate waiting times and turn-around times for the rest of the processes
        for (int i = 1; i < N; i++) {
            wt[i] = bt[i - 1] + wt[i - 1]; // Calculate waiting time based on previous burst times and waiting time
            tt[i] = bt[i] + wt[i]; // Correctly calculate Turn-Around Time for process i
            System.out.print((i + 1) + "\t\t" + bt[i] + "\t\t" + wt[i] + "\t\t" + tt[i] + "\n");
        }
        
        // Calculate average waiting time
        float sumWT = 0;
        for (int i = 0; i < N; i++) {
            sumWT += wt[i];
        }
        float avgWT = sumWT / N; // Average waiting time
        
        // Calculate average turn-around time
        float sumTT = 0;
        for (int i = 0; i < N; i++) {
            sumTT += tt[i];
        }
        float avgTT = sumTT / N; // Average turn-around time
        
        // Print average times
        System.out.println("Average waiting time is " + avgWT);
        System.out.println("Average turn-around time is " + avgTT);
    }
}
