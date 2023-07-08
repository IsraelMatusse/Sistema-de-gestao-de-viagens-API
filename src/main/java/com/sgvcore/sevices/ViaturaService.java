package com.sgvcore.sevices;
import com.sgvcore.DTOs.viaturaDTOs.ViaturaRespostaDTO;
import com.sgvcore.Model.Associacao;
import com.sgvcore.Model.Rota;
import com.sgvcore.Model.Viactura;
import com.sgvcore.repository.ViacturaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ViaturaService {

    @Autowired
    private ViacturaRepo viacturaRepo;

    public Viactura criar(Viactura viactura){
        return viacturaRepo.save(viactura);
    }

    public List<ViaturaRespostaDTO> listarViacturas(){
        List<Viactura> viatura= viacturaRepo.findAll();
        return viatura.stream().map(viactura -> new ViaturaRespostaDTO(viactura)).collect(Collectors.toList());
    }
    public Viactura buscarViaturaPorId(Long id){
        return viacturaRepo.findById(id).orElse(null);
    }
    public ViaturaRespostaDTO buscarPorCodigoRes(String codigoViactura){
        Viactura viactura=viacturaRepo.findByCodigo(codigoViactura);
        if(viactura !=null){
            return new ViaturaRespostaDTO(viactura);
        }
        return null;
    }
    public Viactura buscarPorCodigo(String codigoViactura){
       return viacturaRepo.findByCodigo(codigoViactura);
    }
    public List<ViaturaRespostaDTO> buscarViaturasDaDaAssociacao(Associacao associacao){
        return viacturaRepo.findByIdAssociacao(associacao).stream().map(viactura -> new ViaturaRespostaDTO(viactura)).collect(Collectors.toList());
    }
    public List<ViaturaRespostaDTO> buscarViaturasDeUmaRota(Rota rota){
        return viacturaRepo.findByIdRota(rota).stream().map(viactura -> new ViaturaRespostaDTO(viactura)).collect(Collectors.toList());
    }
    public Long numeroViaturas(){
        return viacturaRepo.count();
    }
}
