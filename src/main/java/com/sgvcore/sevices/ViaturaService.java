package com.sgvcore.sevices;

import com.sgvcore.DTOs.viaturaDTOs.ViaturaCriarDTO;
import com.sgvcore.DTOs.viaturaDTOs.ViaturaRespostaDTO;
import com.sgvcore.Model.Associacao;
import com.sgvcore.Model.Proprietario;
import com.sgvcore.Model.Rota;
import com.sgvcore.Model.Viatura;
import com.sgvcore.exceptions.ContentAlreadyExists;
import com.sgvcore.exceptions.ModelNotFound;
import com.sgvcore.exceptions.NotOwner;
import com.sgvcore.repository.ViacturaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ViaturaService {

    @Autowired
    private ViacturaRepo viacturaRepo;
    @Autowired
    private ViaturaService viaturaService;
    @Autowired
    private RotaService rotaService;
    @Autowired
    private ProprietarioService proprietarioService;
    @Autowired
    private AssociacaoService associacaoService;
    @Autowired
    private AssociacaoRotaService asociacaoRotaService;
    @Autowired
    private ProvinciaService provinciaService;
    @Autowired
    private GeneroService generoService;
    @Autowired
    private DocumentoIdentificacaoService documentoIdentificacaoService;
    @Autowired
    private TipoDocumentoService tipoDocumentoService;
    @Autowired
    private MotoristaViacturaService motoristaViacturaService;
    @Autowired
    private MotoristaService motoristaService;

    public Viatura criar(ViaturaCriarDTO dto) throws NotOwner, ModelNotFound, ContentAlreadyExists {
        if (viacturaRepo.existsByMatricula(dto.getMatricula())) {
            throw new ContentAlreadyExists("Viatura com esta matricula ja existe");
        }
        Proprietario proprietario = proprietarioService.buscarPorCodigo(dto.getCodigoProprietario());
        Associacao associacao = associacaoService.buscarPorCodigo(dto.getCodigoAssociacao());
        Rota rota = asociacaoRotaService.buscarRotasPeloCodigoDaAssociacaoEAssociacao(associacao, dto.getCodigoRota());
        try {
            Viatura viatura = new Viatura(dto, rota, proprietario, associacao);
            return viacturaRepo.save(viatura);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar viatura");
        }
    }

    public List<ViaturaRespostaDTO> listarViacturas() {
        List<Viatura> viatura = viacturaRepo.findAll();
        return viatura.stream().map(viactura -> new ViaturaRespostaDTO(viactura)).collect(Collectors.toList());
    }

    public Viatura buscarViaturaPorId(Long id) {
        return viacturaRepo.findById(id).orElse(null);
    }

    public ViaturaRespostaDTO buscarPorCodigoRes(String codigoViactura) throws ModelNotFound {
        Viatura viactura = viacturaRepo.findByCodigo(codigoViactura).orElseThrow(() -> new ModelNotFound("Viatura nao encontrada"));
        return new ViaturaRespostaDTO(viactura);
    }

    public Viatura buscarPorCodigo(String codigoViactura) throws ModelNotFound {
        return viacturaRepo.findByCodigo(codigoViactura).orElseThrow(() -> new ModelNotFound("Viatura nao encontrada"));
    }

    public List<ViaturaRespostaDTO> buscarViaturasDaDaAssociacao(Associacao associacao) {
        return viacturaRepo.findByIdAssociacao(associacao).stream().map(viatura -> new ViaturaRespostaDTO(viatura)).collect(Collectors.toList());
    }

    public List<ViaturaRespostaDTO> buscarViaturasDeUmaRota(Rota rota) {
        return viacturaRepo.findByIdRota(rota).stream().map(viactura -> new ViaturaRespostaDTO(viactura)).collect(Collectors.toList());
    }

    public ViaturaRespostaDTO buscarViaturaPelaAssociacaoECodigoViaturaRes(Associacao associacao, String codigViatura) throws ModelNotFound {
        Viatura viatura = viacturaRepo.findByIdAssociacaoAndCodigo(associacao, codigViatura).orElseThrow(() -> new ModelNotFound("Viatura nao encontrada"));
        return new ViaturaRespostaDTO(viatura);
    }

    public Viatura buscarViaturaPelaAssociacaoECodigoViatura(Associacao associacao, String codigoViatura) throws ModelNotFound {
        return viacturaRepo.findByIdAssociacaoAndCodigo(associacao, codigoViatura).orElseThrow(() -> new ModelNotFound("Viatura nao encontrada"));
    }

    public Boolean existePorMatricula(String matricula) {
        return viacturaRepo.existsByMatricula(matricula);
    }

    public Long numeroViaturas() {
        return viacturaRepo.count();
    }
}
