package com.sgvcore.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AssociacaoRota extends AccoesDoSistema {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Rota idRota;
    @ManyToOne
    private Associacao idAssociacao;

    public AssociacaoRota(Rota rota, Associacao associacao){
        this.idAssociacao=associacao;
        this.idRota=rota;
    }

}
