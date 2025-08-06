package gm.inventarios.controlador;

import gm.inventarios.DTO.ProductoDTO;
import gm.inventarios.excepcion.RecursoNoEncontradoExcepcion;
import gm.inventarios.mapper.ProductoMapper;
import gm.inventarios.modelo.Producto;
import gm.inventarios.servicio.ProductoService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("inventario-app")
@CrossOrigin(value = "http://localhost:4200") // Puerto por default de Angular

public class ProductoControlador {
    private static final Logger logger = LoggerFactory.getLogger(ProductoControlador.class);

    @Autowired
    private ProductoService productoServicio;

    @GetMapping("/productos")
    public List<Producto> obtenerProductos(){
        List<Producto> productos = this.productoServicio.listarProductos();
        logger.info("Productos obtenidos:");
        productos.forEach(producto -> logger.info(producto.toString()));
        return productos;
    }

    @PostMapping("/productos")
    public ResponseEntity <?> agregarProducto(@Valid  @RequestBody ProductoDTO dto){
        Producto producto = ProductoMapper.toEntity(dto);
        productoServicio.guardarProducto(producto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @GetMapping("/productos/{id}")
    public ResponseEntity<Producto> obtenerProductoPorId(
            @PathVariable int id
    ){
        Producto producto = this.productoServicio.buscarProductoPorId(id);
        if(producto !=null){
            return ResponseEntity.ok(producto);
        }else{
            throw new RecursoNoEncontradoExcepcion("No se encontro el id:"+ id);
        }
    }

    @PutMapping("productos/{id}")
    public ResponseEntity<Producto> actualizarProducto(
            @PathVariable int id,
            @RequestBody Producto productoRecibido
    ){
        Producto producto = this.productoServicio.buscarProductoPorId(id);
        producto.setDescripcion(productoRecibido.getDescripcion());
        producto.setPrecio(productoRecibido.getPrecio());
        producto.setExistencia(productoRecibido.getExistencia());
        // Guardamos la información
        this.productoServicio.guardarProducto(producto);
        return ResponseEntity.ok(producto);

    }
    @DeleteMapping("productos/{id}")
    public ResponseEntity<Producto> eliminarProducto(
            @PathVariable int id
    ){
        Producto producto = this.productoServicio.buscarProductoPorId(id);
        this.productoServicio.eliminarProducto(id);
        return ResponseEntity.ok(producto);
    }

}
