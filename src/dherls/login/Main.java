package dherls.login;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import dherls.visuals.MainFrame;
import dherls.visuals.SelectDirectoryFrame;
/**
 * 
 *This program was designed to help keep track of the logs of the Full Metal Falcons, FRC team 4557
 *<p>
 * You may use and redistribute this software provided that you do not charge anything for it and do not pass it off as your own work.
 * 
 * @author Dan Herlihy
 * @version 1.0.0
 * 
 */
public class Main {
	private static Team team = new Team();
	private static MainFrame frame;
	
	public static void main(String[] args){
		frame = new MainFrame();
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
	/**
	 * 
	 * @return The team instance which handles all of the members
	 */
	public static Team getTeam(){
		return team;
	}
	
	/**
	 * 
	 * @return The main frame from which all the visible components are displayed.
	 */
	public static MainFrame getFrame(){
		return frame;
	}
	
	/**
	 * Runs the bulk of the program
	 */
	public static void execute(){
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
		}
		LogBook.init();
		MemberBook.init();
		team.importMembers();
		team.importGroups();
		frame.visualize();
	}
}