import java.util.*;

public class LeftFactoring {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of productions: ");
        int n = scanner.nextInt();
        scanner.nextLine();

        Map<String, List<String>> grammar = new HashMap<>();
        System.out.println("Enter the productions (e.g., S -> a | aS | (S)):");
        for (int i = 0; i < n; i++) {
            String production = scanner.nextLine();
            String[] parts = production.split("->");
            String nonTerminal = parts[0].trim();
            String[] rules = parts[1].split("\\|");

            grammar.put(nonTerminal, new ArrayList<>());
            for (String rule : rules) {
                grammar.get(nonTerminal).add(rule.trim());
            }
        }

        System.out.println("\nGrammar after left factoring:");
        for (String nonTerminal : grammar.keySet()) {
            leftFactor(nonTerminal, grammar);
        }

        scanner.close();
    }

    public static void leftFactor(String nonTerminal, Map<String, List<String>> grammar) {
        List<String> productions = grammar.get(nonTerminal);
        Map<String, List<String>> factored = new HashMap<>();
        String commonPrefix = null;

        for (String production : productions) {
            if (commonPrefix == null || production.startsWith(commonPrefix)) {
                commonPrefix = production;
            }
        }

        String newNonTerminal = nonTerminal + "'";
        System.out.println(nonTerminal + " -> " + commonPrefix + newNonTerminal);
        System.out.println(newNonTerminal + " -> " + commonPrefix + " | ε");
    }
}

// Enter the number of productions: 2
// Enter the productions (e.g., S -> a | aS | (S)):
// S -> ab | ac