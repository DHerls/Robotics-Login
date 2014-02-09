package dherls.login;

import java.util.ArrayList;

import dherls.visuals.MessageFrame;

public class Team {
	
	private ArrayList<Member> members = new ArrayList<>();	
	
	public void importMembers(){
		members = MemberBook.importMembers();
		System.out.println("Successfully imported " + members.size() + " members.");
	}

	public void printAll() {
		for (Member m:members){
			m.print();
		}
		
	}
	
	public boolean addMember(String name, int id, boolean state){
		if (getMember(id)!=null){
			new MessageFrame("The id " + id + " already exists");
			return false;
			
		} else {
			members.add(new Member(name,id, members.size(), state));
			LogBook.addLog(LogBook.JOIN, members.get(members.size()-1));
			LogBook.addLog(LogBook.LOG_IN, members.get(members.size()-1));
			MemberBook.addMember(name, id, state);
			return true;
		}
		
	}
	
	//removes the member from list 'members' and decreases the position of all members above the removed member
	public void removeMember(Member m){
		members.remove(m);
		if (m.getIsLoggedIn()){
			logOut(m);
		}
		LogBook.addLog(LogBook.LEAVE, m);
		MemberBook.removeMember(m);
		for (Member mem: members){
			if (mem.getPosition()>m.getPosition()){
				mem.memberRemoved();
			}
		}
	}

	public void logIn(Member m) {
		if (!m.getIsLoggedIn()){
			m.logIn();
			LogBook.addLog(LogBook.LOG_IN,m);
			MemberBook.stateChange(m);
		}
	}
	
	public void logOut(Member m) {
		if (m.getIsLoggedIn()){
			m.logOut();
			LogBook.addLog(LogBook.LOG_OUT,m);
			MemberBook.stateChange(m);
		}
	}
	
	public void logOutAll() {
		for(Member m: members){
			logOut(m);
		}
	}
	
	public Member getMember(int id){
		for (Member m: members){
			if (m.getId()==id){
				return m;
			}
		}
		return null;
	}

	public void test(){
		
	}
}
