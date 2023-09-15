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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public Rota criar(Rota rota) {
        return rotaRepo.save(rota);
    }

    public Rota criarRota(RotaCriarDTO dto) throws ContentAlreadyExists, NotOwner {
        // verificar privilegios de criacao de rota
        Usuario usuario = usuarioService.buscarUsuarioOnline();
        List<FuncaoDoUsuario> funcaoDoUsuario = new ArrayList<>(usuario.getFuncoes());
        if (funcaoDoUsuario.get(0).getName().equalsIgnoreCase(FuncoesUsuarios.ROLE_ADMIN.name()) || funcaoDoUsuario.get(0).getName().equalsIgnoreCase(FuncoesUsuarios.ROLE_TERMINAL.name())) {
            //verificar se rota ja existe no sistema
            Boolean rotaExiste = rotaRepo.existsByNomeRota(dto.getNomeRota());
            if (rotaExiste) {
                throw new ContentAlreadyExists("Rota ja existe no sistema");
            }
            Rota novaRota = null;
            try {
                novaRota = new Rota(dto);
                rotaRepo.save(novaRota);
                return novaRota;
            } catch (Exception e) {
                throw new RuntimeException("Erro ao salvar a rota");
            }
        } else {
            throw new NotOwner("Nao possui acesso a este recurso");
        }
    }

    public List<RotaRespostaDTO> listarRotas() {
        return rotaRepo.findAll().stream().map(rota -> new RotaRespostaDTO(rota)).collect(Collectors.toList());
    }

    public Page<RotaRespostaDTO> listarRotasPaginadas(int page, int size, Sort sort) {
        // Criar o objeto de paginação com base nos parâmetros de paginação e ordenação
        Pageable pageable = PageRequest.of(page, size, sort);
        // Realizar a consulta paginada ao banco de dados usando o RotaRepository
        Page<Rota> rotas= rotaRepo.findAll(pageable);
        return rotas.map(Rota::toDTO);
    }

    public Rota buscarRotaPorId(Long id) throws ModelNotFound {
        return rotaRepo.findById(id).orElseThrow(() -> new ModelNotFound("Rota nao encontrada"));
    }

    public Rota buscarPorCodigo(String codigoRota) throws ModelNotFound {
        return rotaRepo.findByCodigo(codigoRota).orElseThrow(() -> new ModelNotFound("Rota nao encontrada!"));
    }

    public Boolean existePorDesignacao(String rota) {
        return rotaRepo.existsByNomeRota(rota);
    }

    public Rota buscarRotaPorDesignacao(String nomeRota) throws ModelNotFound {
        return rotaRepo.findByNomeRota(nomeRota).orElseThrow(() -> new ModelNotFound("Rota nao encontrada!"));
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
