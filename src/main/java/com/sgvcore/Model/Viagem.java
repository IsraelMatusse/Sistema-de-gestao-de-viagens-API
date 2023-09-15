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
public class Viagem extends AccoesDoSistema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String destinoViagem;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(nullable = false)
    private Date saida;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(nullable = false)
    private Date prevChegada;
    @Column(nullable = false)
    private String codigo;

    @ManyToOne
    private Rota idRota;
    @ManyToOne
    private Associacao idAssociacao;
    @ManyToOne
    private Viatura idViactura;
    @ManyToOne
    private Motorista idMotorista;

    public Viagem(ViagemCriarDTO dto, Rota rota, Associacao associacao, Viatura viactura, Motorista motorista) throws NoSuchAlgorithmException {
        this.codigo = GeneratePin.generateStringPin();
        this.destinoViagem = dto.getDestinoViagem();
        this.idRota = rota;
        this.idAssociacao = associacao;
        this.saida = dto.getSaida();
        this.prevChegada = dto.getPrevChegada();
        this.idViactura = viactura;
        this.idMotorista = motorista;
    }


}
