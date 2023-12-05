package com.year2023;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

/*
    Solution approach:
    (Part 1)
    - get input
        - save initial seed position
        - extract one mapping instruction line at a time
        - check if seed positions need to be remapped
            - if current seed position is within range source range start and range length
        - ...
 */
public class day05 {
    public static void main(String[] args) throws Exception {
        System.out.println("Advent of Code – Day 5");
        String inputFile = "/2023/day05.txt";
        int n = 189;        // number of lines in input file
        int seeds = 20;

        // possibility to switch to example input
        System.out.println("Do you want to run on example input? (y/n)");

        Scanner sc = new Scanner(System.in);
        char ynInput = sc.nextLine().trim().toLowerCase().charAt(0);

        if (ynInput == 'y') {
            inputFile = "/2023/day05e.txt";
            n = 33;
            seeds = 4;
        }

        // get puzzle input
        final InputStream source = com.year2023.day05.class.getResourceAsStream(inputFile);
        String[] almanacInput = new String[n];
        try (Scanner input = new Scanner(source)) {
            for (int i = 0; input.hasNextLine(); i++) {
                almanacInput[i] = input.nextLine();
            }
        }

        // get initial seed locations
        long[] seedLoc = getInitialSeed(seeds, almanacInput[0]);

        // make copy of original location to save to during sets of instructions
        long[] savedLoc = seedLoc.clone();

        // run through instructions and remap locations
        for (int i=1; i <(n-1); i++) {
            if (Objects.equals(almanacInput[i], "")) {        // if empty line, hop over this and next line to get to instructions
                i+= 2;
                seedLoc = savedLoc.clone();
            }
            long[] remappedLoc = remapLocation(seedLoc, almanacInput[i]);
            savedLoc = saveNewLocation(seedLoc, remappedLoc, savedLoc);
        }

        seedLoc = savedLoc.clone();

        // Find smallest location
        Arrays.sort(seedLoc);
        long smallestLoc = seedLoc[0];

        // print result
        System.out.println("The solution for part 1 is: " + smallestLoc);

        // Test output
       // System.out.println(seedLoc[3]);
    }

    public static long[] getInitialSeed(int numSeeds, String input) {
        long[] initialLoc = new long[numSeeds];

        Scanner in = new Scanner(input);
        in.next();      // ignore "Seeds:" bit
        for (int i=0; in.hasNext(); i++) {
            initialLoc[i] = Long.parseLong(in.next());
        }
        return initialLoc;
    }

    public static long[] remapLocation(long[] origLoc, String input) {
        /*
        input example:
        50 98 2
        52 50 48
        destination range start, source range start, range length
        if seed location is => source and < (source+range) then
        new location = current location + (destination - source)
        */
        long[] location = origLoc.clone();
        int seedNum = location.length;

        Scanner in = new Scanner(input);
        long destination = Long.parseLong(in.next());
        long source = Long.parseLong(in.next());
        long range = Long.parseLong(in.next());

        for (int i=0; i<seedNum; i++) {
            if ((location[i] >= source) && (location[i] < (source+range))) {  // ex 1: >= 98 && < 100 -> 98,99
                location[i] = location[i] + destination - source;    // ex 1: new = current + (50-98) = current - 48
                                                        // ex 2: new = current + (52-50) = current + 2
            }
        }

        return location;
    }

    public static long[] saveNewLocation(long[] originalLoc, long[] remappedLoc, long[] savedLoc) {
        int seedNum = originalLoc.length;

        for (int i=0; i<seedNum; i++) {
            if (remappedLoc[i] != originalLoc[i]) {
                savedLoc[i] = remappedLoc[i];
            }
        }
        return savedLoc;
    }
}
