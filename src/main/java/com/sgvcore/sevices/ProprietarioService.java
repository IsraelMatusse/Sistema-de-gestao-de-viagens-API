package com.sgvcore.sevices;

import com.sgvcore.DTOs.proprietarioDTOs.ProprietarioCriarDTO;
import com.sgvcore.Model.*;
import com.sgvcore.exceptions.ContentAlreadyExists;
import com.sgvcore.exceptions.ModelNotFound;
import com.sgvcore.repository.ProprietarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProprietarioService {

    @Autowired
    private ProprietarioRepo proprietarioRepo;
    @Autowired
    private GeneroService generoService;
    @Autowired
    private DocumentoIdentificacaoService documentoIdentificacaoService;
    @Autowired
    private TipoProprietarioService tipoProprietarioService;
    @Autowired
    private TipoDocumentoService tipoDocumentoService;
    @Autowired
    private ContactoService contactoService;
    @Autowired
    private ProvinciaService provinciaService;
    @Autowired
    private DistritoService distritoService;

    public Proprietario criar(ProprietarioCriarDTO dto) throws ModelNotFound, ContentAlreadyExists {
        //verificar se usuario tem perfil de terminal, administrador


        //verificar existencia de objectos
        Genero genero = generoService.buscarPorId(dto.getIdGenero());
        TipoProprietario tipoProprietario = tipoProprietarioService.buscarPorCodigo(dto.getCodigoTipoProprietario());
        Provincia provincia = provinciaService.buscarProvinciaporCodigo(dto.getCodigoProvincia());
        Distrito distrito = distritoService.buscarDistritoPorCodigoEProvincia(dto.getCodigoDistrito(), provincia);
        TipoDocumentoIdentificacao tipoDocumentoIdentificacao = tipoDocumentoService.buscarPorCodigo(dto.getCodigoTipoDocumento());
        Boolean contExiste = contactoService.existePorMsisdn(dto.getMsidsn());
        if (contExiste) {
            throw new ContentAlreadyExists("O Contacto ja existe");
        }
        Contacto novoContacto = new Contacto(dto);
        documentoIdentificacaoService.buscarPorNumeroDocumento(dto.getNumeroDocumento());
        DocumentoIdentifiacacao novoDocumento = new DocumentoIdentifiacacao(dto, tipoDocumentoIdentificacao);
        Proprietario novoProprietario = null;
        try {
            documentoIdentificacaoService.criar(documentoIdentificacaoService.converterDTO(novoDocumento));
            contactoService.criar(novoContacto);
            novoProprietario = new Proprietario(dto, genero, novoContacto, provincia, tipoProprietario, novoDocumento, distrito);
            proprietarioRepo.save(novoProprietario);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar proprietario");
        }
        return novoProprietario;
    }

    public List<Proprietario> listar() {
        return proprietarioRepo.findAll();
    }

    public Proprietario buscarPorId(Long id) {
        return proprietarioRepo.findById(id).orElse(null);
    }

    public Proprietario buscarPorCodigo(String codigo) {
        return proprietarioRepo.findByCodigo(codigo);
    }

    public Long numeroProprietarios() {
        return proprietarioRepo.count();
    }
}
