package service_calls;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Locale;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
public class tester {
	public static void main(String[] args)throws IOException 
	{
		
		
		String s = "fusion gene";
		System.out.print("Starting Tester");
		//tester to test PubMed service 
		PubMedServiceCall ab = new PubMedServiceCall();
		JSONArray documents = ab.Query_pubmed(s);
		if(documents.size()==0)
		{
			System.out.println("Nothing was found");
		}
		 for(int i=0;i<documents.size();i++)
	        {
	        	JSONObject ob1 = (JSONObject)documents.get(i);
	        	System.out.println("http://www.ncbi.nlm.nih.gov/pubmed/"+ob1.get("pmid"));
	        	sentencebreaker((String) ob1.get("documentAbstract"));
	        }
		/*
		//tester to test Gene Ontology service
		 GOServiceCall s1 = new GOServiceCall();
		 JSONArray findings = s1.QueryGO(s);
		 for(int i=0;i<findings.size();i++)
	        {
			 JSONObject ob1 = (JSONObject)findings.get(i);
			 JSONObject conce = (JSONObject) ob1.get("concept");
			 System.out.println((String)conce.get("uri"));
	        }
		//Tester for MeSH
		MeSHServiceCall s2 = new MeSHServiceCall();
		JSONArray findings2 = s2.QueryMeSH(s);
		 for(int i=0;i<findings2.size();i++)
	        {
			 JSONObject ob1 = (JSONObject)findings2.get(i);
			 JSONObject conce = (JSONObject) ob1.get("concept");
			 System.out.println((String)conce.get("uri"));
	        }
		 
		//Tester for DO
		DOServiceCall s4 = new DOServiceCall();
		JSONArray findings4 = s4.QueryDO(s);
		for(int i=0;i<findings4.size();i++)
		{
			JSONObject ob1 = (JSONObject)findings4.get(i);
			JSONObject conce = (JSONObject) ob1.get("concept");
			System.out.println((String)conce.get("uri"));
		}
		
		//Tester for UniProt
		UniProtServiceCall s5 = new UniProtServiceCall();
		JSONArray findings5 = s5.QueryUniProt(s);
		for(int i=0;i<findings5.size();i++)
		{
			JSONObject ob1 = (JSONObject)findings5.get(i);
			JSONObject conce = (JSONObject) ob1.get("concept");
			System.out.println((String)conce.get("uri"));
		}
		
		//Tester for Jochem
		JochemServiceCall s6 = new JochemServiceCall();
		JSONArray findings6 = s6.QueryJochem(s);
		for(int i=0;i<findings6.size();i++)
		{
			JSONObject ob1 = (JSONObject)findings6.get(i);
			JSONObject conce = (JSONObject) ob1.get("concept");
			System.out.println((String)conce.get("uri"));
		}
			 
			*/
	}
	
public static void sentencebreaker(String source){
		
		BreakIterator iterator = BreakIterator.getSentenceInstance(Locale.US);
		iterator.setText(source);
		int start = iterator.first();
		for (int end = iterator.next();
		    end != BreakIterator.DONE;
		    start = end, end = iterator.next()) {
		  System.out.println(source.substring(start,end));
		  System.out.println(start);
		  System.out.println(end);
		}
	
	}
	

}
