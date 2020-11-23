/***
 * Author: Kasthuri Thambipillai 
 * Student Number: 20059146 
 * My path: 46 to 91 
 * I certify that this submission contains my own work, except as noted.  
 */
package assignment2;

import java.io.*;
import java.util.Scanner;

public class connectingFlight {
	public static void main(String[] args) throws Exception 
	{
		//User Inputs for file, start location, and final destination 
		Scanner myObj = new Scanner (System.in);
		System.out.println("File Path:");
		File file = new File(myObj.nextLine());
		
		Scanner myStart = new Scanner (System.in);
		System.out.println("Enter starting city:");
		int start = myStart.nextInt();
		Scanner myFinal = new Scanner (System.in);
		System.out.println("Enter destination city:");
		int finalDestination = myFinal.nextInt(); 
		
		//Find the number of of possible paths 
		Scanner read = new Scanner(file);
		int numPaths = 0;
		while (read.hasNextInt()!= false) {
			int temp= read.nextInt();
			numPaths++;
		  }
		numPaths= numPaths/4; 
		read.close();
		
		
		//Fill in the graph matrix with the paths from the text file  
		Scanner sc = new Scanner(file);
		//Identify the number of cities 
		int numCities = sc.nextInt();
		int [][] graph= new int [numPaths][4];
		for (int i =0; i< numPaths; i++) {
			for(int j=0; j<4; j++) {
				graph [i][j] = sc.nextInt();
				}
			}
		

		//Start of connecting path algorithm 
		
		//Declarations
		int cost[] = new int [numCities]; 
		int estimateArrivalWeight [] = new int [numCities];
		int previousDeparture [][] = new int [numCities][4];
		boolean reached[] = new boolean [numCities]; 
		int connections [][]= new int [numCities][2];
		
		cost[0] = 0;
		int destination = start;
		int plane = finalDestination; 
		int count = 0;
		
		
		//Initializing all paths as NOT reached and with a high Arrival time 
		for(int i=0; i<numCities; i++) {
			reached[i]= false;  
			estimateArrivalWeight[i]=10000000;
			
			//Initialize the empty departure array with -1 
			for (int j = 0; j < 4; j++) {
			previousDeparture[i][j] = -1;
			}
		}
		
		//At starting location initialize arrival and departure with 0
		previousDeparture[start][2]= 0;
		previousDeparture[start][3]= 0;
		estimateArrivalWeight[start]=0;
			
		
		while (destination != finalDestination) {
			int best_estimateArrival= 10000000;
			
			//Loop through all possible cities 
			for (int i =0; i<numCities; i++) {
				//find a city that has not been visited yet AND has the smallest arrival time  
				if ((reached[i]== false) && (estimateArrivalWeight[i] < best_estimateArrival)){	
						best_estimateArrival = estimateArrivalWeight[i];
						destination = i;
					}
			}

			//Mark that destination as reached so that it is not visited again
			reached[destination]= true; 
			cost[destination]= estimateArrivalWeight[destination];
			
			//Loop through potential paths and find ones that meet the constraints  
			for (int i =0; i<numPaths; i++) {
				if ((graph[i][0] == destination  && reached[graph[i][1]] == false && previousDeparture [graph[i][0]][3]< graph[i][2] && estimateArrivalWeight[graph[i][1]]> graph[i][3] )) {
					estimateArrivalWeight[graph[i][1]] = graph[i][3]; 
					for (int j=0; j< 4 ; j++) {
						previousDeparture[graph[i][1]][j] = graph[i][j];
					}
				}
			}
		}
		
		//Print Outcomes 
		System.out.print("Optimal route from " + start + " to " + finalDestination + ": \n \n");
		count =0;
		
		if (reached[finalDestination]= false) {
			System.out.print("No optimal route!");
		}
		
		//Print shortest path 
		else {
			while (plane != start) {
				for (int i =0; i <2 ; i++) {
					connections [count][i]= previousDeparture[plane][i];
				}
				plane = previousDeparture[plane][0];
				count ++;
			}
			for (int i = count-1; i>= 0 ; i--) {
				System.out.print("Fly from " + connections[i][0] + " to " + connections[i][1] + "\n");
			}
			
			System.out.print("\nArrive at " + finalDestination + " at time " + previousDeparture[finalDestination][3]);
			}
		}	
	}

