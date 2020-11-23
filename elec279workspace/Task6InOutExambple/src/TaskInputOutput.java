import java.util.Scanner;
public class TaskInputOutput {
public static void main(String args[]) {
	int sum, product, x , y;
	
	Scanner in = new Scanner(System.in);
	
	x = in.nextInt();
	y = in.nextInt(); 
	
	sum = x + y; 
	product = x * y;
	
	System.out.println("the sum of the intergers = " +sum);
	System.out.println("the product of the intergers = " +product);
}

}
