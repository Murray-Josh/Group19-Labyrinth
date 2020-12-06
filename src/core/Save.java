package core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import players.Player;

/**
 * Saves objects to file
 *
 * @author Joseph Omar
 * @version 3.1
 */
public class Save {

   private static final String extension = ".ser";

   /**
    * Saves a game to a file
    *
    * @param skip          Skip player
    * @param activePlayer  Current player field
    * @param playerCounter Player counter field
    * @param movementsLeft Movements left field
    * @param temp          Temp coordinate field
    * @param movement      Current Player Movement instance
    * @throws IOException If the file could not be written
    */
   public static void save(boolean skip, Player activePlayer,
        int playerCounter, int movementsLeft, Coordinate temp, PlayerMovement movement)
        throws IOException {
      HashMap<Key, Object> data = new HashMap<>();
      data.put(Key.SKIP, skip);
      data.put(Key.ACTIVE_PLAYER, activePlayer);
      data.put(Key.PLAYER_COUNTER, playerCounter);
      data.put(Key.MOVEMENTS_LEFT, movementsLeft);
      data.put(Key.TEMPORARY_COORDINATE, temp);
      data.put(Key.MOVEMENT, movement);
      saveGame(data);
   }

   /**
    * Saves the gameboard and it's tiles, the players and their current hands, positions and active
    * effects and the silk bag.
    *
    * @param data Objects to be saved to a file
    * @throws IOException If the file cannot be written
    */
   @SuppressWarnings({"ignored", "ResultOfMethodCallIgnored"})
   private static void saveGame(HashMap<Key, Object> data) throws IOException {
      /* Create a file name using the date and time of the save */
      Date date = new Date();
      SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");
      File file = new File(dateFormat.format(date) + "Labyrinth_Save" + extension);
      if (file.exists()) {
         file.delete();
      }
      /*Write the file */
      FileOutputStream fileOutputStream = new FileOutputStream(file);
      ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
      objectOutputStream.writeObject(data);

      fileOutputStream.close();
      objectOutputStream.close();
   }

   /**
    * Reads the specified Save file and returns a hashmap of the objects contained within
    *
    * @param filename The file name to load from
    * @return Hashmap of objects in the file
    * @throws IOException            If the file cannot be found
    * @throws ClassNotFoundException If the classes for the objects in the file are not present in
    *                                the application
    * @throws NullPointerException   If the file does not exist
    */
   @SuppressWarnings("unchecked")
   public static HashMap<Key, Object> load(File filename)
        throws IOException, ClassNotFoundException, NullPointerException {
      if (!filename.exists()) {
         throw new NullPointerException("The file located at <" + filename + "> does not exist.");
      } else {
         FileInputStream fileInputStream = new FileInputStream(filename);
         ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
         HashMap<Key, Object> toReturn = (HashMap<Key, Object>) objectInputStream.readObject();
         fileInputStream.close();
         objectInputStream.close();

         return toReturn;
      }
   }


   /**
    * Creates constant global key values allowing a class to decode the save and get the correct
    * data
    */
   public enum Key {
      SKIP, ACTIVE_PLAYER, PLAYER_COUNTER, MOVEMENTS_LEFT, TEMPORARY_COORDINATE, MOVEMENT
   }
}

