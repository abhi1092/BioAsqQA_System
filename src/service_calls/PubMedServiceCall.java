package service_calls;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;


public class PubMedServiceCall {
	private static final String server = "http://bioasq.org:8000/pubmed";
	static URL url;
	static StringBuilder sessionURL;
	@SuppressWarnings("checked")
	public PubMedServiceCall()throws IOException
	{
		// Prepare Session URL; do this only once, the session can be used
        // several times.
		url = new URL(server);
		sessionURL = new StringBuilder();
		char[] buf = new char[1024];
        Reader is = new InputStreamReader(url.openStream(), "UTF-8");
        for (int r = is.read(buf); r >= 0; r = is.read(buf)) {
            sessionURL.append(buf, 0, r);
        }
	}
	@SuppressWarnings("checked")
    public JSONArray Query_pubmed(String keyword) throws IOException
    {
        int page = 0;
        int articlesPerPage = 10;
        // Build the request JSON object
        // json={"findPubMedCitations": ["transcription factor foxp3", 80, 5]}
        JSONObject requestObject = new JSONObject();
        JSONArray parameterList = new JSONArray();
        parameterList.add(keyword);
        parameterList.add(page);
        parameterList.add(articlesPerPage);
        requestObject.put("findPubMedCitations", parameterList);
        
        // Build the HTTP POST Request
        HttpClient client = new HttpClient();
        PostMethod method = new PostMethod(sessionURL.toString());
        
        StringPart stringPart = new StringPart("json", requestObject.toJSONString());
        stringPart.setCharSet("utf-8");
        stringPart.setContentType("application/json");
        method.setRequestEntity(new MultipartRequestEntity(new Part[] { stringPart }, method.getParams()));

        // Execute and retrieve result
       client.executeMethod(method);
        String response = method.getResponseBodyAsString();
        //System.out.println(response);
        // Parse result
        Object parsedResult = JSONValue.parse(response);
        if(parsedResult!=null)
        {
        	JSONObject result = (JSONObject) ((Map) parsedResult).get("result");
        	if(result!=null)
        	{
        		JSONArray documents = (JSONArray) result.get("documents"); 
                return documents;
        	}
            return null;
        }
        
        return null;
    	
    }
}
