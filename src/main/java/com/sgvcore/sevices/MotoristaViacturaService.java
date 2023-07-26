package com.sgvcore.sevices;

import com.sgvcore.DTOs.viaturaDTOs.ViacturaAssociarMotoristaDTO;
import com.sgvcore.Model.*;
import com.sgvcore.exceptions.ContentAlreadyExists;
import com.sgvcore.exceptions.ModelNotFound;
import com.sgvcore.exceptions.NotOwner;
import com.sgvcore.repository.MotoristaViacturaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotoristaViacturaService {

    @Autowired
    private MotoristaViacturaRepo motoristaViacturaRepo;
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
    private MotoristaService motoristaService;

    public MotoristaViactura criar(ViacturaAssociarMotoristaDTO dto) throws NotOwner, ModelNotFound, ContentAlreadyExists {
        Genero genero = generoService.buscarPorId(dto.getIdGenero());
        Provincia provincia = provinciaService.buscarProvinciaporCodigo(dto.getCodigoProvincia());
        TipoDocumentoIdentificacao tipoDocumentoIdentificacao = tipoDocumentoService.buscarTipoDocumentoporId(dto.getTipoDocumento());
        documentoIdentificacaoService.existePorNumeroDocumento(dto.getNumeroDocumento());
        DocumentoIdentifiacacao novoDocumento;
        MotoristaViactura novoMotoristaViatura = null;
        Viatura viatura = viaturaService.buscarPorCodigo(dto.getCodigoViatura());
        try {
            novoDocumento = new DocumentoIdentifiacacao(dto, tipoDocumentoIdentificacao);
            documentoIdentificacaoService.criar(novoDocumento);
            Motorista motorista = new Motorista(dto, novoDocumento, genero, provincia);
            motoristaService.cirar(motorista);
            novoMotoristaViatura = new MotoristaViactura(motorista, viatura);
            motoristaViacturaRepo.save(novoMotoristaViatura);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Erro ao salvar motorista");
        }
        return novoMotoristaViatura;
    }

    public List<MotoristaViactura> listar() {
        return motoristaViacturaRepo.findAll();
    }

    public MotoristaViactura buscarPorCodigo(String codigo) {
        return motoristaViacturaRepo.findByCodigo(codigo);
    }

    public Motorista buscarMotoristaPeloCodigoMotoristaEViatura(Viatura viatura, String codigoMotorista) throws ModelNotFound {
        Motorista motorista = motoristaViacturaRepo.findByIdViacturaAndIdMotoristaCodigo(viatura, codigoMotorista).orElseThrow(() -> new ModelNotFound("Motorista nao encontrado ou nao e desta viatura")).getIdMotorista();
        return motorista;
    }
}
