package iclalErturk_21011037;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class Main extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Hashtable<String,Journal> journals = new Hashtable<>();
		Vector<Subscriber> subscribers = new Vector<>();
		Distributor distributor = new Distributor(journals, subscribers);
		// Frame boyutlarını al
		Dimension screenSize = getToolkit().getScreenSize();
		
		setBounds(0, 0, screenSize.width, screenSize.height);

		contentPane = new JPanel();
		contentPane.setBackground(new Color(129, 175, 147));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		// contentPane'i tüm pencereyi kaplayacak şekilde ayarla
		contentPane.setBounds(0, 0, screenSize.width, screenSize.height);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel distPanel = new JPanel();
		distPanel.setLayout(null);
		distPanel.setBackground(new Color(129, 175, 147));
		distPanel.setBorder(new RoundedBorder(20, new Color(39, 99, 61), new Color(39, 99, 61)));
		distPanel.setBounds(55, 50, 1421, 175);
		
		JLabel lblNewLabel = new JLabel("Journal Distribution Information System");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 55));
		lblNewLabel.setBounds(0, 0, 1421, 175);
		
		distPanel.add(lblNewLabel);
		contentPane.add(distPanel);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(new Color(129, 175, 147));
		buttonPanel.setBorder(new RoundedBorder(20, new Color(39, 99, 61), new Color(39, 99, 61)));
		buttonPanel.setBounds(55, 240, 1421, 527);
		contentPane.add(buttonPanel);
		buttonPanel.setLayout(null);
		
		JButton btnNewButton = new JButton("Add Journal") {
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
                if (getModel().isArmed()) {
                    g.setColor(Color.PINK);
                } else {
                    g.setColor(getBackground());
                }
                g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
                super.paintComponent(g);
            }

            @Override
            protected void paintBorder(Graphics g) {
                g.setColor(new Color(74, 130, 96));
                g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
            }
		};
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String journalName = JOptionPane.showInputDialog(null, "Journal Name:");
				String issn = JOptionPane.showInputDialog(null, "ISSN:");
				int frequency = Integer.parseInt(JOptionPane.showInputDialog(null, "Frequency:"));
				double issuePrice = Double.parseDouble(JOptionPane.showInputDialog(null, "Issue Price:"));
				Journal journal = new Journal(journalName, issn, frequency, issuePrice);
				if(distributor.addJournal(journal)) {
					JOptionPane.showMessageDialog(null, "Journal added.");
				}
				else {
					JOptionPane.showMessageDialog(null, "Error!");
				}
			}
		});
		btnNewButton.setFocusPainted(false);
		btnNewButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		btnNewButton.setOpaque(true);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBackground(new Color(74, 130, 96));
		btnNewButton.setBounds(76, 36, 400, 69);
		buttonPanel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Search Journal") {
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
                if (getModel().isArmed()) {
                    g.setColor(Color.PINK);
                } else {
                    g.setColor(getBackground());
                }
                g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
                super.paintComponent(g);
            }

            @Override
            protected void paintBorder(Graphics g) {
                g.setColor(new Color(74, 130, 96));
                g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
            }
		};
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String issn = JOptionPane.showInputDialog(null, "ISSN:");
				if(distributor.searchJournal(issn) != null) {
					JOptionPane.showMessageDialog(null,distributor.searchJournal(issn).toString() );
				}
				else {
					JOptionPane.showMessageDialog(null,"This subscriber have not found." );
				}
			}
		});
		btnNewButton_1.setOpaque(true);
		btnNewButton_1.setContentAreaFilled(false);
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_1.setFocusPainted(false);
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		btnNewButton_1.setBackground(new Color(74, 130, 96));
		btnNewButton_1.setBounds(76, 130, 400, 69);
		buttonPanel.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("Add Subscriber") {
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
                if (getModel().isArmed()) {
                    g.setColor(Color.PINK);
                } else {
                    g.setColor(getBackground());
                }
                g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
                super.paintComponent(g);
            }

            @Override
            protected void paintBorder(Graphics g) {
                g.setColor(new Color(74, 130, 96));
                g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
            }
		};
		
		btnNewButton_1_1.setOpaque(true);
		btnNewButton_1_1.setContentAreaFilled(false);
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_1_1.setFocusPainted(false);
		btnNewButton_1_1.setBorderPainted(false);
		btnNewButton_1_1.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		btnNewButton_1_1.setBackground(new Color(74, 130, 96));
		btnNewButton_1_1.setBounds(76, 227, 400, 69);
		buttonPanel.add(btnNewButton_1_1);
		
		JButton btnNewButton_1_2 = new JButton("Search Subscriber") {
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
                if (getModel().isArmed()) {
                    g.setColor(Color.PINK);
                } else {
                    g.setColor(getBackground());
                }
                g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
                super.paintComponent(g);
            }

            @Override
            protected void paintBorder(Graphics g) {
                g.setColor(new Color(74, 130, 96));
                g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
            }
		};
		btnNewButton_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = JOptionPane.showInputDialog(null, "ISSN:");
				if(distributor.searchSubscriber(name) != null) {
					JOptionPane.showMessageDialog(null,distributor.searchSubscriber(name).toString() );
				}
				else {
					JOptionPane.showMessageDialog(null,"This subscriber have not found." );
				}
			}
		});
		btnNewButton_1_2.setOpaque(true);
		btnNewButton_1_2.setContentAreaFilled(false);
		btnNewButton_1_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_1_2.setFocusPainted(false);
		btnNewButton_1_2.setBorderPainted(false);
		btnNewButton_1_2.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		btnNewButton_1_2.setBackground(new Color(74, 130, 96));
		btnNewButton_1_2.setBounds(76, 322, 400, 69);
		buttonPanel.add(btnNewButton_1_2);
		
		JButton btnNewButton_2 = new JButton("Add Subscription") {
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
                if (getModel().isArmed()) {
                    g.setColor(Color.PINK);
                } else {
                    g.setColor(getBackground());
                }
                g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
                super.paintComponent(g);
            }

            @Override
            protected void paintBorder(Graphics g) {
                g.setColor(new Color(74, 130, 96));
                g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
            }
		};
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = JOptionPane.showInputDialog(null, "Subscriber Name:");
				String issn = JOptionPane.showInputDialog(null, "Journal ISSN:");
				Subscriber s = distributor.searchSubscriber(name);
				int startMonth = Integer.parseInt(JOptionPane.showInputDialog(null, "Start Month:"));
				int startYear = Integer.parseInt(JOptionPane.showInputDialog(null, "Start Year:"));
				DateInfo date = new DateInfo(startMonth,startYear);
				Subscription subscription = new Subscription(date,1,distributor.searchJournal(issn),s);
				if(distributor.addSubscription(issn, s, subscription)) {
					JOptionPane.showMessageDialog(null,"Succesfull!" );
				}
				else {
					JOptionPane.showMessageDialog(null,"Error!" );
				}
			}
		});
		btnNewButton_2.setOpaque(true);
		btnNewButton_2.setContentAreaFilled(false);
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_2.setFocusPainted(false);
		btnNewButton_2.setBorderPainted(false);
		btnNewButton_2.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		btnNewButton_2.setBackground(new Color(74, 130, 96));
		btnNewButton_2.setBounds(516, 36, 400, 69);
		buttonPanel.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("List All Sending Orders") {
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
                if (getModel().isArmed()) {
                    g.setColor(Color.PINK);
                } else {
                    g.setColor(getBackground());
                }
                g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
                super.paintComponent(g);
            }

            @Override
            protected void paintBorder(Graphics g) {
                g.setColor(new Color(74, 130, 96));
                g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
            }
		};
		btnNewButton_3.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int month = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter month:"));
		        int year = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter year:"));

		        // Sending orders listesini oluştur
		        List<String> sendingOrders = new ArrayList<>();
		        for (Subscriber ser : subscribers) {
		            sendingOrders.add("Subscriber: " + ser.getName());
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
		                            stion.canSend(month)) {

		                            sendingOrders.add("\t" + j.getName());
		                            break; // Aboneliğin bir kopyası bulunduğunda diğer abonelikleri kontrol etme.
		                        }
		                    }
		                }
		            }
		        }

		        // Eğer sending orders varsa yeni bir pencere aç
		        if (!sendingOrders.isEmpty()) {
		            JFrame sendingOrdersFrame = new JFrame("Sending Orders for All Subscribers");
		            sendingOrdersFrame.setSize(400, 300);
		            sendingOrdersFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		            sendingOrdersFrame.setLocationRelativeTo(null);
		            sendingOrdersFrame.getContentPane().setLayout(new BorderLayout());

		            DefaultListModel<String> listModel = new DefaultListModel<>();
		            for (String order : sendingOrders) {
		                listModel.addElement(order);
		            }

		            JList<String> sendingOrdersList = new JList<>(listModel);
		            sendingOrdersList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		            JScrollPane scrollPane = new JScrollPane(sendingOrdersList);
		            sendingOrdersFrame.getContentPane().add(scrollPane, BorderLayout.CENTER);

		            sendingOrdersFrame.setVisible(true);
		        } else {
		            JOptionPane.showMessageDialog(null, "No sending orders found.");
		        }
		    }
		});

		btnNewButton_3.setOpaque(true);
		btnNewButton_3.setContentAreaFilled(false);
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_3.setFocusPainted(false);
		btnNewButton_3.setBorderPainted(false);
		btnNewButton_3.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		btnNewButton_3.setBackground(new Color(74, 130, 96));
		btnNewButton_3.setBounds(516, 130, 400, 69);
		buttonPanel.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("List Sending Orders") {
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
                if (getModel().isArmed()) {
                    g.setColor(Color.PINK);
                } else {
                    g.setColor(getBackground());
                }
                g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
                super.paintComponent(g);
            }

            @Override
            protected void paintBorder(Graphics g) {
                g.setColor(new Color(74, 130, 96));
                g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
            }
		};
		btnNewButton_4.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String issn = JOptionPane.showInputDialog(null, "Enter ISSN:");
		        int month = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter month:"));
		        int year = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter year:"));

		        Journal j = journals.get(issn);
		        if (j != null) {
		            List<String> sendingOrders = new ArrayList<>();
		            sendingOrders.add("Sending orders for ISSN: " + issn);
		            for (Subscriber subscriber : subscribers) {
		                sendingOrders.add("Subscriber: " + subscriber.getName());
		                for (Subscription stion : j.getSubscriptions()) {
		                    if (stion.getSubscriber() == subscriber &&
		                        stion.getJournal() == j &&
		                        stion.getDates().getStartMonth() <= month &&
		                        stion.getDates().getStartYear() <= year &&
		                        stion.getDates().getEndMonth() >= month &&
		                        stion.getPayment() != null &&
		                        stion.canSend(month)) {

		                        sendingOrders.add("\t" + j.getName());
		                        break; // Aboneliğin bir kopyası bulunduğunda diğer abonelikleri kontrol etme.
		                    }
		                }
		            }

		            if (!sendingOrders.isEmpty()) {
		                JFrame sendingOrdersFrame = new JFrame("Sending Orders for ISSN: " + issn);
		                sendingOrdersFrame.setSize(400, 300);
		                sendingOrdersFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		                sendingOrdersFrame.setLocationRelativeTo(null);
		                sendingOrdersFrame.getContentPane().setLayout(new BorderLayout());

		                DefaultListModel<String> listModel = new DefaultListModel<>();
		                for (String order : sendingOrders) {
		                    listModel.addElement(order);
		                }

		                JList<String> sendingOrdersList = new JList<>(listModel);
		                sendingOrdersList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		                JScrollPane scrollPane = new JScrollPane(sendingOrdersList);
		                sendingOrdersFrame.getContentPane().add(scrollPane, BorderLayout.CENTER);

		                sendingOrdersFrame.setVisible(true);
		            } else {
		                JOptionPane.showMessageDialog(null, "No sending orders found.");
		            }
		        } else {
		            JOptionPane.showMessageDialog(null, "Journal not found for ISSN: " + issn);
		        }
		    }
		});

		btnNewButton_4.setOpaque(true);
		btnNewButton_4.setContentAreaFilled(false);
		btnNewButton_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_4.setFocusPainted(false);
		btnNewButton_4.setBorderPainted(false);
		btnNewButton_4.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		btnNewButton_4.setBackground(new Color(74, 130, 96));
		btnNewButton_4.setBounds(516, 227, 400, 69);
		buttonPanel.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Load State") {
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
                if (getModel().isArmed()) {
                    g.setColor(Color.PINK);
                } else {
                    g.setColor(getBackground());
                }
                g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
                super.paintComponent(g);
            }

            @Override
            protected void paintBorder(Graphics g) {
                g.setColor(new Color(74, 130, 96));
                g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
            }
		};
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fileName = JOptionPane.showInputDialog(null, "Enter file name to load state:");
		        if (fileName != null && !fileName.trim().isEmpty()) {
		            distributor.loadState(fileName);
		            
		        }
			}
		});
		btnNewButton_5.setOpaque(true);
		btnNewButton_5.setContentAreaFilled(false);
		btnNewButton_5.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_5.setFocusPainted(false);
		btnNewButton_5.setBorderPainted(false);
		btnNewButton_5.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		btnNewButton_5.setBackground(new Color(74, 130, 96));
		btnNewButton_5.setBounds(955, 322, 400, 69);
		buttonPanel.add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("List Subscriptions with Subscriber Name") {
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
                if (getModel().isArmed()) {
                    g.setColor(Color.PINK);
                } else {
                    g.setColor(getBackground());
                }
                g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
                super.paintComponent(g);
            }

            @Override
            protected void paintBorder(Graphics g) {
                g.setColor(new Color(74, 130, 96));
                g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
            }
		};
		btnNewButton_6.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String name = JOptionPane.showInputDialog(null, "Subscriber Name:");
		        if (name != null && !name.trim().isEmpty()) {
		            // Subscriptions listesini al
		            List<String> subs = distributor.listSubscriptions(name);

		            // Eğer subscription varsa yeni bir pencere aç
		            if (!subs.isEmpty()) {
		                // SubscriptionFrame içinde JList ile listeyi göster
		                JFrame subscriptionFrame = new JFrame("Subscriptions for " + name);
		                subscriptionFrame.setSize(300, 400);
		                subscriptionFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		                subscriptionFrame.setLocationRelativeTo(null);
		                subscriptionFrame.getContentPane().setLayout(new BorderLayout());

		                // List öğeleri için model oluşturma
		                DefaultListModel<String> listModel = new DefaultListModel<>();
		                for (String sub : subs) {
		                    listModel.addElement(sub);
		                }

		                // JList oluşturma ve modele bağlama
		                JList<String> subscriptionList = new JList<>(listModel);
		                subscriptionList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		                // JList'i bir JScrollPane içine yerleştirme
		                JScrollPane scrollPane = new JScrollPane(subscriptionList);
		                subscriptionFrame.getContentPane().add(scrollPane, BorderLayout.CENTER);

		                subscriptionFrame.setVisible(true);
		            } else {
		                // Eğer subscription yoksa kullanıcıya bilgi ver
		                JOptionPane.showMessageDialog(null, "No subscriptions found for " + name);
		            }
		        }
		    }
		});

		btnNewButton_6.setOpaque(true);
		btnNewButton_6.setContentAreaFilled(false);
		btnNewButton_6.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_6.setFocusPainted(false);
		btnNewButton_6.setBorderPainted(false);
		btnNewButton_6.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		btnNewButton_6.setBackground(new Color(74, 130, 96));
		btnNewButton_6.setBounds(955, 36, 400, 69);
		buttonPanel.add(btnNewButton_6);
		
		JButton btnNewButton_7 = new JButton("List Subscriptions with ISSN") {
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
                if (getModel().isArmed()) {
                    g.setColor(Color.PINK);
                } else {
                    g.setColor(getBackground());
                }
                g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
                super.paintComponent(g);
            }

            @Override
            protected void paintBorder(Graphics g) {
                g.setColor(new Color(74, 130, 96));
                g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
            }
		};
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String issn = JOptionPane.showInputDialog(null, "Journal ISSN:");
		        if (issn != null && !issn.trim().isEmpty()) {
		            // Subscriptions listesini al
		            List<String> subs = distributor.listSubscriptions2(issn);

		            // Eğer subscription varsa yeni bir pencere aç
		            if (!subs.isEmpty()) {
		                // SubscriptionFrame içinde JList ile listeyi göster
		                JFrame subscriptionFrame = new JFrame("Subscriptions for " + issn);
		                subscriptionFrame.setSize(300, 400);
		                subscriptionFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		                subscriptionFrame.setLocationRelativeTo(null);
		                subscriptionFrame.getContentPane().setLayout(new BorderLayout());

		                // List öğeleri için model oluşturma
		                DefaultListModel<String> listModel = new DefaultListModel<>();
		                for (String sub : subs) {
		                    listModel.addElement(sub);
		                }

		                // JList oluşturma ve modele bağlama
		                JList<String> subscriptionList = new JList<>(listModel);
		                subscriptionList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		                // JList'i bir JScrollPane içine yerleştirme
		                JScrollPane scrollPane = new JScrollPane(subscriptionList);
		                subscriptionFrame.getContentPane().add(scrollPane, BorderLayout.CENTER);

		                subscriptionFrame.setVisible(true);
		            } else {
		                // Eğer subscription yoksa kullanıcıya bilgi ver
		                JOptionPane.showMessageDialog(null, "No subscriptions found for " + issn);
		            }
		        }
			}
		});
		btnNewButton_7.setOpaque(true);
		btnNewButton_7.setContentAreaFilled(false);
		btnNewButton_7.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_7.setFocusPainted(false);
		btnNewButton_7.setBorderPainted(false);
		btnNewButton_7.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		btnNewButton_7.setBackground(new Color(74, 130, 96));
		btnNewButton_7.setBounds(955, 130, 400, 69);
		buttonPanel.add(btnNewButton_7);
		
		JButton btnNewButton_8 = new JButton("Save State") {
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
                if (getModel().isArmed()) {
                    g.setColor(Color.PINK);
                } else {
                    g.setColor(getBackground());
                }
                g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
                super.paintComponent(g);
            }

            @Override
            protected void paintBorder(Graphics g) {
                g.setColor(new Color(74, 130, 96));
                g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
            }
		};
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fileName = JOptionPane.showInputDialog(null, "Enter file name to save state:");
		        if (fileName != null && !fileName.trim().isEmpty()) {
		            distributor.saveState(fileName);
		            
		        }
			}
		});
		btnNewButton_8.setOpaque(true);
		btnNewButton_8.setContentAreaFilled(false);
		btnNewButton_8.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_8.setFocusPainted(false);
		btnNewButton_8.setBorderPainted(false);
		btnNewButton_8.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		btnNewButton_8.setBackground(new Color(74, 130, 96));
		btnNewButton_8.setBounds(516, 322, 400, 69);
		buttonPanel.add(btnNewButton_8);
		
		JButton btnNewButton_9 = new JButton("List Incomplete Payments") {
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
                if (getModel().isArmed()) {
                    g.setColor(Color.PINK);
                } else {
                    g.setColor(getBackground());
                }
                g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
                super.paintComponent(g);
            }

            @Override
            protected void paintBorder(Graphics g) {
                g.setColor(new Color(74, 130, 96));
                g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
            }
		};
		btnNewButton_9.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Incomplete payments listesini al
		        List<String> incompletePayments = distributor.listIncompletePayments();

		        // Eğer incomplete payment varsa yeni bir pencere aç
		        if (!incompletePayments.isEmpty()) {
		            // IncompletePaymentsFrame içinde JList ile listeyi göster
		            JFrame incompletePaymentsFrame = new JFrame("Incomplete Payments");
		            incompletePaymentsFrame.setSize(400, 300);
		            incompletePaymentsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		            incompletePaymentsFrame.setLocationRelativeTo(null);
		            incompletePaymentsFrame.getContentPane().setLayout(new BorderLayout());

		            // List öğeleri için model oluşturma
		            DefaultListModel<String> listModel = new DefaultListModel<>();
		            for (String payment : incompletePayments) {
		                listModel.addElement(payment);
		            }

		            // JList oluşturma ve modele bağlama
		            JList<String> incompletePaymentsList = new JList<>(listModel);
		            incompletePaymentsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		            // JList'i bir JScrollPane içine yerleştirme
		            JScrollPane scrollPane = new JScrollPane(incompletePaymentsList);
		            incompletePaymentsFrame.getContentPane().add(scrollPane, BorderLayout.CENTER);

		            incompletePaymentsFrame.setVisible(true);
		        } else {
		            // Eğer incomplete payment yoksa kullanıcıya bilgi ver
		            JOptionPane.showMessageDialog(null, "No incomplete payments found.");
		        }
		    }
		});

		btnNewButton_9.setOpaque(true);
		btnNewButton_9.setContentAreaFilled(false);
		btnNewButton_9.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_9.setFocusPainted(false);
		btnNewButton_9.setBorderPainted(false);
		btnNewButton_9.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		btnNewButton_9.setBackground(new Color(74, 130, 96));
		btnNewButton_9.setBounds(955, 227, 400, 69);
		buttonPanel.add(btnNewButton_9);
		
		JButton btnNewButton_10 = new JButton("Report") {
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
                if (getModel().isArmed()) {
                    g.setColor(Color.PINK);
                } else {
                    g.setColor(getBackground());
                }
                g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
                super.paintComponent(g);
            }

            @Override
            protected void paintBorder(Graphics g) {
                g.setColor(new Color(74, 130, 96));
                g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
            }
		};
		btnNewButton_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 Thread reportThread = new Thread(distributor);
	                reportThread.start();
			}
		});
		btnNewButton_10.setOpaque(true);
		btnNewButton_10.setContentAreaFilled(false);
		btnNewButton_10.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_10.setFocusPainted(false);
		btnNewButton_10.setBorderPainted(false);
		btnNewButton_10.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		btnNewButton_10.setBackground(new Color(74, 130, 96));
		btnNewButton_10.setBounds(516, 417, 400, 69);
		buttonPanel.add(btnNewButton_10);
		
		
		JPanel buttonPanelSubs = new JPanel();
		buttonPanelSubs.setBackground(new Color(129, 175, 147));
		buttonPanelSubs.setBorder(new RoundedBorder(20, new Color(39, 99, 61), new Color(39, 99, 61)));
		buttonPanelSubs.setBounds(259, 142, 980, 527);
		contentPane.add(buttonPanelSubs);
		buttonPanelSubs.setLayout(null);
		buttonPanelSubs.setVisible(false);
		
		JPanel panel = new JPanel();
		panel.setVisible(false);
		panel.setBackground(new Color(39, 99, 61));
		panel.setBounds(20, 38, 939, 449);
		buttonPanelSubs.add(panel);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(115, 50, 794, 435);
		buttonPanelSubs.add(panel_1);
		panel_1.setVisible(false);
		panel_1.setBackground(new Color(39, 99, 61));
		panel_1.setLayout(null);
		
		
		JButton indivButton = new JButton("Individual") {
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
                if (getModel().isArmed()) {
                    g.setColor(Color.PINK);
                } else {
                    g.setColor(getBackground());
                }
                g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
                super.paintComponent(g);
            }

            @Override
            protected void paintBorder(Graphics g) {
                g.setColor(new Color(74, 130, 96));
                g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
            }
		};
		indivButton.setBounds(130, 40, 725, 202);
		buttonPanelSubs.add(indivButton);
		indivButton.setFocusPainted(false);
		indivButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		indivButton.setOpaque(true);
		indivButton.setBorderPainted(false);
		indivButton.setContentAreaFilled(false);
		indivButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		indivButton.setBackground(new Color(74, 130, 96));
		
		JButton corpBut = new JButton("Corporation") {
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
                if (getModel().isArmed()) {
                    g.setColor(Color.PINK);
                } else {
                    g.setColor(getBackground());
                }
                g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
                super.paintComponent(g);
            }

            @Override
            protected void paintBorder(Graphics g) {
                g.setColor(new Color(74, 130, 96));
                g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
            }
		};
		corpBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				indivButton.setVisible(false);
				corpBut.setVisible(false);
				panel_1.setVisible(true);
			}
		});
		corpBut.setBounds(130, 275, 725, 217);
		buttonPanelSubs.add(corpBut);
		corpBut.setOpaque(true);
		corpBut.setContentAreaFilled(false);
		corpBut.setFont(new Font("Tahoma", Font.PLAIN, 20));
		corpBut.setFocusPainted(false);
		corpBut.setBorderPainted(false);
		corpBut.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		corpBut.setBackground(new Color(74, 130, 96));
		
		indivButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				indivButton.setVisible(false);
				corpBut.setVisible(false);
				panel.setVisible(true);
			}
		});
		
		JTextArea nameIndiv = new JTextArea();
		nameIndiv.setBounds(317, 60, 272, 38);
		//nameIndiv.setBackground(new Color(39, 99, 61));
		//nameIndiv.setBorder(new RoundedBorder(20, new Color(39, 99, 61), new Color(250,250,250)));
		panel.add(nameIndiv);
		
		JTextArea addressIndiv = new JTextArea();
		addressIndiv.setBounds(317, 143, 272, 65);
		panel.add(addressIndiv);
		
		JTextArea cardIndiv = new JTextArea();
		cardIndiv.setBounds(317, 250, 272, 38);
		panel.add(cardIndiv);
		
		JTextArea yearIndiv = new JTextArea();
		yearIndiv.setBounds(416, 325, 71, 31);
		panel.add(yearIndiv);
		
		
		JTextArea ccvIndiv = new JTextArea();
		ccvIndiv.setBounds(518, 325, 71, 31);
		panel.add(ccvIndiv);
		
		JTextArea monthIndiv = new JTextArea();
		monthIndiv.setBounds(317, 325, 71, 31);
		panel.add(monthIndiv);
		
		JLabel lblNewLabelN = new JLabel("Name");
		lblNewLabelN.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabelN.setBounds(316, 32, 119, 31);
		panel.add(lblNewLabelN);
		
		JLabel lblNewLabel_1 = new JLabel("Address");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(317, 117, 119, 31);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Card Nr");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(317, 225, 119, 31);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Month");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(316, 298, 119, 31);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Years");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_4.setBounds(416, 298, 119, 31);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("CCV");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_5.setBounds(516, 298, 119, 31);
		panel.add(lblNewLabel_5);
		
		JButton addButton1 = new JButton("Add") {//individual
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
                if (getModel().isArmed()) {
                    g.setColor(Color.PINK);
                } else {
                    g.setColor(getBackground());
                }
                g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
                super.paintComponent(g);
            }

            @Override
            protected void paintBorder(Graphics g) {
                g.setColor(new Color(74, 130, 96));
                g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
            }
		};
		addButton1.setOpaque(true);
		addButton1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		addButton1.setFocusPainted(false);
		addButton1.setContentAreaFilled(false);
		addButton1.setBorderPainted(false);
		addButton1.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		addButton1.setBackground(new Color(74, 130, 96));
		addButton1.setBounds(475, 380, 113, 50);
		panel.add(addButton1);
		
		JTextArea nameCorp = new JTextArea();
		nameCorp.setBounds(66, 67, 272, 38);
		panel_1.add(nameCorp);
		
		JLabel lblNewLabel_6 = new JLabel("Name");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_6.setBounds(66, 39, 119, 31);
		panel_1.add(lblNewLabel_6);
		
		JLabel lblNewLabel_1_1 = new JLabel("Address");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(66, 115, 119, 31);
		panel_1.add(lblNewLabel_1_1);
		
		JTextArea addressCorp = new JTextArea();
		addressCorp.setBounds(66, 147, 272, 65);
		panel_1.add(addressCorp);
		
		JLabel lblNewLabel_2_1 = new JLabel("Bank Name");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2_1.setBounds(402, 39, 119, 31);
		panel_1.add(lblNewLabel_2_1);
		
		JTextArea bankName = new JTextArea();
		bankName.setBounds(402, 67, 272, 38);
		panel_1.add(bankName);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Bank Code");
		lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2_1_1.setBounds(402, 115, 119, 31);
		panel_1.add(lblNewLabel_2_1_1);
		
		JTextArea bankCode = new JTextArea();
		bankCode.setBounds(402, 147, 272, 38);
		panel_1.add(bankCode);
		
		JLabel lblNewLabel_3_1 = new JLabel("Day");
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_3_1.setBounds(402, 205, 119, 31);
		panel_1.add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_3_2 = new JLabel("Month");
		lblNewLabel_3_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_3_2.setBounds(501, 205, 119, 31);
		panel_1.add(lblNewLabel_3_2);
		
		JLabel lblNewLabel_3_3 = new JLabel("Year");
		lblNewLabel_3_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_3_3.setBounds(601, 205, 119, 31);
		panel_1.add(lblNewLabel_3_3);
		
		JTextArea dayCorp = new JTextArea();
		dayCorp.setBounds(402, 245, 71, 31);
		panel_1.add(dayCorp);
		
		JTextArea monthCorp = new JTextArea();
		monthCorp.setBounds(505, 245, 71, 31);
		panel_1.add(monthCorp);
		
		JTextArea yearCorp = new JTextArea();
		yearCorp.setBounds(603, 245, 71, 31);
		panel_1.add(yearCorp);
		
		JLabel lblNewLabel_2_1_1_1 = new JLabel("Account Number");
		lblNewLabel_2_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2_1_1_1.setBounds(66, 222, 187, 31);
		panel_1.add(lblNewLabel_2_1_1_1);
		
		JTextArea accountNum = new JTextArea();
		accountNum.setBounds(66, 263, 272, 38);
		panel_1.add(accountNum);
		
		JButton addButton2 = new JButton("Add") {
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
                if (getModel().isArmed()) {
                    g.setColor(Color.PINK);
                } else {
                    g.setColor(getBackground());
                }
                g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
                super.paintComponent(g);
            }

            @Override
            protected void paintBorder(Graphics g) {
                g.setColor(new Color(74, 130, 96));
                g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
            }
		};
		addButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = nameCorp.getText();
				String address = addressCorp.getText();
				String bankName1 = bankName.getText();
				int bankCode1 = Integer.parseInt(bankCode.getText());
				int day = Integer.parseInt(dayCorp.getText());
				int month = Integer.parseInt(monthCorp.getText());
				int year = Integer.parseInt(yearCorp.getText());
				int accountNumber = Integer.parseInt(accountNum.getText());

				Subscriber s = new Corporation(name,address,bankCode1,bankName1,day,month,year,accountNumber);
				if(distributor.addSubscriber(s)) {
					JOptionPane.showMessageDialog(null,"Subscriber added." );
					buttonPanel.setVisible(true);
					panel.setVisible(false);
					panel_1.setVisible(false);
					buttonPanelSubs.setVisible(false);
					lblNewLabel.setVisible(true);
					distPanel.setVisible(true);
					
				}
				else {
					JOptionPane.showMessageDialog(null,"Subscriber could not be added." );
					buttonPanel.setVisible(false);
					panel.setVisible(true);
					panel_1.setVisible(false);
					buttonPanelSubs.setVisible(true);
					lblNewLabel.setVisible(false);
					distPanel.setVisible(false);
					
				}
			}
		});
		addButton2.setBounds(565, 315, 113, 50);
		panel_1.add(addButton2);
		addButton2.setOpaque(true);
		addButton2.setContentAreaFilled(false);
		addButton2.setFocusPainted(false);
		addButton2.setBorderPainted(false);
		addButton2.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		addButton2.setBackground(new Color(74, 130, 96));
		addButton2.setFont(new Font("Tahoma", Font.PLAIN, 25));
		panel.setVisible(false);
		panel_1.setVisible(false);
		//addsubcriber butonu
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				buttonPanel.setVisible(false);
				panel.setVisible(false);
				panel_1.setVisible(false);
				buttonPanelSubs.setVisible(true);
				lblNewLabel.setVisible(false);
				distPanel.setVisible(false);
				
			}
		});
		addButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = nameIndiv.getText();
				String address = addressIndiv.getText();
				String card = cardIndiv.getText();
				int month = Integer.parseInt(monthIndiv.getText());
				int year = Integer.parseInt(yearIndiv.getText());
				int ccv = Integer.parseInt(ccvIndiv.getText());
								
				Subscriber s = new Individual(name,address,card,month,year,ccv);
				if(distributor.addSubscriber(s)) {
					JOptionPane.showMessageDialog(null,"Subscriber added." );
					buttonPanel.setVisible(true);
					panel.setVisible(false);
					panel_1.setVisible(false);
					buttonPanelSubs.setVisible(false);
					lblNewLabel.setVisible(true);
					distPanel.setVisible(true);				
				}
				else {
					JOptionPane.showMessageDialog(null,"Subscriber could not be added." );
					buttonPanel.setVisible(false);
					panel.setVisible(true);
					panel_1.setVisible(false);
					buttonPanelSubs.setVisible(true);
					lblNewLabel.setVisible(false);
					distPanel.setVisible(false);					
				}			
			}
		});
	}
}
