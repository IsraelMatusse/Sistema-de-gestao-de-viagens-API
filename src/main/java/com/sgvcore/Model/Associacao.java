package com.sgvcore.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Associacao {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @Column(nullable=false)
    private String designacao;
    @Column(nullable = false)
    private String emailassociacao;
    @ManyToOne
    private Contacto idContacto;
    @ManyToOne
    private Licenca idLicenca;
    private String codigo;

}
