package dherls.login;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class Team {
	
	private ArrayList<Member> members = new ArrayList<>();
	private BufferedWriter logWriter;
	private File logFile = new File("log.txt");
	
	/*Imports all members from members.txt
	 * file should be set up as followed:
	 * name1
	 * id1
	 * name2
	 * id2
	*/
	public void importMembers(){
		try {
			
			Scanner fileScanner = new Scanner(new File("members.txt"));
			while (fileScanner.hasNext()){
				try{
					members.add(new Member(fileScanner.nextLine(),fileScanner.nextLine(),this, members.size()));
				} catch(NoSuchElementException e){
					//TODO Add stuff if an id is not found
				}
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void printAll() {
		for (Member m:members){
			m.print();
		}
		
	}
	
	public void addMember(String nameIn, String idIn){
		members.add(new Member(nameIn,idIn,this, members.size()));
		LogBook.addLog(LogBook.JOIN, members.get(members.size()-1));
	}
	
	//removes the member from list 'members' and decreases the position of all members above the removed member
	public void removeMember(Member m){
		members.remove(m);
		if (m.getIsLoggedIn()){
			m.logOut();
		}
		LogBook.addLog(LogBook.LEAVE, m);
		for (Member mem: members){
			if (mem.getPosition()>m.getPosition()){
				mem.memberRemoved();
			}
		}
	}

	public void logInMember(int position) {
		Member m = members.get(position);
		LogBook.addLog(LogBook.LOG_IN,m);
	}
	
	public void logOutMember(int position) {
		Member m = members.get(position);
		LogBook.addLog(LogBook.LOG_OUT,m);
	}
	
	

	public void test(){
		members.get(0).logIn();
	}
}
