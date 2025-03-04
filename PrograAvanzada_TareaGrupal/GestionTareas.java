// Importamos las librerías que vamos a usar
import java.util.ArrayList; // Permite crear listas dinámicas
import java.util.List; // Interfaz para trabajar con listas de manera más flexible
import java.util.Scanner; // Para leer lo que escriba el usuario
import java.util.stream.Collectors; // Para filtrar y transformar datos fácilmente

// Clase principal donde se arma toda la vaina
public class GestionTareas {
    // Clase interna para definir cada tarea individual
    static class Tarea {
        String descripcion; // Aquí guardamos lo que hay que hacer
        boolean completada; // Esto nos dice si ya se hizo o no

        // Constructor para crear una tarea nueva con la descripción que ponga el usuario
        public Tarea(String descripcion) {
            this.descripcion = descripcion;
            this.completada = false; // Por defecto las tareas empiezan como pendientes
        }

        // Método para marcar la tarea como hecha
        public void marcarComoCompletada() {
            this.completada = true;
        }

        // Este método sirve para imprimir bonito la tarea con una X si está completada o vacío si no
        @Override
        public String toString() {
            return "[" + (completada ? "X" : " ") + "] " + descripcion;
        }
    }

    // Aquí empieza la magia del programa
    public static void main(String[] args) {
        List<Tarea> tareas = new ArrayList<>(); // Lista donde vamos a guardar todas las tareas
        Scanner scanner = new Scanner(System.in); // Para leer lo que escriba el usuario
        int opcion; // Para guardar la opción que elija el usuario

        // Menú interactivo que se repite hasta que el usuario salga
        do {
            System.out.println("\nLista de Tareas Pendientes");
            System.out.println("1. Agregar tarea");
            System.out.println("2. Marcar tarea como completada");
            System.out.println("3. Ver tareas incompletas");
            System.out.println("4. Salir");
            System.out.print("Elige una opción: ");
            opcion = scanner.nextInt(); // Leemos la opción del usuario
            scanner.nextLine(); // Limpiamos el buffer porque si no, se salta el siguiente input

            switch (opcion) {
                case 1:
                    System.out.print("Introduce la descripción de la tarea: ");
                    String descripcion = scanner.nextLine(); // Leemos lo que escribe el usuario
                    tareas.add(new Tarea(descripcion)); // Añadimos la tarea a la lista
                    System.out.println("Tarea agregada.");
                    break;
                case 2:
                    System.out.println("Lista de tareas:");
                    for (int i = 0; i < tareas.size(); i++) {
                        System.out.println(i + ". " + tareas.get(i)); // Mostramos todas las tareas con su índice
                    }
                    System.out.print("Introduce el número de la tarea a marcar como completada: ");
                    int indice = scanner.nextInt(); // Leemos el número que escribió el usuario
                    if (indice >= 0 && indice < tareas.size()) { // Verificamos que el número sea válido
                        tareas.get(indice).marcarComoCompletada(); // Marcamos la tarea como completada
                        System.out.println("Tarea marcada como completada.");
                    } else {
                        System.out.println("Índice inválido."); // Si el número no existe, le avisamos
                    }
                    break;
                case 3:
                    System.out.println("Tareas incompletas:");
                    tareas.stream()
                          .filter(t -> !t.completada) // Filtramos solo las que no están completadas
                          .forEach(System.out::println); // Las imprimimos
                    break;
                case 4:
                    System.out.println("Saliendo..."); // Mensaje de despedida
                    break;
                default:
                    System.out.println("Opción inválida. Intenta de nuevo."); // Si pone algo raro, le avisamos
            }
        } while (opcion != 4); // El bucle sigue hasta que el usuario elija salir

        scanner.close(); // Cerramos el scanner para liberar memoria
    }
}