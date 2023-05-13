package Controller;

import Model.Artista;
import Model.Cancion;
import Model.Dominio;
import Persistencia.Persistencia;
import Model.Usuario;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Singleton {


    static private Dominio umusic = new Dominio();

    private static class SingletonHolder { private final static Singleton E_INSTANCE = new Singleton();}

    public static Singleton getInstance() {
        return SingletonHolder.E_INSTANCE;
    }

    public Dominio getUniversidad() {return umusic;}

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

    public void anadir_cancion (Cancion c){
        this.umusic.agregar_cancion(c);
    }

    /**
     * crea un artista en el servicio
     * @param nombreArtista
     */
    public void crearCancion(String titulo ,String nombreArtista, File cancion) {
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

    public void registroLog (String mensajeLog, int nivel, String accion){
        Persistencia.crearLogg(mensajeLog, nivel, accion);
    }


    /**
     * Este metodo llama a guardar empleados de persistencia y guaradar productos
     */
    public void iniciarSalvarDatos() {
        try {
            Persistencia.cargarDatosArchivos(getUniversidad());
            JOptionPane.showMessageDialog(null, "Datos Guardados correctamente!!!!");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void guardarXml () throws Exception {
        Persistencia.guardarArchivoSerializado(umusic);
        JOptionPane.showMessageDialog(null, "Datos Guardados!!!");
    }

    public void cargarxml () throws Exception {
        this.umusic = (Dominio) Persistencia.cargarArchivoSerializado();
        JOptionPane.showMessageDialog(null, "Datos Cargados!!!");
    }

    /**
     * Con este metodo llama a el metodo de cargar Datos de persistencia, imprimiendo un mensaje de que se cargo satisfactoriamente
     */
    public void cargarDatosDesdeArchivos() {

        Dominio umus = new Dominio();
        try {
            Persistencia.cargarDatosArchivos(getUniversidad());
            ArrayList <Usuario> users = umus.getUsuarios();
            JOptionPane.showMessageDialog(null, "Datos Cargados!!!!");

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Obtiene la Lista de empleados de la tienda
     * @return
     */
    public ArrayList<Artista> obtener_Artistas() {
        return umusic.getArtistas();
    }

    public void setDominio(Dominio servicio_Umusic) {this.umusic = servicio_Umusic;}


}
