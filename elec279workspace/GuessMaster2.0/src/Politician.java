/**
 * @author Kasthuri Thambipillai
 * @StudentNumber 2009146
 */

public class Politician extends Person {

	private String party;

	// copy constructor -- using super to call the instance variables of the
	// inherited classes
	public Politician(String name, Date birthDate, double diffculty, String gender, String party) {
		super(name, birthDate, gender, diffculty);
		this.party = party;
	}

	// clone() method previously defined as an abstract in base class
	public Politician clone() {
		Politician clone = new Politician(this.getName(), this.getBorn(), this.getDifficulty(), this.getGender(),
				party);
		return clone;
	}

	// using the clone() method to copy a variable of class type Politician
	public Politician copy(Politician clonePolitician) {
		return clonePolitician.clone();
	}

	// method denoting the entity type an abstract method in the base class
	public String entityType() {
		return "This entity is a Politician!";
	}

	// method call the toString method from the base class and printing the party
	// variable
	public String toString() {
		return super.toString() + "Party: " + party + "\n";

	}
}
