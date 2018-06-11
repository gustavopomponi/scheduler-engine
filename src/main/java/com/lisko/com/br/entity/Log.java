
package com.lisko.com.br.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_log")
public class Log implements Serializable {
    
    @Id @GeneratedValue(strategy=IDENTITY)
    @Column(name="cod_log", unique=true, nullable=false)
    private Long id;
    
    @Column(name="dt_envio")
    private LocalDateTime  dataenvio;
    
    @Column(name="des_destino")
    private String  destino;
    
    @Column(name="des_clienteid")
    private String clienteid;
    
    @Column(name="boo_resultado")
    private boolean  resultado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataenvio() {
        return dataenvio;
    }

    public void setDataenvio(LocalDateTime dataenvio) {
        this.dataenvio = dataenvio;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public boolean getResultado() {
        return resultado;
    }

    public void setResultado(boolean resultado) {
        this.resultado = resultado;
    }

    public String getClienteid() {
        return clienteid;
    }

    public void setClienteid(String clienteid) {
        this.clienteid = clienteid;
    } 
    
}
