package com.sgvcore.Model;

import com.sgvcore.DTOs.associacaoDTOs.AssociacaoCriarDTO;
import com.sgvcore.utils.GeneratePin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.security.NoSuchAlgorithmException;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Associacao {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String designacao;
    @Column
    private String emailassociacao;
    @ManyToOne
    private Contacto idContacto;
    @ManyToOne
    private Licenca idLicenca;
    private String codigo;

    public Associacao(AssociacaoCriarDTO dto, Contacto contacto, Licenca licenca) throws NoSuchAlgorithmException {
        this.codigo = GeneratePin.generateStringPin();
        this.designacao = dto.getDesignacao();
        this.idContacto = contacto;
        this.idLicenca = licenca;
        this.emailassociacao = dto.getEmailassociacao();
    }


}
