package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class Counter {
    private final Map<String, Integer> map = new HashMap<>();
    private int totalWords;

    public Counter(Scanner in) {
        String word;
        while (in.hasNext()) {
            word = in.next();
            totalWords++;
            if (map.containsKey(word))
                map.replace(word, map.get(word) + 1);
            else
                map.put(word, 1);
        }
    }

    public String getWordsWithFrequencies() {
        return map.entrySet().stream()
                .map(entry -> new Data(entry.getKey(), entry.getValue(),
                ((double) entry.getValue() / totalWords) * 100))
                .sorted(Comparator.reverseOrder())
                .map(Data::toString)
                .collect(Collectors.joining("\n"));
    }
}
