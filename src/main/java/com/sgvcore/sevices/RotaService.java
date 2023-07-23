package com.sgvcore.sevices;

import com.sgvcore.DTOs.rotaDTO.RotaCriarDTO;
import com.sgvcore.DTOs.rotaDTO.RotaRespostaDTO;
import com.sgvcore.Model.FuncaoDoUsuario;
import com.sgvcore.Model.Rota;
import com.sgvcore.Model.Usuario;
import com.sgvcore.enums.FuncoesUsuarios;
import com.sgvcore.exceptions.ContentAlreadyExists;
import com.sgvcore.exceptions.ModelNotFound;
import com.sgvcore.exceptions.NotOwner;
import com.sgvcore.repository.RotaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RotaService {

    @Autowired
    UsuarioService usuarioService;
    @Autowired
    private RotaRepo rotaRepo;

    public Rota criar(RotaCriarDTO dto) throws ContentAlreadyExists, NotOwner {
        // verificar privilegios de criacao de rota
        Usuario usuario = usuarioService.buscarUsuarioOnline();
        List<FuncaoDoUsuario> funcaoDoUsuario = new ArrayList<>(usuario.getFuncoes());
        if (funcaoDoUsuario.get(0).getName().equalsIgnoreCase(FuncoesUsuarios.ROLE_ADMIN.name()) || funcaoDoUsuario.get(0).getName().equalsIgnoreCase(FuncoesUsuarios.ROLE_TERMINAL.name())) {
            //verificar se rota ja existe no sistema
            Boolean rotaExiste = rotaRepo.existsByNomerota(dto.getNomeRota());
            if (rotaExiste) {
                throw new ContentAlreadyExists("Rota ja existe no sistema");
            }
            Rota novaRota = null;
            try {
                novaRota = new Rota(dto);
                rotaRepo.save(novaRota);
            } catch (Exception e) {
                throw new RuntimeException("Erro ao salvar a rota");
            }
        }
        throw new NotOwner("Nao possui acesso a este recurso");
    }

    public List<RotaRespostaDTO> listarRotas() {
        return rotaRepo.findAll().stream().map(rota -> new RotaRespostaDTO(rota)).collect(Collectors.toList());
    }

    public Rota buscarRotaPorId(Long id) throws ModelNotFound {
        return rotaRepo.findById(id).orElseThrow(() -> new ModelNotFound("Rota nao encontrada"));
    }

    public Rota buscarPorCodigo(String codigoRota) throws ModelNotFound {
        return rotaRepo.findByCodigo(codigoRota).orElseThrow(() -> new ModelNotFound("Rota nao encontrada!"));
    }

    public Boolean verificarAexistenciaDaRotaPorDesignacao(String rota) {
        return rotaRepo.existsByNomerota(rota);
    }

    public Rota buscarRotaPorDesignacao(String designacao) throws ModelNotFound {
        return rotaRepo.findByNomerota(designacao).orElseThrow(() -> new ModelNotFound("Rota nao encontrada!"));
    }

    public Long numeroRotas() throws NotOwner {
        // verificar privilegios para ver estatisticas
        Usuario usuario = usuarioService.buscarUsuarioOnline();
        List<FuncaoDoUsuario> funcaoDoUsuario = new ArrayList<>(usuario.getFuncoes());
        if (funcaoDoUsuario.get(0).getName().equalsIgnoreCase(FuncoesUsuarios.ROLE_ADMIN.name()) || funcaoDoUsuario.get(0).getName().equalsIgnoreCase(FuncoesUsuarios.ROLE_TERMINAL.name())) {
            return rotaRepo.count();
        }
        throw new NotOwner("Nao possui acesso a esse recurso");
    }
}
