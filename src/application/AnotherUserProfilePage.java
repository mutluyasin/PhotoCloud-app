package application;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

public class AnotherUserProfilePage {
	
	private User profilePageUser;
	private JPanel contentPane;
	private JPanel postPanel;
	private JFrame frame;
	private User from;
	private AnotherUserProfilePage thisPage;

	public JPanel getContentPane() {
		return contentPane;
	}

	public JPanel getPostPanel() {
		return postPanel;
	}

	public JFrame getFrame() {
		return frame;
	}

	public User getFrom() {
		return from;
	}

	public AnotherUserProfilePage getThisPage() {
		return thisPage;
	}

	public AnotherUserProfilePage(User profilePageUser,User from) {
		this.profilePageUser = profilePageUser;
		this.from = from;
		this.thisPage = this;
		frame = new JFrame();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(200, 30, 550, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel ppLabel = new JLabel("pp");
		ppLabel.setBounds(0, 11, 121, 142);
		Image image = profilePageUser.getProfilePhoto().getImage();
		Image scaledImage = image.getScaledInstance(121, 106, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImage);
		ppLabel.setIcon(scaledIcon); 
		contentPane.add(ppLabel);
		
		JLabel lblNewLabel = new JLabel("Name:");
		lblNewLabel.setBounds(200, 22, 62, 23);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel(profilePageUser.getFirstName());
		lblNewLabel_1.setBounds(298, 26, 91, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Surname: ");
		lblNewLabel_2.setBounds(200, 61, 78, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel(profilePageUser.getLastName());
		lblNewLabel_3.setBounds(298, 61, 49, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_6 = new JLabel("Username: ");
		lblNewLabel_6.setBounds(200, 92, 78, 14);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel(profilePageUser.getNickName());
		lblNewLabel_7.setBounds(298, 92, 121, 14);
		contentPane.add(lblNewLabel_7);
		
		
		JButton btnNewButton_1 = new JButton("discover");
		btnNewButton_1.setBounds(10, 173, 89, 23);
		contentPane.add(btnNewButton_1);
		
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DiscoverPage page = new DiscoverPage(from);
				frame.dispose();
				
			}
		});
		
		postPanel = new JPanel();
        postPanel.setLayout(new BoxLayout(postPanel, BoxLayout.PAGE_AXIS));
        
        int count = 0;
        for(Post p:profilePageUser.getPosts()) {
        	if(p.isIspublic()) {
        		createPost(p);
        		count++;
        	}
			
		}

        postPanel.setPreferredSize(new Dimension(442, 300*count));
		JScrollPane scrollPane_1 = new JScrollPane(postPanel);
		scrollPane_1.setBounds(10, 209, 500, 393);
		contentPane.add(scrollPane_1);
		scrollPane_1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		
	}

	/**
	 * 
	 * @param p
	 */
	private void createPost(Post p) {
		

		 PostPanel postContainer = new PostPanel(p,from,thisPage); 
		 postContainer.setLayout(null);
		 postContainer.setSize(100,300);
		
		 postPanel.add(postContainer);
		 postPanel.add(Box.createRigidArea(new Dimension(0, 10))); 
	}
	/**
	 * 
	 * @return
	 */
	public User getProfilePageUser() {
		return profilePageUser;
	}

	public void disposeFrame() {
		
		frame.dispose();
	}
	
	
	
	

}
