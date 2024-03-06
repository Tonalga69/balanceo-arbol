package org.example;


import java.util.LinkedList;
import java.util.Queue;

class Arbol {
    private Nodo raiz;

    public Arbol() {

    }


    public void insertar(int dato) {
        if (this.raiz == null) {
            this.raiz = new Nodo(dato);
        } else {
            this.insertar(this.raiz, dato);
        }
    }

    public void insertarConOrden(int value) {
        if (this.raiz == null) {
            this.raiz = new Nodo(value);
        } else {
            Nodo nodo = this.raiz;
            while (nodo != null) {
                if (value == nodo.getValue()) {
                    return;
                }
                if (value > nodo.getValue()) {
                    if (nodo.getDerecha() == null) {
                        nodo.setDerecha(new Nodo(value));
                        break;
                    } else {
                        nodo = nodo.getDerecha();
                    }
                } else {
                    if (nodo.getIzquierda() == null) {
                        nodo.setIzquierda(new Nodo(value));
                        break;
                    } else {
                        nodo = nodo.getIzquierda();
                    }
                }

                int factorEquilibrio = obtenerFactorEquilibrio(nodo);

                // Si el nodo se ha vuelto desequilibrado, hay 4 casos posibles

                // Caso Izquierda-Izquierda
                if (factorEquilibrio > 1 && value < nodo.getIzquierda().getValue()){
                    rotarDerecha(nodo);
                    return;
                }


                // Caso Derecha-Derecha
                if (factorEquilibrio < -1 && value > nodo.getDerecha().getValue())
                {
                    rotarIzquierda(nodo);
                    return;
                }


                // Caso Izquierda-Derecha
                if (factorEquilibrio > 1 && value > nodo.getIzquierda().getValue()) {
                    nodo.setIzquierda(rotarIzquierda(nodo.getIzquierda()));
                    rotarDerecha(nodo);
                    return;
                }

                // Caso Derecha-Izquierda
                if (factorEquilibrio < -1 && value < nodo.getDerecha().getValue()) {
                    nodo.setDerecha(rotarDerecha(nodo.getDerecha()));
                    rotarIzquierda(nodo);
                    return;
                }


            }
        }
    }

    Nodo rotarDerecha(Nodo y) {
        Nodo x = y.getIzquierda();
        Nodo T2 = x.getDerecha();


        x.setDerecha(y);
        y.setIzquierda(T2);


        return x;
    }

    Nodo rotarIzquierda(Nodo x) {
        Nodo y = x.getDerecha();
        Nodo T2 = y.getIzquierda();

        // Realizar la rotación
        y.setIzquierda(x);
        x.setDerecha(T2);

        // Actualizar alturas
        return y;
    }

    // Función para obtener la altura de un árbol
    int obtenerAltura(Nodo nodo) {
        if (nodo == null)
            return 0;
        return 1 + Math.max(obtenerAltura(nodo.getIzquierda()), obtenerAltura(nodo.getDerecha()));
    }

    // Función para obtener el factor de equilibrio de un nodo
    int obtenerFactorEquilibrio(Nodo nodo) {
        if (nodo == null)
            return 0;
        return obtenerAltura(nodo.getIzquierda()) - obtenerAltura(nodo.getDerecha());
    }

    private void insertar(Nodo padre, int dato) {
        if (dato > padre.getValue()) {
            if (padre.getDerecha() == null) {
                padre.setDerecha(new Nodo(dato));
            } else {
                this.insertar(padre.getDerecha(), dato);
            }
        } else {
            if (padre.getIzquierda() == null) {
                padre.setIzquierda(new Nodo(dato));
            } else {
                this.insertar(padre.getIzquierda(), dato);
            }
        }
    }

    private void preorden(Nodo n) {
        if (n != null) {
            n.imprimirDato();
            preorden(n.getIzquierda());
            preorden(n.getDerecha());
            System.out.println("__________________________");
        }

    }

    private void inorden(Nodo n) {
        if (n != null) {
            inorden(n.getIzquierda());
            n.imprimirDato();
            inorden(n.getDerecha());
            System.out.println("__________________________");
        }

    }

    void imprimirPorNiveles() {
        Queue<Nodo> cola = new LinkedList<>();
        //solo usado para imprimir por niveles facilmente
        //no me pegue
        cola.add(raiz);

        while (!cola.isEmpty()) {
            int elementosNivel = cola.size();
            for (int i = 0; i < elementosNivel; i++) {
                Nodo nodoActual = cola.poll();
                assert nodoActual != null;
                System.out.print(nodoActual.getValue() + " ");

                if (nodoActual.getIzquierda() != null)
                    cola.add(nodoActual.getIzquierda());
                if (nodoActual.getDerecha() != null)
                    cola.add(nodoActual.getDerecha());
            }
            System.out.println();
        }
    }

    private void postorden(Nodo n) {
        if (n != null) {
            postorden(n.getIzquierda());
            postorden(n.getDerecha());
            n.imprimirDato();
        }
    }

    public void preorden() {
        this.preorden(this.raiz);

    }

    public void inorden() {
        this.inorden(this.raiz);
    }

    public void inordenRight() {
        this.inordenDerecha(this.raiz);
    }

    private void inordenDerecha(Nodo raiz) {
        if (raiz != null) {
            inordenDerecha(raiz.getDerecha());
            raiz.imprimirDato();
            inordenDerecha(raiz.getIzquierda());
        }
    }

    public void postorden() {
        this.postorden(this.raiz);
    }
}

class Nodo {
    private final int dato;
    private Nodo izquierda, derecha;

    public Nodo(int dato) {
        this.dato = dato;
        this.izquierda = this.derecha = null;
    }


    public int getValue() {
        return dato;
    }

    public Nodo getIzquierda() {
        return izquierda;
    }

    public void setIzquierda(Nodo izquierda) {
        this.izquierda = izquierda;
    }

    public Nodo getDerecha() {
        return derecha;
    }

    public void setDerecha(Nodo derecha) {
        this.derecha = derecha;
    }

    public void imprimirDato() {
        System.out.println(this.getValue());
    }
}