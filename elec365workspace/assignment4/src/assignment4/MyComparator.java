/***
 * Kasthuri Thambipillai 
 * 20059146
 * Assignment 3 
 */

package assignment4;
import java.util.*; 

class MyComparator implements Comparator<HuffmanNode> { 
    public int compare(HuffmanNode x, HuffmanNode y) 
    { 
    	//The following code for the HuffmanCode Function was built based of the following reference 
    	/**
    	 * Author: Aashish Barnwal 
    	 * https://www.geeksforgeeks.org/huffman-coding-greedy-algo-3/
    	 */
    	//This class aid in building the priority queue in the HuffmanCode function 
        return x.data - y.data; 
    } 
} 
