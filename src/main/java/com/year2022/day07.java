package com.year2022;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class day07 {
    public static void main(String[] args) throws Exception {
        final InputStream source = day07.class.getResourceAsStream("/day07.txt");

        // Read input and store in List
        ArrayList<String> inputList = getInput(source);

        // Get max Level of directories
        int maxLevel = getMaxLevel(inputList);

        // Calculate size of directories
        ArrayList<Integer> dirSizeList = new ArrayList<>(100);
        int currentLevel = 0;
        int[] sizeOnLevel = new int[maxLevel+1];

        for (int i=0; i<inputList.size(); i++) {
            String currentLine = inputList.get(i);
            if (currentLine.contains("$ cd")) {
                if (currentLine.contains("..")) {
                    dirSizeList.add(sizeOnLevel[currentLevel]);    // add size sum of current level to List
                    sizeOnLevel[currentLevel-1]+= sizeOnLevel[currentLevel];    // add size sum parent dir
                    sizeOnLevel[currentLevel] = 0;          // reset current level sum
                    currentLevel--;                         // decrease level
                } else {                                    // line is $ cd <dir>
                    currentLevel++;                         // increase level
                }
            } else if (startsWithDigit(currentLine)) {    // add to current level size
                sizeOnLevel[currentLevel]+= extractSize(currentLine);
            }
        }

        // add size sums all the way up after last dir check
        for (int i=currentLevel; i>0; i--) {
            dirSizeList.add(sizeOnLevel[currentLevel]);    // add size sum of current level to List
            sizeOnLevel[currentLevel-1]+= sizeOnLevel[currentLevel];    // add size sum parent dir
            currentLevel--;                         // decrease level
        }

        // sum up all directories with filesize with max X
        int maxSize = 100000;
        int sumSize = 0;
        for (int i=0; i<dirSizeList.size(); i++) {
            if (dirSizeList.get(i) <= maxSize) {
                sumSize+= dirSizeList.get(i);
            }
        }

        // PART 2
        int totalSpace = 70000000;
        int usedSpace = getUsedSpace(dirSizeList);
        int requiredSpace = 30000000;
        int spaceNeeded = requiredSpace- (totalSpace - usedSpace);

        // Find directory closest to at least <spaceNeeded>
        // sort list
        Integer[] sortedList = new Integer[dirSizeList.size()];
        sortedList = dirSizeList.toArray(sortedList);
        Arrays.sort(sortedList);

        // Loop through list to find first size that is >= <spaceNeeded>
        Boolean foundDir = false;
        int sizeOfDirToDelete = 0;
        for (int i=0; i<sortedList.length && !foundDir; i++) {
            if (sortedList[i] >= spaceNeeded) {
                sizeOfDirToDelete = sortedList[i];
                foundDir = true;
            }
        }

    // Print solutions:
    System.out.println("Solution Part 1: " + sumSize);
    System.out.println("Solution Part 2: " + sizeOfDirToDelete);

    }

    public static ArrayList<String> getInput(InputStream source) {
        ArrayList<String> inputList = new ArrayList<>();
        try(Scanner input = new Scanner(source)) {
            while (input.hasNextLine()) {
                inputList.add(input.nextLine());
            }
        }
        return (inputList);
    }
    public static int getMaxLevel(ArrayList<String> inputList) {
        // count max level of directories; root being level 1
        int maxLevel = 0;
        int currentLevel = 0;
        for (int i=0; i<inputList.size(); i++) {
            if (inputList.get(i).contains("$ cd")) {
                if (inputList.get(i).contains("..")){
                    currentLevel--;
                } else {
                    currentLevel++;
                    if (currentLevel > maxLevel) {
                        maxLevel = currentLevel;
                    }
                }
            }
        }
        return (maxLevel);
    }

    public static Boolean startsWithDigit(String string) {
        Boolean isDigit = false;
        char ch = string.charAt(0);
        if (ch > 48 && ch < 58) {
            isDigit = true;
        }
        return isDigit;
    }

    public static int extractSize(String string) {
        Scanner in = new Scanner(string).useDelimiter("[^0-9]+");
        int size = in.nextInt();
        return size;
    }

    public static int getUsedSpace(ArrayList<Integer> list) {
        Integer[] listInt = new Integer[list.size()];
        listInt = list.toArray(listInt);
        Arrays.sort(listInt);
        int usedSpace = listInt[list.size()-1];
        return usedSpace;
    }

}
