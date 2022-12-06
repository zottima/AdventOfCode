package com.year2022;

import java.io.InputStream;
import java.util.Scanner;

public class day04 {
    public static void main(String[] args) throws Exception {
        final InputStream source = day04.class.getResourceAsStream("/day04.txt");

        // Get assignment pairs
        int[][] assignmentPairs = new int[1000][4];
        try(Scanner input = new Scanner(source).useDelimiter(",|-|\\r\\n|\\n")) {
            for (int i=0; input.hasNextLine(); i++) {
                for (int j=0; j<4; j++) {
                    assignmentPairs[i][j] = Integer.parseInt(input.next());
                }
            }
        }

        // Check if one range is fully covered by the other range
        Boolean[] fullyCovered = new Boolean[1000];
        int numberOfFullyCovered = 0;

        for (int i=0; i<1000; i++) {
            if (assignmentPairs[i][0] == assignmentPairs[i][2]) {
                fullyCovered[i] = true;
                numberOfFullyCovered++;
            } else if (assignmentPairs[i][0] <= assignmentPairs[i][2]) {
                if (assignmentPairs[i][1] >= assignmentPairs[i][3]) {
                    fullyCovered[i] = true;
                    numberOfFullyCovered++;
                } else {
                    fullyCovered[i] = false;
                }
            } else if (assignmentPairs[i][1] <= assignmentPairs[i][3]) {
                fullyCovered[i] = true;
                numberOfFullyCovered++;
            } else {
                fullyCovered[i] = false;
            }
        }

        // Check for any overlap
        Boolean[] hasOverlap = new Boolean[1000];
        int numberOfOverlappingPairs = 0;

        for (int i=0; i<1000; i++) {
            if ((assignmentPairs[i][1] < assignmentPairs[i][2]) || assignmentPairs[i][0] > assignmentPairs[i][3]) {
                hasOverlap[i] = false;
            } else {
                hasOverlap[i] = true;
                numberOfOverlappingPairs++;
            }
        }

        // Print solution:
        System.out.println("Solution Part 1: " + numberOfFullyCovered);
        System.out.println("Solution Part 2: " + numberOfOverlappingPairs);

        // Print for testing along the way
//        System.out.println("\nOutput for testing:");
//        System.out.println(assignmentPairs[4][2]);
    }
}
