package com.sgvcore.sevices;

import com.sgvcore.Model.ProvinciaDistrito;
import com.sgvcore.repository.ProvinciaDistritoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinciaDistritoService {
    @Autowired
    private ProvinciaDistritoRepo provinciaDistritoRepo;
    public ProvinciaDistrito criar(ProvinciaDistrito provinciaDistrito){
        return provinciaDistritoRepo.save(provinciaDistrito);
    }
    public List<ProvinciaDistrito> lisarProvinciaDistrito() {
        return provinciaDistritoRepo.findAll();
    }

}
