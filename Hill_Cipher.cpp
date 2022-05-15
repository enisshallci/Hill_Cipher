#include<iostream>
#include<math.h>
 
using namespace std;
 
float encrypt[3][1], decrypt[3][1], a[3][3], b[3][3], mes[3][1], c[3][3];
 
void encryption(); //encrypts the message
void decryption(); //decrypts the message
void getKeyMessage(); //gets key and message from user
void inverse(); //finds inverse of key matrix
 
int main() {
getKeyMessage();
encryption();
decryption();
}
 
void encryption() {
	
	int i, j, k;
	for(i = 0; i < 3; i++)
	for(j = 0; j < 1; j++)
	for(k = 0; k < 3; k++)
	
	encrypt[i][j] = encrypt[i][j] + a[i][k] * mes[k][j];
	cout<<"\nEncrypted string is: ";
		for(i = 0; i < 3; i++)
	cout<<(char)(fmod(encrypt[i][0], 26) + 97);
}
 
void decryption() {
	int i, j, k;
	inverse();
	for(i = 0; i < 3; i++)
	for(j = 0; j < 1; j++)
	for(k = 0; k < 3; k++)
		decrypt[i][j] = decrypt[i][j] + b[i][k] * encrypt[k][j];
		cout<<"\nDecrypted string is: ";
	for(i = 0; i < 3; i++)
		cout<<(char)(fmod(decrypt[i][0], 26) + 97);
		cout<<"\n";
}
 
void getKeyMessage() {
	int i, j;
	char msg[3];
	 
	cout<<"Enter 3x3 matrix for key (It should be inversible):\n";
	for(i = 0; i < 3; i++)
	for(j = 0; j < 3; j++) {
	cin>>a[i][j];
	c[i][j] = a[i][j];
}
	cout<<"\nEnter a 3 letter string: ";
	cin>>msg;
	for(i = 0; i < 3; i++)
	mes[i][0] = msg[i] - 97;
}
 
void inverse() {
	int i, j, k;
	float p, q;
	for(i = 0; i < 3; i++)
	for(j = 0; j < 3; j++) {
	if(i == j)
		b[i][j]=1;
	else
	b[i][j]=0;
	}
	for(k = 0; k < 3; k++) {
	for(i = 0; i < 3; i++) {
	p = c[i][k];
	q = c[k][k];	
	for(j = 0; j < 3; j++) {
	if(i != k) {
	c[i][j] = c[i][j]*q - p*c[k][j];
	b[i][j] = b[i][j]*q - p*b[k][j];
			}
		}
	}
}
	for(i = 0; i < 3; i++)
	for(j = 0; j < 3; j++)
	b[i][j] = b[i][j] / c[i][i];
	cout<<"\n\nInverse Matrix is:\n";
	for(i = 0; i < 3; i++) {
	for(j = 0; j < 3; j++)
	cout<<b[i][j]<<" ";
	cout<<"\n";
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
