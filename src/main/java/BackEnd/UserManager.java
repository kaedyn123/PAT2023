package BackEnd;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class UserManager {

    public static boolean validateUser(String username, String password, String filePath) throws FileNotFoundException {
        Scanner scFile = new Scanner(new File(filePath));
        while (scFile.hasNext()) {
            String line = scFile.nextLine();
            String[] userFields = line.split("#");
            if (userFields.length >= 4) {
                if (username.equalsIgnoreCase(userFields[1]) && password.equals(userFields[3])) {
                    scFile.close();
                    return true;
                }
            }
        }
        scFile.close();
        return false;
    }

    // Method to add a user using PrintWriter
    public static boolean addUser(String fullName, String username, String phoneNumber, String password, String confirmPassword, String filePath) throws FileNotFoundException {
        // Check if any field is empty
        if (fullName.isEmpty() || username.isEmpty() || phoneNumber.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            return false;
        }

        // Check if passwords match
        if (!password.equals(confirmPassword)) {
            return false;
        }

        // Add user to file
        PrintWriter pw = new PrintWriter(new FileOutputStream(new File(filePath), true)); // Open file in append mode
        String userData = "\n" + fullName + "#" + username + "#" + phoneNumber + "#" + password; // Added \n at the beginning
        pw.write(userData);
        pw.close();

        return true;
    }

}
