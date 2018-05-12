
package com.lisko.com.br.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_mensagem")
public class Mensagem implements Serializable {
    
    @Id @GeneratedValue(strategy=IDENTITY)
    @Column(name="cod_mensagem", unique=true, nullable=false)
    private Long id;
    
    @Column(name="des_titulo", nullable=false)
    private String titulomensagem;
    
    @Column(name="des_assunto", nullable=false)
    private String  assuntomensagem;
    
    @Column(name="des_mensagem", nullable=false)
    private String mensagem;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulomensagem() {
        return titulomensagem;
    }

    public void setTitulomensagem(String titulomensagem) {
        this.titulomensagem = titulomensagem;
    }

    public String getAssuntomensagem() {
        return assuntomensagem;
    }

    public void setAssuntomensagem(String assuntomensagem) {
        this.assuntomensagem = assuntomensagem;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
    
    
    
}
