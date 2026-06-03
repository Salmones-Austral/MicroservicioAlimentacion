package cl.AlmonesAustral.Alimentacion.mapper;

import cl.AlmonesAustral.Alimentacion.dto.*;
import cl.AlmonesAustral.Alimentacion.model.Alimentacion;

public class AlimentacionMapper {

    public static Alimentacion toAlimentacionCreate(CreateAlimentacionRequest request) {
        Alimentacion alimentacion = new Alimentacion();
            //id
            alimentacion.setIdAlimentacion(request.idAlimentacion());
            alimentacion.setTipoAlimentacion(request.tipoAlimentacion());
            alimentacion.setCantidadAlimentacion(request.cantidadAlimentacion());
            alimentacion.setVecesAlimentados(request.vecesAlimentados());
            alimentacion.setRecepcionAlertaAlgas(request.recepcionAlertaAlgas());
            return alimentacion;
    }

    public static Alimentacion toAlimentacionUpdate(int id, UpdateAlimentacionRequest request) {
        Alimentacion alimentacion = new Alimentacion();
            //id de el endpoint
            alimentacion.setIdAlimentacion(request.idAlimentacion());
            alimentacion.setTipoAlimentacion(request.tipoAlimentacion());
            alimentacion.setCantidadAlimentacion(request.cantidadAlimentacion());
            alimentacion.setVecesAlimentados(request.vecesAlimentados());
            alimentacion.setRecepcionAlertaAlgas(request.recepcionAlertaAlgas());
            return alimentacion;
    }
}
