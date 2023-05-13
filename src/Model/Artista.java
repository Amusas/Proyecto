package Model;

import Controller.Singleton;
import Exceptions.MyUtils;
import Exceptions.StringNuloOrVacioException;
import Librerias.ArbolBinario;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Artista {

    //atributos
    private String nombreArtista, contrasenia;
    private ArbolBinario<String, Cancion> listaCanciones = new ArbolBinario<>();

    public Artista() {}

    public Artista(String nombreArtista, String contrasenia) {
        this.nombreArtista = nombreArtista;
        this.contrasenia = contrasenia;
    }


    //CRUD Cancion
    public void crearCancion(String titulo, File cancion) throws StringNuloOrVacioException {
        if (cancion == null) throw new NullPointerException("No se ha seleccionado un archivo");
        MyUtils.validarSiNuloOrVacio(titulo);
        Cancion c = new Cancion(titulo, this.nombreArtista, cancion);
        this.listaCanciones.agregar(c.getTitulo(), c);
        Singleton.getInstance().anadir_cancion(c);//con esto se añade tambien la cancion a todas las canciones deisponibles

    }

    //READ
    public Cancion leerCancion(String titulo) {
        if (titulo != null) {
            if (listaCanciones.buscar(titulo) != null){
                return listaCanciones.buscar(titulo);
            }
        }
        return null;
    }


    //stters y getters
    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getNombreArtista() {
        return nombreArtista;
    }

    public void setNombreArtista(String nombreArtista) {
        this.nombreArtista = nombreArtista;
    }



}
