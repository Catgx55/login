package co.edu.sena.auth.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import co.edu.sena.auth.dto.CredentialsDTO;
import co.edu.sena.auth.model.User;
import co.edu.sena.auth.repository.UserRepository;

@Service 
public class AuthService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encriptador = new BCryptPasswordEncoder();

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //Registra un usuario nuevo. Devuelve false si el correo ya existe.
    public boolean register(CredentialsDTO credentials) {
        if (userRepository.existsByEmail(credentials.getEmail())) {
            return false;
        }
        User user = new User();
        user.setEmail(credentials.getEmail());
        //La contraseña se guarda protegida con un hash, no en texto plano.
        user.setPassword(encriptador.encode(credentials.getPassword()));
        userRepository.save(user);
        return true;
    }

    //Comprueba si las credenciales son correctas. Devuelve true si el correo existe y la contraseña coincide.
    public boolean login(CredentialsDTO credentials) {
        return userRepository.findByEmail(credentials.getEmail())
                .map(user -> encriptador.matches(credentials.getPassword(), user.getPassword()))
                .orElse(false); //Si el correo no existe, devuelve false.
    }

}
