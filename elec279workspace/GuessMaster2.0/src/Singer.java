/**
 * @author Kasthuri Thambipillai
 * @StudentNumber 2009146
 */

public class Singer extends Person {
	private String debutAlbum;
	private Date debutAlbumReleaseDate;

	// copy constructor -- using super to call instance variables of inherited
	// classes
	public Singer(String name, Date birthDate, double diffculty, String gender, String debutAlbum,
			Date debutAlbumReleaseDate) {
		super(name, birthDate, gender, diffculty);
		this.debutAlbum = debutAlbum;
		this.debutAlbumReleaseDate = debutAlbumReleaseDate;
	}

	// clone() method previously defined as an abstract method in entity
	public Singer clone() {
		Singer clone = new Singer(this.getName(), this.getBorn(), this.getDifficulty(), this.getGender(), debutAlbum,
				debutAlbumReleaseDate);
		return clone;
	}

	// using the clone() method to copy the variable of class type Singer
	public Singer copy(Singer cloneSinger) {
		return cloneSinger.clone();
	}

	// method that prints the entityType previously defined as an abstract in entity
	public String entityType() {
		return "This entity is a Singer!";

	}

	// method that prints the toString() method from the base class and the instance
	// variables of this class
	public String toString() {
		return super.toString() + "Debut Album: " + debutAlbum + "\n" + "Release Date: " + debutAlbumReleaseDate
				+ "\n\n";

	}
}
