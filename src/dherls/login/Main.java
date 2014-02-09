package dherls.login;

import dherls.visuals.MainFrame;

public class Main {
	private static Team team = new Team();
	private static MainFrame frame;
	
	public static void main(String[] args){
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
