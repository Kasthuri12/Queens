/** 
 * ELEC 279 Assignment 1 
 * @author Kasthuri Thambipillai 
 * @studentNumber 20059146
 */

public class Entity {
	//instance variables 
	private String name; 								
	private Date born;
	
	//copy constructor for instance variables keep in mind privacy leaks 
	public Entity(String name, Date born)
	{ 			 
		this.name = new String(name); 					
		this.born = new Date (born);
	}
	
	//copy constructor for entity 
	public Entity(Entity anEntity) {
		anEntity = new Entity(anEntity);
	}
	
	//default constructor 
	public Entity () 
	{
		this.born = new Date(); 
		String name = "default"; 
		this.name = name; 
	}
	
	//assessor method, since name is a string privacy leaks are not an issue 
	public String getName() 
	{ 							
		return name; 								
	}
	
	//assessor method, consider privacy leaks when returning born 
	public Date getBorn() 
	{ 							
		return this.born = new Date(born); 				
	}
	
	//method that returns the content of an entity as a string
	public String toString()
	{							
		return name + "," + "born on" + born;  
	}
	
	//compare content of two entities 
	// the .equals compares the values of the objects and == the locations in memory
	boolean equals(Entity entity) 
	{						
		if (born.equals(entity.born)&&name==entity.name)  
			return true; 
		
		return false;
	}

}

