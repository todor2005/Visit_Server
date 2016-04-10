/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bg.visit_s;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Todor
 */
public class Connection {

    public static java.sql.Connection conn;
    private static final String url = "jdbc:postgresql://130.204.218.83:5432/";
    private static final String dbName = "Visit";
    private static final String userName = "postgres";
    private static final String password = "password";


    public static void connect(){
        try {
            conn = DriverManager.getConnection( url + dbName, userName, password );
            System.out.println( "Connection to DB is successfull" );
        } catch( Exception e ) {
            e.printStackTrace();
        }
    }

    public static void disconnect(){
        try {
            conn.close();
        } catch( Exception e ) {
        }
    }
    
    public static void checkConnection(){
        try {
            if( conn == null || conn.isClosed() ){
                connect();
            }
        } catch (SQLException ex) {
        }
    }
    
}
