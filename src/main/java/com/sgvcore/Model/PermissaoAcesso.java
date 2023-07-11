package com.sgvcore.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
public class PermissaoAcesso extends AccoesDoSistema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private FuncaoDoUsuario funcaoDoUsuario;
    @ManyToOne
    private IdentificadorDeMenu identificadorDeMenu;
    @ManyToOne
    private NivelAcesso nivelAcesso;
}