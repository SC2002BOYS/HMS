//Test Case 23: Approve Replenishment Requests
//● Administrator approves a replenishment request from a pharmacist.
//● Verify that the request status changes to "approved," and the medication
//inventory is updated accordingly. 

package Controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReplenishmentController implements IReplenishmentController {

    private static final String CSV_FILE_PATH = "HMS/External Data/ReplenishRequest.csv";

    public boolean printPendingRequests() {
        boolean hasPending = false;
        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length >= 3 && values[2].equalsIgnoreCase("PENDING")) {
                    System.out.println(line);
                    hasPending = true;
                }
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading the CSV file: " + e.getMessage());
        }

        if (!hasPending) {
            System.out.println("No current replenish requests.");
        }
        return hasPending;
    }


    public void approveReplenishmentRequest() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the date of the replenishment request (YYYY-MM-DD):");
        String date = sc.next();

        System.out.println("Enter the medication name:");
        String medicationName = sc.next();

        List<String> lines = new ArrayList<>();
        boolean found = false;

        // Read the existing CSV file
        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                if (values[0].equals(date) && values[1].equals(medicationName) && values[2].equalsIgnoreCase("PENDING")) {
                    line = date + "," + medicationName + ",APPROVED";
                    found = true;
                }
                lines.add(line);
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading the CSV file: " + e.getMessage());
        }

                // Write the updated content back to the CSV file
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE_PATH))) {
                    for (String line : lines) {
                        writer.write(line);
                        writer.newLine();
                    }
                } catch (IOException e) {
                    System.err.println("An error occurred while writing to the CSV file: " + e.getMessage());
                }
        
                if (found) {
                    System.out.println("Replenishment request approved.");
                } else {
                    System.out.println("Replenishment request not found or already approved.");
                }
            }
        
            public static void main(String[] args) {
                ReplenishmentController controller = new ReplenishmentController();
                if (controller.printPendingRequests()) {
                    controller.approveReplenishmentRequest();
                }
            }

    
    
}
