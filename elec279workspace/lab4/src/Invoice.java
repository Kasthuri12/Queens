public class Invoice implements Comparable <Invoice>, PayAble
{
	
	private String companyName;
	private Double amount;
	
	public int compareTo(Invoice bill) {
		if(this.getAmount() == bill.getAmount()) {
			return 0;	
		}
		else if(this.getAmount() > bill.getAmount()) { //calling obj is greater
			return -1; 
		}
		else return 1; //calling obj is less than
	}
	
	//implementing PayAble methods
	
	public Double amountToPay() {
		return this.amount;
	}
	
	public void printPayment() {
		System.out.printf("\nPayment information for an invoice. Company name: " + this.companyName + "; payment: " + this.amountToPay());
	}
	
	public Invoice(){
		companyName = "No Name";
		amount = 0.0;
	}

	public Invoice(String theName, Double theAmount){
		companyName = theName;
		if (theName == null || theAmount < 0)
        {
             System.out.println("Fatal Error creating Invoice.");
             System.exit(0);
        }
        companyName = theName;
        amount = theAmount;
	}
	
	public String getName(){
		return companyName;
	}
	
	public Double getAmount(){
		return amount;
	}
	
	public void setName(String newName){
		if (newName == null)
        {
             System.out.println("Fatal Error setting invoice name.");
             System.exit(0);
        }
       else
            companyName = newName;
	}
	
	public void setAmount(Double newAmount){
		if (newAmount < 0)
        {
             System.out.println("Fatal Error setting invoice name.");
             System.exit(0);
        }
       else
            amount = newAmount;
	}
	
	public String toString(){
		return companyName + " is owed " + amount;
	}
	

}
