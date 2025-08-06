package gm.inventarios.service;
import gm.inventarios.modelo.Producto;
import gm.inventarios.modelo.Usuario;
import gm.inventarios.repositorio.ProductoRepositorio;
import gm.inventarios.servicio.ProductoService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;

import static org.assertj.core.api.Assertions.as;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.security.Provider;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@MockitoSettings (strictness = Strictness.LENIENT)
@ExtendWith(MockitoExtension.class)

public class ProductoServiceTest {

    private Producto producto;

    @BeforeEach
    void setup(){
        producto = new Producto("Iphone 16 pro", 3.20000, 24);
        producto.setIdProducto(1);
    }

    @Mock
    private ProductoRepositorio productoRepositorio;

    @InjectMocks
    private ProductoService productoServicio;

    @DisplayName("Test para guardar un producto")
    @Test
    void testGuardarProducto(){

        // Given
        given(productoRepositorio.findById(producto.getIdProducto()))
                .willReturn(Optional.empty());

        given(productoRepositorio.save(producto)).willReturn(producto);

        // When
        Producto productoGuardado = productoServicio.guardarProducto(producto);

        // Then
        assertThat(productoGuardado).isNotNull();
    }

//    @DisplayName("Test para guardar un producto Con Throw Exception")
//    @Test
//    void testGuardarProductoConThrowException(){
//
//        // Given
//        given(productoRepositorio.findById(producto.getIdProducto()))
//                .willReturn(Optional.of(producto));
//
//        // When
//        assertThrows(ConfigDataResourceNotFoundException .class,() -> {
//            productoServicio.guardarProducto(producto);
//        });
//
//        // Then
//        verify(productoRepositorio, never()).save(any(Producto.class));
//    }

    @DisplayName("Test para listar los productos")
    @Test
    void testListarProductos(){
        // given

        Producto producto1 = new Producto("Iphone 16 pro", 3.20000, 24);
        productoRepositorio.save(producto1);

        given(productoRepositorio.findAll()).willReturn(List.of(producto,producto1));

        //when
        List<Producto> productos = productoServicio.listarProductos();

        //then
        assertThat(productos).isNotNull();
        assertThat(productos.size()).isEqualTo(2);
    }
    @DisplayName("Test para retornar una lista vacía")
    @Test
    void testListarColeccionPorudctosVacia(){
        //Given
        Producto producto1 = new Producto("Iphone 16 pro", 3.20000, 24);
        productoRepositorio.save(producto1);

        given(productoRepositorio.findAll()).willReturn(Collections.emptyList());
        //When
        List<Producto> listaProductos = productoServicio.listarProductos();
        //Then
        assertThat(listaProductos).isEmpty();
        assertThat(listaProductos.size()).isEqualTo(0);
    }

    @DisplayName("Test para obtener un producto por ID")
    @Test
    void testObtenerProductoPorId(){
        //Given
        given(productoRepositorio.findById(1)).willReturn(Optional.of(producto));

        //When
        Producto productoGuardado = productoServicio.buscarProductoPorId(1);
        //Then
        assertThat(productoGuardado).isNotNull();
    }
    @DisplayName("Test para actualizar un producto")
    @Test
    void testActualizarProducto(){
        //Given
        given(productoRepositorio.save(producto)).willReturn(producto);
        producto.setDescripcion("Iphone 17 pro");
        producto.setPrecio(4.500000);
        //When
        Producto productoActualizado = productoServicio.guardarProducto(producto);

        //Then
        assertThat(productoActualizado.getDescripcion()).isEqualTo("Iphone 17 pro");
        assertThat(productoActualizado.getPrecio()).isEqualTo(4.500000);
    }
    @DisplayName("Test para eliminar un producto")
    @Test
    void testEliminarProducto(){
        //Given
        given(productoRepositorio.findById(1)).willReturn(Optional.of(producto));

        // When
        productoServicio.eliminarProducto(1);

        // Then
        given(productoRepositorio.findById(1)).willReturn(Optional.empty());
        Optional<Producto> resultado = productoRepositorio.findById(1);
        assertThat(resultado).isEmpty();

    }





}
