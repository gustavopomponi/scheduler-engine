
package com.lisko.com.br.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Cliente implements Serializable {
    
    @Id
    @Column(name="cli")
    private String cli;
    
    @Column(name="email")
    private String  email;
    
    @Column(name="cobemail")
    private String cobemail;

    public String getCli() {
        return cli;
    }

    public String getEmail() {
        return email;
    }

    public String getCobemail() {
        return cobemail;
    }
   
}
