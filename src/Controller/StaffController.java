package Controller;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.util.List;


public class StaffController implements IStaffController {
    // Method to view hospital staff
    // need to chnage logic, this is just a placeholder
    private static final String CSV_FILE_PATH = "HMS/External Data/Staff_List.csv";
    private static final String USERS_CSV_FILE_PATH = "HMS/External Data/Users.csv";

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
        System.out.println("Enter the staff name:");
        String name = sc.next();

        System.out.println("Enter the staff role:");
        String role = sc.next();

        System.out.println("Enter the gender:");
        String gender = sc.next();

        System.out.println("Enter the staff password:");
        String password = sc.next();

        System.out.println("Enter the age:");
        String age = sc.next();

        String StaffcsvLine = name + "," + role + "," + gender + "," + password + "," + age;
        String UsercsvLine = name + "," + password + "," + role + "," + gender + "," + age;

        // Write the CSV line to the STAFF_List.csv file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE_PATH, true))) {
            writer.write(StaffcsvLine);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the STAFF_List.csv file: " + e.getMessage());
        }

        // Write the CSV line to the Users.csv file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USERS_CSV_FILE_PATH, true))) {
            writer.write(UsercsvLine);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the Users.csv file: " + e.getMessage());
        }

        System.out.println("Staff member added: " + StaffcsvLine);
    }

    // Method to update a staff member
    // need to chnage logic, this is just a placeholder
    public void updateStaffMember() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the staff ID to update:");
        String staffID = sc.next();

        boolean existsInStaffList = checkIfExistsInFile(CSV_FILE_PATH, staffID);
        boolean existsInUsers = checkIfExistsInFile(USERS_CSV_FILE_PATH, staffID);

        if (existsInStaffList && existsInUsers) {
            // Ask for new details once
            System.out.println("Enter the new staff role:");
            String role = sc.next();

            System.out.println("Enter the new staff password:");
            String password = sc.next();

            System.out.println("Enter the new gender:");
            String gender = sc.next();

            System.out.println("Enter the new age:");
            String age = sc.next();

            updateLineInFile(CSV_FILE_PATH, staffID, role, password, gender, age);
            updateLineInFile(USERS_CSV_FILE_PATH, staffID, role, password, gender, age);

            System.out.println("Staff member updated.");
        } else {
            System.out.println("Staff member not found with ID: " + staffID);
        } 
    }

    // Method to remove a staff member
    // need to chnage logic, this is just a placeholder
    public void removeStaffMember() 
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the staff ID to remove:");
        String staffID = sc.next();

        boolean foundInStaffList = removeLineFromFile(CSV_FILE_PATH, staffID);
        boolean foundInUsers = removeLineFromFile(USERS_CSV_FILE_PATH, staffID);

        if (foundInStaffList && foundInUsers) {
            System.out.println("Staff member removed with ID: " + staffID);
        } else {
            System.out.println("Staff member not found with ID: " + staffID);
        }  
    }

    private boolean removeLineFromFile(String filePath, String staffID) {
            List<String> lines = new ArrayList<>();
            boolean found = false;
    
            // Read the existing CSV file
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
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
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the CSV file: " + e.getMessage());
        }

        return found;
    }

    private boolean checkIfExistsInFile(String filePath, String staffID) {
        boolean found = false;

        // Read the existing CSV file
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                if (values[0].equals(staffID)) {
                    found = true;
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading the CSV file: " + e.getMessage());
        }

        return found;
    }

    private void updateLineInFile(String filePath, String staffID, String role, String password, String gender, String age) {
        List<String> lines = new ArrayList<>();

        // Read the existing CSV file
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                if (values[0].equals(staffID)) {
                    // Update the staff member's information
                    line = staffID + "," + role + "," + gender + "," + password + "," + age;
                }
                lines.add(line);
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading the CSV file: " + e.getMessage());
        }

        // Write the updated content back to the CSV file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String updatedLine : lines) {
                writer.write(updatedLine);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the CSV file: " + e.getMessage());
        }
    }



}
