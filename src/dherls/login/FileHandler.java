package dherls.login;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import dherls.visuals.MessageFrame;

public class FileHandler {
	
	private static File temp = new File(System.getProperty("java.io.tmpdir") + "robolog.txt");
	private static File dir;
	
	public static boolean doesTempFileExist() {
		
		System.out.println(System.getProperty("java.io.tmpdir") + "robolog.txt");
		return temp.exists();
	}

	public static void createTempFile() {
		try {
			temp.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public static void getUserDirPref() {
		JFileChooser fc = new JFileChooser();
		File defaultDir = new File(System.getProperty("user.home") + File.separator + "Login" + File.separator);
		defaultDir.mkdirs();

		System.out.println(defaultDir.getAbsolutePath());
		//AbstractButton button = SwingUtils.getDescendantOfType(AbstractButton.class, fc, "Icon", UIManager.getIcon("FileChooser.detailsViewIcon"));
		//button.doClick();
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fc.setCurrentDirectory(defaultDir);
		new MessageFrame("Please choose a location for user files");
		
		
		int returnVal = fc.showOpenDialog(null);
		

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			dir = fc.getSelectedFile();
			if (dir.getAbsolutePath() != defaultDir.getAbsolutePath()){
				defaultDir.delete();
			}
		} else {
			defaultDir.delete();
			temp.delete();
			System.exit(0);
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
		
		
	}
	
}
