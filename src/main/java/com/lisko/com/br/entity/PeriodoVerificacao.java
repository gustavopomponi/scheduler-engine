package com.lisko.com.br.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_periodoverificacao")
public class PeriodoVerificacao implements Serializable {
   
    private Long id;
    private String descricao;
    private Integer fatormultiplicador;
    
    public PeriodoVerificacao() {
        
    }
    
    @Id @GeneratedValue(strategy=IDENTITY)
    @Column(name="cod_periodoverificacao", unique=true, nullable=false)
    public Long getCodPeriodoVerificacao() {
        return this.id;
    }
    
    public void setCodPeriodoVerificacao(Long cod) {
        this.id = cod;
    }
    
    @Column(name="des_periodoverificacao", nullable=false)
    public String getDescricaoPeriodoVerificacao() {
        return this.descricao;
    }
    
    public void setDescricaoPeriodoVerificacao (String descricao){
        this.descricao = descricao;
    }
    
    @Column(name="num_fatormultiplicador", nullable=false)
    public Integer getFatorMultiplicador() {
        return this.fatormultiplicador;
    }
    
    public void setFatorMultiplicador(Integer fator){
       this.fatormultiplicador = fator;
    }
    
}
