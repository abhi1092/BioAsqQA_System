package Main;

import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import service_calls.DOServiceCall;
import service_calls.GOServiceCall;
import service_calls.JochemServiceCall;
import service_calls.MeSHServiceCall;
import service_calls.UniProtServiceCall;

public class findConcepts {

	public JSONArray FindBestConcepts(String keyword) throws IOException{
		JSONArray concept = new JSONArray();
		int index;
		//Concepts from Mesh
		MeSHServiceCall MeshCall = new MeSHServiceCall();
		JSONArray mesh_findings = MeshCall.QueryMeSH(keyword);
		if(mesh_findings!=null)
		{
			if(mesh_findings.size()>2)
				index = 2;
			else
				index = mesh_findings.size();
			for(int i=0;i<index;i++)
			{
				JSONObject ob1 = (JSONObject)mesh_findings.get(i);
				JSONObject conce = (JSONObject) ob1.get("concept");
				concept.add(conce.get("uri"));
			}
		}
		
		
		//Concepts from DO
		DOServiceCall DOCall = new DOServiceCall();
		JSONArray DOFindings = DOCall.QueryDO(keyword);
		if(DOFindings!=null)
		{
			if(DOFindings.size()>2)
				index = 2;
			else
				index = DOFindings.size();
			for(int i=0;i<index;i++)
			{
				JSONObject ob1 = (JSONObject)DOFindings.get(i);
				JSONObject conce = (JSONObject) ob1.get("concept");
				concept.add(conce.get("uri"));
			}
		}
		
		
		//Concepts from Uniprot
		UniProtServiceCall CallUniprot = new UniProtServiceCall();
		JSONArray Uniprotfinding = CallUniprot.QueryUniProt(keyword);
		if(Uniprotfinding!=null)
		{
			if(Uniprotfinding.size()>2)
				index = 2;
			else
				index = Uniprotfinding.size();
			for(int i=0;i<index;i++)
			{
				JSONObject ob1 = (JSONObject)Uniprotfinding.get(i);
				JSONObject conce = (JSONObject) ob1.get("concept");
				concept.add(conce.get("uri"));
			}
		}
		
		
		//Concept from Jochem
		JochemServiceCall JochemCall = new JochemServiceCall();
		JSONArray Jochemfinding = JochemCall.QueryJochem(keyword);
		if(Jochemfinding!=null)
		{
			if(Jochemfinding.size()>2)
				index = 2;
			else
				index = Jochemfinding.size();
			for(int i=0;i<index;i++)
			{
				JSONObject ob1 = (JSONObject)Jochemfinding.get(i);
				JSONObject conce = (JSONObject) ob1.get("concept");
				concept.add(conce.get("uri"));
			}
		}
		
		
		//Concepts from GO
		 GOServiceCall GOCall = new GOServiceCall();
		 JSONArray GOFindings = GOCall.QueryGO(keyword);
		 if(GOFindings!=null)
		 {
			 if(GOFindings.size()>2)
					index = 2;
				else
					index = GOFindings.size();
			 for(int i=0;i<index;i++)
		        {
				 JSONObject ob1 = (JSONObject)GOFindings.get(i);
				 JSONObject conce = (JSONObject) ob1.get("concept");
				 concept.add(conce.get("uri"));
		        }
		 }
		 
		return concept;
		
	}
	
}
