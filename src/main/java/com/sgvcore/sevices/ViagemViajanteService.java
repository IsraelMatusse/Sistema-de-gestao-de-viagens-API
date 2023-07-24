package com.sgvcore.sevices;

import com.sgvcore.DTOs.viagemDTO.ViagemAssociarViajanteDTO;
import com.sgvcore.DTOs.viajanteDTO.ViajanteRespostaDTO;
import com.sgvcore.Model.*;
import com.sgvcore.exceptions.ContentAlreadyExists;
import com.sgvcore.exceptions.ModelNotFound;
import com.sgvcore.repository.ViagemRepo;
import com.sgvcore.repository.ViagemViajanteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ViagemViajanteService {

    @Autowired
    private TipoDocumentoService tipoDocumentoService;
    @Autowired
    private DocumentoIdentificacaoService documentoIdentificacaoService;
    @Autowired
    private ViagemViajanteRepo viagemViajanteRepo;
    @Autowired
    private ViagemRepo viagemRepo;
    @Autowired
    private ViagemService viagemService;
    @Autowired
    private ViajanteService viajanteService;
    @Autowired
    private RotaService rotaService;
    @Autowired
    private AssociacaoService associacaoService;
    @Autowired
    private GeneroService generoService;
    @Autowired
    private ProvinciaService provinciaService;
    @Autowired
    private DistritoService distritoService;
    @Autowired
    private ContactoService contactoService;
    @Autowired
    private CargaService cargaService;
    @Autowired
    private MotoristaService motoristaService;
    @Autowired
    private ViaturaService viaturaService;
    @Autowired
    private MotoristaViacturaService motoristaViacturaService;
    @Autowired
    private UsuarioService usuarioService;

    public ViagemViajante criar(ViagemAssociarViajanteDTO dto) throws ModelNotFound, ContentAlreadyExists {
        // validações de existência
        Viagem viagem = viagemService.buscarPorCodigo(dto.getCodigoViagem());
        Genero genero = generoService.buscarPorId(dto.getIdGenero());
        Provincia provincia = provinciaService.buscarProvinciaporCodigo(dto.getCodigoProvincia());
        Distrito distrito = distritoService.buscarDistritoPorCodigoEProvincia(dto.getCodigoDistrito(), provincia);
        TipoDocumentoIdentificacao tipoDocumentoIdentificacao = tipoDocumentoService.buscarTipoDocumentoporId(dto.getIdTipoDocumento());
        // Verificar a não existência dos objetos antes de criá-los
        Boolean docExiste= documentoIdentificacaoService.existePorNumeroDocumento(dto.getNumeroDocumento());
        if(docExiste){
            throw new ContentAlreadyExists("Documeto ja existe");
        }
        Boolean contExiste = contactoService.existePorMsisdn(dto.getMsisdn());
        if(contExiste){
            throw new ContentAlreadyExists("Contacto ja existe");
        }
        Boolean cargaExiste = cargaService.existeCargaPorDesignacao(dto.getDesignacao());
        if(cargaExiste){
            throw new ContentAlreadyExists("Carga ja existe");
        }
        // Criar salvar os objetos
        try {
            DocumentoIdentifiacacao novoDocumento = new DocumentoIdentifiacacao(dto, tipoDocumentoIdentificacao);
            documentoIdentificacaoService.criarr(novoDocumento);

            Contacto novoContacto = new Contacto(dto);
            contactoService.criar(novoContacto);

            Carga novaCarga = new Carga(dto);
            cargaService.criar(novaCarga);

            Viajante viajante = new Viajante(dto, genero, novaCarga, novoDocumento, provincia, distrito, novoContacto);
            viajanteService.criarr(viajante);

            ViagemViajante viagemViajante = new ViagemViajante(viagem, viajante);
            return viagemViajanteRepo.save(viagemViajante);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar o viajante");
        }
    }

    public List<ViagemViajante> listar() {
        return viagemViajanteRepo.findAll();
    }

    public ViagemViajante buscarPorId(Long id) throws ModelNotFound {
        return viagemViajanteRepo.findById(id).orElseThrow(() -> new ModelNotFound("Viagem nao encontrada"));
    }

    public List<ViajanteRespostaDTO> listarViajantesDeUmaViagem(Viagem viagem) {
        return viagemViajanteRepo.findByIdViagem(viagem).stream().map(viagemViajante -> new ViajanteRespostaDTO(viagemViajante.getIdViajante())).collect(Collectors.toList());
    }

    public Long numeroViagens(Viagem viagem) {
        return viagemViajanteRepo.countByIdViagem(viagem);
    }


}
