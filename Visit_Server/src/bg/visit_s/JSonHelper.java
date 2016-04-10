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
            vTUser.setId_country( tmp.getInt( "idCountry" ) );
            vTUser.setId_city( tmp.getInt( "idCity" ) );
            vTUser.setId_secret_question( tmp.getInt( "idSecretQuestion" ) );
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

    public static void vtUserGetList( ClientInfo info, JSONObject jsonClient ){

        VTUserQ q = new VTUserQ();

        JSONArray array = jsonClient.getJSONArray( "mail_array" );

        for( int i = 0; i < array.size(); i++ ){

            JSONObject tmp = array.getJSONObject( i );
            String mail = tmp.getString( "mail" );
            if( mail != null && mail.isEmpty() == false ){
                q.getEmail().add( mail );
            }
        }

        VTResultList res = ConnectionHelper.vtUsersGetList( q );

        JSONObject finalOb = new JSONObject();

        if( res.getError() == 0 ){

            array = new JSONArray();

            for( VTUser tmp : (ArrayList<VTUser>) res.getResult() ){
                JSONObject json = new JSONObject();

                json.put( "id", tmp.getId() );
                json.put( "firstName", tmp.getFirstName() );
                json.put( "lastName", tmp.getLastName() );
                json.put( "email", tmp.getEmail() );
                json.put( "password", tmp.getPassword() );
                json.put( "id_city", tmp.getId_city() );
                json.put( "id_city_name", tmp.getId_city_name() );
                json.put( "id_country", tmp.getId_country() );
                json.put( "id_country_name", tmp.getId_country_name() );
                json.put( "gender", tmp.getGender() );
                json.put( "age", tmp.getAge() );
                json.put( "id_secret_question", tmp.getId_secret_question() );
                json.put( "id_secret_question_name", tmp.getId_secret_question_name() );
                json.put( "answer", tmp.getAnswer() );
                json.put( "timeins", tmp.getTimeins().getTime() );
                json.put( "timemod", tmp.getTimemod().getTime() );

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

    public static void vtAddDelModPlaceComment( ClientInfo info, JSONObject jsonClient ){

        VTAddDelMod addDelMod = new VTAddDelMod();

        JSONArray array_add = jsonClient.getJSONArray( "array_add" );

        ArrayList<VTPlaceComment> add = new ArrayList<VTPlaceComment>();

        for( int i = 0; i < array_add.size(); i++ ){

            JSONObject tmp = array_add.getJSONObject( i );

            VTPlaceComment comment = new VTPlaceComment();

            comment.setId_place( tmp.getString( "id_place" ) );
            comment.setId_user( tmp.getInt( "id_user" ) );
            comment.setComment( tmp.getString( "comment" ) );

            add.add( comment );
        }

        addDelMod.setAdd( add );

        VTResultList res = ConnectionHelper.vtAddDelModPlaceComment( addDelMod );

        JSONObject finalOb = new JSONObject();

        if( res.getError() == 0 ){

            JSONArray array = new JSONArray();

            for( VTPlaceComment tmp : (ArrayList<VTPlaceComment>) res.getResult() ){
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

    public static void vtPlaceCommentGetList( ClientInfo info, JSONObject jsonClient ){

        VTPlaceCommentQ q = new VTPlaceCommentQ();

        JSONArray array = jsonClient.getJSONArray( "id_place_array" );

        if( array != null ){
            for( int i = 0; i < array.size(); i++ ){

                JSONObject tmp = array.getJSONObject( i );
                String id_place = tmp.getString( "id_place" );
                if( id_place != null && id_place.isEmpty() == false ){
                    q.getId_place().add( id_place );
                }
            }
        }

        VTResultList res = ConnectionHelper.vtPlaceCommentGetList( q );

        JSONObject finalOb = new JSONObject();

        if( res.getError() == 0 ){

            array = new JSONArray();

            for( VTPlaceComment tmp : (ArrayList<VTPlaceComment>) res.getResult() ){
                JSONObject json = new JSONObject();

                json.put( "id", tmp.getId() );
                json.put( "id_place", tmp.getId_place() );
                json.put( "id_user", tmp.getId_user() );
                json.put( "id_user_name", tmp.getId_user_name() );
                json.put( "comment", tmp.getComment() );

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
