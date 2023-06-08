package com.sgvcore;

import com.sgvcore.Model.*;
import com.sgvcore.sevices.DistritoService;
import com.sgvcore.sevices.GeneroService;
import com.sgvcore.sevices.ProvinciaService;
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
												DistritoService distritoService){
		ZonaRegional zonaSul = zonaRegionalService.criar(new ZonaRegional(null, "ZONA SUL"));
		Provincia maputo = provinciaService.criar(new Provincia(null, "Cidade de Maputo", "0101", "MPC", zonaSul));
		// Distritos de Maputo
		Distrito kaMpfumo = distritoService.criar(new Distrito(null, "Distrito de KaMpfumo", "0958", maputo));
		Distrito nlhamankulu = distritoService.criar(new Distrito(null, "Distrito de Nlhamankulu", "0847", maputo));
		Distrito kaMaxaquene = distritoService.criar(new Distrito(null, "Distrito de KaMaxaquene", "0356", maputo));

		provinciaDistritoService.criar(new ProvinciaDistrito(null, kaMpfumo, maputo));
		provinciaDistritoService.criar(new ProvinciaDistrito(null, nlhamankulu, maputo));
		provinciaDistritoService.criar(new ProvinciaDistrito(null, kaMaxaquene, maputo));

		provinciaService.criar(new Provincia(null, "Maputo", "6578", "MPT", zonaSul));
		provinciaService.criar(new Provincia(null, "Gaza", "3452", "GZA", zonaSul));
		provinciaService.criar(new Provincia(null, "Inhambane", "1097", "INH", zonaSul));

		ZonaRegional zonaCentro = zonaRegionalService.criar(new ZonaRegional(null, "ZONA CENTRO"));
		provinciaService.criar(new Provincia(null, "Manica", "0102", "MAN", zonaCentro));
		provinciaService.criar(new Provincia(null, "Sofala", "1342", "SOF", zonaCentro));
		provinciaService.criar(new Provincia(null, "Tete", "0767", "TET", zonaCentro));
		provinciaService.criar(new Provincia(null, "Nampula", "3453", "NPL", zonaCentro));

		ZonaRegional zonaNorte = zonaRegionalService.criar(new ZonaRegional(null, "ZONA NORTE"));
		provinciaService.criar(new Provincia(null, "Niassa", "0104", "NIA", zonaNorte));
		provinciaService.criar(new Provincia(null, "Cabo Delgado", "0985", "CDL", zonaNorte));
		provinciaService.criar(new Provincia(null, "Zambézia", "1230", "ZAM", zonaNorte));
	}

	@Bean
	CommandLineRunner run(GeneroService generoService) {
		return args -> {
//			Auto Runnable code (on start)
			inicializarGeneros(generoService);
		};
	}
}

