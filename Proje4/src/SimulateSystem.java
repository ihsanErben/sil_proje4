
import java.util.Random;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class SimulateSystem {
	
	List<Subscriber> subscribers = new ArrayList<Subscriber>(); 
	List<ServiceProvider> serviceProviders = new ArrayList<ServiceProvider>();	 
	Scanner s = new Scanner(System.in);

	
	public void menu() {
		int menuId = 0;
		do {
			
			System.out.println("1- Creating a new Service Provider");
			System.out.println("2- Create a new Subscriber");
			System.out.println("3- Voice Call: A subscriber calls another subscriber");
			System.out.println("4- Messaging: A subscriber sends a message to another subscriber");
			System.out.println("5- Internet: A subscriber connects to the Internet");
			System.out.println("6- Pay Invoice: A subscriber pays his/her invoice");
			System.out.println("7- Change ServiceProvider: A subscriber changes his/her provider");
			System.out.println("8- Change Limit: A subscriber changes his/her usage limit for the Invoice");
			System.out.println("9- List all Subscribers (show s_id, isActive, s_provider, invoice)");
			System.out.println("10- List all Service Providers (show p_id, p_name, all costs, and discount)");
			System.out.println("11- Exit");
			menuId = s.nextInt();
			if (menuId == 1) {
		    	createServiceProvider();
			} else if (menuId == 2) {
				createSubscriber();
			} else if (menuId == 3) {
				voiceCall();
			} else if (menuId == 4) {
				sendMessage();
			} else if (menuId == 5) {
				connectInternet();
			} else if (menuId == 6) {
				payInvoice();
			} else if (menuId == 7) {
				changeServiceProvider();
			} else if (menuId == 8) {
				changeUsageLimit();
			} else if (menuId == 9) {
				listSubscribers();
			} else if (menuId == 10) {
				listServiceProviders();
			} else if (menuId == 11) {
				printAnalytics();
				System.out.println("exit");
			} else if (menuId == 12) {
				printAnalytics();
			}
			else {
				System.out.println("Ge�erli de�er giriniz");
			}
			
		} while (menuId != 11);
		
	}
	
	private ServiceProvider findServiceProvider(int pId) { 
		for(ServiceProvider sp : serviceProviders) {
			   if( sp.getP_id() == pId ) {
			       return sp;
			   }
		}
		return null;
	}
	
	private Subscriber findSubscriber(int sId) {
		for(Subscriber s : subscribers) {
			   if( s.getS_id() == sId ) { 
			       return s;
			   }
		}
		return null;
	}
	
	
	public void createServiceProvider() {
		System.out.println("Enter the Voice Cost: ");
		double voiceCost = s.nextDouble();
		System.out.println("Enter the Message Cost: ");
		double messageCost = s.nextDouble();
		System.out.println("Enter the Internet Cost: ");
		double internetCost = s.nextDouble();
		System.out.println("Enter the Discount Ratio: ");
		int discountRatio = s.nextInt(); 
		
		Random rPid = new Random();
		int low = 500;
		int high = 600;
		int pid = rPid.nextInt(high-low) + low;
		
		ServiceProvider serviceProvider = new ServiceProvider(pid, "Turk Telekom", voiceCost, messageCost, internetCost, discountRatio);
		serviceProviders.add(serviceProvider);
		listServiceProviders();
	}

	public void createSubscriber() {
		System.out.println("Enter the name: ");
		String name = s.next();
		System.out.println("Enter the age: ");
		int age = s.nextInt();
		System.out.println("Enter the Provider Id: ");
		int pid = s.nextInt();
		System.out.println("Enter the usage limit: ");
		double usageLimit = s.nextDouble();
		
		Random rSid = new Random();
		int low = 1000000;
		int high = 9000000;
		int sid = rSid.nextInt(high-low) + low;
		
		ServiceProvider serviceProvider = findServiceProvider(pid);
		Subscriber subscriber = new Subscriber(sid, name, age, usageLimit, serviceProvider);
		subscribers.add(subscriber);
		listSubscribers();
	}
	
	
	public void voiceCall() {
		System.out.println("Enter the Caller Id: ");
		int callerId = s.nextInt();
		System.out.println("Enter the Receiver Id: ");
		int receiverId = s.nextInt();
		System.out.println("Enter the minute: ");
		int minute = s.nextInt();
		
		Subscriber caller = findSubscriber(callerId);
		Subscriber receiver = findSubscriber(receiverId);
		if (caller != null && receiver != null) {
			caller.makeVoiceCall(minute, receiver);
		}		
	}
	
	public void sendMessage() {
		System.out.println("Enter the Sender Id: ");
		int senderId = s.nextInt();
		System.out.println("Enter the Receiver Id: ");
		int receiverId = s.nextInt();
		System.out.println("Enter the quantity: ");
		int quantity = s.nextInt();
		
		Subscriber sender = findSubscriber(senderId);
		Subscriber receiver = findSubscriber(receiverId);
		if (sender != null && receiver != null) {
			sender.sendMessage(quantity, receiver);
		}
		
	}
	
	public void connectInternet() {
		System.out.println("Enter the subscriber Id: ");
		int subscriberId= s.nextInt();
		System.out.println("Enter the amount");
		double amount = s.nextDouble();
		Subscriber subscriber = findSubscriber(subscriberId);
		if (subscriber != null) {
			subscriber.connectToInternet( amount);
		}
	}
	
	public void payInvoice() {
		System.out.println("Enter the Subscriber Id: ");
		int subscriberId= s.nextInt();
		System.out.println("Enter the amount: ");
		double amount = s.nextDouble();
		Subscriber subscriber = findSubscriber(subscriberId);
		subscriber.getInv().pay(amount); 
	}
	
	public void changeServiceProvider() {
		System.out.println("Enter the Subscriber Id: ");
		int subscriberId= s.nextInt();
		Subscriber subscriber = findSubscriber(subscriberId);
		System.out.println("Enter the Provider Id: ");
		int serviceProviderId = s.nextInt();
		ServiceProvider serviceProvider = findServiceProvider(serviceProviderId);
		subscriber.changeServiceProvider(serviceProvider);
		System.out.println("Service Provider Changed");
	}
	
	public void changeUsageLimit() {
		System.out.println("Enter the subscriber Id: ");
		int subscriberId= s.nextInt();
		Subscriber subscriber = findSubscriber(subscriberId);
		System.out.println("Enter the limit: ");
		double limit = s.nextDouble();
		subscriber.getInv().changeUsageLimit(limit);
		
	}
	
	public void listSubscribers() {
		System.out.println("**********\n");
		for(Subscriber s :subscribers) { 
			System.out.println(s.getS_id() + " " + s.getName() + " " + s.getAge());	
		}

		System.out.println("**********\n");
	}
	
	public void listServiceProviders() {
		System.out.println("**********\n");
		for(ServiceProvider sp :serviceProviders)
		 System.out.println(sp.getP_id() + " " + sp.getP_name());
		System.out.println("**********\n");
	}
	
	public void printAnalytics() {
		for (ServiceProvider serviceProvider : serviceProviders) {
			  System.out.println(serviceProvider.getP_id()+  " Total Voice Call Time: " + serviceProvider.totalVoiceCallTime + " Total Messages: " + serviceProvider.totalNumberOfMessages + " Total Amount Of Internet: " + serviceProvider.totalMBInternetUsage);
		}
		
		
		for (Subscriber subscriber : subscribers) {
			  System.out.println(subscriber.getS_id() +  " Total Spending: " + subscriber.getTotalSpending() + " Current Spending: " + subscriber.getCurrentSpending());
			  System.out.println(subscriber.getS_id() + " Total Voice Call Time : " + subscriber.getTotalVoiceCallTime());
			  System.out.println(subscriber.getS_id() + " Total Message: " + subscriber.getTotalNumberOfMessages());
			  System.out.println(subscriber.getS_id() + " Total Amount Of Internet: " + subscriber.getTotalAmountOfInternetUsage());
		}
	}

}