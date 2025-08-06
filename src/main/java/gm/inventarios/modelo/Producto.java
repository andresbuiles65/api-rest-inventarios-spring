package gm.inventarios.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.web.WebProperties;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Producto {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column (name= "id_producto")
    private Integer idProducto;

    @Column (name= "descripcion", unique = true)
    private String descripcion;

    @Column (name= "precio")
    private Double precio;

    @Column (name= "existencia")
    private Integer existencia;

    public Producto(String descripcion, Double precio, Integer existencia){
        this.descripcion = descripcion;
        this.precio = precio;
        this.existencia = existencia;
    }
}
