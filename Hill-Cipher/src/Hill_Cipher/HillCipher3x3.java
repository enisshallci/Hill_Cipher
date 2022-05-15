package Hill_Cipher;
import java.util.Scanner;

public class HillCipher3x3 {

	 private static Scanner in;
	    public static void main(String[] args){
	        in = new Scanner(System.in);

	        System.out.print("1. Encryption\n2. Decryption\nChoose(1,2): ");
	        int choice = in.nextInt();
	        in.nextLine();

	        if(choice == 1){
	            System.out.println("---Encryption---");
	            cipherEncryption();
	        } else if (choice == 2){
	            System.out.println("---Decryption---");
	            cipherDecryption();
	        } else {
	            System.out.println("Invalid Choice");
	        }
	    }

	    private static void cipherDecryption() {
	        System.out.print("Enter message: ");
	        String msg = in.nextLine();
	        msg = msg.replaceAll("\\s" , "");
	        msg = msg.toUpperCase();

	        // if irregular length, then perform padding
	        int lenChk = 0;
	        if (msg.length() % 3 != 0){
	            msg += "0";
	            lenChk = 1;
	        }

	        // message to matrices
	        int[][] msg3D = new int[3][msg.length() / 3];

	        int q = 0;
	        for (int i = 0; i < msg.length() / 3; i++) {
		    	for (int j = 0; j < 3; j++) {
		    		msg3D[j][i] = ((int)msg.charAt(q++)) - 65;
		    	}
	        } 

	        System.out.print("Enter 9 letter key string: ");
	        String key = in.nextLine();
	        key = key.replaceAll("\\s","");
	        key = key.toUpperCase();

	        // key to 3x3 matrix
	        int[][] key3D = new int[3][3];
	        int itr3 = 0;
	        for (int i = 0; i < 3; i++) {
	            for (int j = 0; j < 3; j++) {
	            	key3D[i][j] = (int)key.charAt(itr3)-65;
	                itr3++;
	            }
	        }

	        // validating the key
	        // finding determinant of key matrix
	        int deter = (key3D[0][0] * key3D[1][1] * key3D[2][2])
	        		+ (key3D[0][1] * key3D[1][2] * key3D[2][0])
	        		+ (key3D[0][2] * key3D[1][0] * key3D[1][2])
	        		- (key3D[2][0] * key3D[2][1] * key3D[0][0]) 
	        		- (key3D[2][2] * key3D[1][0] * key3D[0][1]) 
	        		- (key3D[2][2] * key3D[1][0] * key3D[0][1]);
	        deter = moduloFunc(deter, 26);

	        // multiplicative inverse of key matrix
	        int mulInverse = -1;
	        for (int i = 0; i < 26; i++) {
	            int tempInv = deter * i;
	            if (moduloFunc(tempInv, 26) == 1){
	                mulInverse = i;
	                break;
	            } else {
	                continue;
	            } 
	        } 
//Matrica e adnjuguar
       
        key3D[0][0] = (key3D[1][1] * key3D[2][2]) - (key3D[1][2] * key3D[2][1]);
        key3D[0][1] = (key3D[1][0] * key3D[2][2]) - (key3D[1][2] * key3D[2][0]) ;
        key3D[0][2] = (key3D[1][0] * key3D[2][1]) - (key3D[1][1] * key3D[2][0]);
        key3D[1][0] = (key3D[0][1] * key3D[2][2]) - (key3D[0][2] * key3D[2][1]);
        key3D[1][1] = (key3D[0][0] * key3D[2][2]) - (key3D[0][2] * key3D[2][0]);
        key3D[1][2] = (key3D[0][0] * key3D[2][1]) - (key3D[0][1] * key3D[2][0]);
        key3D[2][0] = (key3D[0][1] * key3D[1][2]) - (key3D[0][2] * key3D[1][1]);
        key3D[2][1] = (key3D[0][0] * key3D[1][2]) - (key3D[0][2] * key3D[1][0]);
        key3D[2][2] = (key3D[0][0] * key3D[1][1]) - (key3D[0][1] * key3D[1][0]);
     
       
        key3D[0][1] *= -1;
        key3D[1][0] *= -1;
        key3D[1][2] *= -1;
        key3D[2][1] *= -1;
       
//     key3D[0][1] = moduloFunc(key3D[0][1], 26);
//     key3D[1][0] = moduloFunc(key3D[1][0], 26);
//     key3D[1][2] = moduloFunc(key3D[1][2], 26);
//     key3D[2][1] = moduloFunc(key3D[2][1], 26);
       
        key3D[0][0] = moduloFunc(key3D[0][0], 26);
        key3D[0][1] = moduloFunc(key3D[0][1], 26);
       
        key3D[1][0] = moduloFunc(key3D[1][0], 26);
        key3D[1][1] = moduloFunc(key3D[1][1], 26);

    key3D[2][0] = moduloFunc(key3D[2][0], 26);
    key3D[2][2] = moduloFunc(key3D[2][2], 26);
       
       System.out.println(key3D[0][0]);
   System.out.println(key3D[0][1]);
   System.out.println(key3D[0][2]);
   System.out.println(key3D[1][0]);
   System.out.println(key3D[1][1]);
   System.out.println(key3D[1][2]);
   System.out.println(key3D[2][0]);
   System.out.println(key3D[2][1]);
   System.out.println(key3D[2][2]);
   
 

       
       // multiplying multiplicative inverse with adjugate matrix
       for (int i = 0; i < 3; i++) {
           for (int j = 0; j < 3; j++) {
               key3D[i][j] *= mulInverse;
           }
       }
   

       String decrypText = "";
       int itrCount = msg.length() / 3;
       if (lenChk == 0){
           // if msg length % 3 = 0
           for (int i = 0; i < itrCount; i++) {
             int temp1 = msg3D[0][i] * key3D[0][0] + msg3D[1][i] * key3D[0][1] + msg3D[2][i] * key3D[0][2];
              decrypText += (char)((temp1 % 26) + 65);
               int temp2 = msg3D[0][i] * key3D[1][0] + msg3D[1][i] * key3D[1][1] + msg3D[2][i] * key3D[1][2];
               decrypText += (char)((temp2 % 26) + 65);
               int temp3 = msg3D[0][i] * key3D[2][0] + msg3D[1][i] * key3D[2][1] + msg3D[2][i] * key3D[2][2];
               decrypText += (char)((temp3 % 26) + 65);
           }
       } else {
           // if msg length % 3 != 0 (irregular length msg)
           for (int i = 0; i < itrCount - 1; i++) {
             int temp1 = msg3D[0][i] * key3D[0][0] + msg3D[1][i] * key3D[0][1] + msg3D[2][i] * key3D[0][2];
              decrypText += (char)((temp1 % 26) + 65);
               int temp2 = msg3D[0][i] * key3D[1][0] + msg3D[1][i] * key3D[1][1] + msg3D[2][i] * key3D[1][2];
               decrypText += (char)((temp2 % 26) + 65);
               int temp3 = msg3D[0][i] * key3D[2][0] + msg3D[1][i] * key3D[2][1] + msg3D[2][i] * key3D[2][2];
               decrypText += (char)((temp3 % 26) + 65);
           }
       }

       System.out.println("Decrypted Text: " + decrypText);


   }

   private static void cipherEncryption() {
       System.out.print("Enter message: ");
       String msg = in.nextLine();
       msg = msg.replaceAll("\\s" , "");
       msg = msg.toUpperCase();

       // if irregular length, then perform padding
       int lenChk = 0;
       if (msg.length() % 3 != 0){
           msg += "0";
           lenChk = 1;
       }

       int[][] msg3D = new int[3][msg.length() / 3];

       int q = 0;
   for (int i = 0; i < msg.length() / 3; i++) {
    for (int j = 0; j < 3; j++) {
    msg3D[j][i] = ((int)msg.charAt(q++)) - 65;
    }
   }
 

       System.out.print("Enter 9 letter key string: ");
       String key = in.nextLine();
       key = key.replaceAll("\\s","");
       key = key.toUpperCase();

       // key to 3x3 matrix
       int[][] key3D = new int[3][3];
       int itr3 = 0;
       for (int i = 0; i < 3; i++) {
           for (int j = 0; j < 3; j++) {
            key3D[i][j] = (int)key.charAt(itr3)-65;
               itr3++;
           }
       }

       // validating the key
       // finding determinant of key matrix
       int deter = (key3D[0][0] * key3D[1][1] * key3D[2][2])
        + (key3D[0][1] * key3D[1][2] * key3D[2][0])
        + (key3D[0][2] * key3D[1][0] * key3D[1][2])
        - (key3D[2][0] * key3D[2][1] * key3D[0][0])
        - (key3D[2][2] * key3D[1][0] * key3D[0][1])
        - (key3D[2][2] * key3D[1][0] * key3D[0][1]);
       deter = moduloFunc(deter, 26);

       // multiplicative inverse of key matrix
       int mulInverse = -1;
       for (int i = 0; i < 26; i++) {
           int tempInv = deter * i;
           if (moduloFunc(tempInv, 26) == 1){
               mulInverse = i;
               break;
           } else {
               continue;
           }
       }

       if (mulInverse == -1){
           System.out.println("invalid key");
           System.exit(1);
       }

       String encrypText = "";
       int itrCount = msg.length() / 3;
       if (lenChk == 0){
           // if msg length % 3 = 0
           for (int i = 0; i < itrCount; i++) {
               int temp1 = msg3D[0][i] * key3D[0][0] + msg3D[1][i] * key3D[0][1] + msg3D[2][i] * key3D[0][2];
               encrypText += (char)((temp1 % 26) + 65);
               int temp2 = msg3D[0][i] * key3D[1][0] + msg3D[1][i] * key3D[1][1] + msg3D[2][i] * key3D[1][2];
               encrypText += (char)((temp2 % 26) + 65);
               int temp3 = msg3D[0][i] * key3D[2][0] + msg3D[1][i] * key3D[2][1] + msg3D[2][i] * key3D[2][2];
               encrypText += (char)((temp3 % 26) + 65);
           }
       } else {
           // if msg length % 3 != 0 (irregular length msg)
           for (int i = 0; i < itrCount-1; i++) {
              int temp1 = msg3D[0][i] * key3D[0][0] + msg3D[1][i] * key3D[0][1] + msg3D[2][i] * key3D[0][2];
               encrypText += (char)((temp1 % 26) + 65);
               int temp2 = msg3D[0][i] * key3D[1][0] + msg3D[1][i] * key3D[1][1] + msg3D[2][i] * key3D[1][2];
               encrypText += (char)((temp2 % 26) + 65);
               int temp3 = msg3D[0][i] * key3D[2][0] + msg3D[1][i] * key3D[2][1] + msg3D[2][i] * key3D[2][2];
               encrypText += (char)((temp3 % 26) + 65);
           }
       }

       System.out.println("Encrypted Text: " + encrypText);
   }

   // modulo function
   public static int moduloFunc(int a, int b){
       int result = a % b;
       if (result < 0){
           result += b;
       }
       return result;
   }
}
