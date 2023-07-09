package com.sgvcore.Controllers;

import com.sgvcore.Model.ResponseAPI;
import com.sgvcore.sevices.CorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cores")
public class CorController {

    @Autowired
    private CorService corService;
    @GetMapping
    public ResponseEntity<ResponseAPI> listarCores(){
        return ResponseEntity.status(200).body(new ResponseAPI(false, "200", "Cores", corService.listar()));

    }
}
