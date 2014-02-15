package dherls.login;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Member {
	
	private String name;
	private int id;
	private boolean isLoggedIn = false;
	//This variable tells the member what position he is in in his team's member list
	private int position;
	private File pictureFile;
	private BufferedImage image;
	
	public Member(String name, int id, int position){
		this.name = name;
		this.id = id;
		this.position = position;
		loadPicture();
	}
	
	public Member(String name, int id, int position, boolean isLoggedIn){
		this.name = name;
		this.id = id;
		this.position = position;
		this.isLoggedIn = isLoggedIn;
		loadPicture();
		
	}
	
	public void loadPicture() {
		pictureFile = new File(FileHandler.getPictureDir().getAbsolutePath() + File.separator + String.valueOf(id) + ".png");
		try{
			image = ImageIO.read(pictureFile);
		} catch (IOException e){
			try {
				image = ImageIO.read(getClass().getResource("/dherls/resources/unknown.png"));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
	}

	public String getName(){
		return name;
	}
	
	public int getId(){
		return id;
	}
	
	public boolean getIsLoggedIn(){
		return isLoggedIn;
	}
	
	public void logIn(){
		isLoggedIn = true;
	}
	
	public void logOut(){
		isLoggedIn = false;
	}
	
	public void print(){
		System.out.println(name + "\t" + id + "\t" + isLoggedIn + "\t" + position);
	}
	
	public void memberRemoved(){
		position--;
	}
	
	public int getPosition(){
		return position;
	}
	
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
	
	public BufferedImage getImage(){
		return image;
	}

	public File getPictureFile() {
		return pictureFile;
	}
	
}
