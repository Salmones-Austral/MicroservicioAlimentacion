package cl.AlmonesAustral.Alimentacion.repository;

import cl.AlmonesAustral.Alimentacion.model.Alimentacion;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface AlimentacionRepository extends JpaRepository<Alimentacion, Integer> {
/* 
    List<Alimentacion> findByTipoIdAlimentacions(String idAlimentacion);

    @Query(value = "SELECT * FROM Alimentacion WHERE tipoAlimentacion = :tipoAlimentacion", nativeQuery = true)
    List<Alimentacion> findByTipoAlimentacion(@Param("tipoAlimentacion") String tipoAlimentacion);*/
}
