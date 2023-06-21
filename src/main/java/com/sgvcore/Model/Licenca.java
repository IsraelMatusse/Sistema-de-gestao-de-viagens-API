package com.sgvcore.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Licenca extends AccoesDoSistema{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String numeroLicenca;
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column
    private Date dataValidade;

    @ManyToOne
    private TipopLicenca idLicenca;
}
