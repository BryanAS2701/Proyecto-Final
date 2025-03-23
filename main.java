import java.util.Scanner;

import service.TareaService;
import service.TareaServiceImpl;
import utils.Globales;

public class main {
    private static TareaService tareaService;
    private static Scanner scanner;
    
    public static void main(String[] args) {
        tareaService = new TareaServiceImpl();
        scanner = new Scanner(System.in);
        
        boolean continuar = true;
        
        System.out.println("=== SISTEMA DE GESTIÓN DE TAREAS ===");
        
        while (continuar) {
            mostrarMenu();
            int opcion = leerOpcion();
            
            switch (opcion) {
                case 1:
                    tareaService.crearTarea();
                    break;
                case 2:
                    System.out.print("\nIngrese el ID de la tarea a editar: ");
                    try {
                        Long id = Long.parseLong(scanner.nextLine());
                        tareaService.editarTarea(id);
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Ingrese un ID válido");
                    }
                    break;
                case 3:
                    System.out.print("\nIngrese el ID de la tarea a eliminar: ");
                    try {
                        Long id = Long.parseLong(scanner.nextLine());
                        tareaService.eliminarTarea(id);
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Ingrese un ID válido");
                    }
                    break;
                case 4:
                    tareaService.listarTareas();
                    break;
                case 5:
                    System.out.print("\nIngrese el ID de la tarea a cambiar estado: ");
                    try {
                        Long id = Long.parseLong(scanner.nextLine());
                        tareaService.estadoTarea(id);
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Ingrese un ID válido");
                    }
                    break;
                case 6:
                    mostrarMenuOrdenamiento();
                    break;
                case 7:
                    mostrarMenuFiltrado();
                    break;
                case 8:
                    System.out.println("\nSaliendo del sistema...");
                    continuar = false;
                    break;
                default:
                    System.out.println("\nOpción inválida. Intente nuevamente.");
            }
            
            if (continuar) {
                System.out.print("\nPresione ENTER para continuar...");
                scanner.nextLine();
            }
        }
        
        scanner.close();
    }
    
    private static void mostrarMenu() {
        System.out.println("\n=== MENÚ PRINCIPAL ===");
        System.out.println("1. Agregar tarea");
        System.out.println("2. Editar tarea");
        System.out.println("3. Eliminar tarea");
        System.out.println("4. Listar tareas");
        System.out.println("5. Cambiar estado de tarea");
        System.out.println("6. Ordenar tareas");
        System.out.println("7. Filtrar tareas por estado");
        System.out.println("8. Salir");
        System.out.print("Seleccione una opción (1-8): ");
    }
    
    private static void mostrarMenuOrdenamiento() {
        System.out.println("\n=== ORDENAR TAREAS ===");
        System.out.println("1. Ordenar por prioridad");
        System.out.println("2. Ordenar por fecha de vencimiento");
        System.out.println("3. Volver al menú principal");
        System.out.print("Seleccione una opción (1-3): ");
        
        int opcion = leerOpcion();
        
        switch (opcion) {
            case 1:
                tareaService.ordenarPrioridad();
                break;
            case 2:
                tareaService.ordenarFechaVencimiento();
                break;
            case 3:
                break;
            default:
                System.out.println("\nOpción inválida. Volviendo al menú principal.");
        }
    }
    
    private static void mostrarMenuFiltrado() {
        System.out.println("\n=== FILTRAR TAREAS ===");
        System.out.println("1. Mostrar tareas pendientes");
        System.out.println("2. Mostrar tareas completadas");
        System.out.println("3. Volver al menú principal");
        System.out.print("Seleccione una opción (1-3): ");
        
        int opcion = leerOpcion();
        
        switch (opcion) {
            case 1:
                tareaService.filtrarEstadoTarea(Globales.ESTADO_PENDIENTE);
                break;
            case 2:
                tareaService.filtrarEstadoTarea(Globales.ESTADO_COMPLETO);
                break;
            case 3:
                break;
            default:
                System.out.println("\nOpción inválida. Volviendo al menú principal.");
        }
    }
    
    private static int leerOpcion() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1; 
        }
    }
}
