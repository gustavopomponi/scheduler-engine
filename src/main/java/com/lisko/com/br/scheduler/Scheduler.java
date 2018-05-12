package com.lisko.com.br.scheduler;

import com.lisko.com.br.dao.ParametroDAO;
import com.lisko.com.br.dao.PeriodoVerificacaoDAO;

public class Scheduler {

    public static void main(String[] args) {
                       
        if (ParametroDAO.isDatabaseEmpty()){
            
            PeriodoVerificacaoDAO.populatePeriodo();
            ParametroDAO.populateParametro();
            
            Relogio.run();
            
        }
        else
        {
            
            Relogio.run();
            
        }
        
    }
}
    

