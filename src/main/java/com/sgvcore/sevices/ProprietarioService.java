package com.sgvcore.sevices;

import com.sgvcore.DTOs.proprietarioDTOs.ProprietarioCriarDTO;
import com.sgvcore.Model.*;
import com.sgvcore.enums.FuncoesUsuarios;
import com.sgvcore.exceptions.*;
import com.sgvcore.repository.ProprietarioRepo;
import com.sgvcore.utils.PhoneNumberValidator;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
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
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private FuncaoUsuarioService funcaoUsuarioService;
    @Autowired
    private PhoneNumberValidator phoneNumberValidator;

    public Proprietario criar(ProprietarioCriarDTO dto) throws ModelNotFound, ContentAlreadyExists, NotOwner, UnprocessableEntity, ForbiddenException {
        //verificar se usuario tem perfil de terminal, administrador
        Usuario usuario = usuarioService.buscarUsuarioOnline();
        List<FuncaoDoUsuario> funcaoDoUsuario = new ArrayList<>(usuario.getFuncoes());
        if (funcaoDoUsuario.get(0).getName().equalsIgnoreCase(FuncoesUsuarios.ROLE_TERMINAL.name())) {
            Boolean propExist = existePorNuit(dto.getNuit());
            if (propExist) {
                throw new ContentAlreadyExists("Proprietario ja esta cadastrado");
            }
            //verificar existencia de objectos
            Genero genero = generoService.buscarPorId(dto.getIdGenero());
            TipoProprietario tipoProprietario = tipoProprietarioService.buscarPorCodigo(dto.getCodigoTipoProprietario());
            Provincia provincia = provinciaService.buscarProvinciaporCodigo(dto.getCodigoProvincia());
            Distrito distrito = distritoService.buscarDistritoPorCodigoEProvincia(dto.getCodigoDistrito(), provincia);
            TipoDocumentoIdentificacao tipoDocumentoIdentificacao = tipoDocumentoService.buscarTipoDocumentoporId(dto.getIdTipoDocumento());
            Boolean contExiste = contactoService.existePorMsisdn(dto.getMsidsn());
            if (contExiste) {
                throw new ContentAlreadyExists("O Contacto ja existe");
            }
            boolean prefixMoz = phoneNumberValidator.validarTelefoneMocambicano(dto.getMsidsn());
            if (!prefixMoz) {
                throw new UnprocessableEntity("O contacto inserdio nao e mocambicano");
            }
            Contacto novoContacto = new Contacto(dto);
            Boolean docExiste = documentoIdentificacaoService.existePorNumeroDocumento(dto.getNumeroDocumento());
            if (docExiste) {
                throw new ContentAlreadyExists("documento ja existe");
            }
            DocumentoIdentifiacacao novoDocumento;
            if (dto.getDataValidade().before(new Date())) {
                throw new UnprocessableEntity("Documento expirado");
            }
            Proprietario novoProprietario = null;
            try {
                novoDocumento = new DocumentoIdentifiacacao(dto, tipoDocumentoIdentificacao);
                documentoIdentificacaoService.criar(novoDocumento);
                contactoService.criar(novoContacto);
                novoProprietario = new Proprietario(dto, genero, novoContacto, provincia, tipoProprietario, novoDocumento, distrito);
                proprietarioRepo.save(novoProprietario);
            } catch (DataIntegrityViolationException ex) {
                throw new ContentAlreadyExists("Proprietario ja existe");
            } catch (HibernateException ex) {
                throw new RuntimeException("Erro ao salvar proprietario");
            } catch (DataAccessException | NoSuchAlgorithmException ex) {
                throw new DataIntegrityViolationException("Nao foi possivel aceder a a base de dados");
            }
            return novoProprietario;
        } else {
            throw new ForbiddenException("Nao possui acesso a esse recurso");
        }
    }

    public List<Proprietario> listar() throws NotOwner, ForbiddenException {
        //verificar se usuario tem perfil de terminal, administrador
        Usuario usuario = usuarioService.buscarUsuarioOnline();
        List<FuncaoDoUsuario> funcaoDoUsuario = new ArrayList<>(usuario.getFuncoes());
        if (funcaoDoUsuario.get(0).getName().equalsIgnoreCase(FuncoesUsuarios.ROLE_ADMIN.name()) || funcaoDoUsuario.get(0).getName().equalsIgnoreCase(FuncoesUsuarios.ROLE_TERMINAL.name()) || funcaoDoUsuario.get(0).getName().equalsIgnoreCase(FuncoesUsuarios.ROLE_ASSOCIACAO.name())) {
            return proprietarioRepo.findAll();
        }
        throw new ForbiddenException("Nao possui acesso a esse recurso");
    }

    public Proprietario buscarPorId(Long id) {
        return proprietarioRepo.findById(id).orElse(null);
    }

    public Proprietario buscarPorCodigo(@PathVariable(value = "codigoProprietario") String codigo) throws NotOwner, ModelNotFound {
        Usuario usuario = usuarioService.buscarUsuarioOnline();
        List<FuncaoDoUsuario> funcaoDoUsuario = new ArrayList<>(usuario.getFuncoes());
        if (funcaoDoUsuario.get(0).getName().equalsIgnoreCase(FuncoesUsuarios.ROLE_ADMIN.name()) || funcaoDoUsuario.get(0).getName().equalsIgnoreCase(FuncoesUsuarios.ROLE_TERMINAL.name()) || funcaoDoUsuario.get(0).getName().equalsIgnoreCase(FuncoesUsuarios.ROLE_ASSOCIACAO.name())) {
            if (codigo == null) {
                throw new ModelNotFound("codigo de proprietario nao encontrado");
            }
            return proprietarioRepo.findByCodigo(codigo);
        }
        throw new NotOwner("Nao possui acesso a esse recurso");
    }

    public Long numeroProprietarios() throws ForbiddenException {
        Usuario usuario = usuarioService.buscarUsuarioOnline();
        List<FuncaoDoUsuario> funcaoDoUsuario = new ArrayList<>(usuario.getFuncoes());
        if (funcaoDoUsuario.get(0).getName().equalsIgnoreCase(FuncoesUsuarios.ROLE_ADMIN.name()) || funcaoDoUsuario.get(0).getName().equalsIgnoreCase(FuncoesUsuarios.ROLE_TERMINAL.name())) {
            return proprietarioRepo.count();
        }
        throw new ForbiddenException("Nao possui acesso a esse recurso");
    }

    public Boolean existePorNuit(String nuit) {
        return proprietarioRepo.existsByNuit(nuit);
    }
}
