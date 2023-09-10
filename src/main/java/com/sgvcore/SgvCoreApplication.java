package com.sgvcore;

import com.sgvcore.Model.*;
import com.sgvcore.exceptions.ModelNotFound;
import com.sgvcore.sevices.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.text.DateFormat;
import java.text.ParseException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

@SpringBootApplication
public class SgvCoreApplication {

    DateFormat f = DateFormat.getDateInstance();

    public static void main(String[] args) {
        SpringApplication.run(SgvCoreApplication.class, args);
    }


    private void criarFuncoesDeUsuarioPadrao(FuncaoUsuarioService funcaoUsuarioService) {
        funcaoUsuarioService.criarFuncao(new FuncaoDoUsuario(null, "ROLE_ADMIN"));
        funcaoUsuarioService.criarFuncao(new FuncaoDoUsuario(null, "ROLE_ASSOCIACAO"));
        funcaoUsuarioService.criarFuncao(new FuncaoDoUsuario(null, "ROLE_TERMINAL"));
    }

    public void inicializarNiveisDeAcesso(NivelAcessoService nivelAcessoService) {
        nivelAcessoService.criarNivelDeAcesso(new NivelAcesso(null, "CRIAR"));
        nivelAcessoService.criarNivelDeAcesso(new NivelAcesso(null, "ATUALIZAR"));
        nivelAcessoService.criarNivelDeAcesso(new NivelAcesso(null, "VER"));
        nivelAcessoService.criarNivelDeAcesso(new NivelAcesso(null, "APAGAR"));
    }


    private void criarSuperAdministrador(FuncaoUsuarioService funcaoUsuarioService, UsuarioService usuarioService) throws ModelNotFound {
        // Creating an Admin and Adding Role Admin
        Usuario user = new Usuario(null,
                "sgv@gmail.com", "sgv123", new HashSet<>(), new ArrayList<>(), false, true, null, null, null,
                Date.from(Instant.ofEpochSecond(System.currentTimeMillis())), null, null);
        usuarioService.criarUsuario(user);
        funcaoUsuarioService.adicionarFuncaoAUsuario("sgv@gmail.com", "ROLE_ADMIN");
    }

    private void criarTerminalUsuario(FuncaoUsuarioService funcaoUsuarioService, UsuarioService usuarioService) throws ModelNotFound {
        // Creating an Admin and Adding Role Admin
        Usuario user = new Usuario(null,
                "trdj@gmail.com", "trdj123", new HashSet<>(), new ArrayList<>(), false, true, null, null, null,
                Date.from(Instant.ofEpochSecond(System.currentTimeMillis())), null, null);
        usuarioService.criarUsuario(user);
        funcaoUsuarioService.adicionarFuncaoAUsuario("trdj@gmail.com", "ROLE_TERMINAL");
    }

    private void criarAssociacaoUsuario(FuncaoUsuarioService funcaoUsuarioService, UsuarioService usuarioService) throws ModelNotFound {
        // Creating an Admin and Adding Role Admin
        Usuario user = new Usuario(null,
                "atmp@gmail.com", "atmp123", new HashSet<>(), new ArrayList<>(), false, true, null, null, null,
                Date.from(Instant.ofEpochSecond(System.currentTimeMillis())), null, null);
        usuarioService.criarUsuario(user);
        funcaoUsuarioService.adicionarFuncaoAUsuario("atmp@gmail.com", "ROLE_ASSOCIACAO");
    }

    private void criarSuperAdministradorTeste(FuncaoUsuarioService funcaoUsuarioService, UsuarioService usuarioService) throws ModelNotFound {
        // Creating an Admin and Adding Role Admin
        Usuario user = new Usuario(null,
                "admin@gmail.com", "admin", new HashSet<>(), new ArrayList<>(), false, true, null, null, null,
                Date.from(Instant.ofEpochSecond(System.currentTimeMillis())), null, null);
        usuarioService.criarUsuario(user);
        funcaoUsuarioService.adicionarFuncaoAUsuario("admin@gmail.com", "ROLE_ADMIN");
    }


    public void inicializarGeneros(GeneroService generoService) {
        generoService.criarGenero(new Genero(null, "Masculino", 'M'));
        generoService.criarGenero(new Genero(null, "Feminino", 'F'));
    }

    public void inicializarMarcasEModelos(MarcaService marcaService, ModeloService modeloService, MarcaModeloService marcaModeloService) {
        Marca toyota = marcaService.criar(new Marca(null, "001", "Toyota"));
        Modelo hilux = modeloService.criar(new Modelo(null, "001", "hilux"));
        marcaModeloService.criar(new MarcaModelo(toyota, hilux));
        Marca ford = marcaService.criar(new Marca(null, "002", "Ford"));
        Modelo mustang = modeloService.criar(new Modelo(null, "002", "Mustang"));
        marcaModeloService.criar(new MarcaModelo(ford, mustang));

        Marca chevrolet = marcaService.criar(new Marca(null, "003", "Chevrolet"));
        Modelo camaro = modeloService.criar(new Modelo(null, "003", "Camaro"));
        Modelo corvette = modeloService.criar(new Modelo(null, "004", "Corvette"));
        marcaModeloService.criar(new MarcaModelo(chevrolet, camaro));
        marcaModeloService.criar(new MarcaModelo(chevrolet, corvette));

        Marca bmw = marcaService.criar(new Marca(null, "005", "BMW"));
        Modelo serie3 = modeloService.criar(new Modelo(null, "005", "Série 3"));
        Modelo serie5 = modeloService.criar(new Modelo(null, "006", "Série 5"));
        marcaModeloService.criar(new MarcaModelo(bmw, serie3));
        marcaModeloService.criar(new MarcaModelo(bmw, serie5));

        Modelo corolla = modeloService.criar(new Modelo(null, "001", "Corolla"));
        Modelo rav4 = modeloService.criar(new Modelo(null, "002", "RAV4"));
        Modelo hiace = modeloService.criar(new Modelo(null, "003", "Hiace"));

        marcaModeloService.criar(new MarcaModelo(toyota, corolla));
        marcaModeloService.criar(new MarcaModelo(toyota, rav4));
        marcaModeloService.criar(new MarcaModelo(toyota, hiace));
        Marca honda = marcaService.criar(new Marca(null, "002", "Honda"));
        Marca mitsubishi = marcaService.criar(new Marca(null, "003", "Mitsubishi"));

        Modelo coaster = modeloService.criar(new Modelo(null, "001", "Coaster"));
        Modelo odyssey = modeloService.criar(new Modelo(null, "002", "Odyssey"));
        Modelo rosa = modeloService.criar(new Modelo(null, "003", "Rosa"));
        Modelo canter = modeloService.criar(new Modelo(null, "004", "Canter"));

        marcaModeloService.criar(new MarcaModelo(toyota, coaster));
        marcaModeloService.criar(new MarcaModelo(honda, odyssey));
        marcaModeloService.criar(new MarcaModelo(mitsubishi, rosa));
        marcaModeloService.criar(new MarcaModelo(mitsubishi, canter));

    }

    public void inicializarCores(CorService corService) {
        Cor branco = corService.criar(new Cor(null, " #FFFFFF", "branco"));
        Cor vermelho = corService.criar(new Cor(null, "#FF0000", "vermelho"));
        Cor verde = corService.criar(new Cor(null, "#00FF00", "verde"));
        Cor azul = corService.criar(new Cor(null, "#0000FF", "azul"));
        Cor amarelo = corService.criar(new Cor(null, "#FFFF00", "amarelo"));
        Cor laranja = corService.criar(new Cor(null, "#FFA500", "laranja"));
        Cor roxo = corService.criar(new Cor(null, "#800080", "roxo"));
        Cor rosa = corService.criar(new Cor(null, "#FFC0CB", "rosa"));
        Cor marrom = corService.criar(new Cor(null, "#A52A2A", "marrom"));
        Cor preto = corService.criar(new Cor(null, "#000000", "preto"));
        Cor cinza = corService.criar(new Cor(null, "#808080", "cinza"));
    }

    public void inicializarTipoProprietarios(TipoProprietarioService tipoProprietarioService) {
        TipoProprietario individual = tipoProprietarioService.criar(new TipoProprietario(null, "Proprietario", "0112"));
        TipoProprietario empresa = tipoProprietarioService.criar(new TipoProprietario(null, "Empresa", "0113"));
    }

    public void inicializarAssociacoesLicencaseTipoDeLicenca(AssociacaoService associacaoService, ContactoService contactoService,
                                                             TipoLicencaService tipoLicencaService,
                                                             LicencaService licencaService) throws ParseException {

        Contacto etrago = contactoService.criar(new Contacto(null, "829283901"));
        Contacto nagi = contactoService.criar(new Contacto(null, "847283892"));
        Contacto tco = contactoService.criar(new Contacto(null, "873902097"));

        TipopLicenca transporte = tipoLicencaService.criar(new TipopLicenca(null, "transporte", "tr", "001"));
        TipopLicenca comercial = tipoLicencaService.criar(new TipopLicenca(null, "comercial", "cl", "002"));
        Date data1 = new Date();
        Licenca transnporteRodoviario = licencaService.criar(new Licenca(null, "12678029p", data1, transporte));
        Licenca coomercioGeral = licencaService.criar(new Licenca(null, "8340987ol", data1, comercial));


    }

    public void inicializarRotas(RotaService rotaService) {
        rotaService.criar(new Rota(null, "Maputo-Gaza", 150L, 500L, "001"));
        rotaService.criar(new Rota(null, "Maputo-Inhambane", 400L, 800L, "002"));
        rotaService.criar(new Rota(null, "Maputo-Beira", 800L, 1500L, "003"));
        rotaService.criar(new Rota(null, "Maputo-Zambezia", 810L, 2000L, "003"));
        rotaService.criar(new Rota(null, "Maputo-Tete", 849L, 2050L, "004"));
        rotaService.criar(new Rota(null, "Maputo-Manica", 900L, 2500L, "005"));
        rotaService.criar(new Rota(null, "Maputo-Nampula", 870L, 3000L, "006"));
        rotaService.criar(new Rota(null, "Maputo-Cabo-Delegado", 890L, 2900L, "007"));
        rotaService.criar(new Rota(null, "Maputo-Niassa", 900L, 5000L, "008"));
    }


    public void inicializarTipoDocumento(TipoDocumentoService tipoDocumentoService) {
        tipoDocumentoService.criar(new TipoDocumentoIdentificacao(null, "Bilhete de identidade", "BI", "002"));
        tipoDocumentoService.criar(new TipoDocumentoIdentificacao(null, "Carta de conducao", "CC", "003"));
        tipoDocumentoService.criar(new TipoDocumentoIdentificacao(null, "Cartao de eleitor", "CE", "004"));
        tipoDocumentoService.criar(new TipoDocumentoIdentificacao(null, "Cedula", "Ced", "005"));
        tipoDocumentoService.criar(new TipoDocumentoIdentificacao(null, "Bolentim de nascimento", "BN", "006"));
        tipoDocumentoService.criar(new TipoDocumentoIdentificacao(null, "Assento", "Ass", "007"));
        tipoDocumentoService.criar(new TipoDocumentoIdentificacao(null, "Passaporte", "Pass", "008"));
        tipoDocumentoService.criar(new TipoDocumentoIdentificacao(null, "Documento de identificacao e residencia para estrangeiro", "DIRE", "009"));

    }


    @Bean
    CommandLineRunner run
            (GeneroService generoService, ZonaRegionalService zonaRegionalService,
             ProvinciaService provinciaService, DistritoService distritoService,
             ProvinciaDistritoService provinciaDistritoService,
             TipoDocumentoService tipoDocumentoService, TipoLicencaService tipoLicencaService, LicencaService licencaService,
             AssociacaoService associacaoService, ContactoService contactoService, RotaService rotaService, TipoProprietarioService tipoProprietarioService,
             CorService corService, ModeloService modeloService, MarcaService marcaService, MarcaModeloService marcaModeloService, FuncaoUsuarioService funcaoUsuarioService,
             NivelAcessoService nivelAcessoService, UsuarioService usuarioService) {
        return args -> {
            //Auto Runnable code (on start)
            if (funcaoUsuarioService.listarFuncoes().isEmpty()) {
                inicializarNiveisDeAcesso(nivelAcessoService);
                criarFuncoesDeUsuarioPadrao(funcaoUsuarioService);
                inicializarGeneros(generoService);
                inicializarRotas(rotaService);
                inicializarTipoDocumento(tipoDocumentoService);
                inicializarAssociacoesLicencaseTipoDeLicenca(associacaoService, contactoService, tipoLicencaService, licencaService);
                inicializarTipoProprietarios(tipoProprietarioService);
                inicializarCores(corService);
                inicializarMarcasEModelos(marcaService, modeloService, marcaModeloService);
                criarAssociacaoUsuario(funcaoUsuarioService, usuarioService);
                criarSuperAdministrador(funcaoUsuarioService, usuarioService);
                criarTerminalUsuario(funcaoUsuarioService, usuarioService);
                criarSuperAdministradorTeste(funcaoUsuarioService, usuarioService);


            }
        };
    }

    @Bean
    public javax.validation.Validator localValidatorFactoryBean() {
        return new LocalValidatorFactoryBean();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**");
            }
        };
    }

}
