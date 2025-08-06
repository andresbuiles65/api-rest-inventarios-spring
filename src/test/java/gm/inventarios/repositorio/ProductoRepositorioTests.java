package gm.inventarios.repositorio;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import gm.inventarios.modelo.Producto;
import jakarta.validation.constraints.AssertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;


@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest

public class ProductoRepositorioTests {

    @Autowired
    private ProductoRepositorio productoRepositorio;

    @BeforeEach
    void setup(){
        productoRepositorio.deleteAll();

    }

    @Test
    void testGuardarProducto(){
        // Given  - dado o condicion previa o configuración
        var producto1 = new Producto("Celular1",250.000,23);

        // When   - accion o el comportamiento que vamos a probar
        Producto productoGuardado = productoRepositorio.save(producto1);

        // Then   - Verificar la salida
        assertThat(productoGuardado).isNotNull();
        assertThat(productoGuardado.getIdProducto()).isGreaterThan(0);
    }
    @Test
    void testListarProducto(){
        //Given
        var producto1 = new Producto("Macbook Pro M4",6.000000,10);
        var producto2 = new Producto("Macbook Air M4",4.000000,15);
        productoRepositorio.save(producto1);
        productoRepositorio.save(producto2);

        // When
        List<Producto> listaProductos = productoRepositorio.findAll();

        // Then
        assertThat(listaProductos).isNotNull();
        assertThat(listaProductos.size()).isEqualTo(2);

    }
    @DisplayName("Test para obtener un producto por id")
    @Test
    void TestListarProductosPorId(){
        // Given
        var producto1 = new Producto("Macbook Pro 2026",6.000000,12);
        productoRepositorio.save(producto1);
        //When
        Producto findProducto = productoRepositorio.findById(producto1.getIdProducto()).orElse(null);
        //Then
        assertThat(findProducto).isNotNull();
    }

    @DisplayName("Test para actualizar un producto")
    @Test
    void ModificarProducto(){

        // Given
        var producto1 = new Producto("Teclado Logitech",2.300000,4);
        productoRepositorio.save(producto1);

        // When
        Producto findProducto = productoRepositorio.findById(producto1.getIdProducto()).orElse(null);
        findProducto.setDescripcion("Mouse");
        findProducto.setPrecio(300.000);
        findProducto.setExistencia(26);
        Producto productoActualizado = productoRepositorio.save(findProducto);

        //Then
        assertThat(productoActualizado.getDescripcion()).isEqualTo("Mouse");
        assertThat(productoActualizado.getPrecio()).isEqualTo(300.000);
        assertThat(productoActualizado.getExistencia()).isEqualTo(26);
    }

    @DisplayName("Test para eliminar un producto")
    @Test
    void EliminarProducto(){
        // Given
        var producto1 = new Producto("Teclado Logitech",2.300000,4);
        productoRepositorio.save(producto1);

        // When
        productoRepositorio.deleteById(producto1.getIdProducto());

        // Then
        Optional<Producto> productoEliminado = productoRepositorio.findById(producto1.getIdProducto());
        assertTrue(productoEliminado.isEmpty());
    }



}
