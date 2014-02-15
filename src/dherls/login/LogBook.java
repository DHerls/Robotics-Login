package dherls.login;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class LogBook {
	
	public static int LOG_IN = 1;
	public static int LOG_OUT = 2;
	public static int JOIN = 3;
	public static int LEAVE = 4;
	
	private static HSSFWorkbook wb;
	private static Sheet s;
	
	private static CellStyle logStyle;
	
	private static DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
	
	public static void init(){
		FileInputStream getLogBook = null;
		try {
			getLogBook = new FileInputStream(FileHandler.getDir().getAbsolutePath() + File.separator + "log.xls");
			wb = (HSSFWorkbook) WorkbookFactory.create(getLogBook);
			s = wb.getSheetAt(0);
			System.out.println("Log Book found, import complete.");
		} catch (FileNotFoundException e) {
			System.out.println("Log Book not found, creating one.");
			setUpLogBook();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				getLogBook.close();
			} catch (Exception e) {
				
			}
		}
		
		logStyle = wb.createCellStyle();
		logStyle.setBorderBottom(CellStyle.BORDER_THIN);
		logStyle.setBorderRight(CellStyle.BORDER_THIN);
		logStyle.setBorderLeft(CellStyle.BORDER_THIN);
		logStyle.setAlignment(CellStyle.ALIGN_CENTER);
	}
	
	private static void setUpLogBook() {
		@SuppressWarnings("unused")
		FileOutputStream fileOut = null;
		
			wb = new HSSFWorkbook();
			wb.createSheet("Logs");
			s = wb.getSheetAt(0);
			Row r = s.createRow(0);
			s.createFreezePane( 0, 1, 0, 1 );
			Cell c = r.createCell(0);
			CellStyle style = wb.createCellStyle();
			style.setAlignment(CellStyle.ALIGN_CENTER);
			style.setBorderBottom(CellStyle.BORDER_THICK);
			style.setBorderRight(CellStyle.BORDER_THIN);
			
			Font font = wb.createFont();
		    font.setFontHeightInPoints((short) 10);
		    font.setFontName("Times New Roman");
		    font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		    style.setFont(font);
			
		    c.setCellValue("Action");
			c.setCellStyle(style);
			
			c = r.createCell(1);
			c.setCellValue("Date");
			c.setCellStyle(style);
			
			c = r.createCell(2);
			c.setCellValue("Name");
			c.setCellStyle(style);
			
			c = r.createCell(3);
			c.setCellValue("ID");
			c.setCellStyle(style);
			
			write();
			System.out.println("Log Book created.");

		
	}

	public static void addLog(int action, Member m) {
		/*
		 * Two different fonts and styles were set up in order to have a different first cell color.
		 */
		
		CellStyle style = wb.createCellStyle();
		Font colorFont = wb.createFont();
		Font blackFont = wb.createFont();
		Row r = s.createRow(s.getLastRowNum()+1);
		Cell c = r.createCell(0);
		
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setBorderBottom(CellStyle.BORDER_THIN);
		style.setBorderRight(CellStyle.BORDER_THIN);
		style.setBorderLeft(CellStyle.BORDER_THIN);
		blackFont.setColor(HSSFColor.BLACK.index);
		
		switch (action){
		case 1:
			colorFont.setColor(HSSFColor.BLUE.index);
			style.setFont(colorFont);
			c.setCellValue("LOG_IN");
			c.setCellStyle(style);
			System.out.println("[LOG-IN] " + dateFormat.format(new Date()) + "-" + m.getName() + "(" + m.getId() +")");
			break;
		case 2:
			colorFont.setColor(HSSFColor.LAVENDER.index);
			style.setFont(colorFont);
			c.setCellValue("LOG_OUT");
			c.setCellStyle(style);
			System.out.println("[LOG-OUT] " + dateFormat.format(new Date()) + "-" + m.getName() + "(" + m.getId() +")");

			break;
		case 3:
			colorFont.setColor(HSSFColor.GREEN.index);
			style.setFont(colorFont);
			c.setCellValue("JOIN");
			c.setCellStyle(style);
			System.out.println("[JOIN] " + dateFormat.format(new Date()) + "-" + m.getName() + "(" + m.getId() +")");

			break;
		case 4:
			colorFont.setColor(HSSFColor.RED.index);
			style.setFont(colorFont);
			c.setCellValue("LEAVE");
			c.setCellStyle(style);
			System.out.println("[LEAVE] " + dateFormat.format(new Date()) + "-" + m.getName() + "(" + m.getId() +")");

			break;
		}
		
		//Main.getFrame().checkLoggedIn();
		
		Cell cell;
		
		
		logStyle.setFont(blackFont);
		
		cell = r.createCell(1);
		cell.setCellValue(dateFormat.format(new Date()));
		cell.setCellStyle(logStyle);
		
		cell = r.createCell(2);
		cell.setCellValue(m.getName());
		cell.setCellStyle(logStyle);
		
		cell = r.createCell(3);
		cell.setCellValue(m.getId());
		cell.setCellStyle(logStyle);
		
		for (int i = 0; i< 4;i++){
			s.autoSizeColumn(i);
		}
		
		write();
		
		
	}
	
	private static void write(){
		FileOutputStream fileOut = null;
		try {
			fileOut = new FileOutputStream(FileHandler.getDir().getAbsolutePath() + File.separator + "log.xls");
			wb.write(fileOut);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				fileOut.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
