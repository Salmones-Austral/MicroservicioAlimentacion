package cl.AlmonesAustral.Alimentacion.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.AlmonesAustral.Alimentacion.dto.*;
import cl.AlmonesAustral.Alimentacion.mapper.AlimentacionMapper;

import cl.AlmonesAustral.Alimentacion.model.Alimentacion;
import cl.AlmonesAustral.Alimentacion.service.AlimentacionService;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("api/v1/alimentacion")
public class AlimentacionControllador {

    private final AlimentacionService alimentacionService;

    public AlimentacionControllador(AlimentacionService alimentacionService) {
        this.alimentacionService = alimentacionService;
    }

    @GetMapping
    public List<Alimentacion> getAllAlimentacion() {
        return alimentacionService.getAllAlimentacion();
    }
    //crear dto
    @PostMapping
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
    }
    //Actualizar dto
    @PutMapping("/{id}")
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
    }
    

}
