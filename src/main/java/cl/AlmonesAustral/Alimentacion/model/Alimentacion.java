package cl.AlmonesAustral.Alimentacion.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name= "TablaAlimentacion")
public class Alimentacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "idAlimentacion", nullable = false, length = 20)
    private int idAlimentacion;

    @Column(name = "tipoAlimentacion", nullable = false, length = 50)
    private String tipoAlimentacion;

    @Column(name = "cantidadAlimentacion", nullable = false, length = 8)
    private String cantidadAlimentacion;

    @Column(name = "vecesAlimentados", nullable = false, length = 1)
    private int vecesAlimentados;

    @Column(name = "recepcionAlertaAlgas", nullable = false, length = 10)
    private String recepcionAlertaAlgas;

    //getters y setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdAlimentacion() {
        return idAlimentacion;
    }

    public void setIdAlimentacion(int idAlimentacion) {
        this.idAlimentacion = idAlimentacion;
    }

    public String getTipoAlimentacion() {
        return tipoAlimentacion;
    }

    public void setTipoAlimentacion(String tipoAlimentacion) {
        this.tipoAlimentacion = tipoAlimentacion;
    }

    public String getCantidadAlimentacion() {
        return cantidadAlimentacion;
    }

    public void setCantidadAlimentacion(String cantidadAlimentacion) {
        this.cantidadAlimentacion = cantidadAlimentacion;
    }

    public int getVecesAlimentados() {
        return vecesAlimentados;
    }

    public void setVecesAlimentados(int vecesAlimentados) {
        this.vecesAlimentados = vecesAlimentados;
    }

    public String getRecepcionAlertaAlgas() {
        return recepcionAlertaAlgas;
    }

    public void setRecepcionAlertaAlgas(String recepcionAlertaAlgas) {
        this.recepcionAlertaAlgas = recepcionAlertaAlgas;
    }

}
