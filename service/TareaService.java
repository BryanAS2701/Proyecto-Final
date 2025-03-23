package service;

import java.util.List;

import model.Tarea;

public interface TareaService {
    public void crearTarea();
    public Tarea editarTarea(Long id);
    public void eliminarTarea(Long id);
    public List<Tarea> listarTareas();
    public void estadoTarea(Long id);
    public List<Tarea> ordenarPrioridad();
    public List<Tarea> ordenarFechaVencimiento();
    public List<Tarea> filtrarEstadoTarea(String estado);
    public Tarea buscarPorId(Long id);
}
