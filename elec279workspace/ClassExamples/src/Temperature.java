import java.util.Scanner;
public class Temperature {  // this is the class name 
	private double degrees; // private declaration can not be accessed outside the class 
	public Temperature() {
		degrees = 0; 
	}
	
	public Temperature (double initialDegrees) { //object 
		degrees = initialDegrees; 
	}
	
	public void setDegrees (double newDegrees ) { // object 
		degrees = newDegrees; 
	}
	
	public double getDegrees() { // object 
		return degrees; 
	}
	
	public String toString() { // method 
		return (degrees +"C"); 
	}
	
	public boolean equals (Temperature otherTemperature){
		return (degrees == otherTemperature.degrees); 
	}
	
	public static double toCelsius(double degreesF) {  // static method 
		return 5*(degreesF-32)/9; 
	}
	public static void main (String [] args) {
		double degreesF, degreesC; 
		
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Enter degreed Fahrenheit:");
		degreesF = keyboard.nextDouble(); 
		degreesC = toCelsius(degreesF);  // b/c this is in the definition of the class temp. this is equiv. to Temperature.toCelsius (degreeF)
		Temperature temperatureObject = new Temperature(degreesC);
		System.out.println("Equivalent Celsius temperature is" + temperatureObject.toString()); // b/c main is a staic method, toString must have a specified calling object like temperatureObject  
	
	}
}