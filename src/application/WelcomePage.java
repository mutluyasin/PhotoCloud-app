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
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;

public class WelcomePage{




	public WelcomePage() {
		
		JFrame frame = new JFrame();
		frame.setVisible(true);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 450, 300);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton loginButton = new JButton("Login");
		loginButton.setBounds(162, 99, 89, 23);
		contentPane.add(loginButton);
		
		JButton signinButton = new JButton("Sign up");
		signinButton.setBounds(162, 158, 89, 23);
		contentPane.add(signinButton);
		
		JButton exitButton = new JButton("Exit");
		exitButton.setBounds(162, 229, 89, 23);
		contentPane.add(exitButton);
		
		JLabel lblNewLabel = new JLabel("PhotoCloud");
		lblNewLabel.setForeground(new Color(0, 128, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setBounds(145, 27, 176, 33);
		contentPane.add(lblNewLabel);
		
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				LoginPage page = new LoginPage(null); 
				frame.dispose();
				
			}
		});
		
		signinButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				SigninPage page = new SigninPage(null);
				frame.dispose();;
				
			}
		});
		
		exitButton.addActionListener(new ActionListener() {
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
	}
}
