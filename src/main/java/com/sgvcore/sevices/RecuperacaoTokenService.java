package com.sgvcore.sevices;

import com.sgvcore.Model.RecuperacaoToken;
import com.sgvcore.repository.RecuperacaoTokenRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecuperacaoTokenService {
    @Autowired
    private RecuperacaoTokenRepo recuperacaoTokenRepo;

    public RecuperacaoToken tokenRecuperacao(String username) {
        return recuperacaoTokenRepo.findByActivoTrueAndUsuarioUsername(username);
    }
}