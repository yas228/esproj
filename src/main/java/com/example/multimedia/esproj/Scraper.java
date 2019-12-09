package com.example.multimedia.esproj;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Scraper {
	
	public void getMovieInfo(String url) throws IOException{
		
		Movie movie = new Movie();
		
 		Document doc = Jsoup.connect(url).get();
 		
 		//browses link with all keywords of the movie
		Document keywordDoc = Jsoup.connect(url+"/keywords?ref_=tt_stry_kw").get();
		
		movie.title = doc.title();
		
		System.out.println("Title:\t" + doc.title());

		Elements director  = doc.select("div.credit_summary_item:contains(Director) > a");
		for (Element elem:director) {
			movie.director = elem.html();
			System.out.println("Director: " + movie.director);
		}
		
		
		Elements year  = doc.select("span#titleYear > a");
		for (Element elem:year) {
			movie.yearOfProduction = Integer.valueOf(elem.html());
			System.out.println("Year: " + movie.yearOfProduction);
		}
		
		
		Elements keywords = keywordDoc.select("table.dataTable > tbody > tr > td > div.sodatext > a");
		for (Element elem:keywords) {
			movie.keywords += elem.html()+"|";
		}
		System.out.println("key:  " + movie.keywords);

		
		Elements genres  = doc.select("div.see-more:contains(Genres) > a");
		for (Element elem:genres) {
			movie.genre += elem.html()+"|";
		}
		System.out.println("Genre: " + movie.genre);

		
		
		Elements country  = doc.select("div.txt-block:contains(Country) > a");
		for (Element elem:country) {
			movie.country = elem.html();
			System.out.println("Country: " + movie.country);
		}
	}	
}
