package com.sgvcore.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Viagem {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @Column(nullable=false)
    private String destino_viagem;
    @Column(nullable=false)
    private Date saida;
    @Column(nullable=false)
    private Date prev_chegada;
    @Column(nullable=false)
    private String chaveviagem;
}
