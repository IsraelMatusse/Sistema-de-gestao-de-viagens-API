package com.sgvcore.Model;

import com.sgvcore.DTOs.viaturaDTOs.ViacturaAssociarMotoristaDTO;
import com.sgvcore.DTOs.viaturaDTOs.ViaturaCriarDTO;
import com.sgvcore.utils.GeneratePin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.security.NoSuchAlgorithmException;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Viatura extends AccoesDoSistema {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false)
    private String matricula;
    @Column(nullable=false)
    private String cor;
    @Column(nullable=false)
    private String marca;
    @Column(nullable=false)
    private String modelo;
    @Column(nullable=false)
    private Long lotacao;
    @Column(nullable=false)
    private Long pesobruto;
    @Column(nullable=false)
    private String tipo;
    @Column(nullable=false)
    private Long anofabrico;
    @Column(nullable=false)
    private Long quilometragem;
    @Column(nullable=false)
    private String combustivel;
    @Column(nullable=false)
    private String nrmotor;
    @Column(nullable=false)
    private Long nrportas;
    private String codigo;

    @ManyToOne
    private Rota idRota;
    @ManyToOne
    private Associacao idAssociacao;
    @ManyToOne
    private Proprietario idProprietario;

    public Viatura(ViaturaCriarDTO viaturaCriarDTO, Rota rota, Proprietario proprietario, Associacao associacao) throws NoSuchAlgorithmException {
        this.anofabrico=viaturaCriarDTO.getAnofabrico();
        this.cor=viaturaCriarDTO.getCor();
        this.idProprietario=proprietario;
        this.combustivel=viaturaCriarDTO.getCombustivel();
        this.lotacao=viaturaCriarDTO.getLotacao();
        this.marca=viaturaCriarDTO.getMarca();
        this.matricula=viaturaCriarDTO.getMatricula();
        this.modelo=viaturaCriarDTO.getModelo();
        this.idRota=rota;
        this.nrportas=viaturaCriarDTO.getNrportas();
        this.pesobruto=viaturaCriarDTO.getPesobruto();
        this.nrmotor=viaturaCriarDTO.getNrmotor();
        this.quilometragem=viaturaCriarDTO.getQuilometragem();
        this.tipo=viaturaCriarDTO.getTipo();
        this.codigo= GeneratePin.generateStringPin();
        this.idAssociacao=associacao;
    }
    public Viatura(ViacturaAssociarMotoristaDTO dto, Rota rota, Proprietario proprietario, Associacao associacao) throws NoSuchAlgorithmException {
        this.codigo=GeneratePin.generateStringPin();
        this.tipo= dto.getTipo();
        this.nrmotor= dto.getNrmotor();
        this.pesobruto=dto.getPesobruto();
        this.idAssociacao=associacao;
        this.quilometragem=dto.getQuilometragem();
        this.idRota=rota;
        this.modelo=dto.getModelo();
        this.matricula=dto.getMatricula();
        this.combustivel=dto.getCombustivel();
        this.anofabrico=dto.getAnofabrico();
        this.idProprietario=proprietario;
        this.cor=dto.getCor();
        this.marca=dto.getMarca();
        this.lotacao=dto.getLotacao();
        this.nrportas=dto.getNrPortas();
    }
}
