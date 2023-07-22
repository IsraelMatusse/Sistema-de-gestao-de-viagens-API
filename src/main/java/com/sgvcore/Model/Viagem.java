package com.sgvcore.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sgvcore.DTOs.viagemDTO.ViagemCriarDTO;
import com.sgvcore.utils.GeneratePin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Viagem {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @Column(nullable=false)
    private String destino_viagem;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(nullable=false)
    private Date saida;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(nullable=false)
    private Date prevChegada;
    @Column(nullable=false)
    private String codigo;

    @ManyToOne
    private  Rota idRota;
    @ManyToOne
    private Associacao idAssociacao;
    @ManyToOne
    private Viactura idViactura;
    @ManyToOne
    private Motorista idMotorista;

    public Viagem(ViagemCriarDTO dto, Rota rota, Associacao associacao, Viactura viactura, Motorista motorista) throws NoSuchAlgorithmException {
        this.codigo= GeneratePin.generateStringPin();
        this.destino_viagem=dto.getDestinoViagem();
        this.idRota=rota;
        this.idAssociacao=associacao;
        this.saida=dto.getSaida();
        this.prevChegada=dto.getPrevChegada();
        this.idViactura=viactura;
        this.idMotorista=motorista;
    }

}
