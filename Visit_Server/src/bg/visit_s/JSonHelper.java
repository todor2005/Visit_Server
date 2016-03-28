/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bg.visit_s;

import bg.visit.VTCities;
import bg.visit.VTCitiesQ;
import bg.visit.VTCountries;
import bg.visit.VTCountriesQ;
import java.util.ArrayList;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 *
 * @author Todor
 */
public class JSonHelper {

    public static void vtCountryGetList( ClientInfo info ){

        VTResultList res = ConnectionHelper.vtCountryGetList( new VTCountriesQ() );

        JSONObject finalOb = new JSONObject();

        if( res.getError() == 0 ){

            JSONArray array = new JSONArray();

            for( VTCountries tmp : (ArrayList<VTCountries>) res.getResult() ){
                JSONObject json = new JSONObject();

                json.put( "id", tmp.getId() );
                json.put( "name", tmp.getName() );

                array.add( json );
            }

            finalOb.put( "array", array );

        }else{
            finalOb.put( "error_str", res.getError_str() );
        }

        finalOb.put( "error", res.getError() );

        info.mClientSender.sendMessage( finalOb.toString() );
    }
    
    
    public static void vtCitiesGetList( ClientInfo info ){

        VTResultList res = ConnectionHelper.vtCityGetList( new VTCitiesQ() );

        JSONObject finalOb = new JSONObject();

        if( res.getError() == 0 ){

            JSONArray array = new JSONArray();

            for( VTCities tmp : (ArrayList<VTCities>) res.getResult() ){
                JSONObject json = new JSONObject();

                json.put( "id", tmp.getId() );
                json.put( "name", tmp.getName() );
                json.put( "id_country", tmp.getId_country() );
                json.put( "id_country_name", tmp.getId_country_name() );

                array.add( json );
            }

            finalOb.put( "array", array );

        }else{
            finalOb.put( "error_str", res.getError_str() );
        }

        finalOb.put( "error", res.getError() );

        info.mClientSender.sendMessage( finalOb.toString() );
    }

}
