package ProjectOne;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.NoSuchElementException;
import javax.swing.*;





public class UI extends JFrame implements ActionListener{

	JButton netflix = new JButton("Netflix");
	JButton Facebook = new JButton("Facebook"); 
	JButton myUW = new JButton("MyUW");
	JButton Niche = new JButton("Niche");
	JButton Spotify = new JButton("Spotify");
	JButton LinkedIn = new JButton("LinkedIn");
	JButton Steam = new JButton("Steam");
	JButton GitHub = new JButton("GitHub");
	JButton add = new JButton("Add");

	/**
	 * Constructor for user interface of website buttons.
	 */
	UI() {
		System.setProperty("webdriver.chrome.driver", "/Applications/chromedriver");
		this.setLayout(new GridLayout(3,4));
		addComponents();
		addActionListener();
		
	}
	
	/**
	 * Adds the components to the frame.
	 */
	public void addComponents() {
		this.add(Facebook);
		this.add(myUW);
		this.add(netflix);
		this.add(Niche);
		this.add(Spotify);
		this.add(LinkedIn);
		this.add(Steam);
		this.add(GitHub);
		this.add(add);
		
	}
	/**
	 * Attaches action listeners to each website button. 
	 */
	public void addActionListener() {
		Facebook.addActionListener(this);
		myUW.addActionListener(this);
		netflix.addActionListener(this);
		Niche.addActionListener(this);
		Spotify.addActionListener(this);
		LinkedIn.addActionListener(this);
		Steam.addActionListener(this);
		GitHub.addActionListener(this);
		add.addActionListener(this);
	}
	/**
	 * Performs an action specific to a certain button clicked. 
	 */
	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() == Facebook) {
				
				String username = (String) AddWebsite.usernames.get("facebook");
				String password = (String) AddWebsite.passwords.get("facebook");
				WebPortalClass facebook = new WebPortalClass(username, password, "https://facebook.com/", "login", "email", "pass");
				facebook.run();
				
			} else if (e.getSource() == myUW) {
				
				String username = (String) AddWebsite.usernames.get("myuw");
				String password = (String) AddWebsite.passwords.get("myuw");
				WebPortalClass wisco = new WebPortalClass(username, password, "https://my.wisc.edu/", "_eventId_proceed", "j_username", "j_password");
				wisco.run();	
				
			} else if (e.getSource() == netflix) {
				
				String username = (String) AddWebsite.usernames.get("netflix");
				String password = (String) AddWebsite.passwords.get("netflix");
				WebPortalClass netflix = new WebPortalClass(username, password, "https://www.netflix.com/login", "button[data-uia=login-submit-button]", "id_userLoginId", "id_password");
				netflix.run();
				
			} else if (e.getSource() == Niche) {
				
				String username = (String) AddWebsite.usernames.get("niche");
				String password = (String) AddWebsite.passwords.get("niche");
				WebPortalClass niche = new WebPortalClass(username, password, "https://www.niche.com/account/login/", "form__submit__btn--submit button button--large button--wide button--full-width form__submit__btn--skinny", "loginEmail", "loginPassword");
				niche.run();
					
			} else if (e.getSource() == Spotify) {
				
				String username = (String) AddWebsite.usernames.get("spotify");
				String password = (String) AddWebsite.passwords.get("spotify");
				WebPortalClass spotify = new WebPortalClass(username, password, "https://accounts.spotify.com/en/login/?continue=https:%2F%2Fwww.spotify.com%2Fus%2Faccount%2Foverview%2F&_locale=en-US", "login-button", "login-username", "login-password");
				spotify.run();
				
			} else if (e.getSource() == LinkedIn) {
				
				String username = (String) AddWebsite.usernames.get("linkedin");
				String password = (String) AddWebsite.passwords.get("linkedin");
				WebPortalClass linkedin = new WebPortalClass(username, password, "https://www.linkedin.com/login?fromSignIn=true&trk=guest_homepage-basic_nav-header-signin", "login", "username", "password");
				linkedin.run();
					
			} else if (e.getSource() == Steam) {
				
				String username = (String) AddWebsite.usernames.get("steam");
				String password = (String) AddWebsite.passwords.get("steam");
				WebPortalClass steam = new WebPortalClass(username, password, "https://store.steampowered.com/login/", "login_btn_signin", "input_username", "input_password");
				steam.run();

						
			} else if (e.getSource() == GitHub) {
				
				String username = (String) AddWebsite.usernames.get("github");
				String password = (String) AddWebsite.passwords.get("github");
				WebPortalClass github = new WebPortalClass(username, password, "https://github.com/login", "commit", "login_field", "password");
				github.run();
					
			} else if (e.getSource() == add) {
				AddWebsite frame = new AddWebsite();
		        frame.setTitle("Login Form");
		        frame.setVisible(true);
		        frame.setBounds(10, 10, 200, 200);
		        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        frame.setLocationRelativeTo(null);
		        frame.setResizable(false);
		        this.dispose();
				
			}
		} catch(NoSuchElementException i) {
			System.out.println("It appears you do not have a username and/or password for that website.");
			System.out.println("Please enter one through the add button.");
		}
		
	}
	
	public static void main(String[] args) {
		
    	UI frame = new UI();
        frame.setTitle("Login Form");
        frame.setVisible(true);
        frame.setBounds(10, 10, 370, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
	}
}
