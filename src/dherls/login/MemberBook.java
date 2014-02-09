package dherls.login;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class MemberBook {
	
	private static Workbook wb;
	private static Sheet s;
	
	public static void init(){
		FileInputStream fs = null;
		try {
			fs = new FileInputStream("members.xls");
			System.out.println("Member Book found.");

		} catch (FileNotFoundException e) {
			System.out.println("Member Book not found, creating one.");
		} finally{
			try {
				fs.close();
			} catch (Exception e) {
			}
		}
		
		importWorkbook();
		for (int i = 0; i<3;i++){
			s.autoSizeColumn(i);
		}
		write();
		importWorkbook();
	}

	private static void setUpMemberBook() {
			
			wb = new HSSFWorkbook();
			wb.createSheet("Members");
			s = wb.getSheetAt(0);
			Row r = s.createRow(0);
			s.createFreezePane( 0, 1, 0, 1 );
			Cell c = r.createCell(0);
			CellStyle style = wb.createCellStyle();
			style.setAlignment(CellStyle.ALIGN_CENTER);
			style.setBorderBottom(CellStyle.BORDER_THICK);
			style.setBorderRight(CellStyle.BORDER_THIN);
			
			Font font = wb.createFont();
		    font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		    style.setFont(font);
			
		    c.setCellValue("Name");
			c.setCellStyle(style);
			
			c = r.createCell(1);
			c.setCellValue("ID");
			c.setCellStyle(style);
			
			c = r.createCell(2);
			c.setCellValue("Current State");
			c.setCellStyle(style);
			
			for (int i = 0; i<3; i++){
				s.autoSizeColumn(i);
			}
			
			write();
			System.out.println("Member Book created.");
		
		
	}
	
	private static void write(){
		FileOutputStream fileOut = null;
		try {
			fileOut = new FileOutputStream("members.xls");
			wb.write(fileOut);
		    fileOut.close();;
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				fileOut.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public static ArrayList<Member> importMembers(){
		ArrayList<Member> members = new ArrayList<>();
		for (Row r: s){
			if (r.getRowNum()>0 && r.getCell(0)!=null){
				members.add(new Member(r.getCell(0).getStringCellValue().trim(), (int) r.getCell(1).getNumericCellValue() , members.size(),r.getCell(2).getStringCellValue().trim().equalsIgnoreCase("IN") ? true:false));
			}
			
		}
		
		return members;
	}
	
	private static void importWorkbook(){
		FileInputStream getLogBook = null;
		try {
			getLogBook = new FileInputStream("members.xls");
			wb = WorkbookFactory.create(getLogBook);
			s = wb.getSheetAt(0);
			
		} catch (FileNotFoundException e) {
			setUpMemberBook();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				getLogBook.close();
			} catch (Exception e) {
				
			}
		}
	}
	
	public static void stateChange(Member m){
		Row r = s.getRow(m.getPosition()+1);
		Cell c = r.getCell(2);
		c.setCellValue(m.getIsLoggedIn() ? "IN" : "OUT");
		write();
		importWorkbook();
	}
	
	public static void addMember(String name, int id, boolean state){
		Row r = s.createRow(s.getLastRowNum()+1);
		Cell c = r.createCell(0);
		CellStyle style = wb.createCellStyle();
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setBorderBottom(CellStyle.BORDER_THIN);
		style.setBorderRight(CellStyle.BORDER_THIN);
		style.setBorderLeft(CellStyle.BORDER_THIN);
		
		c.setCellValue(name);
		c.setCellStyle(style);
		
		c = r.createCell(1);
		c.setCellValue(id);
		c.setCellStyle(style);
		
		c = r.createCell(2);
		c.setCellValue(state ?  "IN" : "OUT" );
		c.setCellStyle(style);
		
		write();
		importWorkbook();
	}

	public static void removeMember(Member m) {
		s.removeRow(s.getRow(m.getPosition()+1));
		s.shiftRows(m.getPosition()+2, s.getLastRowNum(), -1);
		write();
		importWorkbook();
	}
}