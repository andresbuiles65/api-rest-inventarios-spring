package gm.inventarios.controlador;


import gm.inventarios.modelo.Usuario;
import gm.inventarios.servicio.UsuarioServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("inventario-app")
@CrossOrigin(value = "http://localhost:4200")
public class UsuarioControlador {
    private static final Logger logger = LoggerFactory.getLogger(ProductoControlador.class);

    @Autowired
    private UsuarioServicio usuarioServicio;

    @PostMapping("/usuarios")
    public Usuario agregarUsuario(@RequestBody Usuario usuario){
        logger.info("Usuario a agregar: " + usuario);
        return this.usuarioServicio.guardarUsuario(usuario);
    }



}
