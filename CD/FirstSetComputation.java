import java.util.*;

public class FirstSetComputation {
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

        Map<String, Set<String>> firstSets = new HashMap<>();
        for (String nonTerminal : grammar.keySet()) {
            firstSets.put(nonTerminal, new HashSet<>());
        }

        for (String nonTerminal : grammar.keySet()) {
            computeFirst(nonTerminal, grammar, firstSets);
        }

        System.out.println("\nFirst sets:");
        for (String nonTerminal : firstSets.keySet()) {
            System.out.println("First(" + nonTerminal + ") = " + firstSets.get(nonTerminal));
        }

        scanner.close();
    }

    public static void computeFirst(String nonTerminal, Map<String, List<String>> grammar, Map<String, Set<String>> firstSets) {
        if (!firstSets.get(nonTerminal).isEmpty()) {
            return;
        }

        Set<String> firstSet = firstSets.get(nonTerminal);

        for (String production : grammar.get(nonTerminal)) {
            if (isTerminal(production.charAt(0))) {
                firstSet.add(String.valueOf(production.charAt(0)));
            } else {
                for (int i = 0; i < production.length(); i++) {
                    String firstSymbol = String.valueOf(production.charAt(i));

                    if (isTerminal(firstSymbol.charAt(0))) {
                        firstSet.add(firstSymbol);
                        break;
                    } else {
                        computeFirst(firstSymbol, grammar, firstSets);
                        firstSet.addAll(firstSets.get(firstSymbol));
                        if (firstSets.get(firstSymbol).contains("ε")) {
                            continue;
                        } else {
                            break;
                        }
                    }
                }
            }
        }
    }

    public static boolean isTerminal(char c) {
        return Character.isLowerCase(c);
    }
}

// Enter the number of productions: 3
// Enter the productions (e.g., S -> a | aS | (S)):
// S -> aB | bC | d
// B -> a | b
// C -> a | c