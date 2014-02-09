package dherls.login;

public class Main {
	public static Team team = new Team();

	
	
	public static void main(String[] args){
		LogBook.init();
		MemberBook.init();
		MemberBook.importMembers();
		team.importMembers();
		team.test();
	}
}
