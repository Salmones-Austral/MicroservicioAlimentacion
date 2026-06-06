package cl.SalmonesAustral.Alimentacion.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record CreateAlimentacionRequest (
    
    @Positive(message= "El id de alimentacion debe ser un numero positivo")
    int idAlimentacion,

    @NotBlank(message= "El nombre del tipo de alimentacion no puede estar vacio")
    String tipoAlimentacion,

    @NotBlank(message= "La cantidad de alimentacion no puede estar vacia")
    String cantidadAlimentacion,

    @Positive(message= "Las veces alimentados debe ser un numero positivo")
    int vecesAlimentados,
    
    @NotBlank(message= "La recepcion de alerta de algas no puede estar vacia")
    String recepcionAlertaAlgas
)
{}
