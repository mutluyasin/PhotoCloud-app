package application;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;

public class LoginPage{

	private JPanel contentPane;
	private JTextField usernameText;
	private JTextField passwordText;


	public LoginPage(String error) {
		
		JFrame frame = new JFrame();
		frame.setVisible(true);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 350, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel usernameLabel = new JLabel("Username: ");
		usernameLabel.setBounds(42, 105, 93, 14);
		contentPane.add(usernameLabel);
		
		JLabel passwordLabel = new JLabel("Password: ");
		passwordLabel.setBounds(42, 188, 93, 14);
		contentPane.add(passwordLabel);
		
		usernameText = new JTextField();
		usernameText.setBounds(152, 102, 96, 20);
		contentPane.add(usernameText);
		usernameText.setColumns(10);
		
		passwordText = new JPasswordField();
		passwordText.setBounds(152, 185, 96, 20);
		contentPane.add(passwordText);
		passwordText.setColumns(10);
		
		JButton loginButton = new JButton("Login");
		loginButton.setBounds(114, 250, 89, 23);
		contentPane.add(loginButton);
		
		JLabel lblNewLabel = new JLabel("Dont have an account create one: ");
		lblNewLabel.setBounds(10, 314, 206, 14);
		contentPane.add(lblNewLabel);
		
		JButton signinButton = new JButton("Signin");
		signinButton.setBounds(237, 310, 89, 23);
		contentPane.add(signinButton);
		
		JButton extButton = new JButton("EXIT");
		extButton.setBounds(237, 0, 89, 23);
		contentPane.add(extButton);
		
		extButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				  Date date = new Date();

			        SimpleDateFormat sdf = new SimpleDateFormat("E MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);

			        String formattedDate = sdf.format(date);

			        String filePath = "errors_and_infos/application_info.txt";
			        String text = String.format("[%s] [INFO] Application terminated.",
			        		formattedDate);

			        try (FileWriter fileWriter = new FileWriter(filePath, true)) {
			        	  fileWriter.write(text);
			       	 fileWriter.write(System.lineSeparator());
			          
			           
			        } catch (IOException eror) {
			            eror.printStackTrace();
			        }
				System.exit(0);
				
				
				
			}
		});
		
		if(error != null) {
			JLabel errorLabel = new JLabel(error);
			errorLabel.setForeground(new Color(255, 0, 0));
			errorLabel.setBounds(42, 48, 259, 14);
			contentPane.add(errorLabel);
		}
		
		
		signinButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				SigninPage page = new SigninPage(null);
				frame.dispose();;
				
			}
		});
		
		loginButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
			
				String username = usernameText.getText();
				String password = (String)passwordText.getText();
				
				
				User user = checkValidity(username,password);
				
				if (user == null) {
					LoginPage page = new LoginPage("Invalid username or password.");
					 
					Date date = new Date();

				        SimpleDateFormat sdf = new SimpleDateFormat("E MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);

				        String formattedDate = sdf.format(date);

				        String filePath1 = "errors_and_infos/application_error.txt";
				        String text1 = String.format("[%s] [ERROR] Invalid username or password.",
				        		formattedDate);

				        try (FileWriter fileWriter = new FileWriter(filePath1, true)) {  
				        	  fileWriter.write(text1);
				       	 fileWriter.write(System.lineSeparator());
				          
				           
				        } catch (IOException eror) {
				            eror.printStackTrace();
				        }
				    frame.dispose();
				}
				
				else {
					
					DiscoverPage page = new DiscoverPage(user); 
					
					frame.dispose();
					

			        Date date = new Date();

			        SimpleDateFormat sdf = new SimpleDateFormat("E MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);

			        String formattedDate = sdf.format(date);

			        String filePath = "errors_and_infos/application_info.txt";
			        String text = String.format("[%s] [INFO] %s logged in.",
			        		formattedDate,user.getNickName());

			        try (FileWriter fileWriter = new FileWriter(filePath, true)) {
			        	  fileWriter.write(text);
			       	 fileWriter.write(System.lineSeparator());
			          
			           
			        } catch (IOException eror) {
			            eror.printStackTrace();
			        }
				}
				
			}
		});
		
	}
	/**
	 * 
	 * @param userName
	 * @param password
	 * @return
	 */
	
private User checkValidity(String userName,String password) {
		
		User result = null;
		for(User u:DiscoverPage.getAllUsers()) {
			if(u.getNickName().equals(userName)) {
				if(u.getPassword().equals(password)) {
					result = u;
					break;
				}
				
				else {
					return result;
				}
			}
		}
		
		return result;
		
	}
}
