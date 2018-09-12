/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concesionario;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 *
 * @author mati
 */
public class Vehiculo implements Serializable {

    private String matricula;
    private String marca;
    private String modelo;
    private String potencia;
    private String color;
    private Double precio;
    private char[] listaLetras = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'};

    public Vehiculo(String matricula, String marca, String modelo, String potencia, String color, Double precio) {
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.potencia = potencia;
        this.color = color;
        this.precio = precio;

    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPotencia() {
        return potencia;
    }

    public void setPotencia(String potencia) {
        this.potencia = potencia;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public void writeObject(ObjectOutputStream salida) throws IOException {
        salida.writeChars(this.matricula);
        salida.writeChars(this.marca);
        salida.writeChars(this.modelo);
        salida.writeChars(this.potencia);
        salida.writeChars(this.color);
        char[] letras = this.precio.toString().toCharArray();
        String resEncriptado = "";
        for (int i = 0; i < letras.length; i++) {
            resEncriptado = resEncriptado + this.listaLetras[Integer.parseInt("" + letras[i])];
        }
        salida.writeChars(resEncriptado);
        System.out.println("Escrito con encriptación");
    }

    public void readObject(ObjectInputStream entrada) {

    }

    @Override
    public String toString() {
        return "Coche{" + "matricula=" + matricula + ", marca=" + marca + ", modelo=" + modelo + ", potencia=" + potencia + ", color=" + color + ", precio=" + precio + '}';
    }

}
