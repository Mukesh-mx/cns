import java.util.Scanner;

public class HillCipher {
    private int[][] keyMatrix;
    private int keySize;

    // Constructor to set the key matrix
    public HillCipher(int[][] key) {
        keySize = key.length;
        keyMatrix = key;
    }

    // Encrypt a message using the key matrix
    public String encrypt(String message) {
        message = message.replaceAll("[^a-zA-Z]", "").toUpperCase();

        // Padding the message if its length is not divisible by keySize
        while (message.length() % keySize != 0) {
            message += 'X';
        }

        StringBuilder encryptedText = new StringBuilder();

        for (int i = 0; i < message.length(); i += keySize) {
            int[] plainBlock = new int[keySize];
            int[] cipherBlock = new int[keySize];

            // Create blocks of integers from letters using their ASCII values
            for (int j = 0; j < keySize; j++) {
                plainBlock[j] = message.charAt(i + j) - 'A';
            }

            // Multiply the key matrix by the plain block
            for (int j = 0; j < keySize; j++) {
                for (int k = 0; k < keySize; k++) {
                    cipherBlock[j] += keyMatrix[j][k] * plainBlock[k];
                }
                cipherBlock[j] %= 26; // Modulo 26 for the result to be in the range [0-25]
            }

            // Convert the integers back to letters
            for (int j = 0; j < keySize; j++) {
                encryptedText.append((char) (cipherBlock[j] + 'A'));
            }
        }

        return encryptedText.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Example key matrix for demonstration purposes (3x3 matrix)
        int[][] key = {
            {6, 24, 1},
            {13, 16, 10},
            {20, 17, 15}
        };

        HillCipher hillCipher = new HillCipher(key);

        System.out.print("Enter the message to encrypt: ");
        String message = scanner.nextLine();

        String encryptedMessage = hillCipher.encrypt(message);
        System.out.println("Encrypted message: " + encryptedMessage);

        scanner.close();
    }
}
