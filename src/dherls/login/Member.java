package dherls.login;


public class Member {
	
	private String name;
	private int id;
	private boolean isLoggedIn = false;
	//This variable tells the member what position he is in in his team's member list
	private int position;
	
	public Member(String name, int id, int position){
		this.name = name;
		this.id = id;
		this.position = position;
	}
	
	public Member(String name, int id, int position, boolean isLoggedIn){
		this.name = name;
		this.id = id;
		this.position = position;
		this.isLoggedIn = isLoggedIn;
	}
	
	public String getName(){
		return name;
	}
	
	public int getId(){
		return id;
	}
	
	public boolean getIsLoggedIn(){
		return isLoggedIn;
	}
	
	public void logIn(){
		isLoggedIn = true;
	}
	
	public void logOut(){
		isLoggedIn = false;
	}
	
	public void print(){
		System.out.println(name + "\t" + id + "\t" + isLoggedIn);
	}
	
	public void memberRemoved(){
		position--;
	}
	
	public int getPosition(){
		return position;
	}
	
	
}
