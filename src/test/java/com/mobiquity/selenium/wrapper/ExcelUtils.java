package com.mobiquity.selenium.wrapper;

import java.io.InputStream;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelUtils {

	final static Logger logger = Logger.getLogger(ExcelUtils.class);

	/*
	 * @Description : This method is used to get the First Sheet of a Test excel
	 * Data
	 */
	@SuppressWarnings("resource")
	public static HSSFSheet getSheetObj(String excelPath, String sheetName) {
		HSSFSheet sheet;
		try {
			InputStream fis = ExcelUtils.class.getResourceAsStream(excelPath);
			HSSFWorkbook workBook = new HSSFWorkbook(fis);
			sheet = workBook.getSheet(sheetName);
		} catch (Exception exception) {
			sheet = null;
			logger.error(exception.getMessage());
		}
		return sheet;
	}

	@SuppressWarnings("deprecation")
	public static String getExlCellData(HSSFSheet sheetobj, int rowNum, int colNum) {
		Object cell = "";
		String val = null;
		cell = sheetobj.getRow(rowNum).getCell(colNum);
		try {
			if (cell != null) {
				if (((HSSFCell) cell).getCellType() == HSSFCell.CELL_TYPE_STRING) {
					cell = sheetobj.getRow(rowNum).getCell(colNum).getStringCellValue();
					val = cell.toString();
				} else if (((HSSFCell) cell).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
					cell = sheetobj.getRow(rowNum).getCell(colNum).getNumericCellValue();
					val = cell.toString();
					if (val.contains("E")) {
						val = val.substring(0, val.indexOf("E"));
						val = val.replace(".", "");
					} else {
						val = val.substring(0, val.indexOf("."));
					}
				}
			}
		} catch (Exception exception) {
			val = null;
			exception.printStackTrace();
			logger.error(exception.getMessage());
		}
		return val;

	}
}
