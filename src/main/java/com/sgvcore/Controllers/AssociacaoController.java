package com.sgvcore.Controllers;

import com.sgvcore.DTOs.associacaoDTOs.AssociacaoCriarDTO;
import com.sgvcore.Model.Associacao;
import com.sgvcore.Model.FuncaoDoUsuario;
import com.sgvcore.Model.ResponseAPI;
import com.sgvcore.Model.Usuario;
import com.sgvcore.enums.FuncoesUsuarios;
import com.sgvcore.exceptions.BadRequest;
import com.sgvcore.exceptions.ContentAlreadyExists;
import com.sgvcore.exceptions.ModelNotFound;
import com.sgvcore.exceptions.NotOwner;
import com.sgvcore.sevices.AssociacaoService;
import com.sgvcore.sevices.UsuarioService;
import com.sgvcore.sevices.ViaturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/associacoes")
public class AssociacaoController {
    @Autowired
    private AssociacaoService associacaoService;
    @Autowired
    private ViaturaService viaturaService;
    @Autowired
    private UsuarioService usuarioService;

    //cadastrar uma associacoa e as suas rotas
    @PostMapping("/adicionar")
    public ResponseEntity<ResponseAPI> crarAssociacoes(@RequestBody AssociacaoCriarDTO dto) throws ContentAlreadyExists, ModelNotFound, NotOwner, NoSuchAlgorithmException {
        associacaoService.criar(dto);
        return ResponseEntity.status(201).body(new ResponseAPI(true, "201", "Associacao cadastrada com sucesso", null));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_TERMINAL')")
    @GetMapping
    public ResponseEntity<ResponseAPI> listarAssociacoes() {
        return ResponseEntity.status(200).body(new ResponseAPI(false, "200", "Associacoes do sistema", associacaoService.listarAssociacao()));
    }

    @GetMapping("/viaturas")
    public ResponseEntity<ResponseAPI> listarViaturasAssociacao(@RequestParam("codigoAssociacao") String codigoAssociacao) throws ModelNotFound {
        Usuario usuario = usuarioService.buscarUsuarioOnline();
        List<FuncaoDoUsuario> funcaoDoUsuario = new ArrayList<>(usuario.getFuncoes());
        if (funcaoDoUsuario.get(0).getName().equalsIgnoreCase(FuncoesUsuarios.ROLE_ASSOCIACAO.name())) {
            Associacao associacao = associacaoService.buscarAssociacaoPorUsuarioOnline(usuario);
            return ResponseEntity.status(200).body(new ResponseAPI(true, "200", "Viaturas da associacao" + associacao.getDesignacao() + " ", viaturaService.buscarViaturasDaDaAssociacao(associacao)));
        } else if (funcaoDoUsuario.get(0).getName().equalsIgnoreCase(FuncoesUsuarios.ROLE_ADMIN.name())) {
            if (codigoAssociacao == null) {
                return ResponseEntity.status(400).body(new ResponseAPI(false, "400", "Parametro codigo de associacao nao presente", null));
            }
            Associacao associacao = associacaoService.buscarPorCodigo(codigoAssociacao);
            return ResponseEntity.status(200).body(new ResponseAPI(true, "200", "Viaturas da associacao" + associacao.getDesignacao() + " ", viaturaService.buscarViaturasDaDaAssociacao(associacao)));
        }
        return ResponseEntity.status(403).body(new ResponseAPI(false, "403", "Nao possui acesso a esse recurso", null));
    }

    @GetMapping("/perfil")
    public ResponseEntity<ResponseAPI> buscarPorCodigo(@RequestParam(value = "codigoAssociacao", required = false) String codigoAssociacao) throws ModelNotFound, NotOwner, BadRequest {
        Usuario usuario = usuarioService.buscarUsuarioOnline();
        return ResponseEntity.status(200).body(new ResponseAPI(false, "200", "Associacoes do sistema", associacaoService.buscarAssociacaoPorUsuarioOnlineRes(usuario, codigoAssociacao)));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_TERMINAL')")
    @GetMapping("numero_associacoes")
    public ResponseEntity<ResponseAPI> nuneroDeAssociacoes() {
        return ResponseEntity.status(200).body(new ResponseAPI(false, "200", "Numero de Associacoes do sistema", associacaoService.numeroDeAssociacoes()));
    }
}
