package com.sgvcore.Controllers;

import com.sgvcore.Model.ResponseAPI;
import com.sgvcore.sevices.CorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cores")
@RequiredArgsConstructor
public class CorController {

    private final CorService corService;

    @GetMapping
    public ResponseEntity<ResponseAPI> listarCores() {
        return ResponseEntity.status(200).body(new ResponseAPI(false, "200", "Cores", corService.listar()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseAPI> buscarCorPoId(@PathVariable(value = "id") Long id) {
        return ResponseEntity.status(200).body(new ResponseAPI(false, "200", "Cores", corService.buscarPorId(id)));
    }
}
