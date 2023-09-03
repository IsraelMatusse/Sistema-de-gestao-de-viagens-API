package com.sgvcore.Controllers;

import com.sgvcore.DTOs.rotaDTO.RotaCriarDTO;
import com.sgvcore.Model.*;
import com.sgvcore.enums.FuncoesUsuarios;
import com.sgvcore.exceptions.ContentAlreadyExists;
import com.sgvcore.exceptions.ModelNotFound;
import com.sgvcore.exceptions.NotOwner;
import com.sgvcore.sevices.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
        rotaService.criarRota(dto);
        return ResponseEntity.status(201).body(new ResponseAPI(true, "201", "Rota criada com sucesso!", null));
    }

    @GetMapping
    public ResponseEntity<ResponseAPI> listarRotas() {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseAPI(true, "200", "Rotas do sistema!", rotaService.listarRotas()));
    }

    //Rotas Paginadas
    @GetMapping("/pag")
    public ResponseEntity<ResponseAPI> listarRotasPaginadas(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "nomeRota") String filter,
            @RequestParam(defaultValue = "desc") String order
    ) {
        // Definir o objeto de ordenação com base nos parâmetros de filtro
        Sort.Direction direction = order.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sort = Sort.by(direction, filter);
        // Realizar a consulta ao serviço para obter a página de rotas
        Page<Rota> paginaDeRotas = rotaService.listarRotasPaginadas(page, size, sort);
        // Construir o objeto de resposta com os dados da página de rotas
        ResponseAPI response = new ResponseAPI(true, "200", "Rotas do sistema!", paginaDeRotas.getContent());

        // Adicionar informações de paginação ao cabeçalho da resposta
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", String.valueOf(paginaDeRotas.getTotalElements()));
        headers.add("X-Total-Pages", String.valueOf(paginaDeRotas.getTotalPages()));
        // Retornar o conteúdo da página e os cabeçalhos no ResponseEntity
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(response);
    }

    @GetMapping("/associacao/{codigo-associacao}")
    public ResponseEntity<ResponseAPI> listarRotasDaAssociacao(@PathVariable(value = "codigo-associacao", required = false) String codigoAssociacao) throws ModelNotFound {
        Usuario usuario = usuarioService.buscarUsuarioOnline();
        List<FuncaoDoUsuario> funcaoDoUsuario = new ArrayList<>(usuario.getFuncoes());
        if (funcaoDoUsuario.get(0).getName().equalsIgnoreCase(FuncoesUsuarios.ROLE_ASSOCIACAO.name())) {
            Associacao associacao = associacaoService.buscarAssociacaoPorUsuarioOnline(usuario);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseAPI(true, "200", "Rotas da associacao " + associacao.getDesignacao() + " !", associacaoRotaService.buscarPorAssociacao(associacao)));
        } else if (funcaoDoUsuario.get(0).getName().equalsIgnoreCase(FuncoesUsuarios.ROLE_ADMIN.name()) || funcaoDoUsuario.get(0).getName().equalsIgnoreCase(FuncoesUsuarios.ROLE_TERMINAL.name())) {
            if (codigoAssociacao == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseAPI(false, "400", "Codigo da associacao nao encontrada na requesicao!", null));
            }
            Associacao associacao = associacaoService.buscarPorCodigo(codigoAssociacao);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseAPI(true, "200", "Rotas da associacao " + associacao.getDesignacao() + " !", associacaoRotaService.buscarPorAssociacao(associacao)));
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ResponseAPI(false, "403", "Nao possui acesso a esse recurso.", null));
    }

    @GetMapping("/{codigo_rota}/viaturas")
    public ResponseEntity<ResponseAPI> listarRotasDeUmaRota(@PathVariable(value = "codigo_rota") String codigoRota) throws ModelNotFound {
        Rota rota = rotaService.buscarPorCodigo(codigoRota);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseAPI(true, "200", "Vituras da rota " + rota.getNomeRota() + " !", viaturaService.buscarViaturasDeUmaRota(rota)));
    }

    @GetMapping("/numero_rotas")
    public ResponseEntity<ResponseAPI> numeroRotas() throws NotOwner {
        return ResponseEntity.status(200).body(new ResponseAPI(false, "200", "Numero de rotas do sistema", rotaService.numeroRotas()));
    }

}
