package com.lisko.com.br.scheduler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import com.lisko.com.br.dao.ParametroDAO;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import com.lisko.com.br.entity.Mail;
import java.time.ZoneId;

public class Relogio {
  
    private static void checkNextExecution() {
        
            Mail mail = new Mail();
        
            if(LocalDateTime.now().withNano(0).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME).equals(ParametroDAO.get_nextExecution())){
                System.out.println("Data é igual !!");
                Email.enviar(mail);
                ParametroDAO.updateNextExecution();
            }
            else
            {
                System.out.println("Totalmente Diferente !!");
            }
                    
    }
    
    public static void run(){
        
        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(() -> {
               
                checkNextExecution();                              
               
            }, Integer.parseInt("0"), Integer.parseInt("1"), TimeUnit.SECONDS);
        
    }
  
    public static String getData(){
        
        LocalDateTime localDate = LocalDateTime.now();
        ZoneId zoneid = ZoneId.of("America/Sao_Paulo");
        
        return localDate.atZone(zoneid).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        
    }
    
    public static LocalDateTime getDataHora()
    {
        
        LocalDateTime localDate = LocalDateTime.now().withNano(0);
        return localDate;
        
    }
    
     public static String getDataPorExtenso(){
        
        LocalDateTime localDate = LocalDateTime.now();
        ZoneId zoneid = ZoneId.of("America/Sao_Paulo");
        
        String dia = Integer.toString(localDate.atZone(zoneid).getDayOfMonth());
        String mes = localDate.atZone(zoneid).getMonth().toString();
        String ano = Integer.toString(localDate.atZone(zoneid).getYear());
        
        return dia + " de " + converteMes(mes) + " de " + ano;
        
    }
     
     private static String converteMes(String mes){
         
         String mesretorno;
         
         switch(mes) {
             
             case "JANUARY": mesretorno = "Janeiro";
                             break;
             case "FEBRUARY": mesretorno = "Fevereiro";
                             break;
             case "MARCH": mesretorno = "Março";
                             break;
             case "APRIL": mesretorno = "Abril";
                             break;
             case "MAY": mesretorno = "Maio";
                             break;
             case "JUNE": mesretorno = "Junho";
                             break;
             case "JULY": mesretorno = "Julho";
                             break;
             case "AUGUST": mesretorno = "Agosto";
                             break;
             case "SEPTEMBER": mesretorno = "Setembro";
                             break;
             case "OCTOBER": mesretorno = "Outubro";
                             break;
             case "NOVEMBER": mesretorno = "Novembro";
                             break;
             case "DECEMBER": mesretorno = "Dezembro";
                             break;
             default: mesretorno = "Invalid month";
                     break;
             
         }
         
         return mesretorno;
         
     }
    
}