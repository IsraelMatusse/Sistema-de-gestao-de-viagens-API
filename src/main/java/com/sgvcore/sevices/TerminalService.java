package com.sgvcore.sevices;

import com.sgvcore.DTOs.terminalDTOs.TerminalCriarDTO;
import com.sgvcore.DTOs.terminalDTOs.TerminalRespostaDTO;
import com.sgvcore.Model.*;
import com.sgvcore.enums.FuncoesUsuarios;
import com.sgvcore.exceptions.ContentAlreadyExists;
import com.sgvcore.exceptions.ModelNotFound;
import com.sgvcore.exceptions.NotOwner;
import com.sgvcore.repository.TerminalRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class TerminalService {

    private final TerminalRepo terminalRepo;
    private final UsuarioService usuarioService;
    private final ContactoService contactoService;
    private final ProvinciaService provinciaService;
    private final DistritoService distritoService;
    private final FuncaoUsuarioService funcaoUsuarioService;
    private final Logger logger = LoggerFactory.getLogger(TerminalService.class);

    public TerminalService(TerminalRepo terminalRepo, UsuarioService usuarioService, ContactoService contactoService, ProvinciaService provinciaService, DistritoService distritoService, FuncaoUsuarioService funcaoUsuarioService) {
        this.terminalRepo = terminalRepo;
        this.usuarioService = usuarioService;
        this.contactoService = contactoService;
        this.provinciaService = provinciaService;
        this.distritoService = distritoService;
        this.funcaoUsuarioService = funcaoUsuarioService;
    }

    public Terminal criar(Terminal terminal) {
        return terminalRepo.save(terminal);
    }

    public Terminal buscarPorId(Long id) throws ModelNotFound {
        return terminalRepo.findById(id).orElseThrow(() -> {
            logger.error("Erro,, terminal nao foi encontrado");
            return new ModelNotFound("Terminal nao foi econtrado");
        });
    }

    public Terminal criarTerminal(TerminalCriarDTO dto) throws Exception {

        boolean terminalExiste=existePorDesignacao(dto.getDesignacao());
        if(terminalExiste){
            logger.error("Erro, associcacao com essa designacao ja existe");
            throw new ContentAlreadyExists("associcacao com essa designacao ja existe");
        }
        Terminal novoTerminal;
        //verificar se usuario online e administrador
        Usuario usuario = usuarioService.buscarUsuarioOnline();
        List<FuncaoDoUsuario> funcaoDoUsuario = new ArrayList<>(usuario.getFuncoes());
        if (funcaoDoUsuario.get(0).getName().equalsIgnoreCase(FuncoesUsuarios.ROLE_ADMIN.name())) {
            //verificar se contacto ja existe
            Boolean contExiste = contactoService.existePorMsisdn(dto.getMsisdn());
            if (contExiste) {
                throw new ContentAlreadyExists("Contacto ja existe");
            }
            System.out.println(dto.getMsisdn());
            Contacto novoContacto = new Contacto(dto);
            Provincia provincia = provinciaService.buscarProvinciaporCodigo(dto.getCodigoProvincia());
            Distrito distrito = distritoService.buscarDistritoPorCodigoEProvincia(dto.getCodigoDistrito(), provincia);
            Usuario novoUsuario = new Usuario();
            try {
                contactoService.criar(novoContacto);
                 novoTerminal= new Terminal(dto, novoContacto, provincia, distrito);
                 criar(novoTerminal);


                novoUsuario.setUsername(dto.getEmail());
                novoUsuario.setPassword(dto.getMsisdn());
                Usuario terminalAuth = usuarioService.criarUsuario(novoUsuario);
                System.out.println(terminalAuth);
                    // Dar role de Terminal
                funcaoUsuarioService.adicionarFuncaoAUsuario(dto.getEmail(), "ROLE_TERMINAL");
                // Atualizar Terminal
                novoTerminal.setUsuario(terminalAuth);
                criar(novoTerminal);
            }catch (Exception e){
                throw new Exception(e.getMessage());
            }
        }else{
            throw new NotOwner("Nao possui acesso a esse recurso");
        }

        return novoTerminal;
    }

    public boolean existePorDesignacao(String designacao) {
        return terminalRepo.existsByDesignacao(designacao);
    }
    public TerminalRespostaDTO buscarPorCodigo(String codigo) throws ModelNotFound {
        Terminal terminal = terminalRepo.findByCodigo(codigo).orElseThrow(() -> {
            logger.error("Erro, terminal nao foi encontrado");
            return new ModelNotFound("Terminal nao foi econtrado");
        });
        return new TerminalRespostaDTO(terminal);
    }

    public List<TerminalRespostaDTO> listarTerminais(){
        return terminalRepo.findAll().stream().map(terminal -> new TerminalRespostaDTO(terminal)).collect(Collectors.toList());
    }

    public Terminal buscarTerminalPorUsuarioOnline(Usuario usuario) throws ModelNotFound {
        return terminalRepo.findByUsuario(usuario).orElseThrow(()->{
            logger.error("Erro, terminal nao foi encontrado");
            return new ModelNotFound("Terminal nao foi econtrado");
        });
    }

    public TerminalRespostaDTO buscarTerminalPorUsuarioOnlineRes(Usuario usuario) throws ModelNotFound {
        Terminal terminal= terminalRepo.findByUsuario(usuario).orElseThrow(()-> new ModelNotFound("terminal nao encontrado"));
        return new TerminalRespostaDTO(terminal);
    }

}
