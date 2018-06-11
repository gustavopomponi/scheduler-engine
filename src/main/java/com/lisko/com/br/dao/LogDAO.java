
package com.lisko.com.br.dao;

import com.lisko.com.br.entity.Log;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class LogDAO {
   
    @SuppressWarnings("ConvertToTryWithResources")
    public static void gerar(Log registro){
            
        //Log log = new Log();
        
        SessionFactory sessionfactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
          
        Session session=sessionfactory.openSession();
          
        session.beginTransaction();
 
        session.save(registro); 
        
        session.getTransaction().commit();
        session.close();
        sessionfactory.close();
    
}}
