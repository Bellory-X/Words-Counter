package org.example;


import java.io.*;

public class Main {

    public static void main(String[] args) {
        if (args.length == 0 || args.length > 2) {
            System.err.println("""
                Bad input
                First argument: path to .txt file
                Second argument(optional): path to .csv file
                If second argument missing, print result to console
                """);
            return;
        }

        try (FileReader in = new FileReader(args[0]);
             FileWriter out = args.length > 1 ? new FileWriter(args[1]) : null)
        {
            String str = Counter.getWordsWithFrequencies(in);

            if (str.isEmpty()) return;

            if (args.length == 1)
                System.out.println(str);
            else
                out.write(str);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}