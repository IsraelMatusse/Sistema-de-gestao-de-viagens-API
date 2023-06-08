package com.sgvcore.Controllers;

import com.sgvcore.DTOs.viajanteDTO.ViajanteCriarDTO;
import com.sgvcore.Model.ResponseAPI;
import com.sgvcore.sevices.ViajanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/viajantes")
public class ViajanteController {

    @Autowired
    private ViajanteService viajanteService;

    public ResponseEntity<ResponseAPI> criarViajante(ViajanteCriarDTO dto){

    }
}
