
package com.lisko.com.br.dao;

import com.lisko.com.br.entity.Mensagem;
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
          
         
          proximaexecucao = session.createQuery( "from Parametro" ).list();
          for ( Parametro param : (List<Parametro>) proximaexecucao ) {
                retorno = param.getProximaexecucao();
          }
          
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
    public static void populateParametro(Long periodoId, Long mensagemId) {
        
        PeriodoVerificacao periodo = new PeriodoVerificacao();
        Parametro param = new Parametro();
        
        SessionFactory sessionfactory = new Configuration().configure().buildSessionFactory();
          
        Session session=sessionfactory.openSession();
          
        session.beginTransaction();
         
        int year = LocalDateTime.now().getYear();
        int month = LocalDateTime.now().getMonthValue();
        int day = LocalDateTime.now().getDayOfMonth();
      
        periodo.setCodPeriodoVerificacao(periodoId);
        
        param.setFrequenciaverificacao(1);
        param.setEmailfrom("scheduler@lisko.com.br");
        param.setPeriodoverificacao(periodo);
        param.setProximaexecucao(LocalDateTime.of(year,month,day,23,40).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        param.setGerarlog(false);
        param.setSmtpauth(false);
        param.setSmtphost("mail.lisko.com.br");
        param.setSmtpport("25");
        param.setSmtppassword("afwerwerwe");
        param.setSmtpstarttls(false);
        param.setSmtpusername("scheduler@lisko.com.br");
        
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
        
        SessionFactory sessionfactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
          
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
              objParametro.setEmailfrom(param.getEmailfrom());
              objParametro.setSmtpauth(param.getSmtpauth());
              objParametro.setSmtpstarttls(param.getSmtpstarttls());
              objParametro.setSmtphost(param.getSmtphost());
              objParametro.setSmtppassword(param.getSmtppassword());
              objParametro.setSmtpport(param.getSmtpport());
              objParametro.setSmtpusername(param.getSmtpusername());

              
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
    
    @SuppressWarnings("JPQLValidation")
    public static Parametro getParametrosSmtp(){
        
        Parametro objParametro = new Parametro();
        List parametrosmtp;

        try (SessionFactory sessionfactory = new Configuration().configure().buildSessionFactory(); Session session = sessionfactory.openSession()) {
            
            session.beginTransaction();
            
            parametrosmtp = session.createQuery( "from Parametro" ).list();
            
            for ( Parametro param : (List<Parametro>) parametrosmtp ) {
                
                
                objParametro.setEmailfrom(param.getEmailfrom());
                objParametro.setSmtpusername(param.getSmtpusername());
                objParametro.setSmtppassword(param.getSmtppassword());
                objParametro.setSmtphost(param.getSmtphost());
                objParametro.setSmtpport(param.getSmtpport());
                objParametro.setSmtpauth(param.getSmtpauth());
                objParametro.setSmtpstarttls(param.getSmtpstarttls());
                objParametro.setGerarlog(param.getGerarlog());
                
                
            }
            
            session.getTransaction().commit();
        }
        
        return objParametro;
        
    }
    
}
