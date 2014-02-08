package dherls.login;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Member {
	
	private String name;
	private String id;
	private boolean isLoggedIn = false;
	private Team team;
	private DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
	private Date date = new Date();
	//This variable tells the member what position he is in in his team's member list
	private int position;
	
	public Member(String name, String id, Team team, int position){
		this.team = team;
		this.name = name;
		this.id = id;
		this.position = position;
	}
	
	public String getName(){
		return name;
	}
	
	public String getId(){
		return id;
	}
	
	public boolean getIsLoggedIn(){
		return isLoggedIn;
	}
	
	//When these two methods are called, the member logs itself into and out of the team respectively
	public void logIn(){
		isLoggedIn = true;
		team.logIn(position,dateFormat.format(date));
	}
	
	public void logOut(){
		isLoggedIn = false;
	}
	
	public void print(){
		System.out.println(name + "\t" + id + "\t" + isLoggedIn);
	}
	
	
}
