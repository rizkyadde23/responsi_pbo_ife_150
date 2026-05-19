/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pbo.responsi.config;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Lab Informatika
 */
public class DatabaseConnection {
   private static volatile DatabaseConnection instance;
   private Connection connection;
   private DatabaseConnection(){
       try {
           String url = "jdbc:mysql://localhost:3306/150_responsi_pbo";
           String user = "root";
           String pass = "";
           this.connection = DriverManager.getConnection(url, user, pass);
           System.out.println("Berhasil");
       } catch (Exception e) {
           System.out.println("Gagal : "+e.getMessage());
       }
   }
   public static DatabaseConnection getInstance(){
       if (instance == null) {
           synchronized (DatabaseConnection.class) {
               if (instance == null) {
                instance = new DatabaseConnection();               
               }
           }
       }
       return instance;
   }
   
   public Connection getConnection(){
      return connection;
   }
}
