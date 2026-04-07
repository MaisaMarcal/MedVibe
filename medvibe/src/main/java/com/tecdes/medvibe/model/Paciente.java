package com.tecdes.medvibe.model;

import jakarta.persistence.*;
import lombok.*; // Requisito C
import java.util.List;

@Entity
@Table(name = "paciente")
@Getter @Setter               // Gera Getters e Setters
@NoArgsConstructor            // Construtor vazio (obrigatório JPA)
@AllArgsConstructor           // Construtor com todos os campos
@Builder                      // Facilita a criação no Service
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private Long cpfid;
    private String idade;
    private String sintoma;

    // RELACIONAMENTO 1:N (Um paciente para muitas consultas)
    // mappedBy: indica que o campo "paciente" na classe Consulta é o dono da relação
    // cascade: Requisito D - Exclui as consultas se o paciente for deletado
    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Consulta> consultas;
}