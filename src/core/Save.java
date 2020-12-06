package core;

import controllers.GameboardController;
import holdables.Holdable;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import players.Player;
import players.PlayerMovement;

/**
 * Saves objects to file
 *
 * @author Joseph Omar
 * @version 2.0.0
 */
public class Save {

    public static void save(Player activePlayer, ArrayList<Player> players, int playerCounter, int movementsLeft, Coordinate temp, ArrayList<Integer> nextMovable, ArrayList<Integer> currentMovable, boolean[] alignsArray ) {

    }

    private static final String extension = ".ser";

    /**
     * Saves the gameboard and it's tiles, the players and their current hands, positions and active effects and the silk bag.
     *
     * @param obj Objects to be saved
     * @throws IOException If the file cannot be written
     */
    public static void saveGame(HashMap<String, Object> data) throws IOException {
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
        objectOutputStream.writeObject(data);

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
    @SuppressWarnings("unchecked")
    public static HashMap<String, Object> loadGame(File filename) throws IOException, ClassNotFoundException, NullPointerException {
        if (!filename.exists()) {
            throw new NullPointerException("The file located at <" + filename + "> does not exist.");
        } else {
            FileInputStream fileInputStream = new FileInputStream(filename);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            return (HashMap<String, Object>) objectInputStream.readObject();
        }
    }
}

