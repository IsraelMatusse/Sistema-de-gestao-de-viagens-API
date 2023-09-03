package com.sgvcore.sevices;

import com.sgvcore.Model.FuncaoDoUsuario;
import com.sgvcore.Model.Usuario;
import com.sgvcore.exceptions.ModelNotFound;
import com.sgvcore.repository.FuncaoUsuarioRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class FuncaoUsuarioService {

    private final FuncaoUsuarioRepo funcaoUsuarioRepo;
    private final UsuarioService usuarioService;

    public FuncaoUsuarioService(FuncaoUsuarioRepo funcaoUsuarioRepo, UsuarioService usuarioService) {
        this.funcaoUsuarioRepo = funcaoUsuarioRepo;
        this.usuarioService = usuarioService;
    }
    public FuncaoDoUsuario getRoleByName(String name) throws ModelNotFound {
        return funcaoUsuarioRepo.findByName(name).orElseThrow(() -> new ModelNotFound("A funcao de usuario nao foi encontrada"));
    }
    public Optional<Usuario> adicionarFuncaoAUsuario(String username, String rolename) throws ModelNotFound {
        Usuario usuario = usuarioService.buscarUsuarioPorUsername(username);
        FuncaoDoUsuario role = getRoleByName(rolename);
        usuario.getFuncoes().add(role);
        return Optional.ofNullable(usuarioService.atualizarUsuario(usuario));
    }


    public FuncaoDoUsuario criarFuncao(FuncaoDoUsuario funcaoDoUsuario){
        return funcaoUsuarioRepo.save(funcaoDoUsuario);
    }

    public List<FuncaoDoUsuario> listarFuncoes(){
        return funcaoUsuarioRepo.findAll();
    }


    public FuncaoDoUsuario updateRole(FuncaoDoUsuario item){
        return funcaoUsuarioRepo.save(item);
    }

    public Optional<FuncaoDoUsuario> getSpecificRole(Long id) {
        return funcaoUsuarioRepo.findById(id);
    }

    public String deleteRole(Long id){
        funcaoUsuarioRepo.deleteById(id);
        return "Deleted Successfully";
    }
}
