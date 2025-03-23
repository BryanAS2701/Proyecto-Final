package model;

import java.util.Date;

import utils.Globales;

public class Tarea {
    private long id;
    private String titulo;
    private String descripcion;
    private Date fechaVencimiento;
    private String prioridad;
    private String estado;

    public Tarea(long id, String titulo, String descripcion, Date fechaVencimiento, String prioridad, String estado) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaVencimiento = fechaVencimiento;
        this.prioridad = prioridad;
        this.estado = Globales.ESTADO_PENDIENTE;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }
    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }
    public String getPrioridad() {
        return prioridad;
    }
    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "id= " + id + ", titulo= " + titulo + ", descripcion= " + descripcion + 
               ", fechaVencimiento= " + fechaVencimiento + ", prioridad= " + prioridad + 
               ", estado= " + estado;
    }

}
