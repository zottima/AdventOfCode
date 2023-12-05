package com.year2023;

import java.io.InputStream;
import java.util.Scanner;

/*
    Solution approach:
    (Part 1)
    - get input
        - save initial seed position
        - extract one mapping instruction line at a time
        - check if seed positions need to be remapped
            - if current seed position is within range source range start and range length
 */
public class day05 {
    public static void main(String[] args) throws Exception {
        System.out.println("Advent of Code – Day 5");
        String inputFile = "/2023/day05.txt";
        int n = 189;        // number of lines in input file
        int seeds = 20;

        // possibility to switch to example input
        System.out.println("Do you want to run on example input? (y/n)");

        Scanner sc = new Scanner(System.in);
        char ynInput = sc.nextLine().trim().toLowerCase().charAt(0);

        if (ynInput == 'y') {
            inputFile = "/2023/day05e.txt";
            n = 33;
            seeds = 4;
        }

        // get puzzle input
        final InputStream source = com.year2023.day05.class.getResourceAsStream(inputFile);
        String[] almanacInput = new String[n];
        try (Scanner input = new Scanner(source)) {
            for (int i = 0; input.hasNextLine(); i++) {
                almanacInput[i] = input.nextLine();
            }
        }
    }
}
