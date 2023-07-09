package com.sgvcore.Controllers;

import com.sgvcore.Model.Marca;
import com.sgvcore.Model.ResponseAPI;
import com.sgvcore.sevices.MarcaModeloService;
import com.sgvcore.sevices.MarcaService;
import com.sgvcore.sevices.ModeloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;

@RestController
@RequestMapping("/api/modelos")
public class ModeloController {

    @Autowired
    private ModeloService modeloService;
    @Autowired
    private MarcaService marcaService;
    @Autowired
    private MarcaModeloService marcaModeloService;

    public ResponseEntity<ResponseAPI> listar(){
        return ResponseEntity.status(200).body(new ResponseAPI(false, "200", "Modelos do sistema", modeloService.listarRes()));
    }

    @GetMapping("/{codigo_marca}/modelos")
    public ResponseEntity<ResponseAPI> buscarModelosPelaMarca(@PathVariable(value = "codigo_marca")String codigoMarca){
        Marca marca=marcaService.buscarPorCodigo(codigoMarca);
        if(marca==null){
            return ResponseEntity.status(404).body(new ResponseAPI(false, "404", "Marca nao enocntrada!",null));
        }
        return ResponseEntity.status(201).body(new ResponseAPI(false, "201", "Modelos da marca "+marca.getDesignacao()+" ", marcaModeloService.buscarModelosDeUmaMarca(marca)));
    }
}
