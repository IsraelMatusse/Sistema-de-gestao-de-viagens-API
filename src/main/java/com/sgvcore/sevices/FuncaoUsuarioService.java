package com.sgvcore.sevices;

import com.sgvcore.Model.FuncaoDoUsuario;
import com.sgvcore.Model.Usuario;
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

    public FuncaoDoUsuario criarFuncao(FuncaoDoUsuario funcaoDoUsuario){
        return funcaoUsuarioRepo.save(funcaoDoUsuario);
    }

    public List<FuncaoDoUsuario> listarFuncoes(){
        return funcaoUsuarioRepo.findAll();
    }

    public Optional<FuncaoDoUsuario> getRoleByName(String name){
        return funcaoUsuarioRepo.findByName(name);
    }

    public Optional<Usuario> addRoleToUser(String username, String rolename){
        Usuario usuario = usuarioService.buscarUsuarioPorUsername(username);
        Optional<FuncaoDoUsuario> role = getRoleByName(rolename);
        if (role.isPresent()){
            usuario.getFuncoes().add(role.get());
        }
        return Optional.ofNullable(usuarioService.atualizarUsuario(usuario));
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
