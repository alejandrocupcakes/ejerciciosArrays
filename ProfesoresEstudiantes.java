package ejercicioCristian;

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ProfesoresEstudiantes {
    ArrayList<String> ListaProfesores;
    ArrayList<String> ListEstudiantes;
    ArrayList<ArrayList<String>> ListaGeneralEstudiantes;
    public ProfesoresEstudiantes () {
        listaProfesores=new ArrayList<>();
        listaGeneralEstudiantes=new ArrayList<>();
    }
    //en esta parte el usuario realizara una serie de paso para que el código pueda seguir su flujo//
    public void iniciar() {
        int opt = 0;
        do {
            opt = Integer.parseInt(JOptionPane.showInputDialog("1. Registrar profesores\n2. Registrar estudiantes\n3. Consultar la lista de profesores y estudiantes\n4. Consultar el profesor por nombre\n5. Consultar la cantidad de estudiantes por profesor\n6. Consultar estudiante por nombre\n7. Terminar.\n"));
            switch (opt) {
                case 1:
                    registrarProfesores();
                    break;
                case 2:
                    registrarEstudiantes();
                    break;
                case 3:
                    consultarListaProfesoresYEstudiantes();
                    break;
                case 4:
                    consultaProfesorPorNombre();
                    break;
                case 5:
                    consultaCantidadEstudiantesPorProfesor();
                    break;
                case 6:
                    consultarEstudiante();
                    break;
                case 7:
                    JOptionPane.showMessageDialog(null, "Saliendo...");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción invalida, vuelva a intentar.");
            }
        } while (opt!=7);
    }
    // en esta parte permite al usuario registrar profesores y maneja posibles duplicados en la lista que ya esten//
    private void registrarProfesores() {
        System.out.println("\n<<<< Registro de Profesosres >>>>");
        int cant=Integer.parseInt(JOptionPane.showInputDialog("Ingrese la Cantidad de profresores"));
        for (int i = 0; i < cant; i++) {
            String nombreProfesor=JOptionPane.showInputDialog("nombre profesor"+(i+1));
            if (!ListaProfesores.contains(nombreProfesor))
                ListaProfesores.add(nombreProfesor);
            else
                JOptionPane.showMessageDialog(null, "El profesor ya se encuentra en la lista");
        }
    }
    // en esta parte permitirá que el usuario ingrese los estudiantes para un profesor, en donde se registrará el nombre del estudiante y evitar su duplicado//
    private void registrarEstudiantes() {
        System.out.println("\n<<<< REgistro de Estudiantes >>>>");
        for (int i = 0; i < ListaProfesores.size(); i++) {
            JOptionPane.showMessageDialog(null, "Ingrese los estudiantes para el profesor "ListaProfesores.get(i));
            listEstudiantes=new ArrayList<String>();
            int cant=Integer.parseInt(JOptionPane.showInputDialog("Cantidad de estudiantes"));
            for (int j = 0; j < cant; j++) {
                String nombreEst=JOptionPane.showInputDialog("nombre estudiante"+(j+1));
                if (!ListEstudiantes.contains(nombreEst))
                    ListEstudiantes.add(nombreEst);
                else
                    JOptionPane.showMessageDialog(null, "El estudiante ya se encuentra en la lista");
            }
            ListaGeneralEstudiantes.add(listEstudiantes);
        }
        System.out.println("Registro de estudiantes Exitoso!\n");
    }
    //en esta parte se consultará los estudiantes y profesores registrados, donde se muestre un mensaje indicando que se debe registrar al menos uno primero//
    private void consultarListaProfesoresYEstudiantes() {
        System.out.println("\n<<<< Lista de Profesores y Estudiantes >>>>");
        ArrayList<String> listaTemporal;
        if (ListaProfesores.size() > 0) {
            if (ListaGeneralEstudiantes.size() > 0) {
                for (int i = 0; i < ListaGeneralEstudiantes.size(); i++) {
                    listaTemporal=ListaGeneralEstudiantes.get(i);
                    System.out.print("Profesor: "+ListaProfesores.get(i)+" = ");
                    System.out.print("[");
                    for (int j = 0; j < ListaTemporal.size(); j++) {
                        System.out.print(ListaTemporal.get(j));
                        if (j<ListaTemporal.size()-1) {
                            System.out.print(", ");
                        }
                    }
                    System.out.print("]\n");
                }
            } else {
                JOptionPane.showMessageDialog(null, "El profesor "+ListaProfesores.get(0)+" no tiene estudiantes, hay que registrar uno primero");
            }
        } else {
            JOptionPane.showMessageDialog(null, "No hay instructores, hay que registrar uno primero");
        }
    }
    //en esta parte permite consultar la lista de estudiantes asociados a un profesor específico, si el profesor no tiene alumnos, se mostrar que el profesor no existe//
    private void consultaProfesorPorNombre() {
        System.out.println("\n<<<< Consulta de Profesor >>>>  \n");
        String nombre=JOptionPane.showInputDialog("Ingrese el nombre del profesor");
        if (ListaProfesores.contains(nombre)) {
            System.out.print("Profesor: "+nombre+" = ");
            int posicion=ListaProfesores.indexOf(nombre);
            if (posicion < listaGeneralEstudiantes.size()) {
                System.out.print("[");
                ArrayList<String> listaEstudiantesTemporal=ListaGeneralEstudiantes.get(posicion);
                for (int j = 0; j < ListaEstudiantesTemporal.size(); j++) {
                    System.out.print(ListaEstudiantesTemporal.get(j));
                    if (j<ListaEstudiantesTemporal.size()-1) {
                        System.out.print(", ");
                    }
                }
                System.out.println("]");
            } else {
                System.out.println("El profesor no tiene alumnos");
            }
        } else {
            System.out.println("No se encuentra el profesor "+nombre+"\n");
        }
    }
    //en esta parte el código proporciona la cantidad de estudiantes asociados a un profesor específico, si el profesor no tiene estudiantes, se mostrar indicado que no se encuentra al profesor//
    private void consultaCantidadEstudiantesPorProfesor() {
        System.out.println("\n<<<< Consulta cantidad de estudiantes de Profesores >>>>  \n");
        String nombre=JOptionPane.showInputDialog("Ingrese el nombre del profesor para saber la cantidad de estudiantes asociados");
        if (ListaProfesores.contains(nombre)) {
            int posicion=ListaProfesores.indexOf(nombre);
            if (posicion < ListaGeneralEstudiantes.size()) {
                ArrayList<String> listaEstudiantesTemporal=ListaGeneralEstudiantes.get(posicion);
                System.out.println("La cantidad de estudiantes asociados al profesor "+nombre+" es "+ListaEstudiantesTemporal.size());
            } else {
                System.out.println("El profesor "+nombre+" no tiene estudiantes.");
            }
        } else {
            System.out.println("No se encuentra el profesor "+nombre+"\n");
        }
    }
    // en esta parte se mostrar el estudiante que quiere consultar, y a que grupo pertenece, si el estudiante en el grupo, indicara que no existe//
    private void consultarEstudiante() {
        System.out.println("\n<<<< Consulta de Estudiante >>>>  \n");
        ArrayList<String> listTemporal;
        String nombre=JOptionPane.showInputDialog("Ingrese el nombre del estudiante a consultar");
        if (ListEstudiantes.size() > 0) {
            for (int i = 0; i < ListaGeneralEstudiantes.size(); i++) {
                listTemporal=ListaGeneralEstudiantes.get(i);
                if (ListTemporal.contains(nombre)) {
                    for (int j = 0; j < ListTemporal.size(); j++) {
                        if (ListTemporal.get(j).equalsIgnoreCase(nombre)) {
                            System.out.println("Encuentra al nombre: "+nombre+" pertenece al grupo de "+ListaProfesores.get(i));
                        }
                    }
                } else {
                    System.out.println("No se encuentra el nombre en el grupo de "+ListaProfesores.get(i));
                }
            }
        } else {
            System.out.println("No hay estudiantes, hay que ingresar uno primero.");
        }
    }
}
