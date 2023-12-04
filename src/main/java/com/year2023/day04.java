package com.year2023;

import java.io.InputStream;
import java.util.Scanner;

/*
    Solution approach:
    (Part 1)
    - get input
    - split input into two arrays: winning numbers, my numbers
    - per line
        - check for each winning number if it's contained in my numbers
        - if yes, increase counter
        - calculate points: 1*2^(counter-1)
    - sum up all points
 */
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

        // get puzzle input
        final InputStream source = com.year2023.day04.class.getResourceAsStream(inputFile);
        String[] gameInput = new String[n];
        try (Scanner input = new Scanner(source)) {
            for (int i = 0; input.hasNextLine(); i++) {
                gameInput[i] = input.nextLine();
            }
        }

        // get numbers
        int[][] winningNumbers = new int[n][nWin];
        int[][] myNumbers = new int[n][nMine];

        for (int i=0; i<n; i++) {   // card
            Scanner card = new Scanner(gameInput[i]);
            card.next();        // no need for "Card x:" bit
            card.next();

            // winning numbers
            for (int j=0; j<nWin; j++) {
                winningNumbers[i][j] = Integer.parseInt(card.next());
            }
            // hop over vertical bar
            card.next();

            // my numbers
            for (int k=0; k<nMine; k++) {
                myNumbers[i][k] = Integer.parseInt(card.next());
            }
        }

        // check how many matches per row
        int[] matches = new int[n];

        for (int i=0; i<n; i++) {
            matches[i] = checkNumOfMatches(winningNumbers[i], myNumbers[i]);
        }

        // compute points
        int points = 0;
        for (int i=0; i<n; i++) {
            if (matches[i] > 0) {
                points += (int)Math.pow(2, matches[i]-1);
            }
        }

        // print result
        System.out.println("The result for part 1 is: " + points);
    }

    public static int checkNumOfMatches(int[] winNums, int[] myNums) {
        int matches = 0;
        int n = winNums.length;

        for (int i=0; i<n; i++) {
            if (checkIfContains(winNums[i], myNums)) {
                matches++;
            }
        }
        return matches;
    }

    public static boolean checkIfContains(int number, int[] numList) {
        boolean isContained = false;
        int n = numList.length;

        for (int i=0; i<n; i++) {
            if (number == numList[i]) {
                isContained = true;
                break;
            }
        }

        return isContained;
    }
}
