package com.year2023;

import java.io.InputStream;
import java.util.Scanner;

public class day01 {
    /*
        solution approach:
        - data "containers" needed:
            - input array of strings (1000, example: 4)
            - calibrationDigits array of int (1000 x 2, example: 4 x 2)
            - calibrationValue array of int (1000, example 4)
            - calibrationSum int -> solution
        - parse input into input array
        - per line:
            - check from front: first char that is digit, save to calibration array
            - check from back: first char that is digit, save to calibration array
        - digits into value array: 10 * first + last
        - sum over value array
     */
    public static void main(String[] args) throws Exception {

        System.out.println("Advent of Code – Day 1, Part 1");
        String inputFile = "/2023/day01.txt";
        int n = 1000;

        // possibility to switch to example input
        System.out.println("Do you want to run on example input? (y/n)");

        Scanner sc = new Scanner(System.in);
        char ynInput = sc.nextLine().trim().toLowerCase().charAt(0);

        if (ynInput == 'y') {
            inputFile = "/2023/day01e.txt";
            n = 4;
        }     

        // get puzzle input
        final InputStream source = com.year2023.day01.class.getResourceAsStream(inputFile);
        String[] calibrationDoc = new String[n];
        try(Scanner input = new Scanner(source)) {
            for (int i=0; input.hasNextLine(); i++) {
                calibrationDoc[i] = input.next();
            }
        }

        // Test output
        System.out.println(calibrationDoc[calibrationDoc.length-1]);
    }
}
