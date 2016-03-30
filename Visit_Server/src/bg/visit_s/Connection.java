/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bg.visit_s;

import java.sql.DriverManager;

/**
 *
 * @author Todor
 */
public class Connection {

    public static java.sql.Connection conn;
    private String url = "jdbc:postgresql://130.204.218.83:5432/";
    private String dbName = "Visit";
    private String userName = "postgres";
    private String password = "password";

    public Connection(){
    }

    public void connect(){
        try {
            conn = DriverManager.getConnection( url + dbName, userName, password );
            System.out.println( "Connection to DB is successfull" );
        } catch( Exception e ) {
            e.printStackTrace();
        }
    }

    public void disconnect(){
        try {
            conn.close();
        } catch( Exception e ) {
            e.printStackTrace();
        }
    }
}
