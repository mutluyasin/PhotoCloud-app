package application;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;


import javax.swing.JLabel;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class UserProfilePage {

	private JPanel contentPane;
	private User userOfPage;
	private JPanel postPanel; 
	private JFrame frame;
	private UserProfilePage thisprofile;
	

	
	public UserProfilePage(User user) {
		this.userOfPage = user;
		UserProfilePage thispage = this;
		this.thisprofile = this;
		
		frame = new JFrame();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(200, 30, 550, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel ppLabel = new JLabel("pp");
		ppLabel.setBounds(10, 22, 121, 142);
		Image image = userOfPage.getProfilePhoto().getImage();
		Image scaledImage = image.getScaledInstance(121, 106, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImage);
		ppLabel.setIcon(scaledIcon); 
		contentPane.add(ppLabel);
		
		JLabel lblNewLabel = new JLabel("Name:");
		lblNewLabel.setBounds(200, 22, 62, 23);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel(userOfPage.getFirstName());
		lblNewLabel_1.setBounds(298, 26, 141, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Surname: ");
		lblNewLabel_2.setBounds(200, 61, 78, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel(userOfPage.getLastName());
		lblNewLabel_3.setBounds(298, 61, 121, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Email: ");
		lblNewLabel_4.setBounds(200, 117, 49, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel(userOfPage.getEmail());
		lblNewLabel_5.setBounds(298, 117, 154, 23);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Username: ");
		lblNewLabel_6.setBounds(200, 92, 78, 14);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel(userOfPage.getNickName());
		lblNewLabel_7.setBounds(298, 92, 121, 14);
		contentPane.add(lblNewLabel_7);
		
		JButton changeButton = new JButton("Change user infos");
		changeButton.setBounds(298, 173, 167, 23);
		contentPane.add(changeButton);
		
		postPanel = new JPanel();
        postPanel.setLayout(new BoxLayout(postPanel, BoxLayout.PAGE_AXIS));

        for(Post p:userOfPage.getPosts()) {
			createPost(p);
			
		}
		
        postPanel.setPreferredSize(new Dimension(442, 300*userOfPage.getPosts().size()));
        
		JScrollPane scrollPane_1 = new JScrollPane(postPanel);
		scrollPane_1.setBounds(10, 209, 500, 393);
		contentPane.add(scrollPane_1); 
		scrollPane_1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		JLabel ageLabel = new JLabel("Age:");
		ageLabel.setBounds(200, 142, 62, 14);
		contentPane.add(ageLabel);
		
		JLabel lblNewLabel_8 = new JLabel(Integer.toString(userOfPage.getAge()));
		lblNewLabel_8.setBounds(298, 142, 88, 14);
		contentPane.add(lblNewLabel_8);
		
		JButton btnNewButton = new JButton("create post");
		btnNewButton.setBounds(137, 173, 125, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("discover");
		btnNewButton_1.setBounds(10, 173, 89, 23);
		contentPane.add(btnNewButton_1);
		
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DiscoverPage page = new DiscoverPage(user);
				frame.dispose();
				
			}
		});
		
		JButton btnNewButton_2 = new JButton("Log out");
		btnNewButton_2.setBounds(449,0,87,30);
		contentPane.add(btnNewButton_2);
		
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				LoginPage page = new LoginPage(null);
				frame.dispose();
				
				  Date date = new Date();

			        SimpleDateFormat sdf = new SimpleDateFormat("E MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);

			        String formattedDate = sdf.format(date);

			        String filePath = "errors_and_infos/application_info.txt";
			        String text = String.format("[%s] [INFO] %s logged out.",
			        		formattedDate,user.getNickName());

			        try (FileWriter fileWriter = new FileWriter(filePath, true)) {
			        	  fileWriter.write(text);
			       	 fileWriter.write(System.lineSeparator());
			          
			           
			        } catch (IOException eror) {
			            eror.printStackTrace();
			        }
			}
		});
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
		
				CreatePost page = new CreatePost(user,null,thisprofile,null);

			}
		});
		
		changeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				
				ChangingPage page = new ChangingPage(null,userOfPage,thispage);

			}
		});
    
	}
	/**
	 * 
	 * @param p
	 */
	
	private void createPost(Post p) {
		
		 
		
		
		 PostPanel postContainer = new PostPanel(p,userOfPage,thisprofile);  
		 postContainer.setLayout(null);
		 postContainer.setSize(100,300);
		
		 postPanel.add(postContainer);
		 postPanel.add(Box.createRigidArea(new Dimension(0, 10)));

		
	}
	
	public JPanel getContentPane() {
		return contentPane;
	}

	public User getUserOfPage() {
		return userOfPage;
	}

	public JPanel getPostPanel() {
		return postPanel;
	}

	public JFrame getFrame() {
		return frame;
	}

	public UserProfilePage getThisprofile() {
		return thisprofile;
	}

	public void disposeFrame() {
		frame.dispose();
	}
}
