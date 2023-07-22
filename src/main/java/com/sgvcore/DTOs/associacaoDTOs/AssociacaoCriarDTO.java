package com.sgvcore.DTOs.associacaoDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssociacaoCriarDTO {


    private String designacao;
    private String emailassociacao;
    private String msisdn;
    private String numeroLicenca;
    private Date dataValidade;
    private Long tipoLicenca;
    private List<String> rotas = new ArrayList<>();


}
