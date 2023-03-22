package shapeBuilder;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class ConsoleReader {
    public static int readIntFromConsole(String info, boolean ifCanBeZero){
        int number = 0;
        boolean ifContiniue = true;
        Scanner scanner = new Scanner(System.in);

        while (ifContiniue) {
            try {
                System.out.println(info);
                number = Integer.parseInt(scanner.nextLine());
                ifContiniue = false;

                if((!ifCanBeZero && number <= 0) || (ifCanBeZero && number < 0)){
                    System.out.println("Please enter a positive value!");
                    ifContiniue = true;
                }


            } catch (NoSuchElementException | NumberFormatException e) {
                System.out.println("Please enter a number!");
                ifContiniue = true;
            }
        }
        return number;
    }
}
