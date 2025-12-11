package day_1;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/*
*   Program to find first non-repeating Character in a string  using stream
*/
public class Program1 {
    public static void main(String[] args) {
        String str = "abacdbef";

        Optional<Character> firstNonRepeatingCharacter = str.chars()
                .mapToObj(c -> (char) c) // Convert int stream to Character stream
                .collect(Collectors.groupingBy(
                        Function.identity(),   // Group by character itself
                        LinkedHashMap::new,    // Preserve insertion order
                        Collectors.counting()  // Count occurrences
                ))
                .entrySet()
                .stream()
                .filter(e -> e.getValue() == 1L) // Only keep non-repeating characters
                .map(Map.Entry::getKey)
                .findFirst(); // Get the first one

        // Print the result
        firstNonRepeatingCharacter.ifPresent(System.out::println);
    }
}
