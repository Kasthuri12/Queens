package assingment1;
import java.io.File;
import java.util.Scanner;
public class dijkstra {
	public static void main(String[] args) throws Exception 
	{
	//Read file from txt 
		File file = new File("C:\\Users\\Kasthuri\\Desktop\\Dijkstra_Data_6.txt");
		Scanner sc = new Scanner(file);
		int numVert = sc.nextInt();
		int [][] graph = new int [numVert][numVert];
		for (int i =0; i<numVert; i++) {
			for(int j=0; j< numVert; j++) {
				graph [j][i] = sc.nextInt();
			}
		}
		
		for (int i =0; i<numVert; i++) {
			for(int j=0; j< numVert; j++) {
				//System.out.println(graph [j][i]);
			}
		}
		
		int cost[] = new int [numVert]; 
		int est[] = new int [numVert];
		boolean reached[] = new boolean [numVert]; 
		boolean cand [] = new boolean [numVert];
		int best = 0;
		
		cost[0]=0;
		cand[0]= false; 
		
		for(int i=1; i<numVert; i++) {
			reached[i]= false; 
		}
		
		//for each neighbour x of A
		for (int i =1; i<numVert; i++) {
			if (graph [0][i] > 0) {
				cand[i]= true;
				est[i]= graph[0][i];
			}
			else { 
				cand[i]= false; 
				est[i]= 100000000; 
			}
			//System.out.println(cand[i]);
		}
		
			
		int count=1;
		boolean done= false;
		while (!done) {
			int best_cand= 10000000;
			for (int i =0; i<numVert; i++) {
				if ((cand[i]== true) && (est[i] < best_cand)) {
					best= i; 
					best_cand = est[i];
				}
			}
			
			cost[best] = est[best];
			reached[best]= true; 
			cand[best]=false;
			
			for (int i =0; i<numVert; i++) {
				if ((graph[best][i]>0 && reached[i] ==false)) {
					if (cost[best] + graph[best][i]< est[i]) {
						est[i] = cost[best] + graph [best][i]; 
						cand[i]=true;
					}
				}
			}
			count++;
			System.out.println(count+ "   "+ best + " = " + cost[best]);
			if (count == numVert) {
				done = true;
			}
		}
	}
}
	
	

