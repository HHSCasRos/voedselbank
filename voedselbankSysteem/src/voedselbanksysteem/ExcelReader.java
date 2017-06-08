package voedselbanksysteem; 

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;

public class ExcelReader {
    
    
    public void ExcelReader(String path){
       
        try {
            FileInputStream file = new FileInputStream(new File(path));

            //Create Workbook instance holding reference to .xlsx file
            HSSFWorkbook workbook = new HSSFWorkbook(file);

            FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();

            //Get first/desired sheet from the workbook
            HSSFSheet sheet = workbook.getSheetAt(0);

            //Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();
            
            for(int i = 1; i < 6; i++){
                Row row = rowIterator.next();
            }
            while (rowIterator.hasNext()){
                Row row = rowIterator.next();
                //For each row, iterate through all the columns
                Iterator<Cell> cellIterator = row.cellIterator();
                String sql = "INSERT INTO gegevens VALUES(";
                while (cellIterator.hasNext()){
                    Cell cell = cellIterator.next();
                    //Check the cell type after eveluating formulae
                    //If it is formula cell, it will be evaluated otherwise no change will happen
                    switch (evaluator.evaluateInCell(cell).getCellType()){
                        case Cell.CELL_TYPE_NUMERIC:
                            sql += cell.getNumericCellValue() + ", ";
                            break;
                        case Cell.CELL_TYPE_STRING:
                            sql += "'" + cell.getStringCellValue() + "', ";
                            break;
                        case Cell.CELL_TYPE_FORMULA:
                            //Not again
                            break;
                        case Cell.CELL_TYPE_BLANK:
                            sql += "NULL, ";
                    }
                }
                //replace last "," with ")"
                int index = sql.lastIndexOf(", ");
                sql = sql.substring(0, index);
                sql += ")";
                System.out.println(sql);
            }
            file.close();
        }
        catch (IOException e) {
            System.out.println ("Er is iets mis met de Excelfile " + e.getMessage());
        }
    }
}