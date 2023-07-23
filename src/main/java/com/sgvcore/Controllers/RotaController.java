package com.sgvcore.Controllers;

import com.sgvcore.DTOs.rotaDTO.RotaCriarDTO;
import com.sgvcore.Model.*;
import com.sgvcore.enums.FuncoesUsuarios;
import com.sgvcore.exceptions.ContentAlreadyExists;
import com.sgvcore.exceptions.ModelNotFound;
import com.sgvcore.exceptions.NotOwner;
import com.sgvcore.sevices.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/rotas")
public class RotaController {

    @Autowired
    private RotaService rotaService;
    @Autowired
    private AssociacaoService associacaoService;
    @Autowired
    private ViaturaService viaturaService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private FuncaoUsuarioService funcaoUsuarioService;
    @Autowired
    private AssociacaoRotaService associacaoRotaService;

    @PostMapping("/adicionar")
    public ResponseEntity<ResponseAPI> criarRotas(@RequestBody @Valid RotaCriarDTO dto) throws NoSuchAlgorithmException, NotOwner, ContentAlreadyExists {
        rotaService.criar(dto);
        return ResponseEntity.status(201).body(new ResponseAPI(true, "201", "Rota criada com sucesso!", null));
    }

    @GetMapping
    public ResponseEntity<ResponseAPI> listarRotas() {
        return ResponseEntity.status(200).body(new ResponseAPI(true, "200", "Rotas do sistema!", rotaService.listarRotas()));
    }

    @GetMapping("/associacao/{codigo_associacao}")
    public ResponseEntity<ResponseAPI> listarRotasDaAssociacao(@PathVariable(value = "codigo_associacao") String codigoAssociacao) throws ModelNotFound {
        Usuario usuario = usuarioService.buscarUsuarioOnline();
        List<FuncaoDoUsuario> funcaoDoUsuario = new ArrayList<>(usuario.getFuncoes());
        if (funcaoDoUsuario.get(0).getName().equalsIgnoreCase(FuncoesUsuarios.ROLE_ASSOCIACAO.name())) {
            Associacao associacao = associacaoService.buscarAssociacaoPorUsuarioOnline(usuario);
            return ResponseEntity.status(200).body(new ResponseAPI(true, "200", "Rotas da associacao " + associacao.getDesignacao() + " !", associacaoRotaService.buscarPorAssociacao(associacao)));
        } else if (funcaoDoUsuario.get(0).getName().equalsIgnoreCase(FuncoesUsuarios.ROLE_ADMIN.name()) || funcaoDoUsuario.get(0).getName().equalsIgnoreCase(FuncoesUsuarios.ROLE_TERMINAL.name())) {
            if (codigoAssociacao == null) {
                return ResponseEntity.status(400).body(new ResponseAPI(false, "400", "Codigo da associacao nao encontrada na requesicao!", null));
            }
            Associacao associacao = associacaoService.buscarPorCodigo(codigoAssociacao);
            return ResponseEntity.status(200).body(new ResponseAPI(true, "200", "Rotas da associacao " + associacao.getDesignacao() + " !", associacaoRotaService.buscarPorAssociacao(associacao)));
        }
        return ResponseEntity.status(403).body(new ResponseAPI(false, "403", "Nao possui acesso a esse recurso.", null));
    }

    @GetMapping("/{codigo_rota}/viaturas")
    public ResponseEntity<ResponseAPI> listarRotasDeUmaRota(@PathVariable(value = "codigo_rota") String codigoRota) throws ModelNotFound {
        Rota rota = rotaService.buscarPorCodigo(codigoRota);
        return ResponseEntity.status(200).body(new ResponseAPI(true, "200", "Vituras da rota " + rota.getNomerota() + " !", viaturaService.buscarViaturasDeUmaRota(rota)));
    }

    @GetMapping("/numero_rotas")
    public ResponseEntity<ResponseAPI> numeroRotas() throws NotOwner {
        return ResponseEntity.status(200).body(new ResponseAPI(false, "200", "Numero de rotas do sistema", rotaService.numeroRotas()));
    }

}
