package com.sgvcore.Model;

import com.sgvcore.utils.GeneratePin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.security.NoSuchAlgorithmException;

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
    @Column
    private String codigo;
    public MotoristaViactura(Motorista motorista, Viactura viactura) throws NoSuchAlgorithmException {
        this.idMotorista=motorista;
        this.idViactura=viactura;
        this.codigo= GeneratePin.generateStringPin();
    }

}
