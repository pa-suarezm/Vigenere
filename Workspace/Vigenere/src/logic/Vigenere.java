package logic;

import java.util.Scanner;

public class Vigenere {
	
	//---------------------------------------------------------
	//Attributes
	//---------------------------------------------------------

	private String[][] table = new String[27][27];
	
	private String[] alphabet = new String[27];
	
	private String key = "";
	
	//---------------------------------------------------------
	//Constructor method
	//---------------------------------------------------------
	
	public Vigenere(){
		initialize();
		
		//Uncomment the line below to verify the table using the stdout
		//verify();
	}
	
	//---------------------------------------------------------
	//Public methods
	//---------------------------------------------------------
	
	/**
	 * Three getters and one setter. Self explanatory
	 */
	public String[][] getTable(){return table;}
	public String[] getAlphabet(){return alphabet;}
	public String getKey(){return key;}
	public void setKey(String nKey){key = nKey;}
	
	/**
	 * Encrypts the message that comes in using the value stored in "key"
	 * @param msg
	 * @returns Encrypted message
	 */
	public String encrypt(String msg){
		String ans = "";
		
		int mLength = msg.length();
		int kLength = key.length();
		
		String sequence = "";
		for(int i=0; i<=(Math.floorDiv(mLength, kLength)-1); i++)
			sequence += key;
		int rest = (mLength%kLength);
		sequence += key.substring(0, rest);
		
		String[] aMsg = msg.split("");
		String[] aSeq = sequence.split("");
		
		for(int i=0; i<mLength; i++)
			ans += table[giveIndex(aMsg[i])][giveIndex(aSeq[i])];
		
		return ans;
	}
	
	/**
	 * Decrypts the message that comes in using the value stored in "key"
	 * @param msg
	 * @returns Decrypted message
	 */
	public String decrypt (String msg){
		String ans = "";
		
		int mLength = msg.length();
		int kLength = key.length();
		
		String sequence = "";
		for(int i=0; i<=(Math.floorDiv(mLength, kLength)-1); i++)
			sequence += key;
		int rest = (mLength%kLength);
		sequence += key.substring(0, rest);
		
		String[] aMsg = msg.split("");
		String[] aSeq = sequence.split("");
		
		for(int i=0; i<mLength; i++){
			ans += table[giveIndexInRow(aMsg[i], giveIndex(aSeq[i]))][0];
		}
		
		return ans;
	}
	
	//---------------------------------------------------------
	//Main method
	//---------------------------------------------------------
	
	public static void main(String[] args) {
		Vigenere cypher = new Vigenere();
		Scanner s = new Scanner(System.in);
		System.out.println("=========================================================");
		System.out.println("-----------Vigenere cipher encryptor/decryptor-----------");
		System.out.println("=========================================================");
		
		int option = 0;
		while(option != -1){
			System.out.println("Enter the key to encrypt/decrypt with:");
			cypher.setKey(s.nextLine());
			System.out.println("REMEMBER TO KEEP YOUR KEY SOMEWHERE SAFE");
			System.out.println("IF YOU LOOSE IT, THERE'S NO WAY TO DECRYPT THE ENCRYPTED MSG");
			System.out.println("------------------------------------------------------------------");
			
			System.out.println("Enter the text you wish to encrypt/decrypt:");
			String msg = s.nextLine();
			System.out.println("------------------------------------------------------------------");
			
			System.out.println("Enter the number of the option you wish (eg. 1):");
			System.out.println("1. Encrypt message");
			System.out.println("2. Decrypt message");
			option = Integer.valueOf(s.nextLine().trim());
			System.out.println("------------------------------------------------------------------");
			String ans = "";
			if (option == 1)
				ans = cypher.encrypt(msg);
			else if (option == 2)
				ans = cypher.decrypt(msg);
			else {
				ans = "Please restart the program and try again";
				option = -1;
			}
			System.out.println(ans);
			System.out.println("------------------------------------------------------------------");
			System.out.println("==================================================================");
		}
		
		s.close();
	}
	
	//---------------------------------------------------------
	//Private methods
	//---------------------------------------------------------
	
	/**
	 * Given a row returns the index of the letter in that row of the table
	 * @param letter
	 * @param row
	 * @return  Index of letter in row
	 */
	private int giveIndexInRow(String letter, int row){
		int ans = -1;
		
		boolean found = false;
		for(int i=0; i<27 && !found; i++){
			if(table[i][row].equalsIgnoreCase(letter)){
				ans = i;
				found = true;
			}
		}
		
		return ans;
	}
	
	/**
	 * Gives the index of a letter in the alphabet
	 * @param letter
	 * @return Index of the letter
	 */
	private int giveIndex(String letter){
		int ans = -1;
		
		boolean found = false;
		for(int i=0; i<27 && !found; i++){
			if(alphabet[i].equalsIgnoreCase(letter)){
				ans = i;
				found = true;
			}
		}
		
		return ans;
	}
	
	/**
	 * Initializes the variables "alphabet" and "table"
	 */
	private void initialize(){
		//Alfabeto
		alphabet[0] = "A";
		alphabet[1] = "B";
		alphabet[2] = "C";
		alphabet[3] = "D";
		alphabet[4] = "E";
		alphabet[5] = "F";
		alphabet[6] = "G";
		alphabet[7] = "H";
		alphabet[8] = "I";
		alphabet[9] = "J";
		alphabet[10] = "K";
		alphabet[11] = "L";
		alphabet[12] = "M";
		alphabet[13] = "N";
		alphabet[14] = "�";
		alphabet[15] = "O";
		alphabet[16] = "P";
		alphabet[17] = "Q";
		alphabet[18] = "R";
		alphabet[19] = "S";
		alphabet[20] = "T";
		alphabet[21] = "U";
		alphabet[22] = "V";
		alphabet[23] = "W";
		alphabet[24] = "X";
		alphabet[25] = "Y";
		alphabet[26] = "Z";
		
		//Table
		for(int i=0; i<27; i++){
			for(int j=0; j<27; j++){
				table[i][j] = alphabet[(j+i)%27];
			}
		}
	}
	
	/**
	 * Prints the contents of "alphabet" and "table" so the user/programmer
	 * can verify and validate it
	 */
	@SuppressWarnings("unused")
	private void verify(){
		//Alphabet
		System.out.print("[");
		for(int i=0; i<26; i++)
			System.out.print(alphabet[i] + ", ");
		System.out.println(alphabet[26] + "]");
		System.out.println("-------------------------------------");
		
		//Table
		for(int i=0; i<27; i++){
			System.out.println();
			for(int j=0; j<27; j++){
				System.out.print(table[i][j]);
			}
		}
	}
}
