package dherls.login;

import javax.swing.UIManager;

import dherls.visuals.MainFrame;
import dherls.visuals.MessageFrame;

public class Main {
	private static Team team = new Team();
	private static MainFrame frame;
	
	public static void main(String[] args){

		if (!FileHandler.doesTempFileExist()){
			try{
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
				FileHandler.createTempFile();
				FileHandler.getUserDirPref();
				UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			} catch (Exception e){
				
			}
		} else {
			//FileHandler.delete();
			
			FileHandler.readFile();
		}
		LogBook.init();
		MemberBook.init();
		team.importMembers();
		frame = new MainFrame();
	}

	public static Team getTeam(){
		return team;
	}
	
	public static MainFrame getFrame(){
		return frame;
	}
}