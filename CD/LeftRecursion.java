import java.util.*;

public class LeftRecursion {
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

        System.out.println("\nGrammar after left recursion removal:");
        for (String nonTerminal : grammar.keySet()) {
            removeLeftRecursion(nonTerminal, grammar);
        }

        scanner.close();
    }

    public static void removeLeftRecursion(String nonTerminal, Map<String, List<String>> grammar) {
        List<String> productions = grammar.get(nonTerminal);
        List<String> alpha = new ArrayList<>();
        List<String> beta = new ArrayList<>();

        for (String production : productions) {
            if (production.startsWith(String.valueOf(nonTerminal))) {
                alpha.add(production.substring(1));
            } else {
                beta.add(production);
            }
        }

        if (alpha.isEmpty()) {
            System.out.println(nonTerminal + " -> " + String.join(" | ", productions));
            return;
        }

        String newNonTerminal = nonTerminal + "'";
        System.out.println(nonTerminal + " -> " + String.join(" | ", beta) + " | " + newNonTerminal);
        System.out.println(newNonTerminal + " -> " + String.join(" | ", alpha) + " | ε");

        grammar.put(newNonTerminal, alpha);
    }
}

// Enter the number of productions: 3
// Enter the productions (e.g., S -> a | aS | (S)):
// S -> Sa | Sb | c
