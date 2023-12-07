package com.year2022;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class day10 {
    public static void main(String[] args) throws Exception {
        final InputStream source = day10.class.getResourceAsStream("/2022/day10.txt");

        // get input
        ArrayList<Integer> signalList = getInput(source);        // 0 for "noop"

        // Instantiate register
        ArrayList<Integer> registerList = new ArrayList<>();
        registerList.add(0);        // index register equals "during cycle X"
        registerList.add(1);        // register starts at 1

        // Fill register
        for (int i=0; i<signalList.size(); i++) {
            registerList.add(registerList.get(registerList.size()-1));
            if (signalList.get(i) != 0) {
                registerList.add(registerList.get(registerList.size()-1)+signalList.get(i));
            }
        }

        // Calculate signal strength
        int cycleStart = 20;
        int cycleInterval = 40;
        int cycleEnd = 220;
        int sumSignalStrength = 0;

        for (int i = cycleStart; i <=cycleEnd; i+= cycleInterval) {
            int signalStrength = i * registerList.get(i);
            sumSignalStrength+= signalStrength;
        }

        System.out.println("Solution Part 1: " + sumSignalStrength);
    }

    public static ArrayList<Integer> getInput(InputStream source) {
        ArrayList<Integer> inputList = new ArrayList<>();
        try(Scanner input = new Scanner(source)) {
            while (input.hasNextLine()) {
                if (input.hasNext("noop")) {
                    inputList.add(0);
                    input.next();
                } else {
                    input.next();
                    inputList.add(input.nextInt());
                }
            }
        }
        return (inputList);
    }

}
