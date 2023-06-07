package com.sgvcore.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Provincia extends AccoesDoSistema {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String designacao;
    @Column()
    private String codigo;
    @Column(nullable = false, unique = true)
    private String sigla;

    @ManyToOne
    private ZonaRegional idZonaRegional;
}
