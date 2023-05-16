package Librerias;

import java.io.Serializable;

public class ArbolBinario<K extends Comparable<K>, V> implements Serializable {
    private Nodo raiz;
    private int len;

    private class Nodo implements Serializable{
        private K clave;
        private V valor;
        private Nodo izquierdo;
        private Nodo derecho;

        public Nodo(K clave, V valor) {
            this.clave = clave;
            this.valor = valor;
        }

        public K getClave() {
            return clave;
        }

        public void setClave(K clave) {
            this.clave = clave;
        }

        public V getValor() {
            return valor;
        }

        public void setValor(V valor) {
            this.valor = valor;
        }

        public Nodo getIzquierdo() {
            return izquierdo;
        }

        public void setIzquierdo(Nodo izquierdo) {
            this.izquierdo = izquierdo;
        }

        public Nodo getDerecho() {
            return derecho;
        }

        public void setDerecho(Nodo derecho) {
            this.derecho = derecho;
        }
    }

    public void agregar(K clave, V valor) {
        raiz = agregar(raiz, clave, valor);
    }

    private Nodo agregar(Nodo nodo, K clave, V valor) {
        if (nodo == null) {
            len++;
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

    public K buscarPorIndice(int indice) {
        return buscarPorIndice(raiz, new int[]{0}, indice);
    }

    private K buscarPorIndice(Nodo nodo, int[] contador, int indice) {
        if (nodo == null) {
            return null;
        }

        K resultado = buscarPorIndice(nodo.izquierdo, contador, indice);
        if (resultado != null) {
            return resultado;
        }

        if (contador[0] == indice) {
            return nodo.clave;
        }
        contador[0]++;

        return buscarPorIndice(nodo.derecho, contador, indice);
    }

    public int length (){
        return len;
    }

    public Nodo getRaiz() {
        return raiz;
    }

    public void setRaiz(Nodo raiz) {
        this.raiz = raiz;
    }

    public int getLen() {
        return len;
    }

    public void setLen(int len) {
        this.len = len;
    }

}
