package org.example;

import java.io.FileReader;
import java.util.*;

public class Counter {
    private final List<Data> dataList = new ArrayList<>();

    private Counter(Scanner in) {
        Map<String, Integer> map = new TreeMap<>();
        String word;
        int count;
        int totalWords = 0;
        while (in.hasNext()) {
            word = in.next();
            totalWords++;
            if (map.containsKey(word)) {
                count = map.get(word);
                map.replace(word, count + 1);
            } else {
                map.put(word, 1);
            }
        }
//        map.forEach((key, value) -> dataList.add(new Data(key, value, totalWords)));

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            Data tmp = new Data(entry.getKey(),  entry.getValue(), totalWords);
            dataList.add(tmp);
        }

        dataList.sort(Comparator.reverseOrder());
    }

    public static String getWordsWithFrequencies(FileReader in) {
        Counter counter = new Counter(new Scanner(in));

        StringJoiner str = new StringJoiner("\n");

        counter.dataList.forEach(data -> str.add(data.toString()));

        return str.toString();
    }
}
