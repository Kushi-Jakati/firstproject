package edu.guilford;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class PasswordGenerator 
{
    public static void main( String[] args )
    {

        //         It should instantiate two User objects with information provided by the user.
// It should generate passwords for the two User objects.
// It should display the password, the encrypted version of the password (with the secret key), 
//and the results of the decrypt operation in the AES class to show that the 
//original password can be recovered. The output should be annotated and neatly formatted.

       // Create a scanner object to read user input
         Scanner scan = new Scanner(System.in);
         ArrayList<User> allUsers = new ArrayList<User>();
        // instantiate two User objects with information provided by the user.

        for (int i = 0; i < 2; i++) {
            System.out.println("Enter your first name: ");
            String firstName = scan.nextLine();
            System.out.println("Enter your last name: ");
            String lastName = scan.nextLine();
            System.out.println("Enter your email: ");
            String email = scan.nextLine();
            User testingUser = new User(firstName, lastName, email);
            System.out.println("Let us create a new password for you.");
            String password = testingUser.generatePassword();
            String encryptedPassword = AES.encrypt(password, "AES");
            String decryptedPassword = AES.decrypt(encryptedPassword, "AES");
            User User = new User(firstName, lastName, email, password, encryptedPassword, decryptedPassword); 
            allUsers.add(User);
            System.out.println("Thank you for your service. We shall now proceed to the second user.\n");
        }

            System.out.println("Here is the information for all users in the system right now."); 
            String allInfo;
            for (int i = 0; i < allUsers.size(); i++) {
                allInfo = allUsers.get(i).toString();
                System.out.println(allInfo);
            }
    
    }
}
