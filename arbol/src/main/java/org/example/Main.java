package org.example;

public class Main {
    public static void main(String[] args) {
        Arbol arbol = new Arbol();
        arbol.insertarConOrden(10);
        arbol.insertarConOrden(5);
        arbol.insertarConOrden(15);
        arbol.insertarConOrden(3);
        arbol.insertarConOrden(7);
        arbol.insertarConOrden(12);
        arbol.insertarConOrden(20);
        arbol.insertarConOrden(1);
        arbol.insertarConOrden(4);
        arbol.insertarConOrden(6);
        arbol.insertarConOrden(8);
        arbol.imprimirPorNiveles();

    }
}