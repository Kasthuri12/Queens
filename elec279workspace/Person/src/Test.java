
public class Test {
	public static void main(String[] args) { 
		Person p1 = new Person ("Julia", " 181 University Ave.", 121456);
		System.out.println(p1.toString()); 
		Student s1= new Student("John", "189 Unversity Ave.", 246718); 
		s1.addGrade("ELEC_279", 97);
		s1.addGrade("ELEC_221", 68);
		System.out.printf(s1.toString());
		s1.printGrades(); 
		GradStudent s2 = new GradStudent("Tom", "92 Reg Street", 1014);
		s2.addGrade("ELEC 279", 97);
		s2.addGrade("ELEC 271", 88);
		s2.setComitteeNum(6);
		s2.addGrade("CompI", "PASS");
		s2.addGrade("COMPII", "PASS");
		s2.addGrade("Thesis Defense first trial", "FAIL");
		s2.addGrade("Thesis Defense second trial", "FAIL");
		s2.printExamGrades();
		s2.printGrades();
	}
}
