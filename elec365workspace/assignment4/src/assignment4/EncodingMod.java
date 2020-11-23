/***
 * Kasthuri Thambipillai 
 * 20059146
 * Assignment 3 
 */

package assignment4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class EncodingMod {
	public static void main(String[] args) throws IOException 
	{
		//1. Please comment out the section you want to test before running code 
		//2. Please ensure that the file path is correct 
		
		//take in the file built from the CodeBuilderMod class and create a dictionary for the printables 
		File file = new File("C:\\elec365workspace\\assignment4\\outputCC3.txt");
		String[] dictionary = DictionaryMaker(file);
		
		
		/****************PART 1***********************/
		
//		//Read in the File1.txt
//		File fileEncode = new File ("C:\\elec365workspace\\assignment4\\File1.txt");
//		//Call FileCodeMakerP1 on the file and dictionary 
//		FileCodeMakerP1(fileEncode, dictionary);


		/****************PART 2***********************/
		//Read in folder 
		File folder = new File ("C:\\elec365workspace\\assignment4\\Data");
		String [] names = folder.list();
		
		//Go through all the files within the folder and call FileCodeMakerP2 for each 
		for (String count : names) {
			Scanner scanner = new Scanner ("C:\\elec365workspace\\assignment4\\Data\\" + count);
			File fileEncodeP2 = new File(scanner.nextLine());
			FileCodeMakerP2(fileEncodeP2, dictionary,count);
		}
	}
	
	
		
	/*********************DICTIONARYMAKER********************/ 
	public static String[] DictionaryMaker(File file) throws IOException  {
		
		//File Reader
		FileInputStream fileStream = new FileInputStream(file); 
		InputStreamReader input = new InputStreamReader(fileStream); 
		BufferedReader reader = new BufferedReader(input); 
		

		//Initializing 
		String [] dictionary = new String [128];

		//While loop read each line until there are no other lines to read 
		while (true) {
			String line = reader.readLine();
			if (line == null) {
				break;
			}

			//Split the line by the tab character and store it in array 
			String getCode[] = line.split("\t");
			//The second split holds the code of each printable  
			String code = getCode[1];
			//Use scanner to reach the first part of the line which is the index
			Scanner scan = new Scanner(line);
			int index = scan.nextInt(); 

			//Store each code at the index value that corresponds to the printable to create the dictionary 
			dictionary[index] = code;
			//include the new line character 
			if((int)index == 10) {
				dictionary[10] = code;
			}
		}
	    reader.close();
	    return dictionary;
	    
	}// end of DictionaryMaker

	
	
	
	/*********************FileCodeMakerP!********************/ 
	public static void FileCodeMakerP1(File file, String[] dictionary) throws IOException  {
		//File Reader
		FileInputStream fileStream = new FileInputStream(file); 
		InputStreamReader input = new InputStreamReader(fileStream); 
		BufferedReader reader = new BufferedReader(input); 
		
		//Write result to file 
		File outFile = new File ("C:\\elec365workspace\\assignment4\\encoding.txt");
		if (!outFile.exists()) {
			outFile.createNewFile();
		}
		FileWriter filewrite = new FileWriter(outFile);
        BufferedWriter write = new BufferedWriter(filewrite);
        
        
        //While there are lines in the file to read 
		while (true) {
			String fileLine = reader.readLine();
			if (fileLine == null) {
				break;
			}
			System.out.println(fileLine);
			
			//For the length of the line read each character and write to the file the code that corresponds with that index 
			for (int i = 0; i<fileLine.length(); i++ ) {
				char readLetter = fileLine.charAt(i);
				write.write(dictionary[readLetter]);
			}
			//Write the new line code after each line is read
			write.write(dictionary[10]);
		}
		write.close();
		filewrite.close();
	}
		
	
	public static void FileCodeMakerP2(File file, String[] dictionary, String count) throws IOException  {
		//FileCodeMakerP2 is the same code as FileCodeMakerP1 however for this function we are write to multiple files 
		FileInputStream fileStream = new FileInputStream(file); 
		InputStreamReader input = new InputStreamReader(fileStream); 
		BufferedReader reader = new BufferedReader(input); 
		
		File outFile = new File ("C:\\elec365workspace\\assignment4\\Encoding\\encodingCC3"+count+".txt");
		if (!outFile.exists()) {
			outFile.createNewFile();
		}
		FileWriter filewrite = new FileWriter(outFile);
        BufferedWriter write = new BufferedWriter(filewrite);
        
		while (true) {
			String fileLine = reader.readLine();
			if (fileLine == null) {
				break;
			}
			System.out.println(fileLine);
			
			for (int i = 0; i<fileLine.length(); i++ ) {
				char readLetter = fileLine.charAt(i);
				write.write(dictionary[readLetter]);
			}
			write.write(dictionary[10]);
		}
		write.close();
		filewrite.close();
	}
		
}
