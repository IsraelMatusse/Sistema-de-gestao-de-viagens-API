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
import com.sgvcore.sevices.FuncaoUsuarioService;
import com.sgvcore.sevices.UsuarioService;
import com.sgvcore.sevices.ViaturaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/associacoes")
@RequiredArgsConstructor
public class AssociacaoController {

    private final AssociacaoService associacaoService;
    private final ViaturaService viaturaService;
    private final UsuarioService usuarioService;
    private final FuncaoUsuarioService funcaoUsuarioService;

    //cadastrar uma associacoa e as suas rotas
    @PostMapping("/adicionar")
    public ResponseEntity<ResponseAPI> crarAssociacoes(@Valid @RequestBody AssociacaoCriarDTO dto) throws ContentAlreadyExists, ModelNotFound, NotOwner, NoSuchAlgorithmException {
        Associacao associacao = associacaoService.criarAssociacao(dto);

        Usuario usuario = new Usuario();
        usuario.setUsername(dto.getEmailassociacao());
        usuario.setPassword(dto.getMsisdn());
        Usuario associacaoAuth = usuarioService.criarUsuario(usuario);
        // Dar role de docente
        funcaoUsuarioService.adicionarFuncaoAUsuario(dto.getEmailassociacao(), "ROLE_ASSOCIACAO");
        // Atualizar docente
        associacao.setUsuario(associacaoAuth);
        associacaoService.criar(associacao);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseAPI(true, "201", "Associacao cadastrada com sucesso", null));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_TERMINAL')")
    @GetMapping
    public ResponseEntity<ResponseAPI> listarAssociacoes() {
        return ResponseEntity.status(200).body(new ResponseAPI(false, "200", "Associacoes do sistema", associacaoService.listarAssociacao()));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_TERMINAL')")
    @GetMapping("/pag")
    public ResponseEntity<ResponseAPI> listarAssociacoesPorPaginacao(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "designacao") String filter,
            @RequestParam(defaultValue = "desc") String order
    ) {
        Sort.Direction direction = order.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sort = Sort.by(direction, filter);
        Page<Associacao> paginaDeAssociacoes = associacaoService.listarAssociacoesPorPaginacao(page, size, sort);
        ResponseAPI response = new ResponseAPI(true, "200", "Rotas do sistema", paginaDeAssociacoes);

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", String.valueOf(paginaDeAssociacoes.getTotalElements()));
        headers.add("X-Total-Pages", String.valueOf(paginaDeAssociacoes.getTotalPages()));
        // Retornar o conteúdo da página e os cabeçalhos no ResponseEntity
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(response);
    }

    @GetMapping("/viaturas")
    public ResponseEntity<ResponseAPI> listarViaturasAssociacao(@RequestParam("codigoAssociacao") String codigoAssociacao) throws ModelNotFound {
        Usuario usuario = usuarioService.buscarUsuarioOnline();
        List<FuncaoDoUsuario> funcaoDoUsuario = new ArrayList<>(usuario.getFuncoes());
        if (funcaoDoUsuario.get(0).getName().equalsIgnoreCase(FuncoesUsuarios.ROLE_ASSOCIACAO.name())) {
            Associacao associacao = associacaoService.buscarAssociacaoPorUsuarioOnline(usuario);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseAPI(true, "200", "Viaturas da associacao" + associacao.getDesignacao() + " ", viaturaService.buscarViaturasDaDaAssociacao(associacao)));
        } else if (funcaoDoUsuario.get(0).getName().equalsIgnoreCase(FuncoesUsuarios.ROLE_ADMIN.name())) {
            if (codigoAssociacao == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseAPI(false, "400", "Parametro codigo de associacao nao presente", null));
            }
            Associacao associacao = associacaoService.buscarPorCodigo(codigoAssociacao);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseAPI(true, "200", "Viaturas da associacao" + associacao.getDesignacao() + " ", viaturaService.buscarViaturasDaDaAssociacao(associacao)));
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ResponseAPI(false, "403", "Nao possui acesso a esse recurso", null));
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
