package com.sgvcore.DTOs.viaturaDTOs;

import com.sgvcore.enums.TipoCombustivel;
import com.sgvcore.enums.TipoViatura;
import com.sgvcore.validators.constraints.EnumValidation;
import com.sgvcore.validators.constraints.Matricula;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViaturaCriarDTO {
    @NotBlank(message = "Matricula nao pode ser nula")
    @Matricula(message = "Matricula invalida")
    private String matricula;
    @NotBlank
    private String cor;
    @NotBlank
    private String marca;
    @NotBlank
    private String modelo;
    @NotNull
    private int lotacao;
    @NotNull
    private Long pesobruto;
    @NotBlank
    @EnumValidation(message = "Tipo de viatura invalido", enumClass = TipoViatura.class)
    private String tipo;
    @NotNull
    private int anofabrico;
    @NotNull
    private Long quilometragem;
    @NotBlank(message = "O combustivel nao pode ser nulo")
    @EnumValidation(message = "Insira um combustivel valido", enumClass = TipoCombustivel.class)
    private String combustivel;
    @NotBlank
    private String nrmotor;
    @NotNull
    private int nrportas;
    @NotBlank
    private String codigoRota;
    @NotBlank
    private String codigoAssociacao;
    @NotBlank
    private String codigoProprietario;


}
