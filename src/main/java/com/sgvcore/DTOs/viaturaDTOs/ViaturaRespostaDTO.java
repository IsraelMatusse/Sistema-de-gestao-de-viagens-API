package com.sgvcore.DTOs.viaturaDTOs;

import com.sgvcore.Model.Viactura;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViaturaRespostaDTO {

    private String matricula;
    private String cor;
    private String marca;
    private String modelo;
    private Long lotacao;
    private Long pesobruto;
    private String tipo;
    private Long anofabrico;
    private Long quilometragem;
    private String combustivel;
    private String nrmotor;
    private Long nrportas;
    private String designacaoRota;
    private String designacaoAssociacao;
    private String nomeProprietario;

    public ViaturaRespostaDTO(Viactura viactura){
        this.anofabrico=viactura.getAnofabrico();
        this.designacaoAssociacao=viactura.getIdAssociacao().getDesignacao();
        this.cor=viactura.getCor();
        this.designacaoRota=viactura.getIdRota().getNomerota();
        this.lotacao=viactura.getLotacao();
        this.marca=viactura.getMarca();
        this.modelo=viactura.getModelo();
        this.nrmotor=viactura.getNrmotor();
        this.nrportas=viactura.getNrportas();
        this.pesobruto=viactura.getPesobruto();
        this.tipo=viactura.getTipo();
        this.quilometragem=viactura.getQuilometragem();
        this.combustivel=viactura.getCombustivel();
        this.matricula=viactura.getMatricula();
        this.nomeProprietario=viactura.getIdProprietario().getNome();
    }
}
