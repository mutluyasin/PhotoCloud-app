package application;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JSlider;

public class CreatePost{

	private final JPanel contentPanel = new JPanel();
	private ImageIcon postIcon;
	private String error;
	private UserProfilePage profile;
	private File selectedFile;
	private DiscoverPage discover;
	private String postIconPath;
	private User user;
	private long startTime;
	private long endTime;
	private long duration;
	private String filter;
	private BufferedImage image1;
	private int blurValue = 0;
	private int sharpenValue = 0;
	private int brightnessValue = 45;
	private int contrastValue = 40;
	private int grayscaleValue = 0;
	private int edgedetectionValue = 0;
	

	
	public CreatePost(User user,String error,UserProfilePage profile,DiscoverPage discover) { 
		this.error = error;
		this.profile = profile;
		this.discover = discover;
		this.user = user;
		
		JDialog dialog = new JDialog();
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
		dialog.setBounds(100, 100, 450, 600);
		dialog.getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		dialog.getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(22, 24, 261, 184);
		
		JButton btnNewButton = new JButton("Select photo");
		btnNewButton.setBounds(293, 24, 116, 23);
		contentPanel.add(btnNewButton);
		
		postIcon = null;
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
               if (returnValue == JFileChooser.APPROVE_OPTION) {
                    selectedFile = fileChooser.getSelectedFile();
                 
                   postIcon = new ImageIcon(selectedFile.getAbsolutePath());
                   postIconPath = selectedFile.getAbsolutePath();
                    Image image = postIcon.getImage();
                    Image scaledImage = image.getScaledInstance(261, 184, Image.SCALE_SMOOTH);
                    ImageIcon scaledIcon = new ImageIcon(scaledImage);
                    lblNewLabel.setIcon(scaledIcon);
                    
                    Date date = new Date();

        	        SimpleDateFormat sdf = new SimpleDateFormat("E MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);

        	        String formattedDate = sdf.format(date);

        	        String filePath = "errors_and_infos/application_info.txt";
        	        String text = String.format("[%s] [INFO] %s file read.",
        	        		formattedDate,postIconPath);

        	        try (FileWriter fileWriter = new FileWriter(filePath, true)) {
        	        	  fileWriter.write(text);
        	       	 fileWriter.write(System.lineSeparator());
        	          
        	           
        	        } catch (IOException eror) {
        	            eror.printStackTrace();
        	        }
                   
                }
			}
		});

		contentPanel.add(lblNewLabel);
		
		
		JLabel lblNewLabel_1 = new JLabel("Description: ");
		lblNewLabel_1.setBounds(293, 70, 85, 14);
		contentPanel.add(lblNewLabel_1);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(293, 96, 133, 91);
		contentPanel.add(textArea);
		
		JButton btnNewButton_1 = new JButton("Create post");
		btnNewButton_1.setBounds(292, 506, 134, 46);
		contentPanel.add(btnNewButton_1);
		
		
		if(error != null) {
			JLabel lblNewLabel_2 = new JLabel(error);
			lblNewLabel_2.setForeground(new Color(255,0,0));
			lblNewLabel_2.setBounds(54, 11, 249, 14);
			contentPanel.add(lblNewLabel_2);
		
		}
		
		
		
			JSlider blurSlider = new JSlider(0,100,0);
			blurSlider.setMajorTickSpacing(25);
			blurSlider.setMinorTickSpacing(10);
			blurSlider.setPaintTicks(true);
			blurSlider.setBounds(135, 255, 200, 31);
			contentPanel.add(blurSlider);
			
			
			
			JSlider sharpenSlider = new JSlider(0,100,0);
			sharpenSlider.setMajorTickSpacing(25);
			sharpenSlider.setPaintTicks(true);
			sharpenSlider.setMinorTickSpacing(10);
			sharpenSlider.setBounds(135, 305, 200, 19);
			contentPanel.add(sharpenSlider);
		
			
			JLabel lblNewLabel_3 = new JLabel("Blur: ");
			lblNewLabel_3.setBounds(22, 262, 77, 14);
			contentPanel.add(lblNewLabel_3);
			
			JLabel lblNewLabel_4 = new JLabel("Sharpen: ");
			lblNewLabel_4.setBounds(22, 310, 69, 14);
			contentPanel.add(lblNewLabel_4);
			
			blurSlider.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					
					try {
						
						BufferedImage image1 = ImageSecretary.readResourceImage(selectedFile.getAbsolutePath()).getBufferedImage();
						
						image1 = Filters.makeSharp(image1, sharpenValue);
						image1 = Filters.makeEdgeDetection(image1, edgedetectionValue);
						image1 = Filters.makeGrayscale(image1, grayscaleValue);
						image1 = Filters.changeBrightness(image1, brightnessValue);
						image1 = Filters.changeConntrast(image1, contrastValue);
						startTime = System.currentTimeMillis();
					BufferedImage	image = Filters.makeBlur(image1, blurSlider.getValue());
					blurValue = blurSlider.getValue();
						endTime = System.currentTimeMillis();
						duration = endTime-startTime;
						filter = "Bluring";
		                    Image scaledImage = image.getScaledInstance(261, 184, Image.SCALE_SMOOTH);
		                    ImageIcon scaledIcon = new ImageIcon(scaledImage);
		                    postIcon = scaledIcon;
		                    lblNewLabel.setIcon(scaledIcon);
					} catch (IOException  | NullPointerException e1) {
						// TODO Auto-generated catch block
						  
	                    Date date = new Date();

	        	        SimpleDateFormat sdf = new SimpleDateFormat("E MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);

	        	        String formattedDate = sdf.format(date);

	        	        String filePath = "errors_and_infos/application_error.txt";
	        	        String text = String.format("[%s] [ERROR] %s",
	        	        		formattedDate,e1);

	        	        try (FileWriter fileWriter = new FileWriter(filePath, true)) {
	        	        	  fileWriter.write(text);
	        	       	 fileWriter.write(System.lineSeparator());
	        	          
	        	           
	        	        } catch (IOException eror) {
	        	            eror.printStackTrace();
	        	        }
					}
				}
			});
			
			sharpenSlider.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					
					try {
						
						BufferedImage image1 = ImageSecretary.readResourceImage(selectedFile.getAbsolutePath()).getBufferedImage();
						image1 = Filters.makeBlur(image1, blurValue);
						
						image1 = Filters.makeEdgeDetection(image1, edgedetectionValue);
						image1 = Filters.makeGrayscale(image1, grayscaleValue);
						image1 = Filters.changeBrightness(image1, brightnessValue);
						image1 = Filters.changeConntrast(image1, contrastValue);
						startTime = System.currentTimeMillis();
						BufferedImage image = Filters.makeSharp(image1, sharpenSlider.getValue());
						sharpenValue = sharpenSlider.getValue();
						endTime = System.currentTimeMillis();
						duration = endTime-startTime;
						filter = "Sharpening";
		                    Image scaledImage = image.getScaledInstance(261, 184, Image.SCALE_SMOOTH);
		                    ImageIcon scaledIcon = new ImageIcon(scaledImage);
		                    postIcon = scaledIcon;
		                    lblNewLabel.setIcon(scaledIcon);
					} catch (IOException  | NullPointerException e1) {
						// TODO Auto-generated catch block
						  
	                    Date date = new Date();

	        	        SimpleDateFormat sdf = new SimpleDateFormat("E MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);

	        	        String formattedDate = sdf.format(date);

	        	        String filePath = "errors_and_infos/application_error.txt";
	        	        String text = String.format("[%s] [ERROR] %s",
	        	        		formattedDate,e1);

	        	        try (FileWriter fileWriter = new FileWriter(filePath, true)) {
	        	        	  fileWriter.write(text);
	        	       	 fileWriter.write(System.lineSeparator());
	        	          
	        	           
	        	        } catch (IOException eror) {
	        	            eror.printStackTrace();
	        	        }
					}
				}
			}); 
			
			JLabel lblNewLabel_7 = new JLabel("Contrast: ");
			lblNewLabel_7.setBounds(22, 370, 77, 14);
			contentPanel.add(lblNewLabel_7);
			
			JLabel lblNewLabel_8 = new JLabel("Brightness: ");
			lblNewLabel_8.setBounds(10, 415, 115, 14);
			contentPanel.add(lblNewLabel_8);
			
			JSlider contrastSlider = new JSlider(0,100,40);
			contrastSlider.setMajorTickSpacing(25);
			contrastSlider.setPaintTicks(true);
			contrastSlider.setMinorTickSpacing(10);
			contrastSlider.setBounds(135, 365, 200, 19);
			contentPanel.add(contrastSlider);
			
			JSlider brightnessSlider = new JSlider(0,100,45);
			brightnessSlider.setMajorTickSpacing(25);
			brightnessSlider.setPaintTicks(true);
			brightnessSlider.setMinorTickSpacing(10);
			brightnessSlider.setBounds(135, 410, 200, 19);
			contentPanel.add(brightnessSlider);
			
		
			
			
			
			
			
			brightnessSlider.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					
					try {
					
						BufferedImage image1 = ImageSecretary.readResourceImage(selectedFile.getAbsolutePath()).getBufferedImage();
						image1 = Filters.makeBlur(image1, blurValue);
						image1 = Filters.makeSharp(image1, sharpenValue);
						image1 = Filters.makeEdgeDetection(image1, edgedetectionValue);
						image1 = Filters.makeGrayscale(image1, grayscaleValue);
						
						image1 = Filters.changeConntrast(image1, contrastValue);
						
						startTime = System.currentTimeMillis();
					BufferedImage image = Filters.changeBrightness(image1, brightnessSlider.getValue());
					brightnessValue = brightnessSlider.getValue();
					
						endTime = System.currentTimeMillis();
						duration = endTime-startTime;
						filter = "Brightness";
		                    Image scaledImage = image.getScaledInstance(261, 184, Image.SCALE_SMOOTH);
		                    ImageIcon scaledIcon = new ImageIcon(scaledImage);
		                    postIcon = scaledIcon;
		                    lblNewLabel.setIcon(scaledIcon);
					} catch (IOException  | NullPointerException e1) {
					
						  
	                    Date date = new Date();

	        	        SimpleDateFormat sdf = new SimpleDateFormat("E MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);

	        	        String formattedDate = sdf.format(date);

	        	        String filePath = "errors_and_infos/application_error.txt";
	        	        String text = String.format("[%s] [ERROR] %s",
	        	        		formattedDate,e1);

	        	        try (FileWriter fileWriter = new FileWriter(filePath, true)) {
	        	        	  fileWriter.write(text);
	        	       	 fileWriter.write(System.lineSeparator());
	        	          
	        	           
	        	        } catch (IOException eror) {
	        	            eror.printStackTrace();
	        	        }
					}
				}
			});
			
			contrastSlider.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					
					try {
						
						
						BufferedImage image1 = ImageSecretary.readResourceImage(selectedFile.getAbsolutePath()).getBufferedImage();
						image1 = Filters.makeBlur(image1, blurValue);
						image1 = Filters.makeSharp(image1, sharpenValue);
						image1 = Filters.makeEdgeDetection(image1, edgedetectionValue);
						image1 = Filters.makeGrayscale(image1, grayscaleValue);
						image1 = Filters.changeBrightness(image1, brightnessValue);
						
						
						startTime = System.currentTimeMillis();
						BufferedImage image = Filters.changeConntrast(image1, contrastSlider.getValue());
						contrastValue = contrastSlider.getValue();
				
						endTime = System.currentTimeMillis();
						duration = endTime-startTime;
						filter = "Contrast";
		                    Image scaledImage = image.getScaledInstance(261, 184, Image.SCALE_SMOOTH);
		                    ImageIcon scaledIcon = new ImageIcon(scaledImage);
		                    postIcon = scaledIcon;
		                    lblNewLabel.setIcon(scaledIcon);
					} catch (IOException  | NullPointerException e1) {
						
	                    Date date = new Date();

	        	        SimpleDateFormat sdf = new SimpleDateFormat("E MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);

	        	        String formattedDate = sdf.format(date);

	        	        String filePath = "errors_and_infos/application_error.txt";
	        	        String text = String.format("[%s] [ERROR] %s",
	        	        		formattedDate,e1);

	        	        try (FileWriter fileWriter = new FileWriter(filePath, true)) {
	        	        	  fileWriter.write(text);
	        	       	 fileWriter.write(System.lineSeparator());
	        	          
	        	           
	        	        } catch (IOException eror) {
	        	            eror.printStackTrace();
	        	        }
					}
				}
			});
			
			JLabel lblNewLabel_5 = new JLabel("GrayScale: ");
			lblNewLabel_5.setBounds(22, 445, 77, 14);
			contentPanel.add(lblNewLabel_5);
			
			JLabel lblNewLabel_6 = new JLabel("Edge detection: ");
			lblNewLabel_6.setBounds(22, 484, 77, 14);
			contentPanel.add(lblNewLabel_6);
			
			
			JSlider grayScaleSlider = new JSlider(0,100,0);
			grayScaleSlider.setMajorTickSpacing(25);
			grayScaleSlider.setPaintTicks(true);
			grayScaleSlider.setMinorTickSpacing(10);
			grayScaleSlider.setBounds(135, 440, 200, 19);
			contentPanel.add(grayScaleSlider);
			
			JSlider edgeDedectionSlider = new JSlider(0,100,0);
			edgeDedectionSlider.setMajorTickSpacing(25);
			edgeDedectionSlider.setPaintTicks(true);
			edgeDedectionSlider.setMinorTickSpacing(10);
			edgeDedectionSlider.setBounds(135, 470, 200, 31);
			contentPanel.add(edgeDedectionSlider);
			
			grayScaleSlider.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					
					try {
						
						BufferedImage image1 = ImageSecretary.readResourceImage(selectedFile.getAbsolutePath()).getBufferedImage();
						image1 = Filters.makeBlur(image1, blurValue);
						image1 = Filters.makeSharp(image1, sharpenValue);
						image1 = Filters.makeEdgeDetection(image1, edgedetectionValue);
						
						image1 = Filters.changeBrightness(image1, brightnessValue);
						image1 = Filters.changeConntrast(image1, contrastValue);
						startTime = System.currentTimeMillis();
						BufferedImage image = Filters.makeGrayscale(image1, grayScaleSlider.getValue());
						grayscaleValue = grayScaleSlider.getValue();
						endTime = System.currentTimeMillis();
						duration = endTime-startTime;
						filter = "GrayScale";
		                    Image scaledImage = image.getScaledInstance(261, 184, Image.SCALE_SMOOTH);
		                    ImageIcon scaledIcon = new ImageIcon(scaledImage);
		                    postIcon = scaledIcon;
		                    lblNewLabel.setIcon(scaledIcon);
					} catch (IOException  | NullPointerException e1) {
					  
	                    Date date = new Date();

	        	        SimpleDateFormat sdf = new SimpleDateFormat("E MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);

	        	        String formattedDate = sdf.format(date);

	        	        String filePath = "errors_and_infos/application_error.txt";
	        	        String text = String.format("[%s] [ERROR] %s",
	        	        		formattedDate,e1);

	        	        try (FileWriter fileWriter = new FileWriter(filePath, true)) {
	        	        	  fileWriter.write(text);
	        	       	 fileWriter.write(System.lineSeparator());
	        	          
	        	           
	        	        } catch (IOException eror) {
	        	            eror.printStackTrace();
	        	        }
					}
				}
			});
			
			edgeDedectionSlider.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					
					try {
						
						BufferedImage image1 = ImageSecretary.readResourceImage(selectedFile.getAbsolutePath()).getBufferedImage();
						image1 = Filters.makeBlur(image1, blurValue);
						image1 = Filters.makeSharp(image1, sharpenValue);
						
						image1 = Filters.makeGrayscale(image1, grayscaleValue);
						image1 = Filters.changeBrightness(image1, brightnessValue);
						image1 = Filters.changeConntrast(image1, contrastValue);
						startTime = System.currentTimeMillis();
						BufferedImage image = Filters.makeEdgeDetection(image1, edgeDedectionSlider.getValue());
						edgedetectionValue = edgeDedectionSlider.getValue();
						endTime = System.currentTimeMillis();
						duration = endTime-startTime;
						filter = "Edge Detection";
		                    Image scaledImage = image.getScaledInstance(261, 184, Image.SCALE_SMOOTH);
		                    ImageIcon scaledIcon = new ImageIcon(scaledImage);
		                    postIcon = scaledIcon;
		                    lblNewLabel.setIcon(scaledIcon);
					} catch (IOException  | NullPointerException e1) {
						// TODO Auto-generated catch block
						  
	                    Date date = new Date();

	        	        SimpleDateFormat sdf = new SimpleDateFormat("E MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);

	        	        String formattedDate = sdf.format(date);

	        	        String filePath = "errors_and_infos/application_error.txt";
	        	        String text = String.format("[%s] [ERROR] %s",
	        	        		formattedDate,e1);

	        	        try (FileWriter fileWriter = new FileWriter(filePath, true)) {
	        	        	  fileWriter.write(text);
	        	       	 fileWriter.write(System.lineSeparator());
	        	          
	        	           
	        	        } catch (IOException eror) {
	        	            eror.printStackTrace();
	        	        }
					}
				}
			});
		
			if(user.getTier() == Tier.FREE) {
				
				brightnessSlider.setEnabled(false);
				contrastSlider.setEnabled(false);
				edgeDedectionSlider.setEnabled(false);
				grayScaleSlider.setEnabled(false);
				
				
			}
			else if(user.getTier() == Tier.HOBBYIST) {
				
				edgeDedectionSlider.setEnabled(false);
				grayScaleSlider.setEnabled(false);
			}
			
	btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(postIcon == null) {
					
					if(discover == null) {
						
						CreatePost page = new CreatePost(user,"Please choose an image.",profile,null);
						dialog.dispose();
						
						Date date = new Date();

				        SimpleDateFormat sdf = new SimpleDateFormat("E MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);

				        String formattedDate = sdf.format(date);

				        String filePath1 = "errors_and_infos/application_error.txt";
				        String text1 = String.format("[%s] [ERROR] Please choose an image.",
				        		formattedDate);

				        try (FileWriter fileWriter = new FileWriter(filePath1, true)) {  
				        	  fileWriter.write(text1);
				       	 fileWriter.write(System.lineSeparator());
				          
				           
				        } catch (IOException eror) {
				            eror.printStackTrace();
				        }
					
					}
					
					else {
						
						CreatePost page = new CreatePost(user,"Please choose an image.",null,discover); 
						dialog.dispose();
						
						Date date = new Date();

				        SimpleDateFormat sdf = new SimpleDateFormat("E MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);

				        String formattedDate = sdf.format(date);

				        String filePath1 = "errors_and_infos/application_error.txt";
				        String text1 = String.format("[%s] [ERROR] Please choose an image.",
				        		formattedDate);

				        try (FileWriter fileWriter = new FileWriter(filePath1, true)) {  
				        	  fileWriter.write(text1);
				       	 fileWriter.write(System.lineSeparator());
				          
				           
				        } catch (IOException eror) {
				            eror.printStackTrace();
				        }
					}
					
					
				}
                   
				else {
					String description = textArea.getText();
					
					if(description.equals("")) {
						
						 String filePath = "database/posts.txt";
				         String text = String.format("%s,%s",
				        		user.getNickName(),postIconPath); 

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
				       
				         
				         Date date = new Date();

					        SimpleDateFormat sdf = new SimpleDateFormat("E MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);

					        String formattedDate = sdf.format(date);

					        String filePath1 = "errors_and_infos/application_info.txt";
					        String text1 = String.format("[%s] [INFO] %s filter applied to %s file. Took %s ms.",
					        		formattedDate,filter,postIconPath,duration);

					        try (FileWriter fileWriter = new FileWriter(filePath1, true)) {
					        	  fileWriter.write(text1);
					       	 fileWriter.write(System.lineSeparator());
					          
					           
					        } catch (IOException eror) {
					            eror.printStackTrace();
					        }
					        
					        String originalString = postIconPath;
			                 StringBuilder reversedStringBuilder = new StringBuilder(originalString);
			                 String reversedString = reversedStringBuilder.reverse().toString();
			                 
			                 int index = reversedString.indexOf('/');
			                 
			                 String name = reversedString.substring(0, index);
			                 StringBuilder r = new StringBuilder(name);
			                 name = r.reverse().toString();
					        
					        Post post = new Post(user,postIcon,name,filter);
				         
						if(discover == null) {
							profile.disposeFrame();
							UserProfilePage page = new UserProfilePage(user);
							
							
						}
						
						else {
							discover.disposeFrame();
							DiscoverPage page = new DiscoverPage(user);
							
							
						}
						
						dialog.dispose();
						
					}
					else {
						
					
						 String filePath = "database/posts.txt";
				         String text = String.format("%s,%s,%s",
				        		user.getNickName(),postIconPath,description); 

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
				         
				         Date date = new Date();

					        SimpleDateFormat sdf = new SimpleDateFormat("E MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);

					        String formattedDate = sdf.format(date);

					        String filePath1 = "errors_and_infos/application_info.txt";
					        String text1 = String.format("[%s] [INFO] %s filter applied to %s file. Took %s ms.",
					        		formattedDate,filter,postIconPath,duration);

					        try (FileWriter fileWriter = new FileWriter(filePath1, true)) {  
					        	  fileWriter.write(text1);
					       	 fileWriter.write(System.lineSeparator());
					          
					           
					        } catch (IOException eror) {
					            eror.printStackTrace();
					        }
					        String originalString = postIconPath;
			                 StringBuilder reversedStringBuilder = new StringBuilder(originalString);
			                 String reversedString = reversedStringBuilder.reverse().toString();
			                 
			                 int index = reversedString.indexOf('/');
			                 
			                 String name = reversedString.substring(0, index);
			                 StringBuilder r = new StringBuilder(name);
			                 name = r.reverse().toString();
					        
					    	Post post = new Post(user,postIcon,description,name,filter);
						if(discover == null) {
							profile.disposeFrame();
							UserProfilePage page = new UserProfilePage(user);
							
							
						}
						
						else {
							discover.disposeFrame();
							DiscoverPage page = new DiscoverPage(user);
							
							
						}
						
						dialog.dispose();
						
						
					}
				}
                }
			
		});
		
		
	}


	}
	
