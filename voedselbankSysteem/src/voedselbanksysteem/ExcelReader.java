package voedselbanksysteem; 

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
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

            //array containing the excelsheet
            Iterator<Row> rowIterator = sheet.iterator();
            
            //skip fist few rows that dont contain information that needs to be processed
            for(int i = 1; i < 6; i++){
                Row row = rowIterator.next();
            }
            //Iterate through each rows one by one
            boolean succes = true;
            while (rowIterator.hasNext() && succes == true){
                Row row = rowIterator.next();
                //For each row, iterate through all the columns
                Iterator<Cell> cellIterator = row.cellIterator();
                
                ArrayList<String> velden = new ArrayList();
                while (cellIterator.hasNext()){
                    Cell cell = cellIterator.next();
                    //Check the cell type after eveluating formulae
                    //If it is formula cell, it will be evaluated otherwise no change will happen
                    switch (evaluator.evaluateInCell(cell).getCellType()){
                        case Cell.CELL_TYPE_NUMERIC:
                            velden.add(cell.getNumericCellValue() + ", ");
                            break;
                        case Cell.CELL_TYPE_STRING:
                            velden.add("'" + cell.getStringCellValue() + "', ");
                            break;
                        case Cell.CELL_TYPE_FORMULA:
                            //Not again
                            break;
                        case Cell.CELL_TYPE_BLANK:
                            velden.add("NULL, ");
                    }
                }
                String temp = velden.get(velden.size() - 1);
                velden.remove(velden.size() - 1);
                
                temp = temp.replace(',', ')');
                
                velden.add(temp);
                succes = JDBCDriver.Toevoegen(velden);
            }
            if(succes == false){
                System.out.println ("Er is iets mis met de informatie van de excelfile");
            } else {
                //move data from the temp table to the correct tables
                System.out.println(JDBCDriver.setCliëntFromTemp());
                System.out.println(JDBCDriver.setIntakerFromTemp());
//                System.out.println(JDBCDriver.setHulpverlenendeInsantieFromTemp());
//                System.out.println(JDBCDriver.setIntakeFromTemp());
//                System.out.println(JDBCDriver.setUitgifteFromTemp());
//                System.out.println(JDBCDriver.setVoedselpakketFromTemp());
//                System.out.println(JDBCDriver.setIdentiteitFromTemp());
//                
                
                
                
                
            }
            file.close();
        }
        catch (IOException e) {
            System.out.println ("Er is iets mis met de Excelfile " + e.getMessage());
        }
    }
}