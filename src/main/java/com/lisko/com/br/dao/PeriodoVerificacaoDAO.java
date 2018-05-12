package com.lisko.com.br.dao;

import com.lisko.com.br.entity.PeriodoVerificacao;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class PeriodoVerificacaoDAO {
    
    @SuppressWarnings("ConvertToTryWithResources")
    public static void get_data(){
        
        try{
            
          SessionFactory sessionfactory = new Configuration().configure().buildSessionFactory();
          
          Session session=sessionfactory.openSession();
          
          PeriodoVerificacao objPeriodo=new PeriodoVerificacao();
         
          //objPeriodo=(PeriodoVerificacao)session.get(PeriodoVerificacao.class,2);
          
          List result = session.createQuery( "from PeriodoVerificacao" ).list();
          for ( PeriodoVerificacao periodo : (List<PeriodoVerificacao>) result ) {
              System.out.println( periodo.getDescricaoPeriodoVerificacao() );
          }
          
          System.out.println(objPeriodo.getDescricaoPeriodoVerificacao());
          
          session.beginTransaction().commit();
          
          session.close();
          sessionfactory.close();
            
        } catch(HibernateException e) {
            
            System.out.println(e);
            
        } finally {
            
            
        }
        
        
    }
    
    public static void populatePeriodo(){
        
         PeriodoVerificacao periodo = new PeriodoVerificacao();
        
         SessionFactory sessionfactory = new Configuration().configure().buildSessionFactory();
          
         Session session=sessionfactory.openSession();
          
         session.beginTransaction();
          
         periodo.setDescricaoPeriodoVerificacao("Dias");
         periodo.setFatorMultiplicador(1440);
         session.save(periodo);
          
         session.getTransaction().commit();
         session.close();
         
         
         session=sessionfactory.openSession();
         session.beginTransaction();
          
         periodo.setDescricaoPeriodoVerificacao("Horas");
         periodo.setFatorMultiplicador(60);
         session.save(periodo);
          
         session.getTransaction().commit();
         session.close();
         
         session=sessionfactory.openSession();
         session.beginTransaction();
          
         periodo.setDescricaoPeriodoVerificacao("Minutos");
         periodo.setFatorMultiplicador(1);
         session.save(periodo);
          
         session.getTransaction().commit();
          
         session.close();
         sessionfactory.close();

    }
    
    public static String getMaxId(){
        
         SessionFactory sessionfactory = new Configuration().configure().buildSessionFactory();
          
         Session session=sessionfactory.openSession();
          
         session.beginTransaction();
         
         String sqlquery = ("select max(id) from PeriodoVerificacao");
         Query query = session.createQuery(sqlquery);
         List list = query.list();
         
         String result = list.get(0).toString();
                    
         session.getTransaction().commit();
          
         session.close();
         sessionfactory.close();
        
         return result;
    }
    
}
