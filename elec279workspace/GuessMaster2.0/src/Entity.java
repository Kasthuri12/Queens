/**
 * @author Kasthuri Thambipillai
 * @StudentNumber 2009146
 */

public abstract class Entity {
	private String name;
	private Date born;
	private double difficulty;

	// abstract methods -- will be used in the preceding classes
	public abstract String entityType();

	public abstract Entity clone();

	//default constructor
	public Entity() {
		name = "Jane";
	}

	//copy constructor
	public Entity(double difficulty) {
		this.difficulty = difficulty;
	}

	//copy constructor
	public Entity(String name, Date birthDate, double difficulty) {
		this.name = name;
		this.born = new Date(birthDate); // no privacy leak
		this.difficulty = difficulty;
	}

	public Entity(Entity entity) {
		this.name = entity.name;
		this.born = new Date(entity.born); // no privacy leak
		this.difficulty = entity.difficulty;
	}

	//assessor method
	public String getName() {
		return name;
	}

	//assessor method
	public Date getBorn() {
		return new Date(born);
	}

	//assessor method
	public double getDifficulty() {
		return difficulty;
	}

	//assessor method -- to calculate the number of tickets awarded to the user
	// during a winning round
	public int getAwardedTicketNumber() {
		int numTickets = (int) (difficulty * 100);
		return numTickets;
	}

	//the base class toString method that contain the standard entity details
	public String toString() {
		return "Name: " + name + "\n" + "Born at: " + born.toString() + "\n";
	}

	//method welcoming the user to the Guess Master Game
	public String welcomeMessage() {
		return "Welcome! Let's start the game! This entity is a " + entityType() + "\n";
	}

	//method prompting the winning output message
	public String closingMessage() {
		return "Congradulations! The detailed information of the entity you guessed is:" + "\n" + toString();
	}

}
