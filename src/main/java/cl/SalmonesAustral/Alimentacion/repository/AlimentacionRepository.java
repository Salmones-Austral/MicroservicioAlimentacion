package cl.SalmonesAustral.Alimentacion.repository;

import cl.SalmonesAustral.Alimentacion.model.Alimentacion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlimentacionRepository extends JpaRepository<Alimentacion, Integer> {
    // Corregido: Busca correctamente por el tipo de alimentación
    List<Alimentacion> findByTipoAlimentacion(String tipoAlimentacion);
}
