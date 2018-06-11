
package com.lisko.com.br.configuration;

import java.util.Properties;
 
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;
import com.lisko.com.br.dao.ParametroDAO;
import com.lisko.com.br.entity.Parametro;

@Configuration
@ComponentScan(basePackages = "com.lisko.com.br")
public class ApplicationConfig {
    
    Parametro parametro = new Parametro();
    
    @Bean
    public JavaMailSender getMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        
        parametro = ParametroDAO.getParametrosSmtp();
        
        mailSender.setHost(parametro.getSmtphost());
        mailSender.setPort(Integer.parseInt(parametro.getSmtpport()));
        
        if(parametro.getSmtpauth())
        {
            mailSender.setUsername(parametro.getSmtpusername());
            mailSender.setPassword(parametro.getSmtppassword());
        }
        
 
        Properties javaMailProperties = new Properties();
        javaMailProperties.put("mail.smtp.starttls.enable", parametro.getSmtpstarttls());
        javaMailProperties.put("mail.smtp.auth", parametro.getSmtpauth());
        javaMailProperties.put("mail.transport.protocol", "smtp");
        javaMailProperties.put("mail.debug", "true");
        

        mailSender.setJavaMailProperties(javaMailProperties);
        return mailSender;
    }
 
    @Bean
    public FreeMarkerConfigurationFactoryBean getFreeMarkerConfiguration() {
        FreeMarkerConfigurationFactoryBean fmConfigFactoryBean = new FreeMarkerConfigurationFactoryBean();
        fmConfigFactoryBean.setTemplateLoaderPath("/templates/");
        return fmConfigFactoryBean;
    }
    
}
