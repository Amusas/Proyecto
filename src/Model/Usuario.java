package Model;

import Librerias.ListaCircular;

import java.io.Serializable;

public class Usuario implements Serializable {

    String nombre, nombreUsuario, contrasenia;
    ListaCircular<Cancion> playList = new ListaCircular<>();

    //constructor
    public Usuario(String nombre, String nombreUsuario, String contrasenia) {
        this.nombre = nombre;
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
    }

    public Usuario(String nombreUsuario, String contrasenia) {
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
    }

    public void agregar_cancion (Cancion c){
        playList.agregar(c);
    }

    //getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }


    public ListaCircular<Cancion> getPlayList() {
        return playList;
    }

    public void setPlayList(ListaCircular<Cancion> playList) {
        this.playList = playList;
    }

    public Usuario() {
    }

    public Usuario(Usuario u) {
        nombre = u.getNombre();
        nombreUsuario = u.getNombreUsuario();
        contrasenia = u.getContrasenia();
    }
}
