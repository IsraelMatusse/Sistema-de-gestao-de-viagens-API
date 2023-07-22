package com.sgvcore.sevices;

import com.sgvcore.Model.ZonaRegional;
import com.sgvcore.repository.ZonaRegionalRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZonaRegionalService {
    @Autowired
    private ZonaRegionalRepo zonaRegionalRepo;
    public ZonaRegional criar(ZonaRegional model){
        return zonaRegionalRepo.save(model);
    }

    public List<ZonaRegional> listarTodos(){
        return zonaRegionalRepo.findAll();
    }

}
