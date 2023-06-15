package com.sgvcore.DTOs.contactoDTO;

import com.sgvcore.Model.Contacto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactoRespostaDTO {

    private Long id;
    private String msisdn;

    public ContactoRespostaDTO(Contacto contacto){
        this.id=contacto.getId();
        this.msisdn= contacto.getMsisdn();
    }
}
