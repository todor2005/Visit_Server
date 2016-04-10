/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bg.visit_s;

import bg.visit.*;
import java.util.ArrayList;
import net.sf.json.JSONArray;
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

        Connection.checkConnection();

        try {
            JSONObject json = (JSONObject) JSONSerializer.toJSON( text );

            String tip = json.getString( "type" );

            System.out.println( "tip = " + tip );

            switch( tip ){
                case VTCountries.GET_LST: {
                    JSonHelper.vtCountryGetList( info );
                    break;
                }
                case VTCities.GET_LST: {
                    JSonHelper.vtCitiesGetList( info );
                    break;
                }
                case VTSecretQuesion.GET_LST: {
                    JSonHelper.vtSecretQuesion( info );
                    break;
                }

                case VTUser.GET_AddDelMod: {
                    JSonHelper.vtAddDelModUser( info, json );
                    break;
                }
                case VTUser.GET_LST: {
                    JSonHelper.vtUserGetList( info, json );
                    break;
                }

                case Vars.LOGIN_ATTEMPT: {
                    JSonHelper.vtLoginAttempt( info, json );
                    break;
                }

                case VTPlaceComment.GET_AddDelMod: {
                    JSonHelper.vtAddDelModPlaceComment( info, json );
                    break;
                }
                case VTPlaceComment.GET_LST: {
                    JSonHelper.vtPlaceCommentGetList( info, json );
                    break;
                }

                default: {
                    sendDefaultError( "Method not found", info );
                    break;
                }
            }

        } catch( JSONException e ) {
//            sendDefaultError( e.getMessage(), info );
        }
    }

    private static void sendDefaultError( String error_str, ClientInfo info ){
        JSONObject finalOb = new JSONObject();

        finalOb.put( "error_str", error_str );

        finalOb.put( "error", -200 );

        info.mClientSender.sendMessage( finalOb.toString() );
    }

}
