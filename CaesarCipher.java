
import java.util.*;

public class CaesarCipher {
    // Map to hold the encryption key
    private Map<Character, Character> encryptionKey = new HashMap<>();
    private Map<Character, Character> decryptionKey = new HashMap<>();

    public static void main(String[] args) {
    	// creating custom/instance of class for further work with method
        CaesarCipher cipher = new CaesarCipher();
        cipher.generateEncryptionKey(4); // Example shift value

        String message = "hello world";
        String encryptedMessage = cipher.encrypt(message);
        System.out.println("Encrypted Message: " + encryptedMessage);

        String decryptedMessage = cipher.decrypt(encryptedMessage);
        System.out.println("Decrypted Message: " + decryptedMessage);

        // Brute-force cracking example 
        cipher.bruteForceDecrypt(message);
    }

    // Method to generate the encryption key
    public void generateEncryptionKey(int shift) {
    	// for each letter in the alphabet, they are shifted forward the given shift amount  
    	for (char c = 'a'; c <= 'z'; c++) {
    		// "c-'a'" converts the character c into an integer index that represents its position in alphabet starting from 0
    		// modulo "26%" applied for when a character is shifting past the letter "z", it returns to start of alphabet, "wraps around".
    		// "+ 'a'" converts integer index back into character.
    		// final result is integer representing a character, (char) converts result into final char type.
            char encryptedChar = (char) ((c - 'a' + shift) % 26 + 'a');
            // stores each letter with their corresponding encrypted character in hash map
            encryptionKey.put(c, encryptedChar);
            decryptionKey.put(encryptedChar, c);
        }

        for (char c = 'A'; c <= 'Z'; c++) {
            char encryptedChar = (char) ((c - 'A' + shift) % 26 + 'A');
            encryptionKey.put(c, encryptedChar);
            decryptionKey.put(encryptedChar, c);
        }

        // For non-alphabetic characters, maps them to themselves
        for (char c = 32; c < 127; c++) {
            if (!encryptionKey.containsKey(c)) {
                encryptionKey.put(c, c);
                decryptionKey.put(c, c);
            }
        
        } 
    }

    // Method to encrypt the message
    public String encrypt(String message) {
    	// similar to the encrypt method, instead it maps the encrypted characters back to their regular form.
        StringBuilder encrypted = new StringBuilder();
        //standard for loop to iterate through string characters
        for (int i = 0; i < message.length(); i++) {
            char c = message.charAt(i);
            encrypted.append(encryptionKey.getOrDefault(c, c));
        }
        
        return encrypted.toString();
        
    }

    // Method to decrypt the message
    public String decrypt(String encryptedMessage) {
        StringBuilder decrypted = new StringBuilder();
        
        //standard for loop to iterate through string characters
        for (int i = 0; i < encryptedMessage.length(); i++) {
            char c = encryptedMessage.charAt(i);
            decrypted.append(decryptionKey.getOrDefault(c, c));
        }
        
        return decrypted.toString();
    }

    // Method to brute-force decrypt the message
    public void bruteForceDecrypt(String message) {
    	System.out.println("Decryption attempt");
    	for (int shift = 0; shift < 26; shift++) {
    		CaesarCipher cipher = new CaesarCipher();
            cipher.generateEncryptionKey(shift);
            String decryptionAttempt = cipher.decrypt(message);
            System.out.println("Shift " + shift + ": " + decryptionAttempt);
    	}
    	
    	
        
    }
}
