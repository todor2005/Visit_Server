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

        System.out.println( "vtAddDelModUser" );

        VTAddDelMod addDelMod = new VTAddDelMod();
        
        System.out.println( "jsonClient.toString() = " + jsonClient.toString());

        JSONArray array_add = jsonClient.getJSONArray( "array_add" );
        
        System.out.println( "ch 1 array_add.size() = " + array_add.size() );

        ArrayList add = new ArrayList();

        for( int i = 0; i < array_add.size(); i++ ){
            
            System.out.println( "ch 2" );
            
            JSONObject tmp = array_add.getJSONObject( i );
            
            System.out.println( "ch 3" );

            VTUser vTUser = new VTUser();
            
            System.out.println( "ch 4" );
            
            vTUser.setAge( tmp.getInt( "age" ) );
            System.out.println( "ch 5" );
            
            vTUser.setAnswer( tmp.getString( "answer" ) );
            System.out.println( "ch 6" );
            vTUser.setEmail( tmp.getString( "email" ) );
            System.out.println( "ch 7" );
            vTUser.setFirstName( tmp.getString( "firstName" ) );
            System.out.println( "ch 8" );
            vTUser.setLastName( tmp.getString( "lastName" ) );
            System.out.println( "ch 9" );
            vTUser.setIdCoutry( tmp.getInt( "idCountry" ) );
            System.out.println( "ch 10" );
            vTUser.setIdCity( tmp.getInt( "idCity" ) );
            System.out.println( "ch 11" );
            vTUser.setIdSecretQuestion( tmp.getInt( "idSecretQuestion" ) );
            System.out.println( "ch 12" );
            vTUser.setPassword( tmp.getString( "password" ) );
            System.out.println( "ch 13" );

            add.add( vTUser );
            
            System.out.println( "ch 14" );
        }

        System.out.println( "add.size() = " + add.size() );

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

}
