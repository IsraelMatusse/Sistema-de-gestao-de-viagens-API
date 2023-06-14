package com.sgvcore;

import com.sgvcore.Model.*;
import com.sgvcore.sevices.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SgvCoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(SgvCoreApplication.class, args);
	}


	public void inicializarGeneros(GeneroService generoService) {
		generoService.criarGenero(new Genero(null, "Masculino", 'M'));
		generoService.criarGenero(new Genero(null, "Feminino", 'F'));
	}

	public void inicializarProvinciasEDistritos(ProvinciaService provinciaService,
												DistritoService distritoService, ProvinciaDistritoService provinciaDistritoService,
												ZonaRegionalService zonaRegionalService){
		ZonaRegional zonaSul = zonaRegionalService.criar(new ZonaRegional(null, "ZONA SUL"));
		Provincia maputo = provinciaService.criar(new Provincia(null, "Cidade de Maputo", "0101", "MPC", zonaSul));

		// Distritos de Maputo
		Distrito kaMpfumo = distritoService.criar(new Distrito(null, "Distrito de KaMpfumo", "0958", maputo));
		Distrito nlhamankulu = distritoService.criar(new Distrito(null, "Distrito de Nlhamankulu", "0847", maputo));
		Distrito kaMaxaquene = distritoService.criar(new Distrito(null, "Distrito de KaMaxaquene", "0356", maputo));
		Distrito moamba = distritoService.criar(new Distrito(null, "Distrito de Moamba", "7381", maputo));

		provinciaDistritoService.criar(new ProvinciaDistrito(null, kaMpfumo, maputo));
		provinciaDistritoService.criar(new ProvinciaDistrito(null, nlhamankulu, maputo));
		provinciaDistritoService.criar(new ProvinciaDistrito(null, kaMaxaquene, maputo));
		provinciaDistritoService.criar(new ProvinciaDistrito(null, moamba, maputo));


		Provincia gaza = provinciaService.criar(new Provincia(null, "Provincia de Gaza", "0102", "PG", zonaSul));
		//Distritos de Gaza
		Distrito chibuto = distritoService.criar(new Distrito(null, "Distrito de Chibuto", "9031", gaza));
		Distrito chókwè = distritoService.criar(new Distrito(null, "Distrito de Chokwe", "0492", gaza));
		Distrito bilene = distritoService.criar(new Distrito(null, "Distrito de Bilene", "2397", gaza));
		Distrito xai_xai = distritoService.criar(new Distrito(null, "Distrito de Xai_xai", "2793", gaza));

		provinciaDistritoService.criar(new ProvinciaDistrito(null, chibuto, gaza));
		provinciaDistritoService.criar(new ProvinciaDistrito(null, chókwè, gaza));
		provinciaDistritoService.criar(new ProvinciaDistrito(null, bilene, gaza));
		provinciaDistritoService.criar(new ProvinciaDistrito(null, xai_xai, gaza));


		Provincia inhambane= provinciaService.criar(new Provincia(null, "Provincia de Inhambane", "0103", "PIE", zonaSul));
		//Distritos da provincia de inhambane
		Distrito maxixe = distritoService.criar(new Distrito(null, "Distrito de Maxixe", "6574", inhambane));
		Distrito morrumbene = distritoService.criar(new Distrito(null, "Distrito de Morrumbene", "4924", inhambane));
		Distrito massinga = distritoService.criar(new Distrito(null, "Distrito de Massinga", "5892", inhambane));
		Distrito vilankulo = distritoService.criar(new Distrito(null, "Distrito de vilanculos", "1233", inhambane));

		provinciaDistritoService.criar(new ProvinciaDistrito(null, maxixe, inhambane));
		provinciaDistritoService.criar(new ProvinciaDistrito(null, morrumbene, inhambane));
		provinciaDistritoService.criar(new ProvinciaDistrito(null, massinga, inhambane));
		provinciaDistritoService.criar(new ProvinciaDistrito(null, vilankulo, inhambane));


		provinciaService.criar(new Provincia(null, "Maputo", "6578", "MPT", zonaSul));
		provinciaService.criar(new Provincia(null, "Gaza", "3452", "GZA", zonaSul));
		provinciaService.criar(new Provincia(null, "Inhambane", "1097", "INH", zonaSul));

		ZonaRegional zonaCentro = zonaRegionalService.criar(new ZonaRegional(null, "ZONA CENTRO"));
		provinciaService.criar(new Provincia(null, "Manica", "0102", "MAN", zonaCentro));
		provinciaService.criar(new Provincia(null, "Sofala", "1342", "SOF", zonaCentro));
		provinciaService.criar(new Provincia(null, "Tete", "0767", "TET", zonaCentro));
		provinciaService.criar(new Provincia(null, "Zambézia", "3453", "ZAM", zonaCentro));

		ZonaRegional zonaNorte = zonaRegionalService.criar(new ZonaRegional(null, "ZONA NORTE"));
		provinciaService.criar(new Provincia(null, "Niassa", "0104", "NIA", zonaNorte));
		provinciaService.criar(new Provincia(null, "Cabo Delgado", "0985", "CDL", zonaNorte));
		provinciaService.criar(new Provincia(null, "Nampula", "1230", "NPL", zonaNorte));
	}

	public void inicializarTipoDocumento(TipoDocumentoService tipoDocumentoService){
		tipoDocumentoService.criar(new TipoDocumentoIdentificacao(null, "Bilhete de identidade", "BI"));
		tipoDocumentoService.criar(new TipoDocumentoIdentificacao(null, "Carta de conducao", "CC"));
		tipoDocumentoService.criar(new TipoDocumentoIdentificacao(null, "Cartao de eleitor", "CE"));
		tipoDocumentoService.criar(new TipoDocumentoIdentificacao(null, "Cedula", "Ced"));
		tipoDocumentoService.criar(new TipoDocumentoIdentificacao(null, "Bolentim de nascimento", "BN"));
		tipoDocumentoService.criar(new TipoDocumentoIdentificacao(null, "Assento", "Ass"));
		tipoDocumentoService.criar(new TipoDocumentoIdentificacao(null, "Passaporte", "Pass"));
		tipoDocumentoService.criar(new TipoDocumentoIdentificacao(null, "Documento de identificacao e residencia para estrangeiro", "DIRE"));

	}

	@Bean
	CommandLineRunner run(GeneroService generoService, ZonaRegionalService zonaRegionalService,
						  ProvinciaService provinciaService, DistritoService distritoService,
						  ProvinciaDistritoService provinciaDistritoService,
						  TipoDocumentoService tipoDocumentoService) {
		return args -> {
//			Auto Runnable code (on start)
			inicializarGeneros(generoService);
			inicializarProvinciasEDistritos( provinciaService,
					 distritoService,  provinciaDistritoService,
					 zonaRegionalService);
			inicializarTipoDocumento( tipoDocumentoService);

		};
	}
}

