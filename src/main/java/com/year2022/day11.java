package com.year2022;

import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

public class day11 {
    public static void main(String[] args) throws Exception {
        // input
        final InputStream source = day11.class.getResourceAsStream("/day11.txt");
        int numOfRounds = 10000;
        int part = 2;

        // get input
        ArrayList<String[]> monkeyNotes = getInput(source);

        // create all monkeys
        ArrayList<Monkey> monkeyList = new ArrayList<>();
        for (int i=0; i<monkeyNotes.size(); i++) {
            Monkey monkey = new Monkey(monkeyNotes.get(i));
            monkeyList.add(monkey);
        }

        System.out.println(monkeyList.get(0).hasConstFactor);
        System.out.println(monkeyList.get(1).hasConstFactor);
        System.out.println(monkeyList.get(2).hasConstFactor);
        System.out.println(monkeyList.get(3).hasConstFactor);
        System.out.println(monkeyList.get(0).opIsAdd);
        System.out.println(monkeyList.get(1).opIsAdd);
        System.out.println(monkeyList.get(2).opIsAdd);
        System.out.println(monkeyList.get(3).opIsAdd);

        //Find LCF
        int lcf = findLcf(monkeyList);
        System.out.println(lcf);

        // Flow per round:

        for (int k=0; k<numOfRounds; k++) {
 //           System.out.println("Round " + (k+1));
            for (int i=0; i<monkeyList.size(); i++) {       // loop through all monkeys
                Monkey monkey = monkeyList.get(i);
                int counter = monkey.itemsInspected;
                while (!monkey.itemList.isEmpty()) {        // check if any item in list, loop through all
                    long item = monkey.itemList.get(0);      // get item [0] in list
                    item = doOperation(monkey, item);               // do operation (worry level item)
     //               System.out.println("worry level start: "+ item);
                    if (part == 1) {
                        item = (item / 3);                // divide by 3, round down (worry level item)
                        //              System.out.println("divided by 3: " + item);
                    } else {
                        if (item>lcf) {
                            item = item % lcf;
                            System.out.println("part 2 stuff: " + item);
                        }
                    }
                    int throwTo = findNextMonkey(monkey, item);     // test worry level, using divisor
                    monkeyList.get(throwTo).addItem(item);       // throw item to other monkey:  // add to other monkey's list
                    monkeyList.get(i).removeItem(0);     // remove from current monkey's list
                    counter++;
                }
                monkey.setItemsInspected(counter);
            }
        }

        // get monkey business Part 1
        long monkeyBusiness = getMonkeyBusiness(monkeyList);

        System.out.println(monkeyBusiness);







    }

    public static ArrayList<String[]> getInput(InputStream source) {
        ArrayList<String[]> monkeyNotes = new ArrayList<>();

        try (Scanner input = new Scanner(source)) {
            while (input.hasNextLine()) {
                Boolean isEmpty = false;
                String[] notes = new String[6];
                for (int i = 0; !isEmpty && input.hasNextLine(); i++) {
                    String string = input.nextLine();
                    if (string.isEmpty()) {
                        isEmpty = true;
                    } else {
                        notes[i] = string;
                    }
                }
                monkeyNotes.add(notes);
            }
        }

        return monkeyNotes;
    }

    public static long doOperation(Monkey monkey, long item) {
        long worryLevel = item;
        Boolean hasConstFactor = monkey.hasConstFactor;
        int factor = monkey.factor;
        Boolean opIsAdd = monkey.opIsAdd;

        // Summation
        if (opIsAdd) {
            worryLevel+= factor;
        } else if (hasConstFactor) {
            worryLevel*= factor;
        } else {
            worryLevel*= worryLevel;
        }

        return worryLevel;
    }

    public static int findNextMonkey(Monkey monkey, long item) {
        long worryLevel = item;
        int divisor = monkey.divisor;
        int trueThrow = monkey.trueThrow;
        int falseThrow = monkey.falseThrow;
        int index = 0;
        if ((worryLevel % divisor) == 0) {
            index = trueThrow;
        } else {
            index = falseThrow;
        }

        return index;
    }

    public static long getMonkeyBusiness(ArrayList<Monkey> monkeyList) {
        // Get count inspected items from all monkeys
        int[] inspectedItemsList = new int[monkeyList.size()];
        for (int i=0; i<monkeyList.size(); i++) {
            inspectedItemsList[i] = monkeyList.get(i).itemsInspected;
        }

        // Get two highest counts
        Arrays.sort(inspectedItemsList);
        long monkeyBusiness = inspectedItemsList[inspectedItemsList.length-1] * inspectedItemsList[inspectedItemsList.length-2];

        System.out.println("Monkey Business: " + monkeyBusiness);
        System.out.println("This one: " + inspectedItemsList[inspectedItemsList.length-1]);
        System.out.println("times this one: "+ inspectedItemsList[inspectedItemsList.length-2]);
        System.out.println(inspectedItemsList[inspectedItemsList.length-3]);
        System.out.println(inspectedItemsList[inspectedItemsList.length-4]);

        return monkeyBusiness;
    }

    public static int findLcf(ArrayList<Monkey> monkeyList) {
        int lcf = 1;

        for (int i=0; i<monkeyList.size(); i++) {
            lcf*= monkeyList.get(i).divisor;
        }
        return lcf;
    }

    public static long keepWorryLevelDown(long item, int lcf) {
        long worryLevel = item;

        return worryLevel;
    }

}
