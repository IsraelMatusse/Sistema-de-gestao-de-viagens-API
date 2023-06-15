package com.sgvcore.DTOs.contactoDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactoCriarDTO {

    @NotNull(message = "O contacto nao pode ser nulo")
    private String msisdn;
}
