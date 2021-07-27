package ProjectOne;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AddWebsite extends JFrame implements ActionListener {
	JButton login = new JButton("Enter");
	static HashTableMap usernames = new HashTableMap();
	static HashTableMap passwords = new HashTableMap();
	JLabel usernameLabel = new JLabel("Username:");
	JLabel passwordLabel = new JLabel("Password:");
	JLabel websiteLabel = new JLabel("Website:");
	JTextField website = new JTextField();
	JTextField username = new JTextField();
	JTextField password = new JTextField();
	
	/**
	 * Constructor for the addWebsite class. 
	 */
	AddWebsite() {
		this.setLayout(null);
		setLocation();
		addComponents();
		addActionListener();
	}
	/**
	 * Sets the location and size for the frame components.
	 */
	public void setLocation() {
		websiteLabel.setBounds(10, 10, 100, 25);
		usernameLabel.setBounds(10, 50, 100, 25);
		passwordLabel.setBounds(10, 100, 100, 25);
		website.setBounds(100, 10, 100, 25);
		username.setBounds(100, 50, 100, 25);
		password.setBounds(100, 100, 100, 25);
		login.setBounds(100, 150, 100, 25);
	}
	/**
	 * Adds the components to the frame.
	 */
	public void addComponents() {
		this.add(websiteLabel);
		this.add(usernameLabel);
		this.add(passwordLabel);
		this.add(website);
		this.add(username);
		this.add(password);
		this.add(login);
	}
	
	/**
	 * Adds an action listener to the login button. 
	 */
	public void addActionListener() {
		login.addActionListener(this);
	}
	/**
	 * Executes an action when the login button is clicked. 
	 * Opens up the UI class frame and closes the AddWebsite frame.
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == login) {
			usernames.put(website.getText(), username.getText());
			passwords.put(website.getText(), password.getText());
		   	UI frame = new UI();
	        frame.setTitle("Login Form (Enter website in all lowercase)");
	        frame.setVisible(true);
	        frame.setBounds(10, 10, 370, 400);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setLocationRelativeTo(null);
	        frame.setResizable(false);
	        this.dispose();
		}
	}
}
