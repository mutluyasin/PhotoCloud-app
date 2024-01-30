package application;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import javax.swing.ImageIcon;

public class Post {

	private String description;
	private User postOwner;
	private ImageIcon postPhoto;
	private int likeCount;
	private int dislikeCount;
	private int commentCount;
	private ArrayList<ArrayList<String>> comments;
	private ArrayList<User> likedUsers;
	private ArrayList<User> dislikedUsers;
	private boolean ispublic;
	private String photoName;
	private String filter;
	
	
	public Post(User postOwner,ImageIcon postPhoto,String photoName,String filter) {
		this.postOwner = postOwner;
		this.postPhoto = postPhoto;
		this.photoName = photoName;
		this.filter = filter;
		this.description = "";
		this.likeCount = 0;
		this.dislikeCount = 0;
		this.commentCount = 0;
		this.comments = new ArrayList<>();
		this.likedUsers = new ArrayList<>();
		this.dislikedUsers = new ArrayList<>();
	
		this.ispublic = false;
		postOwner.addPost(this);
	
		  Date date = new Date();

	        SimpleDateFormat sdf = new SimpleDateFormat("E MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);

	        String formattedDate = sdf.format(date);

	        String filePath = "errors_and_infos/application_info.txt";
	        String text = String.format("[%s] [INFO] A post was created by %s.",
	        		formattedDate,postOwner.getNickName());

	        try (FileWriter fileWriter = new FileWriter(filePath, true)) {
	        	  fileWriter.write(text);
	       	 fileWriter.write(System.lineSeparator());
	          
	           
	        } catch (IOException eror) {
	            eror.printStackTrace();
	        }
		
	}
	
	public ArrayList<User> getLikedUsers() {
		return likedUsers;
	}

	public ArrayList<User> getDislikedUsers() {
		return dislikedUsers;
	}

	public Post(User postOwner,ImageIcon postPhoto,String description,String photoName,String filter) {
		this.postOwner = postOwner;
		this.description = description;
		this.postPhoto = postPhoto;
		this.photoName = photoName;
		this.filter = filter;
		this.likeCount = 0;
		this.dislikeCount = 0;
		this.commentCount = 0;
		this.comments = new ArrayList<>();
		this.likedUsers = new ArrayList<>();
		this.dislikedUsers = new ArrayList<>();
		this.ispublic = false;
		postOwner.addPost(this);
	
		  Date date = new Date();

	        SimpleDateFormat sdf = new SimpleDateFormat("E MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);

	        String formattedDate = sdf.format(date);

	        String filePath = "errors_and_infos/application_info.txt";
	        String text = String.format("[%s] [INFO] A post was created by %s.",
	        		formattedDate,postOwner.getNickName());

	        try (FileWriter fileWriter = new FileWriter(filePath, true)) {
	        	  fileWriter.write(text);
	       	 fileWriter.write(System.lineSeparator());
	          
	           
	        } catch (IOException eror) {
	            eror.printStackTrace();
	        }
	}

	public String getFilter() {
		return filter;
	}

	public String getPhotoName() {
		return photoName;
	}

	public boolean isIspublic() {
		return ispublic;
	}

	public void setIspublic(boolean ispublic) {
		this.ispublic = ispublic;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getPostOwner() {
		return postOwner;
	}


	public int getLikeCount() {
		return likeCount;
	}


	public int getDislikeCount() {
		return dislikeCount;
	}


	public int getCommentCount() {
		return commentCount;
	}


	public ArrayList<ArrayList<String>> getComments() {
		return comments;
	}
	/**
	 * 
	 * @param u
	 */
	
	public void increaseLike(User u) {
		if(dislikedUsers != null) {
			if(dislikedUsers.contains(u)) {
				dislikedUsers.remove(u);
				dislikeCount--;
				likedUsers.add(u);
			}
			else {
				likedUsers.add(u);
			}
		}
		else {
			likedUsers.add(u);
		}

		
		
		likeCount++;
		
	}
	/**
	 * 
	 * @param u
	 */
	public void increaseDislike(User u) { 
		if(likedUsers != null) {
			if(likedUsers.contains(u)) {
				likedUsers.remove(u);
				likeCount--;
				dislikedUsers.add(u);
			}
			else {
				dislikedUsers.add(u);
			}
		}
		else {
			dislikedUsers.add(u);
		}
		dislikeCount++;
	}
	/**
	 * 
	 * @param u
	 * @param comment
	 */
	public void addComment(User u,String comment) {
		
		
		ArrayList<String> temp = new ArrayList<>();
		temp.add(u.getNickName());
		temp.add(comment);
		comments.add(temp);
		
		
	}
	
	public ImageIcon getPostPhoto() {return postPhoto;}
	
	public void setPostPhoto(ImageIcon photo) {
		this.postPhoto = photo;
	}


}
