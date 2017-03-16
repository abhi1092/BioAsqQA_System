package Main;

import java.io.FileWriter;
import java.lang.Object;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.gson.JsonParser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;


public class JSON_Writer
{
    public static void main(String args[])
    {
        try
        {
            // Create a new JSONObject
            JSONObject jsonObject = new JSONObject();

            // Add the values to the jsonObject
            jsonObject.put("Age", "999");
            jsonObject.put("Name", "www.javainterviewpoint.com");
            

            // Create a new JSONArray object
            JSONArray jsonArray = new JSONArray();

            // Add values to the jsonArray
            jsonArray.add("India");
            jsonArray.add("England");
            jsonArray.add("Australia");

            // Add the jsoArray to jsonObject
            jsonObject.put("Countries", jsonArray);

            // Create a new FileWriter object
            FileWriter fileWriter = new FileWriter("C:/Users/Abhishek/Documents/sample.json");
           
            // Writting the jsonObject into sample.json
            
            fileWriter.write(toPrettyFormat(jsonObject.toJSONString()));
            fileWriter.close();


            System.out.println("JSON Object Successfully written to the file!!");

        } catch (Exception e)
        {
            e.printStackTrace();
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