package com.sgvcore.Controllers;

import com.sgvcore.DTOs.viajanteDTO.ViajanteCriarDTO;
import com.sgvcore.Model.ResponseAPI;
import com.sgvcore.Model.Viajante;
import com.sgvcore.exceptions.ContentAlreadyExists;
import com.sgvcore.exceptions.ModelNotFound;
import com.sgvcore.sevices.ViajanteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/viajantes")
@RequiredArgsConstructor
public class ViajanteController {

    private final ViajanteService viajanteService;

    @PostMapping("/adicionar")
    public ResponseEntity<ResponseAPI> criarViajante(@RequestBody ViajanteCriarDTO dto) throws ContentAlreadyExists, ModelNotFound {
        viajanteService.criarViajante(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseAPI(true, "201", "Viajante criado com sucesso!", null));
    }

    @GetMapping
    public ResponseEntity<ResponseAPI> ListarViajantesDoSistema(@RequestBody ViajanteCriarDTO dto) throws ContentAlreadyExists, ModelNotFound {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseAPI(true, "200", "Viajantes do sistema", viajanteService.listarViajantes()));
    }

    @GetMapping("/pag")
    public ResponseEntity<ResponseAPI> listarViajantesDoSistemaPaginado(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "nome") String filter,
            @RequestParam(defaultValue = "desc") String order
    ) {
        Sort.Direction direction = order.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sort = Sort.by(direction, filter);

        Page<Viajante> viajnatesPaginados = viajanteService.listarViajanatesDoSistemaPaginado(page, size, sort);

        HttpHeaders headers = new HttpHeaders();
        headers.add("paginas", String.valueOf(viajnatesPaginados.getTotalPages()));
        headers.add("quantidade", String.valueOf(viajnatesPaginados.getTotalElements()));

        ResponseAPI response = new ResponseAPI(true, "200", "Viajantes do sistema", viajnatesPaginados);

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(response);

    }
}
