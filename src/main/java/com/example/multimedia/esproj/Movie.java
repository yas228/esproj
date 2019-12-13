package com.example.multimedia.esproj;

import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;

public class Movie {
	String Id = "";
	Integer yearOfProduction = 0;
	String title =  "" ;
	String keywords = "" ;
	String director = "" ;
	String genre = "";
	String country ="";
	
	public IndexRequest toIndexRequest() {
		Map<String, Object> jsonMap = new HashMap<>();
		jsonMap.put("yearOfProduction", yearOfProduction);
		jsonMap.put("title", title);
		jsonMap.put("keywords", keywords);
		jsonMap.put("director", director);
		jsonMap.put("genre", genre);
		jsonMap.put("country", country);
		IndexRequest indexRequest = new IndexRequest("movies")
		    .id(Id).source(jsonMap);
		
		return indexRequest;
	}
	
	public UpdateRequest toUpdateRequest() {
		Map<String, Object> jsonMap = new HashMap<>();
		jsonMap.put("keywords", keywords);
		jsonMap.put("genre", genre);
		UpdateRequest updateRequest = new UpdateRequest("movies", Id)
		    .doc(jsonMap);
		
		return updateRequest;
	}
	
	
}
