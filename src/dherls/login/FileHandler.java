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

public class FileHandler {
	
	private static File temp = new File(System.getProperty("java.io.tmpdir") + "robolog.txt");
	private static File dir;
	private static File defaultDir = new File(System.getProperty("user.home") + File.separator + "Login" + File.separator);
	private static File pictureDir;

	
	public static boolean doesTempFileExist() {		
		return temp.exists();
	}

	public static void createTempFile() {
		try {
			temp.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

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
	
	public static File getDir(){
		return dir;
	}
	
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

	public static void copyPic(File sourceFile,int id) throws IOException {
		if (sourceFile!=null){
			RenderedImage r = ImageIO.read(sourceFile);
			File dirOut = new File(pictureDir.getAbsolutePath() + File.separator + String.valueOf(id) + ".png");
			ImageIO.write(r, "png", dirOut);
		}
	}
	
	public static void removePicture(Member m){
		new File(pictureDir.getAbsolutePath() + File.separator + String.valueOf(m.getId()) + ".png").delete();
	}

	public static File getPictureDir() {
		return pictureDir;
	}
	
}
