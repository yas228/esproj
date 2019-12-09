package com.example.multimedia.esproj;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;

public class ElasticSearchManager {
	RestHighLevelClient client = new RestHighLevelClient(
			RestClient.builder(
					new HttpHost("localhost", 9200, "http"),
					new HttpHost("localhost", 9201, "http")));


	public void indexCreation() {



		CreateIndexRequest request = new CreateIndexRequest("test"); 

		request.settings(Settings.builder() 
				.put("index.number_of_shards", 1)
				.put("index.number_of_replicas", 1)
				);
		try {		
			XContentBuilder builder = XContentFactory.jsonBuilder();
			builder.startObject();
			{
				builder.startObject("properties");
				{
					builder.startObject("yearOfProduction");
					{
						builder.field("type", "integer");
					}
					builder.endObject();
					builder.startObject("title");
					{
						builder.field("type", "text");
					}
					builder.endObject();
					builder.startObject("keywords");
					{
						builder.field("type", "text");
						builder.field("index", "false");
					}
					builder.endObject();
					builder.startObject("director");
					{
						builder.field("type", "keyword");
						builder.field("index", "false");					}
					builder.endObject();
					builder.startObject("genre");
					{
						builder.field("type", "text");
						builder.field("index", "false");					}
					builder.endObject();
					builder.startObject("country");
					{
						builder.field("type", "keyword");
						builder.field("index", "false");					}
					builder.endObject();
				}
				builder.endObject();
			}
			builder.endObject();
			request.mapping(builder);


			CreateIndexResponse createIndexResponse = client.indices().create(request, RequestOptions.DEFAULT);

			boolean acknowledged = createIndexResponse.isAcknowledged(); 
			boolean shardsAcknowledged = createIndexResponse.isShardsAcknowledged(); 

			System.out.println(acknowledged);
			System.out.println(shardsAcknowledged);

			this.client.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	

}
