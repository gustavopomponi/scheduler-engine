package com.lisko.com.br.scheduler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import com.lisko.com.br.dao.ParametroDAO;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Relogio {
    
  
    private static void checkNextExecution() {
        
            if(LocalDateTime.now().withNano(0).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME).equals(ParametroDAO.get_nextExecution())){
                System.out.println("Data é igual !!");
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
  
}