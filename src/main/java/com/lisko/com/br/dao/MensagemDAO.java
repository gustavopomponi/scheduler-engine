
package com.lisko.com.br.dao;

import com.lisko.com.br.entity.Mensagem;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class MensagemDAO {
    
    @SuppressWarnings("ConvertToTryWithResources")
    public static void populateMensagem(){
        
        Mensagem mensagem = new Mensagem();
        
        SessionFactory sessionfactory = new Configuration().configure().buildSessionFactory();
          
        Session session=sessionfactory.openSession();
          
        session.beginTransaction();
         
        mensagem.setTitulomensagem("Mensagem de Boletos vencidos há mais de 10 dias");
        mensagem.setAssuntomensagem("Boleto Vencido !!");
        mensagem.setMensagem("Boa Tarde !! Viemos através desta, informar que o Sr. possui boleto vencido há mais de 10 dias. Caso já tenha quitado, por favor, ignorar esta mensagem !!  Obrigado !!!");
        
        session.save(mensagem);       
        session.getTransaction().commit();
        session.close();
        
        sessionfactory.close();
        
    }
    
    public static String getMaxId(){
        
         SessionFactory sessionfactory = new Configuration().configure().buildSessionFactory();
          
         Session session=sessionfactory.openSession();
          
         session.beginTransaction();
         
         String sqlquery = ("select max(id) from Mensagem");
         Query query = session.createQuery(sqlquery);
         List list = query.list();
         
         String result = list.get(0).toString();
                    
         session.getTransaction().commit();
          
         session.close();
         sessionfactory.close();
        
         return result;
    }
    
}
