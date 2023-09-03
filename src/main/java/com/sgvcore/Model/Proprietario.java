package com.sgvcore.Model;

import com.sgvcore.DTOs.proprietarioDTOs.ProprietarioCriarDTO;
import com.sgvcore.utils.GeneratePin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.security.NoSuchAlgorithmException;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Proprietario extends AccoesDoSistema {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private Long anocriacao;
    @Column
    private String nuit;
    @Column
    private String codigo;
    @ManyToOne
    private Provincia idProvincia;
    @ManyToOne
    private Distrito idDistrito;
    @ManyToOne
    private DocumentoIdentifiacacao idIdentificacao;
    @ManyToOne
    private Contacto idContacto;
    @ManyToOne
    private TipoProprietario idTipoProprietario;
    @ManyToOne
    private Genero idGenero;

    public Proprietario(ProprietarioCriarDTO dto, Genero genero, Contacto contacto, Provincia provincia, TipoProprietario tipoProprietario, DocumentoIdentifiacacao documentoIdentifiacacao, Distrito distrito) throws NoSuchAlgorithmException {
        this.anocriacao = dto.getAnocriacao();
        this.codigo = GeneratePin.generateStringPin();
        this.idIdentificacao = documentoIdentifiacacao;
        this.nome = dto.getNome();
        this.idContacto = contacto;
        this.idGenero = genero;
        this.idProvincia = provincia;
        this.idTipoProprietario = tipoProprietario;
        this.nuit = dto.getNuit();
        this.idDistrito = distrito;
    }
}
