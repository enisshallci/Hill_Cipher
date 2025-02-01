import java.util.Scanner;

public class HillCipherImplementation {
    private static Scanner in;

    public static void main(String[] args) {
        in = new Scanner(System.in);
        System.out.print("1. Encryption\n2. Decryption\nChoose(1,2): ");
        int choice = in.nextInt();
        in.nextLine();
        if (choice == 1) {
            System.out.println("---Encryption---");
            cipherEncryption();
        } else if (choice == 2) {
            System.out.println("---Decryption---");
            cipherDecryption();
        } else {
            System.out.println("Invalid Choice");
        }

    }

    private static void cipherDecryption() {
        System.out.print("Enter message: ");
        String msg = in.nextLine();
        msg = msg.replaceAll("\\s", "");
        msg = msg.toUpperCase();
        boolean lenChk = false;
        if (msg.length() % 2 != 0) {
            msg = msg + "0";
            lenChk = true;
        }

        int[][] msg2D = new int[2][msg.length()];
        int itr1 = 0;
        int itr2 = 0;

        for(int i = 0; i < msg.length(); ++i) {
            if (i % 2 == 0) {
                msg2D[0][itr1] = msg.charAt(i) - 65;
                ++itr1;
            } else {
                msg2D[1][itr2] = msg.charAt(i) - 65;
                ++itr2;
            }
        }

        System.out.print("Enter 4 letter key string: ");
        String key = in.nextLine();
        key = key.replaceAll("\\s", "");
        key = key.toUpperCase();
        int[][] key2D = new int[2][2];
        int itr3 = 0;

        int deter;
        int mulInverse;
        for(deter = 0; deter < 2; ++deter) {
            for(mulInverse = 0; mulInverse < 2; ++mulInverse) {
                key2D[deter][mulInverse] = key.charAt(itr3) - 65;
                ++itr3;
            }
        }

        deter = key2D[0][0] * key2D[1][1] - key2D[0][1] * key2D[1][0];
        deter = moduloFunc(deter, 26);
        mulInverse = -1;

        int i;
        for(i = 0; i < 26; ++i) {
            i = deter * i;
            if (moduloFunc(i, 26) == 1) {
                mulInverse = i;
                break;
            }
        }

        i = key2D[0][0];
        key2D[0][0] = key2D[1][1];
        key2D[1][1] = i;
        key2D[0][1] *= -1;
        key2D[1][0] *= -1;
        key2D[0][1] = moduloFunc(key2D[0][1], 26);
        key2D[1][0] = moduloFunc(key2D[1][0], 26);
        System.out.println(key2D[1][0]);

        int itrCount;
        for(i = 0; i < 2; ++i) {
            for(itrCount = 0; itrCount < 2; ++itrCount) {
                key2D[i][itrCount] *= mulInverse;
            }
        }

        String decrypText = "";
        itrCount = msg.length() / 2;
        int temp1;
        int temp2;
        if (!lenChk) {
            for(i = 0; i < itrCount; ++i) {
                temp1 = msg2D[0][i] * key2D[0][0] + msg2D[1][i] * key2D[0][1];
                decrypText = decrypText + (char)(temp1 % 26 + 65);
                temp2 = msg2D[0][i] * key2D[1][0] + msg2D[1][i] * key2D[1][1];
                decrypText = decrypText + (char)(temp2 % 26 + 65);
            }
        } else {
            for(i = 0; i < itrCount - 1; ++i) {
                temp1 = msg2D[0][i] * key2D[0][0] + msg2D[1][i] * key2D[0][1];
                decrypText = decrypText + (char)(temp1 % 26 + 65);
                temp2 = msg2D[0][i] * key2D[1][0] + msg2D[1][i] * key2D[1][1];
                decrypText = decrypText + (char)(temp2 % 26 + 65);
            }
        }

        System.out.println("Decrypted Text: " + decrypText);
    }

    private static void cipherEncryption() {
        System.out.print("Enter message: ");
        String msg = in.nextLine();
        msg = msg.replaceAll("\\s", "");
        msg = msg.toUpperCase();
        boolean lenChk = false;
        if (msg.length() % 2 != 0) {
            msg = msg + "0";
            lenChk = true;
        }

        int[][] msg2D = new int[2][msg.length()];
        int itr1 = 0;
        int itr2 = 0;

        for(int i = 0; i < msg.length(); ++i) {
            if (i % 2 == 0) {
                msg2D[0][itr1] = msg.charAt(i) - 65;
                ++itr1;
            } else {
                msg2D[1][itr2] = msg.charAt(i) - 65;
                ++itr2;
            }
        }

        System.out.print("Enter 4 letter key string: ");
        String key = in.nextLine();
        key = key.replaceAll("\\s", "");
        key = key.toUpperCase();
        int[][] key2D = new int[2][2];
        int itr3 = 0;

        int deter;
        int mulInverse;
        for(deter = 0; deter < 2; ++deter) {
            for(mulInverse = 0; mulInverse < 2; ++mulInverse) {
                key2D[deter][mulInverse] = key.charAt(itr3) - 65;
                ++itr3;
            }
        }

        deter = key2D[0][0] * key2D[1][1] - key2D[0][1] * key2D[1][0];
        deter = moduloFunc(deter, 26);
        mulInverse = -1;

        int itrCount;
        for(int i = 0; i < 26; ++i) {
            itrCount = deter * i;
            if (moduloFunc(itrCount, 26) == 1) {
                mulInverse = i;
                break;
            }
        }

        if (mulInverse == -1) {
            System.out.println("invalid key");
            System.exit(1);
        }

        String encrypText = "";
        itrCount = msg.length() / 2;
        int i;
        int temp1;
        int temp2;
        if (!lenChk) {
            for(i = 0; i < itrCount; ++i) {
                temp1 = msg2D[0][i] * key2D[0][0] + msg2D[1][i] * key2D[0][1];
                encrypText = encrypText + (char)(temp1 % 26 + 65);
                temp2 = msg2D[0][i] * key2D[1][0] + msg2D[1][i] * key2D[1][1];
                encrypText = encrypText + (char)(temp2 % 26 + 65);
            }
        } else {
            for(i = 0; i < itrCount - 1; ++i) {
                temp1 = msg2D[0][i] * key2D[0][0] + msg2D[1][i] * key2D[0][1];
                encrypText = encrypText + (char)(temp1 % 26 + 65);
                temp2 = msg2D[0][i] * key2D[1][0] + msg2D[1][i] * key2D[1][1];
                encrypText = encrypText + (char)(temp2 % 26 + 65);
            }
        }

        System.out.println("Encrypted Text: " + encrypText);
    }

    public static int moduloFunc(int a, int b) {
        int result = a % b;
        if (result < 0) {
            result += b;
        }

        return result;
    }
}
