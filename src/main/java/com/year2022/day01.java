package com.year2022;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

public class day01 {
    public static void main(String[] args) throws Exception {
        final InputStream source = day01.class.getResourceAsStream("/2022/day01.txt");
        int[] calories = new int[2000];
        int elves = 0;

        // Sum up calories per Elf
        try(Scanner input = new Scanner(source)) {
            while (input.hasNextLine()) {
                boolean isBlankLine = false;
                int calorieSum = 0;
                int caloriePack = 0;
                while (!isBlankLine && input.hasNextLine()) {
                    String inputLine = input.nextLine();
                    if ("".equals(inputLine)) {
                        isBlankLine = true;
                        calories[elves] = calorieSum;
                        elves++;
                    } else {
                        caloriePack = Integer.parseInt(inputLine);
                        calorieSum += caloriePack;
                    }
                }
            }
        }

        // Find Elf carrying the most calories

        Arrays.sort(calories);
        int maxCalories = calories[calories.length - 1];
        System.out.println("Solution Part 1: " + maxCalories);

        // Part 2: Sum of top three calorie carriers
        int topThreeSum = calories[calories.length - 1] + calories[calories.length - 2] + calories[calories.length - 3];
        System.out.println("Solution Part 2: " + topThreeSum);
    }
}
