package utility;

import java.io.File;
import java.io.FileInputStream;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelFile {
	
	XSSFWorkbook wb;
	XSSFSheet sheet;
	
	public ReadExcelFile(String ExcelPath) {
		try {
			File excel = new File(ExcelPath); 
			FileInputStream fis = new FileInputStream(excel);
			wb = new XSSFWorkbook(fis);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public String getData(int sheetnum, int rownum, int columnnum) {
		sheet = wb.getSheetAt(sheetnum);
		String data = sheet.getRow(rownum).getCell(columnnum).getStringCellValue();
		return data;
	}
	
	public int getRowCount(int sheetIndex) {
		int rows = wb.getSheetAt(sheetIndex).getLastRowNum();
		rows = rows+1;
		return rows;
	}
	

}
