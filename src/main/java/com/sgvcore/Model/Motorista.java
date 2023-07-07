package com.sgvcore.Model;

import com.sgvcore.DTOs.viaturaDTOs.ViacturaAssociarMotoristaDTO;
import com.sgvcore.utils.GeneratePin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.security.NoSuchAlgorithmException;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class Motorista extends AccoesDoSistema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false)
    private String nome;
    @Column(nullable=false)
    private String apelido;
    @Column(nullable=false)
    private Long anonascimento;
    @Column(nullable=false)
    private String email;
    @Column
    private String codigo;

    @ManyToOne
    private Provincia idProvincia;
    @ManyToOne
    private DocumentoIdentifiacacao idIdentificacao;
    @ManyToOne
    private Genero idGenero;


    public Motorista(ViacturaAssociarMotoristaDTO dto, DocumentoIdentifiacacao documentoIdentifiacacao, Genero genero, Provincia provincia) throws NoSuchAlgorithmException {
        this.anonascimento=dto.getAnoNascimento();
        this.apelido=dto.getApelido();
        this.codigo= GeneratePin.generateStringPin();
        this.email=dto.getEmail();
        this.idIdentificacao=documentoIdentifiacacao;
        this.idProvincia=provincia;
        this.nome=dto.getNome();
        this.idGenero=genero;
    }

    public Motorista(ViacturaAssociarMotoristaDTO dto) {
    }
}
