package com.example.multimedia.esproj;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Scraper {
	
	public static void getMovieInfo(String url) throws IOException{
		Document doc = Jsoup.connect(url).get();
		System.out.println("Items from:\t" + doc.title());

		Elements credit = doc.select("div.credit_summary_item");
		for (Element elem:credit) {
			System.out.println("\t:" + elem.text());
		}
		
		System.out.println("Title:\t" + doc.title());
		
		System.out.println("a href from:\t" + doc.title());
		Elements lhref = doc.select("a[href]");
		for (Element elem:lhref) {
			System.out.println("\t:" + elem.text());
		}

	}	
}
