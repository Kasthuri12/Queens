/****
 * Kasthuri Thambipillai
 * 20059146
 * Assignment 3 (Part 1 & 2 ) 
 */
package assignment3;
import java.io.*;
import java.util.ArrayList; 

public class Set {
	
	public ArrayList<Integer> elements;
	public int sum; 
	
	//Empty Constructor  
	public Set(){
		this.elements = new ArrayList<Integer>();
		this.sum= 0;			
	}
	
	//2 parameter Constructor  
	public Set(ArrayList<Integer> elements, int sum){
		this.elements = (ArrayList<Integer>)elements.clone();
		this.sum= sum;			
	}
	
	//1 parameter Constructor  
	public Set(ArrayList<Integer> elements){
		this.elements = (ArrayList<Integer>)elements.clone();
		this.sum= 0;			
	}
	
	//getElements Method 
	public ArrayList<Integer> getElements() {
		return (ArrayList<Integer>)elements.clone(); 
	}
	
	//getSum Method 
	public int getSum () {
		return sum; 
	}
	
	//SumFinder Method 
	public int sumFinder() {
		int counter = 0; 
		if(this.elements.size()<1) {
			this.sum=0;
		}
		else {
			this.sum=0;
			for (int i=0; i<elements.size(); i++) {
				this.sum= this.sum + this.elements.get(i);
				counter++;
			}
		}
		return counter;
	}
	
	//toString Method
	public String toString() {
		return ("Elements:" + elements.toString() + " Sum: " + sum + "\n");
	}
	
	//sizeOf Method  
	public int sizeOf() {
		return elements.size();
	}

}
