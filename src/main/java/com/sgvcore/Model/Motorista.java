package com.sgvcore.Model;

import javax.persistence.*;

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

    @ManyToOne
    private Provincia idProvincia;
    @ManyToOne
    private DocumentoIdentifiacacao idIdentificacao;
    @ManyToOne
    private Genero idGenero;


}
