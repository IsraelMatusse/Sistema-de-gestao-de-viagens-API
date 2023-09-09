package com.sgvcore.Controllers;

import com.sgvcore.DTOs.viagemDTO.ViagemAssociarViajanteDTO;
import com.sgvcore.DTOs.viagemDTO.ViagemCriarDTO;
import com.sgvcore.Model.ResponseAPI;
import com.sgvcore.Model.Viagem;
import com.sgvcore.exceptions.*;
import com.sgvcore.sevices.ViagemService;
import com.sgvcore.sevices.ViagemViajanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

@RestController
@RequestMapping("/api/viagens")
public class ViagemController {


    @Autowired
    private ViagemService viagemService;
    @Autowired
    private ViagemViajanteService viagemViajanteService;

    //lISTAR TODAS VIAGENS DO SISTEMA PAGINADAS
    @GetMapping
    public ResponseEntity<ResponseAPI> listarTodasViagensDoSistema(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "destinoViagem") String filter,
            @RequestParam(defaultValue = "desc") String order
    ) {
        //definir a ordem
        Sort.Direction direction = order.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sort = Sort.by(direction, filter);

        Page<Viagem> viagensDoSistemaPaginadas = viagemService.listarTodasViagensPaginadas(page, size, sort);
        ResponseAPI response = new ResponseAPI(true, "200", "Lista de todas viagens do sistema", viagensDoSistemaPaginadas);

        HttpHeaders headers = new HttpHeaders();
        headers.add("paginas", String.valueOf(viagensDoSistemaPaginadas.getTotalPages()));
        headers.add("quantidade", String.valueOf(viagensDoSistemaPaginadas.getTotalElements()));

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(response);
    }

    @PreAuthorize("hasRole('ROLE_ASSOCIACAO')")
    @PostMapping("/adicionar")
    public ResponseEntity<ResponseAPI> criarViagem(@RequestBody @Valid ViagemCriarDTO dto) throws NoSuchAlgorithmException, ModelNotFound, BadRequest, ContentAlreadyExists, UnprocessableEntity, ForbiddenException {
        viagemService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseAPI(true, "201", "Viagem criada com sucesso!", null));
    }

    @PreAuthorize("hasAnyRole('ROLE_ASSOCIACAO')")
    @PostMapping("/associar-viajante")
    public ResponseEntity<ResponseAPI> associarViajante(@RequestBody ViagemAssociarViajanteDTO dto) throws ModelNotFound, ContentAlreadyExists, ForbiddenException {
        viagemViajanteService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseAPI(true, "201", "Viajante associado com sucesso!", null));
    }

    @GetMapping("/{codigo-viagem}/viajantes")
    public ResponseEntity<ResponseAPI> listarViajantesPorCodigoViagem(@PathVariable(value = "codigo_viagem") String codigoViagem) throws ModelNotFound {
        Viagem viagem = viagemService.buscarPorCodigo(codigoViagem);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseAPI(true, "200", "Lista de viajantes!", viagemViajanteService.listarViajantesDeUmaViagem(viagem)));
    }

    @GetMapping("/numero-viagens")
    public ResponseEntity<ResponseAPI> numeroViagens() {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseAPI(true, "200", "Numeor de viagens do sistema!", viagemService.numeroViagens()));
    }

    @GetMapping("/{codigo-viagem}/numero-viajantes")
    public ResponseEntity<ResponseAPI> numeroDeViajantes(@PathVariable(value = "codigo_viagem") String codigoViagem) throws ModelNotFound {
        Viagem viagem = viagemService.buscarPorCodigo(codigoViagem);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseAPI(true, "200", "Numero de viajantes de uma viagem!", viagemViajanteService.numeroViagens(viagem)));
    }

    @GetMapping("/viagens-dia")
    public ResponseEntity<ResponseAPI> viagensDoDia() {
        Date saida = new Date();
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseAPI(true, "200", "Viagens dos dia!", viagemService.listarViagensPelaDataDeHoje(saida)));
    }

    @GetMapping("/hoje")
    public ResponseEntity<ResponseAPI> listarViagensDoDiaPaginada(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "destinoViagem") String filter,
            @RequestParam(defaultValue = "desc") String order
    ) {
        // Definir o objeto de ordenação com base nos parâmetros de filtro
        Sort.Direction direction = order.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sort = Sort.by(direction, filter);
        Date saida = new Date();
        Page<Viagem> viagensDoDiaPaginadas = viagemService.listarViagnsDoDiaPaginado(page, size, sort, saida);
        ResponseAPI response = new ResponseAPI(true, "200", "Viagens dos dia", viagensDoDiaPaginadas);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Paginas", String.valueOf(viagensDoDiaPaginadas.getTotalPages()));
        headers.add("quantidade", String.valueOf(viagensDoDiaPaginadas.getTotalElements()));
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(response);
    }
}

