package com.lisko.com.br.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@Table(name="tb_parametro")
public class Parametro implements Serializable {
    
    @Id @GeneratedValue(strategy=IDENTITY)
    @Column(name="cod_parametro", unique=true, nullable=false)
    private Long id;
    
    @Column(name="num_frequenciaverificacao", nullable=false)
    private Integer frequenciaverificacao;
    
    @OneToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="cod_periodoverificacao")
    private PeriodoVerificacao periodoverificacao;
    
    @Column(name="dt_proximaexecucao", nullable=false)
    private String  proximaexecucao;
    
    @Column(name="dt_ultimaexecucao")
    private String  ultimaexecucao;
    
    @Column(name="dt_horainicio")
    private String horainicio;
    
    @Column(name="boo_gerarlog", nullable=false)
    private Boolean gerarlog;
   
    public Parametro() {
        
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getFrequenciaverificacao() {
        return frequenciaverificacao;
    }

    public void setFrequenciaverificacao(Integer frequenciaverificacao) {
        this.frequenciaverificacao = frequenciaverificacao;
    }

    public PeriodoVerificacao getPeriodoverificacao() {
        return periodoverificacao;
    }

    public void setPeriodoverificacao(PeriodoVerificacao periodoverificacao) {
        this.periodoverificacao = periodoverificacao;
    }

    public String getProximaexecucao() {
        return proximaexecucao;
    }

    public void setProximaexecucao(String proximaexecucao) {
        this.proximaexecucao = proximaexecucao;
    }

    public String getUltimaexecucao() {
        return ultimaexecucao;
    }

    public void setUltimaexecucao(String ultimaexecucao) {
        this.ultimaexecucao = ultimaexecucao;
    }
    
    public String getHorainicio() {
        return horainicio;
    }

    public void setHorainicio(String horainicio) {
        this.horainicio = horainicio;
    }

    public Boolean getGerarlog() {
        return gerarlog;
    }

    public void setGerarlog(Boolean gerarlog) {
        this.gerarlog = gerarlog;
    }
    
    

    
}
