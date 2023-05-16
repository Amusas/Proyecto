package Controller;

import Model.Artista;
import Model.Cancion;
import Model.Dominio;
import Persistencia.Persistencia;
import Model.Usuario;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Singleton {


    static private Dominio umusic = new Dominio();

    //Singleton para que solo exista una sola instancia del dominio en el proyecto
    private static class SingletonHolder { private final static Singleton E_INSTANCE = new Singleton();}

    public static Singleton getInstance() {
        return SingletonHolder.E_INSTANCE;
    }


    public Dominio getDominio() {return umusic;}

    /**
     * este metodo crea un usuario en la plataforma
     * @param nombre
     * @param nombreUsuario
     * @param contrasenia
     */
    public void crearUsuario(String nombre, String nombreUsuario, String contrasenia) {
        try {
            umusic.crearUusario(nombre, nombreUsuario, contrasenia);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * este metodo sirve para validar si ya existe un usuario en el servicio
     * @param nombre_usuario
     * @return
     */
    public boolean buscarUsuario (String nombre_usuario) {
        Usuario u = this.umusic.leerUsuario(nombre_usuario);
        if (u != null){
            //si el usuario se encuentra en la lista de usuarios, devuelve true ya que existe
            return true;
        }else {
            return false;
        }
    }

    /**
     * crea un artista en el servicio
     * @param nombreArtista
     * @param contrasenia
     */
    public void crearArtista(String nombreArtista, String contrasenia) {
        try {
            umusic.crearArtista(nombreArtista, contrasenia);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * este metodo sirve para validar si ya existe un usuario en el servicio
     * @param nombreArtista
     * @return
     */
    public boolean buscarArtista(String nombreArtista) {
        Artista a = this.umusic.leerArtista(nombreArtista);
        if (a != null){
            //si el Artista se encuentra en la lista de usuarios, devuelve true ya que existe
            return true;
        }else {
            return false;
        }
    }

    public Artista buscarArtistaValor(String nombreArtista) {
        Artista a = this.umusic.leerArtista(nombreArtista);
        System.out.println(nombreArtista);
        //si el Artista se encuentra en la lista de usuarios, devuelve true ya que existe
        return a;
    }

    public void anadir_cancion (Cancion c){
        this.umusic.agregar_cancion(c);
    }

    /**
     * crea un artista en el servicio
     * @param nombreArtista
     */
    public void crearCancion(String titulo ,String nombreArtista, String cancion) {
        try {
            Artista a = this.umusic.leerArtista(nombreArtista);
            a.crearCancion(titulo, cancion);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * este metodo sirve para validar si ya existe un usuario en el servicio
     * @param nombreArtista
     * @return
     */
    public boolean buscarCancion(String nombreArtista, String titulo) {
        Artista a = this.umusic.leerArtista(nombreArtista);
        Cancion c = a.leerCancion(titulo);
        if (c != null){
            //si el Artista se encuentra en la lista de usuarios, devuelve true ya que existe
            return true;
        }else {
            return false;
        }
    }

    /**
     * este metodo crea un log de las acciones que hagan los usuarios
     * @param mensajeLog
     * @param nivel
     * @param accion
     */
    public void registroLog (String mensajeLog, int nivel, String accion){
        Persistencia.crearLogg(mensajeLog, nivel, accion);
    }

    /**
     * este metodo crea la persistencia de todo el proyecto
     * @throws IOException
     */
    public void guardarDominio() throws IOException {
        Persistencia.guardarDominio(umusic);
    }

    /**
     * este metodo carga los datos del archivo en donde esta la base de datos
     * @throws IOException
     */
    public void cargarDominio() throws IOException {
        umusic = (Dominio) Persistencia.cargarDominio();
    }

    /**
     * Obtiene la Lista de empleados de la tienda
     * @return
     */
    public ArrayList<Artista> obtener_Artistas() {
        return umusic.getArtistas();
    }

    public void setDominio(Dominio servicio_Umusic) {umusic = servicio_Umusic;}


}
