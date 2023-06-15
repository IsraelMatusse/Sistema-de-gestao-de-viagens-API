package com.sgvcore.Model;

import com.sgvcore.DTOs.contactoDTO.ContactoCriarDTO;
import com.sgvcore.DTOs.viajanteDTO.ViajanteCriarDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Contacto {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String msisdn;

    public Contacto(ContactoCriarDTO contactoCriarDTO){
        this.msisdn=contactoCriarDTO.getMsisdn();
    }
    public Contacto (ViajanteCriarDTO contactoCriarDTO){
        this.msisdn=contactoCriarDTO.getMsisdn();
    }

}
