
package com.lisko.com.br.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Titulos implements Serializable {

    
    @Column(name="cli")
    private String cli;
    
    @Column(name="loja")
    private String  loja;
    
    @Column(name="nome")
    private String  nome;

    @Column(name="email")
    private String email;
    
    @Column(name="pfx")
    private String pfx;
    
    @Id
    @Column(name="num")
    private String num;
    
    @Column(name="saldo")
    private String saldo;
    
    @Column(name="total_saldo")
    private String total_saldo;
    
    @Column(name="emissao")
    private String emissao;
    
    @Column(name="venc")
    private String venc;
    
    @Column(name="dias")
    private int dias;
    
    @Column(name="valor")
    private String valor;
    
    @Column(name="total_valor")
    private String total_valor;

    public String getCli() {
        return cli;
    }

    public String getLoja() {
        return loja;
    }

    public String getNome() {
        return nome;
    }
        
    public String getEmail() {
        return email;
    }

    public String getPfx() {
        return pfx;
    }

    public String getNum() {
        return num;
    }

    public String getSaldo() {
        return saldo;
    }

    public String getEmissao() {
        return emissao;
    }

    public String getVenc() {
        return venc;
    }

    public int getDias() {
        return dias;
    }

    public String getValor() {
        return valor;
    }

    public String getTotal_saldo() {
        return total_saldo;
    }

    public String getTotal_valor() {
        return total_valor;
    }

}