package com.sgvcore.Controllers;

import com.sgvcore.Model.Provincia;
import com.sgvcore.Model.ResponseAPI;
import com.sgvcore.exceptions.ModelNotFound;
import com.sgvcore.sevices.DistritoService;
import com.sgvcore.sevices.ProvinciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/distritos")
public class DistritoController {
    @Autowired
    private DistritoService distritoService;
    @Autowired
    private ProvinciaService provinciaService;

    @GetMapping
    public ResponseEntity<ResponseAPI> todosDistritos(@RequestParam(name = "id") Optional<Long> id) {
        if (id.isPresent()) {
            return ResponseEntity.status(200).body(new ResponseAPI(true, "200", "Distrito com o id " + id.get() + " !", distritoService.buscarDistritoPorId(id.get())));
        }
        return ResponseEntity.status(200).body(new ResponseAPI(true, "200", "Todas os distritos de Moçambique!", distritoService.listarTodos()));
    }

    @GetMapping("/p/{id}")
    public ResponseEntity<ResponseAPI> distritosPorIdDeProvincia(@PathVariable Long id) throws ModelNotFound {
        Provincia provincia = provinciaService.buscarPorId(id);
        return ResponseEntity.status(200).body(new ResponseAPI(true, "200", "Todas os distritos da provincia de!" + provincia.getDesignacao() + " ", distritoService.listarTodosPorProvincia(provincia)));
    }

    @GetMapping("/provincia")
    public ResponseEntity<ResponseAPI> distritosPorIdDeProvincia(@RequestParam("codigo") Optional<String> codigo) throws ModelNotFound {
        if (codigo.isPresent()) {
            Provincia provincia = provinciaService.buscarProvinciaporCodigo(codigo.get());
            if (provincia == null) {
                return ResponseEntity.status(404).body(new ResponseAPI(true, "404", "Provincia com codigo: " + codigo.isPresent() + " não existe!", null));
            }
            return ResponseEntity.status(200).body(new ResponseAPI(true, "200", "Todas os distritos da provincia com o codigo: " + codigo.isPresent() + "!", distritoService.listarTodosPorProvincia(provincia)));
        }
        return ResponseEntity.status(404).body(new ResponseAPI(true, "404", "Insira os parametros", null));
    }
}