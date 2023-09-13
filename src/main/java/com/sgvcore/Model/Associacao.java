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
public class Associacao extends AccoesDoSistema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String designacao;
    @Column
    private String emailassociacao;
    @Column
    private String codigo;
    @ManyToOne
    private Contacto idContacto;
    @ManyToOne
    private Licenca idLicenca;
    @ManyToOne
    private Usuario usuario;


    public Associacao(AssociacaoCriarDTO dto, Contacto contacto, Licenca licenca) throws NoSuchAlgorithmException {
        this.codigo = GeneratePin.generateStringPin();
        this.designacao = dto.getDesignacao();
        this.idContacto = contacto;
        this.idLicenca = licenca;
        this.emailassociacao = dto.getEmailAssociacao();
    }


}
