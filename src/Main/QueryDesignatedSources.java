package Main;

import java.io.FileWriter;
import java.io.IOException;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Locale;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import service_calls.LinkedLifeDataServiceCall;
import service_calls.PubMedServiceCall;


public class QueryDesignatedSources {
	
	public static LinkedHashMap QuerySoureces(String ProcessedKeyword, String id) throws IOException
	{
		LinkedHashMap<String, Object> question_response = new LinkedHashMap<String, Object>();
		//String ProcessedKeyword = "gene fusion events";
		//String id = "51596a8ad24251bc0500009e";
		question_response.put("id", id);
		//Find relevent articles
		PubMedServiceCall PubMedCall = new PubMedServiceCall();
		JSONArray documents = PubMedCall.Query_pubmed(ProcessedKeyword);
		JSONArray doc_list = new JSONArray();
		JSONArray snippet_list = new JSONArray();
		
		String beginSection = "sections.0";
		String endSection = "sections.0";
		int offsetInBeginSection = 0;
		int offsetInEndSection = 0;
		String text;
		if(documents!=null)
		{
			for(int i=0;i<documents.size();i++)
			{
				JSONObject ob1 = (JSONObject)documents.get(i);
				//String s = "http://www.ncbi.nlm.nih.gov/pubmed/"+ob1.get("pmid");
				doc_list.add("http://www.ncbi.nlm.nih.gov/pubmed/"+ob1.get("pmid"));
				/*LinkedHashMap<String, Object> snippets = new LinkedHashMap<String, Object>();
				snippets.put("beginSection",beginSection);
				snippets.put("document","http://www.ncbi.nlm.nih.gov/pubmed/"+ob1.get("pmid"));
				snippets.put("endSection",endSection);
				snippets.put("offsetInBeginSection",offsetInBeginSection);
				snippets.put("offsetInEndSection",offsetInEndSection);
				snippets.put("text", ob1.get("documentAbstract"));
				snippet_list.add(snippets);*/
			}
			
			
		}
		question_response.put("documents", doc_list);
		//question_response.put("snippets", snippet_list);
		//Find concepts
		findConcepts finder = new findConcepts();
		JSONArray concepts = finder.FindBestConcepts(ProcessedKeyword);
		question_response.put("concepts", concepts);
		
		
		
		return question_response;
		
		
	}
	
	 
	
}
