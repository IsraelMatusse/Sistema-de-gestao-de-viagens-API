package com.sgvcore.sevices;

import com.sgvcore.DTOs.viagemDTO.ViagemCriarDTO;
import com.sgvcore.DTOs.viagemDTO.ViagemRespostaDTO;
import com.sgvcore.Model.*;
import com.sgvcore.enums.FuncoesUsuarios;
import com.sgvcore.exceptions.*;
import com.sgvcore.repository.ViagemRepo;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ViagemService {

    @Autowired
    private ViagemRepo viagemRepo;
    @Autowired
    private RotaService rotaService;
    @Autowired
    private AssociacaoService associacaoService;
    @Autowired
    private MotoristaService motoristaService;
    @Autowired
    private ViaturaService viaturaService;
    @Autowired
    private MotoristaViacturaService motoristaViacturaService;
    @Autowired
    private UsuarioService usuarioService;

    public Viagem criar(ViagemCriarDTO dto) throws BadRequest, ModelNotFound, UnprocessableEntity, ContentAlreadyExists, ForbiddenException {
        //verificar permissoes do usuario para aceder a  funcionalidade
        Usuario usuario = usuarioService.buscarUsuarioOnline();

        List<FuncaoDoUsuario> funcaoDoUsuario = new ArrayList<>(usuario.getFuncoes());

        if (funcaoDoUsuario.get(0).getName().equalsIgnoreCase(FuncoesUsuarios.ROLE_ASSOCIACAO.name())) {
            // verificar data de partida e chegada
            if (dto.getSaida().after(dto.getPrevChegada()) || dto.getSaida().equals(dto.getPrevChegada())) {
                throw new UnprocessableEntity("Verique as datas");
            }
            //verificar a existencia da rota e da viatura
            Rota rota = rotaService.buscarRotaPorId(dto.getIdRota());

            Associacao associacao = associacaoService.buscarAssociacaoPorUsuarioOnline(usuario);

            //buscar viatura da associacao pelo codigo da viatura a a associacao
            Viatura viatura = viaturaService.buscarViaturaPelaAssociacaoECodigoViatura(associacao, dto.getCodigoViatura());

            Motorista motorista = motoristaViacturaService.buscarMotoristaPeloCodigoMotoristaEViatura(viatura, dto.getCodigoMotorista());

            try {
                Viagem novaViagem = new Viagem(dto, rota, associacao, viatura, motorista);
                return viagemRepo.save(novaViagem);
            } catch (DataIntegrityViolationException ex) {
                throw new ContentAlreadyExists("Viagem ja existe");
            } catch (HibernateException ex) {
                throw new RuntimeException("Erro ao salvar a viagem");
            } catch (DataAccessException | NoSuchAlgorithmException ex) {
                throw new RuntimeException("Erro ao aceder a base de dados");
            }
        }
        throw new ForbiddenException("Nao possuir acesso a esse recurso");
    }

    public List<ViagemRespostaDTO> listar() {
        return viagemRepo.findAll().stream().map(viagem -> new ViagemRespostaDTO(viagem)).collect(Collectors.toList());
    }

    public Page<Viagem> listarTodasViagensPaginadas(int page, int size, Sort sort) {
        Pageable pageable = PageRequest.of(page, size, sort);
        return viagemRepo.findAll(pageable);
    }

    public Viagem buscarPorId(Long id) throws ModelNotFound {
        return viagemRepo.findById(id).orElseThrow(() -> new ModelNotFound("Viagem nao encontrada"));
    }

    public Viagem buscarPorCodigo(String codigo) throws ModelNotFound {
        return viagemRepo.findByCodigo(codigo).orElseThrow(() -> new ModelNotFound("Viagem nao encontrada"));
    }

    public ViagemRespostaDTO buscarPorCodigoRes(String codigo) throws ModelNotFound {
        Viagem viagem = viagemRepo.findByCodigo(codigo).orElseThrow(() -> new ModelNotFound("Viagem nao encontrada"));
        return new ViagemRespostaDTO(viagem);
    }

    public Long numeroViagens() {
        return viagemRepo.count();
    }

    public List<ViagemRespostaDTO> listarViagensPelaDataDeHoje(Date saida) {
        return viagemRepo.findBySaida(saida).stream().map(viagem -> new ViagemRespostaDTO(viagem)).collect(Collectors.toList());
    }

    public Page<Viagem> listarViagnsDoDiaPaginado(int page, int size, Sort sort, Date saida) {
        Pageable pageable = PageRequest.of(page, size, sort);
        return viagemRepo.findAllBySaida(saida, pageable);
    }

}
