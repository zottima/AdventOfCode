import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

public class AoC2022 {
    public static void main(String[] args) throws Exception {
        dayOneP1();
    }

    public static void dayOneP1() {
        final InputStream source = AoC2022.class.getResourceAsStream("day01.txt");
        Scanner input = new Scanner(source);
        int[] calories = new int[2000];
        int elves = 0;

        // Sum up calories per Elf
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
        input.close();

        // Find Elf carrying the most calories

        Arrays.sort(calories);
        int maxCalories = calories[calories.length - 1];
        System.out.println(maxCalories);

    }
}