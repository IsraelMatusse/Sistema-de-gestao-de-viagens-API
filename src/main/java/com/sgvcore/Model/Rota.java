package com.sgvcore.Model;

import com.sgvcore.DTOs.rotaDTO.RotaCriarDTO;
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
public class Rota extends AccoesDoSistema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nomeRota;
    @Column(nullable = false)
    private Long distancia;
    @Column(nullable = false)
    private Long preco;
    @Column
    private String codigo;

    public Rota(RotaCriarDTO rotaCriarDTO) throws NoSuchAlgorithmException {
        this.distancia = rotaCriarDTO.getDistancia();
        this.nomeRota = rotaCriarDTO.getNomeRota();
        this.preco = rotaCriarDTO.getPreco();
        this.codigo = GeneratePin.generateStringPin();
    }
}
