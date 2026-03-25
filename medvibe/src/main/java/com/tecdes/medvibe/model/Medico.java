package com.tecdes.medvibe.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



@Entity
@Table(name = "medicos")  
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )

    private Long id;
    private String nome;
    private Long cpfid;
    private Long crmid;
    private String especialidade;

    
public Medico() {
    }

public Medico(Long id, String nome, Long cpfid, Long crmid, String especialidade) {
    this.id= id;
    this.nome =nome;
    this.cpfid = cpfid;
    this.crmid = crmid;
    this.especialidade = especialidade;

}
    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public Long getCPFid() {
        return cpfid;
    }

    public void setCPFid(Long cpfid) {
        this.cpfid = cpfid;
    }
    public Long getCRMid() {
        return crmid;
    }

    public void setCRMid(Long crmid) {
        this.crmid = crmid;

    }
    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade( String especialidade) {
        this.especialidade = especialidade;
    }

    
}

    



