import java.util.Arrays;

public class PlayfairCipher {
    private char[][] matrix;

    public PlayfairCipher(String key) {
        matrix = createMatrix(key);
    }

    // Create the Playfair matrix based on the key
    private char[][] createMatrix(String key) {
        key = key.replaceAll("[^a-zA-Z]", "").toUpperCase();
        key = key.replace("J", "I"); // Replace J with I

        char[][] playfairMatrix = new char[5][5];
        String keyString = key + "ABCDEFGHIKLMNOPQRSTUVWXYZ";

        int index = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                playfairMatrix[i][j] = keyString.charAt(index);
                index++;
            }
        }

        return playfairMatrix;
    }

    // Find the positions of two characters in the matrix
    private int[] getPositions(char ch) {
        int[] positions = new int[2];

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (matrix[i][j] == ch) {
                    positions[0] = i;
                    positions[1] = j;
                    return positions;
                }
            }
        }

        return positions;
    }

    // Encrypt a pair of characters using the Playfair cipher rules
    private String encryptPair(char a, char b) {
        int[] positionA = getPositions(a);
        int[] positionB = getPositions(b);

        int rowA = positionA[0];
        int colA = positionA[1];
        int rowB = positionB[0];
        int colB = positionB[1];

        if (rowA == rowB) {
            colA = (colA + 1) % 5;
            colB = (colB + 1) % 5;
        } else if (colA == colB) {
            rowA = (rowA + 1) % 5;
            rowB = (rowB + 1) % 5;
        } else {
            int temp = colA;
            colA = colB;
            colB = temp;
        }

        return String.valueOf(matrix[rowA][colA]) + matrix[rowB][colB];
    }

    // Encrypt the plaintext using the Playfair cipher
    public String encrypt(String plainText) {
        plainText = plainText.replaceAll("[^a-zA-Z]", "").toUpperCase();
        plainText = plainText.replace("J", "I");

        StringBuilder encryptedText = new StringBuilder();

        for (int i = 0; i < plainText.length(); i += 2) {
            char a = plainText.charAt(i);
            char b = (i + 1 < plainText.length()) ? plainText.charAt(i + 1) : 'X';

            if (a == b) {
                b = 'X';
                i--;
            }

            encryptedText.append(encryptPair(a, b));
        }

        return encryptedText.toString();
    }

    public static void main(String[] args) {
        String key = "KEYWORD";
        String plaintext = "HELLO WORLD";

        PlayfairCipher playfairCipher = new PlayfairCipher(key);
        String encryptedText = playfairCipher.encrypt(plaintext);

        System.out.println("Plain Text: " + plaintext);
        System.out.println("Encrypted Text: " + encryptedText);
    }
}
