package com.popland.pop.firebase_databasedemo;

/**
 * Created by hai on 06/07/2016.
 */
public class DoiTuong{
     public String username;
     public String email;

     public DoiTuong() {
           //default constructor to call DataSnapShot.setValue(object.class)
     }

     public DoiTuong(String username,String email){
         this.username = username;
         this.email = email;
     }
}
