package com.sgvcore.DTOs.viajanteDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViajanteCriarDTO {

    private String nome;
    private String apelido;
    private int anonascimento;
    private String bairro;
    private String email;
}
