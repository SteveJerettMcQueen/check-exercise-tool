/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lms.maven.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 * @author sm6668
 */
public class DerbyNetworkListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent evt) {
        System.out.println("ContextInitialized!");
    }

    @Override
    public void contextDestroyed(ServletContextEvent evt) {
        System.out.println("ContextDestroyed!");
    }

}
