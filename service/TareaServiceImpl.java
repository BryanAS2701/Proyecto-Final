package service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import model.Tarea;
import utils.Globales;
import utils.excepciones.InvalidInputException;

public class TareaServiceImpl implements TareaService{

    private List<Tarea> tareas;
    private Scanner scanner;
    private long nextId;
    private SimpleDateFormat dateFormat;

    public TareaServiceImpl() {
        this.tareas = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        this.nextId = 1L;
        this.dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    }

    @Override
    public void crearTarea() {
        try {
            System.out.println("\n--- CREAR NUEVA TAREA ---");
            System.out.print("Título: ");
            String titulo = scanner.nextLine();
            if (titulo.trim().isEmpty()) {
                throw new InvalidInputException("El título no puede estar vacío");
            }
            
            System.out.print("Descripción: ");
            String descripcion = scanner.nextLine();
    
            System.out.print("Fecha de vencimiento (dd/MM/yyyy): ");
            String fechaStr = scanner.nextLine();
            Date fechaVencimiento;
            try {
                fechaVencimiento = dateFormat.parse(fechaStr);
            } catch (ParseException e) {
                throw new InvalidInputException("Formato de fecha inválido. Use dd/MM/yyyy");
            }
            
            System.out.println("Prioridad:");
            System.out.println("1. Baja");
            System.out.println("2. Media");
            System.out.println("3. Alta");
            System.out.print("Seleccione una opción (1-3): ");
            int opcionPrioridad = Integer.parseInt(scanner.nextLine());
            
            String prioridad;
            switch (opcionPrioridad) {
                case 1:
                    prioridad = Globales.PRIORIDAD_BAJA;
                    break;
                case 2:
                    prioridad = Globales.PRIORIDAD_MEDIA;
                    break;
                case 3:
                    prioridad = Globales.PRIORIDAD_ALTA;
                    break;
                default:
                    throw new InvalidInputException("Opción de prioridad inválida");
            }
            
            Tarea nuevaTarea = new Tarea(nextId++, titulo, descripcion, fechaVencimiento, prioridad, Globales.ESTADO_PENDIENTE);
            tareas.add(nuevaTarea);     
            System.out.println("Tarea creada exitosamente con ID: " + nuevaTarea.getId());
            
        } catch (InvalidInputException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error: Ingrese un número válido");
        }
    }

    @Override
    public Tarea editarTarea(Long id) {
        Tarea tarea = buscarPorId(id);
        
        if (tarea == null) {
            System.out.println("No se encontró ninguna tarea con el ID: " + id);
            return null;
        } 
        try {   
            System.out.print("Título actual " + tarea.getTitulo());
            String nuevoTitulo = scanner.nextLine();
            if (!nuevoTitulo.trim().isEmpty()) {
                tarea.setTitulo(nuevoTitulo);
            }
            
            System.out.print("Descripción actual " + tarea.getDescripcion());
            String nuevaDescripcion = scanner.nextLine();
            if (!nuevaDescripcion.trim().isEmpty()) {
                tarea.setDescripcion(nuevaDescripcion);
            }      
            System.out.print("Fecha de vencimiento actual " + dateFormat.format(tarea.getFechaVencimiento()) + " (dd/MM/yyyy): ");
            String nuevaFechaStr = scanner.nextLine();
            if (!nuevaFechaStr.trim().isEmpty()) {
                try {
                    Date nuevaFecha = dateFormat.parse(nuevaFechaStr);
                    tarea.setFechaVencimiento(nuevaFecha);
                } catch (ParseException e) {
                    throw new InvalidInputException("Formato de fecha inválido. Use dd/MM/yyyy");
                }
            }      
            System.out.println("Prioridad actual " + tarea.getPrioridad());
            System.out.println("1. Baja");
            System.out.println("2. Media");
            System.out.println("3. Alta");
            System.out.print("Seleccione una opción (1-3) o deje en blanco para mantener: ");
            String opcionPrioridad = scanner.nextLine();         
            if (!opcionPrioridad.trim().isEmpty()) {
                int opcion = Integer.parseInt(opcionPrioridad);
                switch (opcion) {
                    case 1:
                        tarea.setPrioridad(Globales.PRIORIDAD_BAJA);
                        break;
                    case 2:
                        tarea.setPrioridad(Globales.PRIORIDAD_MEDIA);
                        break;
                    case 3:
                        tarea.setPrioridad(Globales.PRIORIDAD_ALTA);
                        break;
                    default:
                        throw new InvalidInputException("Opción de prioridad inválida");
                }
            }           
            System.out.println("Tarea actualizada exitosamente");
            return tarea;
            
        } catch (InvalidInputException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error: Ingrese un número válido");
        }    
        return null;
    }

    @Override
    public void eliminarTarea(Long id) {
        Tarea tarea = buscarPorId(id);
        if (tarea == null) {
            System.out.println("No se encontró ninguna tarea con el ID: " + id);
            return;
        }
        System.out.println("¿Está seguro que desea eliminar la siguiente tarea?");
        mostrarDetalleTarea(tarea);
        System.out.print("Confirmar eliminación (S/N): ");
        String confirmacion = scanner.nextLine();
        if (confirmacion.equalsIgnoreCase(Globales.SI)) {
            tareas.remove(tarea);
            System.out.println("Tarea eliminada exitosamente");
        } else {
            System.out.println("Operación cancelada");
        }
    }


    @Override
    public List<Tarea> listarTareas() {
        if (tareas.isEmpty()) {
            System.out.println("No hay tareas registradas");
            return Collections.emptyList();
        }
        
        System.out.println("\n--- LISTA DE TAREAS ---");
        for (Tarea tarea : tareas) {
            mostrarDetalleTarea(tarea);
        }
        
        return tareas;
    }

    @Override
    public void estadoTarea(Long id) {
        Tarea tarea = buscarPorId(id);
        
        if (tarea == null) {
            System.out.println("No se encontró ninguna tarea con el ID: " + id);
            return;
        }

        System.out.println("Estado actual: " + tarea.getEstado());
        
        if (tarea.getEstado().equals(Globales.ESTADO_PENDIENTE)) {
            tarea.setEstado(Globales.ESTADO_COMPLETO);
            System.out.println("Tarea marcada como COMPLETADA");
        } else {
            tarea.setEstado(Globales.ESTADO_PENDIENTE);
            System.out.println("Tarea marcada como PENDIENTE");
        }
    }

    @Override
    public List<Tarea> ordenarPrioridad() {
        if (tareas.isEmpty()) {
            System.out.println("No hay tareas registradas");
            return Collections.emptyList();
        }
        
        List<Tarea> tareasOrdenadas = new ArrayList<>(tareas);

        Comparator<Tarea> comparador = (t1, t2) -> {
            int valor1 = getPrioridadValor(t1.getPrioridad());
            int valor2 = getPrioridadValor(t2.getPrioridad());
            return Integer.compare(valor2, valor1);
        };
        
        Collections.sort(tareasOrdenadas, comparador);
        
        System.out.println("\n--- TAREAS ORDENADAS POR PRIORIDAD ---");
        for (Tarea tarea : tareasOrdenadas) {
            mostrarDetalleTarea(tarea);
        }
        
        return tareasOrdenadas;
    }

    private int getPrioridadValor(String prioridad) {
        switch (prioridad) {
            case "Alta": return 3;
            case "Media": return 2;
            case "Baja": return 1;
            default: return 0;
        }
    }

    @Override
    public List<Tarea> ordenarFechaVencimiento() {
        if (tareas.isEmpty()) {
            System.out.println("No hay tareas registradas");
            return Collections.emptyList();
        }
        List<Tarea> tareasOrdenadas = new ArrayList<>(tareas);
        Collections.sort(tareasOrdenadas, Comparator.comparing(Tarea::getFechaVencimiento));
        for (Tarea tarea : tareasOrdenadas) {
            mostrarDetalleTarea(tarea);
        }   
        return tareasOrdenadas;
    }

    @Override
    public List<Tarea> filtrarEstadoTarea(String estado) {
        if (tareas.isEmpty()) {
            System.out.println("No hay tareas registradas");
            return Collections.emptyList();
        }
        List<Tarea> tareasFiltradas = tareas.stream()
                .filter(t -> t.getEstado().equals(estado))
                .collect(Collectors.toList());  
        if (tareasFiltradas.isEmpty()) {
            System.out.println("No hay tareas con el estado: " + estado);
            return Collections.emptyList();
        }
        System.out.println("\n--- TAREAS " + estado.toUpperCase() + "S ---");
        for (Tarea tarea : tareasFiltradas) {
            mostrarDetalleTarea(tarea);
        }
        return tareasFiltradas;
    }

    @Override
    public Tarea buscarPorId(Long id) {
        for (Tarea tarea : tareas) {
            if (tarea.getId() == id) {
                return tarea;
            }
        }
        return null;
    }
    private void mostrarDetalleTarea(Tarea tarea) {
        System.out.println("ID: " + tarea.getId());
        System.out.println("Título: " + tarea.getTitulo());
        System.out.println("Descripción: " + tarea.getDescripcion());
        System.out.println("Fecha de vencimiento: " + dateFormat.format(tarea.getFechaVencimiento()));
        System.out.println("Prioridad: " + tarea.getPrioridad());
        System.out.println("Estado: " + tarea.getEstado());
    }
}