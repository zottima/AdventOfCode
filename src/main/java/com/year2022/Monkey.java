package com.year2022;

import java.util.ArrayList;
import java.util.Scanner;

public class Monkey {
    int index;
    ArrayList<Long> itemList = new ArrayList<>();
    Boolean opIsAdd;
    Boolean opIsMultiply;
    Boolean hasConstFactor;
    int factor;
    int divisor;
    int trueThrow;
    int falseThrow;
    int itemsInspected;

    public Monkey () {
    }

    public Monkey (String[] monkeyNotes) {
        this.index = getIndex(monkeyNotes[0]);
        this.itemList = getItemList(monkeyNotes[1]);
        setOperator(getOperator(monkeyNotes[2]));
        setFactor(getFactor(monkeyNotes[2]));
        this.divisor = getDivisor(monkeyNotes[3]);
        setTrueThrow(getTrueThrow(monkeyNotes[4]));
        setFalseThrow(getFalseThrow(monkeyNotes[5]));
        this.itemsInspected = 0;
    }

    private static int getIndex(String notes) {
        String string = notes.replaceAll("[^0-9]", "").trim(); // remove "Monkey" and colon
        int index = Integer.parseInt(string); // get monkey index
        return index;
    }

    private static ArrayList<Long> getItemList(String notes) {
        String string = notes.replaceAll("Starting items:", "").trim();
        string = string.replaceAll(",", "");
        Scanner in = new Scanner(string);
        ArrayList<Long> itemList = new ArrayList<>();
        for (int i=0; in.hasNext(); i++) {
            itemList.add(in.nextLong());
        }
        return itemList;
    }

    private static char getOperator(String notes) {
        String string = notes.replaceAll("Operation: new = old", "").trim();
        Scanner in = new Scanner(string);
        char operator = in.next().charAt(0);
        return operator;
    }

    private static int getFactor(String notes) {
        String string = notes.replaceAll("Operation: new = old", "").trim();
        Scanner in = new Scanner(string);
        in.next();
        String factorString = in.next();
        int factor = 0;
        if (!factorString.contains("old")) {
            factor = Integer.parseInt(factorString);
        }
        System.out.println("Factor: " + factor);
        return factor;
    }

    private static int getDivisor(String notes) {
        String string = notes.replaceAll("Test: divisible by ","").trim();
        int divisor = Integer.parseInt(string);
        return divisor;
    }

    private static int getTrueThrow(String notes) {
        String string =  notes.replaceAll("If true: throw to monkey", "").trim();
        int trueThrow = Integer.parseInt(string);
        return trueThrow;
    }

    private static int getFalseThrow(String notes) {
        String string =  notes.replaceAll("If false: throw to monkey", "").trim();
        int falseThrow = Integer.parseInt(string);
        return falseThrow;
    }

    public Monkey(int index) {
        this.index = index;
    }

    public void addItem(long item) {
        this.itemList.add(item);
    }

    public void removeItem(int index) {
        this.itemList.remove(index);
    }

    public void setOperator(char operator) {
        switch (operator) {
            case '+':
                this.opIsAdd = true;
                this.opIsMultiply = false;
                break;
            case '*':
                this.opIsAdd = false;
                this.opIsMultiply = true;
                break;
        }
    }

    public void setFactor(int factor) {
        if (factor == 0) {                  // "old"
            this.hasConstFactor = false;
        } else {
            this.hasConstFactor = true;
            this.factor = factor;
        }
    }

    public void setDivisor(int divisor) {
        this.divisor = divisor;
    }

    public void setTrueThrow(int index) {
        this.trueThrow = index;
    }

    public void setFalseThrow(int index) {
        this.falseThrow = index;
    }

    public void setItemsInspected(int counter) {
        this.itemsInspected = counter;
    }

}
