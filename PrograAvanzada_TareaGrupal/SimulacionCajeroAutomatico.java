import java.util.HashMap;
import java.util.Map;

class CajeroAutomatico {
    // HashMap para gestionar las cuentas bancarias y sus saldos
    private static Map<String, Integer> cuentasBancarias = new HashMap<>();

    // Método para inicializar las cuentas con saldo
    static void inicializarCuentas() {
        cuentasBancarias.put("123456", 5000); // Cuenta con saldo inicial de 5000
        cuentasBancarias.put("789101", 3000); // Cuenta con saldo inicial de 3000
    }

    // Método sincronizado para realizar un retiro
    public static synchronized void retirarDinero(String cuenta, int cantidad) {
        // Verificamos si la cuenta existe
        if (cuentasBancarias.containsKey(cuenta)) {
            int saldoActual = cuentasBancarias.get(cuenta);
            // Verificamos si hay suficiente dinero
            if (saldoActual >= cantidad) {
                // Si hay suficiente saldo, realizamos el retiro
                saldoActual -= cantidad;
                cuentasBancarias.put(cuenta, saldoActual);
                System.out.println("Retiro de " + cantidad + " realizado correctamente. Saldo restante: " + saldoActual);
            } else {
                // Si no hay suficiente saldo
                System.out.println("No hay suficiente saldo en la cuenta " + cuenta + " para realizar el retiro de " + cantidad);
            }
        } else {
            System.out.println("La cuenta " + cuenta + " no existe.");
        }
    }
}

class Cliente extends Thread {
    private String cuenta;
    private int cantidad;

    // Constructor para cada cliente (cuenta y cantidad a retirar)
    public Cliente(String cuenta, int cantidad) {
        this.cuenta = cuenta;
        this.cantidad = cantidad;
    }

    @Override
    public void run() {
        // Simulamos el retiro de dinero
        CajeroAutomatico.retirarDinero(cuenta, cantidad);
    }
}

public class SimulacionCajeroAutomatico {
    public static void main(String[] args) {
        // Inicializamos las cuentas
        CajeroAutomatico.inicializarCuentas();

        // Creamos los clientes (hilos) con diferentes cuentas y cantidades a retirar
        Cliente cliente1 = new Cliente("123456", 1000);
        Cliente cliente2 = new Cliente("789101", 1500);
        Cliente cliente3 = new Cliente("123456", 3000);
        Cliente cliente4 = new Cliente("789101", 500);

        // Iniciamos los hilos
        cliente1.start();
        cliente2.start();
        cliente3.start();
        cliente4.start();
        
        // Esperamos a que los hilos terminen
        try {
            cliente1.join();
            cliente2.join();
            cliente3.join();
            cliente4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}