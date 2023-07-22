package com.sgvcore.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class MarcaModelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Marca marca;
    @ManyToOne
    private Modelo modelo;
    @Column
    private String codigo;

    public MarcaModelo(Marca marca, Modelo modelo) {
        this.modelo=modelo;
        this.marca= marca;
    }
}
