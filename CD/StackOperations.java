import java.util.*;

public class StackOperations {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Stack<Integer> stack = new Stack<>();
        int choice;

        do {
            System.out.println("\n1. Push\n2. Pop\n3. Display\n4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter a value to push: ");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case 2:
                    if (!stack.isEmpty()) {
                        System.out.println("Popped value: " + stack.pop());
                    } else {
                        System.out.println("Stack is empty!");
                    }
                    break;
                case 3:
                    if (!stack.isEmpty()) {
                        System.out.println("Stack contents: " + stack);
                    } else {
                        System.out.println("Stack is empty!");
                    }
                    break;
                case 4:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 4);

        scanner.close();
    }
}

// 1. Push
// 2. Pop
// 3. Display
// 4. Exit
// Enter your choice: 1
// Enter a value to push: 10
// Enter your choice: 1
// Enter a value to push: 20
// Enter your choice: 3
// Stack contents: [10, 20]
// Enter your choice: 2
// Popped value: 20
// Enter your choice: 3
// Stack contents: [10]
