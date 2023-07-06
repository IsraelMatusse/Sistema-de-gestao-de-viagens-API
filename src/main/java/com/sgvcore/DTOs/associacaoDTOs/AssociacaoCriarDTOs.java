package com.sgvcore.DTOs.associacaoDTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sgvcore.Model.Contacto;
import com.sgvcore.Model.Licenca;
import com.sgvcore.Model.Rota;
import com.sgvcore.Model.TipopLicenca;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.util.Strings;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssociacaoCriarDTOs {


    private String designacao;
    private String emailassociacao;
    private String msdisn;
    private String numeroLicenca;
    private Date dataValidade;
    private Long tipoLicenca;
    private List<String> rotas= new ArrayList<>();


}