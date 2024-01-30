package application;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
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

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ChangingPage{

	



	private JPanel contentPane;
	private JTextField nameText;
	private JTextField surnameText;
	private JTextField ageText;
	private JTextField passwordText;
	private ImageIcon profileIcon;
	private JTextField usernameText;
	private JTextField emailText;

	public ChangingPage(String error,User user,UserProfilePage profilepage) {
		
		JDialog dialog = new JDialog();
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
		
		dialog.setBounds(100, 100, 500, 750);
		dialog.getContentPane().setLayout(new BorderLayout());
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		dialog.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel nameLabel = new JLabel("Name: ");
		nameLabel.setBounds(26, 194, 92, 14);
		contentPane.add(nameLabel);
		
		JLabel surnameLabel = new JLabel("Surname:");
		surnameLabel.setBounds(26, 230, 92, 14);
		contentPane.add(surnameLabel);
		
		JLabel ageLabel = new JLabel("Age:");
		ageLabel.setBounds(26, 294, 49, 14);
		contentPane.add(ageLabel);
		
		JLabel passwordLabel = new JLabel("Password: ");
		passwordLabel.setBounds(26, 334, 92, 14);
		contentPane.add(passwordLabel);
		
		JLabel usernameLabel = new JLabel("Username:");
		usernameLabel.setBounds(26, 158, 92, 14);
		contentPane.add(usernameLabel);
		
		JLabel emailLabel = new JLabel("E-mail: ");
		emailLabel.setBounds(26, 269, 103, 14);
		contentPane.add(emailLabel);
		
		usernameText = new JTextField();
		usernameText.setBounds(145, 155, 96, 20);
		usernameText.setText(user.getNickName());
		usernameText.setEditable(false);
		contentPane.add(usernameText);
		usernameText.setColumns(10);
		
		emailText = new JTextField();
		emailText.setBounds(145, 266, 96, 20);
		emailText.setText(user.getEmail());
		contentPane.add(emailText);
		emailText.setColumns(10);
		
		nameText = new JTextField();
		nameText.setText(user.getFirstName());
		nameText.setBounds(145, 191, 96, 20);
		contentPane.add(nameText);
		nameText.setColumns(10);
		
		surnameText = new JTextField();
		surnameText.setText(user.getLastName());
		surnameText.setBounds(145, 227, 96, 20);
		contentPane.add(surnameText);
		surnameText.setColumns(10);
		
		ageText = new JTextField();
		ageText.setText(Integer.toString(user.getAge())); 
		ageText.setBounds(145, 291, 96, 20);
		contentPane.add(ageText);
		ageText.setColumns(10);
		
		passwordText = new JTextField();
		passwordText.setText(user.getPassword());
		passwordText.setBounds(145, 331, 96, 20);
		contentPane.add(passwordText);
		passwordText.setColumns(10);
		
		JButton saveButton = new JButton("Save");
		saveButton.setBounds(181, 451, 89, 23);
		contentPane.add(saveButton);
		
		
		JButton filechooserButton = new JButton("Choose a profile picture");
		filechooserButton.setBounds(152, 102, 174, 23);
		contentPane.add(filechooserButton);
		
		JLabel label = new JLabel();
        label.setBounds(26, 48, 103, 106);
        Image image1 = user.getProfilePhoto().getImage();
        Image scaledImage1 = image1.getScaledInstance(103, 106, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon1 = new ImageIcon(scaledImage1);
        label.setIcon(scaledIcon1);
        profileIcon = null;
		filechooserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
               if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    profileIcon = new ImageIcon(selectedFile.getAbsolutePath());
                    Image image = profileIcon.getImage();
                    Image scaledImage = image.getScaledInstance(103, 106, Image.SCALE_SMOOTH);
                    ImageIcon scaledIcon = new ImageIcon(scaledImage);
                    label.setIcon(scaledIcon);
                   
                }
			}
		});

		 contentPane.add(label);
		 
		 JLabel usertypeLabel = new JLabel("Select User Type: ");
			usertypeLabel.setBounds(26, 403, 130, 14);
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
		        
		        JButton closeButton = new JButton("Close");
		        closeButton.setBounds(181, 524, 92, 32);
		        contentPane.add(closeButton);
		        
		        closeButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                       dialog.dispose(); 
                    }
                });
		        
		        if(error != null) {
					JLabel errorLabel = new JLabel(error); 
					errorLabel.setForeground(new Color(255, 0, 0));
					errorLabel.setBounds(10, 19, 294, 14);
					contentPane.add(errorLabel);
					
				}
		        
		        saveButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					
						
						String name = nameText.getText();
						String surname = surnameText.getText();
						String password = passwordText.getText();
						String email = emailText.getText();
						int age = Integer.parseInt(ageText.getText())  ;
						
						
						
						ImageIcon profilePhoto;
						if(profileIcon == null) {
							profilePhoto = new ImageIcon("photos/pp/default.png"); // default photonn yerini yaz
						}
						else {
							profilePhoto = profileIcon;
						}
						
						
						Tier tier = null;
						
						if(freeButton.isSelected()) {
							tier = Tier.FREE;
						}
						
						else if (hobbyistButton.isSelected()) {
							tier = Tier.HOBBYIST;
						}
						
						else {
							tier = Tier.PROFESSIONAL;
						}
						
						String error = checkValidity(password,email);
						
						  
						if(error == null) {
							user.setFirstName(name);
							user.setLastName(surname);
							user.setAge(age);
							user.setPassword(password);
							user.setEmail(email);
							user.setTier(tier);
							profilepage.disposeFrame();
							UserProfilePage p = new UserProfilePage(user);
							dialog.dispose();
							
							Date date1 = new Date();

					        SimpleDateFormat sdf1 = new SimpleDateFormat("E MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);

					        String formattedDate1 = sdf1.format(date1);

					        String filePath2 = "errors_and_infos/application_info.txt";
					        String text1 = String.format("[%s] [INFO] The user %s changed his/her informations.",
					        		formattedDate1,user.getNickName());

					        try (FileWriter fileWriter = new FileWriter(filePath2, true)) {  
					        	  fileWriter.write(text1);
					       	 fileWriter.write(System.lineSeparator());
					          
					           
					        } catch (IOException eror2) {
					            eror2.printStackTrace();
					        }
							
					    }
					    
					    else {
					    	ChangingPage page = new ChangingPage(error,user,profilepage);
					    	
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
					    	dialog.dispose();
					    }
			
					}
				});
	}
	/**
	 * 
	 * @param password
	 * @param email
	 * @return
	 */
	private String checkValidity(String password,String email) {
		if(checkPassword(password) == null & checkEmail(email) == null) {
			return null;
		}
		else { 
			
			
			if(checkEmail(email) != null) {
				return checkEmail(email);
			}
			
			return checkPassword(password);

		}
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
