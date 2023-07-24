package com.sgvcore.DTOs.proprietarioDTOs;

import com.sgvcore.Model.Proprietario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProprietarioRespostaDTO {

    private String nome;
    private Long anocriacao;
    private String nuit;
    private String codigo;
    private Long id;
    private String designacaoProvincia;
    private String numeroDocumento;
    private String msidsn;
    private String designacaoTipoProprietario;
    private String designacaoGenero;

    public ProprietarioRespostaDTO(Proprietario proprietario) {
        this.anocriacao = proprietario.getAnocriacao();
        this.designacaoGenero = proprietario.getIdGenero().getDesignacao();
        this.designacaoTipoProprietario = proprietario.getIdTipoProprietario().getDesignacao();
        this.nome = proprietario.getNome();
        this.designacaoProvincia = proprietario.getIdProvincia().getDesignacao();
        this.id = proprietario.getId();
        this.nuit = proprietario.getNuit();
        this.codigo = proprietario.getCodigo();
        this.msidsn = proprietario.getIdContacto().getMsisdn();
        this.numeroDocumento = proprietario.getIdIdentificacao().getNumeroDocumento();
    }
}
