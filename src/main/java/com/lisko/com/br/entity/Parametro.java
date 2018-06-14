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
    
    @OneToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="cod_mensagem")
    private Mensagem mensagem;
    
    @Column(name="dt_proximaexecucao", nullable=false)
    private String  proximaexecucao;
    
    @Column(name="dt_ultimaexecucao")
    private String  ultimaexecucao;
    
    @Column(name="dt_horainicio")
    private String horainicio;
    
    @Column(name="boo_gerarlog", nullable=false)
    private Boolean gerarlog;
    
    @Column(name="des_emailfrom", nullable=false, columnDefinition = "varchar(100) default 'scheduler@lisko.com.br'")
    private String emailfrom;
    
    @Column(name="des_smtpusername")
    private String smtpusername;
    
    @Column(name="des_smtppassword")
    private String smtppassword;
    
    @Column(name="des_smtphost")
    private String smtphost;
    
    @Column(name="boo_smtpauth", nullable=false, columnDefinition = "boolean default false")
    private Boolean smtpauth;
    
    @Column(name="boo_smtpstarttls", nullable=false, columnDefinition = "boolean default false")
    private Boolean smtpstarttls;
    
    @Column(name="des_smtpport")
    private String smtpport;
    
    @Column(name="num_taxajuros")
    private float taxajuros;
    
    @Column(name="num_taxamulta")
    private float taxamulta;
    
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

    public String getEmailfrom() {
        return emailfrom;
    }

    public void setEmailfrom(String emailfrom) {
        this.emailfrom = emailfrom;
    }

    public String getSmtpusername() {
        return smtpusername;
    }

    public void setSmtpusername(String smtpusername) {
        this.smtpusername = smtpusername;
    }

    public String getSmtppassword() {
        return smtppassword;
    }

    public void setSmtppassword(String smtppassword) {
        this.smtppassword = smtppassword;
    }

    public String getSmtphost() {
        return smtphost;
    }

    public void setSmtphost(String smtphost) {
        this.smtphost = smtphost;
    }

    public Boolean getSmtpauth() {
        return smtpauth;
    }

    public void setSmtpauth(Boolean smtpauth) {
        this.smtpauth = smtpauth;
    }

    public Boolean getSmtpstarttls() {
        return smtpstarttls;
    }

    public void setSmtpstarttls(Boolean smtpstarttls) {
        this.smtpstarttls = smtpstarttls;
    }

    public String getSmtpport() {
        return smtpport;
    }

    public void setSmtpport(String smtpport) {
        this.smtpport = smtpport;
    }

    public float getTaxajuros() {
        return taxajuros;
    }

    public void setTaxajuros(float taxajuros) {
        this.taxajuros = taxajuros;
    }

    public float getTaxamulta() {
        return taxamulta;
    }

    public void setTaxamulta(float taxamulta) {
        this.taxamulta = taxamulta;
    }

}
