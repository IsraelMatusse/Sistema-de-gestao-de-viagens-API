package com.sgvcore.DTOs.viaturaDTOs;

import com.sgvcore.Model.Associacao;
import com.sgvcore.Model.Rota;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViaturaCriarDTO {
    @NotBlank
    private String matricula;
    @NotBlank
    private String cor;
    @NotBlank
    private String marca;
    @NotBlank
    private String modelo;
    @NotBlank
    private Long lotacao;
    @NotBlank
    private Long pesobruto;
    @NotBlank
    private String tipo;
    @NotBlank
    private Long anofabrico;
    @NotBlank
    private Long quilometragem;
    @NotBlank
    private String combustivel;
    @NotBlank
    private String nrmotor;
    @NotBlank
    private Long nrportas;
    @NotBlank
    private String codigoRota;
    @NotBlank
    private String codigoAssociacao;
    @NotBlank
    private String codigoProprietario;
}
