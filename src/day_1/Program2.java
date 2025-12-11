package day_1;

import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Program2{
    public static void main(String[] args) {
        // Input string
        String str = "aabbbccccddddddee";

        /*
         Step 1: Convert the string to a Stream of characters.
         str.chars() returns an IntStream of ASCII values.
         mapToObj(c -> (char)c) converts each int to a Character object.
        */
        Optional<Map.Entry<Character, Long>> maxChar = str.chars()
                .mapToObj(c -> (char) c)

                /*
                 Step 2: Group the characters and count occurrences.
                 - Function.identity() means we group by the character itself.
                 - Collectors.counting() counts how many times each character appears.
                 - The result is a Map<Character, Long>.
                */
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                ))

                /*
                 Step 3: Convert the map to a Stream of entries (key-value pairs)
                 so that we can find the maximum by value.
                */
                .entrySet()
                .stream()

                /*
                 Step 4: Find the entry with the maximum count.
                 Map.Entry.comparingByValue() compares entries by their values (frequency).
                 The result is an Optional because the map could be empty.
                */
                .max(Map.Entry.comparingByValue());

        /*
         Step 5: Print the most frequent character and its count.
         ifPresent() executes the lambda only if the Optional is not empty.
        */
        maxChar.ifPresent(e -> System.out.println(
                "Most frequent character: " + e.getKey() + ", count: " + e.getValue()
        ));
    }
}
