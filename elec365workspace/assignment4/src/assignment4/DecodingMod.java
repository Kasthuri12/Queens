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
import java.util.*;

public class DecodingMod {
	public static void main(String[] args) throws IOException 
	{
		
		//1. Please comment out the section you want to test before running code 
		//2. Please ensure that the file path is correct 
		
		//Create dictionary array from codes developed by CodeBuildingMod 
		File file = new File("C:\\elec365workspace\\assignment4\\outputCC1.txt");
		String[] dictionary = DictionaryMaker(file);
		
		/***************PART 1 **********************/
//		//Take in a file from user 
//		Scanner myObj = new Scanner (System.in);
//		System.out.println("File Path of text file:");
//		File fileEncodeP1 = new File(myObj.nextLine());
//		//Call the Decode function on that file 
//		DecodeP1(fileEncodeP1, dictionary);

		
		/****************PART 2**************************/
		//Read in the Encoding folder 
		File folder = new File ("C:\\elec365workspace\\assignment4\\Encoding");
		String [] names = folder.list();
		
		//For each file in the folder apply the decode function on each 
		for (String count : names) {
			Scanner scanner = new Scanner ("C:\\elec365workspace\\assignment4\\Encoding\\" + count);
			File fileEncodeP2 = new File(scanner.nextLine());
			DecodeP2(fileEncodeP2, dictionary, count);
		}
	}
	
	
		
	/*********************DICTIONARYMAKER********************/ 
	public static String[] DictionaryMaker(File file) throws IOException  {
		
		//This function is the same as the one found in the EncodingMod class 
		
		//File Reader
		FileInputStream fileStream = new FileInputStream(file); 
		InputStreamReader input = new InputStreamReader(fileStream); 
		BufferedReader reader = new BufferedReader(input); 
		

		//Initializing 
		String [] dictionary = new String [128];

		while (true) {
			String line = reader.readLine();
			if (line == null) {
				break;
			}

			String getCode[] = line.split("\t");
			String code = getCode[1];
			
			Scanner scan = new Scanner(line);
			int index = scan.nextInt(); 

			
			dictionary[index] = code;
			if((int)index == 10) {
				dictionary[10] = code;
			}
			
		}
	    reader.close();
	    return dictionary;
	    
	}// end of DictionaryMaker
	
	
	/*********************DECODEP1********************/ 
	public static void DecodeP1(File file, String[] dictionary) throws IOException  {
		//Write to a decoding file 
		File fileOut = new File("C:\\elec365workspace\\assignment4\\decoding.txt");
		if(!fileOut.exists()) {
			fileOut.createNewFile();
		}
		
		//Read file 
		FileInputStream fileStream = new FileInputStream(file); 
		InputStreamReader input = new InputStreamReader(fileStream); 
		BufferedReader reader = new BufferedReader(input);
		
		//Write to file 
		FileWriter filewrite = new FileWriter(fileOut);
        BufferedWriter write = new BufferedWriter(filewrite);
        
        //Encoding file is one line, thus we can read that one line 
        String fileLine = reader.readLine();
        String temp = "";
        
        //Use StringBuilder to append each character as we read through the file 
        StringBuilder build = new StringBuilder();
        
        //Go through the file length and append each char one at a time 
        for(int i=0; i<fileLine.length(); i++) {
        	build.append(fileLine.charAt(i));
        	temp = build.toString();
        	//Check for matches in dictionary after each append made  
        	for(int j=10; j<128; j++) {
        		if(dictionary[j] != null && dictionary[j].equals(temp)) {
        			write.write((char)j);
        			//if there is a match we no longer care about those codes moving forward so we can delete them 
        			build.delete(0, build.length());
        		}
        	}
        	
        }
        write.close();
        filewrite.close();
        reader.close();
	}

	
	public static void DecodeP2(File file, String[] dictionary, String count) throws IOException  {
		//This function is the same as DecodeP1, however we are writing to multiple files in Decoding 
		File fileOut = new File("C:\\elec365workspace\\assignment4\\Decoding\\decoding"+count+".txt");
		if(!fileOut.exists()) {
			fileOut.createNewFile();
		}
		
		FileInputStream fileStream = new FileInputStream(file); 
		InputStreamReader input = new InputStreamReader(fileStream); 
		BufferedReader reader = new BufferedReader(input);
		
		FileWriter filewrite = new FileWriter(fileOut);
        BufferedWriter write = new BufferedWriter(filewrite);
        
        String fileLine = reader.readLine();
        String temp = "";
        StringBuilder build = new StringBuilder();
        for(int i=0; i<fileLine.length(); i++) {
        	build.append(fileLine.charAt(i));
        	temp = build.toString();

        	for(int j=10; j<128; j++) {
        		if(dictionary[j] != null && dictionary[j].equals(temp)) {
        			write.write((char)j);
        			build.delete(0, build.length());
        		}
        	}
        	
        }
        write.close();
        filewrite.close();
        reader.close();
	}
		
}
