package Persistencia;
import Model.Artista;
import Model.Cancion;
import Model.Dominio;
import Model.Usuario;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Persistencia {


    private final static String RUTA_TXT_USUARIOS = "src/Recursos/usuarios.txt";
    private final static String RUTA_TXT_CANCIONES = "src/Recursos/canciones.txt";
    private final static String RUTA_TXT_ARTISTAS = "src/Recursos/artistas.txt";
    private final static String RUTA_TXT_UMUSIC = "src/Recursos/umusic.xml";
    private final static String RUTA_TXT_LOGG = "src/Recursos/logg.txt";

    public static void cargarDatosArchivos(Dominio umusic) throws FileNotFoundException, IOException {

        //cargar archivo de Estudiantes
        ArrayList<Usuario> usuarios_Cargados = cargarUsuario();
        ArrayList<Artista> artistas_Cargados = cargarArtista();
        ArrayList<Cancion> canciones_Cargadas = cargarCanciones();

        if(usuarios_Cargados.size() > 0) umusic.getUsuarios().addAll(usuarios_Cargados);
        if(artistas_Cargados.size() > 0) umusic.getArtistas().addAll(artistas_Cargados);
        if(canciones_Cargadas.size() > 0) umusic.getCanciones().addAll(canciones_Cargadas);

    }

    /**
     * Guarda en un archivo de texto toda la información de las personas almacenadas en el ArrayList
     * @throws IOException
     */
    public static void guardarUsuario(ArrayList<Usuario> listaUsuarios) throws IOException {
        // TODO Auto-generated method stub
        String contenido = "";
        for(Usuario u: listaUsuarios)
        {
            contenido+= u.getNombre()+"," + u.getNombreUsuario() + "," + u.getContrasenia() + "\n";
        }
        ArchivoUtil.guardarArchivo(RUTA_TXT_USUARIOS, contenido, false);
    }

    public static void guardarArtista(ArrayList<Artista> listaArtistas) throws IOException {
        // TODO Auto-generated method stub
        String contenido = "";
        for(Artista a: listaArtistas)
        {
            contenido+= a.getNombreArtista()+"," + a.getContrasenia() + "\n";
        }
        ArchivoUtil.guardarArchivo(RUTA_TXT_ARTISTAS, contenido, false);
    }

    public static void guardarCancion(ArrayList<Cancion> listaCanciones) throws IOException {
        // TODO Auto-generated method stub
        String contenido = "";
        for(Cancion c: listaCanciones)
        {
            contenido+= c.getTitulo()+ "," + c.getNombre_artista() + c.getArchivo_cancion() + "\n";
        }
        ArchivoUtil.guardarArchivo(RUTA_TXT_CANCIONES, contenido, false);
    }



    public static void crearLogg (String mensajeLog, int nivel, String accion) {
        ArchivoUtil.guardarRegistroLog(mensajeLog, nivel, accion, RUTA_TXT_LOGG);
    }


    /**
     * @return un Arraylist de personas con los datos obtenidos del archivo de texto indicado
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static ArrayList<Usuario> cargarUsuario() throws FileNotFoundException, IOException
    {
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

        ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_TXT_USUARIOS);
        String linea="";

        for (int i = 0; i < contenido.size(); i++)
        {
            linea = contenido.get(i);
            Usuario u = new Usuario();
            u.setNombre(linea.split(",")[0]);
            u.setNombreUsuario(linea.split(",")[1]);
            u.setContrasenia(linea.split(",")[2]);
            usuarios.add(u);
        }
        return usuarios;
    }

    public static ArrayList<Artista> cargarArtista() throws FileNotFoundException, IOException
    {
        ArrayList<Artista> artistas = new ArrayList<Artista>();

        ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_TXT_ARTISTAS);
        String linea="";

        for (int i = 0; i < contenido.size(); i++)
        {
            linea = contenido.get(i);
            Artista a = new Artista();
            a.setNombreArtista(linea.split(",")[0]);
            a.setContrasenia(linea.split(",")[1]);
            artistas.add(a);
        }
        return artistas;
    }

    public static ArrayList<Cancion> cargarCanciones() throws FileNotFoundException, IOException
    {
        ArrayList<Cancion> canciones = new ArrayList<Cancion>();

        ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_TXT_CANCIONES);

        String linea = "";

        for (int i = 0; i < contenido.size(); i++)
        {
            linea = contenido.get(i);
            Cancion c = new Cancion();
            c.setTitulo(linea.split(",")[0]);
            c.setNombre_artista(linea.split(",")[1]);
            c.setArchivo_cancion(new File(linea.split(",")[2]));
            canciones.add(c);
        }
        return canciones;
    }

    public static void guardarDominio(Dominio dominio) throws IOException {
        ArchivoUtil.salvarRecursoSerializadoXML(RUTA_TXT_UMUSIC, dominio);
    }

    public static Object cargarDominio() throws IOException {
        Object o = ArchivoUtil.cargarRecursoSerializadoXML(RUTA_TXT_UMUSIC);
        return o;
    }

}
