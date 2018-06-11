
package com.lisko.com.br.scheduler;

import java.util.HashMap;
import java.util.Map;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import com.lisko.com.br.entity.Mail;
import com.lisko.com.br.configuration.ApplicationConfig;
import com.lisko.com.br.dao.ClienteDAO;
import com.lisko.com.br.dao.ParametroDAO;
import com.lisko.com.br.dao.TitulosDAO;
import com.lisko.com.br.entity.Cliente;
import com.lisko.com.br.entity.Parametro;
import com.lisko.com.br.entity.Titulos;
import com.lisko.com.br.services.MailService;
import java.util.List;


public class Email {
    
    @SuppressWarnings("ConvertToTryWithResources")
    public static void enviar(Mail mail){
        
        Parametro param = ParametroDAO.getParametrosSmtp();       

        mail.setMailFrom(param.getEmailfrom());
        mail.setMailSubject("Template de carta de cobrança");
        

        List<Cliente> clientes = ClienteDAO.getClientes();
        for (Cliente st : clientes) {
           
            String dataporextenso = Relogio.getDataPorExtenso();
            String data = Relogio.getData();
            String emaildestination = "gustavo@lisko.com.br";
            String clienteID = st.getCli();
            
            mail.setClientId(clienteID);
            mail.setMailTo(emaildestination);
                    
            List<Titulos> titulos = TitulosDAO.getTitulos(st.getCli());
            
            Map < String, Object > model = new HashMap <  > ();
            model.put("dataextenso", dataporextenso);
            model.put("datapadrao", data);
            model.put("titulos", titulos);
            
            mail.setModel(model);

            AbstractApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
            MailService mailService = (MailService) context.getBean("mailService");
            mailService.sendEmail(mail);
            context.close();
            
        }

    }
    
}
