package com.sgvcore.DTOs.viajanteDTO;

import com.sgvcore.DTOs.viagemDTO.ViagemAssociarViajanteDTO;
import com.sgvcore.Model.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class ViajanteCriarDTO {

    @NotNull
    private String nome;
    @NotNull
    private String apelido;
    @NotNull
    private Date dataNascimento;
    @NotNull
    private String bairro;
    @NotNull
    private String email;
    @NotNull
    private String codigoProvincia;
    @NotNull
    private String codigoDistrito;
    @NotNull
    private String numeroDocumento;
    @NotNull
    private Long idGenero;
    @NotNull
    private String msisdn;
    @NotNull
    private Long idTipoDocumento;
    @NotNull
    private Date dataValidade;
    @NotNull
    private String designacao;
    @NotNull
    private Long peso;

    public ViajanteCriarDTO(ViagemAssociarViajanteDTO dto, Genero genero, Carga novaCarga, DocumentoIdentifiacacao novoDocumento, Provincia provincia, Distrito distrito, Contacto novoContacto) {
    }
}
