package com.lisko.com.br.services;

import com.lisko.com.br.dao.LogDAO;
import com.lisko.com.br.dao.ParametroDAO;
import com.lisko.com.br.entity.Log;
import java.util.Map;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import com.lisko.com.br.entity.Mail;
import com.lisko.com.br.entity.Parametro;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.time.LocalDateTime;


@Service("mailService")
public class MailServiceImpl implements MailService {

    
    @Autowired
    JavaMailSender mailSender;
 
    @Autowired
    Configuration fmConfiguration;

    @Override
    public void sendEmail(Mail mail, String template) {
        
        Log registro = new Log();
        Parametro param = ParametroDAO.getParametrosSmtp();
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        
        registro.setDestino(mail.getMailTo());
        registro.setDataenvio(LocalDateTime.now().withNano(0));
        registro.setClienteid(mail.getClientId());
        registro.setResultado(true);        
        
        try {
 
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
 
            mimeMessageHelper.setSubject(mail.getMailSubject());
            mimeMessageHelper.setFrom(mail.getMailFrom());
            mimeMessageHelper.setTo(mail.getMailTo());
            mail.setMailContent(geContentFromTemplate(mail.getModel(), template));
            mimeMessageHelper.setText(mail.getMailContent(), true);
 
            mailSender.send(mimeMessageHelper.getMimeMessage());
            
            if(param.getGerarlog()){
                LogDAO.gerar(registro);
            }
            
        } catch (MessagingException e) {
            
            registro.setResultado(false);
            
            if(param.getGerarlog()){
                LogDAO.gerar(registro);
            }
            
        }  
       
    }
    
    public String geContentFromTemplate(Map < String, Object > model, String template) {
        StringBuilder content = new StringBuilder();
 
        try {
            content.append(FreeMarkerTemplateUtils
                .processTemplateIntoString(fmConfiguration.getTemplate(template), model));
        } catch (TemplateException | IOException e) {
            
        }
        return content.toString();
    }
    
}
