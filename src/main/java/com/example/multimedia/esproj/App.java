package com.example.multimedia.esproj;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class App 
{
    public static void main( String[] args )
    {
    	try {
			//readExcel();
    		
    		ElasticSearchManager esm = new ElasticSearchManager();
    		esm.indexCreation();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public static void readExcel() throws Exception
    {
    	try {
    		
    		
    		
    		Scraper scraper = new Scraper();
    		
    		XSSFWorkbook wb = new XSSFWorkbook(new File("MovieGenreIGC_v3.xlsx"));
            XSSFSheet sheet = wb.getSheetAt(0);

            int rows = sheet.getLastRowNum();
            
            //looping through all movie in the excel sheet
            for (int i = 1; i < 10; ++i) {
                XSSFRow row = sheet.getRow(i);

                XSSFCell imdbIdCell = row.getCell(0);
                XSSFCell urlCell = row.getCell(1);
                
                String imdbId = imdbIdCell.getRawValue() ;
                String url = urlCell.getStringCellValue();
                scraper.getMovieInfo(url);

                System.out.printf("%s, %s%n", imdbId, url);
                scraper.getMovieInfo(url);

            }
            
            
            wb.close();
     
		
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
    


