package byow.InterfaceEngine;

import byow.Core.Config;
import byow.Entity.World;
import byow.Input.InputDevice;

import java.io.*;

public class LoadGameInterface extends BaseInterface {
    public LoadGameInterface (Config config) {
        super(config);
    }

    @Override
    public void show() {
        // Do nothing.
        return;
    }

    @Override
    public void start(InputDevice inputDevice) {
        File file = new File(config.FILE_NAME);
        if (!file.exists()) {
            System.out.println("file not found");
            System.exit(0);
        }
        try {
            FileInputStream fileInStream = new FileInputStream(file);
            ObjectInputStream objectInStream = new ObjectInputStream(fileInStream);
            World savedWorld = (World) objectInStream.readObject();
            nextUserInterface = new WorldInterface(config, savedWorld);
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
            System.exit(0);
        } catch (IOException e) {
            System.out.println(e);
            System.exit(0);
        } catch (ClassNotFoundException e) {
            System.out.println("class not found");
            System.exit(0);
        }
    }
}
