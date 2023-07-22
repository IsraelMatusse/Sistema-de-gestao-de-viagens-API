package com.sgvcore.Model;

import com.sgvcore.utils.GeneratePin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.security.NoSuchAlgorithmException;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ViagemViajante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String codigo;
    @ManyToOne
    private Viagem idViagem;
    @ManyToOne
    private Viajante idViajante;

    public ViagemViajante(Viagem viagem, Viajante viajante) throws NoSuchAlgorithmException {
        this.idViagem=viagem;
        this.idViajante=viajante;
        this.codigo= GeneratePin.generateStringPin();
    }
}
