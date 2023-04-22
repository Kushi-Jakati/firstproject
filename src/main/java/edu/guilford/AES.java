package edu.guilford;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AES {
    
    //Creates a new instance of the SecretKeySpec class called secretKey. This variable will be 
    //used to encrypt and decrypt the password with accordance to the encryption alogrithm set.
    private static SecretKeySpec secretKey;

    //Creates an array of bytes for the key.
    private static byte[] key;

    //Sets the key for the encryption. 
    public static void setKey(String myKey) {
        MessageDigest sha = null;
        try {
            //UTF-8 is a character encoding that allows you to represent any character in the Unicode standard.
            //getBytes encodes the string (with the use of UTF-8) into a sequence of bytes and stores it in a byte array.
            key = myKey.getBytes("UTF-8");
            //SHA-1 is an algorithm that generates an almost-unique, fixed size 160-bit (20-byte) hash.
            //Message Digests are secure one-way hash functions 
            //that take arbitrary-sized data and output a fixed-length hash value.
            //MessageDigest is a class that provides applications the functionality of a message digest algorithm, 
            //such as SHA-1 or SHA-256 (in thus case, it is SHA-1). 
            sha = MessageDigest.getInstance("SHA-1");
            //digest() method is called to calculate the message digest of the input and return a byte array.
            key = sha.digest(key);
            //copyOf copies the specified array, truncating or padding with zeros (if necessary) so the 
            //copy has the specified "newLength." In this case, the new length is 16.
            key = Arrays.copyOf(key, 16);
            //AES is an encryption algorithm that uses a 128-bit key.
            //SecretKeySpec is a class that represents a secret (symmetric) key, which in this case is AES
            secretKey = new SecretKeySpec(key, "AES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    //Encrypts the password using AES encryption.
    public static String encrypt(String strToEncrypt, String secret) {
        try {
            //Sets the key for the encryption (AES) according to the setKey method.
            //setKey is called here with the parameter secret, which is the key that is passed in from the
            //main method. 
            setKey(secret);
            //Cipher is a class that provides the functionality of a cryptographic cipher. getInstance is a method that 
            //returns a Cipher object that implements the specified transformation. In this case, the transformation is 
            //AES/ECB/PKCS5Padding. ECB is the Electronic Codebook mode, which is a simple block cipher mode that does not
            //require an initialization vector. PKCS5Padding is a padding scheme that pads the plain text with bytes
            //having value 1 to n, where n is the number of padding bytes added.
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            //Encrypts the password. init is a method that initializes this cipher with a key and a source of randomness.
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        } catch (Exception e) {
            //Any errors will be printed to the console.
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }

    //Decrypts the password using AES encryption.
    public static String decrypt(String strToDecrypt, String secret) {
        try {
            //Same key used in the encryption will need to be used in this method as well in order to decrypt the password.
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            //Decrypts the password. 
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } catch (Exception e) {
            //Again, any errors will be printed to the console.
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }
}
