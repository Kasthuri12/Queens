/** 
 * ELEC 279 Assignment 1 
 * @author Kasthuri Thambipillai 
 * @studentNumber 20059146
 */

import java.util.Scanner;
import java.util.Random;

public class GuessMaster {
	//instance variables
	private int numberOfCandidateEntities;
	private Entity[] entities;

	//constructor to declare instance variables
	public GuessMaster() {
		numberOfCandidateEntities = 0;
		entities = new Entity[100];
	}

	//constructor for mutator to set instance variables
	public GuessMaster(int numCandidate, Entity[] entityIn) {
		setGuessMaster(numCandidate, entityIn);
	}

	// constructor for storing the input entity at the first index using the public
	// constructor in the entity class
	public GuessMaster(Entity entityIn) {
		this.numberOfCandidateEntities = 0;
		this.entities[numberOfCandidateEntities] = new Entity(entityIn.getName(), entityIn.getBorn());
	}

	//constructor to initializing the entity array and call mutator on the public
	//Guess Master constructor
	public GuessMaster(int numCandidate) {
		Entity[] entitiesIn = new Entity[numCandidate];
		setGuessMaster(numCandidate, entitiesIn);
	}

	//constructor to initialize entities array to zero at every index
	public void setGuessMaster(int numCandidate, Entity[] entityIn) {
		numberOfCandidateEntities = numCandidate;
		for (int i = 0; i < numCandidate; i++) {
			entities[i] = entityIn[0];
		}
	}

	//assessor method that assigns number of candidate entities
	public int getNumCandidates() {
		return numberOfCandidateEntities;
	}

	//assessor method for entity where you are declaring an empty array
	public Entity[] getEntities() { 
		Entity[] entityResult = new Entity [entities.length];
		for ( int i =0; i< entities.length; i++) {
			Entity copy = entities[i]; 
			if (copy != null) { 
				entityResult[i] = new Entity(copy);
			}
		}
		return entityResult;
	}
	
	//adds a new input entity into entities and increments index
	public void addEntity(Entity entityIn) {
		this.entities[numberOfCandidateEntities] = entityIn;
		this.numberOfCandidateEntities++;
	}

	public void playGame(Entity entity) {
		String entityInfo;
		System.out.println("Welcome to the Guess Master Game");
		System.out.println("This game was programmed by Kasthuri Thambipillai");
		System.out.println("If you wish to exit the game at any time please type \"quit\"");
		System.out.println("Guess the birthday of " + entity.getName());

		//loop takes in users answers from keyboard
		while (true) {
			boolean tryAgain = true;
			Scanner keyboard = new Scanner(System.in);

			//.next method return the next token from the keyboard
			while (tryAgain) {
				System.out.println("Enter your guess date using the format: mm/dd/yyyy");
				String input = keyboard.next();

				//if the next token in quit output message and exit
				if (input.equals("quit") || input.contentEquals("Quit")) {
					System.out.println("Thanks for plyaing the Guess Master Game");
					tryAgain = false;
					System.exit(0);
				}

				Date guessDate = new Date(input);

				// the date guessed by user is true, determined using .equals, output message
				// and prompt the next round
				if (guessDate.dateOK()) {
					if (guessDate.equals(entity.getBorn()) == true) {
						System.out.println("Bingo. YOu got it!! \n");
						entityInfo = entity.toString();
						System.out.println(entityInfo);
						entity = entities[genRandomEntityIndex()];
						System.out.println("Now guess the birthday of " + entity.getName());

					}

					// Using the precedes method from the date class check if the input is later
					// than the correct date
					else if (guessDate.precedes(entity.getBorn()) == true) {
						System.out.println("Incorrect! Try a later Date\n");
					}

					//if the program was not caught by the above cases, the guessed dateis than
					//earlier than the correct date
					else {
						System.out.println("Incorrect! Try a earlier Date\n");
					}
				} //end if (guessDate.dateOK())

				//if date inputed was not of the correct format the program will output this message
				else
					System.out.println("Illegal date. Re-enter guess. \n");
			}
		}
	}

	//takes an entity index and calls the method playGame with the entity Index
	public void playGame(int entityInd) {
		playGame(entities[entityInd]);
	}

	//generate random number using genRandomEntityIndex and call playGame
	public void playGame() {
		int randomEntity = genRandomEntityIndex();
		playGame(randomEntity);
	}

	//generates a random number from [0 to numberOfCandidateEntities]
	int genRandomEntityIndex() {
		Random rnd = new Random();
		return rnd.nextInt(numberOfCandidateEntities);
	}

	public static void main(String[] args) {
		Entity justinTrudeau = new Entity("Justin Trudeau", new Date("December", 25, 1971));
		Entity kasthuriThambipillai = new Entity("Kasthuri Thambipillai", new Date("June", 22, 1999));
		Entity celineDion = new Entity("Celine Dion", new Date("March", 30, 1968));
		Entity usa = new Entity("usa", new Date("July", 4, 1776));

		GuessMaster guesser = new GuessMaster();
		guesser.addEntity(justinTrudeau);
		guesser.addEntity(kasthuriThambipillai);
		guesser.addEntity(celineDion);
		guesser.addEntity(usa);
		guesser.playGame();
	}
}
