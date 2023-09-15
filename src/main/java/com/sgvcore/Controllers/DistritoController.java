package com.sgvcore.Controllers;

import com.sgvcore.Model.Provincia;
import com.sgvcore.Model.ResponseAPI;
import com.sgvcore.exceptions.ModelNotFound;
import com.sgvcore.sevices.DistritoService;
import com.sgvcore.sevices.ProvinciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/distritos")
@RequiredArgsConstructor
public class DistritoController {
    private final DistritoService distritoService;
    private final ProvinciaService provinciaService;

    @GetMapping
    public ResponseEntity<ResponseAPI> todosDistritos(@RequestParam(name = "id") Optional<Long> id) {
        if (id.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseAPI(true, "200", "Distrito com o id " + id.get() + " !", distritoService.buscarDistritoPorId(id.get())));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseAPI(true, "200", "Todas os distritos de Moçambique!", distritoService.listarTodos()));
    }

    @GetMapping("/p/{id}")
    public ResponseEntity<ResponseAPI> distritosPorIdDeProvincia(@PathVariable Long id) throws ModelNotFound {
        Provincia provincia = provinciaService.buscarPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseAPI(true, "200", "Todas os distritos da provincia de!" + provincia.getDesignacao() + " ", distritoService.listarTodosPorProvincia(provincia)));
    }

    /*@GetMapping("/p/{codigo-provincia}")
    public ResponseEntity<ResponseAPI> distritosPorcodigoProvincia(@PathVariable("codigo-provincia") String codigoProvincia) throws ModelNotFound {
        Provincia provincia = provinciaService.buscarProvinciaporCodigo(codigoProvincia);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseAPI(true, "200", "Todas os distritos da provincia de!" + provincia.getDesignacao() + " ", distritoService.listarTodosPorProvincia(provincia)));
    }


     */
    @GetMapping("/provincia")
    public ResponseEntity<ResponseAPI> distritosPorIdDeProvincia(@RequestParam("codigo") Optional<String> codigo) throws ModelNotFound {
        if (codigo.isPresent()) {
            Provincia provincia = provinciaService.buscarProvinciaporCodigo(codigo.get());
            if (provincia == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseAPI(true, "404", "Provincia com codigo: " + codigo.isPresent() + " não existe!", null));
            }
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseAPI(true, "200", "Todas os distritos da provincia com o codigo: " + codigo.isPresent() + "!", distritoService.listarTodosPorProvincia(provincia)));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseAPI(true, "404", "Insira os parametros", null));
    }

    @GetMapping("/{codigo-provincia}")
    public ResponseEntity<ResponseAPI> listarDistritosPorCodigoProvincia(@PathVariable(value = "codigo-provincia")  String codigo) throws ModelNotFound {
        Provincia provincia = provinciaService.buscarProvinciaporCodigo(codigo);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseAPI(true, "200", "Todas os distritos da provincia de!" + provincia.getDesignacao() + " ", distritoService.listarTodosPorProvincia(provincia)));

    }
}