
import java.util.Date;
public class Subscriber {
   private int s_id; // 1000000 - 9000000
   private  String name;
   private int age;
   private boolean isActive = true;
   private ServiceProvider s_provider;
   private Invoice inv; 
    
   private  double totalSpending = 0;
   private double currentSpending = 0;
   
   
   public double getTotalSpending() {
	return totalSpending;
}

public void setTotalSpending(double totalSpending) {
	this.totalSpending = totalSpending;
}

public double getCurrentSpending() {
	return currentSpending;
}

public void setCurrentSpending(double currentSpending) {
	this.currentSpending = currentSpending;
}

public int getTotalVoiceCallTime() {
	return totalVoiceCallTime;
}

public void setTotalVoiceCallTime(int totalVoiceCallTime) {
	this.totalVoiceCallTime = totalVoiceCallTime;
}

public int getTotalNumberOfMessages() {
	return totalNumberOfMessages;
}

public void setTotalNumberOfMessages(int totalNumberOfMessages) {
	this.totalNumberOfMessages = totalNumberOfMessages;
}

public int getTotalAmountOfInternetUsage() {
	return totalAmountOfInternetUsage;
}

public void setTotalAmountOfInternetUsage(int totalAmountOfInternetUsage) {
	this.totalAmountOfInternetUsage = totalAmountOfInternetUsage;
}

private int totalVoiceCallTime = 0;
   private int totalNumberOfMessages = 0;
   private  int totalAmountOfInternetUsage = 0;


	public int getS_id() {
		return s_id;
	}

	public void setS_id(int s_id) {
		this.s_id = s_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public ServiceProvider getS_provider() {
		return s_provider;
	}

	public void setS_provider(ServiceProvider s_provider) {
		this.s_provider = s_provider;
	}

	public Invoice getInv() {
		return inv;
	}

	public void setInv(Invoice inv) {
		this.inv = inv;
	}
    
    
    public Subscriber(int sId, String name, int age, double usageLimit, ServiceProvider s_provider) {	
		this.s_id = sId;
		this.name = name;
		this.age = age;
		this.inv = new Invoice(usageLimit); 
		this.inv.setUsageLimit(usageLimit);
		this.s_provider = s_provider;
	}

	public void updateStatus() {
        if (inv.getLastDayToPay().after(new Date()) ) {
            this.isActive = false;
        }
    }
    
    public void makeVoiceCall(int minute, Subscriber receiver) {
    	if (!isActive) {
    		System.out.println("Inactive subscriber");
    		return ;
    	}
    	totalVoiceCallTime += minute; 
    	s_provider.totalVoiceCallTime += minute; 
        currentSpending = s_provider.calculateVoiceCallCost(minute, receiver);
    	totalSpending += currentSpending;
    	System.out.println("receiver called " + receiver.s_id); 
    
  
    }
    
    public void sendMessage(int quantity, Subscriber receiver) {
    	if (!isActive) {
    		return;
    	}
    	totalNumberOfMessages += quantity; 
    	s_provider.totalNumberOfMessages += quantity;
    	currentSpending = s_provider.calculateMessagingCost(quantity, receiver, receiver);
       	totalSpending += currentSpending;
    	System.out.println("message send " + receiver.s_id); 
    }
    
    public void connectToInternet( double amount) {
    	if (!isActive) {
    		return;
    	}
    	totalAmountOfInternetUsage += amount;
    	s_provider.totalMBInternetUsage += amount;
    	currentSpending = s_provider.calculateInternetCost( this,amount);
    	totalSpending += currentSpending;
    	System.out.println("connected to internet.");
    }
    
    public void changeServiceProvider(ServiceProvider s_provider)  {
    	if (inv.getLastDayToPay().after(new Date()) ) {
    		System.out.println("You must pay your bill!");
    		return;
    	}
    	System.out.println("Service Provider changed to " + s_provider.getP_id()); 
    	this.s_provider = s_provider;
    }

	
}