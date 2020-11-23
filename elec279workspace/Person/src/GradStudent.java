
public class GradStudent extends Student {
	private int numComMembers;
	private int examsTaken;
	private String[] examName;
	private String[] gradesList;
	private static final int maxTrial = 6;
	
	public GradStudent(String name, String address, int ID) {
		super(name, address, ID);
		numComMembers = 0;
		examsTaken = 0;
		examName = new String[maxTrial];
		gradesList = new String[maxTrial];
	}
	
	public void setComitteeNum(int num) {
		numComMembers = num;
	}
	
	public int getComitteeNum() {
		return numComMembers;
	}
	
	public void addGrade(String name, String grade) {
		examsTaken++;
		examName[examsTaken - 1] = name;
		gradesList[examsTaken - 1] = grade;
	}
	
	public String toString() {
		return ("Grad " + super.toString());
	}
	
	public void printExamGrades() {
		for(int i = 0; i < examsTaken; i++) {
			System.out.println(examName[i] + ": " + gradesList[i]);
		}
	}
}
