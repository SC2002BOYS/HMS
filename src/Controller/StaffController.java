package Controller;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.util.List;


public class StaffController implements IStaffController {
    // Method to view hospital staff
    // need to chnage logic, this is just a placeholder
    private static final String CSV_FILE_PATH = "External Data/Staff_List.csv";
    private static final String USERS_CSV_FILE_PATH = "External Data/Users.csv";

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
        String staffID;

        while (true) {
            System.out.println("Enter the staff ID:");
            staffID = sc.next();

            boolean existsInStaffList = checkIfExistsInFile(CSV_FILE_PATH, staffID);
            boolean existsInUsers = checkIfExistsInFile(USERS_CSV_FILE_PATH, staffID);

            if (existsInStaffList || existsInUsers) {
                System.out.println("Staff ID already taken. Please enter another staff ID.");
            } else {
                break;
            }
        }

        System.out.println("Enter the staff role:");
        String role = sc.next();

        System.out.println("Enter the gender:");
        String gender = sc.next();

        System.out.println("Enter the staff password:");
        String password = sc.next();

        System.out.println("Enter the age:");
        String age = sc.next();

        String staffCsvLine = staffID + "," + password + "," + role + "," + gender + "," + age;
        String userCsvLine = staffID + "," + password + "," + role + "," + gender + "," + age;

        // Write the CSV line to the Staff_List.csv file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE_PATH, true))) {
            writer.write(staffCsvLine);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the Staff_List.csv file: " + e.getMessage());
        }

               // Write the CSV line to the Users.csv file
               try (BufferedWriter writer = new BufferedWriter(new FileWriter(USERS_CSV_FILE_PATH, true))) {
                writer.write(userCsvLine);
                writer.newLine();
            } catch (IOException e) {
                System.err.println("An error occurred while writing to the Users.csv file: " + e.getMessage());
            }
    
            System.out.println("Staff member added: " + staffCsvLine);
        
    
    }

    // Method to update a staff member
    // need to chnage logic, this is just a placeholder
    public void updateStaffMember() {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the staff ID to update:");
        String staffID = sc.next();

        System.out.println("Enter the current staff password:");
        String currentPassword = sc.next();

        boolean existsInStaffList = checkIfExistsInFile(CSV_FILE_PATH, staffID, currentPassword, 5);
        boolean existsInUsers = checkIfExistsInFile(USERS_CSV_FILE_PATH, staffID, currentPassword, 5);

        if (existsInStaffList && existsInUsers) {
            // Ask for new details
            System.out.println("Enter the new staff role:");
            String role = sc.next();

            System.out.println("Enter the new staff password:");
            String newPassword = sc.next();

            // Update the files with the new role and password, keeping gender and age the same
            updateLineInFile(CSV_FILE_PATH, staffID, currentPassword, role, newPassword, 5);
            updateLineInFile(USERS_CSV_FILE_PATH, staffID, currentPassword, role, newPassword, 5);

            System.out.println("Staff member updated.");
        } else {
            System.out.println("Staff member not found with ID and password: " + staffID);
        }
       
    }

    public void removeStaffMember() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the staff ID to remove:");
        String staffID = sc.next();

        System.out.println("Enter the current staff password:");
        String currentPassword = sc.next();

        boolean existsInStaffList = checkIfExistsInFile(CSV_FILE_PATH, staffID, currentPassword, 5);
        boolean existsInUsers = checkIfExistsInFile(USERS_CSV_FILE_PATH, staffID, currentPassword, 5);

        if (existsInStaffList && existsInUsers) {
            // Remove the lines from both files
            removeLineFromFile(CSV_FILE_PATH, staffID, currentPassword, 5);
            removeLineFromFile(USERS_CSV_FILE_PATH, staffID, currentPassword, 5);

            System.out.println("Staff member removed.");
        } else {
            System.out.println("Staff member not found with ID and password: " + staffID);
        }
    }

    private void removeLineFromFile(String filePath, String staffID, String password, int expectedColumns) {
        List<String> lines = new ArrayList<>();
        boolean removed = false;

        // Read the existing CSV file
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                if (!(values.length >= expectedColumns && values[0].equals(staffID) && values[1].equals(password))) {
                    lines.add(line);
                } else {
                    removed = true;
                    System.out.println("Removed line: " + line); // Debugging statement
                }
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
                    if (removed) {
                        System.out.println("File updated successfully: " + filePath);
                    } else {
                        System.out.println("No updates made to the file: " + filePath);
                    }
                } catch (IOException e) {
                    System.err.println("An error occurred while writing to the CSV file: " + e.getMessage());
                }
            }







 

    private boolean checkIfExistsInFile(String filePath, String staffID, String password, int expectedColumns) {
        boolean found = false;

        // Read the existing CSV file
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length >= expectedColumns && values[0].equals(staffID) && values[1].equals(password)) {
                    found = true;
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading the CSV file: " + e.getMessage());
        }

        return found;
    }

    private void updateLineInFile(String filePath, String staffID, String currentPassword, String role, String newPassword, int expectedColumns) {
        List<String> lines = new ArrayList<>();
        boolean updated = false;

        // Read the existing CSV file
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length >= expectedColumns && values[0].equals(staffID) && values[1].equals(currentPassword)) {
                    // Update the staff member's role and password, keeping gender and age the same
                    if (expectedColumns == 5) {
                        line = staffID + "," + newPassword + "," + role + "," + values[3] + "," + values[4];
                    } else if (expectedColumns == 6) {
                        line = staffID + "," + newPassword + "," + role + "," + values[3] + "," + values[4] + "," + values[5];
                    }
                    updated = true;

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
            if (updated) {
                System.out.println("updated successfully");
            } else {
                System.out.println("No updates made to the file: " + filePath);
            }
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the CSV file: " + e.getMessage());
        }
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




    


    
              
}




    


    

