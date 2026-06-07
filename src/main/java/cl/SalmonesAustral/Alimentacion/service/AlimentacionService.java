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
    private AlimentacionRepository alimentacionRepo;
    //todo
    public List<Alimentacion> getAllAlimentacion() {
        return alimentacionRepo.findAll();
    }
    //obtener id
    public Alimentacion getIdAlimentacion(int id) {
        return alimentacionRepo.findById(id).
        orElseThrow(() -> new ResourceNotFoundException("Personal no existente con este id: " + id));
    }
    //guardar
    public Alimentacion setAlimentacion(Alimentacion alimentacion) {
        return alimentacionRepo.save(alimentacion);
    }
    //actualizar
    public Alimentacion updateAlimentacion(Alimentacion alimentacion) {
        if (!alimentacionRepo.existsById(alimentacion.getId())) {
            throw new ResourceNotFoundException("En alimentacion no existe este id: " + alimentacion.getId());
        }
        return alimentacionRepo.save(alimentacion);
    }
    //eliminar
    public void deleteIdAlimentacion(int id) {
        if(!alimentacionRepo.existsById(id)){
            throw new ResourceNotFoundException("En alimentacion no existe este id: " + id);
        }
        alimentacionRepo.deleteById(id);
    }
    

}
