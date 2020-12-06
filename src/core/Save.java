package core;

import controllers.GameboardController;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import players.PlayerMovement;

/**
 * Saves objects to file
 *
 * @author Joseph Omar
 * @version 2.0.0
 */
public class Save {
    private static final String extension = ".ser";

    /**
     * Saves the gameboard and it's tiles, the players and their current hands, positions and active effects and the silk bag.
     *
     * @param obj Objects to be saved
     * @throws IOException If the file cannot be written+
     */
    public static void saveGame(Object[] obj) throws IOException {
        /* Create a file name using the date and time of the save */
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");
        File file = new File(dateFormat.format(date) + extension);
        if (file.exists()) {
            file.delete();
        }
        /*Write the file */
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(obj);

    }


    /**
     * Reads the specified Core.Save File and creates an array of Objects containing the data in the file
     *
     * @param filename The file name to load from
     * @return Objects in the specified file
     * @throws IOException            If the file cannot be found
     * @throws ClassNotFoundException If the classes for the objects in the file are not present in the application
     * @throws NullPointerException   If the file does not exist
     */
    public static Object[] loadGame(File filename) throws IOException, ClassNotFoundException, NullPointerException {
        if (!filename.exists()) {
            throw new NullPointerException("The file located at <" + filename + "> does not exist.");
        } else {
            FileInputStream fileInputStream = new FileInputStream(filename);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            return (Object[]) objectInputStream.readObject();
        }
    }
}

