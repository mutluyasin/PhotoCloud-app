package application;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.swing.ImageIcon;

public class User {
	
	private String nickName;
	private String password;
	private String firstName;
	private String lastName;
	private int age; 
	private String email; 
	private ImageIcon profilePhoto;
	private ArrayList<Post> posts;
	private Tier tier;
	
	
	public User(String nickName, String password, String firstName, String lastName, int age, String email,
			ImageIcon profilePhoto,Tier tier) { 
		this.nickName = nickName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.email = email;
		this.profilePhoto = profilePhoto;
		this.tier = tier;
		this.posts = new ArrayList<>();
		DiscoverPage.addUser(this);
		
		  Date date = new Date();

	        SimpleDateFormat sdf = new SimpleDateFormat("E MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);

	        String formattedDate = sdf.format(date);

	        String filePath = "errors_and_infos/application_info.txt";
	        String text = String.format("[%s] [INFO] %s signed up.",
	        		formattedDate,nickName);

	        try (FileWriter fileWriter = new FileWriter(filePath, true)) {
	        	  fileWriter.write(text);
	       	 fileWriter.write(System.lineSeparator());
	          
	           
	        } catch (IOException eror) {
	            eror.printStackTrace();
	        }
	}
	

	public Tier getTier() {
		return tier;
	}
	
	public void setTier(Tier tier) {
		this.tier = tier;
	}
	public ArrayList<Post> getPosts() {
		return posts;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ImageIcon getProfilePhoto() {
		return profilePhoto;
	}

	public void setProfilePhoto(ImageIcon profilePhoto) {
		this.profilePhoto = profilePhoto;
	}


	public void addPost(Post p) {
		posts.add(p);
	
	}

	public void removePost(Post p) {
		posts.remove(p);
	}
}
