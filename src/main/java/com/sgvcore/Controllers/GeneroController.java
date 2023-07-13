package com.sgvcore.Controllers;

import com.sgvcore.Model.Genero;
import com.sgvcore.Model.ResponseAPI;
import com.sgvcore.sevices.GeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/generos")
public class GeneroController {

    @Autowired
    private GeneroService generoService;

    @GetMapping
    public ResponseEntity<ResponseAPI> listarGeneros(){
        return ResponseEntity.status(200).body(new ResponseAPI(false, "200", "Generos", generoService.listarGeneros()));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ResponseAPI> buscarGeberoPorId(@PathVariable(value = "id") Long id){
        Genero genero= generoService.buscarPorId(id);
        if(genero==null){
            return ResponseEntity.status(404).body(new ResponseAPI(false, "404", "Genero nao existe",null));
        }
        return ResponseEntity.status(200).body(new ResponseAPI(false, "200", "Cores", genero));
    }

}
