package dherls.login;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import dherls.visuals.MainFrame;
import dherls.visuals.MemberPicturePanel;
import dherls.visuals.SelectDirectoryFrame;

public class Main {
	private static Team team = new Team();
	private static MainFrame frame;
	
	public static void main(String[] args){
		if (!FileHandler.doesTempFileExist()){
			try{
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
				new SelectDirectoryFrame();
			} catch (Exception e){
				
			}
		} else {			
			FileHandler.readFile();
			execute();
		}

		
	}

	public static Team getTeam(){
		return team;
	}
	
	public static MainFrame getFrame(){
		return frame;
	}
	
	public static void execute(){
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
		}
		LogBook.init();
		MemberBook.init();
		team.importMembers();
		frame = new MainFrame();
	}
}