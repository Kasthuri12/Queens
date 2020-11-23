/***
 * Kasthuri Thambipillai 
 * 20059146
 * Assignment 3 
 */

package assignment4;
import java.io.*;
import java.util.*;
import java.util.zip.*;
public class CodeBuildingMod {
	
	public static void main(String[] args) throws Exception 
	{
		//1. Please comment out the section you want to test before running code 
		//2. Please ensure that the file path is correct 
		
		/************************PART1***********************/
//		//Read in File1.txt
//		Scanner scannerP1 = new Scanner ("C:\\elec365workspace\\assignment4\\File1.txt");
//		File fileP1 = new File(scannerP1.nextLine());
//		
//		//Call the CalFreq function and return the result to a an array 
//		int[] freqP1 = CalFreq(fileP1);
//		
//		//Creates an array with all the freqs stored at the index corresponding to the ASCII value of the printable 
//		char[] charsP1 = new char[128];
//        for (int i = 0; i < 128; i++) {
//        	charsP1[i] = (char)i;
//        }
//       
//        //Call the Huffman function to create the binary codes 
//        HuffmanCode(freqP1, charsP1);
		
		/************************PART2***********************/
        //Read in folder 
		File folder = new File ("C:\\elec365workspace\\assignment4\\CanonicalCollection3");
		String [] names = folder.list();
		
		//Go through all he files in the folder 
		for (String count : names) {
			Scanner scannerP2 = new Scanner ("C:\\elec365workspace\\assignment4\\CanonicalCollection3\\" + count);
		
			File file = new File(scannerP2.nextLine());
			
			//Call the CalFreq function and return the result to a an array 
			int[] freqP2 = CalFreq(file);
			
			//Creates an array with all the freqs stored at the index corresponding to the ASCII value of the printable
			char[] charsP2 = new char[128];
	        for (int i = 0; i < 128; i++) {
	        	charsP2[i] = (char)i;
	        }
	        
	        //Call the Huffman function to create the binary codes 
	        HuffmanCode(freqP2, charsP2);
		}
        
	}
	
	
	
	
	/*********************CALFREQ********************/ 
	public static int[] CalFreq(File file) throws IOException  {	
		//File Reader
		FileInputStream fileStream = new FileInputStream(file); 
		InputStreamReader input = new InputStreamReader(fileStream); 
		BufferedReader reader = new BufferedReader(input); 
		
		//Initializing 
		char cChar;
		int nextChar;
		int [] freq = new int [128];
		int lineCount = 0;
		
		//Loop to calculate all the characters in the file   
		while ((nextChar = reader.read()) != -1) {
			cChar = ((char) nextChar);
			
			//increase the freq at the index of the printable value  
			freq[cChar]++;
			
			//Create a condition so that we can count the freq of new lines 
			if((int)cChar == 10) {
				lineCount++;
			}
		}
		freq[10] = lineCount;
	   
		reader.close();
	    return freq;
	    
	}// end of CalFreq
	
	
    
    /**********************PRINTCODE*******************/
    public static void printCode(HuffmanNode root, String s, BufferedWriter write) throws Exception{ 
        //The following code for the HuffmanCode Function was built based of the following reference 
    	/**
    	 * Author: Aashish Barnwal 
    	 * https://www.geeksforgeeks.org/huffman-coding-greedy-algo-3/
    	 */
    	
    	//BASE CASE - if we are looking at a leaf we print s, the code that is generated from traversing the tree 
        if (root.left == null && root.right == null) { 
            System.out.println((int)root.c +"\t"+ s); 
            //Writes to output file
            write.write((int)root.c +"\t"+ s);
            write.newLine();
            return; 
        } 
        
        //Call printCode to assign 0 when we left and 1 when we go right 
        printCode(root.left, s + "0", write); 
        printCode(root.right, s + "1", write); 
        
    }//end printCode
    
    
    
	
	/**********************HUFFMANCODE********************/
    //The following code for the HuffmanCode Function was built based of the following reference 
    	/**
    	 * Author: Aashish Barnwal 
    	 * https://www.geeksforgeeks.org/huffman-coding-greedy-algo-3/
    	 */
    
	public static void HuffmanCode(int[] charfreq, char[] charArray) throws Exception {
		int n = 128;
		
		//Creating a Priority Queue 
        PriorityQueue<HuffmanNode> q 
            = new PriorityQueue<HuffmanNode>(n, new MyComparator()); 
  
        //Creating a Huffman node using the respective class and add each printable to the priority queue 
        for (int i = 32; i < n; i++) {  
            HuffmanNode hn = new HuffmanNode(); 
            hn.c = charArray[i];
            hn.data = charfreq[i]; 
            hn.left = null; 
            hn.right = null; 
            q.add(hn); 
        } 
        
        //Create a node for the new line character and add it to the priority queue 
        HuffmanNode hn = new HuffmanNode(); 
        hn.c = charArray[10]; 
        hn.data = charfreq[10]; 
        hn.left = null; 
        hn.right = null; 
        q.add(hn);

        //Create a root node 
        HuffmanNode root = null; 
        
        //Find the two min values from the heap until the size is less than 1  
        while (q.size() > 1) { 
  
            HuffmanNode x = q.peek(); 
            q.poll(); 
  
            HuffmanNode y = q.peek(); 
            q.poll(); 
  
            //Create a new node f 
            HuffmanNode f = new HuffmanNode(); 
  
            //Sum the two freq and put the result in the new node 
            f.data = x.data + y.data; 
            f.c = '-'; 
  
            //Create tree by placing x and y as leaves of f  
            f.left = x; 
            f.right = y; 
  
            //Now the f node as the root node of x and y. 
            root = f; 
  
            //Add node to priority-queue. 
            q.add(f); 
        } 

        //Print results to file 
        File outFile = new File("C:\\elec365workspace\\assignment4\\outputCC3.txt");
		//Create file if it does not exist 
        if(!outFile.exists()) {
			outFile.createNewFile();
		}
        FileWriter filewrite = new FileWriter(outFile);
        BufferedWriter write = new BufferedWriter(filewrite);
        //Use printCode to write the binary codes 
        printCode(root, "", write); 
        write.close();
    }//end of HuffmanCode 
		
}