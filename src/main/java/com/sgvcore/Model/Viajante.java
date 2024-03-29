package com.sgvcore.Model;

import com.sgvcore.DTOs.viagemDTO.ViagemAssociarViajanteDTO;
import com.sgvcore.DTOs.viajanteDTO.ViajanteCriarDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Viajante extends AccoesDoSistema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String apelido;
    @Column(nullable = false)
    private Date dataNascimento;
    @Column(nullable = false)
    private String email;
    @ManyToOne
    private Provincia idProvincia;
    @ManyToOne
    private DocumentoIdentifiacacao idIdentificacao;
    @ManyToOne
    private Carga idCarga;
    @ManyToOne
    private Genero idGenero;
    @ManyToOne
    private Distrito idDistrito;
    @ManyToOne
    private Contacto idContacto;

    private String contactoEmergencia;

    public Viajante(ViajanteCriarDTO dto, Genero genero, Carga carga, DocumentoIdentifiacacao documentoIdentifiacacao, Provincia provincia, Distrito distrito, Contacto contacto) {
        this.apelido = dto.getApelido();
        this.dataNascimento = dto.getDataNascimento();
        this.nome = dto.getNome();
        this.email = dto.getEmail();
        this.idCarga = carga;
        this.idGenero = genero;
        this.idProvincia = provincia;
        this.idIdentificacao = documentoIdentifiacacao;
        this.idDistrito = distrito;
        this.idContacto = contacto;
    }

    public Viajante(ViagemAssociarViajanteDTO dto, Genero genero, Carga carga, DocumentoIdentifiacacao documentoIdentifiacacao, Provincia provincia, Distrito distrito, Contacto contacto) {
        this.apelido = dto.getApelido();
        this.dataNascimento = dto.getDataNascimento();
        this.nome = dto.getNome();
        this.email = dto.getEmail();
        this.idCarga = carga;
        this.idGenero = genero;
        this.idProvincia = provincia;
        this.idIdentificacao = documentoIdentifiacacao;
        this.idDistrito = distrito;
        this.idContacto = contacto;
        this.contactoEmergencia=dto.getContactoEmergencia();
    }
}
