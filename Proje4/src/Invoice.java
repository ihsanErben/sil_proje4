
import java.util.Calendar;
import java.util.Date;
public class Invoice {
    private double usageLimit; 
    private double currentSpending;
    private Date lastDayToPay;
    
    public double getUsageLimit() {
		return usageLimit;
	}

	public void setUsageLimit(double usageLimit) {
		this.usageLimit = usageLimit;
	}

	public double getCurrentSpending() {
		return currentSpending;
	}

	public void setCurrentSpending(double currentSpending) {
		this.currentSpending = currentSpending;
	}

	public Date getLastDayToPay() {
		return lastDayToPay;
	}

	public void setLastDayToPay(Date lastDayToPay) {
		this.lastDayToPay = lastDayToPay;
	}

    
    public Invoice(double usageLimit) {
	
		this.usageLimit = usageLimit;
		Calendar cal = Calendar.getInstance(); 
		cal.setTime(new Date()); 
		cal.add(Calendar.DATE, -30);
		Date dateBefore30Days = cal.getTime(); 
		lastDayToPay = dateBefore30Days;
	}

	public boolean isLimitExceeded(double amount) { 
       
    	if (amount > usageLimit) {
    		return true; 
        }
        return false;
    }
    
    public void addCost(double amount) {
        
    }
        
    public void pay(double amount) {
     
    if (amount==currentSpending) {
    	lastDayToPay =new Date();
    	System.out.println("Bill paid");
    	
    }
    else {
    	System.out.println("Amount must be greater than currentSpend");
    }
    }
    
    public void changeUsageLimit(double amount) {
        
    	usageLimit = amount;
    	System.out.println("Change limitied");
    }
}