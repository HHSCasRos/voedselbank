/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voedselbanksysteem;

import java.sql.Connection;

/**
 *
 * @author Calvin
 */
public class DatabaseSource {
    
   private static String dbserver;
   private static String database;
   private static String username;
   private static String password;
   
   private static Connection activeConn;

   private static void init()
         
   {
      try {
        String driver = "com.mysql.jdbc.Driver";
        Class.forName(driver);
      }
      catch (ClassNotFoundException e) {
          System.out.println(e);
      }


      dbserver=" ";
      database=" ";
      username = " ";
      password = " ";
      
      
   }
}
