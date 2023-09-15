package com.sgvcore.Controllers;

import com.sgvcore.Model.ResponseAPI;
import com.sgvcore.exceptions.ModelNotFound;
import com.sgvcore.sevices.ProvinciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/provincias")
public class ProvinciaController {

    @Autowired
    private ProvinciaService provinciaService;

    @GetMapping
    public ResponseEntity<ResponseAPI> todasProvincias() {
        return ResponseEntity.status(200).body(new ResponseAPI(true, "200", "Todas as provincias de Mocambique!", provinciaService.listarTodos()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseAPI> buscarProvinciPorId(@PathVariable(value = "id") Long id) throws ModelNotFound {
        return ResponseEntity.status(200).body(new ResponseAPI(true, "200", "Provincia com id" + id, provinciaService.buscarPorId(id)));
    }

    @GetMapping("/provincia/{codigo-provincia}")
    public ResponseEntity<ResponseAPI> buscarProvinciaPorCodigo(@PathVariable(value = "codigo-provincia") String codigoProvincia) throws ModelNotFound {
        return ResponseEntity.status(200).body(new ResponseAPI(true, "200", "Provincia com o codigo" + codigoProvincia, provinciaService.buscarProvinciaporRes(codigoProvincia)));
    }
}