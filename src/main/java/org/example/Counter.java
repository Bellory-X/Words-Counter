package org.example;

import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

public class Counter {
    private final TreeMap<String, Integer> map = new TreeMap<>();
    private final List<Data> dataList = new ArrayList<>();
    private int totalWords = 0;

    private Counter(Scanner in) {
        String word;
        int value;
        while (in.hasNext()) {
            word = in.next();
            totalWords++;
            if (map.containsKey(word)) {
                value = map.get(word);
                map.replace(word, value + 1);
            } else {
                map.put(word, 1);
            }
        }
        fromMapToList();
    }

    public static String getWordsWithFrequencies(FileReader in) {
        Counter counter = new Counter(new Scanner(in));
        return counter.formatData();
    }

    private void fromMapToList () {
        map.forEach((key, value) -> dataList.add(new Data(key, value, totalWords)));
        dataList.sort(Comparator.reverseOrder());
    }

    private String formatData () {
        return dataList.stream().map(Object::toString).collect(Collectors.joining("\n"));
    }

    public static final class Data implements Comparable<Data> {
        private final String word;
        private final Integer frequency;
        private final String percentage;

        private Data(String word, int frequency, int totalWords) {
            this.word = word;
            this.frequency = frequency;
            this.percentage = getPercentage(totalWords);
        }

        private String getPercentage(int totalWords) {
            return String.valueOf(((double)frequency / (double) totalWords) * 100);
        }

        public int frequency() {
            return frequency;
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
            return this.word.equals(that.word) &&
                    this.frequency.equals(that.frequency) ;
        }

        @Override
        public int compareTo(Data wordWithFrequency) {
            return this.frequency.compareTo(wordWithFrequency.frequency());
        }
    }
}
