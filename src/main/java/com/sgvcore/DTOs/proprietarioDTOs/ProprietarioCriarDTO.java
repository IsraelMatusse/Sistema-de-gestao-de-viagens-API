package com.sgvcore.DTOs.proprietarioDTOs;

import com.sgvcore.Model.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProprietarioCriarDTO {
    @NotBlank(message = "O nome nao pode ser nulo")
    private String nome;
    @NotNull(message = "O ano de criacao nao pode ser nulo")
    private Long anocriacao;
    @Size(min = 9, max = 9)
    @NotBlank(message = "O nuit nao pode ser nulo")
    private String nuit;
    @NotBlank(message = "O codigo da provincia nao pode ser nula")
    private String  codigoProvincia;
    @NotBlank(message = "O codigo do sistrito nao pode ser nulo")
    private String codigoDistrito;
    @NotBlank(message = "O numero do documento nao pode ser nulo")
    private String numeroDocumento;
    @NotNull(message = "A data de validade o documento nao pode ser nula")
    private Date dataValidade;
    @NotNull(message = "O tipo de documento nao pode ser nulo")
    private Long tipoDocumento;
    @NotBlank(message = "Contacto nao pode ser nulo")
    private String msidsn;
    @NotBlank(message = "Codigo de proprietario nao pode ser nulo")
    private String codigoTipoProprietario;
    private Long idGenero;
}
