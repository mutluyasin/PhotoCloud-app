package application;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.Box; 
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;

public class DiscoverPage{

	private JPanel contentPane;
	private static ArrayList<User> allUsers = new ArrayList<>();
	private static HashSet<Post> posts = new HashSet<>();
	private User owner;
	private JPanel postPanel; 
	private DiscoverPage discover;
	private JFrame frame;
	private JTextField searchText;


	public DiscoverPage(User owner) {
		
		this.owner = owner;
		this.discover = this;
		createPosts();
		
		frame = new JFrame();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(200, 10, 550, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		postPanel = new JPanel();
        postPanel.setLayout(new BoxLayout(postPanel, BoxLayout.PAGE_AXIS));

        int count = 0;
        for(Post p:posts) {
        	addPostToPanel(p);
        	count++;
        }
        postPanel.setPreferredSize(new Dimension(442, 300*count));
		
        JScrollPane scrollPane = new JScrollPane(postPanel);
		scrollPane.setBounds(10, 35, 495, 523);
		contentPane.add(scrollPane);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		JLabel lblNewLabel = new JLabel("profile photo");
		lblNewLabel.setBounds(426, 569, 49, 33);
		contentPane.add(lblNewLabel);
		
		ImageIcon icon = owner.getProfilePhoto();
          Image image = icon.getImage();
          Image scaledImage = image.getScaledInstance(49, 33, Image.SCALE_SMOOTH);
          ImageIcon scaledIcon = new ImageIcon(scaledImage);
         lblNewLabel.setIcon(scaledIcon);
		
		JButton btnNewButton = new JButton("My profile");
		btnNewButton.setBounds(315, 574, 101, 23);
		contentPane.add(btnNewButton);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				UserProfilePage page = new UserProfilePage(owner);
				frame.dispose();
				
			}
		});
		
		JButton btnNewButton_1 = new JButton("Add post");
		btnNewButton_1.setBounds(172, 574, 101, 23);
		contentPane.add(btnNewButton_1);
		
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				CreatePost page = new CreatePost(owner,null,null,discover);
			}
		});
		
		
		searchText = new JTextField();
		searchText.setBounds(108, 4, 175, 20);
		contentPane.add(searchText);
		searchText.setColumns(10);
		
		JButton btnNewButton_3 = new JButton("Search");
		btnNewButton_3.setBounds(327, 3, 89, 23);
		contentPane.add(btnNewButton_3);
		
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String username = searchText.getText();
				
				User u = null;
				
				for (User user:allUsers) {
					if(username.equals(user.getNickName())) {
						u = user;
						break;
					}
				}
				
				if(u != null) {
					AnotherUserProfilePage page = new AnotherUserProfilePage(u,owner);
					frame.dispose();
				}
				
				
				
			}
		});
		
		JButton btnNewButton_2 = new JButton("Log out");
		btnNewButton_2.setBounds(36, 579, 89, 23);
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
			        		formattedDate,owner.getNickName());

			        try (FileWriter fileWriter = new FileWriter(filePath, true)) {
			        	  fileWriter.write(text);
			       	 fileWriter.write(System.lineSeparator());
			          
			           
			        } catch (IOException eror) {
			            eror.printStackTrace();
			        }
			}
		});
	}

	/**
	 * 
	 * @return
	 */
	public static ArrayList<User> getAllUsers(){
		return allUsers;
	}
	
	
	private static void createPosts() {
		posts.clear();
		for(User u:allUsers) {
			for(Post p:u.getPosts()) {
				if(p.isIspublic()) {
					posts.add(p);
				
				}
			}
		}
	}
	
	/**
	 * 
	 * @param u
	 */
	public static void addUser(User u) {
		allUsers.add(u);
	
	}
	

	/**
	 * 
	 * @param post
	 */
	private void addPostToPanel(Post post) {
		
		 PostPanel postContainer = new PostPanel(post,owner,discover); 
		 postContainer.setLayout(null);
		 postContainer.setSize(100,300);
		
		 postPanel.add(postContainer);
		 postPanel.add(Box.createRigidArea(new Dimension(0, 10))); 

	}
	
	public User getOwner() {
		return owner;
	}


	public void disposeFrame() {
		frame.dispose();
	}
}
