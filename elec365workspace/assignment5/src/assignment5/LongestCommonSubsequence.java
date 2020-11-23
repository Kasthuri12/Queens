/***
 * Kasthuri Thambipillai
 * 20059146
 * November 24th, 2019 
 */

package assignment5;

import java.io.*;
import java.util.*;

public class LongestCommonSubsequence {
	//C:\elec365workspace\assignment5\Data\Three_Bears.v1.txt
	//C:\elec365workspace\assignment5\Data\Dijkstra.py
	
	
	public static void main(String[] args) throws Exception 
	{
		//Ask user to input the first file
		Scanner fileA = new Scanner (System.in);
		System.out.println("File Path:");
		File file = new File(fileA.nextLine());
		Scanner file1 = new Scanner (file);
		
		//Ask user for the second file 
		Scanner fileB = new Scanner (System.in);
		System.out.println("File Path:");
		File fFile = new File(fileB.nextLine());
		Scanner file2 = new Scanner (fFile);
		
		//Create two ArrayLists to hold the list of strings from each file 
		ArrayList <String> arrayFile1 = new <String>ArrayList(); 
		ArrayList <String> arrayFile2 = new <String>ArrayList(); 

		//Go through file 1 and read every line and input it into its respective ArrayList 
		while(true) {
			if (!file1.hasNextLine()) {
				break; 
			}
			String file1Line = file1.nextLine();
			
			arrayFile1.add(file1Line);

		}//end of while
		
		//Go through file 2 and read every line and input it into its respective ArrayList 
		while (true) {
			if (!file2.hasNextLine()) {
				break; 
			}
			String file2Line = file2.nextLine();

			arrayFile2.add(file2Line); 
			
		}//end of while


		
		//Declare the table that will identify if there is a match 
		int [][]lookupTable = new int [arrayFile1.size()+1][arrayFile2.size()+1];
		
		
		//fill in lookupTable using the f1 and g1 functions to identify matches 
		for (int i=1; i<=arrayFile2.size(); i++) {
			for (int j= 1; j<=arrayFile1.size(); j++) {
				
				int file1Sum = (int)f1(arrayFile1.get(j-1)); 
				int file2Sum = (int)f1(arrayFile2.get(i-1)); 
				int file1Result =(int) g2(arrayFile1.get(j-1)); 
				int file2Result= (int)g2(arrayFile2.get(i-1)); 
				
				
				if ((file1Sum != file2Sum) || (file1Result != file2Result)){
					lookupTable[j][i]= noMatch(lookupTable, j, i);
				}
				else {
					for (int k =0; k< arrayFile1.get(j-1).length(); k++) {
						if(arrayFile1.get(j-1).charAt(k) !=arrayFile2.get(i-1).charAt(k)) {
							//System.out.println(" Not Equal 2");
						}
						//System.out.println("Equal");
					}
					lookupTable[j][i]= match(lookupTable, j, i);
				}
			}
		}	
		createFlag(lookupTable, arrayFile1, arrayFile2);
	}//end of main
	
	
	/***************MATCH****************/
	public static int match(int [][]lookupTable, int file1Line, int file2Line) { 
		//Base case: if the first box is a match 
		if ((file2Line == 1) && (file1Line ==1)) {
			lookupTable[1][1]=1;
		}
		//Base case: if first row is a match 
		else if(file2Line == 1){
			lookupTable[file1Line][1]= lookupTable[file1Line-1][1] +1 ;
		}
		//Base case: if first col is a match 
		else if(file1Line == 1){
			lookupTable[1][file2Line]= lookupTable[1][file2Line-1] +1 ;
		}
		//when there is a match you want to add to the one that is diagonal from the current 
		else {
		lookupTable[file1Line][file2Line]= lookupTable[file1Line-1][file2Line-1] +1;
		}
		return lookupTable[file1Line][file2Line];
	}
	
	/***************NOMATCH***************/
	
	public static int noMatch(int [][]lookupTable, int file1Line, int file2Line) {
		//Base Case: If first row is no match then take the number before 
		if(file2Line == 1){
			lookupTable[file1Line][1]= lookupTable[file1Line-1][1];
		}
		//Base Case: If first col is no match then take the number before 
		else if(file1Line == 1){
			lookupTable[1][file2Line]= lookupTable[1][file2Line-1];
		}
		//when no match take the max number of the one above or beside 
		else {
		lookupTable[file1Line][file2Line]= Math.max(lookupTable[file1Line-1][file2Line], lookupTable[file1Line][file2Line-1]);
		}
		return lookupTable[file1Line][file2Line];
	}
	
	
	/****************** FUNCTION ONE ****************/
	public static int f1(String file1) {
		//Sums the ACSII values of the string 
		int sum = 0; 

		for (int i=0; i< file1.length(); i++) {
			sum += (int)file1.charAt(i);
		}
		return sum;
	}
	
	/****************** FUNCTION TWO ****************/
	public static int g2(String file2) {
		//uses a hash function 
		int result = 0; 

		for(int i =0; i <file2.length(); i++) {
			result =((7 *result) + ((int)file2.charAt(i)))%100000;
		}
		return result;
	}
	/************************CREATEFLAG***********************/
	public static int[][] createFlag(int [][] lookupTable, ArrayList <String> arrayFile1, ArrayList <String> arrayFile2 ) {
		int col = arrayFile2.size();
		int row = arrayFile1.size();

		//Declare a flag array to find the match and non-matching lines 
		int [][] flag= new int [arrayFile1.size()+1][arrayFile2.size()+1]; 
		while (true) {
			//Base Case: First row and col
			if ((row ==1) && (col ==1)) {
				//Match
				if (lookupTable[row][col]!= 0) {
					flag[row][col]= 1;
					break;
				}
				//Mismatch
				else {
					flag[row][col]= 0;
					break;
				}
			}
			//Base Case:When you are in row 1 
			else if (row==1) {
				//Mismatch
				if (lookupTable[row][col]== lookupTable[row][col-1]) {
					flag[row][col]= 0;
					col--;
				}
				//Match
				else {
					flag[row][col]= 1;
					col--;
					row--;
				}
			}
			//Base Case: When you are in col 1 
			else if (col==1) {
				//Mismatch
				if (lookupTable[row][col]== lookupTable[row-1][col]) {
					flag[row][col]= 0;
					row--;
				}
				//Match
				else {
					flag[row][col]= 1;
					row--;
					col--;
				}
			}
			
			//Mismatch:
			else if (lookupTable[row][col] == lookupTable[row-1][col]) {
				flag[row][col]= 0;
				row--;
				
			}
			
			//Mismatch:
			else if (lookupTable[row][col] == lookupTable[row][col-1]) {
				flag[row][col]= 0;
				col--;
			}
			//Match:
			else { 
				flag[row][col]= 1;
				row--;
				col--;
			}
		}
		

		//Create a match array the breaks down the flag 2D array into 2 single arrays 
		int maxSize= Math.max(arrayFile1.size(), arrayFile2.size());
		int []match1 = new int[maxSize+1];
		int []match2 = new int[maxSize+1];
		
		//Fill the two array by going through flag array 
		for(int i = 1; i<=arrayFile2.size(); i++) {
			for(int j = 1; j<=arrayFile1.size(); j++) {
				if(flag[j][i]==1) {
					match1[j]= 1; 
					match2[i]= 1;
				}
			}
		}
		

		//Declare file 1 and file 2 start and end counters 
		int end1 =1; 
		int end2= 1; 
		int start1 = 1;
		int start2= 1;
		
		//Create a flag variable 
		int matchFlag= 1;
		
		//Keep printing while end1 and end2 is less than maxSize
		while((end1<maxSize || end2 <maxSize)) {
			while(matchFlag ==1) {
				//If both arrays indicate a match 
				if (match1[start1]==1 && match2[start2]==1) {
					end1=start1;
					end2=start2;
					
					while((match1[end1]==1)) {
						if (match2[end2]==1) {
							end2++;
						}
						end1++;

						if (end1 >maxSize|| start2> maxSize) {
							break;
						}
					}
					if((end1-start1)>(end2-start2)+1) {
						end1=end2; 
						if(start1 > start2) {
							end1=start1+1;
						}
					}
					end1= end1-1;
					end2 = end2-1;
					System.out.println("\nMatch: \t\t File1: <" + start1 +".."+ end1+ "> \t\t File2: <" + start2 + ".."+ end2+ ">\n");
					start1= end1+1; 
					start2= end2+1;
				}
				
				//if only match1 array has a match 
				else if(match1[start1]==1) {
					end1=start1;
					end2=start2;

					while(match1[end1]==1) {// Case 1a:When those lines match 
						if (match2[end2]==1) {
							end2++;
							
						}
						end1++;
						
						//System.out.println(end1);
						if (end1 >maxSize|| start2> maxSize) {
							break;
						}
					}
					end1= end1-1;
					end2 = end2-1;
					System.out.println("\nMatch: \t\t File1: <" + start1 +".."+ end1+ "> \t\t File2: <" + start2 + ".."+ end2+ ">\n");
					start1= end1+1; 
					start2= end2+1;
				}

				break;
			}
			matchFlag=0;

			//Mismatch Print Statement 
			while(matchFlag==0) {
				end1=start1;
				end2=start2;
				if (end1==maxSize || end2== maxSize) {
					break;
				}
				//if one array have mismatches 
				if(match1[end1]==0 && match2[end2]!=0) {
					if (match2[start2]== 1) {
						end1++;
						
						//None cases 
						if(match1[end1]==0) {
							System.out.println("\nMismatch: \t File1: <" + start1 +".."+ (end1)+ "> \t\t File2: <" +  "None"+ ">\n");
							start1=start1+2;
						}
						else {
							System.out.println("\nMismatch: \t File1: <" + start1 +".."+ (end1-1)+ "> \t\t File2: <" +  "None"+ ">\n");
							start1=start1+1;
						}
					}
				}
				//if both arrays have mismatches 
				else if(match2[end2]==0 && match1[end1]!=0) {
					if (match1[start1]== 1) {
						end2++;
						
						//None
						if(match2[end2]==0) {
							System.out.println("\nMismatch: \t File1: <"  + "None"+ "> \t\t File2: <" + start2 +".."+ (end1) + ">\n");
							start2=start2+2;
						}
							else {
								System.out.println("\nMismatch: \t  File1: <" + "None"+"> \t\t File2: <" + start2 +".."+ (end1-2)+ ">\n");
								start2=start2+1;
							}
						}
					}
				
					//When only the second array Mismatches 
					else {
						while(match2[end2]==0) {
							if (match1[end1]==0) {
								end1++;
							}
							end2++; 
							if (end1==maxSize || end2== maxSize) {
								break;
							}
						}
						end1= end1-1;
						end2 = end2-1;
						System.out.println("\nMismatch: \t File1: <" + start1 +".."+ end1+ "> \t\t File2: <" + start2 + ".."+ end2+ ">\n");
						start1= end1+1; 
						start2= end2+1;
					}
				break;
			}
			matchFlag=1;
			
			if (end1==maxSize || end2== maxSize) {
				break;
			}
					
		}//end of while(true)
		
		//End printing case
		if(end2== maxSize) {
			System.out.println("\nMismatch: \t File1: <" + "None"+ "> \t\t\t File2: <" + end2 + ".."+ maxSize+ ">\n");
		}
		
		//End printing case
		if (end2<maxSize){
			System.out.println("\nMatch: \t\t File1: <" + "None"+ "> \t\t\t File2: <" + end2 + ".."+ maxSize+ ">\n");
		}
	
		return flag;
	}

}//end of program 
