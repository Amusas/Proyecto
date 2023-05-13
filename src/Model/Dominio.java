package Model;

import Exceptions.MyUtils;
import Exceptions.StringNuloOrVacioException;

import java.util.ArrayList;

public class Dominio {

    final private String NOMBRE = "Umusic";
    ArrayList<Usuario> usuarios = new ArrayList<>();
    ArrayList<Artista> artistas = new ArrayList<>();
    ArrayList<Cancion> canciones = new ArrayList<>();

    //CRUD Usuario
    public void crearUusario(String nombre,String nombreUsuario, String contrasenia) throws StringNuloOrVacioException {
        if (nombre == null) throw new NullPointerException("El campo de nombre esta vacio");
        MyUtils.validarSiNuloOrVacio(nombre, nombreUsuario, contrasenia);
        Usuario u = new Usuario(nombre, nombreUsuario, contrasenia);
        this.usuarios.add(u);
    }

    //READ
    public Usuario leerUsuario(String nombreUsuario) {
        if (nombreUsuario != null) {
            for (Usuario u : usuarios) {
                if (u.getNombreUsuario().equals(nombreUsuario))
                    return u;
            }
        }
        return null;
    }

    //CRUD Artista
    public void crearArtista(String nombreArtista, String contrasenia) throws StringNuloOrVacioException {
        if (nombreArtista == null) throw new NullPointerException("El campo de nombre esta vacio");
        MyUtils.validarSiNuloOrVacio(nombreArtista, contrasenia);
        Artista a = new Artista(nombreArtista, contrasenia);
        this.artistas.add(a);
    }

    //READ
    public Artista leerArtista(String nombreArtista) {
        if (nombreArtista != null) {
            for (Artista a : artistas) {
                if (a.getNombreArtista().equals(nombreArtista))
                    return a;
            }
        }
        return null;
    }

    /**
     * este metodo agrega una cancion a la lista
     * @param c
     */
    public void agregar_cancion (Cancion c){
        this.canciones.add(c);
    }

    public String getNOMBRE() {
        return NOMBRE;
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public ArrayList<Artista> getArtistas() {
        return artistas;
    }

    public ArrayList<Cancion> getCanciones() {
        return canciones;
    }
}
