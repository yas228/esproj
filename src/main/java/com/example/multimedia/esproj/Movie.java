package com.example.multimedia.esproj;

import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.action.index.IndexRequest;

public class Movie {
	String Id = "";
	Integer yearOfProduction = 0;
	String title =  "" ;
	String keywords = "" ;
	String director = "" ;
	String genre = "";
	String country ="";
	String summary ="";

	
	public IndexRequest toIndexRequest() {
		Map<String, Object> jsonMap = new HashMap<>();
		jsonMap.put("yearOfProduction", yearOfProduction);
		jsonMap.put("title", title);
		jsonMap.put("keywords", keywords);
		jsonMap.put("director", director);
		jsonMap.put("genre", genre);
		jsonMap.put("country", country);
		jsonMap.put("summary", summary);

		IndexRequest indexRequest = new IndexRequest("movies")
		    .id(Id).source(jsonMap);
		
		return indexRequest;
	}
		
}
