package com.sgvcore.DTOs.contactoDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactoCriarDTO {

    @NotNull(message = "O contacto nao pode ser nulo")
    @Size(min = 9, max = 9)
    private String msisdn;
}
