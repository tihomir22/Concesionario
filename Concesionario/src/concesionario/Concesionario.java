/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concesionario;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author mati
 */
public class Concesionario {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException, EOFException {
        int opcion = 999;
        String eleccion;
        ArrayList<Vendedor> listaVendedores = new ArrayList<>();
        ArrayList<Vehiculo> listaVehiculos = new ArrayList<>();

        ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream("vehiculos.dat"));
        ObjectInputStream entrada = new ObjectInputStream(new FileInputStream("vehiculos.dat"));
        ObjectOutputStream salidaV = new ObjectOutputStream(new FileOutputStream("vendedores.dat"));
        ObjectInputStream entradaV = new ObjectInputStream(new FileInputStream("vendedores.dat"));
        Vehiculo v1 = new Vehiculo("34356353V", "TESLA", "S", "500KW", "NEGRO", (double) 50000);
        Vehiculo v2 = new Vehiculo("34356353V", "PAPI", "S", "500KW", "NEGRO", (double) 50000);
        Vehiculo v3 = new Vehiculo("34356353V", "BATMOBIL", "S", "500KW", "NEGRO", (double) 50000);
        Vehiculo v4 = new Vehiculo("34356353V", "BATMAAAN", "S", "500KW", "NEGRO", (double) 50000);
        Vendedor ve1 = new Vendedor("34353", "Pepe", Categoria.NOOB);
        //escribimos en el fichero
        salida.writeObject(v1);
        salida.writeObject(v2);
        salida.writeObject(v3);
        salida.writeObject(v4);
        salida.close();

        salidaV.writeObject(ve1);
        salidaV.close();
        while (opcion != 0) {
            mostrarMenu();
            Scanner teclado = new Scanner(System.in);
            opcion = teclado.nextInt();
            switch (opcion) {
                case 1:
                    listaVendedores.clear();
                    listaVehiculos.clear();
                    listaVendedores = rellenarDatosVendedor(listaVendedores);
                    System.out.println(listaVendedores.size());
                    listaVehiculos = rellenarDatosVehiculo(listaVehiculos);
                    System.out.println(listaVehiculos.size());
                    break;

                case 2:
                    System.out.println("1.- PARA DAR DE ALTA A UN VENDEDOR ");
                    System.out.println("2.- PARA DAR DE ALTA A UN VEHICULO");
                    opcion = teclado.nextInt();
                    switch (opcion) {
                        case 1:
                            Vendedor v = null;
                            System.out.println("INTRODUZCA CODIGO DEL VENDEDOR - STRING ");
                            teclado.nextLine();
                            String cod = teclado.nextLine();
                            System.out.println("INTRODUZCA NOMBRE DEL VENDEDOR - STRING");
                            String nombre = teclado.nextLine();
                            System.out.println("INTRODUZCA CATEGORIA DEL VENDEDOR : [1- NOVATO],[2- PRO],[3- EXPERTO],[4- NOOB],[5-RETRASADO],[6-ANDREU]");
                            opcion = teclado.nextInt();
                            switch (opcion) {
                                case 1:
                                    v = new Vendedor(cod, nombre, Categoria.NOVATO);
                                    break;
                                case 2:
                                    v = new Vendedor(cod, nombre, Categoria.PRO);
                                    break;
                                case 3:
                                    v = new Vendedor(cod, nombre, Categoria.EXPERTO);
                                    break;
                                case 4:
                                    v = new Vendedor(cod, nombre, Categoria.NOOB);
                                    break;
                                case 5:
                                    v = new Vendedor(cod, nombre, Categoria.RETRASADO);
                                    break;
                                case 6:
                                    v = new Vendedor(cod, nombre, Categoria.ANDREU);
                                    break;
                            }
                            if (v != null) {
                                listaVendedores.add(v);
                                System.out.println("Añadido con exito" + v.toString());
                            }
                            break;
                        case 2:
                            Vehiculo ve = null;
                            System.out.println("Introduzca matricula del vehiculo - String");
                            teclado.nextLine();
                            String matricula = teclado.nextLine();
                            System.out.println("Introduzca marca del vehiculo - String");
                            String marca = teclado.nextLine();
                            System.out.println("Introduzca modelo del vehiculo - String");
                            String modelo = teclado.nextLine();
                            System.out.println("Introduzca potencia del vehiculo - String");
                            String potencia = teclado.nextLine();
                            System.out.println("Introduzca color del vehiculo - String ");
                            String color = teclado.nextLine();
                            System.out.println("Introduzca precio del vehiculo - Double");
                            double precio = teclado.nextDouble();
                            ve = new Vehiculo(matricula, marca, modelo, potencia, color, precio);
                            listaVehiculos.add(ve);
                            break;
                        default:
                            break;
                    }
                    actualizarFicheros(listaVehiculos, listaVendedores);
                    System.out.println("Actualziado con exito");
                    break;

                case 3:

                    break;

                case 4:

                    break;
            }

        }
    }

    public static void mostrarMenu() {
        System.out.println("1.Cargar datos de los vendedores y de los vehiculos en ArrayList");
        System.out.println("2.Alta de vendedores y vehiculos");
        System.out.println("3.Realizar la venta de un coche por vendedor");
        System.out.println("4.Guardar los datos de los vendedores y de los vehiculos en Ficheros");
    }

    public static ArrayList rellenarDatosVendedor(ArrayList<Vendedor> listaV) throws FileNotFoundException, IOException, ClassNotFoundException, EOFException {
        File comprobante = new File("vendedores.dat");
        if (comprobante.exists()) {
            ObjectInputStream clienteObj = new ObjectInputStream(new FileInputStream("vendedores.dat"));
            while (true) {
                try {
                    Vendedor v1 = (Vendedor) clienteObj.readObject();
                    System.out.println(v1.toString());
                    listaV.add(v1);
                } catch (EOFException e) {
                    return listaV;
                }
            }
        } else {
            System.out.println("No hay datos de vendedor que cargar");
        }
        return listaV;
    }

    public static ArrayList rellenarDatosVehiculo(ArrayList<Vehiculo> listaVehiculo) throws FileNotFoundException, IOException, ClassNotFoundException, EOFException {
        File comprobante = new File("vehiculos.dat");
        if (comprobante.exists()) {
            ObjectInputStream clienteObj = new ObjectInputStream(new FileInputStream("vehiculos.dat"));
            while (true) {
                try {
                    Vehiculo v1 = (Vehiculo) clienteObj.readObject();
                    System.out.println(v1.toString());
                    listaVehiculo.add(v1);
                } catch (EOFException e) {
                    return listaVehiculo;
                }
            }
        } else {
            System.out.println("No hay datos de vehiculo que cargar");
        }
        return listaVehiculo;
    }

    public static void actualizarFicheros(ArrayList<Vehiculo> listaVehiculos, ArrayList<Vendedor> listaVendedores) throws FileNotFoundException, IOException {
        ObjectOutputStream salidaVehiculo = new ObjectOutputStream(new FileOutputStream("vehiculos.dat"));
        ObjectOutputStream salidaVendedor = new ObjectOutputStream(new FileOutputStream("vendedores.dat"));

        for (int i = 0; i < listaVehiculos.size(); i++) {
            salidaVehiculo.writeObject(listaVehiculos.get(i));
        }
        salidaVehiculo.close();
        for (int i = 0; i < listaVendedores.size(); i++) {
            salidaVendedor.writeObject(listaVendedores.get(i));
        }
        salidaVendedor.close();

    }
}
