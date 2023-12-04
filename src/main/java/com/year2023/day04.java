package com.year2023;

import java.util.Scanner;

public class day04 {
    public static void main(String[] args) throws Exception {
        System.out.println("Advent of Code – Day 4");
        String inputFile = "/2023/day04.txt";
        int n = 211;
        int nWin = 10;
        int nMine = 25;

        // possibility to switch to example input
        System.out.println("Do you want to run on example input? (y/n)");

        Scanner sc = new Scanner(System.in);
        char ynInput = sc.nextLine().trim().toLowerCase().charAt(0);

        if (ynInput == 'y') {
            inputFile = "/2023/day04e.txt";
            n = 6;
            nWin = 5;
            nMine = 8;
        }
    }
}
