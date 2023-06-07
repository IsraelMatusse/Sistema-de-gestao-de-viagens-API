package com.sgvcore.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Viactura extends AccoesDoSistema {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false)
    private String matricula;
    @Column(nullable=false)
    private String cor;
    @Column(nullable=false)
    private String marca;
    @Column(nullable=false)
    private String modelo;
    @Column(nullable=false)
    private Long lotacao;
    @Column(nullable=false)
    private Long pesobruto;
    @Column(nullable=false)
    private String tipo;
    @Column(nullable=false)
    private Long anofabrico;
    @Column(nullable=false)
    private Long quilometragem;
    @Column(nullable=false)
    private String combustivel;
    @Column(nullable=false)
    private String nrmotor;
    @Column(nullable=false)
    private Long nrportas;

    @ManyToOne
    private Rota idRota;
}
