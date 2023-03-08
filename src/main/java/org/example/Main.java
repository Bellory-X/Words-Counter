package org.example;

import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try (Scanner in = args.length > 0 ? new Scanner(new File(args[0])) : new Scanner(System.in);
             FileWriter out = args.length > 1 ? new FileWriter(args[1]) : null)
        {
            if (out == null)
                System.out.println(new Counter(in).getWordsWithFrequencies());
            else
                out.write(new Counter(in).getWordsWithFrequencies());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}