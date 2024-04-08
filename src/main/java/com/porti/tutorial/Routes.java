package com.porti.tutorial;

import com.porti.tutorial.models.Libro;
import jakarta.websocket.server.PathParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class Routes {

    private final Logger logger = LoggerFactory.getLogger(Routes.class);

    @GetMapping("/hola")
    String miPrimeraRuta() {
        return "Hola Mundo";
    }

    @GetMapping("/libro/{id}/editorial/{editorial}")
    String leerLibro(@PathVariable int id, @PathVariable String editorial) {
        return "Libro:" + id + " y editorial: " + editorial;
    }

    @GetMapping("/libroporparams")
    String libroPorParams(@RequestParam String params) {
        return "Params " + params;
    }

    /* @PostMapping("/guardarlibro")
      String guardarLibro(@RequestBody Map<String, Object> libro){
          libro.keySet().forEach(e -> {
              logger.debug("Llave {} valor {} -> ", e, libro.get(e));
          });
          return "Libro guardado";
     }
     ESTO ES SUSTITUIDO POR EL SIGUIENTE CODIGO
     */
    @PostMapping("/guardarlibro")
    String guardarLibro(@RequestBody Libro libro) {
        logger.debug("Libro: {} Editorial {} ", libro.nombre, libro.editorial);

        return "Libro guardado";
    }

    /* @GetMapping("/saludar")
     @ResponseStatus(value = HttpStatus.ACCEPTED) // esto se refleja en postman
       String rutaConStatus(){
         logger.debug("Status");
         return "Enviando status";
     } */
    @GetMapping("/saludar")
    @ResponseStatus(value = HttpStatus.MOVED_PERMANENTLY, reason = "URL dead")
    String rutaConStatus() {
        logger.debug("Status");
        return "Enviando status";
    }

    @GetMapping("/tipo/{type}")
    public ResponseEntity<String> getBookType(@PathVariable String type){
        if (type.equals("amor"))
            return ResponseEntity.status(HttpStatus.OK).body("Libro de amor");
        else if (type.equals("horror"))
            return ResponseEntity.status(HttpStatus.OK).body("Libro de horror");
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ese tipo no existe: " + type);
    }

    @GetMapping("/error-para-esconder/{id}")
    public int getId(@PathVariable int id){
        throw new NullPointerException("La clave del usuario es "+id+". Pero vamos a proteger este error");
    }
}
