package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;

public class ReadXLdata {

	
	@DataProvider(name = "logindata")
    public Object[][] getData(Method m) throws EncryptedDocumentException, IOException {
        // Define the file path (correctly handle path separators for different OS)
		
		String excelSheetName=m.getName();
        File file = new File(System.getProperty("user.dir") + "/src/test/resources/testdata/testdata.xlsx");
        
        // Check if file exists
        if (!file.exists()) {
            throw new IOException("File not found: " + file.getAbsolutePath());
        }
        
        try (FileInputStream fis = new FileInputStream(file);
             Workbook workbook = WorkbookFactory.create(fis)) {
            
            Sheet sheet = workbook.getSheet(excelSheetName);
            if (sheet == null) {
                throw new IllegalArgumentException("Sheet not found: " + excelSheetName);
            }

            int numRows = sheet.getPhysicalNumberOfRows();
            if (numRows == 0) {
                return new Object[0][0]; // Return an empty array if no rows
            }

            int numCols = sheet.getRow(0).getPhysicalNumberOfCells();
            Object[][] data = new Object[numRows][numCols];

            // Iterate through rows and columns to fill the array
            for (int i = 0; i < numRows; i++) {
                Row row = sheet.getRow(i);
                if (row == null) {
                    continue; // Skip empty rows
                }
                for (int j = 0; j < numCols; j++) {
                    Cell cell = row.getCell(j);
                    if (cell != null) {
                        switch (cell.getCellType()) {
                            case STRING:
                                data[i][j] = cell.getStringCellValue();
                                break;
                            case NUMERIC:
                                data[i][j] = cell.getNumericCellValue();
                                break;
                            case BOOLEAN:
                                data[i][j] = cell.getBooleanCellValue();
                                break;
                            case FORMULA:
                                // Handle formula cells (if needed, evaluate the formula)
                                data[i][j] = cell.getCellFormula();
                                break;
                            default:
                                data[i][j] = null;
                        }
                    } else {
                        data[i][j] = null; // Handle empty cells
                    }
                }
            }

            return data;
        }
    }
}
