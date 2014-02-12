package dherls.login;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

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
		File defaultDir = new File(System.getProperty("user.home"));
		System.out.println(defaultDir.getAbsolutePath());
		//AbstractButton button = SwingUtils.getDescendantOfType(AbstractButton.class, fc, "Icon", UIManager.getIcon("FileChooser.detailsViewIcon"));
		//button.doClick();
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fc.setCurrentDirectory(defaultDir);
		int returnVal = fc.showDialog(new JFrame(), "Choose Folder");
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			dir = fc.getSelectedFile();
		} else {
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
	
}
