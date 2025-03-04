import java.util.*;
import java.util.stream.Collectors;

public class Programa1 {
    private static HashMap<String, Integer> students = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int option;
                do {
            System.out.println("\nSistema de Gestión de Estudiantes");
            System.out.println("1.Registrar estudiante");
            System.out.println("2.Enseñar estudiantes");
            System.out.println("3. Buscar estudiantes con calificación >= 80");
            System.out.println("4.Eliminar estudiante");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    showStudents();
                    break;
                case 3:
                    searchHighScorers();
                    break;
                case 4:
                    removeStudent();
                    break;
                case 5:
                    System.out.println("Saliendo.....................");
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (option != 5);
    }

    private static void addStudent() {
        System.out.print("Ingrese el nombre del estudiante: ");
        String name = scanner.nextLine();
        System.out.print("Ingrese la calificación del estudiante: ");
        int grade = scanner.nextInt();
        scanner.nextLine();
        students.put(name, grade);
        System.out.println("Estudiante registrado exitosamente.");
    }

    private static void showStudents() {
        if (students.isEmpty()) {
            System.out.println("No hay estudiantes registrados.");
        } else {
            System.out.println("Lista de estudiantes:");
            students.forEach((name, grade) -> System.out.println(name + ": " + grade));
        }
    }

    private static void searchHighScorers() {
        List<String> highScorers = students.entrySet().stream()
                .filter(entry -> entry.getValue() >= 80)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        if (highScorers.isEmpty()) {
            System.out.println("error mi dog, no hay estudiantes con calificación mayor o igual a 80.");
        } else {
            System.out.println("Estudiantes con calificación >= 80:");
            highScorers.forEach(System.out::println);
        }
    }

    private static void removeStudent() {
        System.out.print("Ingrese el nombre del estudiante a eliminar: ");
        String name = scanner.nextLine();
        if (students.containsKey(name)) {
            students.remove(name);
            System.out.println("Estudiante eliminado exitosamente.");
        } else {
            System.out.println("Estudiante no encontrado.");
        }
    }
}