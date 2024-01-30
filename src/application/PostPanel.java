package application;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JRadioButton;

public class PostPanel extends JPanel {

	private JPanel commentPanel;
	private Post post;
	private JTextField textField;
	private Object o;
	private User user;
	private JTextField description;
	private JTextArea comment;
	private ImageIcon likedIcon;
	private ImageIcon unlikedIcon;
	private ImageIcon dislikedIcon;
	private ImageIcon undislikedIcon;
	private ImageIcon removeIcon;
	private ImageIcon saveIcon;
	
	
	public PostPanel(Post post,User user,Object o) {
		
		this.user = user;
		this.o = o;
	
		this.post = post;
		this.comment = new JTextArea();
		setLayout(null);
		setSize(500,300);
		
		likedIcon  = new ImageIcon("icons/likedIcon.png");
		Image image_1 = likedIcon.getImage();
		Image scaledImage_1 = image_1.getScaledInstance(23, 23, Image.SCALE_SMOOTH);
		likedIcon = new ImageIcon(scaledImage_1);
		
		unlikedIcon  = new ImageIcon("icons/unlikedIcon.png");
		Image image_2 = unlikedIcon.getImage();
		Image scaledImage_2 = image_2.getScaledInstance(23, 23, Image.SCALE_SMOOTH);
		unlikedIcon = new ImageIcon(scaledImage_2);
		
		dislikedIcon  = new ImageIcon("icons/dislikedIcon.png");
		Image image_3 = dislikedIcon.getImage();
		Image scaledImage_3 = image_3.getScaledInstance(23, 23, Image.SCALE_SMOOTH);
		dislikedIcon = new ImageIcon(scaledImage_3);
		
		undislikedIcon  = new ImageIcon("icons/undislikedIcon.png");
		Image image_4 = undislikedIcon.getImage();
		Image scaledImage_4 = image_4.getScaledInstance(23, 23, Image.SCALE_SMOOTH);
		undislikedIcon = new ImageIcon(scaledImage_4);
		
		
		removeIcon = new ImageIcon("icons/removeIcon.png");
		Image image_5 = removeIcon.getImage();
		Image scaledImage_5 = image_5.getScaledInstance(32, 34, Image.SCALE_SMOOTH);
		removeIcon = new ImageIcon(scaledImage_5);
		
		saveIcon = new ImageIcon("icons/saveIcon.jpg");
		Image image_6 = saveIcon.getImage();
		Image scaledImage_6 = image_6.getScaledInstance(32, 34, Image.SCALE_SMOOTH);
		saveIcon = new ImageIcon(scaledImage_6);
	
		JLabel lblNewLabel = new JLabel("foti");
		lblNewLabel.setBounds(10, 28, 248, 193);
		Image image = post.getPostPhoto().getImage();
		Image scaledImage = image.getScaledInstance(248, 238, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImage);
		lblNewLabel.setIcon(scaledIcon); 
		add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel(Integer.toString(post.getLikeCount())+" Liked");
		lblNewLabel_2.setBounds(53, 270, 81, 14);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_4 = new JLabel(Integer.toString(post.getDislikeCount())+" Disliked");
		lblNewLabel_4.setBounds(192, 270, 89, 14);
		add(lblNewLabel_4);
		
		commentPanel = new JPanel();
		commentPanel.setLayout(new BoxLayout(commentPanel, BoxLayout.PAGE_AXIS));
		
		int i = addComments(post.getComments());
		
		 commentPanel.setPreferredSize(new Dimension(300, 130*i));
		
		JScrollPane scrollPane = new JScrollPane(commentPanel);
		scrollPane.setBounds(268, 28, 210, 146);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		 scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		add(scrollPane);
		
		JLabel lblNewLabel_6 = new JLabel("Comments");
		lblNewLabel_6.setBounds(266, 3, 76, 14);
		add(lblNewLabel_6);
		
		textField = new JTextField();
		textField.setBounds(278, 185, 200, 23);
		add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton_3 = new JButton("Comment");
		btnNewButton_3.setBounds(275, 219, 107, 23);
		add(btnNewButton_3);
		
		JLabel lblNewLabel_7 = new JLabel(Integer.toString(post.getCommentCount()));
		lblNewLabel_7.setBounds(346, 3, 49, 14);
		add(lblNewLabel_7);
		
		JButton btnNewButton_4 = new JButton("Like: ");
		btnNewButton_4.setBounds(20, 266, 23, 23);
		add(btnNewButton_4);
		btnNewButton_4.setIcon(unlikedIcon);
		
		JButton btnNewButton_5 = new JButton("Dislike: ");
		btnNewButton_5.setBounds(159, 266, 23, 23);
		add(btnNewButton_5);
		btnNewButton_5.setIcon(undislikedIcon);
		
		if(post.getLikedUsers() != null & post.getDislikedUsers() != null) {
			if(post.getLikedUsers().contains(user)) {
				btnNewButton_4.setEnabled(false);
				btnNewButton_4.setDisabledIcon(likedIcon);
				
			}
			
			else if (post.getDislikedUsers().contains(user)) {
				btnNewButton_5.setEnabled(false);
				btnNewButton_5.setDisabledIcon(dislikedIcon);
			}
		}
		
		
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				post.increaseLike(user);
				lblNewLabel_2.setText(Integer.toString(post.getLikeCount())+" Liked");
				lblNewLabel_4.setText(Integer.toString(post.getDislikeCount())+" Disliked");
				btnNewButton_4.setEnabled(false);
				btnNewButton_4.setDisabledIcon(likedIcon);
				btnNewButton_5.setEnabled(true);
				btnNewButton_5.setIcon(undislikedIcon);
			}
		});
		
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				post.increaseDislike(user);
				lblNewLabel_4.setText(Integer.toString(post.getDislikeCount())+" Disliked");
				lblNewLabel_2.setText(Integer.toString(post.getLikeCount())+" Liked");
				btnNewButton_5.setEnabled(false);
				btnNewButton_5.setDisabledIcon(dislikedIcon);
				btnNewButton_4.setEnabled(true);
				btnNewButton_4.setIcon(unlikedIcon);
			}
		});
		
		JButton btnNewButton_6 = new JButton(post.getPostOwner().getNickName()+"("+post.getPostOwner().getTier()+")");
		btnNewButton_6.setBounds(0, -1, 207, 23); 
		add(btnNewButton_6);
		
		if(!(post.getDescription().equals(""))) {
			description = new JTextField();
			description.setEditable(false);
			description.setText(post.getDescription());
			description.setBounds(10, 232, 248, 27);
			add(description);
			description.setColumns(10);
		}
		
		
		if(o instanceof UserProfilePage) {
			
			
			scrollPane.setBounds(268, 28, 135, 146);
			textField.setBounds(278, 185, 117, 23);
			
			UserProfilePage a = ((UserProfilePage)o);
			
			scrollPane.setBounds(268, 28, 135, 146);
			textField.setBounds(278, 185, 117, 23);
			
			
			JButton btnNewButton_1 = new JButton("apply");
			btnNewButton_1.setBounds(405, 124, 75, 33);
			add(btnNewButton_1);
			
			JRadioButton rdbtnNewRadioButton = new JRadioButton("Private");
			rdbtnNewRadioButton.setBounds(403, 76, 76, 23);
			add(rdbtnNewRadioButton);
			
			JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Public");
			rdbtnNewRadioButton_1.setBounds(403, 105, 93, 23);
			add(rdbtnNewRadioButton_1);
			
			 ButtonGroup buttonGroup = new ButtonGroup();
		        buttonGroup.add(rdbtnNewRadioButton);
		        buttonGroup.add(rdbtnNewRadioButton_1);
			
			JButton btnNewButton_2 = new JButton("Save");
			btnNewButton_2.setBounds(415, 28, 34, 34);
			btnNewButton_2.setIcon(saveIcon);
			add(btnNewButton_2);
			
			btnNewButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
			
					
					 
					Image image = post.getPostPhoto().getImage();
					
				
					  int width = image.getWidth(null);
			            int height = image.getHeight(null);

			           
			            BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

			          
			            Graphics2D graphics = bufferedImage.createGraphics();

			         
			            graphics.drawImage(image, 0, 0,width,height, null);
			            graphics.dispose();

			            String	name = post.getPostOwner().getNickName()+"_"+post.getFilter()+"_"+post.getPhotoName();
			          
				
					
					ImageSecretary.writeImageToResources(bufferedImage, name);
					
					Date date = new Date();

			        SimpleDateFormat sdf = new SimpleDateFormat("E MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);

			        String formattedDate = sdf.format(date);

			        String filePath = "errors_and_infos/application_info.txt";
			        String text = String.format("[%s] [INFO] The photo %s saved.",
			        		formattedDate,name);

			        try (FileWriter fileWriter = new FileWriter(filePath, true)) {
			        	  fileWriter.write(text);
			       	 fileWriter.write(System.lineSeparator());
			          
			           
			        } 
			        catch (IOException eror) {
			            eror.printStackTrace();
			        }
					
				}
			});
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					if(rdbtnNewRadioButton_1.isSelected()) {
						post.setIspublic(true);
						
						  Date date = new Date();

					        SimpleDateFormat sdf = new SimpleDateFormat("E MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);

					        String formattedDate = sdf.format(date);

					        String filePath = "errors_and_infos/application_info.txt";
					        String text = String.format("[%s] [INFO] A post was shared by %s.",
					        		formattedDate,post.getPostOwner().getNickName());

					        try (FileWriter fileWriter = new FileWriter(filePath, true)) {
					        	  fileWriter.write(text);
					       	 fileWriter.write(System.lineSeparator());
					          
					           
					        } catch (IOException eror) {
					            eror.printStackTrace();
					        }
					}
					else {
						post.setIspublic(false);
					}
				}
			});
			JButton removeButton = new JButton("Remove");
			removeButton.setBounds(450, 28, 34, 34);
			removeButton.setIcon(removeIcon);
			add(removeButton);
			
			removeButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					post.getPostOwner().removePost(post);
					UserProfilePage page = new UserProfilePage(a.getUserOfPage());
					a.disposeFrame();
					
					  Date date = new Date();

				        SimpleDateFormat sdf = new SimpleDateFormat("E MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);

				        String formattedDate = sdf.format(date);

				        String filePath = "errors_and_infos/application_info.txt";
				        String text = String.format("[%s] [INFO] A post was deleted by %s.",
				        		formattedDate,a.getUserOfPage().getNickName());

				        try (FileWriter fileWriter = new FileWriter(filePath, true)) {
				        	  fileWriter.write(text);
				       	 fileWriter.write(System.lineSeparator());
				          
				           
				        } catch (IOException eror) {
				            eror.printStackTrace();
				        }
				}
			});
		}
		
		if(o instanceof DiscoverPage) {
			DiscoverPage a = ((DiscoverPage)o);
			if(a.getOwner() instanceof AdminUser) {
				scrollPane.setBounds(268, 28, 135, 146);
				textField.setBounds(278, 185, 117, 23);
				JButton removeButton = new JButton("Remove");
				removeButton.setBounds(450, 28, 34, 34);
				removeButton.setIcon(removeIcon);
				add(removeButton);
				
				removeButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						post.getPostOwner().removePost(post);
						DiscoverPage page = new DiscoverPage(a.getOwner());
						a.disposeFrame();
						
						  Date date = new Date();

					        SimpleDateFormat sdf = new SimpleDateFormat("E MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);

					        String formattedDate = sdf.format(date);

					        String filePath = "errors_and_infos/application_info.txt";
					        String text = String.format("[%s] [INFO] A %s's post was deleted by admin.",
					        		formattedDate,post.getPostOwner().getNickName());

					        try (FileWriter fileWriter = new FileWriter(filePath, true)) {
					        	  fileWriter.write(text);
					       	 fileWriter.write(System.lineSeparator());
					          
					           
					        } catch (IOException eror) {
					            eror.printStackTrace();
					        }
					}
				});
			}
			
			
			
		}
		
		if(o instanceof AnotherUserProfilePage) {
			AnotherUserProfilePage a = ((AnotherUserProfilePage)o);
			if(a.getFrom() instanceof AdminUser) {
				scrollPane.setBounds(268, 28, 135, 146);
				textField.setBounds(278, 185, 117, 23);
				JButton removeButton = new JButton("Remove");
				removeButton.setBounds(450, 28, 34, 34);
				removeButton.setIcon(removeIcon);
				add(removeButton);
				
				removeButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						post.getPostOwner().removePost(post);
						AnotherUserProfilePage page = new AnotherUserProfilePage(a.getProfilePageUser(),a.getFrom());
						a.disposeFrame();
						
						  Date date = new Date();

					        SimpleDateFormat sdf = new SimpleDateFormat("E MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);

					        String formattedDate = sdf.format(date);

					        String filePath = "errors_and_infos/application_info.txt";
					        String text = String.format("[%s] [INFO] A %s's post was deleted by admin.",
					        		formattedDate,post.getPostOwner().getNickName());

					        try (FileWriter fileWriter = new FileWriter(filePath, true)) {
					        	  fileWriter.write(text);
					       	 fileWriter.write(System.lineSeparator());
					          
					           
					        } catch (IOException eror) {
					            eror.printStackTrace();
					        }
					}
				});
			}
			
			
			
		}
		
		
		
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

				if(o instanceof DiscoverPage) {
					DiscoverPage a = ((DiscoverPage)o);
					a.disposeFrame();
					
				}
				
				if(o instanceof UserProfilePage) {
					UserProfilePage a = ((UserProfilePage)o);
					a.disposeFrame();
					
				}
				
				if(o instanceof AnotherUserProfilePage) {
					AnotherUserProfilePage a = ((AnotherUserProfilePage)o);
					a.disposeFrame();
					
					
				} 
				
				if(post.getPostOwner() == user) {
					
					UserProfilePage page = new UserProfilePage(user);
					
				}
				
				else {
					
					AnotherUserProfilePage page = new AnotherUserProfilePage(post.getPostOwner(),user);
				}
			}
		});
		
		
		

		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String s = textField.getText();
				post.addComment(user, s);
				
				
				
				if(o instanceof DiscoverPage) {
					DiscoverPage a = ((DiscoverPage)o);
					a.disposeFrame();
					new DiscoverPage(a.getOwner());
				}
				
				if(o instanceof UserProfilePage) {
					UserProfilePage a = ((UserProfilePage)o);
					a.disposeFrame();
					new UserProfilePage(a.getUserOfPage());
				}
				
				if(o instanceof AnotherUserProfilePage) {
					AnotherUserProfilePage a = ((AnotherUserProfilePage)o);
					a.disposeFrame();
					new AnotherUserProfilePage(a.getProfilePageUser(),user);
					
				}
				
			}
		});
		
		
	
 
	}
	/**
	 * 
	 * @param comments
	 * @return
	 */
	private int addComments(ArrayList<ArrayList<String>> comments) { 
		
		
		int i = 0;
		for(ArrayList<String> com:comments) {
			String username = com.get(0);
			String commenttext = com.get(1);
			comment.setText(comment.getText()+username+": "+commenttext+"\n");
			comment.setEditable(false);
			comment.setSize(100,30);
			
			
			commentPanel.add(comment);
			commentPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Add spacing between comments
			i++;
		}
		
		return i;
		
		
	}
}
