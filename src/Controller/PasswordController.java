package Controller;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PasswordController {
    private String userID;
    private String newPassword;
    private static final String USER_FILE = "External Data/Users.csv";

    public PasswordController(String userID, String newPW){
        this.userID = userID;
        this.newPassword = newPW;
        this.updatePassword();
    }

    private void updatePassword() {
        List<String> lines = new ArrayList<>();
        boolean userFound = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(USER_FILE))) {
            String line;

            // Read each line from the CSV file
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                // Check if the userID matches
                if (parts.length >= 2 && parts[0].equals(userID)) {
                    parts[1] = newPassword; // Update the password
                    userFound = true;
                    System.out.println("Password updated for user: " + userID);
                }

                // Rebuild the line and add to the list
                lines.add(String.join(",", parts));
            }

        } catch (IOException e) {
            System.err.println("Error reading the user file: " + e.getMessage());
            return;
        }

        // Save the updated lines back to the CSV file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USER_FILE))) {
            for (String updatedLine : lines) {
                writer.write(updatedLine);
                writer.newLine();
            }

            if (userFound) {
                System.out.println("Password successfully updated in the file.");
            } else {
                System.out.println("UserID not found in the file.");
            }

        } catch (IOException e) {
            System.err.println("Error writing to the user file: " + e.getMessage());
        }
    }
}
