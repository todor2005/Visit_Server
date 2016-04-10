/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bg.visit_s;

import bg.visit.*;
import bg.visit.VTUser;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Todor
 */
public class ConnectionHelper {

    public static VTResultList vtSecretQuesion( VTSecretQuesionQ q ){

        VTResultList result = new VTResultList();

        ArrayList<VTSecretQuesion> arr = new ArrayList<>();
        try {
            Statement st = Connection.conn.createStatement();

            String query = "select sq.id,sq.code,sq.question_bg "
                           + "from vt_secret_questions sq";

            ResultSet res = st.executeQuery( query );

            while( res.next() ){

                VTSecretQuesion city = new VTSecretQuesion();

                int col_index = 1;

                city.setId( res.getInt( col_index++ ) );
                city.setCode( res.getString( col_index++ ).charAt( 0 ) );
                city.setQuestion_bg( res.getString( col_index++ ) );
                arr.add( city );
            }

            result.setError( 0 );
            result.setResult( arr );

            return result;

        } catch( Exception e ) {

            result.setError( -1 );
            result.setError_str( e.getMessage() );

            return result;
        }
    }

    public static VTResultList vtCityGetList( VTCitiesQ q ){

        VTResultList result = new VTResultList();

        ArrayList<VTCities> arr = new ArrayList<>();
        try {
            Statement st = Connection.conn.createStatement();

            String query = "select c.id,c.id_country,c.city_name,cs.country_name,c.timeins "
                           + "from vt_cities c "
                           + "join vt_countries cs on (cs.id = c.id_country)";

            ResultSet res = st.executeQuery( query );

            while( res.next() ){

                VTCities city = new VTCities();

                int col_index = 1;

                city.setId( res.getInt( col_index++ ) );
                city.setId_country( res.getInt( col_index++ ) );
                city.setName( res.getString( col_index++ ) );
                city.setId_country_name( res.getString( col_index++ ) );
                city.setTimeins( new Date( res.getTimestamp( col_index++ ).getTime() ) );
                arr.add( city );
            }

            result.setError( 0 );
            result.setResult( arr );

            return result;

        } catch( Exception e ) {

            result.setError( -1 );
            result.setError_str( e.getMessage() );

            return result;
        }
    }

    public static VTResultList vtCountryGetList( VTCountriesQ q ){

        VTResultList result = new VTResultList();

        ArrayList<VTCountries> arr = new ArrayList<>();
        try {
            Statement st = Connection.conn.createStatement();

            String query = "select c.id,c.country_name, c.timeins "
                           + "from vt_countries c";

            ResultSet res = st.executeQuery( query );

            while( res.next() ){

                VTCountries country = new VTCountries();

                int col_index = 1;

                country.setId( res.getInt( col_index++ ) );
                country.setName( res.getString( col_index++ ) );
                country.setTimeins( new Date( res.getTimestamp( col_index++ ).getTime() ) );

                arr.add( country );
            }

            result.setError( 0 );
            result.setResult( arr );

            return result;

        } catch( Exception e ) {

            result.setError( -1 );
            result.setError_str( e.getMessage() );

            return result;
        }
    }

    public static VTResultList vtUsersGetList( VTUserQ q ){

        VTResultList result = new VTResultList();

        try {
            Statement st = Connection.conn.createStatement();

            String query = "select u.id,u.firstname,u.lastname,u.email,u.user_password,u.id_city, "
                           + "c.city_name,cn.id,cn.country_name,u.gender,u.age,u.id_secret_question,sq.question_bg, "
                           + "u.answer,u.timeins,u.timemod "
                           + "from vt_users u "
                           + "join vt_cities c on (c.id = u.id_city) "
                           + "join vt_countries cn on (cn.id = c.id_country) "
                           + "join vt_secret_questions sq on (sq.id = u.id_secret_question)";

            if( q.getEmail().size() > 0 ){
                query += " where u.email in ( ";
                for( int i = 0; i < q.getEmail().size(); i++ ){
                    System.out.println( "q.getEmail().get( i ) = " + q.getEmail().get( i ) );
                    query += "'" + q.getEmail().get( i ) + "'";
                    if( i != q.getEmail().size() - 1 ){
                        query += ", ";
                    }
                }
                query += " )";
            }

            ResultSet res = st.executeQuery( query );

            ArrayList<VTUser> arr = new ArrayList<VTUser>();

            while( res.next() ){

                VTUser user = new VTUser();

                int col_index = 1;

                user.setId( res.getInt( col_index++ ) );
                user.setFirstName( res.getString( col_index++ ) );
                user.setLastName( res.getString( col_index++ ) );
                user.setEmail( res.getString( col_index++ ) );
                user.setPassword( res.getString( col_index++ ) );
                user.setId_city( res.getInt( col_index++ ) );
                user.setId_city_name( res.getString( col_index++ ) );
                user.setId_country( res.getInt( col_index++ ) );
                user.setId_country_name( res.getString( col_index++ ) );
                user.setGender( res.getInt( col_index++ ) );
                user.setAge( res.getInt( col_index++ ) );
                user.setId_secret_question( res.getInt( col_index++ ) );
                user.setId_secret_question_name( res.getString( col_index++ ) );
                user.setAnswer( res.getString( col_index++ ) );
                user.setTimeins( new Date( res.getTimestamp( col_index++ ).getTime() ) );
                user.setTimemod( new Date( res.getTimestamp( col_index++ ).getTime() ) );

                arr.add( user );
            }

            result.setResult( arr );
            result.setError( 0 );

        } catch( Exception e ) {
            e.printStackTrace();
            result.setError( -1 );
            result.setError_str( e.getMessage() );
        }

        return result;
    }

    public static VTResultList vtAddDelModUser( VTAddDelMod addDelMod ){

        VTResultList result = new VTResultList();

        try {
            Statement st = Connection.conn.createStatement();

            ArrayList add = addDelMod.getAdd();

            ArrayList id_arr = new ArrayList();

            for( VTUser user : (ArrayList<VTUser>) add ){

                user.setTimeins( new Date( System.currentTimeMillis() ) );
                user.setTimemod( new Date( System.currentTimeMillis() ) );

                String query = "insert into vt_users (firstname,lastname,email,user_password,id_city,gender,age,id_secret_question,answer,timeins,timemod) "
                               + "values "
                               + "('"
                               + user.getFirstName() //userName
                               + "','"
                               + user.getLastName() //password
                               + "','"
                               + user.getEmail() //firstName
                               + "','"
                               + user.getPassword() //lastName
                               + "','"
                               + user.getId_city() //email
                               + "','"
                               + user.getGender() //gender
                               + "','"
                               + user.getAge()
                               + "','"
                               + user.getId_secret_question() //birthDay
                               + "','"
                               + user.getAnswer() //birthDay
                               + "','"
                               + user.getTimeins() //birthDay
                               + "','"
                               + user.getTimemod() //birthDay
                               + "')";

                System.out.println( "query = " + query );

                int val = st.executeUpdate( query );

                user.setId( val );

            }

            ResultSet rs = st.getGeneratedKeys();

            int counter = 0;
            if( rs.next() ){
                // Retrieve the auto generated key(s).
                int key = rs.getInt( 1 );

                VTUser user = (VTUser) add.get( counter );
                user.setId( key );
                id_arr.add( user );

                counter++;
            }

            result.setError( 0 );
            result.setResult( id_arr );

            return result;
        } catch( Exception e ) {

            result.setError( -1 );
            System.out.println( e.getMessage() );
            if( e.getMessage().equals( "ERROR: duplicate key value violates unique constraint \"vt_users_unique_email\"  Detail: Key (email)=(mail@mail.com) already exists." ) ){
                result.setError( -1001 );
                result.setError_str( "Въведения e-mail вече е регистриран" );
            } else {
                result.setError_str( e.getMessage() );
            }

            return result;
        }

    }

    public static VTResultList vtLoginAttempt( String mail, String password ){

        VTResultList result = new VTResultList();

        try {
            Statement st = Connection.conn.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE );

            String query = "select 1 from vt_users where email = '"
                           + mail
                           + "' and user_password = '"
                           + password
                           + "'";

            ResultSet res = st.executeQuery( query );

            if( res.first() ){

                result.setError( 0 );
            } else {
                result.setError( -1 );
                result.setError_str( "Грешно потребителско име или парола!" );
            }

            return result;

        } catch( Exception e ) {

            result.setError( -1 );
            result.setError_str( e.getMessage() );

            return result;
        }
    }

    public static VTResultList vtPlaceCommentGetList( VTPlaceCommentQ q ){

        VTResultList result = new VTResultList();

        try {
            Statement st = Connection.conn.createStatement();

            String query = "select pc.id,pc.id_place,pc.id_user,u.firstname || ' ' || u.lastname,pc.comment "
                           + "from vt_place_comment pc "
                           + "join vt_users u on (u.id = pc.id_user) ";

            if( q.getId_place().size() > 0 ){
                query += " where pc.id_place in ( ";
                for( int i = 0; i < q.getId_place().size(); i++ ){
                    System.out.println( "q.getEmail().get( i ) = " + q.getId_place().get( i ) );
                    query += "'" + q.getId_place().get( i ) + "'";
                    if( i != q.getId_place().size() - 1 ){
                        query += ", ";
                    }
                }
                query += " )";
            }

            ResultSet res = st.executeQuery( query );

            ArrayList<VTPlaceComment> arr = new ArrayList<VTPlaceComment>();

            while( res.next() ){

                VTPlaceComment user = new VTPlaceComment();

                int col_index = 1;

                user.setId( res.getInt( col_index++ ) );
                user.setId_place( res.getString( col_index++ ) );
                user.setId_user( res.getInt( col_index++ ) );
                user.setId_user_name( res.getString( col_index++ ) );
                user.setComment( res.getString( col_index++ ) );

                arr.add( user );
            }

            result.setResult( arr );
            result.setError( 0 );

        } catch( Exception e ) {
            e.printStackTrace();
            result.setError( -1 );
            result.setError_str( e.getMessage() );
        }

        return result;
    }

    public static VTResultList vtAddDelModPlaceComment( VTAddDelMod addDelMod ){

        VTResultList result = new VTResultList();

        try {
            Statement st = Connection.conn.createStatement();

            ArrayList<VTPlaceComment> add = addDelMod.getAdd();

            ArrayList id_arr = new ArrayList();

            for( VTPlaceComment comment : add ){

                String query = "insert into vt_place_comment  (id_place,id_user,comment) values "
                               + "('"
                               + comment.getId_place()
                               + "', "
                               + comment.getId_user()
                               + ",'"
                               + comment.getComment()
                               + "')";

                int val = st.executeUpdate( query );

            }

            ResultSet rs = st.getGeneratedKeys();

            int counter = 0;
            if( rs.next() ){
                int key = rs.getInt( 1 );

                VTPlaceComment user = (VTPlaceComment) add.get( counter );
                user.setId( key );
                id_arr.add( user );

                counter++;
            }

            result.setError( 0 );
            result.setResult( id_arr );

            return result;
        } catch( Exception e ) {

            result.setError( -1 );
            result.setError_str( e.getMessage() );

            return result;
        }

    }

}
