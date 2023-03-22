package pascalTriangle;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {

    public static int[] nextPascalLine(int[] previousLine) {
        int[] newLine = new int[previousLine.length + 1];
        newLine[0] = 1;
        newLine[previousLine.length] = 1;
        for (int i = 1; i < Math.ceil(newLine.length / 2.0); i++) {
            newLine[i] = previousLine[i - 1] + previousLine[i];
            newLine[newLine.length - i - 1] = newLine[i];
        }
        return newLine;
    }

    public static int readIntFromConsole(String info) {
        int number = 0;
        boolean ifContinue = true;
        Scanner scanner = new Scanner(System.in);

        while (ifContinue) { //zczytywanie ilosci z konsoli
            try {
                System.out.println(info);
                number = Integer.parseInt(scanner.nextLine());
                ifContinue = false;
                if (number <= 0) { //jezeli numer mial byc dodatni to sprawdzamy czy jest dodatni
                    System.out.println("Please enter a positive value!");
                    ifContinue = true;
                }

            } catch (NoSuchElementException | NumberFormatException e) {
                System.out.println("Please enter a number!");
                ifContinue = true;
            }
        }
        return number;
    }

    public static void main(String[] args) {


        int amount = readIntFromConsole("Enter desired amount of new lines: ");
        int[] currentLine = {1};

        System.out.println(Arrays.toString(currentLine));
        for (int i = 0; i < amount; i++) {
            currentLine = nextPascalLine(currentLine);
            System.out.println(Arrays.toString(currentLine));
        }
    }

}
