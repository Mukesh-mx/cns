public class VigenereCipher {
    // Encrypt the plaintext using the Vigenère cipher
    public static String encrypt(String plaintext, String key) {
        plaintext = plaintext.toUpperCase();
        key = key.toUpperCase();

        StringBuilder encryptedText = new StringBuilder();
        int keyLength = key.length();
        int textLength = plaintext.length();

        for (int i = 0, j = 0; i < textLength; i++) {
            char currentChar = plaintext.charAt(i);

            if (Character.isLetter(currentChar)) {
                int shift = (key.charAt(j) - 'A');

                char encryptedChar = (char) (((currentChar - 'A' + shift) % 26) + 'A');
                encryptedText.append(encryptedChar);

                j = (j + 1) % keyLength;
            } else {
                encryptedText.append(currentChar);
            }
        }

        return encryptedText.toString();
    }

    // Decrypt the ciphertext using the Vigenère cipher
    public static String decrypt(String ciphertext, String key) {
        ciphertext = ciphertext.toUpperCase();
        key = key.toUpperCase();

        StringBuilder decryptedText = new StringBuilder();
        int keyLength = key.length();
        int textLength = ciphertext.length();

        for (int i = 0, j = 0; i < textLength; i++) {
            char currentChar = ciphertext.charAt(i);

            if (Character.isLetter(currentChar)) {
                int shift = (key.charAt(j) - 'A');

                char decryptedChar = (char) (((currentChar - 'A' - shift + 26) % 26) + 'A');
                decryptedText.append(decryptedChar);

                j = (j + 1) % keyLength;
            } else {
                decryptedText.append(currentChar);
            }
        }

        return decryptedText.toString();
    }

    public static void main(String[] args) {
        String plaintext = "HELLO";
        String key = "KEY";

        String encryptedText = encrypt(plaintext, key);
        System.out.println("Encrypted text: " + encryptedText);

        String decryptedText = decrypt(encryptedText, key);
        System.out.println("Decrypted text: " + decryptedText);
    }
}
