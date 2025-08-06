package gm.inventarios.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LibroDTO {
    private String title;
    private List<String> author_name;
    private Integer first_publish_year;
    private List<String> language;
}
