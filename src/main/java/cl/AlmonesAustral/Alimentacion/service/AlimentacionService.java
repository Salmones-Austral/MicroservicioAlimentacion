package cl.AlmonesAustral.Alimentacion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.AlmonesAustral.Alimentacion.model.Alimentacion;
import cl.AlmonesAustral.Alimentacion.repository.AlimentacionRepository;
import cl.AlmonesAustral.Alimentacion.exception.ResourceNotFoundException;

@Service
public class AlimentacionService {
    @Autowired
    private AlimentacionRepository alimentacionRepository;
    //todo
    public List<Alimentacion> getAllAlimentacion() {
        return alimentacionRepository.findAll();
    }
    //obtener id
    public Alimentacion getIdAlimentacion(int id) {
        return alimentacionRepository.findById(id).orElse(null);
    }
    //guardar
    public Alimentacion setAlimentacion(Alimentacion alimentacion) {
        return alimentacionRepository.save(alimentacion);
    }
    //actualizar
    public Alimentacion update(Alimentacion alimentacion) {
        if (!alimentacionRepository.existsById(alimentacion.getId())) {
            throw new ResourceNotFoundException("Alimentacion no existe con id: " + alimentacion.getId());
        }
        return alimentacionRepository.save(alimentacion);
    }
    //eliminar
    public void deleteIdAlimentacion(int id) {
        alimentacionRepository.deleteById(id);
    }
    

}
