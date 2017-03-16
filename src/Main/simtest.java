package Main;

import java.io.IOException;

import org.json.simple.JSONArray;

public class simtest {

		public static void main(String arg[]) throws IOException
		{
			JSONArray output_list = new JSONArray();
			Query_processing processor = new Query_processing();
			TextSimilarity ab = new TextSimilarity();
			String p1 = "Is Hirschsprung disease a mendelian or a multifactorial disorder?";
			String p2 = "Chromosomal and related Mendelian syndromes associated with Hirschsprung's disease";
			String question_to_query = "What is known about the Kub5-Hera/RPRD1B interactome";
			String id = "xyz";
			System.out.println(processor.processQuestion(question_to_query));
			output_list.add(QueryDesignatedSources.QuerySoureces(processor.processQuestion(question_to_query),id));
		}
}
