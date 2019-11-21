/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blpobl;

/**
 *
 * @author Marcela Ferraz - Mauricio Zito
 */
public class singleton {
    private static singleton instance;
    
    public static singleton getInstance(){
        if(instance == null){
            instance = new singleton();
        }
        return instance;
    }

    private singleton(){

    }
    
}
