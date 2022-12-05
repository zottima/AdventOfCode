import java.io.InputStream;
import java.util.*;

public class AoC2022 {
    public static void main(String[] args) throws Exception {
        printMainTitle();
        printTitleDay(1);
        dayOne();
        printTitleDay(2);
        dayTwo();
        printTitleDay(3);
        dayThree();
        printTitleDay(4);
        dayFour();
        printTitleDay(5);
        dayFive();
    }

    public static void dayFive() {
        final  InputStream source = AoC2022.class.getResourceAsStream("day05.txt");

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

        for (int i = 0; i < 9; i++) {
            crateStack[i] = new ArrayList<String>();
        }

        int stackIndex = 0;

        for (int i=1; i<35; i+=4) {
            Boolean reachedEndofStack = false;
            for (int j=7; (j>=0) && !reachedEndofStack; j--) {
                if (Character.isUpperCase(crateInput[j][i])) {
                    String string = new String();
                    crateStack[stackIndex].add(string.valueOf(crateInput[j][i]));
                } else {
                    reachedEndofStack = true;
                }
            }
            stackIndex++;
        }

        ArrayList<String>[] copyOfcrateStack = crateStack;

//        // Part 1: Rearrange stacks, moving one crate at a time
//        for (int i=0; i<501; i++) {     // Loop through instructions
//            for (int j=0; j<numOfCratesToMove[i]; j++) {    // How many crates to move
//                int positionInStack = crateStack[startPosition[i]-1].size();    // crate on top of stack
//                String crateToMove = crateStack[startPosition[i]-1].get(positionInStack-1);     // get crate
//                crateStack[endPosition[i]-1].add(crateToMove);    // move to new stack
//                crateStack[startPosition[i]-1].remove(positionInStack-1); // remove stack from where we got it
//            }
//        }
//
//        // Get all crates on top of each stack
//        String topCrates = "";
//        for (int i=0; i<9; i++) {
//            int topCratePosition = crateStack[i].size()-1;
//            topCrates+= crateStack[i].get(topCratePosition);
//        }

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
       // System.out.println("Solution Part 1: " + topCrates);
        System.out.println("Solution Part 2: " + topCratesP2);
    }

    public static void dayFour() {
        final InputStream source = AoC2022.class.getResourceAsStream("day04.txt");

        // Get assignment pairs
        int[][] assignmentPairs = new int[1000][4];
        try(Scanner input = new Scanner(source).useDelimiter(",|-|\\r|\\n")) {
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

    public static void dayThree() {
        final InputStream source = AoC2022.class.getResourceAsStream("day03.txt");
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