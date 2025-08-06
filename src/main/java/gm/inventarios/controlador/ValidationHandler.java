package gm.inventarios.controlador;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class ValidationHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> manejarValidaciones(MethodArgumentNotValidException ex, HttpServletRequest request){
        Map<String, Object> respuesta = new LinkedHashMap<>();
        respuesta.put("status", HttpStatus.BAD_REQUEST.value());
        respuesta.put("error", "validacion fallida");
        respuesta.put("timestamp", LocalDateTime.now());
        respuesta.put("path", request.getRequestURI());
        Map<String, String> errores = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(err -> errores.put(err.getField(), err.getDefaultMessage()));
        respuesta.put("campos", errores);
        return ResponseEntity.badRequest().body(respuesta);
    }
}
