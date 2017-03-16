package Main;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class SystemEntry {

	public static void main(String arg[]) throws FileNotFoundException, IOException, ParseException{
		JSONParser parser = new JSONParser();
		JSONObject inputjson = (JSONObject) parser.parse(new FileReader("C:/Users/Abhishek/Desktop/Semantic_QA/BioASQ-task5bPhaseA-testset1"));
		JSONArray questions_list = (JSONArray) inputjson.get("questions");
		JSONObject output = new JSONObject();
		JSONArray output_list = new JSONArray();
		Query_processing processor = new Query_processing();
		QueryDesignatedSources getinfo = new QueryDesignatedSources();
		String id;
		String question_to_query;
		for(int i=0;i<questions_list.size();i++)
		{
			JSONObject question = (JSONObject) questions_list.get(i);
			id = (String) question.get("id");
			question_to_query = (String) question.get("body");
			System.out.println(i);
			System.out.println(question_to_query);
			output_list.add(QueryDesignatedSources.QuerySoureces(processor.processQuestion(question_to_query),id));
			
		}
		output.put("questions", output_list);
		//Write Output to Json
		try (FileWriter file = new FileWriter("C:/Users/Abhishek/Documents/file1.json")) {
			file.write(toPrettyFormat(output.toString()));
			System.out.println("Successfully Copied JSON Object to File...");
		}
	}
	
	public static String toPrettyFormat(String jsonString) 
    {
        JsonParser parser = new JsonParser();
        JsonObject json = parser.parse(jsonString).getAsJsonObject();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String prettyJson = gson.toJson(json);

        return prettyJson;
    }
}
