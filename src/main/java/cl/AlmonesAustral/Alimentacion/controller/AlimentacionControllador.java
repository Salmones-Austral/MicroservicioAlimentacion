package cl.AlmonesAustral.Alimentacion.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import cl.AlmonesAustral.Alimentacion.dto.*;
import cl.AlmonesAustral.Alimentacion.mapper.AlimentacionMapper;

import cl.AlmonesAustral.Alimentacion.model.Alimentacion;
import cl.AlmonesAustral.Alimentacion.service.AlimentacionService;
import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("api/v1/alimentacion")
public class AlimentacionControllador {

    private final AlimentacionService alimentacionService;
    private final WebClient webClient;

    @Autowired
    public AlimentacionControllador(AlimentacionService alimentacionService, WebClient.Builder webClientBuilder) {
        this.alimentacionService = alimentacionService;
        this.webClient = webClientBuilder.build(); 
    }
    //todo
    @GetMapping
    public ResponseEntity<List<Alimentacion>> listarCriaderos() {
        return ResponseEntity.ok(alimentacionService.getAllAlimentacion());
    }
    //eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlimentacion(@PathVariable Integer id) {
        alimentacionService.deleteIdAlimentacion(id);
        return ResponseEntity.noContent().build();
    }
    //crear
    @PostMapping
    public ResponseEntity<?> crearAlimentacion(@Valid @RequestBody CreateAlimentacionRequest CreateAlimentacion, BindingResult result) {
        
        if (result.hasErrors()) {
            Map<String, String> errores = new HashMap<>();
            result.getFieldErrors().forEach(error -> 
                errores.put(error.getField(), error.getDefaultMessage())
            );
            return new ResponseEntity<>(errores, HttpStatus.BAD_REQUEST);
        }

        // MAGIA DEL MAPPER: Convierte el DTO validado al modelo de la BD
        Alimentacion alimentacion = AlimentacionMapper.toAlimentacionCreate(CreateAlimentacion);
        Alimentacion guardar = alimentacionService.setAlimentacion(alimentacion);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(guardar);
    }
    /*  metodo antiguo
    public Alimentacion setAlimentacion(@RequestBody CreateAlimentacionRequest CreateAlimentacion) {
        Alimentacion alimentacion = AlimentacionMapper.toAlimentacionCreate(CreateAlimentacion);
        alimentacion.getId();
        alimentacion.getIdAlimentacion();
        alimentacion.getTipoAlimentacion();
        alimentacion.getCantidadAlimentacion();
        alimentacion.getVecesAlimentados();
        alimentacion.getRecepcionAlertaAlgas();
        this.alimentacionService.setIdAlimentacion(alimentacion);
        return alimentacion;
    }*/
    //Actualizar
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarCriadero(
            @PathVariable Integer id,
            @Valid @RequestBody UpdateAlimentacionRequest UpdateAlimentacion, BindingResult result) {

        if (result.hasErrors()) {
            Map<String, String> errores = new HashMap<>();
            result.getFieldErrors().forEach(error -> 
                errores.put(error.getField(), error.getDefaultMessage())
            );
            return new ResponseEntity<>(errores, HttpStatus.BAD_REQUEST);
        }

        // MAGIA DEL MAPPER: Convierte el DTO validado al modelo de la BD
        Alimentacion alimentacion = AlimentacionMapper.toAlimentacionUpdate(id, UpdateAlimentacion);
        Alimentacion actualizado = alimentacionService.update(alimentacion);

        return ResponseEntity.ok(actualizado);
    }
    /* metodo antiguo
    public Alimentacion updateAlimentacion(
        @PathVariable("id") int id,
        @RequestBody UpdateAlimentacionRequest UpdateAlimentacion) {
        Alimentacion alimentacion = AlimentacionMapper.toAlimentacionUpdate(id, UpdateAlimentacion);
        alimentacion.getId();
        alimentacion.getIdAlimentacion();
        alimentacion.getTipoAlimentacion();
        alimentacion.getCantidadAlimentacion();
        alimentacion.getVecesAlimentados();
        alimentacion.getRecepcionAlertaAlgas();
        this.alimentacionService.setIdAlimentacion(alimentacion);
        return alimentacion;
    }*/
   ////////////////////Metodos custom/////////////////////////////
    //obtener nombre de criadero
    @GetMapping("/criaderos/{nombres}")
    public ResponseEntity<List<Object>> getNombreCriadero(@RequestParam String nombre) {
        List<Object> criaderos = webClient.get()
                .uri("http://localhost:8080/api/v1/criaderos/{nombre}", nombre)
                .retrieve()
                .bodyToFlux(Object.class)
                .collectList()
                .block();
            return ResponseEntity.ok(criaderos);
    }
    //obtener nombre de jaula
    @GetMapping("/jaulas/{nombres}")
    public ResponseEntity<List<Object>> getNombreJaula(@RequestParam String nombre) {
        List<Object> jaulas = webClient.get()
                .uri("http://localhost:8081/api/v1/jaulas/{nombre}", nombre)
                .retrieve()
                .bodyToFlux(Object.class)
                .collectList()
                .block();
            return ResponseEntity.ok(jaulas);
    }
    //insertar datos con jaula
    @PostMapping("/jaulas/{nombre}/alimento")
    public ResponseEntity<Map<String, Object>> registrarAlimentoEnJaula(
        @PathVariable String nombre, 
        @RequestParam Double cantidadAlimento) {
        List<Object> datosJaula = webClient.get()
            .uri("http://localhost:8081/api/v1/jaulas/{nombre}", nombre)
            .retrieve()
            .bodyToFlux(Object.class)
            .collectList()
            .block();

        if (datosJaula == null || datosJaula.isEmpty()) {
            Map<String, Object> error = new HashMap<>();
            error.put("mensaje", "La jaula '" + nombre + "' no existe.");
            return ResponseEntity.status(404).body(error);
        }
        Map<String, Object> respuestaCombinada = new HashMap<>();
        respuestaCombinada.put("datosJaula", datosJaula.get(0)); 
        respuestaCombinada.put("nombreBuscado", nombre);
        respuestaCombinada.put("cantidadAlimentoAsignada", cantidadAlimento);
        return ResponseEntity.ok(respuestaCombinada);
    }
    
    

}
