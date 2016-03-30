/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bg.visit_s;

import java.util.ArrayList;

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

}
