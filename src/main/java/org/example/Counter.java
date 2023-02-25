package org.example;

import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

public class Counter {
    private final List<Data> dataList = new ArrayList<>();
    private int totalWords = 0;

    private Counter(Scanner in) {
        Map<String, Integer> map = new TreeMap<>();
        String word;
        int count;
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
        return counter.dataList.stream().map(Object::toString).collect(Collectors.joining("\n"));
    }

    private static final class Data implements Comparable<Data> {
        private final String word;
        private final Integer frequency;
        private final String percentage;

        private Data(String word, int frequency, int totalWords) {
            this.word = word;
            this.frequency = frequency;
            this.percentage = String.valueOf(((double)frequency / (double) totalWords) * 100);
        }

        @Override
        public String toString() {
            return word + ", " + frequency + ", " + percentage;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (obj == null || obj.getClass() != this.getClass()) return false;
            var that = (Data) obj;
            return this.frequency.equals(that.frequency);
        }

        @Override
        public int compareTo(Data wordWithFrequency) {
            return this.frequency.compareTo(wordWithFrequency.frequency);
        }
    }
}
