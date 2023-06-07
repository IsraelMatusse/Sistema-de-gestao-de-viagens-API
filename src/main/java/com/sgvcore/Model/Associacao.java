package com.sgvcore.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Associacao {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @Column(nullable=false)
    private String nomeassociacao;
    @Column(nullable=false)
    private String bairroassociacao;
    @Column(nullable=false)
    private String cidadeassociacao;
    @Column(nullable=false)
    private String emailassociacao;
    @Column(nullable=false)
    private int contactoassociacao;
}
