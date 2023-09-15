package com.sgvcore.Model;

import com.sgvcore.DTOs.terminalDTOs.TerminalCriarDTO;
import com.sgvcore.utils.GeneratePin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.AccessType;

import javax.persistence.*;
import java.security.NoSuchAlgorithmException;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Terminal extends AccoesDoSistema{

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String designacao;
    private String email;
    private String nuit;
    private String codigo;
    @ManyToOne
    private Provincia provincia;
    @ManyToOne
    private Distrito distrito;
    @ManyToOne
    private Contacto contacto;
    @ManyToOne
    private Usuario usuario;

    public Terminal (TerminalCriarDTO dto, Contacto contacto, Provincia provincia, Distrito distrito) throws NoSuchAlgorithmException {
        this.codigo= GeneratePin.generateStringPin();
        this.designacao=dto.getDesignacao();
        this.email=dto.getEmail();
        this.nuit=dto.getNuit();
        this.contacto=contacto;
        this.distrito=distrito;
        this.provincia=provincia;
    }

}
