/****
 * Kasthuri Thambipillai
 * 20059146
 * Assignment 3 (Part 2) 
 */
package assignment3;

public class Count {
	
	private int count;
	
	//Empty Constructor 
	public Count() {
		this.count=0;
	}
	
	//getCount Method 
	public int getCount() {
		return this.count; 
	}
	
	//addCount Method -- incremented the counter for HS
	public int addCount () {
		return this.count++;
	}
	
	//addToCount Method -- incremented the counter for HS by a specfic value
	public void addToCount(int value) {
		this.count += value;
	}

}
