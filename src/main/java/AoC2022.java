import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AoC2022 {
    public static void main(String[] args) throws Exception {
//        dayOne();
        dayTwo();
    }

    public static void dayTwo() {
        final InputStream source = AoC2022.class.getResourceAsStream("day02.txt");
        Scanner input = new Scanner(source);
        String[] strategyGuide = new String[2500];
        int[] score = new int[2500];
        int totalScore = 0;

        // Mapping RPS pair to Score
        Map<String, Integer> scoreMap1 = new HashMap();
        scoreMap1.put("AX",4);
        scoreMap1.put("AY",8);
        scoreMap1.put("AZ",3);
        scoreMap1.put("BX",1);
        scoreMap1.put("BY",5);
        scoreMap1.put("BZ",9);
        scoreMap1.put("CX",7);
        scoreMap1.put("CY",2);
        scoreMap1.put("CZ",6);

        // Mapping RPS-LDW pair to Score
        Map<String, Integer> scoreMap2 = new HashMap();
        scoreMap2.put("AX",3);
        scoreMap2.put("AY",4);
        scoreMap2.put("AZ",8);
        scoreMap2.put("BX",1);
        scoreMap2.put("BY",5);
        scoreMap2.put("BZ",9);
        scoreMap2.put("CX",2);
        scoreMap2.put("CY",6);
        scoreMap2.put("CZ",7);


        // Put input into Array
        for (int  i=0; input.hasNextLine(); i++) {
            strategyGuide[i] = input.next() + input.next();
        }
        input.close();

        // Sum up score using mapping
        for (int i = 0; i < strategyGuide.length; i++) {
            score[i] = scoreMap1.get(strategyGuide[i]);
            totalScore+= score[i];
        }

       // Prints for checkpoints
        System.out.println(strategyGuide[4]);
        System.out.println(scoreMap1.get(strategyGuide[4]));
        System.out.println(score[4]);

        // Print Solution
        System.out.println("Solution Part 1: " + totalScore);

        // Same for Part 2, but with Mapping2
        totalScore = 0;
        for (int i = 0; i < strategyGuide.length; i++) {
            score[i] = scoreMap2.get(strategyGuide[i]);
            totalScore+= score[i];
        }
        System.out.println("Solution Part 2: " + totalScore);

    }


    public static void dayOne() {
        final InputStream source = AoC2022.class.getResourceAsStream("day01.txt");
        Scanner input = new Scanner(source);
        int[] calories = new int[2000];
        int elves = 0;

        // Sum up calories per Elf
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
        input.close();

        // Find Elf carrying the most calories

        Arrays.sort(calories);
        int maxCalories = calories[calories.length - 1];
        System.out.println("Solution Part 1: " + maxCalories);

        // Part 2: Sum of top three calorie carriers
        int topThreeSum = calories[calories.length - 1] + calories[calories.length - 2] + calories[calories.length - 3];
        System.out.println("Solution Part 2: " + topThreeSum);

    }
}