package application;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.JPanel; 
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import java.awt.List;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.ButtonGroup;
import java.awt.Color;
import javax.swing.ImageIcon;


public class SigninPage{

	private JPanel contentPane;
	private JTextField usernameText;
	private JTextField nameText;
	private JTextField surnameText;
	private JTextField emailText;
	private JTextField ageText;
	private JTextField passwordText;
	private ImageIcon profileIcon;
	private String profileIconPath;
	


	public SigninPage(String error) {
		
		JFrame frame = new JFrame();;
		frame.setVisible(true);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100,500, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel usernameLabel = new JLabel("Username:");
		usernameLabel.setBounds(26, 158, 92, 14);
		contentPane.add(usernameLabel);
		
		JLabel nameLabel = new JLabel("Name: ");
		nameLabel.setBounds(26, 194, 92, 14);
		contentPane.add(nameLabel);
		
		JLabel surnameLabel = new JLabel("Surname:");
		surnameLabel.setBounds(26, 230, 92, 14);
		contentPane.add(surnameLabel);
		
		JLabel emailLabel = new JLabel("E-mail: ");
		emailLabel.setBounds(26, 269, 103, 14);
		contentPane.add(emailLabel);
		
		JLabel ageLabel = new JLabel("Age:");
		ageLabel.setBounds(26, 294, 49, 14);
		contentPane.add(ageLabel);
		
		JLabel passwordLabel = new JLabel("Password: ");
		passwordLabel.setBounds(26, 334, 92, 14);
		contentPane.add(passwordLabel);
		
		usernameText = new JTextField();
		usernameText.setBounds(145, 155, 96, 20);
		contentPane.add(usernameText);
		usernameText.setColumns(10);
		
		nameText = new JTextField();
		nameText.setBounds(145, 191, 96, 20);
		contentPane.add(nameText);
		nameText.setColumns(10);
		
		surnameText = new JTextField();
		surnameText.setBounds(145, 227, 96, 20);
		contentPane.add(surnameText);
		surnameText.setColumns(10);
		
		emailText = new JTextField();
		emailText.setBounds(145, 266, 96, 20);
		contentPane.add(emailText);
		emailText.setColumns(10);
		
		ageText = new JTextField();
		ageText.setBounds(145, 291, 96, 20);
		contentPane.add(ageText);
		ageText.setColumns(10);
		
		passwordText = new JTextField();
		passwordText.setBounds(145, 331, 96, 20);
		contentPane.add(passwordText);
		passwordText.setColumns(10);
		

		JButton extButton = new JButton("EXIT");
		extButton.setBounds(387, 11, 89, 23);
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
		JButton signinButton = new JButton("Signin");
		signinButton.setBounds(181, 451, 89, 23);
		contentPane.add(signinButton);
		
		JLabel alreadyLabel = new JLabel("Already have an account?");
		alreadyLabel.setBounds(63, 501, 172, 14);
		contentPane.add(alreadyLabel);
		
		JButton loginButton = new JButton("Login");
		loginButton.setBounds(293, 497, 89, 23);
		contentPane.add(loginButton);
		
		
		JButton filechooserButton = new JButton("Choose a profile picture");// filechooser classını kullanarak file seç
		filechooserButton.setBounds(152, 102, 174, 23);
		contentPane.add(filechooserButton);
		
		JLabel label = new JLabel(); 
        label.setBounds(26, 48, 103, 106);
        
        profileIcon = null;
        profileIconPath = null;
		filechooserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
               if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    profileIcon = new ImageIcon(selectedFile.getAbsolutePath());
                    profileIconPath = selectedFile.getAbsolutePath(); 
                    Image image = profileIcon.getImage();
                    Image scaledImage = image.getScaledInstance(103, 106, Image.SCALE_SMOOTH);
                    ImageIcon scaledIcon = new ImageIcon(scaledImage);
                    label.setIcon(scaledIcon);
                   
                }
			}
		});

		 contentPane.add(label);
		
		JLabel usertypeLabel = new JLabel("Select User Type: ");
		usertypeLabel.setBounds(26, 403, 103, 14);
		contentPane.add(usertypeLabel);
		
		JRadioButton freeButton = new JRadioButton("Free");
		freeButton.setSelected(true);
		freeButton.setBounds(148, 399, 93, 23);
		contentPane.add(freeButton);
		
		JRadioButton hobbyistButton = new JRadioButton("Hobbyist");
		hobbyistButton.setBounds(239, 399, 111, 23);
		contentPane.add(hobbyistButton);
		
		JRadioButton professionalButton = new JRadioButton("Professional");
		professionalButton.setBounds(352, 399, 128, 23);
		contentPane.add(professionalButton);
		
		 ButtonGroup buttonGroup = new ButtonGroup();
	        buttonGroup.add(freeButton);
	        buttonGroup.add(hobbyistButton);
	        buttonGroup.add(professionalButton);
	        
		
		
		 if(error != null) {
			JLabel errorLabel = new JLabel(error); 
			errorLabel.setForeground(new Color(255, 0, 0));
			errorLabel.setBounds(10, 19, 294, 14);
			contentPane.add(errorLabel);
			
		}

		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				LoginPage page = new LoginPage(null);
				frame.dispose();
				
			}
		});
		
		signinButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				String name = nameText.getText();
				String surname = surnameText.getText();
				String username = usernameText.getText();
				String email = emailText.getText();
				String password = passwordText.getText();
				int age = 0;
				try {
					age = Integer.parseInt(ageText.getText());
				}
				catch(NumberFormatException error) {
					 Date date1 = new Date();

				        SimpleDateFormat sdf1 = new SimpleDateFormat("E MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);

				        String formattedDate1 = sdf1.format(date1);

				        String filePath2 = "errors_and_infos/application_error.txt";
				        String text1 = String.format("[%s] [ERROR] %s",
				        		formattedDate1,error);

				        try (FileWriter fileWriter = new FileWriter(filePath2, true)) {  
				        	  fileWriter.write(text1);
				       	 fileWriter.write(System.lineSeparator());
				          
				           
				        } catch (IOException eror2) {
				            eror2.printStackTrace();
				        }
				}
				
				
				
				ImageIcon profilePhoto;
				if(profileIcon == null) {
					profilePhoto = new ImageIcon("photos/pp/default.png"); // default photonn yerini yaz
				}
				else {
					profilePhoto = profileIcon;
				}
				
				
				Tier tier = null;
				String tier1 = null;
				if(freeButton.isSelected()) {
					tier = Tier.FREE;
					tier1 = "Free";
				}
				
				else if (hobbyistButton.isSelected()) {
					tier = Tier.HOBBYIST;
					tier1 = "Hobbyist";
				}
				
				else {
					tier = Tier.PROFESSIONAL;
					tier1 = "Professional";
				}
				
				String error = checkValidity(username,password,email);
				  
				if(error == null) {
			    	User user = new User(username,password,name,surname,age,email,profilePhoto,tier);
			    	UserProfilePage page = new UserProfilePage(user);
			    	if(profileIconPath == null) {
			    		profileIconPath = "photos/pp/default.png";
			    	}
			    	
			    	 String filePath = "database/users.txt";
			         String text = String.format("%s,%s,%s,%s,%s,%s,%s,%s",
			        		 username,password,name,surname,age,email,profileIconPath,tier1);

			         try (FileWriter fileWriter = new FileWriter(filePath, true)) {
			        	 fileWriter.write(text);
			        	 fileWriter.write(System.lineSeparator());
			            
			            
			         } catch (IOException eror) {
			        	 Date date1 = new Date();

					        SimpleDateFormat sdf1 = new SimpleDateFormat("E MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);

					        String formattedDate1 = sdf1.format(date1);

					        String filePath2 = "errors_and_infos/application_error.txt";
					        String text1 = String.format("[%s] [ERROR] %s",
					        		formattedDate1,eror);

					        try (FileWriter fileWriter = new FileWriter(filePath2, true)) {  
					        	  fileWriter.write(text1);
					       	 fileWriter.write(System.lineSeparator());
					          
					           
					        } catch (IOException eror2) {
					            eror2.printStackTrace();
					        }
			         }
			    
			    	frame.dispose();
			    }
			    
			    else {
			    	SigninPage page = new SigninPage(error);
			    	frame.dispose();
			    	Date date = new Date();

			        SimpleDateFormat sdf = new SimpleDateFormat("E MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);

			        String formattedDate = sdf.format(date);

			        String filePath1 = "errors_and_infos/application_error.txt";
			        String text1 = String.format("[%s] [ERROR] %s",
			        		formattedDate,error);

			        try (FileWriter fileWriter = new FileWriter(filePath1, true)) {  
			        	  fileWriter.write(text1);
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
	 * @param username
	 * @param password
	 * @param email
	 * @return
	 */
	private String checkValidity(String username,String password,String email) {
		if(checkUsername(username)== null & checkPassword(password) == null & checkEmail(email) == null) {
			return null;
		}
		else { 
			if (checkUsername(username) != null) {
				return checkUsername(username);
			}
			
			if(checkEmail(email) != null) {
				return checkEmail(username);
			}
			
			return checkPassword(password);

		}
	}
	/**
	 * 
	 * @param username
	 * @return
	 */
	
	private String checkUsername(String username) {
		
		if(username.length() == 0) {
			return "Username cannot be left blank.";
		}
		
		if(Character.isLetter(username.charAt(0))) {
			ArrayList<Character> signs = new ArrayList<>(Arrays.asList('-','_','.'));
			
			for(int i = 0;i < username.length();i++) {
				if(Character.isLetter(username.charAt(i)) || Character.isDigit(username.charAt(i))){
					
				}
				
				else {
					if(signs.contains(username.charAt(i))) {
								
					} 
					
					else {
						return "Username cannot contain special character.";
					}
					
				}
			}
			
			if(username.length() > 3 ) {
				
				
				for(User u:DiscoverPage.getAllUsers()) {
					if(u.getNickName().equals(username)) {
						return "The username is taken.";
					}
				}
				
				return null;
			}
			
			return "Username length must be longer than 3.";
			
			

		}
		
		else { return "Username must start with a letter."; }
		
	 
	}
	/**
	 * 
	 * @param password
	 * @return
	 */
	
	private String checkPassword(String password) {
		
		if(password.length() == 0) {
			return "Password cannot be left blank.";
		}
		
		if(6 < password.length() & password.length() < 12) {
			ArrayList<Character> signs = new ArrayList<>(Arrays.asList('-','_','.','?','!'));
			for(int i = 0;i < password.length();i++) {
				if(Character.isLetter(password.charAt(i)) || Character.isDigit(password.charAt(i))){
					
				}
				
				else {
					if(signs.contains(password.charAt(i))) {
								
					} 
					
					else {
						return "Password cannot contain special character.";
					}
					
				}
			}
			
			return null;
		}
		
		else {return "Password length must be between 6 and 12.";}
		
		
	}
	/**
	 * 
	 * @param email
	 * @return
	 */
	private String checkEmail(String email) {
		
		if(email.length() == 0) {
			return "E-mail cannot be left blank.";
		}
		
		int index = email.indexOf('@');
		if (index == -1) {return "Invalid email.";}
		else {
			int index2 = email.indexOf('.', index);
			if(index2 == -1) {return "Invalid email.";}
			else {
				String sub = email.substring(index2,index2 + 4);
				if(sub.equals(".com")) {
					
					
					for(User u:DiscoverPage.getAllUsers()) {
						if(u.getEmail().equals(email)) {
							return "The email is taken.";}
						}
					
					return null;
					}
				
				else {return "Invalid email.";}
			}
		}
		
	}
}
