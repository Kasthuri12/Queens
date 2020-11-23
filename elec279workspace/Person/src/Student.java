
public class Student extends Person {
	private int numCourses= 0; 
	private String[] courseCodes;
	private int[] gradesList;
	private static int maxCourses = 20; 
	
	public Student(String nameIn, String addressIn, int IDIn, int numCoursesIn, 
				String[] courseCodesIn, int[] gradesListIn, int maxCoursesIn)  {
		super (nameIn, addressIn, IDIn);
		this.numCourses = numCoursesIn; 
		courseCodesIn= courseCodes.clone();
	    gradesListIn = gradesList.clone();
		maxCourses = maxCoursesIn; 
	}
	
	public Student(String nameIn, String addressIn, int IDIn) {
		super (nameIn, addressIn, IDIn);
		courseCodes = new String [maxCourses];
		gradesList = new int [maxCourses];
		
	}
	
	public String toString() {
		return ("Student:" + super.toString());
	}
	
	public void addGrade (String courseCode, int grade) {
		numCourses ++; 
		courseCodes[numCourses-1] = courseCode; 
		gradesList[numCourses -1] = grade; 
	}
	
	public void printGrades () {
		for (int i = 0; i< numCourses; i++) {
			System.out.println(courseCodes[i] + ": " + gradesList[i]);
		}
	}
}
