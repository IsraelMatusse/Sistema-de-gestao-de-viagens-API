package com.sgvcore.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MotoristaViactura extends AccoesDoSistema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private  Motorista idMotorista;
    @ManyToOne
    private Viactura idViactura;

}
