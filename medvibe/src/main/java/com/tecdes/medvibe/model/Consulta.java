package com.tecdes.medvibe.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "consulta")  
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )

    private Long id;
    private String dataHora;
    private Long pacienteid;
    private Long medicoid;
    private Long enderecoid;

    
public Consulta() {
    }

public Consulta(Long id, String dataHora, Long pacienteid, Long medicoid, Long enderecoid) {
    this.id= id;
    this.dataHora =dataHora;
    this.pacienteid = pacienteid;
    this.medicoid = medicoid;
    this.enderecoid = enderecoid;

}
    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }
    public Long getPacienteid() {
        return pacienteid;
    }

    public void setPacienteid(Long pacienteid) {
        this.pacienteid = pacienteid;
    }
    public Long getMedicoid() {
        return medicoid;
    }

    public void setMedicoid(Long medicoid) {
        this.medicoid = medicoid;

    }
    public Long getEnderecoid() {
        return enderecoid;
    }

    public void setCEP( Long enderecoid) {
        this.enderecoid = enderecoid;
    }

    
}

    

