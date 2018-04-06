package imt.uvinfo.pacxon.desktop;

import java.nio.file.Path;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import imt.uvinfo.pacxon.app.PacXon;

public class PacXonDesktop {

    public static void main(String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "PacXon";
        config.width = 800;
        config.height = 600;
        config.forceExit = false; // cleaner exit
        new LwjglApplication(new PacXon(), config);
    }
}
