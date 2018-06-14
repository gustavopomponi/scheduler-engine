/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lisko.com.br.entity;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_controleenvio")
public class ControleEnvio implements Serializable {

    @Id
    @Column(name="des_clienteid", nullable=false, unique=true)
    private String clienteid;
    
    @Column(name="dt_envio", nullable=false)
    private LocalDate dataenvio;
    
    @Column(name="dt_proximoenvio")
    private LocalDate dataproximoenvio;
    
    @Column(name="num_flagenvio")
    private int flagenvio;

    public String getClienteid() {
        return clienteid;
    }

    public void setClienteid(String clienteid) {
        this.clienteid = clienteid;
    }

    public LocalDate getDataenvio() {
        return dataenvio;
    }

    public void setDataenvio(LocalDate dataenvio) {
        this.dataenvio = dataenvio;
    }

    public LocalDate getDataproximoenvio() {
        return dataproximoenvio;
    }

    public void setDataproximoenvio(LocalDate dataproximoenvio) {
        this.dataproximoenvio = dataproximoenvio;
    }

    public int getFlagenvio() {
        return flagenvio;
    }

    public void setFlagenvio(int flagenvio) {
        this.flagenvio = flagenvio;
    }
       
    
}
