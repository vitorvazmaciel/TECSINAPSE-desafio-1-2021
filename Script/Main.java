import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONObject; 
import org.json.JSONArray;

import java.io.IOException;
import org.json.JSONException;

public class Main
{
	
	public static void main (String [] args)
	{
		
		try {

			JSONArray array = new JSONArray(readFileAsString("Input/recrutamento.json"));

			String item = "";
			Integer quantidade = 0;
			Double valor = 0.0;

			for(int j = 1; j < 5; j++) {

				String itemAux = "";
				Integer quantidadeAux = 0;
				Double valorAux = 0.0;

				for(int i = 0; i < array.length(); i++) {
					
					JSONObject obj = array.getJSONObject(i);

					if(obj.get("item").toString().equals("item " + j) && obj.get("dia").toString().substring(3).equals("12/2018")) {
						itemAux = obj.get("item").toString();
						quantidadeAux += Integer.parseInt(obj.get("quantidade").toString());
						valorAux += Double.parseDouble(obj.get("total").toString());
					}

				}

				if (quantidadeAux > quantidade || (quantidadeAux == quantidade && itemAux.compareTo(item) > 0)) {
					valor = valorAux;
					quantidade = quantidadeAux;
					item = itemAux;
				} 

			}

			System.out.printf("%s#%.2f\n", item, valor);

		} catch (IOException e) {
		    e.printStackTrace();
		} catch (JSONException e) {
		  	e.printStackTrace();
		}

	}

	public static String readFileAsString(String file) throws IOException {
        	return new String(Files.readAllBytes(Paths.get(file)));
    }
}
