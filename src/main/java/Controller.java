import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collection;

public class Controller {


    private AgencyService agencyService = new AgencyServiceMapImpl();

    public Controller() {

    }

    public Collection<Agency> getAgencies() {
        return agencyService.getAgencies();
    }


    public Agency[] getAgenciesOrder(){

        Collection<Agency> agenciesOrder = agencyService.getAgencies();

        if(agenciesOrder.toArray()[0] == null){
            return null;
        }

        Agency[] agenciesToOrder = agenciesOrder.toArray(new Agency[agenciesOrder.size()]);


        return OrderHash.getOrder(agenciesToOrder);
    }

    public void getAgenciesAPI(String urlAPI){

        String dataJSON = null;
        try {
            dataJSON = readUrl(urlAPI);
            Gson gson = new Gson();
            JsonElement jelem = gson.fromJson(dataJSON, JsonElement.class);
            JsonObject jobj = jelem.getAsJsonObject();
            String data = jobj.get("results").toString();

            Agency[] agencies = new Gson().fromJson(data, Agency[].class);

            agencyService.setAgenciesMap(agencies);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private static String readUrl(String urlString) throws IOException {

        BufferedReader reader = null;


        try {

            URL url = new URL(urlString);
            URLConnection connection = url.openConnection();
            connection.setRequestProperty("Accept","application/json");
            connection.setRequestProperty("User-Agent","Mozilla/5.0");

            reader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(), "UTF-8"
            ));
            int read = 0;
            StringBuffer buffer = new StringBuffer();
            char [] chars = new char[1024];
            while ( (read = reader.read(chars)) != -1){
                buffer.append(chars,0, read);
            }

            return buffer.toString();

        }
        finally {
            if (reader != null){
                reader.close();
            }
        }
    }


}
