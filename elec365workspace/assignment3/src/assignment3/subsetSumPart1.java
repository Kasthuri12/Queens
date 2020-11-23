/****
 * Kasthuri Thambipillai
 * 20059146
 * Assignment 3 (Part 1) 
 */
package assignment3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.lang.Math.*;

public class subsetSumPart1 extends Set {
	
	/********* MAIN ********/
	public static void main(String[] args) {
		//Create the test list of elements {10,20,30}
		ArrayList<Integer> elements = new ArrayList<Integer>();
		elements.add(10);
		elements.add(20);
		elements.add(30);
		int sum = 60;
		Set test = new Set(elements, sum);
		
		//Target value can be adjusted here 
		int target = 30;

		//Calling BFI and HS using the test case -- test one at a time
		//BFISubsetSum(test, target);
		HSSubsetSum(test, target);
				
		} //end of main 	
	
	

	/*********BFI SUBSET SUM ALGORITHM********/
	public static void  BFISubsetSum(Set test , int target) {
		int n = test.elements.size();
		
		ArrayList<Set> subsets = new ArrayList<Set>();
		
		//adding an empty set to subsets 
		subsets.add(new Set());
		
		//loop through the number of elements in test 
		for (int i =0; i <n; i++) {
			ArrayList<Set> new_subsets = new ArrayList<Set>();
			
			//loop through the number of subsets 
			for(int j=0; j<subsets.size(); j++) {
				Set old_u = subsets.get(j);
				ArrayList<Integer> newElement = old_u.getElements();
				
				//append Si to old_u.elements 
				newElement.add(test.elements.get(i)); 
				
				//add Si to old_u.sum 
				int newSum = old_u.getSum() + test.elements.get(i);
				Set new_u = new Set(newElement, newSum);
				
				//check if the sum is equal to target, if so STOP and print solution
				if(new_u.sum == target) {
					System.out.println("Sum found (using BFI algorithm): \n\n\t"+ new_u.toString());
					System.exit(0);
				}
				else {
					new_subsets.add(old_u);
					new_subsets.add(new_u);
				}
			}
			//Use clone method from class sets to make subsets = new_subsets 
			subsets = (ArrayList<Set>) new_subsets.clone();
		}
			//No solution found 
			System.out.print("No subset sums equal to the target value");
			System.exit(0);
			
	}//end of BFI algorithm 
	
	
	
	
	/*********HS SUBSET SUM ALGORITHM********/
	public static void  HSSubsetSum(Set test , int target) {
		int n = test.elements.size();
		int midIndex = n/2;
		Set set_left = new Set();
		Set set_right = new Set();
		
		//Divide test set into a left set using the sublist function 
		if (midIndex!= 0) {
			set_left.elements = new ArrayList<Integer>(test.elements.subList(0, midIndex+1));
		}
		else {
			set_left.elements= new ArrayList<Integer>();
			set_left.elements.add(test.elements.get(0));
			}
		//find the sum of the left set using the method sumFInder in the class Set  
		set_left.sumFinder();
		
		//Divide test set into a right set using the sublist function 
		if((midIndex+1)!=n) {
			set_right.elements = new ArrayList<Integer>(test.elements.subList(midIndex+1, n));
		}
		else {
			set_right.elements= new ArrayList<Integer>();
			set_right.elements.add(test.elements.get(midIndex+1));
			}
		//find the sum of the right set using the method sumFInder in the class Set 
		set_right.sumFinder();
		
		//Apply a modified BFI algorithm on both sets to get all the subsets and thier sums
		ArrayList<Set> subset_left = BFISubsetSumMod(set_left);
		ArrayList<Set> subset_right = BFISubsetSumMod(set_right);
		
		ArrayList<Integer> sumLeft = new ArrayList<Integer>(); 
		ArrayList<Integer> sumRight = new ArrayList<Integer>();
		
		//loop through all the subsets on the left 
		for (Set subset : subset_left) {
			//Check the subset sums to see if they equal target 
			if (subset.sum == target) {
				System.out.println("Sum found (using HS algorithm): \n\n\t" + subset.toString());
				System.exit(0);
			}
			sumLeft.add(subset.sum);
		}
		
		//loop through all the subsets on the right
		for (Set subset : subset_right) {
			//Check the subset sums to see if they equal target 
			if (subset.sum == target) {
				System.out.println("Sum found (using HS algorithm): \n\n\t" + subset.toString());
				System.exit(0);
			}
			sumRight.add(subset.sum);
		}
		
		//If nothing is found sort the right and left sums 
		Collections.sort(sumLeft);
		Collections.sort(sumRight);
		ArrayList<Integer> indexFound = new ArrayList <Integer>();
		//Call Pair Sum on right and left sum 
		indexFound = (ArrayList<Integer>) Pair_Sum(sumLeft,sumRight, target);
		
		int left = indexFound.get(0);
		int right= indexFound.get(1);
		
		//Check if the sum is found from Pair Sum 
		if(target == (left)  + (right)) {
			//Will be used for printing results 
			ArrayList<Integer> targetFound = new ArrayList <Integer>();
			int targetSumFound= 0;
			
			//Go through left sets to find the element that correspond to the sum 
			for(Set set : subset_left) {
				if(set.getSum() == left) {
					targetFound.addAll(set.elements);
					targetSumFound += set.sum;
				}
			}
			
			//Go through right sets to find the element that correspond to the sum
			for(Set set : subset_right) {
				if(set.getSum() == right) {
					targetFound.addAll(set.elements);
					targetSumFound += set.sum;
				}
			}
			System.out.println("Sum found (using HS algorithm): \n\n\t" + "Elements: " + targetFound +" Sum: "+ targetSumFound);
		}
		System.out.print("No subset sums equal to the target value");
	}//end of HS algorithm
		
	
	
	
	/*********BFI SUBSET SUM MODIFIED ALGORITHM********/
	static ArrayList<Set> BFISubsetSumMod(Set test) {
		//This function is similar to the BFISubsetSum Function
		//The goal of the modified BFI is just to create all the subset for your right and left sets 
		int n = test.elements.size();
		ArrayList<Set> subsets = new ArrayList<Set>();
		
		subsets.add(new Set());
		
		for (int i =0; i <n; i++) {
			ArrayList<Set> new_subsets = new ArrayList<Set>();
			
			for(int j=0; j<subsets.size(); j++) {
				Set old_u = subsets.get(j);
				ArrayList<Integer> newElement = old_u.getElements();
				newElement.add(test.elements.get(i)); 
				
				int newSum = old_u.getSum() + test.elements.get(i);
				Set new_u = new Set(newElement, newSum);
				
				new_subsets.add(old_u);
				new_subsets.add(new_u);
			}
			subsets = (ArrayList<Set>) new_subsets.clone();
		}
		return subsets;
	}//end of BFI Subset Sum Modified
		
	
	/*********PAIR SUM FUNCTION********/
	public static ArrayList<Integer> Pair_Sum (ArrayList<Integer> v1, ArrayList<Integer> v2, int target){
		//The goal of Pair Sum is to find the indices of the right and left subsets that equal target 
		int p1 = 0; 
		int p2 = v2.size() -1;
		ArrayList<Integer> index = new ArrayList<Integer>();
		while((p1< v1.size()) && (p2> 0)) {
			int t = v1.get(p1) +v2.get(p2);
			if(t==target) {
				//if these index values are found add the to an ArrayList that will be returned 
				index.add(v1.get(p1));
				index.add(v2.get(p2));
				return index;
			}
			else if (t<target) {
				p1++;
			}
			else {
				p2--;
			}
		}
		//If not found input default indices 
		index.add(-1);
		index.add(-1);
		return index;
	}//end of Pair Sum

}
		



