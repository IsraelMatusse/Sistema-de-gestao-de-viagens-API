package com.sgvcore.sevices;

import com.sgvcore.DTOs.viagemDTO.ViagemCriarDTO;
import com.sgvcore.DTOs.viagemDTO.ViagemRespostaDTO;
import com.sgvcore.Model.*;
import com.sgvcore.enums.FuncoesUsuarios;
import com.sgvcore.exceptions.BadRequest;
import com.sgvcore.exceptions.ModelNotFound;
import com.sgvcore.repository.ViagemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Viagem criar(ViagemCriarDTO dto) throws BadRequest, ModelNotFound {
        //verificar permissoes do usuario para aceder a  funcionalidade
        Usuario usuario = usuarioService.buscarUsuarioOnline();
        List<FuncaoDoUsuario> funcaoDoUsuario = new ArrayList<>(usuario.getFuncoes());
        if (funcaoDoUsuario.get(0).getName().equalsIgnoreCase(FuncoesUsuarios.ROLE_ASSOCIACAO.name()) || funcaoDoUsuario.get(0).getName().equalsIgnoreCase(FuncoesUsuarios.ROLE_TERMINAL.name()))
            // verificar data de partida e chegada
            if (dto.getSaida().before(dto.getPrevChegada()) || dto.getSaida().equals(dto.getPrevChegada())) {
                throw new BadRequest("Verique as datas");
            }
        //verificar a existencia da rota e da viatura
        Rota rota = rotaService.buscarRotaPorId(dto.getIdRota());
        Associacao associacao = associacaoService.buscarPorCodigo(dto.getCodigoAssociacao());
        //buscar viatura da associacao pelo codigo da viatura a a associacao
        Viatura viatura = viaturaService.buscarViaturaPelaAssociacaoECodigoViatura(associacao, dto.getCodigoViatura());
        Motorista motorista = motoristaViacturaService.buscarMotoristaPeloCodigoMotoristaEViatura(viatura, dto.getCodigoMotorista());
        try {
            Viagem novaViagem = new Viagem(dto, rota, associacao, viatura, motorista);
            return viagemRepo.save(novaViagem);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar a viagem");
        }
    }

    public List<ViagemRespostaDTO> listar() {
        return viagemRepo.findAll().stream().map(viagem -> new ViagemRespostaDTO(viagem)).collect(Collectors.toList());
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

    public List<ViagemRespostaDTO> buscarViagensPelaDataDeHoje(Date saida) {
        return viagemRepo.findBySaida(saida).stream().map(viagem -> new ViagemRespostaDTO(viagem)).collect(Collectors.toList());
    }

}
