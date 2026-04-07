package com.tecdes.medvibe.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "consulta")
@Getter @Setter               // Gera Getters e Setters automaticamente
@NoArgsConstructor            // Gera construtor vazio (exigido pelo JPA)
@AllArgsConstructor           // Gera construtor com todos os campos
@Builder                      // Facilita a criação de objetos no Service
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dataHora;

    // RELACIONAMENTOS (O coração da Etapa 2)

    @ManyToOne(fetch = FetchType.EAGER) // Muitas consultas para um Paciente
    @JoinColumn(name = "paciente_id")   // Nome da Coluna da FK no banco
    private Paciente paciente;

    @ManyToOne(fetch = FetchType.EAGER) // Muitas consultas para um Médico
    @JoinColumn(name = "medico_id")     // Nome da Coluna da FK no banco
    private Medico medico;

    // Se você tiver uma entidade Endereco, use @OneToOne ou @ManyToOne aqui
    private Long enderecoid; 
}