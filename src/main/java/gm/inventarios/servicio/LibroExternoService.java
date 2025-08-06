package gm.inventarios.servicio;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import gm.inventarios.DTO.LibroDTO;
import gm.inventarios.config.WebCli;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class LibroExternoService {

    private final WebClient webClient;

    public LibroExternoService(WebClient webClient) {
        this.webClient = webClient;
    }

    public List<LibroDTO> buscarLibros(String query, Integer limit, String lang, String sort) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/search.json")
                        .queryParam("q", query)
                        .queryParam("limit", limit != null ? limit : 10)
                        .queryParamIfPresent("lang", Optional.ofNullable(lang))
                        .queryParamIfPresent("sort", Optional.ofNullable(sort))
                        .build())
                .retrieve()
                .bodyToMono(JsonNode.class)
                .map(json -> {
                    JsonNode docs = json.get("docs");
                    ObjectMapper mapper = new ObjectMapper();
                    return StreamSupport.stream(docs.spliterator(), false)
                            .map(node -> mapper.convertValue(node, LibroDTO.class))
                            .collect(Collectors.toList());
                })
                .block();
    }
}
