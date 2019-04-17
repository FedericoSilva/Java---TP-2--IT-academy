import com.google.gson.Gson;
import spark.Spark.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import static spark.Spark.get;

public class MainPracticoJava {

    public static void main(String[] args) {

        Controller controller = new Controller();

            get("/agencias/", (request, response) -> {
                response.type("application/json");
                String site = request.queryParams("site");
                String paymentMethod = request.queryParams("payment_methods");
                String idAgency = request.queryParams("idAgency");


                String url;
                String sortBy = request.queryParams("sort_by");


                if(idAgency != null){
                     url = "https://api.mercadolibre.com/sites/" + site + "/payment_methods/" + paymentMethod + "/agencies/"+idAgency;


                }
                else{
                    url = "https://api.mercadolibre.com/sites/" + site + "/payment_methods/" + paymentMethod + "/agencies/";



                    String limit =  request.queryParams("limit");
                    String offset =  request.queryParams("offset");
                    boolean first = true;


                    if (limit != null) {
                        url += ( first ? "?" : "&" ) + "limit=" + limit;
                        first = false   ;
                    }
                    if (offset != null) {
                        url += ( first ? "?" : "&" ) + "offset=" + offset;
                        first = false;
                    }
                    if (sortBy != null) {

                        switch (sortBy){
                            case "address_line":
                                Agency.orderAgencies = OrderAgencies.ADDRESS_LINE;
                                break;
                            case "agency_code":
                                Agency.orderAgencies = OrderAgencies.AGENCY_CODE;
                                break;
                            case "distance":
                                Agency.orderAgencies = OrderAgencies.DISTANCE;
                                break;
                        }
                    }

                }
                setLogger(url);
                controller.getAgenciesAPI(url);

                if(sortBy!=null){
                    return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,
                            new Gson().toJsonTree(controller.getAgenciesOrder())));
                }

                return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,
                        new Gson().toJsonTree(controller.getAgencies())));
            });

    }

    private static void setLogger(String url){

        Logger logger = Logger.getLogger("Log");
        FileHandler fh;

        try {

            fh = new FileHandler("./Log application.log");
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);

            logger.info(url);
            logger.info(String.valueOf(new Date()));
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
