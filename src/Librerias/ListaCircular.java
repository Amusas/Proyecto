package Librerias;
public class ListaCircular<T> {
    private class Nodo {
        T dato;
        Nodo anterior, siguiente;

        public Nodo(T dato) {
            this.dato = dato;
        }
    }

    private Nodo raiz;
    private int size;

    public ListaCircular() {
        raiz = null;
        size = 0;
    }

    public void agregar(T dato) {
        Nodo nuevo = new Nodo(dato);
        if (raiz == null) {
            nuevo.siguiente = nuevo;
            nuevo.anterior = nuevo;
            raiz = nuevo;
        } else {
            Nodo ultimo = raiz.anterior;
            nuevo.siguiente = raiz;
            nuevo.anterior = ultimo;
            raiz.anterior = nuevo;
            ultimo.siguiente = nuevo;
        }
        size++;
    }

    public void eliminar(int indice) {
        if (indice >= 0 && indice < size) {
            Nodo actual = raiz;
            for (int i = 0; i < indice; i++) {
                actual = actual.siguiente;
            }
            Nodo anterior = actual.anterior;
            Nodo siguiente = actual.siguiente;
            anterior.siguiente = siguiente;
            siguiente.anterior = anterior;
            if (indice == 0) {
                raiz = siguiente;
            }
            size--;
        }
    }

    public T buscar(int indice) {
        if (indice >= 0 && indice < size) {
            Nodo actual = raiz;
            for (int i = 0; i < indice; i++) {
                actual = actual.siguiente;
            }
            return actual.dato;
        }
        return null;
    }

    public int size() {
        return size;
    }
}
