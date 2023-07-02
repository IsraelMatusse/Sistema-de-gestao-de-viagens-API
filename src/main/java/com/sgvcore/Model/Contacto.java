package com.sgvcore.Model;

import com.sgvcore.DTOs.contactoDTO.ContactoCriarDTO;
import com.sgvcore.DTOs.proprietarioDTOs.ProprietarioCriarDTO;
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
    private String msidsn;

    public Contacto(ContactoCriarDTO contactoCriarDTO){
        this.msidsn=contactoCriarDTO.getMsisdn();
    }
    public Contacto (ViajanteCriarDTO contactoCriarDTO){
        this.msidsn=contactoCriarDTO.getMsisdn();
    }
    public Contacto (ProprietarioCriarDTO contactoCriarDTO){this.msidsn=contactoCriarDTO.getMsidsn();}
}
