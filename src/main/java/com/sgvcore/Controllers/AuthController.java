package com.sgvcore.Controllers;



import com.sgvcore.DTOs.usuarioDTOs.UsuarioCriarDTO;
import com.sgvcore.DTOs.usuarioDTOs.UsuarioLoginDTO;
import com.sgvcore.Model.ResponseAPI;
import com.sgvcore.Model.Usuario;
import com.sgvcore.security.JWTUtils;
import com.sgvcore.security.UserServiceIMPL;
import com.sgvcore.sevices.EmailService;
import com.sgvcore.sevices.FuncaoUsuarioService;
import com.sgvcore.sevices.RecuperacaoTokenService;
import com.sgvcore.sevices.UsuarioService;
import com.sgvcore.utils.GeneratePin;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.util.*;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
@Api(value = "Api para login")
public class AuthController {
    private final UsuarioService usuarioService;
    private final UserServiceIMPL userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final JWTUtils jwtUtils;
    private final FuncaoUsuarioService funcaoUsuarioService;
    private final RecuperacaoTokenService recuperacaoTokenService;
    private final EmailService emailService;

    @PostMapping("/login")
    @ApiOperation(value = "Realiza login")
    public ResponseEntity<ResponseAPI> authenticate(@RequestBody @Valid UsuarioLoginDTO request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        final UserDetails user = userDetailsService.loadUserByUsername(request.getUsername());
        if (user != null){
            Map<String,String> response = new HashMap<>();
            response.put("access_token", jwtUtils.generateToken(user));
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseAPI(true, "200", "Login efectuado com sucesso!", response));
        }
        return ResponseEntity.status(401).body(new ResponseAPI(false, "401", "Verifique as suas credenciais!", null));
    }

    @PostMapping("/registrar")
    @ApiOperation(value = "Cdastrar novo usuario ao sistema")
    public ResponseEntity<ResponseAPI> createUser(@RequestBody @Valid UsuarioCriarDTO userDTO) throws IOException {
        if (usuarioService.usuarioExistePorUsername(userDTO.getUsername())) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new ResponseAPI(false, "422", "Já existe um usuário com esse username!", null));
        }
        Usuario usuario = new Usuario();
        BeanUtils.copyProperties(userDTO, usuario) ;
        try {
            usuarioService.criarUsuario(usuario);
            funcaoUsuarioService.adicionarFuncaoAUsuario(usuario.getUsername(), "ROLE_GUEST");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseAPI(false, "500", "Erro interno de servidor!", null));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseAPI(true, "201", "O usúario de login foi criado com sucesso!", null));
    }

    @PostMapping("/esqueci-senha")
    @ApiOperation(value = "Recuperacao de senha")
    public ResponseEntity<ResponseAPI> sendEmail(@RequestParam("email") String email) throws NoSuchAlgorithmException {
        emailService.sendSimpleMessage(email,"Recuperacao da Senha - Netline", "O seu codigo de recuperacao de senha e: " + GeneratePin.generatePin());
        return ResponseEntity.status(201).body(new ResponseAPI(true, "201", "Email de recuperação enviado com sucesso!", null));
    }

    @GetMapping("/check-token")
    @ApiOperation(value = "Verifica o token de acesso")
    public ResponseEntity<ResponseAPI> checkToken(@RequestParam("token") String token){
        Map<String, Boolean> message = new HashMap<>();
        if (!jwtUtils.isTokenExpiredOffline(token)){
            message.put("expired", false);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseAPI(true, "200", "Token Invalido", message));
        }
        message.put("expired", true);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseAPI(true, "200", "Token Valido", message));
    }

    @GetMapping("/refresh-token")
    @ApiOperation(value = "Faz o refresh do token")
    public ResponseEntity<ResponseAPI> refreshToken(HttpServletRequest request, Principal principal){
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            String accessToken = authorizationHeader.substring("Bearer ".length());
            UserDetails user = userDetailsService.loadUserByUsername(principal.getName());
            if (jwtUtils.isTokenValid(accessToken, user)){
                Map<String, String> refreshToken = new HashMap<>();
                refreshToken.put("refresh_token", jwtUtils.generateRefreshToken(user));
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseAPI(true, "200", "Token de Atualização", refreshToken));
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseAPI(false, "401", "O token inserido não é valido!", null));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseAPI(false, "400", "Authentication header wasn´t found!", null));
    }

}
