package com.lisko.com.br.scheduler;

import com.lisko.com.br.dao.MensagemDAO;
import com.lisko.com.br.dao.PeriodoVerificacaoDAO;
import com.lisko.com.br.entity.Cliente;
import com.lisko.com.br.dao.ClienteDAO;
import com.lisko.com.br.dao.TitulosDAO;
import com.lisko.com.br.entity.Titulos;
import java.time.LocalDateTime;
import java.util.List;


public class Scheduler {

    public static void main(String[] args) {
        
        
        if(PeriodoVerificacaoDAO.isDatabaseEmpty())
        {
            PeriodoVerificacaoDAO.populatePeriodo();
            MensagemDAO.populateMensagem();
            
            Relogio.run();
        }
        else
        {
                     
            Relogio.run();
            
            /*List<Titulos> titulosList = TitulosDAO.getTitulos("T00901");
            for (Titulos at: titulosList){

                System.out.println(at);
                
            }
            /*
            List<Cliente> tit = ClienteDAO.getClientes();
            //System.out.println(titulos[0]);
            
            for (Cliente st : tit) {
                System.out.print("sname:"+st.getCli());
                System.out.print(" sroll:"+st.getEmail());
                System.out.println();
            } */
    }
 }
    
}