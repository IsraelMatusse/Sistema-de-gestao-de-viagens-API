package com.sgvcore.sevices;

import com.sgvcore.DTOs.documentoIdentificacaoDTOs.DocumentoIdentificacaoCriarDTO;
import com.sgvcore.DTOs.viajanteDTO.ViajanteCriarDTO;
import com.sgvcore.DTOs.viajanteDTO.ViajanteRespostaDTO;
import com.sgvcore.Model.*;
import com.sgvcore.exceptions.ContentAlreadyExists;
import com.sgvcore.exceptions.ModelNotFound;
import com.sgvcore.repository.ViagemRepo;
import com.sgvcore.repository.ViajanteRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ViajanteService {

    @Autowired
    DocumentoIdentificacaoService documentoIdentificacaoService;
    @Autowired
    private ViajanteRepo viajanteRepo;
    @Autowired
    private ViagemRepo viagemRepo;
    @Autowired
    private ViagemService viagemService;
    @Autowired
    private ViajanteService viajanteService;
    @Autowired
    private RotaService rotaService;
    @Autowired
    private ViagemViajanteService viagemViajanteService;
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
    private TipoDocumentoService tipoDocumentoService;

    public Viajante criar(ViajanteCriarDTO dto) throws ModelNotFound, ContentAlreadyExists {
        Genero genero = generoService.buscarPorId(dto.getIdGenero());
        Provincia provincia = provinciaService.buscarProvinciaporCodigo(dto.getCodigoProvincia());
        Distrito distrito = distritoService.buscarDistritoPorCodigoEProvincia(dto.getCodigoDistrito(), provincia);
        TipoDocumentoIdentificacao tipoDocumentoIdentificacao = tipoDocumentoService.buscarTipoDocumentoporId(dto.getIdTipoDocumento());
        Boolean docExiste = documentoIdentificacaoService.existePorNumeroDocumento(dto.getNumeroDocumento());
        if (docExiste) {
            throw new ContentAlreadyExists("Documento ja existe");
        }
        DocumentoIdentifiacacao novoDocumento = null;
        {
            Boolean contExiste = contactoService.existePorMsisdn(dto.getMsisdn());
            if (contExiste) {
                throw new ContentAlreadyExists("Contacto ja existe");
            }
            Contacto novoContacto = null;
            Carga carga = cargaService.buscarCargaPorDesignacao(dto.getDesignacao());
            Carga novaCarga = null;
            try {
                novaCarga = cargaService.criar(new Carga(dto));
                novoContacto = contactoService.criar(new Contacto(dto));
                novoDocumento = documentoIdentificacaoService.criar(new DocumentoIdentificacaoCriarDTO(dto, tipoDocumentoIdentificacao));
                return viajanteRepo.save(new Viajante(dto, genero, novaCarga, novoDocumento, provincia, distrito, novoContacto));
            } catch (Exception e) {
                throw new RuntimeException("Erro ao salvar o viajante");
            }
        }
    }

    public List<ViajanteRespostaDTO> listarViajantes() {
        return viajanteRepo.findAll().stream().map(viajante -> new ViajanteRespostaDTO(viajante)).collect(Collectors.toList());
    }

    public ViajanteRespostaDTO buscarViajantPorId(Long id) {
        Viajante viajante = viajanteRepo.findById(id).orElse(null);
        if (viajante != null) {
            return new ViajanteRespostaDTO(viajante);
        }
        return null;
    }

    public Long numeroViajantes() {
        return viajanteRepo.count();
    }

    public ViajanteCriarDTO converterDTO(Viajante viajante) {
        ViajanteCriarDTO dto = new ViajanteCriarDTO();
        BeanUtils.copyProperties(viajante, dto);
        return dto;
    }
}
