package org.example;

public class Data implements Comparable<Data> {
    private final String word;
    private final Integer frequency;
    private final String percentage;

    public Data(String word, int frequency, int totalWords) {
        this.word = word;
        this.frequency = frequency;
        this.percentage = String.valueOf(((double)frequency / (double) totalWords) * 100);
    }

    @Override
    public String toString() {
        return word + ", " + frequency + ", " + percentage;
    }

    @Override
    public int compareTo(Data wordWithFrequency) {
        return this.frequency.compareTo(wordWithFrequency.frequency);
    }
}
