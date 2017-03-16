package Main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class TextSimilarity {

	public float FindSimilarity(String phrase1, String Phrase2) throws IOException{
		
		String link = "http://swoogle.umbc.edu/StsService/GetStsSim?operation=api&phrase1=" + URLEncoder.encode(phrase1)+"&phrase2=" + URLEncoder.encode(Phrase2);
		URL url = new URL(link);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();  
		con.setRequestMethod("GET");  
		
		  
		// Reading response from input Stream  
		BufferedReader in = new BufferedReader( new InputStreamReader(con.getInputStream()));  
		String output;  
		StringBuffer response = new StringBuffer();  
		  
		while ((output = in.readLine()) != null) {  
		response.append(output);  
		 }  
		in.close();  
		  
		  //printing result from response   
		Float f = Float.parseFloat(response.toString());
		return f;
	}
	
}
