package com.sgvcore.Controllers;

import com.sgvcore.DTOs.marcaDTOs.MarcaCriarDTO;
import com.sgvcore.Model.ResponseAPI;
import com.sgvcore.sevices.MarcaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/marcas")
@RequiredArgsConstructor
public class MarcaController {

    private final MarcaService marcaService;

    @GetMapping
    public ResponseEntity<ResponseAPI> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseAPI(true, "200", "Marcas do sistema", marcaService.listar()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseAPI> buscarModeloPorId(@PathVariable(value = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseAPI(true, "200", "marca de carro", marcaService));
    }

    @PostMapping("/adicionar")
    public ResponseEntity<ResponseAPI> criarUmaMarca(MarcaCriarDTO dto) {
        marcaService.criarMarca(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseAPI(true, "201", "Marca criado com sucesso", null));
    }
}
