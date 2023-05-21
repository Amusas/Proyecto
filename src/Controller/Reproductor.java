package Controller;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.AudioDevice;
import javazoom.jl.player.FactoryRegistry;
import javazoom.jl.player.Player;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class Reproductor {
    static String path;
    private static Player player;
    private static long pauseLocation;

    public static void setInstance (String path2) throws FileNotFoundException, JavaLayerException {
        path = path2;
        player = new Player(new FileInputStream(path));
    }

    private static AudioDevice audioDevice;
    private static Timer timer;
    private static long pausePosition;
    private boolean isPaused;

    public void play(String songFilePath) {
        try {
            FileInputStream fis = new FileInputStream(songFilePath);
            audioDevice = FactoryRegistry.systemRegistry().createAudioDevice();
            player = new Player(fis, audioDevice);

            // Iniciar el temporizador para controlar la reproducción
            timer = new Timer();
            timer.scheduleAtFixedRate(new UpdateTask(), 0, 100); // Actualiza cada 100 milisegundos

            // Reproducir la canción en un hilo separado
            Thread playerThread = new Thread(() -> {
                try {
                    player.play();
                } catch (JavaLayerException e) {
                    e.printStackTrace();
                }
            });
            playerThread.start();
        } catch (IOException | JavaLayerException e) {
            e.printStackTrace();
        }
    }

    public static void pause() {
        if (player != null) {
            pausePosition = player.getPosition();
            player.close();
            timer.cancel();
        }
    }

    public static void resume() {
        if (player != null) {
            try {
                FileInputStream fis = new FileInputStream("ruta/de/la/cancion.mp3");
                audioDevice = FactoryRegistry.systemRegistry().createAudioDevice();
                player = new Player(fis, audioDevice);


                timer = new Timer();

                Thread playerThread = new Thread(() -> {
                    try {
                        player.play();
                    } catch (JavaLayerException e) {
                        e.printStackTrace();
                    }
                });
                playerThread.start();

                // Ajustar la posición de reproducción a la posición de pausa
                //player.skip(pausePosition);
            } catch (IOException | JavaLayerException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        if (player != null) {
            player.close();
            timer.cancel();
            player = null;
            audioDevice = null;
        }
    }

    private class UpdateTask extends TimerTask {
        @Override
        public void run() {
            if (player != null && !isPaused) {
                System.out.println("Tiempo actual de reproducción: " + player.getPosition());
            }
        }
    }
}
