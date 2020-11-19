import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class GameSave {

    private static void saveGame(Gameboard gameboard, SilkBag silkBag) throws IOException {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
        File file = new File(dateFormat.format(date) + ".labyrinth");
        if (file.exists()) {
            file.renameTo(new File(file.toString() + ".old"));
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file, true);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(gameboard);
        objectOutputStream.writeObject(silkBag);

    }

    private static void saveProfiles(ArrayList<PlayerProfile> profiles) throws IOException {
        File file = new File("players.profile");
        if (file.exists()) {
            file.renameTo(new File(file.toString() + ".old"));
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(profiles);
        objectOutputStream.close();
        fileOutputStream.close();

    }

    public static Object[] loadGame(File filename) throws IOException, ClassNotFoundException {
        Object toReturn[] = new Object[2];
        if (!filename.exists()) {
            throw new NullPointerException("The file located at <" + filename + "> does not exist.");
        } else {
            FileInputStream fileInputStream = new FileInputStream(filename);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            toReturn[0] = (Gameboard) objectInputStream.readObject();
            toReturn[1] = (SilkBag) objectInputStream.readObject();
        }
        return toReturn;
    }

    public static ArrayList<PlayerProfile> loadProfiles() throws IOException, ClassNotFoundException {
        File filename = new File("Players.profile");
        if (!filename.exists()) {
            throw new NullPointerException("The file located at <" + filename + "> does not exist.");
        } else {
            FileInputStream fileInputStream = new FileInputStream(filename);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            return (ArrayList<PlayerProfile>) objectInputStream.readObject();
        }
    }
}

