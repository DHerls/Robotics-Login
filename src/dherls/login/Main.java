package dherls.login;

public class Main {
	public static Team team = new Team();

	
	
	public static void main(String[] args){
		LogBook.init();
		team.importMembers();
		team.printAll();
		team.test();
	}
}
