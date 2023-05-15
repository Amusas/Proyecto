package Model;

import java.io.File;
import java.io.Serializable;

public class Cancion implements Serializable {

    //atributos
    private String titulo, nombre_artista;
    private File archivo_cancion;

    public Cancion() {}

    //constructor
    public Cancion(String titulo, String nombre_artista, File archivo_cancion) {
        this.titulo = titulo;
        this.nombre_artista = nombre_artista;
        this.archivo_cancion = archivo_cancion;
    }

    //getters y setters
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getNombre_artista() {
        return nombre_artista;
    }

    public void setNombre_artista(String nombre_artista) {
        this.nombre_artista = nombre_artista;
    }

    public String getArchivo_cancion() {
        return archivo_cancion.getPath();
    }

    public void setArchivo_cancion(File archivo_cancion) {
        this.archivo_cancion = archivo_cancion;
    }


}
