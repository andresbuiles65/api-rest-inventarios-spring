package gm.inventarios.controlador;

import gm.inventarios.DTO.LibroDTO;
import gm.inventarios.servicio.LibroExternoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/api/libros")
public class LibroController {
    private final LibroExternoService libroService;

    public LibroController(LibroExternoService libroService){
        this.libroService = libroService;
    }
    @Operation(summary = "Buscar libros en OpenLibrary")
    @GetMapping("/buscar")
    public ResponseEntity<List<LibroDTO>> buscarLibros(
            @Parameter(description = "Texto de busqueda") @RequestParam String query,
            @Parameter(description = "Cantidad de resultados") @RequestParam(required = false) Integer limit,
            @Parameter(description = "Idioma preferido")@RequestParam(required = false) String lang,
            @Parameter(description = "Tipo de ordenamiento")@RequestParam(required = false) String sort
    ){
        List<LibroDTO> libros = libroService.buscarLibros(query, limit, lang, sort);
        return ResponseEntity.ok(libros);
    }

}
