import java.util.*;

class TransCipher {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the plain text");
        String pl = sc.nextLine();
        sc.close();
        
        String s = "";
        int start = 0;
        for (int i = 0; i < pl.length(); i++) {
            if (pl.charAt(i) == ' ') {
                s = s + pl.substring(start, i) + " "; // Add a space between words
                start = i + 1;
            }
        }
        s = s + pl.substring(start);
        
        System.out.println(s);

        // Transposition
        int k = s.length();
        int col = 4;
        int row = (int) Math.ceil((double) k / col);
        char ch[][] = new char[row][col];

        int l = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (l < k) {
                    ch[i][j] = s.charAt(l);
                    l++;
                } else {
                    ch[i][j] = '#';
                }
            }
        }

        char trans[][] = new char[col][row];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                trans[j][i] = ch[i][j];
            }
        }

        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                System.out.print(trans[i][j]);
            }
        }
        System.out.println();
    }
}