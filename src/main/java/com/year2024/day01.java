package com.year2024;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

import static java.lang.Character.isDigit;

public class day01 {
    /*
        solution approach:
        - get input
            - each line as has two numbers, store them in two separate arrays
        - sort numbers in arrays from lowest to highest number
        - calculate difference for each index
        - sum up differences -> solution
     */
    public static void main(String[] args) throws Exception {

        System.out.println("Advent of Code 2024 – Day 1, Part 1");
        String inputFile = "/2024/day01.txt";
        int n = 1000;

        // possibility to switch to example input
        System.out.println("Do you want to run on example input? (y/n)");

        Scanner sc = new Scanner(System.in);
        char ynInput = sc.nextLine().trim().toLowerCase().charAt(0);

        if (ynInput == 'y') {
            inputFile = "/2024/day01e.txt";
            n = 6;
        }

        // get puzzle input
        final InputStream source = com.year2024.day01.class.getResourceAsStream(inputFile);
        int[] locationA = new int[n];
        int[] locationB = new int[n];
        try(Scanner input = new Scanner(source)) {
            for (int i=0; input.hasNextLine(); i++) {
                locationA[i] = Integer.parseInt(input.next());
                locationB[i] = Integer.parseInt(input.next());
            }
        }

        // sort location lists
        Arrays.sort(locationA);
        Arrays.sort(locationB);

        // compute calibration value for each line and sum up
        int valueSum = 0;
        for (int i=0; i<n; i++) {
            int value = Math.abs(locationA[i] - locationB[i]);
            valueSum += value;
        }

        // print result
        System.out.println("The result is: " + valueSum);
        // solution part 1: 2057374 (example: 11)


        // Test output
        /*
        System.out.println("****************");
        */
    }
}
