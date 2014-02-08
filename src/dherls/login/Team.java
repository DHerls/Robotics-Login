package dherls.login;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

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
	}

	public void logIn(int position, String date) {
		try {
			System.out.println("h");
			Member m = members.get(position);
			logWriter = new BufferedWriter(new FileWriter(logFile,true));
			logWriter.write("LOG_IN::" + date + "-" + m.getName() + "(" + m.getId() + ")");
			logWriter.newLine();
			System.out.println("h");
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
				logWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void test(){
		members.get(0).logIn();
	}
}
