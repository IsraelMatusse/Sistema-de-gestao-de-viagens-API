package com.sgvcore.Model;

import com.sgvcore.DTOs.tipoProprietarioDTOs.TipoProprietarioCriarDTO;
import com.sgvcore.utils.GeneratePin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.security.NoSuchAlgorithmException;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TipoProprietario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String designacao;
    private String codigo;

    public TipoProprietario(TipoProprietarioCriarDTO dto) throws NoSuchAlgorithmException {
        this.codigo= GeneratePin.generateStringPin();
        this.designacao=dto.getDesignacao();
    }
}
