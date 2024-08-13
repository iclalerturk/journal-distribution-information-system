package iclalErturk_21011037;

import java.awt.BorderLayout;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Distributor implements Runnable{
	private Hashtable<String,Journal> journals;
	private Vector<Subscriber> subscribers;
	private boolean isReportRunning = false;
	public Distributor(Hashtable<String, Journal> journals, Vector<Subscriber> subscribers) {
		this.journals = journals;
		this.subscribers = subscribers;
	}
	
	public boolean addJournal(Journal j) {
		if(journals.contains(j)) {
			return false;
		}
		else {
			journals.put(j.getIssn(), j);
			return true;
		}
	}
	public Journal searchJournal(String issn) {
		if(journals.containsKey(issn)) {
			return journals.get(issn);
		}
		else 
			return null;
		
	}
	public boolean addSubscriber(Subscriber s) {
		if(subscribers.contains(s)) {
			System.out.println("abone zaten var");
			return false;
		}
		else {
			subscribers.add(s);
			System.out.println("abone eklendi");
			return true;
		}
		
	}
	public Subscriber searchSubscriber(String name) {
		for(Subscriber s: subscribers) {
			if(s.getName().equals(name)) {
				return s;
			}
		}
		return null;
	}
	public boolean addSubscription(String issn,Subscriber ser,Subscription stion) {//oncesinde abone ismi ile subscriber ara
		//Subscriber ser2 = searchSubscriber(ser.getName());
	    Journal j = searchJournal(issn);
	    
	    if (ser != null && j != null) {
		    
			int copy;
			int var=0;
			for(Subscription s: j.getSubscriptions() ) {
				if(s.getSubscriber().getName()==ser.getName()) {
					var=1;
					break;
				}
			}
			if(var==1) {
				
				copy= stion.getCopies()+1;
				stion.setCopies(copy);
				return true;
			}
			else{
				//abonelik oluştur
				Subscription newSubscription = new Subscription(stion.getDates(), 1, j, ser);
		        j.addSubscription(newSubscription);
		        ser.setSubscription(newSubscription);
				return true;
			}						
	    }
		else {
				//System.out.println("abonelik olusturulamaz");
				return false;
			}
		
	}
	public void listAllSendingOrders(int month, int year) {
		for (Subscriber ser : subscribers) {
	        System.out.println("Subscriber: " + ser.getName());
	        for (Journal j : journals.values()) {
	            if (j.getFrequency() > 0) {
	                for (Subscription stion : j.getSubscriptions()) {
	                    if (stion.getSubscriber() == ser &&
	                        stion.getJournal() == j &&
	                        stion.getDates().getStartMonth() <= month &&
	                        stion.getDates().getStartYear() <= year &&
	                        stion.getDates().getEndMonth() >= month &&
	                        stion.getDates().getStartYear() <= year &&
	                        stion.getPayment() != null &&
	                        stion.canSend(month)==true) {
	                        System.out.println("\t" + j.getName());
	                        break; // Aboneliğin bir kopyası bulunduğunda diğer abonelikleri kontrol etme.
	                    }
	                }
	            }
	        }
	    }
	}
	public void listSendingOrders(String issn,int month, int year) {
		Journal j = journals.get(issn);
	    if (j != null) {
	        System.out.println("Sending orders for ISSN: " + issn);
	        for (Subscriber subscriber : subscribers) {
	            System.out.println("Subscriber: " + subscriber.getName());
	            for (Subscription stion : j.getSubscriptions()) {
	                if (stion.getSubscriber() == subscriber &&
	                    stion.getJournal() == j &&
	                    stion.getDates().getStartMonth() <= month &&
	                    stion.getDates().getStartYear() <= year &&
	                    stion.getDates().getEndMonth() >= month &&
	                    stion.getPayment() != null &&
	                    stion.canSend(month)) {

	                    System.out.println("\t" + j.getName());
	                    return; 
	                }
	            }
	        }
	    } else {
	        System.out.println("Journal not found for ISSN: " + issn);
	    }
	}
		
	/*
	 * public void listIncomplatePayments() {
	 * System.out.println("Incomplete Payments:"); for (Subscriber ser :
	 * subscribers) { System.out.println("Subscriber: " + ser.getName()); for
	 * (Journal j : journals.values()) { for (Subscription stion :
	 * j.getSubscriptions()) { if (stion.getSubscriber() == ser &&
	 * stion.getPayment() != null && stion.getPayment().getReceivedPayment() <
	 * j.getIssuePrice()*stion.getCopies()) { System.out.println("\tJournal: " +
	 * j.getName()); } } } } }
	 */
	public List<String> listIncompletePayments() {
	    List<String> incompletePaymentsList = new ArrayList<>();
	    incompletePaymentsList.add("Incomplete Payments:");

	    for (Subscriber ser : subscribers) {
	        incompletePaymentsList.add("Subscriber: " + ser.getName());
	        for (Journal j : journals.values()) {
	            for (Subscription stion : j.getSubscriptions()) {
	                if (stion.getSubscriber() == ser && stion.getPayment() != null &&
	                    stion.getPayment().getReceivedPayment() < j.getIssuePrice() * stion.getCopies()) {                    
	                    incompletePaymentsList.add("\tJournal: " + j.getName());
	                }
	            }
	        }
	    }

	    return incompletePaymentsList;
	}


	/*
	 * public void listSubscirptions(String subscriberName) { Subscriber s =
	 * searchSubscriber(subscriberName); if(s!=null) { for(Journal j:
	 * journals.values()) { for(Subscription stion: j.getSubscriptions()) {
	 * if(stion.getSubscriber().equals(s)) {
	 * System.out.println("Journal: "+j.getName()); } } } } else {
	 * System.out.println("Subscriber not found: " + subscriberName); } }
	 */
	public List<String> listSubscriptions(String subscriberName) {////////bunu gui için yazdım normal yazdığımızı guide gösteremedim çünkü
	    List<String> subscriptionList = new ArrayList<>();
	    Subscriber s = searchSubscriber(subscriberName);

	    if (s != null) {
	        subscriptionList.add("Subscriptions for name: " + s.getName());
	        for (Journal j: journals.values()) {
	            for (Subscription subscription : j.getSubscriptions()) {
	                if (subscription.getSubscriber().equals(s)) {
	                    subscriptionList.add("Journal: "+j.getName());
	                }
	            }
	        }
	    } else {
	        subscriptionList.add("Subscriber not found: " + subscriberName);
	    }

	    return subscriptionList;
	}

	/*
	 * public void listSubscriptions2(String issn) { Journal j=searchJournal(issn);
	 * if(j!=null) { System.out.println("Subscriptions for ISSN: " + issn);
	 * for(Subscriber s:subscribers) { for(Subscription stion: j.getSubscriptions())
	 * { if(stion.getSubscriber().equals(s)) { System.out.println("\tSubscriber: " +
	 * s.getName()); } } } } else{ System.out.println("Journal not found for ISSN: "
	 * + issn); } }
	 */
	public List<String> listSubscriptions2(String issn) {////////bunu gui için yazdım normal yazdığımızı guide gösteremedim çünkü
	    List<String> subscriptionList = new ArrayList<>();
	    Journal j = searchJournal(issn);

	    if (j != null) {
	        subscriptionList.add("Subscriptions for ISSN: " + issn);
	        for (Subscriber s : subscribers) {
	            for (Subscription subscription : j.getSubscriptions()) {
	                if (subscription.getSubscriber().equals(s)) {
	                    subscriptionList.add("\tSubscriber: " + s.getName());
	                }
	            }
	        }
	    } else {
	        subscriptionList.add("Journal not found for ISSN: " + issn);
	    }

	    return subscriptionList;
	}
	public synchronized void saveState(String fileName) {
	    while (isReportRunning) {
	        try {
	            wait(); // Report işleminin bitmesini bekle
	        } catch (InterruptedException e) {
	        	JOptionPane.showMessageDialog(null, "Error saving state: " + e.getMessage());
	            e.printStackTrace();
	        }
	    }
	    
	    try (FileOutputStream fos = new FileOutputStream(fileName);
	         ObjectOutputStream oos = new ObjectOutputStream(fos)) {        
	        oos.writeObject(journals);
	        oos.writeObject(subscribers);
	        System.out.println("State saved to " + fileName);
	        JOptionPane.showMessageDialog(null, "State saved successfully to " + fileName);
	    } catch (IOException ex) {
	    	JOptionPane.showMessageDialog(null, "Error saving state: " + ex.getMessage());
	        ex.printStackTrace();
	    }
	    
	}

	public synchronized void loadState(String fileName) {
	    while (isReportRunning) {
	        try {
	            wait(); // Report işleminin bitmesini bekle
	        } catch (InterruptedException e) {
	        	JOptionPane.showMessageDialog(null, "Error loading state: " + e.getMessage());
	            e.printStackTrace();
	        }
	    }
	    
	    try (FileInputStream fis = new FileInputStream(fileName);
	         ObjectInputStream ois = new ObjectInputStream(fis)) {
	        
	        journals = (Hashtable<String, Journal>) ois.readObject();
	        subscribers = (Vector<Subscriber>) ois.readObject();
	        System.out.println("State loaded from " + fileName);
	        JOptionPane.showMessageDialog(null, "State loaded successfully from " + fileName);
	    } catch (IOException | ClassNotFoundException e) {
	    	JOptionPane.showMessageDialog(null, "Error loading state: " + e.getMessage());
	        e.printStackTrace();
	    }
        
	}

	public synchronized void report() {
	    isReportRunning = true;

	    JFrame reportFrame = new JFrame("Report");
	    reportFrame.setSize(600, 400);
	    reportFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    reportFrame.setLocationRelativeTo(null);
	    reportFrame.setLayout(new BorderLayout());

	    JTextArea reportTextArea = new JTextArea();
	    reportTextArea.setEditable(false);
	    JScrollPane scrollPane = new JScrollPane(reportTextArea);
	    reportFrame.add(scrollPane, BorderLayout.CENTER);

	    // Aboneliklerin belirli bir tarihten sonra sona erecek olanları ve belirli bir yıl aralığında alınan ödemeleri hesaplama işlemleri.
	    StringBuilder reportContent = new StringBuilder();

	    for (Subscriber subscriber : subscribers) {
	        // Abonelik süresi kontrolü ve sonlanma tarihlerinin belirlenmesi
	        for (Journal journal : journals.values()) {
	            for (Subscription subscription : journal.getSubscriptions()) {
	                DateInfo dates = subscription.getDates();
	                // Belirtilen tarihten sonra sona erecek aboneliklerin işlenmesi
	                if (dates.getStartYear() + 1 == 2024 && dates.getEndMonth() > 8) {
	                    reportContent.append("Subscription of ").append(subscriber.getName())
	                                .append(" for journal ").append(journal.getName())
	                                .append(" will expire after the given date.\n");
	                }
	            }
	        }
	    }

	    // Belirli bir yıl aralığında alınan ödemelerin hesaplanması
	    for (Subscriber subscriber : subscribers) {
	        double annualPayment = 0.0;
	        for (Journal journal : journals.values()) {
	            for (Subscription subscription : journal.getSubscriptions()) {
	                DateInfo dates = subscription.getDates();
	                PaymentInfo payment = subscription.getPayment();
	                // Belirtilen yıl aralığında yapılan ödemelerin toplanması
	                if (dates.getStartYear() >= 2023 && dates.getStartYear() + 1 <= 2024 && payment != null) {
	                    annualPayment += payment.getReceivedPayment();
	                }
	            }
	        }
	        reportContent.append("Received annual payment by ").append(subscriber.getName())
	                    .append(" in the given year range: ").append(annualPayment).append("\n");
	    }

	    reportContent.append("Report generation completed.\n");

	    reportTextArea.setText(reportContent.toString());

	    reportFrame.setVisible(true);

	    isReportRunning = false;
	    synchronized (this) {
	        notifyAll(); // Bekleyen diğer işlemleri uyandır
	    }
	}


	public Hashtable<String, Journal> getJournals() {
		return journals;
	}

	public void setJournals(Hashtable<String, Journal> journals) {
		this.journals = journals;
	}

	public Vector<Subscriber> getSubscribers() {
		return subscribers;
	}

	public void setSubscribers(Vector<Subscriber> subscribers) {
		this.subscribers = subscribers;
	}

	@Override
	public void run() {
		report();//////////////
		
	}
	
}
