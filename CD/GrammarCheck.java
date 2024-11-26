import java.util.*;

public class GrammarCheck {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of productions: ");
        int numProductions = scanner.nextInt();
        scanner.nextLine();

        Map<String, List<String>> grammar = new HashMap<>();

        System.out.println("Enter the productions (e.g., S -> a | aS | (S)):");
        for (int i = 0; i < numProductions; i++) {
            String production = scanner.nextLine();
            String[] parts = production.split("->");
            String nonTerminal = parts[0].trim();
            String[] rules = parts[1].split("\\|");

            grammar.put(nonTerminal, new ArrayList<>());
            for (String rule : rules) {
                grammar.get(nonTerminal).add(rule.trim());
            }
        }

        System.out.print("Enter the string to check: ");
        String inputString = scanner.nextLine();
        
        if (checkGrammar("S", inputString, grammar)) {
            System.out.println("The string belongs to the grammar.");
        } else {
            System.out.println("The string does not belong to the grammar.");
        }

        scanner.close();
    }

    public static boolean checkGrammar(String nonTerminal, String inputString, Map<String, List<String>> grammar) {
        if (inputString.isEmpty()) {
            return false;
        }
        for (String production : grammar.get(nonTerminal)) {
            if (production.equals(inputString)) {
                return true;
            }
            if (production.length() > 1 && production.charAt(0) == inputString.charAt(0)) {
                return checkGrammar(nonTerminal, inputString.substring(1), grammar);
            }
        }
        return false;
    }
}

// Enter the number of productions: 3
// Enter the productions (e.g., S -> a | aS | (S)):
// S -> aS | b | ab
// Enter the string to check: aa