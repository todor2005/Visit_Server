/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bg.visit_s;

import bg.visit.*;
import bg.visit.VTSecretQuesionQ;
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

        } else {
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

        } else {
            finalOb.put( "error_str", res.getError_str() );
        }

        finalOb.put( "error", res.getError() );

        info.mClientSender.sendMessage( finalOb.toString() );
    }

    public static void vtSecretQuesion( ClientInfo info ){

        VTResultList res = ConnectionHelper.vtSecretQuesion( new VTSecretQuesionQ() );

        JSONObject finalOb = new JSONObject();

        if( res.getError() == 0 ){

            JSONArray array = new JSONArray();

            for( VTSecretQuesion tmp : (ArrayList<VTSecretQuesion>) res.getResult() ){
                JSONObject json = new JSONObject();

                json.put( "id", tmp.getId() );
                json.put( "code", tmp.getCode() );
                json.put( "question_bg", tmp.getQuestion_bg() );

                array.add( json );
            }

            finalOb.put( "array", array );

        } else {
            finalOb.put( "error_str", res.getError_str() );
        }

        finalOb.put( "error", res.getError() );

        info.mClientSender.sendMessage( finalOb.toString() );
    }

    public static void vtAddDelModUser( ClientInfo info, JSONObject jsonClient ){

        VTAddDelMod addDelMod = new VTAddDelMod();

        JSONArray array_add = jsonClient.getJSONArray( "array_add" );

        ArrayList add = new ArrayList();

        for( int i = 0; i < array_add.size(); i++ ){

            JSONObject tmp = array_add.getJSONObject( i );

            VTUser vTUser = new VTUser();

            vTUser.setAge( tmp.getInt( "age" ) );
            vTUser.setAnswer( tmp.getString( "answer" ) );
            vTUser.setEmail( tmp.getString( "email" ) );
            vTUser.setFirstName( tmp.getString( "firstName" ) );
            vTUser.setLastName( tmp.getString( "lastName" ) );
            vTUser.setIdCoutry( tmp.getInt( "idCountry" ) );
            vTUser.setIdCity( tmp.getInt( "idCity" ) );
            vTUser.setIdSecretQuestion( tmp.getInt( "idSecretQuestion" ) );
            vTUser.setPassword( tmp.getString( "password" ) );

            add.add( vTUser );
        }

        addDelMod.setAdd( add );

        VTResultList res = ConnectionHelper.vtAddDelModUser( addDelMod );

        JSONObject finalOb = new JSONObject();

        if( res.getError() == 0 ){

            JSONArray array = new JSONArray();

            for( VTUser tmp : (ArrayList<VTUser>) res.getResult() ){
                JSONObject json = new JSONObject();

                json.put( "id", tmp.getId() );

                array.add( json );
            }

            finalOb.put( "array", array );

        } else {
            finalOb.put( "error_str", res.getError_str() );
        }

        finalOb.put( "error", res.getError() );

        info.mClientSender.sendMessage( finalOb.toString() );
    }

    public static void vtLoginAttempt( ClientInfo info, JSONObject jsonClient ){

        String mail = jsonClient.getString( "email" );
        String pass = jsonClient.getString( "password" );

        VTResultList res = ConnectionHelper.vtLoginAttempt( mail, pass );

        JSONObject finalOb = new JSONObject();

        finalOb.put( "error", res.getError() );
        finalOb.put( "error_str", res.getError_str() );

        info.mClientSender.sendMessage( finalOb.toString() );
    }

}
