package com.sgvcore.Model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class RecuperacaoToken extends AccoesDoSistema {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false)
    private Usuario usuario;
    @Column(nullable = false, unique = true, length = 6)
    private int token;
}