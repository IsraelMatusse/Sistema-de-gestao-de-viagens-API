package com.sgvcore.Model;

import com.sgvcore.DTOs.associacaoDTOs.AssociacaoCriarDTO;
import com.sgvcore.DTOs.contactoDTO.ContactoCriarDTO;
import com.sgvcore.DTOs.proprietarioDTOs.ProprietarioCriarDTO;
import com.sgvcore.DTOs.viagemDTO.ViagemAssociarViajanteDTO;
import com.sgvcore.DTOs.viajanteDTO.ViajanteCriarDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Contacto extends AccoesDoSistema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String msisdn;

    public Contacto(ContactoCriarDTO contactoCriarDTO) {
        this.msisdn = contactoCriarDTO.getMsisdn();
    }

    public Contacto(ViajanteCriarDTO contactoCriarDTO) {
        this.msisdn = contactoCriarDTO.getMsisdn();
    }

    public Contacto(ProprietarioCriarDTO contactoCriarDTO) {
        this.msisdn = contactoCriarDTO.getMsidsn();
    }

    public Contacto(AssociacaoCriarDTO contactoCriarDTO) {
        this.msisdn = contactoCriarDTO.getMsisdn();
    }

    public Contacto(ViagemAssociarViajanteDTO dto) {
        this.msisdn = dto.getMsisdn();
    }
}
