package com.sgvcore.Controllers;

import com.sgvcore.DTOs.viaturaDTOs.ViaturaCriarDTO;
import com.sgvcore.Model.*;
import com.sgvcore.sevices.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;

@RequestMapping("/api/viaturas")
@RestController
public class ViaturaController {

    @Autowired
    private ViaturaService viaturaService;
    @Autowired
    private RotaService rotaService;
    @Autowired
    private ProprietarioService proprietarioService;
    @Autowired
    private AssociacaoService associacaoService;
    @Autowired
    private AsociacaoRotaService asociacaoRotaService;

    @PostMapping("/adicionar")
    public ResponseEntity<ResponseAPI> criarViacturas(@RequestBody @Valid ViaturaCriarDTO viaturaCriarDTO) throws NoSuchAlgorithmException {
        Proprietario proprietario=proprietarioService.buscarPorCodigo(viaturaCriarDTO.getCodigoProprietario());
        if(proprietario==null){
            return ResponseEntity.status(404).body(new ResponseAPI(false, "404", "Proprietario nao encontrado!", null));
        }
        Associacao associacao=associacaoService.buscarPorCodigo(viaturaCriarDTO.getCodigoAssociacao());
        if(associacao==null){
            return ResponseEntity.status(404).body(new ResponseAPI(false, "404", "Associacao nao encontrada!", null));
        }
        Rota rota= asociacaoRotaService.buscarRotasPeloCodigoDaAssociacaoEAssociacao(associacao, viaturaCriarDTO.getCodigoRota());
        if(rota==null){
            return ResponseEntity.status(404).body(new ResponseAPI(false, "404", "Rota nao encontrada!", null));
        }
        try {
            Viactura viatura = new Viactura(viaturaCriarDTO, rota, proprietario, associacao);
            viaturaService.criar(viatura);
        }catch (Exception e){
            return ResponseEntity.status(500).body(new ResponseAPI(false, "500", "Erro interno de servidor!", null));
        }
        return ResponseEntity.status(201).body(new ResponseAPI(true, "201", "Viatura criada com sucesso!", null));
    }

    public ResponseEntity<ResponseAPI> listar(){
        return ResponseEntity.status(200).body(new ResponseAPI(true, "200", "Viaturas do sistema!", viaturaService.listarViacturas()));

    }

}
