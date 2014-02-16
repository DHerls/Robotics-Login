package dherls.login;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 
 * This class is the basis of the member system.  
 * <p>
 * A member stores:
 * <br> - Its name (String)
 * <br> - Its id (Integer)
 * <br> - Whether it is logged in (true) or logged out (false)
 * <br> - Its position in relation to the other members (Integer)
 * <br> - Its picture (BufferedImage)
 * 
 * @author Dan Herlihy
 *
 */
public class Member {
	
	private String name;
	private int id;
	private boolean isLoggedIn = false;
	//This variable tells the member what position he is in in his team's member list
	private int position;
	private File pictureFile;
	private BufferedImage image;
	
	/**
	 * Initializer for Member
	 * 
	 * @param name The member's name
	 * @param id The member's id
	 * @param position The members position relative to other members in the Team's member list
	 */
	public Member(String name, int id, int position){
		this.name = name;
		this.id = id;
		this.position = position;
		loadPicture();
	}
	
	/**
	 * Secondary initializer for when the member is to be logged in immediately upon creation
	 * 
	 * @param name The member's name
	 * @param id The member's id
	 * @param position The members position relative to other members in the Team's member list
	 * @param isLoggedIn Whether or not the member should be initially logged in
	 */
	public Member(String name, int id, int position, boolean isLoggedIn){
		this.name = name;
		this.id = id;
		this.position = position;
		this.isLoggedIn = isLoggedIn;
		loadPicture();
		
	}
	
	/**
	 * The member find's the picture in the picture directory corresponding to its id number and loads it. If the picture cannot be found it defaults to "unknown.png"
	 */
	public void loadPicture() {
		pictureFile = new File(FileHandler.getPictureDir().getAbsolutePath() + File.separator + String.valueOf(id) + ".png");
		
		try{
			image = ImageIO.read(pictureFile);
		} catch (IOException e){
			try {
				image = ImageIO.read(getClass().getResource("/dherls/resources/unknown.png"));
				pictureFile = null;
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
	}

	/**
	 * 
	 * @return The member's name
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * 
	 * @return The member's id
	 */
	public int getId(){
		return id;
	}
	
	/**
	 * 
	 * @return Whether or not the member is logged in
	 */
	public boolean getIsLoggedIn(){
		return isLoggedIn;
	}
	
	/**
	 * Logs the member in, does not add logs or update member book
	 */
	public void logIn(){
		isLoggedIn = true;
	}
	
	/**
	 * Logs the member out, does not add logs or update member book
	 */
	public void logOut(){
		isLoggedIn = false;
	}
	
	/**
	 * Mostly debugging code, displays the members name, id, current state, and position in the console
	 */
	public void print(){
		System.out.println(name + "\t" + id + "\t" + isLoggedIn + "\t" + position);
	}
	
	/**
	 * Called when another member is deleted, this method tells the member that their position has changed do to that removal
	 */
	public void memberRemoved(){
		position--;
	}
	
	/**
	 * 
	 * @return The member's position in relation to every other member
	 */
	public int getPosition(){
		return position;
	}
	
	/**
	 *  Sets the member's image
	 * @param f The picture file
	 */
	public void setImages(File f){
		if (f!=null){
			pictureFile = f;
			try {
				image = ImageIO.read(f);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 
	 * @return The member's image in BufferedImage format
	 */
	public BufferedImage getImage(){
		return image;
	}

	/**
	 * 
	 * @return The member's image file
	 */
	public File getPictureFile() {
		return pictureFile;
	}
	
}
