public class RailFenceCipher {
    // Encrypt plaintext using Rail Fence cipher
    public static String encrypt(String plaintext, int rails) {
        char[][] matrix = new char[rails][plaintext.length()];
        boolean downward = false;
        int row = 0, col = 0;

        for (int i = 0; i < plaintext.length(); i++) {
            if (row == 0 || row == rails - 1)
                downward = !downward;

            matrix[row][col++] = plaintext.charAt(i);

            if (downward)
                row++;
            else
                row--;
        }

        StringBuilder encryptedText = new StringBuilder();
        for (int i = 0; i < rails; i++) {
            for (int j = 0; j < plaintext.length(); j++) {
                if (matrix[i][j] != 0)
                    encryptedText.append(matrix[i][j]);
            }
        }
        return encryptedText.toString();
    }

    // Decrypt ciphertext using Rail Fence cipher
    public static String decrypt(String ciphertext, int rails) {
        char[][] matrix = new char[rails][ciphertext.length()];
        boolean downward = false;
        int row = 0, col = 0;

        for (int i = 0; i < ciphertext.length(); i++) {
            if (row == 0)
                downward = true;
            else if (row == rails - 1)
                downward = false;

            matrix[row][col++] = '*'; // Placeholder for filled spaces

            if (downward)
                row++;
            else
                row--;
        }

        int index = 0;
        for (int i = 0; i < rails; i++) {
            for (int j = 0; j < ciphertext.length(); j++) {
                if (matrix[i][j] == '*' && index < ciphertext.length())
                    matrix[i][j] = ciphertext.charAt(index++);
            }
        }

        StringBuilder decryptedText = new StringBuilder();
        row = 0;
        col = 0;
        for (int i = 0; i < ciphertext.length(); i++) {
            if (row == 0)
                downward = true;
            else if (row == rails - 1)
                downward = false;

            decryptedText.append(matrix[row][col++]);

            if (downward)
                row++;
            else
                row--;
        }

        return decryptedText.toString();
    }

    public static void main(String[] args) {
        String plaintext = "HELLO WORLD";
        int rails = 3;

        String encryptedText = encrypt(plaintext, rails);
        System.out.println("Encrypted text: " + encryptedText);

        String decryptedText = decrypt(encryptedText, rails);
        System.out.println("Decrypted text: " + decryptedText);
    }
}
