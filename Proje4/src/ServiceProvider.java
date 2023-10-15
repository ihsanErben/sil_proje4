
import java.util.List;

public class ServiceProvider {
   private int p_id; 
   private String p_name;
   private double voiceCallCost;
   private double messagingCost;
   private double internetCost;
   private int discountRatio;	
   int totalVoiceCallTime = 0; 
   int totalNumberOfMessages = 0;
    int totalMBInternetUsage = 0; 
 List<Subscriber> subscribersList; 

public void setP_id(int p_id) {
	this.p_id = p_id;
}

public void setP_name(String p_name) {
	this.p_name = p_name;
}

public void setVoiceCallCost(double voiceCallCost) {
	this.voiceCallCost = voiceCallCost;
}

public void setMessagingCost(double messagingCost) {
	this.messagingCost = messagingCost;
}

public void setInternetCost(double internetCost) {
	this.internetCost = internetCost;
}

public void setDiscountRatio(int discountRatio) {
	this.discountRatio = discountRatio;
}

public void setSubscribersList(List<Subscriber> subscribersList) {
	this.subscribersList = subscribersList;
}


    public int getP_id() {
		return p_id;
	}

	public String getP_name() {
		return p_name;
	}


	public double getVoiceCallCost() {
		return voiceCallCost;
	}
	
	public double getDiscountedVoiceCallCost() {
		return voiceCallCost - (voiceCallCost * discountRatio / 100); 
	}


	public double getMessagingCost() {
		return messagingCost;
	}
	
	public double getDiscountedMessagingCost() {
		return messagingCost - (messagingCost * discountRatio / 100);
	}


	public double getInternetCost() {
		return internetCost;
	}
	
	public int getDiscountRatio() {
		return discountRatio;
	}

	public List<Subscriber> getSubscribersList() {
		return subscribersList;
	}

  
    
    public ServiceProvider(int p_id, String p_name, double voiceCallCost, double messagingCost, double internetCost, int discountRatio) {
		this.p_id = p_id;
		this.p_name = p_name;
		this.voiceCallCost = voiceCallCost;
		this.messagingCost = messagingCost;
		this.internetCost = internetCost;
		this.discountRatio = discountRatio;
	}
    
    public double calculateVoiceCallCost(int minute, Subscriber caller) {
        
    	double calculatedCost = minute * voiceCallCost;
    	if (caller.getAge() >= 10 && caller.getAge() <= 18) {
    		return (minute - 5) * getDiscountedVoiceCallCost();  
    	} else if (caller.getAge() >= 65) { 
    		return  getDiscountedVoiceCallCost();
    	}
    	return calculatedCost; 
    }

    public double calculateMessagingCost(int quantity, Subscriber sender, Subscriber receiver) {
      
    	
    	double cost = messagingCost;
    	
    	if (sender.getS_provider().p_id == receiver.getS_provider().p_id) { 
    	 cost = getDiscountedMessagingCost();
    	}
 
    	if (sender.getAge() >= 10 && receiver.getAge() <= 18) { 
    		return (quantity - 10) * cost;
    	}
    	
      return quantity * cost;  
    }
    
    public double calculateInternetCost(Subscriber subscriber, double amount) { 
        
    	if (subscriber.getAge() >= 10 && subscriber.getAge() <= 18) {
    		return (amount - 5) * internetCost; 
    	}
    	return amount * internetCost; 
    }
    

    public boolean addSubscriber(Subscriber s) {
         
         return subscribersList.add(s);  
    }
    
    public boolean removeSubscriber(Subscriber s) {
        
    	return subscribersList.remove(s); 
    }
}