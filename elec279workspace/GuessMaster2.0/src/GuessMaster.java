
/**
 * @author Kasthuri Thambipillai
 * @StudentNumber 2009146
 */

import java.util.Random;
import java.util.Scanner;

public class GuessMaster {
	private int numOfEntities;
	private Entity[] entities;
	// attribute to keep track of the total amount of tickets accumulated by the
	// user every round 
	int sumOfTickets;

	public GuessMaster() {
		numOfEntities = 0;
		entities = new Entity[100];
	}

	// modified to use the clone() method as entity is now abstract
	public void addEntity(Entity entity) {
		entities[numOfEntities++] = entity.clone();
	}

	public void playGame(int entityId) {
		Entity entity = entities[entityId];
		playGame(entity);
	}

	public void playGame(Entity entity) {

		System.out.println(entity.welcomeMessage());
		System.out.printf("Guess %s's birthday\n", entity.getName());
		System.out.println("(mm/dd/yyyy)");

		Scanner scanner = new Scanner(System.in);

		while (true) {
			String answer = scanner.nextLine();
			answer = answer.replace("\n", "").replace("\r", "");

			if (answer.equals("quit")) {
				System.exit(0);
			}

			Date date = new Date(answer);

			//determines if the input is incorrect or correct
			if (date.precedes(entity.getBorn())) {
				System.out.println("Incorrect. Try a later date.");
			} else if (entity.getBorn().precedes(date)) {
				System.out.println("Incorrect. Try an earlier date.");
			} else {
				Random random = new Random();
				int rd = random.nextInt(50);
				rd += 50;
//				for(int i=0; i<=50*100; i++)
//					System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n");
				System.out.println("*************Bingo!***************");
				System.out.printf("You won " + entity.getAwardedTicketNumber() + " tickets in this round! \n");
				//using the sum operator the number of tickets accumulated from that run
				// is added to the total number of tickets 
				sumOfTickets += entity.getAwardedTicketNumber();
				System.out.printf("The total number of tickets is " + sumOfTickets + "!\n");
				System.out.println("**********************************\n");
				System.out.printf(entity.closingMessage());
				System.out.println("**********************************");
				break;
			}
		}
	}

	public void playGame() {
		while (true) {
		}
	}

	public int genRandomEntityId() {
		Random randomNumber = new Random();
		return randomNumber.nextInt(numOfEntities);
	}

	public static void main(String[] args) {
		System.out.println("=========================\n");
		System.out.println("     GuessMaster 2.0 \n");
		System.out.println("=========================");
		Politician jTrudeau = new Politician("Justin Trudeau", new Date("December", 25, 1971), 0.25, "Male", "Liberal");
		Singer cDion = new Singer("Celine Dion", new Date("March", 30, 1961), 0.5, "Female", "La voix du bon Dieu",
				new Date("November", 6, 1981));
		Country usa = new Country("United States", new Date("July", 4, 1776), "Wash-inton D.C", 0.1);

		GuessMaster gm = new GuessMaster();
		gm.addEntity(jTrudeau);
		gm.addEntity(cDion);
		gm.addEntity(usa);

		gm.playGame();
	}
}
