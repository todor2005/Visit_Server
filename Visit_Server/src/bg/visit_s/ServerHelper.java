/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bg.visit_s;

import bg.visit.*;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

/**
 *
 * @author Todor
 */
public class ServerHelper {
    
    /**
     * 
     * @param text
     * @param info 
     */
    public static void readJSON( String text, ClientInfo info ){

        try {
            JSONObject json = (JSONObject) JSONSerializer.toJSON( text );

            String tip = json.getString( "type" );

            switch( tip ){
                case VTCountries.GET_LST: {
                    JSonHelper.vtCountryGetList( info );
                    break;
                }
                case VTCities.GET_LST: {
                    JSonHelper.vtCitiesGetList( info );
                    break;
                }

                default: {
                    //някаква грешка да им върнем
                    break;
                }
            }

        } catch( JSONException e ) {
            //някаква грешка да им върнем
        }
    }
    
}
