import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner entrada=new Scanner(System.in);
        int opcion;
        opcion=0;
        File archivo;
        do{
            System.out.printf("%20s\n","MENÚ DE OPCIONES:");
            System.out.println("========================");
            System.out.println("1. Listar archivos");
            System.out.println("2. Crear carpeta");
            System.out.println("3. Copiar archivo");
            System.out.println("4. Mover archivo");
            System.out.println("5. Leer archivo");
            System.out.println("6. Escribir archivo");
            System.out.println("7. Eliminar archivo");
            System.out.println("8. Salir");
            System.out.print("Elige una opción:");
            opcion=entrada.nextInt();

            switch (opcion){
                case 1:
                    System.out.println("Introduce la ruta del archivo:");
                    archivo=new File(entrada.next());
                    System.out.println();
                    listarArchivos(archivo);
                    break;
                case 2:
                    crearCarpeta();
                    break;
                case 3:
                    copiarArchivo();
                    break;
                case 4:
                    moverArchivo();
                    break;
                case 5:

                    break;
                case 6:

                    break;
                case 7:

                    break;
                case 8:
                    System.out.println("Saliendo del menú...");
                    break;
                default:
                    System.out.println("Opción no válida");

            }

        }while (opcion!=8);
        entrada.close();

    }

    private static void listarArchivos(File archivo){
        Scanner entrada=new Scanner(System.in);
        int opcion;
        if (!archivo.exists()){
            System.out.println("El archivo elegido no existe.");
        }else {
            do {
                System.out.println();
                System.out.println("1. Modo detallado");
                System.out.println("2. Modo simple");
                System.out.println("3. Salir");
                System.out.print("Elige una opción:");
                opcion = entrada.nextInt();
                if (opcion == 1) {

                    if (archivo.isDirectory()) {
                        System.out.println(archivo.getName() + " es un directorio.");
                        System.out.println("Contenidos:");
                        String info = "";
                        File[] lista = archivo.listFiles();
                        for (File arch : lista) {
                            long fecha = arch.lastModified();
                            DateFormat formato = new SimpleDateFormat("dd/MM/yyyy hh:MM:ss");
                            String tiempoForm = formato.format(fecha);

                            info += "\n " + arch.getName() + " {" + (arch.canRead() ? "r" : "") + (arch.canWrite() ? "w" : "") + (arch.canExecute() ? "x" : "") + "} " + arch.length() + " bytes, " + tiempoForm;

                        }
                        System.out.println(info);
                    }
                    if (archivo.isFile()) {
                        long fecha = archivo.lastModified();
                        DateFormat formato = new SimpleDateFormat("dd/MM/yyyy hh:MM:ss");
                        String tiempoForm = formato.format(fecha);
                        System.out.println("\n " + archivo.getName() + " {" + (archivo.canRead() ? "r" : "") + (archivo.canWrite() ? "w" : "") + (archivo.canExecute() ? "x" : "") + "} " + archivo.length() + " bytes, " + tiempoForm + "]");
                    }

                }
                if (opcion == 2) {
                    if (archivo.isDirectory()) {
                        System.out.println(archivo.getName() + " es un directorio.");
                        System.out.println("Contenidos:");
                        String info = "";
                        File[] lista = archivo.listFiles();
                        for (File arch : lista) {
                            info += "\n" + arch.getName();
                        }
                        System.out.println(info);
                    }
                    if (archivo.isFile()) {
                        System.out.println("\n" + archivo.getName());
                    }

                }

            } while (opcion != 3);
        }
    }


    private static void crearCarpeta(){
        Scanner entrada=new Scanner(System.in);

        System.out.println("Introduce la ruta donde quieras crear la carpeta con el nombre de la carpeta:");
        File ruta=new File(entrada.nextLine());
        System.out.println();
        if (ruta.exists()){
            System.out.println("La carpeta ya existe.");
        }
        else if (ruta.mkdir()){
            System.out.println("La carpeta se ha creado correctamente.");
        }else {
            System.out.println("La ruta no existe por lo que no se pudo crear la carpeta.");
        }

    }


    private static void copiarArchivo(){
        Scanner entrada=new Scanner(System.in);
        System.out.println("Introduce la ruta donde está el archivo que quieres copiar:");
        File origen=new File(entrada.nextLine());
        System.out.println();
        System.out.println("Ahora introduce la ruta donde quieres guardar la copia(con el nombre del archivo incluído):");
        File destino=new File(entrada.nextLine());
        System.out.println();
        if (origen.exists() && !destino.exists()){
            try {
                FileUtils.copyFile(origen,destino);
                if (destino.exists()){
                    System.out.println("La copia se ha creado correctamente.");
                }else {
                    System.out.println("No se pudo crear la copia.");
                }
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }else if(!origen.exists()){
            System.out.println("El archivo origen no existe.");
        } else if (destino.exists()) {
            System.out.println("La ruta destino ya existe.");
        }
    }

    private static void moverArchivo(){
        Scanner entrada=new Scanner(System.in);
        System.out.println("Introduce la ruta del archivo que quieres mover:");
        File origen=new File(entrada.nextLine());
        System.out.println();
        System.out.println("Ahora introduce la ruta hacia donde la quieres mover(con el nombre del archivo incluido):");
        File destino=new File(entrada.nextLine());
        System.out.println();
        if (origen.exists() && !destino.exists()){
            try {
                FileUtils.moveFile(origen,destino);
                if (destino.exists()){
                    System.out.println("Se ha movido el archivo correctamente.");
                }else {
                    System.out.println("No se pudo mover correctamente.");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } else if (!origen.exists()) {
            System.out.println("El archivo que quieres mover no existe.");
        } else if (destino.exists()) {
            System.out.println("El archivo ya existe.");
        }
    }






}