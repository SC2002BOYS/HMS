package Controller;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.util.List;


public class StaffController {
    // Method to view hospital staff
    // need to chnage logic, this is just a placeholder
    private static final String CSV_FILE_PATH = "External Data/Staff_List.csv";
    public void viewHospitalStaff() {
        System.out.println("Viewing hospital staff...");
        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading the CSV file: " + e.getMessage());
        }
    }

    // Method to add a staff member
    public void addStaffMember() {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter the staff id");
        String staffID = sc.next();

        System.out.println("enter the staff role");
        String role = sc.next();

        System.out.println("enter the staff password");
        String password = sc.next();

        System.out.println("enter the gender");
        String gender=sc.next();  


        //to add to the Staff_Lisr.csv file
        // Convert the Staff object to a CSV line
        String csvLine = staffID + "," + role + "," + gender + "," + password;

        // Write the CSV line to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE_PATH, true))) {
            writer.write(csvLine);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the CSV file: " + e.getMessage());
        }

        System.out.println("Staff member added: " + csvLine);

    }

    // Method to update a staff member
    // need to chnage logic, this is just a placeholder
    public void updateStaffMember() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the staff ID to update:");
        String staffID = sc.next();

        List<String> lines = new ArrayList<>();
        boolean found = false;

         try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                if (values[0].equals(staffID)) {
                    // Update the staff member's information
                    System.out.println("Enter the new staff role:");
                    String role = sc.next();

                    System.out.println("Enter the new staff password:");
                    String password = sc.next();

                    System.out.println("Enter the new gender:");
                    String gender = sc.next();

                    line = staffID + "," + role + "," + gender + "," + password;
                    found = true;
                }
                lines.add(line);
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading the CSV file: " + e.getMessage());
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE_PATH))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the CSV file: " + e.getMessage());
        }

        if (found) {
            System.out.println("Staff member updated.");
        } else {
            System.out.println("Staff member not found with ID: " + staffID);
        }  
    }

    // Method to remove a staff member
    // need to chnage logic, this is just a placeholder
    public void removeStaffMember() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the staff ID to remove:");
        String staffID = sc.next();


        List<String> lines = new ArrayList<>();
        boolean found = false;

        // Read the existing CSV file
        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                if (!values[0].equals(staffID)) {
                    lines.add(line);
                } else {
                    found = true;
                }
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
            System.out.println("Staff member removed with ID: " + staffID);
        } else {
            System.out.println("Staff member not found with ID: " + staffID);
        }
    }
}
