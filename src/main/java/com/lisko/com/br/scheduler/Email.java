
package com.lisko.com.br.scheduler;

import java.util.HashMap;
import java.util.Map;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import com.lisko.com.br.entity.Mail;
import com.lisko.com.br.configuration.ApplicationConfig;
import com.lisko.com.br.dao.ClienteDAO;
import com.lisko.com.br.dao.ControleEnvioDAO;
import com.lisko.com.br.dao.ParametroDAO;
import com.lisko.com.br.dao.TitulosDAO;
import com.lisko.com.br.entity.Cliente;
import com.lisko.com.br.entity.ControleEnvio;
import com.lisko.com.br.entity.Parametro;
import com.lisko.com.br.entity.Titulos;
import com.lisko.com.br.services.MailService;
import java.time.LocalDate;
import java.util.List;
import org.json.JSONObject;


public class Email {
    
    @SuppressWarnings("ConvertToTryWithResources")
    public static void enviar(Mail mail){
        
        Parametro param = ParametroDAO.getParametrosSmtp();       

        List<Cliente> clientes = ClienteDAO.getClientes();
        for (Cliente st : clientes) {
             
         //Se cliente recebe cobrança por e-mail
         if("1".equals(st.getCobemail())) { 
             
            JSONObject parameters = new JSONObject();
            
            System.out.println(st.getCli());
            
            parameters.put("dataporextenso", Relogio.getDataPorExtenso());
            parameters.put("taxajuros", String.valueOf(param.getTaxajuros()));
            parameters.put("taxamulta", String.valueOf(param.getTaxamulta()));
            parameters.put("data", Relogio.getData());
            parameters.put("emaildestination", "gustavo@lisko.com.br");
            parameters.put("clienteID", st.getCli());
          
            mail.setClientId(parameters.getString("clienteID"));
            mail.setMailTo(parameters.getString("emaildestination"));
            mail.setMailFrom(param.getEmailfrom());
            mail.setMailSubject("Template de carta de cobrança"); 
             
            List<Titulos> titulosvencer = TitulosDAO.getTitulosVencer(parameters.getString("clienteID"));
            List<Titulos> titulosvencidos = TitulosDAO.getTitulosVencidos(parameters.getString("clienteID"));
            
            System.out.println(titulosvencer.isEmpty());
            System.out.println(titulosvencidos.isEmpty());
            
            //Se cliente possui titulos vencidos ou a vencer
            if (!(titulosvencer.isEmpty() && titulosvencidos.isEmpty())){

                System.out.println("EXISTE CLIENTE: " + parameters.getString("clienteID") + " : " + ControleEnvioDAO.clienteExiste(parameters.getString("clienteID")));
                System.out.println("TITULOS A VENCER: " + titulosvencer.isEmpty());
                
                if(!ControleEnvioDAO.clienteExiste(parameters.getString("clienteID")))
                {
                    ControleEnvioDAO.adicionaCliente(parameters.getString("clienteID"));
                }
                
                Map < String, Object > model = new HashMap <  > ();
                model.put("dataextenso", parameters.getString("dataporextenso"));
                model.put("datapadrao", parameters.getString("data"));
                model.put("taxajuros", parameters.getString("taxajuros"));
                model.put("taxamulta", parameters.getString("taxamulta"));

                // Caso o cliente possua títulos a vencer
                if(!titulosvencer.isEmpty()){
                    
                   String template = "avisoavencer.html";
                   enviaTitulosVencer(mail, model, titulosvencer, template);

                }
               
                //Caso o cliente possua títulos vencidos
                if(!titulosvencidos.isEmpty()){

                   String template = "cartacobranca.html";
                   enviaTitulosVencidos(mail, model, titulosvencidos, template, parameters.getString("clienteID")); 

                }

            } else {
            
                ControleEnvioDAO.removeCliente(parameters.getString("clienteID"));
                
            }
       
            //List<Titulos> titulos = TitulosDAO.getTitulos(clienteID);

         }
            
        }

    }
    
    private static void enviaTitulosVencer(Mail mail, Map < String, Object > model, List<Titulos> titulosvencer, String template){
        
        model.put("titulos", titulosvencer);
        mail.setModel(model);

        try (AbstractApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class)) {
            MailService mailService = (MailService) context.getBean("mailService");
            mailService.sendEmail(mail, template);
        }
        
    }
    
    private static void enviaTitulosVencidos(Mail mail, Map < String, Object > model, List<Titulos> titulosvencidos, String template, String IDCliente){
        
        ControleEnvio controle = ControleEnvioDAO.getControle(IDCliente);
        
        if("0".equals(String.valueOf(controle.getFlagenvio()))){
            enviaCartaCobranca(mail, model, titulosvencidos, template);
            ControleEnvioDAO.atualiza(IDCliente, controle.getFlagenvio() + 1);
        } else if ("1".equals(String.valueOf(controle.getFlagenvio())) && controle.getDataproximoenvio().equals(LocalDate.now())){
                enviaCartaCobranca(mail, model, titulosvencidos, template);
                ControleEnvioDAO.atualiza(IDCliente, controle.getFlagenvio() + 1);
        } else if ("2".equals(String.valueOf(controle.getFlagenvio())) && controle.getDataproximoenvio().equals(LocalDate.now())){
                    enviaCartaCobranca(mail, model, titulosvencidos, template);
                    ControleEnvioDAO.atualiza(IDCliente, 0);
        }   
      
    }
        
    private static void enviaCartaCobranca(Mail mail, Map < String, Object > model, List<Titulos> titulosvencidos, String template)
    {
        model.put("titulos", titulosvencidos);
        mail.setModel(model);

        try (AbstractApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class)) {
            MailService mailService = (MailService) context.getBean("mailService");
            mailService.sendEmail(mail, template);
        }

    }
        
    
}
