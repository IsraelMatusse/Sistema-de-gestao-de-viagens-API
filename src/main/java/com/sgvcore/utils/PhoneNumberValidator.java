package com.sgvcore.utils;

import com.sgvcore.enums.ContactoNacional;
import org.springframework.stereotype.Component;

@Component
public class PhoneNumberValidator {

    public boolean validarTelefoneMocambicano(String numero) {
        if (numero.length() != 9) {
            // O número de telefone mocambicano deve ter 9 dígitos
            return false;
        }
        for (ContactoNacional operadora : ContactoNacional.values()) {
            for (String prefixo : operadora.getPrefixos()) {
                if (numero.substring(0, 2).startsWith(prefixo)) {
                    return true;
                }
            }
        }
        return false;
    }
}