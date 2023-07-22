package com.sgvcore.sevices;

import com.sgvcore.Model.Usuario;
import com.sgvcore.exceptions.ModelNotFound;
import com.sgvcore.repository.UsuarioRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional

public class UsuarioService {
    private final UsuarioRepo usuarioRepo;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepo usuarioRepo, PasswordEncoder passwordEncoder) {
        this.usuarioRepo = usuarioRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario criarUsuario(Usuario usuario){
        usuario.setCreatedBy(usuario.getUsername());
        usuario.setUpdatedBy(usuario.getUsername());
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioRepo.save(usuario);
    }

    public Usuario atualizarUsuario(Usuario usuario){
        return usuarioRepo.save(usuario);
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepo.findAll();
    }

    public Optional<Usuario> buscarUsuarioPorId(Long id) {
        return usuarioRepo.findById(id);
    }

    public Usuario buscarUsuarioPorUsername(String username) {
        return usuarioRepo.findByUsername(username).orElseThrow(()-> new EntityNotFoundException("Não existe"));
    }

    public String buscarUsuarioOnlinePorUsername(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }


    public Usuario usuarioOnline(){
        return usuarioRepo.findByUsername(buscarUsuarioOnlinePorUsername()).orElse(null);
    }

    public Optional<Usuario> buscarUsuarioPorEmail(String email) {
        return usuarioRepo.findByEmail(email);
    }

    public boolean apagarUsuarioPorId(Long id){
        usuarioRepo.deleteById(id);
        return true;
    }

    public Boolean usuarioExistePorUsername(String username){
        return usuarioRepo.existsByUsername(username);
    }

    public Boolean usuarioExistePorEmail(String email){
        return usuarioRepo.existsByEmail(email);
    }

    public Usuario buscarMeuUsuarioOnlinePorId(Long id) throws ModelNotFound {
        Usuario usuario = usuarioRepo.findById(id).orElseThrow(()->new ModelNotFound("User com id "+id+" não foi encontrado!"));
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (usuario.getCreatedBy() == auth.getPrincipal()){
            return usuarioRepo.findById(id).orElseThrow(()->new ModelNotFound("User com id "+id+" não foi encontrado!"));
        }
        return null;
    }

    public Usuario buscarUsuarioOnline(){
        Optional<Usuario> user = usuarioRepo.findByUsername(buscarUsuarioOnlinePorUsername());
        return user.orElse(null);
    }

    public Usuario atualizarMeuUsuario(Usuario usuario) {
        Optional<Usuario> userFound = usuarioRepo.findByUsername(buscarUsuarioOnlinePorUsername());
        if (userFound.isPresent()){
            return usuarioRepo.save(usuario);
        }
        return null;
    }

    public String apagarMeuUsuario() {
        Optional<Usuario> user = usuarioRepo.findByUsername(buscarUsuarioOnlinePorUsername());
        if (user.isPresent()){
            usuarioRepo.deleteByUsername(user.get().getUsername());
            return "Apagado com sucesso!";
        }
        return "DENIED! NOT OWNER!";
    }

}
