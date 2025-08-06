package gm.inventarios.mapper;

import gm.inventarios.DTO.ProductoDTO;
import gm.inventarios.modelo.Producto;

public class ProductoMapper {
    public static Producto toEntity(ProductoDTO dto){
        return new Producto(dto.getDescripcion(), dto.getPrecio(),dto.getExistencia());
    }
    public static ProductoDTO toDTO(Producto producto){
        ProductoDTO dto = new ProductoDTO();
        dto.setDescripcion(producto.getDescripcion());
        dto.setPrecio(producto.getPrecio());
        dto.setExistencia(producto.getExistencia());
        return dto;
    }
}
