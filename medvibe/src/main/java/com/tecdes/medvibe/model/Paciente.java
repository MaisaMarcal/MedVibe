package com.tecdes.medvibe.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



@Entity
@Table(name = "paciente")  
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )

    private Long id;
    private String nome;
    private Long cpfid;
    private String idade;
    private String sintoma;

    
public Paciente() {
    }

public Paciente(Long id, String nome, Long cpfid, String idade, String sintoma) {
    this.id= id;
    this.nome =nome;
    this.cpfid = cpfid;
    this.idade = idade;
    this.sintoma = sintoma;

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
    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;

    }
    public String getSintoma() {
        return sintoma;
    }

    public void setSintoma( String sintoma) {
        this.sintoma = sintoma;
    }

    
}

    



