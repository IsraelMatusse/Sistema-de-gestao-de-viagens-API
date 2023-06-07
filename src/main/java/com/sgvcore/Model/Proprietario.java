package com.sgvcore.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Proprietario {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @Column(nullable=false)
    private String nome;
    @Column(nullable=false)
    private Long anocriacao;

    @ManyToOne
    private Provincia idProvincia;
    @ManyToOne
    private DocumentoIdentifiacacao idIdentificacao;
    @ManyToOne
    private Contacto idContacto;
    @ManyToOne
    private TipoProprietario idTipoProprietario;
    @ManyToOne
    private Genero idGenero;
}
