
package com.lisko.com.br.dao;

import com.lisko.com.br.entity.ControleEnvio;
import java.time.LocalDate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class ControleEnvioDAO {
    
    
    public static ControleEnvio getControle(String IDCliente){
        
        ControleEnvio controle;
        
        SessionFactory sessionfactory;
        sessionfactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        
        try (Session session = sessionfactory.getCurrentSession()) {

            session.beginTransaction();

            controle = (ControleEnvio)session.get(ControleEnvio.class, IDCliente);

            session.getTransaction().commit();

        }
        
        sessionfactory.close();
        
        return controle;
    
}   

            
    public static void adicionaCliente(String IDCliente){
        
        ControleEnvio controle = new ControleEnvio();

        controle.setClienteid(IDCliente);
        controle.setDataenvio(LocalDate.now());

        SessionFactory sessionfactory;
        sessionfactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        
        if(!clienteExiste(IDCliente)) {
        
        try (Session session = sessionfactory.getCurrentSession()) {

            session.beginTransaction();

            session.save(controle);

            session.getTransaction().commit();

        }
     }   

        sessionfactory.close();

        
    }
    
    public static void removeCliente(String IDCliente){
        
        if(clienteExiste(IDCliente)){
            
        SessionFactory sessionfactory;
        sessionfactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
          
        try (Session session = sessionfactory.getCurrentSession()) {

            session.beginTransaction();
            
            ControleEnvio ce = (ControleEnvio)session.load(ControleEnvio.class, IDCliente);

            session.delete(ce);
                    
            session.getTransaction().commit();

        }

        sessionfactory.close();
        
        }
    }
    
    public static boolean clienteExiste(String IDCliente){
        
        SessionFactory sessionfactory;
        sessionfactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        boolean retorno;
          
        try (Session session = sessionfactory.getCurrentSession()) {

            session.beginTransaction();
            
            ControleEnvio ce = (ControleEnvio)session.get(ControleEnvio.class, IDCliente);

            retorno = !ce.getClienteid().isEmpty();
                   
            session.getTransaction().commit();

         } catch (Exception e) {
             
            retorno = false;
             
         }

        sessionfactory.close();
        
        return retorno;
        
    }
    
    public static void atualiza(String IDCliente, int flag){
               
        ControleEnvio controle = getControle(IDCliente);
        
        controle.setDataenvio(LocalDate.now());
        controle.setDataproximoenvio(LocalDate.now().plusDays(30));
        
        if (flag != 0) {
            controle.setFlagenvio(flag);
            controle.setDataproximoenvio(LocalDate.now().plusDays(10));
        }
        
        SessionFactory sessionfactory;
        sessionfactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        
        try (Session session = sessionfactory.getCurrentSession()) {

            session.beginTransaction();

            session.update(controle);
                   
            session.getTransaction().commit();

         } catch (Exception e) {

             
         }

        sessionfactory.close();
        
    }

}
