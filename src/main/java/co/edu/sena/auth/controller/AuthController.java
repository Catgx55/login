package co.edu.sena.auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import co.edu.sena.auth.dto.CredentialsDTO;
import co.edu.sena.auth.service.AuthService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")

public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    //POST /api/auth/register -> 201 creado - 409 si el correo ya existe
    @PostMapping("/register")
    public ResponseEntity<String> registration(@Valid @RequestBody CredentialsDTO credentials) {
        if (!authService.register(credentials)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("El correo ya está en uso.");
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuario registrado exitosamente.");
        }
    }

    //POST /api/auth/login -> 200 ok - 401 no autorizado
    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody CredentialsDTO credentials) {
        if (authService.login(credentials)) {
            return ResponseEntity.status(HttpStatus.OK).body("Inicio de sesión exitoso.");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas.");
        }
    }
    
    
}
