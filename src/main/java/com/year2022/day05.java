package com.year2022;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class day05 {
    public static void main(String[] args) throws Exception {
        final InputStream source = day05.class.getResourceAsStream("/2022/day05.txt");

        // Get input
        char[][] crateInput = new char[8][35];
        int[] numOfCratesToMove = new int[501];
        int[] startPosition = new int[501];
        int[] endPosition = new int[501];

        try(Scanner input = new Scanner(source)) {
            for (int i=0; (i<8) && input.hasNextLine(); i++) {
                crateInput[i] = input.nextLine().toCharArray();
            }
            // dump the next two lines
            input.nextLine();
            input.nextLine();

            for (int j=0; input.hasNextLine(); j++) {
                input.next();
                numOfCratesToMove[j] = input.nextInt();
                input.next();
                startPosition[j] = input.nextInt();
                input.next();
                endPosition[j] =  input.nextInt();
            }
        }

        // Put crate stacks into lists
        ArrayList<String>[] crateStack = new ArrayList[9];
        ArrayList<String>[] copyOfcrateStack = new ArrayList[9]; // copy for reuse part 2

        for (int i = 0; i < 9; i++) {
            crateStack[i] = new ArrayList<String>();
            copyOfcrateStack[i] = new ArrayList<String>();
        }

        int stackIndex = 0;

        for (int i=1; i<35; i+=4) {
            Boolean reachedEndofStack = false;
            for (int j=7; (j>=0) && !reachedEndofStack; j--) {
                if (Character.isUpperCase(crateInput[j][i])) {
                    String string = new String();
                    crateStack[stackIndex].add(string.valueOf(crateInput[j][i]));
                    copyOfcrateStack[stackIndex].add(string.valueOf(crateInput[j][i]));
                } else {
                    reachedEndofStack = true;
                }
            }
            stackIndex++;
        }

        // Part 1: Rearrange stacks, moving one crate at a time
        for (int i=0; i<501; i++) {     // Loop through instructions
            for (int j=0; j<numOfCratesToMove[i]; j++) {    // How many crates to move
                int positionInStack = crateStack[startPosition[i]-1].size();    // crate on top of stack
                String crateToMove = crateStack[startPosition[i]-1].get(positionInStack-1);     // get crate
                crateStack[endPosition[i]-1].add(crateToMove);    // move to new stack
                crateStack[startPosition[i]-1].remove(positionInStack-1); // remove stack from where we got it
            }
        }

        // Get all crates on top of each stack
        String topCrates = "";
        for (int i=0; i<9; i++) {
            int topCratePosition = crateStack[i].size()-1;
            topCrates+= crateStack[i].get(topCratePosition);
        }

        crateStack = copyOfcrateStack;

        // Part 2: Rearrange stacks, moving multiple crates at once
        for (int i=0; i<501; i++) {     // Loop through instructions
            List<String> crateListToMove = new ArrayList<String>();
            for (int j=0; j<numOfCratesToMove[i]; j++) {    // Loop through number of crates to move
                int indexInStack = crateStack[startPosition[i]-1].size()-1;    // index for crate on top of stack
                String crateToMove = crateStack[startPosition[i]-1].get(indexInStack);     // get crate
                crateListToMove.add(crateToMove);
                crateStack[startPosition[i]-1].remove(indexInStack); // remove stack from where we got it
            }
            int loopy = crateListToMove.size();
            for (int k=0; k<loopy; k++) {
                int indexInStack = crateListToMove.size()-1;
                String crateToMove = crateListToMove.get(indexInStack);
                crateStack[endPosition[i]-1].add(crateToMove);    // move to new stack
                crateListToMove.remove(indexInStack); // remove stack from where we got it
            }
        }

        // Get all crates on top of each stack
        String topCratesP2 = "";
        for (int i=0; i<9; i++) {
            int topCratePosition = crateStack[i].size()-1;
            topCratesP2+= crateStack[i].get(topCratePosition);
        }

        // Print for testing along the way
//        System.out.println("\nOutput for testing:");
//        System.out.println(crateInput[7][9]);
//        System.out.println(endPosition[500]);
//        System.out.println(crateStack[3].get(1));
//        System.out.println(crateStack[3].size());

        // Print solution:
        System.out.println("Solution Part 1: " + topCrates);
        System.out.println("Solution Part 2: " + topCratesP2);
    }
}
