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

public class JochemServiceCall {
	private static final String server = "http://bioasq.org:8000/jochem";
	static URL url;
	static StringBuilder sessionURL;
	public JochemServiceCall()throws IOException{
		url = new URL(server);
		sessionURL = new StringBuilder();
        char[] buf = new char[1024];
        Reader is = new InputStreamReader(url.openStream(), "UTF-8");
        for (int r = is.read(buf); r >= 0; r = is.read(buf)) {
            sessionURL.append(buf, 0, r);
        }
	}
	
    @SuppressWarnings("unchecked")
    public JSONArray QueryJochem(String keywords) throws IOException
    {
        //String keywords = "nitric oxide synthase";
        int page = 0;
        int conceptsPerPage = 10;
        
        // Build the request JSON object
        JSONObject requestObject = new JSONObject();
        JSONArray parameterList = new JSONArray();
        parameterList.add(keywords);
        parameterList.add(page);
        parameterList.add(conceptsPerPage);
//        requestObject.put("findEntities", parameterList);
        requestObject.put("findEntitiesPaged", parameterList);

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

        JSONObject result = (JSONObject) ((Map) parsedResult).get("result");
        if(result!=null)
        {
        	JSONArray findings = (JSONArray) result.get("findings");
            return findings;
        }
        return null;
    }
}
