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
			if (state){
				LogBook.addLog(LogBook.LOG_IN, members.get(members.size()-1));
			}

			MemberBook.addMember(getLatestMember());
			Main.getFrame().updateMembers();
			return true;
		}
		

	}
	
	//removes the member from list 'members' and decreases the position of all members above the removed member
	public void removeMember(Member m){
		members.remove(m);
		FileHandler.removePicture(m);
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
		
		Main.getFrame().updateMembers();
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
		Main.getFrame().updateMembers();
	}
	
	public Member getMember(int id){
		for (Member m: members){
			if (m.getId()==id){
				return m;
			}
		}
		return null;
	}
	
	public Member getMemberAt(int position){
		return members.get(position);
	}
	
	public void toggleState(Member m){
		if (m.getIsLoggedIn()){
			logOut(m);
		} else {
			logIn(m);
		}
	}

	public void test(){
		
	}

	public ArrayList<Member> getLoggedIn() {
		ArrayList<Member> loggedIn = new ArrayList<>();
		for (Member m: members){
			if (m.getIsLoggedIn()){
				loggedIn.add(m);
			}
		}
		return loggedIn;
	}
	
	public ArrayList<Member> getMembers(){
		return members;
	}

	public boolean replaceMember(Member oldMember, String name, int id) {
		boolean state = oldMember.getIsLoggedIn();
		Member m = getMember(id);
		if (m!=null){
			if (m.equals(oldMember)){
				members.set(oldMember.getPosition(),new Member(name,id, oldMember.getPosition(), state));
				//LogBook.addLog(LogBook.JOIN, members.get(members.size()-1));
				//if (state){
					//LogBook.addLog(LogBook.LOG_IN, members.get(members.size()-1));
				//}

				//MemberBook.addMember(name, id, state);
				MemberBook.replaceMember(oldMember,name, id, state);
				Main.getFrame().updateMembers();
				return true;
			}
			new MessageFrame("The id " + id + " already exists");
			return false;
			
		} else {
			
			members.set(oldMember.getPosition(),new Member(name,id, oldMember.getPosition(), state));
			//LogBook.addLog(LogBook.JOIN, members.get(members.size()-1));
			//if (state){
				//LogBook.addLog(LogBook.LOG_IN, members.get(members.size()-1));
			//}

			MemberBook.replaceMember(oldMember,name, id, state);
			Main.getFrame().updateMembers();
			return true;
		}
	}

	public Member getLatestMember() {
		return members.get(members.size()-1);
	}
}
