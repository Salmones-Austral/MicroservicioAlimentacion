package cl.AlmonesAustral.Alimentacion.repository;

import cl.AlmonesAustral.Alimentacion.model.Alimentacion;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AlimentacionRepository extends JpaRepository<Alimentacion, Integer> {
    List<Alimentacion> findByTipoIdAlimentacions(String idAlimentacion);
}
