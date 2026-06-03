package cl.AlmonesAustral.Alimentacion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.AlmonesAustral.Alimentacion.model.Alimentacion;
import cl.AlmonesAustral.Alimentacion.repository.AlimentacionRepository;

@Service
public class AlimentacionService {
    @Autowired
    private AlimentacionRepository alimentacionRepository;
    //todo
    public List<Alimentacion> getAllAlimentacion() {
        return alimentacionRepository.findAll();
    }
    //obtener
    public Alimentacion getIdAlimentacion(int id) {
        return alimentacionRepository.findById(id).orElse(null);
    }
    //guardar
    public void setIdAlimentacion(Alimentacion alimentacion) {
        alimentacionRepository.save(alimentacion);
    }

}
