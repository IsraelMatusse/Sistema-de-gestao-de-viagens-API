package com.sgvcore.sevices;

import com.sgvcore.DTOs.contactoDTO.ContactoRespostaDTO;
import com.sgvcore.Model.Contacto;
import com.sgvcore.repository.ContactoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactoService {

    @Autowired
    ContactoRepo contactoRepo;

    public Contacto criar(Contacto contacto){
    return contactoRepo.save(contacto);
    }
    public List<ContactoRespostaDTO> listarContactos(){
        return contactoRepo.findAll().stream().map(contacto -> new ContactoRespostaDTO(contacto)).collect(Collectors.toList());
    }
    public Contacto buscarContactoPorId(Long id){
        return contactoRepo.findById(id).orElse(null);
    }
    public Contacto buscarContactoPorMsisdn(String msisdn){
        return  contactoRepo.findByMsidsn(msisdn);
    }




}
