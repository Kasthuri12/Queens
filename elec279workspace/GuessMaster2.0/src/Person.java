/**
 * @author Kasthuri Thambipillai
 * @StudentNumber 2009146
 */

public class Person extends Entity {

	private String gender;

	// copy constructor
	public Person(String gender) {
		this.gender = gender;
	}

	public Person(Person person) {
		this.gender = person.gender;
	}

	// copy constructor -- using super to call the instance variables from the
	// inherited classes
	public Person(String name, Date birthDate, String gender, double diffculty) {
		super(name, birthDate, diffculty);
		this.gender = gender;
	}

	// clone() method previously defined as an abstract in the base class Entity
	public Person clone() {
		Person clone = new Person(this.getName(), this.getBorn(), gender, this.getDifficulty());
		return clone;
	}

	// using the method clone() to copy the variable of class type Person
	public Person copy(Person clonePerson) {
		return clonePerson.clone();
	}

	// assessor method
	public String getGender() {
		return gender;
	}

	// method previously defined as an abstract in Entity
	public String entityType() {
		return "This entity is a Person!";
	}

	// method that prints the toString() method from the base class Entity and the
	// gender variable
	public String toString() {
		return super.toString() + "Gender: " + gender + "\n";
	}
}

