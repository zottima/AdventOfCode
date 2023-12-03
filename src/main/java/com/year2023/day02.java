package com.year2023;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

public class day02 {
    /*
    Solution approach:
        - get input
        - parse per line:
            - pairs of number and colour
            - if number is > than stored number for this colour, replace, otherwise move on
                (assuming part 2 might need that)
        - alternative (per line):
            - check if number is <= cubeLoad for respective colour
            - if not, set possible games array to 0 and move on to next line
        - sum up game numbers for possible games (note: game number = index+1)

        - data containers needed:
            - input array (n)
            - cube array (n, 3)
            - possible games array (n)
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

        // game setup
        int red = 12;
        int green = 13;
        int blue = 14;

        // Part 1
        runPart1(gameInput, red, green, blue, n);

        // Part 2
        runPart2(gameInput, red, green, blue, n);

    }

    public static void runPart1(String[] gameInput, int red, int green, int blue, int n) {
        boolean[] isGamePossible = new boolean[n];
        Arrays.fill(isGamePossible, true);

        for (int i=0; i<n; i++) {   // game
            Scanner game = new Scanner(gameInput[i]);
            game.next();    // no need for "Game x:" bit
            game.next();
            while (game.hasNext() && isGamePossible[i]) {
                int num = Integer.parseInt(game.next());
                String cubeColour = game.next();
                if (cubeColour.contains("red")) {
                    if (num > red)
                        isGamePossible[i] = false;
                }  else if (cubeColour.contains("green")) {
                    if (num > green)
                        isGamePossible[i] = false;
                }   else if (cubeColour.contains("blue")) {
                    if (num > blue)
                        isGamePossible[i] = false;
                }
            }
        }

        // sum up IDs of possible games
        int sum = 0;
        for (int i=0; i<n; i++) {
            if (isGamePossible[i]) {
                int gameId = i +1;
                sum += gameId;
            }
        }

        // Print result
        System.out.println("The result for part 1 is: " + sum);

        // Test output
        /*
        System.out.println(isGamePossible[0]);
        System.out.println(isGamePossible[1]);
        System.out.println(isGamePossible[2]);
        System.out.println(isGamePossible[3]);
        System.out.println(isGamePossible[4]);
        */
    }

    public static void runPart2(String[] gameInput, int red, int green, int blue, int n) {
        int[][] minSetOfCubes = new int[n][3];

        for (int i=0; i<n; i++) {   // game
            int minRed = 0;
            int minGreen = 0;
            int minBlue = 0;

            Scanner game = new Scanner(gameInput[i]);
            game.next();    // no need for "Game x:" bit
            game.next();
            while (game.hasNext()) {
                int num = Integer.parseInt(game.next());
                String cubeColour = game.next();
                if (cubeColour.contains("red")) {
                    if (num > minRed)
                        minRed = num;
                } else if (cubeColour.contains("green")) {
                    if (num > minGreen)
                        minGreen = num;
                } else if (cubeColour.contains("blue")) {
                    if (num > minBlue)
                        minBlue = num;
                }
            }

            minSetOfCubes[i][0] = minRed;
            minSetOfCubes[i][1] = minGreen;
            minSetOfCubes[i][2] = minBlue;
        }

        // compute power for each game and sum up
        int sum = 0;
        for (int i=0; i<n; i++) {
            sum += minSetOfCubes[i][0] * minSetOfCubes[i][1] * minSetOfCubes[i][2];
        }

        System.out.println("The result for part 2 is: " + sum);
    }
}
