import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AoC2022 {
    public static void main(String[] args) throws Exception {
        printMainTitle();
        printTitleDay(1);
        dayOne();
        printTitleDay(2);
        dayTwo();
        printTitleDay(3);
        dayThree();
    }

    public static void dayThree() {
        final InputStream source = AoC2022.class.getResourceAsStream("day03.txt");
        String[] rucksackContent = new String[300];
        int numberOfItems = 0;
        char[] itemTypes = new char[60];
        int[] misplacedItems = new int[300];
        int[] itemPriority = new int[300];
        int offsetLowerCase = 96;
        int offsetUpperCase = 38;
        int sumPriorities = 0;

        // Get content from all rucksacks
        try(Scanner input = new Scanner(source)) {
            for (int i=0; input.hasNextLine(); i++) {
                rucksackContent[i] = input.next();
            }
        }

        // Put all item types into array and find misplaced item
        for (int i=0; i < 300; i++) {
            numberOfItems = rucksackContent[i].length();
            itemTypes = rucksackContent[i].toCharArray();
            Boolean matchFound = false;
            for (int j=0; (j < (numberOfItems/2)) && !matchFound; j++) {
                for (int k = (numberOfItems/2); (k < numberOfItems) && !matchFound; k++) {
                    if (itemTypes[j] == itemTypes[k]) {
                            matchFound = true;
                            misplacedItems[i] = itemTypes[j];
                    }
                }

            }
        }

        // Calculate item type priority and total of priorities
        for (int i=0; i<300; i++){
            if (misplacedItems[i] >96) {
                itemPriority[i] = misplacedItems[i] - offsetLowerCase;
            } else {
                itemPriority[i] = misplacedItems[i] - offsetUpperCase;
            }
            sumPriorities+= itemPriority[i];
        }

        // Print solution
        System.out.println("Solution Part 1: " + sumPriorities);

        // Print for testing along the way
        System.out.println("\nOutput for testing:");
        System.out.println(rucksackContent[2]);
        System.out.println(rucksackContent[4].length());
        System.out.println(itemTypes[3]);
        System.out.println(misplacedItems[4]);

    }

    public static void dayTwo() {
        final InputStream source = AoC2022.class.getResourceAsStream("day02.txt");
        String[] strategyGuide = new String[2500];
        int[] score = new int[2500];
        int totalScore = 0;

        // Mapping RPS pair to Score
        Map<String, Integer> scoreMap1 = new HashMap();
        scoreMap1.put("AX", 4);
        scoreMap1.put("AY", 8);
        scoreMap1.put("AZ", 3);
        scoreMap1.put("BX", 1);
        scoreMap1.put("BY", 5);
        scoreMap1.put("BZ", 9);
        scoreMap1.put("CX", 7);
        scoreMap1.put("CY", 2);
        scoreMap1.put("CZ", 6);

        // Mapping RPS-LDW pair to Score
        Map<String, Integer> scoreMap2 = new HashMap();
        scoreMap2.put("AX", 3);
        scoreMap2.put("AY", 4);
        scoreMap2.put("AZ", 8);
        scoreMap2.put("BX", 1);
        scoreMap2.put("BY", 5);
        scoreMap2.put("BZ", 9);
        scoreMap2.put("CX", 2);
        scoreMap2.put("CY", 6);
        scoreMap2.put("CZ", 7);

        // Put input into Array
        try(Scanner input = new Scanner(source)) {
            for (int i = 0; input.hasNextLine(); i++) {
                strategyGuide[i] = input.next() + input.next();
            }
        }

        // Sum up score using mapping
        for (int i = 0; i < strategyGuide.length; i++) {
            score[i] = scoreMap1.get(strategyGuide[i]);
            totalScore += score[i];
        }

        // Prints for checkpoints
        // System.out.println(strategyGuide[4]);
        // System.out.println(scoreMap1.get(strategyGuide[4]));
        // System.out.println(score[4]);

        // Print Solution
        System.out.println("Solution Part 1: " + totalScore);

        // Same for Part 2, but with Mapping2
        totalScore = 0;
        for (int i = 0; i < strategyGuide.length; i++) {
            score[i] = scoreMap2.get(strategyGuide[i]);
            totalScore += score[i];
        }
        System.out.println("Solution Part 2: " + totalScore);

    }

    public static void dayOne() {
        final InputStream source = AoC2022.class.getResourceAsStream("day01.txt");
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

    public static void printMainTitle() {
        System.out.println("\nAdvent of Code - 2022: zottima's solutions");
    }

    public static void printTitleDay(int day) {
        System.out.println("\nDay " + day);
    }
}