package gm.inventarios.DTO;

import io.netty.handler.codec.MessageAggregationException;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductoDTO {

    @NotBlank(message = "La descripción es obligatoria")
    private String descripcion;

    @NotNull(message = "El precio no puede ser nulo")
    @DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser mayor a cero")
    private Double precio;

    @NotNull(message = "La Existencia no puede ser nula")
    @Min(value = 0, message = "La Existencia debe ser igual o mayor a cero")
    private Integer existencia;

}
