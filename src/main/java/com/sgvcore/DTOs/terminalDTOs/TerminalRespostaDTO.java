package com.sgvcore.DTOs.terminalDTOs;

import com.sgvcore.Model.Terminal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TerminalRespostaDTO {


    private String designacao;
    private String contacto;
    private String nuit;
    private String email;
    private String provincia;
    private String distrito;
    private String codigo;

    public TerminalRespostaDTO (Terminal terminal){
        this.codigo=terminal.getCodigo();
        this.provincia=terminal.getProvincia().getDesignacao();
        this.distrito=terminal.getDistrito().getDesignacao();
        this.email=terminal.getEmail();
        this.contacto=terminal.getContacto().getMsisdn();
        this.designacao=terminal.getDesignacao();
        this.nuit=terminal.getNuit();
    }
}
