package Librerias;

public class ArbolBinario<K extends Comparable<K>, V> {
    private Nodo raiz;

    private class Nodo {
        private K clave;
        private V valor;
        private Nodo izquierdo;
        private Nodo derecho;

        public Nodo(K clave, V valor) {
            this.clave = clave;
            this.valor = valor;
        }
    }

    public void agregar(K clave, V valor) {
        raiz = agregar(raiz, clave, valor);
    }

    private Nodo agregar(Nodo nodo, K clave, V valor) {
        if (nodo == null) {
            return new Nodo(clave, valor);
        }

        int cmp = clave.compareTo(nodo.clave);
        if (cmp < 0) {
            nodo.izquierdo = agregar(nodo.izquierdo, clave, valor);
        } else if (cmp > 0) {
            nodo.derecho = agregar(nodo.derecho, clave, valor);
        } else {
            nodo.valor = valor;
        }

        return nodo;
    }

    public V buscar(K clave) {
        Nodo nodo = buscar(raiz, clave);
        return nodo == null ? null : nodo.valor;
    }

    private Nodo buscar(Nodo nodo, K clave) {
        if (nodo == null) {
            return null;
        }

        int cmp = clave.compareTo(nodo.clave);
        if (cmp < 0) {
            return buscar(nodo.izquierdo, clave);
        } else if (cmp > 0) {
            return buscar(nodo.derecho, clave);
        } else {
            return nodo;
        }
    }

    public void eliminar(K clave) {
        raiz = eliminar(raiz, clave);
    }

    private Nodo eliminar(Nodo nodo, K clave) {
        if (nodo == null) {
            return null;
        }

        int cmp = clave.compareTo(nodo.clave);
        if (cmp < 0) {
            nodo.izquierdo = eliminar(nodo.izquierdo, clave);
        } else if (cmp > 0) {
            nodo.derecho = eliminar(nodo.derecho, clave);
        } else {
            if (nodo.izquierdo == null) {
                return nodo.derecho;
            }
            if (nodo.derecho == null) {
                return nodo.izquierdo;
            }
            Nodo temp = nodo;
            nodo = min(temp.derecho);
            nodo.derecho = eliminarMin(temp.derecho);
            nodo.izquierdo = temp.izquierdo;
        }

        return nodo;
    }

    private Nodo min(Nodo nodo) {
        if (nodo.izquierdo == null) {
            return nodo;
        } else {
            return min(nodo.izquierdo);
        }
    }

    private Nodo eliminarMin(Nodo nodo) {
        if (nodo.izquierdo == null) {
            return nodo.derecho;
        }
        nodo.izquierdo = eliminarMin(nodo.izquierdo);
        return nodo;
    }
}
