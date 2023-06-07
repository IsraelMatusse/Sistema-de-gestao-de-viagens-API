package com.sgvcore.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ProprietarioEmpresa {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @Column(nullable=false)
    private String nome;
    @Column(nullable=false)
    private String bairro;
    @Column(nullable=false)
    private String cidade;
    @Column(nullable=false)
    private String tipolicenca;
    @Column(nullable=false)
    private String provincia;
    @Column(nullable=false)
    private Double nuit;
    @Column(nullable=false)
    private String alvara;
    @Column(nullable=false)
    private Long anocriacao;

}
