package dherls.login;

public class Main {
	
	public static void main(String[] args){
		Team team = new Team();
		team.importMembers();
		team.printAll();
		team.test();
	}
}
