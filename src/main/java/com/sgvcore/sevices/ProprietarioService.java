package com.sgvcore.sevices;

import com.sgvcore.DTOs.proprietarioDTOs.ProprietarioCriarDTO;
import com.sgvcore.Model.*;
import com.sgvcore.enums.FuncoesUsuarios;
import com.sgvcore.exceptions.ContentAlreadyExists;
import com.sgvcore.exceptions.ModelNotFound;
import com.sgvcore.exceptions.NotOwner;
import com.sgvcore.repository.ProprietarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
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

    public Proprietario criar(ProprietarioCriarDTO dto) throws ModelNotFound, ContentAlreadyExists, NotOwner {
        //verificar se usuario tem perfil de terminal, administrador
        Usuario usuario = usuarioService.buscarUsuarioOnline();
        List<FuncaoDoUsuario> funcaoDoUsuario = new ArrayList<>(usuario.getFuncoes());
        if (funcaoDoUsuario.get(0).getName().equalsIgnoreCase(FuncoesUsuarios.ROLE_ADMIN.name()) || funcaoDoUsuario.get(0).getName().equalsIgnoreCase(FuncoesUsuarios.ROLE_TERMINAL.name())) {
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
        throw new NotOwner("Nao possui acesso a esse recurso");
    }

    public List<Proprietario> listar() throws NotOwner {
        //verificar se usuario tem perfil de terminal, administrador
        Usuario usuario = usuarioService.buscarUsuarioOnline();
        List<FuncaoDoUsuario> funcaoDoUsuario = new ArrayList<>(usuario.getFuncoes());
        if (funcaoDoUsuario.get(0).getName().equalsIgnoreCase(FuncoesUsuarios.ROLE_ADMIN.name()) || funcaoDoUsuario.get(0).getName().equalsIgnoreCase(FuncoesUsuarios.ROLE_TERMINAL.name()) || funcaoDoUsuario.get(0).getName().equalsIgnoreCase(FuncoesUsuarios.ROLE_ASSOCIACAO.name())) {
            return proprietarioRepo.findAll();
        }
        throw new NotOwner("Nao possui acesso a esse recurso");
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

    public Long numeroProprietarios() throws NotOwner {
        Usuario usuario = usuarioService.buscarUsuarioOnline();
        List<FuncaoDoUsuario> funcaoDoUsuario = new ArrayList<>(usuario.getFuncoes());
        if (funcaoDoUsuario.get(0).getName().equalsIgnoreCase(FuncoesUsuarios.ROLE_ADMIN.name()) || funcaoDoUsuario.get(0).getName().equalsIgnoreCase(FuncoesUsuarios.ROLE_TERMINAL.name())) {
            return proprietarioRepo.count();
        }
        throw new NotOwner("Nao possui acesso a esse recurso");

    }
}
