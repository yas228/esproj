package com.example.multimedia.esproj;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Scraper {
	
	public Movie getMovieInfo(String url, Movie movie) throws IOException{
				
 		Document doc = Jsoup.connect(url).get();
 		
 		//browses link with all keywords of the movie
		Document keywordDoc = Jsoup.connect(url+"/keywords?ref_=tt_stry_kw").get();
		
		movie.title = doc.title();
		

		Elements director  = doc.select("div.credit_summary_item:contains(Director) > a");
		for (Element elem:director) {
			movie.director = elem.html();
		}
		
		
		Elements year  = doc.select("span#titleYear > a");
		for (Element elem:year) {
			movie.yearOfProduction = Integer.valueOf(elem.html());
		}
		
		
		Elements keywords = keywordDoc.select("table.dataTable > tbody > tr > td > div.sodatext > a");
		movie.keywords = "";
		for (Element elem:keywords) {
			movie.keywords += elem.html()+"|";
		}

		movie.genre = "";
		Elements genres  = doc.select("div.see-more:contains(Genres) > a");
		for (Element elem:genres) {
			movie.genre += elem.html()+"|";
		}
		
		Elements country  = doc.select("div.txt-block:contains(Country) > a");
		for (Element elem:country) {
			movie.country = elem.html();
		}
		
		
		Elements summary  = doc.select("div#titleStoryLine > div:first-of-type > p > span");
		for (Element elem:summary) {
			movie.summary = elem.html();
    		System.out.println(movie.summary);

		}
		
		return movie;
	}	
	
	 public void readExcel() throws Exception
	    {
	    	try {
	    		
	    		ElasticSearchManager esm = new ElasticSearchManager();
	    		Movie movie = new Movie();
	    		
	    		XSSFWorkbook wb = new XSSFWorkbook(new File("MovieGenreIGC_v3.xlsx"));
	            XSSFSheet sheet = wb.getSheetAt(0);

	            int rows = sheet.getLastRowNum();
	            	            	            
	            //looping through all movie in the excel sheet
	            for (int i = 1; i < rows; i++) {
	                XSSFRow row = sheet.getRow(i);

	                XSSFCell imdbIdCell = row.getCell(0);
	                movie.Id = imdbIdCell.getRawValue();
	                
	                XSSFCell urlCell = row.getCell(1);
	                String url = urlCell.getStringCellValue();
	                
	                movie = getMovieInfo(url, movie);
	                
	                esm.index(movie.toIndexRequest()); 

	        		System.out.println(i);

	            }
	            
	            wb.close();
	            	
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
}
