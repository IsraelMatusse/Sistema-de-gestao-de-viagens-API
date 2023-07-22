package com.sgvcore.DTOs.viaturaDTOs;

import com.sgvcore.Model.Associacao;
import com.sgvcore.Model.Rota;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
    @NotNull
    private Long lotacao;
    @NotNull
    private Long pesobruto;
    @NotBlank
    private String tipo;
    @NotNull
    private Long anofabrico;
    @NotNull
    private Long quilometragem;
    @NotBlank
    private String combustivel;
    @NotBlank
    private String nrmotor;
    @NotNull
    private Long nrportas;
    @NotBlank
    private String codigoRota;
    @NotBlank
    private String codigoAssociacao;
    @NotBlank
    private String codigoProprietario;


}
