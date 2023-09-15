package com.sgvcore.Controllers;

import com.sgvcore.Model.ResponseAPI;
import com.sgvcore.sevices.TipoLicencaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tipos-licenca")
@RequiredArgsConstructor
public class TipoLicencaController {

    private final TipoLicencaService tipoLicencaService;

    @GetMapping
    public ResponseEntity<ResponseAPI> listarTiposDeLicenca(){
        return ResponseEntity.ok(new ResponseAPI(true, "200", "Tipos de licenca", tipoLicencaService.listarTiposLicencaResposta()));
    }

}
