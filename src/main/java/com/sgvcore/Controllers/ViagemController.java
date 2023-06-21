package com.sgvcore.Controllers;

import com.sgvcore.DTOs.viagemDTO.ViagemCriarDTO;
import com.sgvcore.Model.Associacao;
import com.sgvcore.Model.ResponseAPI;
import com.sgvcore.Model.Rota;
import com.sgvcore.Model.Viagem;
import com.sgvcore.sevices.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/api/viagens")
public class ViagemController {

    @Autowired
    private ViagemService viagemService;
    @Autowired
     private ViajanteService viajanteService;
    @Autowired
     private RotaService rotaService;
    @Autowired
     private ViagemViajanteService viagemViajanteService;
    @Autowired
    private AssociacaoService associacaoService;
    @PostMapping("/adicionar")
    public ResponseEntity<ResponseAPI> criarViagem(@RequestBody @Valid ViagemCriarDTO dto) throws NoSuchAlgorithmException {
        Rota rota=rotaService.buscarRotaPorId(dto.getIdRota()).orElse(null);
        if(rota==null) {
            return ResponseEntity.status(404).body(new ResponseAPI(false, "404", "Rota nao encontrada!", null));
        }
        Associacao associacao = associacaoService.buscarPorCodigo(dto.getCodigoAssociacao());
        if(associacao==null){
            return ResponseEntity.status(404).body(new ResponseAPI(false, "404", "Associacao nao enocntrada!", null));
        }
        try{
        viagemService.criar(new Viagem(dto, rota, associacao));

        }catch (Exception e){
            return ResponseEntity.status(500).body(new ResponseAPI(false, "500", "Erro interno de servidor!", null));
        }
        return ResponseEntity.status(200).body(new ResponseAPI(true, "200", "Viajante criado com sucesso!", null));
    }


}
