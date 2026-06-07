package cl.SalmonesAustral.Alimentacion.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.SalmonesAustral.Alimentacion.client.*;
import cl.SalmonesAustral.Alimentacion.dto.*;
import cl.SalmonesAustral.Alimentacion.mapper.AlimentacionMapper;
import cl.SalmonesAustral.Alimentacion.model.Alimentacion;
import cl.SalmonesAustral.Alimentacion.service.AlimentacionService;
import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

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
    private final CriaderoClient criaderoClient;
    private final JaulaClient jaulaClient;

    public AlimentacionControllador(
        AlimentacionService alimentacionService, 
        CriaderoClient criaderoClient, 
        JaulaClient jaulaClient) {
        this.alimentacionService = alimentacionService;
        this.criaderoClient = criaderoClient;
        this.jaulaClient = jaulaClient;
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
    //filtrar por id
    @GetMapping("/{id}")
    public ResponseEntity<Alimentacion> getIdAlimentacion(@PathVariable Integer id){
        Alimentacion alimentacion = alimentacionService.getIdAlimentacion(id);
        return ResponseEntity.ok(alimentacion);      
    }
    //crear
    @PostMapping
    public ResponseEntity<?> crearAlimentacion(@Valid @RequestBody CreateAlimentacionRequest createAlimentacion, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errores = new HashMap<>();
            result.getFieldErrors().forEach(error -> 
                errores.put(error.getField(), error.getDefaultMessage())
            );
            return new ResponseEntity<>(errores, HttpStatus.BAD_REQUEST);
        }
        Alimentacion alimentacion = AlimentacionMapper.toAlimentacionCreate(createAlimentacion);
        Alimentacion guardar = alimentacionService.setAlimentacion(alimentacion);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(guardar);
    }
    //Actualizar
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarCriadero(
            @PathVariable Integer id,
            @Valid @RequestBody UpdateAlimentacionRequest updateAlimentacion, BindingResult result) {

        if (result.hasErrors()) {
            Map<String, String> errores = new HashMap<>();
            result.getFieldErrors().forEach(error -> 
                errores.put(error.getField(), error.getDefaultMessage())
            );
            return new ResponseEntity<>(errores, HttpStatus.BAD_REQUEST);
        }
        Alimentacion alimentacion = AlimentacionMapper.toAlimentacionUpdate(id, updateAlimentacion);
        Alimentacion actualizado = alimentacionService.updateAlimentacion(alimentacion);

        return ResponseEntity.ok(actualizado);
    }
   ////////////////////Metodos custom/////////////////////////////
    //obtener nombre de criadero
    @GetMapping("/criaderos/{nombre}")
    public ResponseEntity<List<Object>> getNombreCriadero(@PathVariable String nombre) {
        List<Object> criaderos = criaderoClient.obtenerCriaderosPorNombre(nombre);
        return ResponseEntity.ok(criaderos);
    }
    //obtener nombre de jaula
    @GetMapping("/jaulas/{nombre}") // Corregido de {nombres} a {nombre}
    public ResponseEntity<List<Object>> getNombreJaula(@PathVariable String nombre) {
        List<Object> jaulas = jaulaClient.obtenerJaulasPorNombre(nombre);
        return ResponseEntity.ok(jaulas);
    }
    //insertar datos con jaula
    @PostMapping("/jaulas/{nombre}/alimento")
    public ResponseEntity<Map<String, Object>> registrarAlimentoEnJaula(
        @PathVariable String nombre, 
        @RequestParam Double cantidadAlimento,
        @RequestParam String tipoAlimeno) {
        List<Object> infoJaula = jaulaClient.obtenerJaulasPorNombre(nombre);
        if (infoJaula == null || infoJaula.isEmpty()) {
            Map<String, Object> error = new HashMap<>();
            error.put("mensaje", "La jaula '" + nombre + "' no existe.");
            return ResponseEntity.status(404).body(error);
        }
        Map<String, Object> confirmacionInfo = new HashMap<>();
        confirmacionInfo.put("id", infoJaula.get(0)); 
        confirmacionInfo.put("nombreBuscado", nombre);
        confirmacionInfo.put("cantidadAlimentoAsignada", cantidadAlimento);
        confirmacionInfo.put("tipoAlimentoBrindado", tipoAlimeno);
        return ResponseEntity.ok(confirmacionInfo);
    } 
}
