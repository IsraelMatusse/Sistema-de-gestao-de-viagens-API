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
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class
ViacturaAssociarMotoristaDTO {
    @NotBlank
    private String codigoViatura;
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
