/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bg.visit_s;

import java.util.ArrayList;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 *
 * @author Todor
 */
public class VTResultList {

    private int error = 0;
    private String error_str = "";
    private ArrayList result = new ArrayList();

    public int getError(){
        return error;
    }

    public void setError( int error ){
        this.error = error;
    }

    public ArrayList getResult(){
        return result;
    }

    public void setResult( ArrayList result ){
        this.result = result;
    }

    public String getError_str(){
        return error_str;
    }

    public void setError_str( String error_str ){
        this.error_str = error_str;
    }

    public static void main( String[] args ){

        JSONArray array = new JSONArray();

        JSONObject json = new JSONObject();

        json.put( "key", "value" );
        json.put( "key1", "value1" );

        array.add( json );

        json = new JSONObject();

        json.put( "key", "value" );
        json.put( "key1", "value1" );

        array.add( json );
        
        json = new JSONObject();

        json.put( "key", "value" );
        json.put( "key1", "value1" );

        array.add( json );
        
        JSONObject finalOb = new JSONObject();
        finalOb.put( "type", "1" );
        finalOb.put( "arr", array );

        System.out.println( "json.toString() = " + json.toString() );
        System.out.println( "array.toString() = " + array.toString() );
        System.out.println( "finalOb.toString() = " + finalOb.toString() );

    }

}
