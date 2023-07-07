package com.sgvcore.DTOs.viaturaDTOs;

import com.sgvcore.Model.DocumentoIdentifiacacao;
import com.sgvcore.Model.Genero;
import com.sgvcore.Model.Provincia;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class
ViacturaAssociarMotoristaDTO {

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
    private Long nrPortas;
    @NotBlank
    private String codigoRota;
    @NotBlank
    private String codigoAssociacao;
    @NotBlank
    private String codigoProprietario;
    @NotBlank
    private String nome;
    @NotBlank
    private String apelido;
    @NotNull
    private Long anoNascimento;
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String codigoProvincia;
    @NotBlank
    private String numeroDocumento;
    @NotNull
    private Date dataValidade;
    @NotNull
    private Long tipoDocumento;
    @NotNull
    private Long idGenero;
}
