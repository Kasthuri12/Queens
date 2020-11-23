/**
 * @author Kasthuri Thambipillai
 * @StudentNumber 2009146
 */

public class Country extends Entity {

	private String capital;

	// copy constructor -- using super() to call instance variables from the base
	// class
	public Country(String name, Date birthDate, String capital, double diffculty) {
		super(name, birthDate, diffculty);
		this.capital = capital;
	}
	


	// clone() constructor previously defined as a abstract method in the Entity
	// class
	public Country clone() {
		Country clone = new Country(this.getName(), this.getBorn(), capital, this.getDifficulty());
		return clone;
	}

	// using the clone() method to copy a variable of class type Country
	public Country copy(Country cloneCountry) {
		return cloneCountry.clone();
	}

	// defining a method that was invoked as as abstract in the class Entity
	public String entityType() {
		return "This entity is a Country!";
	}


	
	// uses super to call the toString() method for Entity and prints the capital
	// variable
	public String toString() {
		return super.toString() + "Capital: " + capital + "\n \n";

	}
}
	