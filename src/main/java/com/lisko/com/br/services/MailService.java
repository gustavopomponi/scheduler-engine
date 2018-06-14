
package com.lisko.com.br.services;

import com.lisko.com.br.entity.Mail;

public interface MailService {
    
    public void sendEmail(Mail mail, String template);
    
}
