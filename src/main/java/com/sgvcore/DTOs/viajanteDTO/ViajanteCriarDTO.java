package com.sgvcore.DTOs.viajanteDTO;

import com.sgvcore.Model.Carga;
import com.sgvcore.Model.DocumentoIdentifiacacao;
import com.sgvcore.Model.Genero;
import com.sgvcore.Model.Provincia;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class ViajanteCriarDTO {

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
    private Long idIdentificacao;
    @NotNull
    private Long idCarga;
    @NotNull
    private Long idGenero;
    @NotNull
    private Long idContacto;

}
