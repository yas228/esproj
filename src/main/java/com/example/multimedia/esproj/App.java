package com.example.multimedia.esproj;

public class App 
{
    public static void main( String[] args )
    {
    	try {
			Scraper sc = new Scraper();
			sc.readExcel();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
   
}
    


