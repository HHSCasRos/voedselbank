/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



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

/**
 *
 * @author FK
 */
public class ExcelReader {
    
    
    public void ExcelReader(String path){
       
            try {
        FileInputStream file = new FileInputStream(new File("C:\\Users\\Calvin\\Desktop\\ExcelReader\\Intakestatuslijst 23-03-2017_Anoniem(2).xls"));
        
        //Create Workbook instance holding reference to .xlsx file
        HSSFWorkbook workbook = new HSSFWorkbook(file);
 
        FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
         
        //Get first/desired sheet from the workbook
        HSSFSheet sheet = workbook.getSheetAt(0);
 
        //Iterate through each rows one by one
        Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext()) 
        {
            Row row = rowIterator.next();
            //For each row, iterate through all the columns
            Iterator<Cell> cellIterator = row.cellIterator();
             
            while (cellIterator.hasNext()) 
            {
                Cell cell = cellIterator.next();
                //Check the cell type after eveluating formulae
                //If it is formula cell, it will be evaluated otherwise no change will happen
                switch (evaluator.evaluateInCell(cell).getCellType()) 
                {
                    case Cell.CELL_TYPE_NUMERIC:
                        System.out.print(cell.getNumericCellValue() + "tt");
                        break;
                    case Cell.CELL_TYPE_STRING:
                        System.out.print(cell.getStringCellValue() + "\t");
                        break;
                    case Cell.CELL_TYPE_FORMULA:
                        //Not again
                        break;
                }
            }
            System.out.println("");
        }
        file.close();
        }
        catch (IOException e) {
            System.out.println ("Er is iets mis met de Excelfile" + e.getMessage());
    }
    
    }
}
