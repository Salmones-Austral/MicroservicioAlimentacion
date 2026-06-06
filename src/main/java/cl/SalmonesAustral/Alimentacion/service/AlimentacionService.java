package cl.SalmonesAustral.Alimentacion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.SalmonesAustral.Alimentacion.exception.ResourceNotFoundException;
import cl.SalmonesAustral.Alimentacion.model.Alimentacion;
import cl.SalmonesAustral.Alimentacion.repository.AlimentacionRepository;

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
        return alimentacionRepository.findById(id).
        orElseThrow(() -> new ResourceNotFoundException("El personal no existe con id: " + id));
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
        if(!alimentacionRepository.existsById(id)){
            throw new ResourceNotFoundException("Alimentacion no existe con id: " + id);
        }
        alimentacionRepository.deleteById(id);
    }
    

}
