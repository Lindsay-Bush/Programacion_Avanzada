/*
 * Ejercicio 5: Simulación de una Sala de Cine (Queue, Thread, ExecutorService, Synchronized) 

Descripción: 
Simula una sala de cine en la que varios clientes llegan al mismo tiempo para comprar 
boletos en una cola de espera. El sistema debe: 
• Agregar clientes a la cola de espera. 
• Atender clientes uno por uno en el mostrador. 
• Simular un proceso concurrente donde varias personas intentan comprar al mismo 
tiempo. 

Herramientas necesarias: 
• Queue<String> (Para manejar la cola de clientes). 
• Thread y ExecutorService (Para simular atención concurrente). 
• synchronized (Para evitar conflictos al comprar boletos). 

Objetivo del ejercicio: 
• Uso de Queue para simular una cola de espera. 
• Uso de Thread y ExecutorService para simular atención concurrente. 
• Uso de synchronized para evitar conflictos al atender clientes.
 */



//utilizamos estas dos primeras librerias para poder trabajar con colas,
//linkedlist nos ayuda a agregar y eliminar elementos 
//ese sigue el orden FIFO
import java.util.LinkedList;
import java.util.Queue;

//vamos a utilizar tambien estas dos ibrerias las cuales nos ayudaran
//con los hilos, no se crearan nuevos hilos si no que se utilizaran los 3 hilos que ya tenemos
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class CineSimulacion {
    // aqui lo que hacemos es que creamos la cola para los clientes y usamos linkedListt para que se pueda ejecutar
    private static Queue<String> colaClientes = new LinkedList<>();
    private static final Object lock = new Object(); // esto nos ayuda para que los hilos no se enrreden
    //osea que si un hilo accede a u cliente en la cola otro hilo no pueda acceder a el

    public static void main(String[] args) {
        // aqui lo que hacemos es que agregamos nombres ala cola
        // utilizamos add para que cada cliente sea representado por su nombre
        colaClientes.add("Lindsay");
        colaClientes.add("Abel");
        colaClientes.add("Stive");
        colaClientes.add("Ninsa");
        colaClientes.add("Pedro");

        // aqui lo que hacemos es que para que no se esten creando hillo es que reutilizamos los mismos 3 hilos que creamos
        //usamos ExecutorService para reutilizar los mismos 3 hilos que creamos con Executors.newFixedThreadPool
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        // aqui lo que hacemos es una simulacion de atencion al cliente
        //usamos ! vara invertir la funcion de is.Empty, 
        //is.Empty es = true lo que significa que la cola esta vacia
        while (!colaClientes.isEmpty()) {
            executorService.execute(new AtenderCliente());//
            //aqui lo que hacemos es pedirle a executorService que atienda a un cliente
            //cada tarea es representada por un objeto de la clase AtenderCliente() 
        }

        //esto lo utilizamos para "cerrar la atencion de cleintes"
        // despues de atender a todos los clintes de la cola utilizamos esto para cerrar el ExecutorService
        executorService.shutdown();
    }

    // Clase que simula la atención a un cliente
    static class AtenderCliente implements Runnable {
        @Override
        public void run() {// usamos run como punto de netrada para que se ejecute un hilo, cuando un hilo este listo atendera a un cliente
            // Tomar el siguiente cliente de la cola
            synchronized (lock) { // vamos a usar  synchronized (lock) para que los hilos no se enrreden
                if (!colaClientes.isEmpty()) {
                    //String cliente sera el nombre del cliente del cual estaria siendo atendido
                    String cliente = colaClientes.poll(); // usamos poll para tomar el primer cliente de la cola y eliminarlo
                    //si ese espacio de la cola esta vacia debolvera null y pasara al siguiente
                    System.out.println(cliente + " ha comprado su boleto.");
                }

                
            }
        }
    }
}