package com.year2023;

import java.io.InputStream;
import java.util.Scanner;

public class day02 {
    /*
    Solution approach:
        -
     */

    public static void main(String[] args) throws Exception {

        System.out.println("Advent of Code – Day 2, Part 1");
        String inputFile = "/2023/day02.txt";
        int n = 100;

        // possibility to switch to example input
        System.out.println("Do you want to run on example input? (y/n)");

        Scanner sc = new Scanner(System.in);
        char ynInput = sc.nextLine().trim().toLowerCase().charAt(0);

        if (ynInput == 'y') {
            inputFile = "/2023/day02e.txt";
            n = 5;
        }

        // get puzzle input
        final InputStream source = com.year2023.day02.class.getResourceAsStream(inputFile);
        String[] gameInput = new String[n];
        try (Scanner input = new Scanner(source)) {
            for (int i = 0; input.hasNextLine(); i++) {
                gameInput[i] = input.nextLine();
            }
        }
    }
}
