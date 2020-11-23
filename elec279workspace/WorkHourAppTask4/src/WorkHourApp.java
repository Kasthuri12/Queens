import java.util.Scanner;
public class WorkHourApp {
public static void main(String args[]) {
	int hoursperday, numdays, total;
	System.out.println("Please enter a value for Hours per day and Number of Days");
	Scanner in = new Scanner(System.in);
	
	hoursperday = in.nextInt();
	numdays = in.nextInt(); 
	
	for(int n = 1; n<= numdays; n++) {
		total= hoursperday*n;
		System.out.println("Total Hours worked=" +total);
	}
	
}

}
