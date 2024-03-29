package com.sgvcore.sevices;

import com.sgvcore.DTOs.associacaoDTOs.AssociacaoCriarDTO;
import com.sgvcore.DTOs.associacaoDTOs.AssociacaoRespostaDTO;
import com.sgvcore.Model.*;
import com.sgvcore.enums.FuncoesUsuarios;
import com.sgvcore.exceptions.BadRequest;
import com.sgvcore.exceptions.ContentAlreadyExists;
import com.sgvcore.exceptions.ModelNotFound;
import com.sgvcore.exceptions.NotOwner;
import com.sgvcore.repository.AssociacaoRepo;
import lombok.RequiredArgsConstructor;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AssociacaoService {

    private final AssociacaoRepo associacaoRepo;

    private final ContactoService contactoService;

    private final LicencaService licencaService;

    private final RotaService rotaService;

    private final AssociacaoRotaService associacaoRotaService;

    private final  TipoLicencaService tipoLicencaService;

    private final UsuarioService usuarioService;

    public Associacao criar(Associacao associacao) {
        return associacaoRepo.save(associacao);
    }

    public Associacao criarAssociacao(AssociacaoCriarDTO dto) throws ContentAlreadyExists, ModelNotFound, NotOwner {
        //verificar se usuario online e administrador
        Usuario usuario = usuarioService.buscarUsuarioOnline();
        List<FuncaoDoUsuario> funcaoDoUsuario = new ArrayList<>(usuario.getFuncoes());
        if (funcaoDoUsuario.get(0).getName().equalsIgnoreCase(FuncoesUsuarios.ROLE_ADMIN.name()) || funcaoDoUsuario.get(0).getName().equalsIgnoreCase(FuncoesUsuarios.ROLE_TERMINAL.name())) {
            //verificar se contacto ja existe
            Boolean contExiste = contactoService.existePorMsisdn(dto.getMsisdn());
            if (contExiste) {
                throw new ContentAlreadyExists("Contacto ja existe");
            }
            Contacto novoContacto = new Contacto(dto);
            TipopLicenca tipopLicenca = tipoLicencaService.buscarTipoLicencaPorId(dto.getTipoLicenca());
            Boolean existeLicenca = licencaService.existePorNumeroDeLicenca(dto.getNumeroLicenca());
            if (existeLicenca) {
                throw new ContentAlreadyExists("Erro, licenca ja existe");
            }
            Licenca novaLicenca = new Licenca(dto, tipopLicenca);
            Associacao novaAssociao = null;
            try {

                if (!dto.getRotas().isEmpty()) {
                    for (int i = 0; i < dto.getRotas().size(); i++) {
                        Boolean existe = rotaService.existePorDesignacao(dto.getRotas().get(i));
                        if (!existe) {
                            throw new ModelNotFound("Rota nao encontrda");
                        }
                    }
                    licencaService.criar(novaLicenca);
                    contactoService.criar(novoContacto);
                    novaAssociao = new Associacao(dto, novoContacto, novaLicenca);
                    associacaoRepo.save(novaAssociao);

                    for (int i = 0; i < dto.getRotas().size(); i++) {
                        Rota rota = rotaService.buscarRotaPorDesignacao(dto.getRotas().get(i));
                        AssociacaoRota novaAssociacaoRota = new AssociacaoRota(rota, novaAssociao);
                        associacaoRotaService.criar(novaAssociacaoRota);
                    }
                }
            } catch (HibernateException | NoSuchAlgorithmException ex) {
                throw new RuntimeException("Erro ao cadastrar a associacao");
            }

            return novaAssociao;
        } else {
            throw new NotOwner("Nao possui acesso a este recurso");
        }
    }

    public List<AssociacaoRespostaDTO> listarAssociacao() {
        return associacaoRepo.findAll().stream().map(associacao -> new AssociacaoRespostaDTO(associacao)).collect(Collectors.toList());
    }

    public Page<Associacao> listarAssociacoesPorPaginacao(int page, int size, Sort sort) {
        Pageable pageable = PageRequest.of(page, size, sort);
        return associacaoRepo.findAll(pageable);
    }

    public Associacao buscarPorCodigo(String codigo) throws ModelNotFound {
        return associacaoRepo.findByCodigo(codigo).orElseThrow(() -> new ModelNotFound("Associacao nao encontrada"));
    }

    public AssociacaoRespostaDTO buscarPorCodigoRes(String codigo) throws ModelNotFound {
        Associacao associacao = associacaoRepo.findByCodigo(codigo).orElseThrow(() -> new ModelNotFound("Associacao nao encontrada"));
        return new AssociacaoRespostaDTO(associacao);
    }

    public Long numeroDeAssociacoes() {
        return associacaoRepo.count();
    }
    //buscar associacao por usuario online

    public Associacao buscarAssociacaoPorUsuarioOnline(Usuario usuario) throws ModelNotFound {
        return associacaoRepo.findByUsuario(usuario).orElseThrow(() -> new ModelNotFound("Associacao nao encontrada"));
    }

    public AssociacaoRespostaDTO buscarAssociacaoPorUsuarioOnlineRes(Usuario usuario, @RequestParam(value = "codigoAssociacao", required = false) String codigoAssociacao) throws ModelNotFound, NotOwner, BadRequest {
        List<FuncaoDoUsuario> funcaoDoUsuario = new ArrayList<>(usuario.getFuncoes());
        if (funcaoDoUsuario.get(0).getName().equalsIgnoreCase(FuncoesUsuarios.ROLE_ASSOCIACAO.name())) {
            Associacao associacao = associacaoRepo.findByUsuario(usuario).orElseThrow(() -> new ModelNotFound("Associacao nao encontrada"));
            return new AssociacaoRespostaDTO(associacao);
        } else {
            if (codigoAssociacao == null) {
                throw new BadRequest("codigo de associacao nao presente na requesicao");
            }
            Associacao associacao = associacaoRepo.findByCodigo(codigoAssociacao).orElseThrow(() -> new ModelNotFound("Associacao nao encontrada"));
            return new AssociacaoRespostaDTO(associacao);
        }
    }


}
