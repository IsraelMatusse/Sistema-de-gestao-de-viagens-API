package com.sgvcore.DTOs.marcaDTOs;

import com.sgvcore.Model.Marca;
import com.sgvcore.Model.Modelo;
import com.sgvcore.utils.GeneratePin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarcaRespostaDTO {

    private String designacao;
    private Long id;
    private String codigo;

    public MarcaRespostaDTO (Marca marca) throws NoSuchAlgorithmException {
        this.codigo= GeneratePin.generateStringPin();
        this.designacao=marca.getDesignacao();
        this.id= marca.getId();
    }
}
