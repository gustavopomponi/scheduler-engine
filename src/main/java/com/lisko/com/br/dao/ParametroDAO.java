
package com.lisko.com.br.dao;

import com.lisko.com.br.entity.Parametro;
import com.lisko.com.br.entity.PeriodoVerificacao;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ParametroDAO {
    
    @SuppressWarnings({"ConvertToTryWithResources", "JPQLValidation"})
    public static String get_nextExecution(){
        
          List proximaexecucao;
          String retorno = new String();
          
        
          SessionFactory sessionfactory = new Configuration().configure().buildSessionFactory();
          
          Session session=sessionfactory.openSession();
          
          Parametro objParametro=new Parametro();
         
          proximaexecucao = session.createQuery( "from Parametro" ).list();
          for ( Parametro param : (List<Parametro>) proximaexecucao ) {
                retorno = param.getProximaexecucao();
          }
          
          //objParametro=(Parametro)session.get(Parametro.class, Long.valueOf(3));
          
          //String varParametro = objParametro.getProximaexecucao();
          /*List result;
          result = session.createQuery( "from PeriodoVerificacao" ).list();
          for ( PeriodoVerificacao periodo : (List<PeriodoVerificacao>) result ) {
              System.out.println( periodo.getDescricaoPeriodoVerificacao() );
          }*/
          session.beginTransaction().commit();
          
          session.close();
          sessionfactory.close();
          
        return retorno;
    }
    
    @SuppressWarnings("ConvertToTryWithResources")
    public static boolean set_executionSchedule(Parametro param){
          
          SessionFactory sessionfactory = new Configuration().configure().buildSessionFactory();
          
          Session session=sessionfactory.openSession();         
          
          session.save(param);
          
          session.beginTransaction().commit();        
          
          session.close();
          sessionfactory.close();
        
          return true;
    }
    
    @SuppressWarnings({"ConvertToTryWithResources", "JPQLValidation"})
    public static boolean isDatabaseEmpty() {
        
          SessionFactory sessionfactory;
          sessionfactory = new Configuration().configure().buildSessionFactory();
          
          Session session=sessionfactory.getCurrentSession();
          
          session.beginTransaction();
          
          boolean empty;                  
          empty = sessionfactory.getCurrentSession().createQuery("Select 1 From Parametro").list().isEmpty();
          
          session.getTransaction().commit();
          
          session.close();
          sessionfactory.close();
          
          return empty;
        
    }
    
    @SuppressWarnings("ConvertToTryWithResources")
    public static void populateParametro() {
        
        PeriodoVerificacao periodo = new PeriodoVerificacao();
        Parametro param = new Parametro();
        
        SessionFactory sessionfactory = new Configuration().configure().buildSessionFactory();
          
        Session session=sessionfactory.openSession();
          
        session.beginTransaction();
         
        int year = LocalDateTime.now().getYear();
        int month = LocalDateTime.now().getMonthValue();
        int day = LocalDateTime.now().getDayOfMonth();
      
        periodo.setCodPeriodoVerificacao(Long.valueOf(PeriodoVerificacaoDAO.getMaxId()));
        
        param.setFrequenciaverificacao(1);
        param.setPeriodoverificacao(periodo);
        param.setProximaexecucao(LocalDateTime.of(year,month,day,23,40).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        param.setGerarlog(false);
        
        session.save(param);       
        session.getTransaction().commit();
        session.close();
        
        sessionfactory.close();
  
    }
    
    @SuppressWarnings("ConvertToTryWithResources")
    public static void updateNextExecution(){
        
        System.out.println("Ola Mundao !! A coisa ta complicada, mas estamos bem !!");
        
        Parametro objParametro = new Parametro();
        List proximaexecucao;
        LocalDateTime calculo = null;
        LocalDateTime horatempo = null;
        
        SessionFactory sessionfactory = new Configuration().configure().buildSessionFactory();
          
        Session session=sessionfactory.openSession();
          
        session.beginTransaction();
        
        proximaexecucao = session.createQuery( "from Parametro" ).list();
          for ( Parametro param : (List<Parametro>) proximaexecucao ) {
              
              horatempo = LocalDateTime.parse(param.getProximaexecucao());
              calculo =   horatempo.plusMinutes(param.getPeriodoverificacao().getFatorMultiplicador().longValue() * param.getFrequenciaverificacao().longValue());
              
              objParametro.setId(param.getId());
              objParametro.setUltimaexecucao(param.getProximaexecucao());
              objParametro.setProximaexecucao(calculo.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
              objParametro.setFrequenciaverificacao(param.getFrequenciaverificacao());
              objParametro.setGerarlog(param.getGerarlog());
              objParametro.setPeriodoverificacao(param.getPeriodoverificacao());
              
              Session sessionUpd=sessionfactory.openSession();
              sessionUpd.beginTransaction();
              sessionUpd.update(objParametro);
              sessionUpd.getTransaction().commit();
              sessionUpd.close();
              
              
          }
          
        session.getTransaction().commit();
        session.close();
    
        sessionfactory.close();
          
    }
    
}
