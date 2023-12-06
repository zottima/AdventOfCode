package com.year2022;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

public class day08 {
    public static void main(String[] args) throws Exception {
        final InputStream source = day08.class.getResourceAsStream("/2022/day08.txt");

        // Read input
        int gridSize = 99;
        int[][] treeGrid = new int[gridSize][gridSize];

        try(Scanner input = new Scanner(source)) {
            char[] inputString = new char[gridSize];
            for (int i=0; input.hasNextLine(); i++) {
                inputString = input.nextLine().toCharArray();
                for (int j=0; j<gridSize; j++) {
                    treeGrid[i][j] = inputString[j] - '0';
                }
            }
        }

        // PART 1

        // Mark all visible trees
        boolean[][] isTreeVisible = new boolean[gridSize][gridSize];

        // All trees on the edge are visible
        for (int i=0; i<gridSize; i++) {
            isTreeVisible[0][i] = true;             // top edge
            isTreeVisible[gridSize-1][i] = true;    // bottom edge
            isTreeVisible[i][0] = true;             // left edge
            isTreeVisible[i][gridSize-1] = true;    // right edge
        }

        // Look from left
        for (int row=1; row<gridSize-1; row++) {
            int maxTreeHeight = treeGrid[row][0];
            for (int col=1; col<gridSize-1; col++) {
                if (treeGrid[row][col] > maxTreeHeight) {
                    maxTreeHeight = treeGrid[row][col];
                    isTreeVisible[row][col] = true;
                }
            }
        }

        // Look from right
        for (int row=1; row<gridSize-1; row++) {
            int maxTreeHeight = treeGrid[row][gridSize-1];
            for (int col=gridSize-2; col>0; col--) {
                if (treeGrid[row][col] > maxTreeHeight) {
                    maxTreeHeight = treeGrid[row][col];
                    isTreeVisible[row][col] = true;
                }
            }
        }

        // Look from top
        for (int col=1; col<gridSize-1; col++) {
            int maxTreeHeight = treeGrid[0][col];
            for (int row=1; row<gridSize-1; row++) {
                if (treeGrid[row][col] > maxTreeHeight) {
                    maxTreeHeight = treeGrid[row][col];
                    isTreeVisible[row][col] = true;
                }
            }
        }

        // Look from bottom
        for (int col=1; col<gridSize-1; col++) {
            int maxTreeHeight = treeGrid[gridSize-1][col];
            for (int row=gridSize-2; row>0; row--) {
                if (treeGrid[row][col] > maxTreeHeight) {
                    maxTreeHeight = treeGrid[row][col];
                    isTreeVisible[row][col] = true;
                }
            }
        }

        // Count all visible trees
        int visibleTrees = 0;

        for (int i=0; i<gridSize; i++) {
            for(int j=0; j<gridSize; j++) {
                if (isTreeVisible[i][j] == true) {
                    visibleTrees++;
                }
            }
        }

        // PART 2

        int[] sceniceScore = new int[gridSize * gridSize];
        int maxScore = 0;

        for (int row=1; row<gridSize-1; row++ ) {       // loop through all trees
            for (int col=1; col<gridSize-1; col++) {          // to check surroundings
                int thisHeight = treeGrid[row][col];    // get height of current tree

                int distanceUp = 1;
                Boolean viewIsBlocked = false;
                for (int i=row-1; i>0 && !viewIsBlocked; i-- ) {           // look up
                    if (thisHeight > treeGrid[i][col]) {
                        distanceUp++;
                    } else {
                        viewIsBlocked = true;
                    }
                }

                int distanceDown = 1;
                viewIsBlocked = false;
                for (int i=row+1; i<gridSize-1 && !viewIsBlocked; i++) {       // look down
                    if (thisHeight > treeGrid[i][col]) {
                        distanceDown++;
                    } else {
                        viewIsBlocked = true;
                    }
                }

                int distanceRight = 1;
                viewIsBlocked = false;
                for (int i=col+1; i<gridSize-1 && !viewIsBlocked; i++) {      // look right
                    if (thisHeight > treeGrid[row][i]) {
                        distanceRight++;
                    } else {
                        viewIsBlocked = true;
                    }
                }

                int distanceLeft = 1;
                viewIsBlocked = false;
                for (int i=(col-1); i>0 && !viewIsBlocked; i--) {            // look left
                    if (thisHeight > treeGrid[row][i]) {
                        distanceLeft++;
                    } else {
                        viewIsBlocked = true;
                    }
                }

                // calculate scenic score
                int index = row * gridSize + col + 1;
                sceniceScore[index] = distanceUp * distanceDown * distanceRight * distanceLeft;
            }

            // Find max for scenic score
            Arrays.sort(sceniceScore);
            maxScore = sceniceScore[sceniceScore.length-1];
        }

        // Print for testing along the way
//        System.out.println("\nOutput for testing:");
//        System.out.println(treeGrid[2][0]);
//        System.out.println(visibleTrees);

        // Print solution:
        System.out.println("Solution Part 1: " + visibleTrees);
        System.out.println("Solution Part 2: " + maxScore);
    }
}
