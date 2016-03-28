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

    public static ArrayList<VTUser> vtUsersGetList(){
        ArrayList<VTUser> users = new ArrayList<>();
        try {
            Statement st = Connection.conn.createStatement();

            ResultSet res = st.executeQuery( "select * from vt_users" );

            while( res.next() ){
                int id = res.getInt( "id" );
                String firstName = res.getString( "firstname" );
                String lastName = res.getString( "lastname" );
                String email = res.getString( "email" );
                String pass = res.getString( "user_password" );
                int id_city = res.getInt( "id_city" );
                int id_country = res.getInt( "id_country" );
                int gender = res.getInt( "gender" );
                int age = res.getInt( "age" );
                int id_secret_question = res.getInt( "id_secret_question" );
                String answer = res.getString( "answer" );
                Date timeins = new Date( res.getTimestamp( "timeins" ).getTime() );
                Date timemod = new Date( res.getTimestamp( "timemod" ).getTime() );

                VTUser user = new VTUser( id, firstName, lastName, email, pass, id_city, id_country, gender, age, id_secret_question, answer, timeins, timemod );

                users.add( user );
            }

        } catch( Exception e ) {
            e.printStackTrace();
        }

        return users;
    }

    public int insertUser( VTUser user ){
        try {
            Statement st = Connection.conn.createStatement();

            int val = st.executeUpdate( "insert into vt_users "
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
                                        + user.getIdCity() //email
                                        + "','"
                                        + user.getIdCountry() //number
                                        + "','"
                                        + user.getGender() //gender
                                        + "','"
                                        + user.getIdSecretQuestion() //birthDay
                                        + "','"
                                        + user.getAnswer() //birthDay
                                        + "','"
                                        + user.getTimeins() //birthDay
                                        + "','"
                                        + user.getTimemod() //birthDay
                                        + "')" );

            return val;
        } catch( Exception e ) {
            e.printStackTrace();
        }

        return -1;
    }

}
