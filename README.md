# Sistema de Gestión de Tareas

Un sistema de consola en Java para gestionar tareas con diferentes prioridades y fechas de vencimiento.

## Descripción

Esta aplicación permite a los usuarios gestionar sus tareas diarias mediante una interfaz de consola intuitiva. El sistema proporciona funcionalidades como agregar, editar y eliminar tareas, así como opciones para listar, ordenar y filtrar las mismas según diversos criterios.

## Características

- Creación de tareas con título, descripción, fecha de vencimiento y prioridad
- Edición y eliminación de tareas existentes
- Marcado de tareas como completadas/pendientes
- Ordenamiento de tareas por prioridad o fecha de vencimiento
- Filtrado de tareas según su estado (completadas/pendientes)
- Interfaz de usuario basada en menús interactivos
- Validación de entradas y manejo de excepciones

## Estructura del Proyecto
src/
├── app/
│   └── Main.java              # Clase principal con el menú interactivo
├── model/
│   └── Tarea.java             # Modelo de datos para las tareas
├── service/
│   ├── TareaService.java      # Interfaz de servicios
│   └── TareaServiceImpl.java  # Implementación de servicios
└── utils/
    ├── Globales.java          # Constantes globales
    └── excepciones/
        └── InvalidInputException.java  # Excepción personalizada
## Requisitos

- Java 8 o superior
- JDK (Java Development Kit)

## Compilación y Ejecución

Para compilar el proyecto:
javac -d bin src/**/*.java

## Guía de Uso

1. **Iniciar la aplicación**: Ejecute el programa para mostrar el menú principal.
2. **Gestionar tareas**: Use las opciones numéricas del menú para seleccionar las diferentes funcionalidades.
3. **Agregar tarea**: Ingrese título, descripción, fecha y prioridad cuando se le solicite.
4. **Editar o eliminar**: Proporcione el ID de la tarea para realizar estas operaciones.
5. **Ordenar y filtrar**: Utilice los submenús correspondientes para visualizar sus tareas de diferentes maneras.

## Contribuir

Si desea contribuir a este proyecto, por favor:
1. Haga un fork del repositorio
2. Cree una rama para su característica (`git checkout -b feature/nueva-caracteristica`)
3. Haga commit de sus cambios (`git commit -m 'Añadir nueva característica'`)
4. Haga push a la rama (`git push origin feature/nueva-caracteristica`)
5. Abra un Pull Request

