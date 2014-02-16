package dherls.login;

import java.awt.image.RenderedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * This class is used to handle all of the information related to the working directory.
 * 
 * @author Dan Herlihy
 *
 */

public class FileHandler {
	
	private static File temp = new File(System.getProperty("java.io.tmpdir") + "robolog.txt");
	private static File dir;
	private static File defaultDir = new File(System.getProperty("user.home") + File.separator + "Login" + File.separator);
	private static File pictureDir;

	/**
	 * 
	 * 
	 * @return Whether or not the text file containing the working directory path exists
	 */
	public static boolean doesTempFileExist() {		
		return temp.exists();
	}
	/**
	 * Creates a 'temporary' file called "robolog.txt" which stores the user's working directory preference
	 */
	public static void createTempFile() {
		try {
			temp.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Creates a JFileChooser window which allows the user to select their preferred working directory
	 * 
	 * @return The folder which the user has selected or null if they have not selected a folder
	 */
	public static File getUserDirPref() {
		JFileChooser fc = new JFileChooser();
		defaultDir.mkdirs();

		System.out.println(defaultDir.getAbsolutePath());
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fc.setCurrentDirectory(defaultDir);		
		
		int returnVal = fc.showOpenDialog(null);
		

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			dir = fc.getSelectedFile();
		} else {
			return null;
		}
		
		return dir;
	}

	public static void delete() {
		temp.delete();
	}
	
	/**
	 * Reads the temp file containing user directory preferences and initializes the main directory file and the picture directory file.
	 */
	public static void readFile() {
		Scanner scan = null;
		try {
			scan = new Scanner(temp);
		} catch (FileNotFoundException e) {
		}
		dir = new File(scan.nextLine());
		pictureDir = new File(dir.getAbsolutePath()+File.separator+"Pictures");
		dir.mkdirs();
		pictureDir.mkdirs();
		System.out.println("Defualt directory found at " + dir.getAbsolutePath());
		
	}
	
	public static File getDefaultDir(){
		return defaultDir;
	}
	/**
	 * Writes the path of the preferred working directory to the temp file
	 * 
	 * @param f The user preferred working directory
	 */
	public static void setDir(File f){
		if (!f.equals(defaultDir)){
			defaultDir.delete();
		}
		dir = f;
		pictureDir = new File(dir.getAbsolutePath()+File.separator+"Pictures");
		pictureDir.mkdirs();
		if (!temp.exists()){
			createTempFile();
		}
		BufferedWriter writer = null;
		
		try {
			writer = new BufferedWriter(new FileWriter(temp));
			writer.write(dir.getAbsolutePath());
		} catch (IOException e) {
			
		} finally{
			try {
				writer.close();
			} catch (IOException e) {
				
			}
		}
	}
	/**
	 * 
	 * @return The work directory
	 */
	public static File getDir(){
		return dir;
	}
	
	/**
	 * Creates a JFileChooser window which allows the user to select members' pictures
	 * 
	 * @return The file which the user has selected or null if no selection has been made
	 */
	public static File getMemberPic() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			File pic;
			JFileChooser fc = new JFileChooser();

			fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Pictures", "jpeg","png","bmp","tiff");
			fc.setFileFilter(filter);
			fc.setApproveButtonText("Select");
			fc.setCurrentDirectory(dir);		
			
			int returnVal = fc.showOpenDialog(null);
			

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				pic = fc.getSelectedFile();
			} else {
				UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
				return null;
			}
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());

			return pic;
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			
			return null;
		}

	}
	/**
	 * Takes the source image file selected by the user and converts it to a .png file with the member's id as a file name. It also places the file in the pictures directory/
	 * 
	 * @param sourceFile The original picture file
	 * @param id The id of the member whose picture this is
	 * @throws IOException
	 */
	public static void copyPic(File sourceFile,int id) throws IOException {
		if (sourceFile!=null){
			RenderedImage r = ImageIO.read(sourceFile);
			File dirOut = new File(pictureDir.getAbsolutePath() + File.separator + String.valueOf(id) + ".png");
			ImageIO.write(r, "png", dirOut);
		}
	}
	
	/**
	 * Typically called when a member is deleted, it deletes the member's picture from the picture directory
	 * 
	 * @param m The member whose picture is to be deleted
	 */
	public static void removePicture(Member m){
		new File(pictureDir.getAbsolutePath() + File.separator + String.valueOf(m.getId()) + ".png").delete();
	}
	
	/**
	 * 
	 * @return The picture directory
	 */
	public static File getPictureDir() {
		return pictureDir;
	}
	
	
}
