package com.sgvcore.Model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
    private String bairro;
    @Column(nullable=false)
    private String email;
    @Column(nullable=false)
    private String cidade;
    @Column(nullable=false)
    private String provincia ;
    @Column(nullable=false)
    private String BI;
    private String nrcarta;
    private String nivelcarta;
}
