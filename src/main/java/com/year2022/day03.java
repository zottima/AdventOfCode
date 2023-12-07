package com.year2022;

import java.io.InputStream;
import java.util.Scanner;

public class day03 {
    public static void main(String[] args) throws Exception {
        final InputStream source = day03.class.getResourceAsStream("/2022/day03.txt");
        String[] rucksackContent = new String[300];
        int[] misplacedItems = new int[300];
        int[] itemPriority = new int[300];
        int offsetLowerCase = 96;
        int offsetUpperCase = 38;
        char[][] sharedTypes12 =  new char[100][60];
        int[] badgeItemTypes = new int[100];
        int[] indexCounter = new int[100];

        // Get content from all rucksacks
        try(Scanner input = new Scanner(source)) {
            for (int i=0; input.hasNextLine(); i++) {
                rucksackContent[i] = input.next();
            }
        }

        // Put all item types into array and find misplaced item
        char[] itemTypes = new char[60];
        int numberOfItems = 0;
        for (int i = 0; i < 300; i++) {
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
        int sumPriorities = 0;
        for (int i = 0; i<300; i++){
            if (misplacedItems[i] >96) {
                itemPriority[i] = misplacedItems[i] - offsetLowerCase;
            } else {
                itemPriority[i] = misplacedItems[i] - offsetUpperCase;
            }
            sumPriorities+= itemPriority[i];
        }

        // Compare 1st and 2nd per group and find matching item types
        for (int i=0; i<100; i++) {
            char[] itemTypesFirstSack = rucksackContent[i*3].toCharArray();
            char[] itemTypesSecondSack = rucksackContent[i*3+1].toCharArray();
            int index = 0;
            for (int j=0; j < itemTypesFirstSack.length; j++) {
                Boolean matchFound = false;
                for (int k=0; (k < itemTypesSecondSack.length) && !matchFound; k++) {
                    if (itemTypesFirstSack[j] == itemTypesSecondSack[k]) {
                        sharedTypes12[i][index] = itemTypesFirstSack[j];
                        indexCounter[i] = index;
                        index++;
                        matchFound = true;
                    }
                }
            }
        }

        // Find shared item type between all three per group
        for (int i=0; i<100; i++) {
            char[] itemTypesThirdSack = rucksackContent[i*3+2].toCharArray();
            Boolean matchFound = false;
            for (int j=0; (j < itemTypesThirdSack.length) && !matchFound; j++) {
                for (int k=0; (k <= indexCounter[i]) && !matchFound; k++) {
                    if (itemTypesThirdSack[j] == sharedTypes12[i][k]) {
                        badgeItemTypes[i] = itemTypesThirdSack[j];
                        matchFound = true;
                    }
                }
            }
        }

        // Calculate item type priority and total of priorities
        int sumBadgePrio = 0;
        int[] badgePriority = new int[100];
        for (int i=0; i<100; i++){
            if (badgeItemTypes[i] > 96) {
                badgePriority[i] = badgeItemTypes[i] - offsetLowerCase;
            } else {
                badgePriority[i] = badgeItemTypes[i] - offsetUpperCase;
            }
            sumBadgePrio+= badgePriority[i];
        }

        // Print solution
        System.out.println("Solution Part 1: " + sumPriorities);
        System.out.println("Solution Part 2: " + sumBadgePrio);

        // Print for testing along the way
//          System.out.println("\nOutput for testing:");
//        System.out.println(rucksackContent[2]);
//        System.out.println(rucksackContent[4].length());
//        System.out.println(itemTypes[3]);
//        System.out.println(misplacedItems[4]);
//        System.out.println(sharedTypes12[99]);
//        System.out.println(badgeItemTypes[3]);
//        System.out.println(indexCounter[1]);
//        System.out.println(sharedTypes12[99][1]);
//        System.out.println(badgeItemTypes[1]);
//        System.out.println(badgeItemTypes.length);
//        System.out.println(badgePriority[99]);

    }
}
