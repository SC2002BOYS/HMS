package Controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class TimeGetter {
    public static LocalDate getTime(){
        Scanner scanner = new Scanner(System.in);

        // Specify the expected date format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate date = null;
        boolean valid = false;
        while(!valid){
            System.out.print("Enter the date (yyyy-MM-dd): ");
            String input = scanner.nextLine();

            try {
                // Parse the input string into a LocalDate
                date = LocalDate.parse(input, formatter);
                valid = true;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please use 'yyyy-MM-dd'.");
            }
        }
        return date;
    }

}
