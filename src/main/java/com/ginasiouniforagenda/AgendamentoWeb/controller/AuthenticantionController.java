package com.ginasiouniforagenda.AgendamentoWeb.controller;

import com.ginasiouniforagenda.AgendamentoWeb.domain.user.AuthenticationDTO;
import com.ginasiouniforagenda.AgendamentoWeb.domain.user.RegisterDTO;
import com.ginasiouniforagenda.AgendamentoWeb.domain.user.User;
import com.ginasiouniforagenda.AgendamentoWeb.infra.security.TokenService;
import com.ginasiouniforagenda.AgendamentoWeb.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticantionController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository repository;

    @Autowired
    TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
            var auth = this.authenticationManager.authenticate(usernamePassword);

            var token = tokenService.generateToken((User) auth.getPrincipal()) ;

            return ResponseEntity.ok(token);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body("Erro: Login ou senha inválidos. Verifique se você está registrado.");
        }
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data){
        if(this.repository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());

        User newUser = new User(data.login(), data.email(), encryptedPassword, data.role());

        this.repository.save(newUser);

        return ResponseEntity.ok().build();
    }
}
