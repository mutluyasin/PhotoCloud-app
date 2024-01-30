package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

import javax.swing.ImageIcon;

/************** Pledge of Honor ******************************************
I hereby certify that I have completed this programming project on my own
without any help from anyone else. The effort in the project thus belongs
completely to me. I did not search for a solution, or I did not consult any
program written by others or did not copy any program from other sources. I
read and followed the guidelines provided in the project description.
READ AND SIGN BY WRITING YOUR NAME SURNAME AND STUDENT ID
SIGNATURE: Yasin MUTLU,0079499
*************************************************************************/
public class Main {
	

	public static void main(String[] args) {
		
		
		 WelcomePage page = new WelcomePage(); 
		 
		  Date date = new Date();

	        SimpleDateFormat sdf = new SimpleDateFormat("E MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);

	        String formattedDate = sdf.format(date);

	        String filePath1 = "errors_and_infos/application_info.txt";
	        String text = String.format("[%s] [INFO] application started.",
	        		formattedDate);

	        try (FileWriter fileWriter = new FileWriter(filePath1, true)) {
	        	  fileWriter.write(text);
	       	 fileWriter.write(System.lineSeparator());
	          
	           
	        } catch (IOException eror) {
	            eror.printStackTrace();
	        }
		
		 AdminUser admin = new AdminUser();
		
		 String filePath = "database/users.txt";

	        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
	            String line;
	            while ((line = br.readLine()) != null) {
	               
	               
	            	String[] info = line.split(",");
	            	
	            	Tier tier = null;
	            	if(info[7].equals("Free")) {
	            		tier = Tier.FREE;
	            	}
	            	else if(info[7].equals("Hobbyist")) {
	            		tier = Tier.HOBBYIST;
	            	}
	            	else {
	            		tier = Tier.PROFESSIONAL;
	            	}
	            	
	            	new User(info[0],info[1],info[2],info[3],Integer.parseInt(info[4]),info[5],new ImageIcon(info[6]),tier); 
	            
	            
	            }
	        } catch (IOException e) {
	        	Date date1 = new Date();

		        SimpleDateFormat sdf1 = new SimpleDateFormat("E MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);

		        String formattedDate1 = sdf1.format(date1);

		        String filePath2 = "errors_and_infos/application_error.txt";
		        String text1 = String.format("[%s] [ERROR] %s",
		        		formattedDate1,e);

		        try (FileWriter fileWriter = new FileWriter(filePath2, true)) {  
		        	  fileWriter.write(text1);
		       	 fileWriter.write(System.lineSeparator());
		          
		           
		        } catch (IOException eror) {
		            eror.printStackTrace();
		        }
	        }
	
	        
	        String posts = "database/posts.txt";

	        try (BufferedReader br = new BufferedReader(new FileReader(posts))) {
	            String line;
	            int count = 1;
	            while ((line = br.readLine()) != null) {
	               
	            	String[] info = line.split(",");
	            	User user = null;
	            	for(User u:DiscoverPage.getAllUsers()) { 
	            		if(u.getNickName().equals(info[0])) {
	            			user = u;
	            			break;
	            		}
	            	}
	            
	            	
	            	 String originalString = info[1];
	                 StringBuilder reversedStringBuilder = new StringBuilder(originalString);
	                 String reversedString = reversedStringBuilder.reverse().toString();
	                 
	                 int index = reversedString.indexOf('/');
	                 
	                 String name = reversedString.substring(0, index);
	                	
	                 StringBuilder r = new StringBuilder(name);
	                 name = r.reverse().toString();
	          
	                 
	                 if(count < 23) {
	                	 if(info.length == 2) {
		                	Post post = new Post(user,new ImageIcon(info[1]),name,null);
		                	post.setIspublic(true);
	                	 }
		                 else {
		                	Post post = new Post(user,new ImageIcon(info[1]),info[2],name,null);
		                	post.setIspublic(true);
		                 }
	                 }
	                 else {
	                	 if(info.length == 2) {
		                	 new Post(user,new ImageIcon(info[1]),name,null);	                 }
		                 else {
		                	 new Post(user,new ImageIcon(info[1]),info[2],name,null);

		                 }
	                 }
	                 
	            		            
	            
	            }
	        } catch (IOException e) {
	        	Date date1 = new Date();

		        SimpleDateFormat sdf1 = new SimpleDateFormat("E MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);

		        String formattedDate1 = sdf1.format(date1);

		        String filePath2 = "errors_and_infos/application_error.txt";
		        String text1 = String.format("[%s] [ERROR] %s",
		        		formattedDate1,e);

		        try (FileWriter fileWriter = new FileWriter(filePath2, true)) {  
		        	  fileWriter.write(text1);
		       	 fileWriter.write(System.lineSeparator());
		          
		           
		        } catch (IOException eror) {
		            eror.printStackTrace();
		        }
	        }
	
		
	       
	   
		
	}

}
