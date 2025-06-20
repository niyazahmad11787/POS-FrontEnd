package com.qa.hippo.utilities;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadTestData {

    private FileInputStream inputStream;
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private XSSFRow row;
    public static int index = 2;

    /**
     * Returns excel data from sheet
     *
     * @param filePath
     * @param sheetName
     * @return
     */
    public Object[][] readSheetData(String filePath, String sheetName) {
        intialiseResources(filePath, sheetName);
        int totalColumn = getCellCount();
        int totalRows = getRowCount();
        Object arrayExcelData[][] = new Object[totalRows - 1][totalColumn];
        int rowsIndex = 0;
        for (int index = 2; index <= totalRows; index++) {
            int colIndex = 0;
            for (int colNum = 0; colNum < totalColumn; colNum++) {
                arrayExcelData[rowsIndex][colIndex] = getCellData(colNum, index);
                colIndex++;
            }
            rowsIndex++;
        }
        return arrayExcelData;
    }

    /**
     * Returns excel cell data
     *
     * @param columnNumber
     * @param rowNumber
     * @return
     */
    public String getCellData(int columnNumber, int rowNumber) {
        String cellText = null;
        try {
            row = sheet.getRow(rowNumber - 1);
            DataFormatter dataFormatter = new DataFormatter();
            cellText = dataFormatter.formatCellValue(row.getCell(columnNumber));
        } catch (Exception e) {
            HTPLLogger.info("No data found", e);
        }
        return cellText;
    }

    /**
     * Initializes required object
     *
     * @param path
     * @param sheetName
     */
    public void intialiseResources(String path, String sheetName) {
        try {
            inputStream = new FileInputStream(path);
            workbook = new XSSFWorkbook(inputStream);
            int index = workbook.getSheetIndex(sheetName);
            if (!(workbook.getSheetIndex(sheetName) == -1)) {
                sheet = workbook.getSheetAt(index);
            }
            inputStream.close();
        } catch (Exception e) {
            HTPLLogger.error(" XLSX File not found : ", e);
        }
    }

    /**
     * Returns row count
     *
     * @return
     */
    public int getRowCount() {
        int number = sheet.getLastRowNum() + 1;
        return number;
    }

    /**
     * Returns cell count
     *
     * @return
     */
    public int getCellCount() {
        int number = sheet.getRow(0).getLastCellNum();
        return number;
    }

}

