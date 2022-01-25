/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoestore;

import myclasses.App;

/**
 *
 * @author pupil
 */
public class Shoestore {
    public static void main(String[] args){
        if(args.length==0){
            App.isBase = true;
        }else{
            App.isBase = false;
        }
        App app;
        app = new App();
        app.run();
    }
}
