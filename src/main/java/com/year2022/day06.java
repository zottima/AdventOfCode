package com.year2022;

import java.io.InputStream;
import java.util.Scanner;

public class day06 {
    public static void main(String[] args) throws Exception {
        final InputStream source = day06.class.getResourceAsStream("/2022/day06.txt");

        // Read input
        String inputString = new String();
        try(Scanner input = new Scanner(source)) {
            inputString = input.next();
        }

        // Toss input into char array
//        int inputLength = inputString.length();
        char[] datastream = inputString.toCharArray();

        // Find first sequence of n different letters
        int n = 14;
        char[] sequence = new char[n];
        int position = n;
        Boolean sequenceIsUnique = false;

        for (int i=0; i<datastream.length && !sequenceIsUnique; i++) {
            Boolean charsMatch = false;
            for (int j=0; j<n && !charsMatch; j++) {               // sequence to check
                sequence[j] = datastream[i + j];
                if (j>0) {
                    for (int k=j; k>0 && !charsMatch; k-- ) {
                        if (sequence[j] == sequence[k-1]) {
                            charsMatch = true;
                            sequenceIsUnique = false;
                            position++;
                        } else {
                            sequenceIsUnique = true;
                        }
                    }
                }
            }
        }

        // Print for testing along the way
        System.out.println("\nOutput for testing:");
//        System.out.println(inputString);
//        System.out.println(inputLength);
//        System.out.println(datastream[5]);
//        System.out.println(datastream.length);
//        System.out.println(position);

        // Print solution:
        System.out.println("Solution Part 1: " + position);
        //       System.out.println("Solution Part 2: " + topCratesP2);
    }
}
