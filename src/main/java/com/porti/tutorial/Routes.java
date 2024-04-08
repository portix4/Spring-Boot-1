package com.porti.tutorial;

import com.porti.tutorial.models.Libro;
import jakarta.websocket.server.PathParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class Routes {

    private final Logger logger = LoggerFactory.getLogger(Routes.class);

    @GetMapping("/hola")
    String miPrimeraRuta(){
        return "Hola Mundo";
    }

    @GetMapping("/libro/{id}/editorial/{editorial}")
    String leerLibro(@PathVariable int id, @PathVariable String editorial){
        return "Libro:" + id + " y editorial: " + editorial;
    }

    @GetMapping("/libroporparams")
    String libroPorParams(@RequestParam String params){
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
  String guardarLibro(@RequestBody Libro libro){
    logger.debug("Libro: {} Editorial {} ", libro.nombre, libro.editorial);

    return "Libro guardado";
  }


}
