package edu.guilford;

import java.util.Random;
import java.util.Scanner;

public class User {
    String firstName;
    String lastName;
    String email;
    String password;
    String encryptedPassword;
    String decryptedPassword;

    // Constructor
    public User(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    // Constructor
    public User(String firstName, String lastName, String email, String password, String encryptedPassword,
            String decryptedPassword) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.encryptedPassword = encryptedPassword;
        this.decryptedPassword = decryptedPassword;
    }

    // toString method
    @Override
    public String toString() {
        return "User Information" + "\n" + "First Name: " + firstName + "\n" + "Last Name: " + lastName
                + "\n" + "Email: " + email + "\n" + "Password: " + password + "\n" + "Encrypted Password: " +
                encryptedPassword + "\n" + "Decrypted Password: " + decryptedPassword + "\n";
    }

    // Getters and Setters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public String getDecryptedPassword() {
        return decryptedPassword;
    }

    public void setDecryptedPassword(String decryptedPassword) {
        this.decryptedPassword = decryptedPassword;
    }

    // Method that generates a password
    public String generatePassword() {

        String userPassword = "";

        // Create a scanner object to read user input
        Scanner scan = new Scanner(System.in);

        // Ask for length of the password (must be more than 14 characters but less than
        // 20)
        System.out.println("How long would you like your password to be? (Must be between 14 and 20 characters)");
        int passwordLength = scan.nextInt();
        // If password length does not meet the requirements, ask again
        if (passwordLength < 14 || passwordLength > 20) {
            System.out.println("Please enter a number between 14 and 20.");
            passwordLength = scan.nextInt();
        }
        scan.nextLine();

        // Ask for their favorite fictional character
        System.out.println(
                "What is the first and last name of your favorite fictional character? Char. name min: 7 char..");
        String favoriteCharacter = scan.nextLine();

        // If there any spaces in the name, seperate the name into strings and retrieve
        // both the first and last name.
        String favoriteCharacterFN;
        String favoriteCharacterLN = "";
        if (favoriteCharacter.contains(" ")) {
            String[] splitName = favoriteCharacter.split(" ");
            favoriteCharacterFN = splitName[0];
            favoriteCharacterLN = splitName[1];
        } else {
            favoriteCharacterFN = favoriteCharacter;
            favoriteCharacterLN = "";
        }
        // If the character name is less than 7 characters, ask them to think of another
        // name.
        while (favoriteCharacter.length() < 7) {
            System.out.println("Please think of a name that is at least 7 characters long.");
            favoriteCharacter = scan.nextLine();
        }

        // Ask for the name of their favorite non-living creature
        System.out.println("What is the name of your favorite non-living creature? Char. name min: 5 char.");
        String favoriteCreature = scan.nextLine();
        // If there any spaces in the creature name, seperate the name into two strings
        // and retrieve the first name.
        if (favoriteCreature.contains(" ")) {
            String[] splitName1 = favoriteCreature.split(" ");
            favoriteCreature = splitName1[0];
        }
        // If the creature name is less than 5 characters, ask them to think of another
        // name.
        while (favoriteCreature.length() < 5) {
            System.out.println("Please think of a name that is at least 5 characters long.");
            favoriteCreature = scan.nextLine();
        }

        // Ask for their favorite number (must be an integer)
        System.out.println("What is your favorite number? Please type an integer only.");
        int favoriteNumber = scan.nextInt();
        String favoriteNumberString = Integer.toString(favoriteNumber);

        // Ask them to think of a random word of more than or equal to 7 characters.
        System.out.println("Think of a random word that is at least 7 characters long. [No Spaces]");
        String randomWord = scan.next();
        // if the random word is less than 7 characters, ask them to think of another
        // word.
        while (randomWord.length() < 7) {
            System.out.println("Please think of a word that is at least 7 characters long.");
            randomWord = scan.next();
        }

        Random random = new Random();
        int randomIndex1 = random.nextInt(favoriteCharacterLN.length());

        // Create a "rough password" using the following parts:
        // favorite character's first name, creature, and a random substring from the
        // favorite character's last name.
        userPassword = favoriteCharacterFN + favoriteCreature + favoriteCharacterLN.substring(0, randomIndex1);

        // Ensure userPassword is the length of the passwordLength. If not, add parts
        // from the random word to end of password.
        if (userPassword.length() < passwordLength) {
            int minusSum = passwordLength - userPassword.length();
            userPassword += randomWord.substring(0, minusSum); // here should be adding characters from random word.
        }

        System.out.println(userPassword);
        // For the fifth, eighth, tenth, and last character, uppercase the characters in
        // userPassword.
        userPassword = userPassword.substring(0, 4) +
                userPassword.substring(4, 5).toUpperCase() +
                userPassword.substring(5, 7) + userPassword.substring(7, 8).toUpperCase()
                + userPassword.substring(8, 9).toUpperCase() + userPassword.substring(9, userPassword.length() - 1)
                + userPassword.substring(userPassword.length() - 1, userPassword.length()).toUpperCase();

        // Create a variable containing a random number between 0-9 and a random index
        // from the user's favorite number.
        int randomNum = random.nextInt(9);
        int randomIndex2 = random.nextInt(favoriteNumberString.length());

        // For the second and ninth characters, replace the characters with an ! and *.
        // For the sixth and eleventh characters, add a random character from user's
        // favorite number
        // and a random number between 0-9 respectively.
        userPassword = userPassword.substring(0, 1) + "!" + userPassword.substring(2, 5) +
                randomNum + userPassword.substring(6, 8) + "*" +
                userPassword.substring(9, 10) + favoriteNumberString.charAt(randomIndex2) +
                userPassword.substring(11, userPassword.length());

        System.out.println(userPassword);
        // If userPassword is longer than passwordLength, remove characters from the end
        // of the password.
        if (userPassword.length() > passwordLength) {
            int minusSum = userPassword.length() - passwordLength;
            userPassword = userPassword.substring(0, userPassword.length() - minusSum);
        }

        // Set the user's password to the generated password.
        password = userPassword;
        System.out.println("Your password is: " + userPassword);
        return userPassword;
    }

}
