import java.util.Scanner;
import java.util.Arrays;
import java.util.List;


  
 //creamos una clase privada Tarea.  
class Tareas {
    private String descripcion;//variables
    private boolean completada;

    //metodo para inicializar las variables
    public Tareas(String descripcion) {
        this.descripcion = descripcion;
        this.completada = false;
    }
    //metodos para conseguir la descripcion de la tarea
    public String getDescripcion() {
        return descripcion;
    }

    public boolean isCompletada() {
        return completada;
    }

    public void completar() {
        this.completada = true;
    }
    //metodo para sobreescribir en el resultado de la descripcion
    @Override
    public String toString() {
        return (completada ? "[X] " : "[ ] ") + descripcion;
    }
}

// Creamos una clase  Pendientes

public class Pendientes {
    private ArrayList<Tarea> tareas;
    //metodo Pendientes
    public Pendientes() {
        tareas = new ArrayList<>(); //le damos a tareas como una lista
    }
    // Metodo para agregar tareas a nuestra lista
    public void agregarTarea(String descripcion) {
        tareas.add(new Tareas(descripcion));
    }
    //Metodo para completar una tarea de la lista de tareas
    public void completarTarea(int indice) {
        if (indice >= 0 && indice < tareas.size()) {
            tareas.get(indice).completar();
        } else {
            System.out.println("Índice de tarea no válido.");
        }
    }
    //Metodo para mostrar lista de tareas por medio de un ciclo for
    public void mostrarTareas() {
        for (int i = 0; i < tareas.size(); i++) {
            System.out.println(i + ". " + tareas.get(i));
        }
    }

        
         public static void main01(String[] args) {
        Pendientes lista = new Pendientes();
        Scanner scanner = new Scanner(System.in);
        String comando;

        System.out.println("Lista de Pendientes:");//mensaje en pantalla para pedir datos
        while (true) {
            System.out.println("\nComandos: agregar [tarea], completar [número], mostrar, salir");
            comando = scanner.nextLine();
            String[] partes = comando.split(" ", 2); //arreglo en dos partes
            //switch para escoger entre una u otra opcion
            switch (partes[0]) {
                case "agregar":
                    if (partes.length > 1) {
                        lista.agregarTarea(partes[1]);
                    } else {
                        System.out.println("Descripción de tarea no proporcionada.");
                    }
                    
                case "completar":
                    if (partes.length > 1) {
                        //try/catch por cualquier error en el formato de ingreso de datos
                        try {
                            int indice = Integer.parseInt(partes[1]);
                            lista.completarTarea(indice);
                        } catch (NumberFormatException e) {
                            System.out.println("Número de tarea no válido.");
                        }
                    } else {
                        System.out.println("Número de tarea no proporcionado.");
                    }
                    break;
                case "mostrar":
                    lista.mostrarTareas();
                    break;
                case "salir":
                    System.out.println("Saliendo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Comando no reconocido.");
            }
        }
    }
}