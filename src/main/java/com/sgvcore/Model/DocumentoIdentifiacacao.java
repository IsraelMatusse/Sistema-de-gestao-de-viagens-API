package com.sgvcore.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentoIdentifiacacao extends AccoesDoSistema {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String numeroDocumento;
    @Column
    private Date dataValidade;
    @ManyToOne
    private TipoDocumentoIdentificacao idIdentificacao;


}
