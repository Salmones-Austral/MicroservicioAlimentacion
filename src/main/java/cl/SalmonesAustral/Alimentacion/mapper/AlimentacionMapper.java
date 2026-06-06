package cl.SalmonesAustral.Alimentacion.mapper;

import cl.SalmonesAustral.Alimentacion.dto.*;
import cl.SalmonesAustral.Alimentacion.model.Alimentacion;

public class AlimentacionMapper {

    public static Alimentacion toAlimentacionCreate(CreateAlimentacionRequest request) {
        Alimentacion alimentacion = new Alimentacion();
            //id
            alimentacion.setTipoAlimentacion(request.tipoAlimentacion());
            alimentacion.setCantidadAlimentacion(request.cantidadAlimentacion());
            alimentacion.setVecesAlimentados(request.vecesAlimentados());
            alimentacion.setRecepcionAlertaAlgas(request.recepcionAlertaAlgas());
            return alimentacion;
    }
    public static Alimentacion toAlimentacionUpdate(int id, UpdateAlimentacionRequest request) {
        Alimentacion alimentacion = new Alimentacion();
            alimentacion.setId(id); 
            alimentacion.setTipoAlimentacion(request.tipoAlimentacion());
            alimentacion.setCantidadAlimentacion(request.cantidadAlimentacion());
            alimentacion.setVecesAlimentados(request.vecesAlimentados());
            alimentacion.setRecepcionAlertaAlgas(request.recepcionAlertaAlgas());

            return alimentacion;
    }
}
