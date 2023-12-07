package com.year2022;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class day09 {
    public static void main(String[] args) throws Exception {
        final InputStream source = day09.class.getResourceAsStream("/2022/day09.txt");

        // Read input and store in List
        ArrayList<String> inputList = getInput(source);

        // Get grid size
        int[] gridSize = getGridSize(inputList);
        int gridX = gridSize[0];
        int gridY = gridSize[1];

        // get starting position
        int[] startingPosition = getStartPosition(inputList);
        int[] positionHead = startingPosition;
        int[] positionTail = startingPosition;

        // Instantiate grid to mark visited fields
        boolean[][] isVisited = new boolean[gridX][gridY];
        isVisited[startingPosition[0]][startingPosition[1]] = true;     // starting field

        // loop through path
        for (int i =0; i<inputList.size(); i++) {
            int steps = getSteps(inputList.get(i));
            String direction = getDirection(inputList.get(i));

            for (int j = 0; j<steps; j++){
                int[] oldPositionHead = positionHead;
                positionHead = moveHead(positionHead,direction);
                if (!checkIfTouching(positionHead,positionTail)) {
                    positionTail = oldPositionHead;
                    if (!isVisited[positionTail[0]][positionTail[1]]) {
                        isVisited[positionTail[0]][positionTail[1]] = true;
                    }
                }
            }
        }

        // count visited fields
        int numOfvisitedFields = countVisitedFields(isVisited);

        // Print for testing:
        System.out.println("\nPrints for testing along the way:");
        System.out.println("Gridsize: x: " + gridSize[0] + " y: " + gridSize[1]);
        System.out.println("Fields visited at least once: " + numOfvisitedFields);
        System.out.println(isVisited.length);
        System.out.println(isVisited[0].length);

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

    public static int[][] getGrid(ArrayList<String> input) {
        int posX = 0;
        int posY = 0;
        int maxX = 0;
        int maxY = 0;
        int minX = 0;
        int minY = 0;

        for (int i=0; i<input.size(); i++) {
            int steps = getSteps(input.get(i));
            if (input.get(i).contains("R")) {
                posX+= steps;
                if (posX > maxX) {
                    maxX = posX;
                }
            } else if (input.get(i).contains("L")) {
                posX-= steps;
                if (posX < minX) {
                    minX = posX;
                }
            } else if (input.get(i).contains("U")) {
                posY+= steps;
                if (posY > maxY) {
                    maxY = posY;
                }
            } else if (input.get(i).contains("D")) {
                posY-= steps;
                if (posY < minY) {
                    minY = posY;
                }
            }
        }
        int gridX = maxX - minX +1;
        int gridY = maxY - minY +1;
        int startX = Math.abs(minX);
        int startY = Math.abs(minY);
        int[][] gridSpecs = {{gridX,gridY},{startX,startY}};
        System.out.println("minX: " + minX + ", minY: " + minY);
        System.out.println("maxX: " + maxX + ", maxY: " + maxY);
        System.out.println("gridX: " + gridX + " gridY: " + gridY);
        return(gridSpecs);
    }

    public static int[] getGridSize(ArrayList<String> input) {
        int[][] gridSpecs = getGrid(input);
        int gridX = gridSpecs[0][0];
        int gridY = gridSpecs[0][1];
        int[] gridSize = {gridX,gridY};
        return gridSize;
    }
    public static int[] getStartPosition(ArrayList<String> input) {
        int[][] gridSpecs = getGrid(input);
        int startX = gridSpecs[1][0];
        int startY = gridSpecs[1][1];
        int[] startPos = {startX,startY};
        return startPos;
    }

    public static int getSteps(String string) {
        Scanner in = new Scanner(string).useDelimiter("[^0-9]+");
        int steps = in.nextInt();
        return steps;
    }

    public static String getDirection(String string) {
        Scanner in = new Scanner(string).useDelimiter("[^D-U]+");
        String direction = in.next();
        return direction;
    }

    public static int[] moveHead(int[] currentPos, String direction) {
        int positionX = currentPos[0];
        int positionY = currentPos[1];

        if (direction.contains("R")) {         // update position depending on moving direction
            positionX++;
        } else if (direction.contains("L")) {
            positionX--;
        } else if (direction.contains("U")) {
            positionY++;
        } else if (direction.contains("D")) {
            positionY--;
        }

        int[] newPosition = {positionX,positionY};
        return newPosition;
    }

    public static Boolean checkIfTouching(int[] posHead, int[] posTail) {
        Boolean isTouching = true;

        if ((Math.abs(posHead[0]-posTail[0]) > 1) || (Math.abs(posHead[1]-posTail[1]) > 1))  {
            isTouching = false;
        }

        return isTouching;
    }

    public static int countVisitedFields(boolean[][] isVisited) {
        int counter = 0;
        for (int i=0; i<isVisited.length; i++) {
            for (int j=0; j<isVisited[0].length; j++) {
                if (isVisited[i][j] == true) {
                    counter++;
                }
            }
        }
        return counter;
    }
}

