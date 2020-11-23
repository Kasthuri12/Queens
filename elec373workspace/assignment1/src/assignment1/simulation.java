/*
 * ELEC 373 Assignmnet 1 
 * Kasthuri Thambipillai 
 * 20059146 
 * Due: February 10, 2020
 */
package assignment1;

import java.util.*;
import java.util.Random;

public class simulation {
	
	/***
	 * This code calculates the average queue delay against 8 different lambda values through a simulation that runs 10000000.
	 * Within the simulation we randomly create a(k) and d(k) rates and compare them against our declared lambda and mu values and 
	 * reduce or increase our queue length accordingly and finally, find the average.
	 */

	public static void main(String[] args) {
		//Declaration of given values
		double [] lambda = {0.2, 0.4, 0.5, 0.6, 0.7, 0.75, 0.79, 0.795};
		double mu = 0.8; 
		
		//Declaration of to be calculated values 
		double [] qLengthAvg= new double [8];
		double [] qDelayAvg= new double [8];
		double currDelay= 0;

		for (int i =0; i <8; i++) {
			currDelay= calcResult(lambda, qLengthAvg, qDelayAvg, i, mu);
		}
		
		/*****Printing Option 1: Results to transfer to a  plot *****/
		System.out.println("Plot:");
		for (int i =0; i <8; i++) {
			System.out.println(lambda [i]+ "\t" + qDelayAvg[i]);
		}
		/*******Printing Option 2: Printing all unknown values*******/
		System.out.println("\nFinal Answer:");
		for (int i =0; i <8; i++) {
			System.out.println("Lambda Value: "+lambda [i]+ "\n\tAverage Queue Length: "+ qLengthAvg[i] + "\n\tAverage Queue Delay: " + qDelayAvg[i]);
		}
} 
	/********************************** CALCRESULT FUNCTION **************************************/
	public static double  calcResult (double lambda[], double [] qLengthAvg, double [] qDelayAvg, int i, double mu) {
		//Declaration 
		double qLengthCurr = 0;  
		double a, d; 
		double sum =0; 
		
		//Simulation 10^6 Times 
		for (int j =0; j < 10000000; j++) { 
			//generate random a(k)
			Random randA = new Random(); 
			a = randA.nextDouble();
			
			if (a <= lambda[i]) { 
				qLengthCurr = qLengthCurr+1;
			}
			
			//generate random d(k)
			Random randD = new Random(); 
			d = randD.nextDouble();
			
			if ((d < mu) && qLengthCurr >0) {
				qLengthCurr = qLengthCurr-1; 
			}
			sum= sum + qLengthCurr; 
		}
		
		//Calculate average queue length and wait time 
		qLengthAvg[i]= sum/10000000;
		qDelayAvg[i] = qLengthAvg[i]/ lambda[i];
		
		return qDelayAvg[i];
		
	}
}