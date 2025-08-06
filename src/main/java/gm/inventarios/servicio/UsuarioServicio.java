package gm.inventarios.servicio;

import gm.inventarios.config.EncoderConfig;
import gm.inventarios.modelo.Usuario;
import gm.inventarios.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServicio implements IUsuarioServicio {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @Override
    public Usuario guardarUsuario(Usuario usuario) {
        String contraseñaEncriptada = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(contraseñaEncriptada);
        return this.usuarioRepositorio.save(usuario);
    }
}
