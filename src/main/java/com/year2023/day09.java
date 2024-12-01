package com.year2023;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class day09 {
    public static void main(String[] args) throws Exception {
        System.out.println("Advent of Code – Day 9");
        String inputFile = "/2023/day09.txt";
        int n = 200;        // number of lines in input file

        // possibility to switch to example input
        System.out.println("Do you want to run on example input? (y/n)");

        Scanner sc = new Scanner(System.in);
        char ynInput = sc.nextLine().trim().toLowerCase().charAt(0);

        if (ynInput == 'y') {
            inputFile = "/2023/day09e.txt";
            n = 3;
        }

        // get puzzle input
        final InputStream source = com.year2023.day09.class.getResourceAsStream(inputFile);
        String[] oasisInput = new String[n];
        try (Scanner input = new Scanner(source)) {
            for (int i = 0; input.hasNextLine(); i++) {
                oasisInput[i] = input.nextLine();
            }
        }

        // read input
        ArrayList<Integer> oasis = new ArrayList<>();
        try(Scanner in = new Scanner(source)) {
            while (in.hasNext()) {
                oasis.add(Integer.parseInt(in.next()));
            }
        }
    }
}
