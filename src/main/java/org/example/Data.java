package org.example;

public record Data(String word, int frequency, double percentage) implements Comparable<Data> {

    @Override
    public String toString() {
        return word + ", " + frequency + ", " + percentage;
    }

    @Override
    public int compareTo(Data wordWithFrequency) {
        return Integer.compare(this.frequency, wordWithFrequency.frequency);
    }
}
