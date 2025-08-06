package gm.inventarios.servicio;

import gm.inventarios.DTO.LoginRequestDTO;
import gm.inventarios.DTO.LoginResponseDTO;
import gm.inventarios.modelo.Usuario;
import gm.inventarios.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;

    public LoginResponseDTO login(LoginRequestDTO request){
        Usuario usuario = usuarioRepositorio.findByUsername(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        if (!passwordEncoder.matches(request.getPassword(), usuario.getPassword())){
            throw new BadCredentialsException("Contraseña incorrecta");
        }
        String token = jwtService.generateToken(usuario.getUsername());
        return new LoginResponseDTO(token, usuario.getUsername());


    }


}
