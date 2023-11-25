public class CaesarCipher {
    // Encryption method
    public static String encrypt(String plainText, int shiftKey) {
        StringBuilder cipherText = new StringBuilder();

        for (int i = 0; i < plainText.length(); i++) {
            char ch = plainText.charAt(i);

            if (Character.isLetter(ch)) {
                char shifted = (char) ('A' + (ch - 'A' + shiftKey) % 26);
                cipherText.append(shifted);
            } else {
                cipherText.append(ch);
            }
        }

        return cipherText.toString();
    }

    // Decryption method
    public static String decrypt(String cipherText, int shiftKey) {
        StringBuilder plainText = new StringBuilder();

        for (int i = 0; i < cipherText.length(); i++) {
            char ch = cipherText.charAt(i);

            if (Character.isLetter(ch)) {
                char shifted = (char) ('A' + (ch - 'A' - shiftKey + 26) % 26);
                plainText.append(shifted);
            } else {
                plainText.append(ch);
            }
        }

        return plainText.toString();
    }

    public static void main(String[] args) {
        String message = "Hello, World!";
        int shiftKey = 3;

        // Encrypt the message
        String encryptedMessage = encrypt(message.toUpperCase(), shiftKey);
        System.out.println("Encrypted message: " + encryptedMessage);

        // Decrypt the message
        String decryptedMessage = decrypt(encryptedMessage, shiftKey);
        System.out.println("Decrypted message: " + decryptedMessage);
    }
}
