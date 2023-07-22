package com.sgvcore.Controllers;

import com.sgvcore.DTOs.rotaDTO.RotaCriarDTO;
import com.sgvcore.Model.Associacao;
import com.sgvcore.Model.ResponseAPI;
import com.sgvcore.Model.Rota;
import com.sgvcore.sevices.AsociacaoRotaService;
import com.sgvcore.sevices.AssociacaoService;
import com.sgvcore.sevices.RotaService;
import com.sgvcore.sevices.ViaturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.ws.Response;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/api/rotas")
public class RotaController {

    @Autowired
    private RotaService rotaService;
    @Autowired
    private AssociacaoService associacaoService;
    @Autowired
    private AsociacaoRotaService asociacaoRotaService;
    @Autowired
    private ViaturaService viaturaService;

    @PostMapping("/adicionar")
    public ResponseEntity<ResponseAPI> criarRotas(@RequestBody @Valid RotaCriarDTO dto) throws NoSuchAlgorithmException {
        try {
            Rota rota = rotaService.criar(new Rota(dto));
        }catch (Exception e){
            return ResponseEntity.status(500).body(new ResponseAPI(false, "500", "Erro interno de servidor!", null));
        }
        return ResponseEntity.status(201).body(new ResponseAPI(true, "201", "Rota criada com sucesso!", null));
    }
    @GetMapping
    public ResponseEntity<ResponseAPI> listarRotas(){
        return ResponseEntity.status(200).body(new ResponseAPI(true, "200", "Rotas do sistema!", rotaService.listarRotas()));
    }
    @GetMapping("/{codigo_associacao}")
    public ResponseEntity<ResponseAPI> listarRotasDaAssociacao(@PathVariable(value = "codigo_associacao") String codigoAssociacao){
        Associacao associacao= associacaoService.buscarPorCodigo(codigoAssociacao);
        if(associacao==null){
            return ResponseEntity.status(404).body(new ResponseAPI(false, "404", "Associacao nao encontrada!", null));
        }
        return ResponseEntity.status(200).body(new ResponseAPI(true, "200", "Rotas da associacao "+associacao.getDesignacao()+" !", asociacaoRotaService.buscarPorAssociacao(associacao)));

    }
    @GetMapping("/{codigo_rota}/viaturas")
    public ResponseEntity<ResponseAPI>listarRotasDeUmaRota(@PathVariable(value = "codigo_rota") String codigoRota){
        Rota rota=rotaService.buscarPorCodigo(codigoRota);
        if(rota==null){
            return ResponseEntity.status(404).body(new ResponseAPI(false, "404", "Rota nao encontrada!", null));
        }
        return ResponseEntity.status(200).body(new ResponseAPI(true, "200", "Vituras da rota "+rota.getNomerota()+" !", viaturaService.buscarViaturasDeUmaRota(rota)));
    }
    @GetMapping("/numero_rotas")
    public ResponseEntity<ResponseAPI> numeroRotas(){
        return ResponseEntity.status(200).body(new ResponseAPI(false, "200", "Numero de rotas do sistema", rotaService.numeroRotas()));
    }

}
