/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lms.maven.listener;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 * @author sm6668
 */
public class DerbyNetworkListener implements ServletContextListener {

//    private Connection conn;
    
    @Override
    public void contextInitialized(ServletContextEvent evt) {
        
//        String url = "jdbc:derby://localhost:1527/checkexercisetool";
//        String driver = "org.apache.derby.jdbc.ClientDriver";
//        String username = "lms";
//        String pass = "csci5520";
//
//        Class.forName(driver);
//        System.out.println("Driver loaded");
//
//        // Establish a connection 
//        conn = DriverManager.getConnection(url, username, pass);
//        System.out.println(conn);
    }

    @Override
    public void contextDestroyed(ServletContextEvent evt) {
//        conn.close();
//        System.out.println(conn);

    }

}
