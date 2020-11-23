/****
 * Kasthuri Thambipillai
 * 20059146
 * Assignment 3 (Part 2) 
 */
package assignment3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.lang.Math.*;

public class SubsetSumPart2 extends Set {
	/****************** MAIN *****************/
	public static void main(String[] args) {
		ArrayList<Integer> elements = new ArrayList<Integer>();
		elements.add(10);
		elements.add(20);
		elements.add(30);
		int sum = 60;
		
		//Variables used for calculating averages  
		int BFIcounter = 0; 
		int HScounter= 0;
		int BFIaverageSet = 0;
		int HSaverageSet = 0;
		int BFIaverageN = 0;
		int HSaverageN = 0;
		
		/****************** EXPERIRMENT *****************/
		Random rand = new Random();
		for(int n=4; n<15; n++) {
		
			for(int i =1; i<20; i++) {
				BFIcounter= 0;
				HScounter = 0;
				ArrayList<Integer> randElements = new ArrayList<Integer>();
				for (int j=0; j<n; j++) {
					randElements.add(rand.nextInt(1000));
				}
				
				ArrayList<Integer> targetValues = new ArrayList<Integer>();
				for (int k = 0; k<11; k++) {
					targetValues.add(rand.nextInt(1000));
				}
				
				Set randTest = new Set(randElements);
				Set randTarget = new Set(targetValues);
				
				for (int k : randTarget.elements) {
					BFIcounter += BFISubsetSum(randTest,k);
					HScounter += HSSubsetSum(randTest, k); 
				}
				BFIaverageSet += BFIcounter/(randTarget.sizeOf());
				HSaverageSet += HScounter/(randTarget.sizeOf());
			}
			BFIaverageN = BFIaverageSet/20;
			HSaverageN = HSaverageSet/20;
			System.out.println(n + " \t\t\t   "+ BFIaverageN + "   \t\t\t   " + HSaverageN);
			
		}//end of experiment 
}//end of main 
	
	
	/****************** BFI SUBSET SUM ALGORITHM *****************/	
	public static int  BFISubsetSum(Set test , int target) {
		int BFIcounter = 0;
		int n = test.elements.size();
		
		ArrayList<Set> subsets = new ArrayList<Set>();
		
		subsets.add(new Set());
		BFIcounter++;
		
		for (int i =0; i <n; i++) {
			ArrayList<Set> new_subsets = new ArrayList<Set>();
			BFIcounter++;
			
			for(int j=0; j<subsets.size(); j++) {
				Set old_u = subsets.get(j);
				ArrayList<Integer> newElement = old_u.getElements();
				newElement.add(test.elements.get(i)); 
				BFIcounter++;
				int newSum = old_u.getSum() + test.elements.get(i);
				BFIcounter++;
				Set new_u = new Set(newElement, newSum);
				
				
				if(new_u.sum == target) {
					BFIcounter++;
					return BFIcounter;
				}
				else {
					new_subsets.add(old_u);
					BFIcounter++;
					new_subsets.add(new_u);
					BFIcounter++;
				}
				
				BFIcounter++;
			}
			subsets = (ArrayList<Set>) new_subsets.clone();
		}
			return BFIcounter;
	}//end of BFI Subset Sum Algorithm 
	

	
	/****************** HS SUBSET SUM ALGOIRTHM *****************/
	public static int  HSSubsetSum(Set test , int target) {
		Count HScounter = new Count();
		int n = test.elements.size();
		int midIndex = n/2;
		Set set_left = new Set();
		Set set_right = new Set();
		
		if (midIndex!= 0) {
			HScounter.addCount();
			set_left.elements = new ArrayList<Integer>(test.elements.subList(0, midIndex+1));
		}
		else {
			set_left.elements= new ArrayList<Integer>();
			set_left.elements.add(test.elements.get(0));
			HScounter.addCount();
			}
		set_left.sumFinder();
		
		if((midIndex+1)!=n) {
			HScounter.addCount();
			set_right.elements = new ArrayList<Integer>(test.elements.subList(midIndex+1, n));
		}
		
		else {
			set_right.elements= new ArrayList<Integer>();
			set_right.elements.add(test.elements.get(midIndex+1));
			HScounter.addCount();
			}
	
		set_right.sumFinder();
		
		
		ArrayList<Set> subset_left = BFISubsetSumMod(set_left, HScounter);
		ArrayList<Set> subset_right = BFISubsetSumMod(set_right, HScounter);
		ArrayList<Integer> sumLeft = new ArrayList<Integer>(); 
		ArrayList<Integer> sumRight = new ArrayList<Integer>();
		
		for (Set subset : subset_left) {
			if (subset.sum == target) {
				HScounter.addCount();
				return HScounter.getCount();
			}
			sumLeft.add(subset.sum);
			HScounter.addCount();
		}
		
		for (Set subset : subset_right) {
			if (subset.sum == target) {
				HScounter.addCount();
				return HScounter.getCount();
			}
			sumRight.add(subset.sum);
			HScounter.addCount();
		}
		
		Collections.sort(sumLeft);
		int nLeft = sumLeft.size();
		HScounter.addToCount((int)(3*nLeft*(Math.log(nLeft))));
		int nRight = sumRight.size();
		Collections.sort(sumRight);
		HScounter.addToCount((int)(3*nRight*(Math.log(nRight))));
		ArrayList<Integer> indexFound = new ArrayList <Integer>();
		indexFound = (ArrayList<Integer>) Pair_Sum(sumLeft,sumRight, target, HScounter);
		
		int left = indexFound.get(0);
		int right= indexFound.get(1);
		
		if(target == (left)  + (right)) {
			HScounter.addCount();
			for(Set set : subset_left) {
				if(set.getSum() == left) {
					HScounter.addCount();
					return HScounter.getCount();
				}
			}
			
			for(Set set : subset_right) {
				if(set.getSum() == right) {
					HScounter.addCount();
					return HScounter.getCount();
				}
			}
			
		}
		return HScounter.getCount();
	}//end of HS Subset SUn Algorithm 
	
	
	
	/****************** BFI SUBSET SUM MODIFIED *****************/	
	static ArrayList<Set> BFISubsetSumMod(Set test, Count count) {
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
				count.addCount();
				Set new_u = new Set(newElement, newSum);
				
				new_subsets.add(old_u);
				count.addCount();
				new_subsets.add(new_u);
				count.addCount();
			}
			subsets = (ArrayList<Set>) new_subsets.clone();
			count.addCount();
		}
		return subsets;
	}//end of BFI Subset Sum Modified 
	
	
	
	/****************** PAIR SUM  *****************/	
	public static ArrayList<Integer> Pair_Sum (ArrayList<Integer> v1, ArrayList<Integer> v2, int target, Count count){
		int p1 = 0; 
		int p2 = v2.size() -1;
		ArrayList<Integer> index = new ArrayList<Integer>();
		while((p1< v1.size()) && (p2> 0)) {
			int t = v1.get(p1) +v2.get(p2);
			if(t==target) {
				index.add(v1.get(p1));
				count.addCount();
				index.add(v2.get(p2));
				count.addCount();
				return index;
			}
			else if (t<target) {
				p1++;
				count.addCount();
			}
			else {
				p2--;
				count.addCount();
			}
		}
		index.add(-1);
		index.add(-1);
		return index;
	}//end of Pair Sum 

}
		



