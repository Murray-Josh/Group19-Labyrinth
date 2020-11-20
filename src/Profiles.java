/**
 * Profiles.java
 *
 * @version 1.0.0
 * @author Martin Samm
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Stores all player's profile in an arraylist
 */
public class Profiles {
    private static final ArrayList<PlayerProfile> list = new ArrayList<>();

    /**
     * Create new list of profiles
     */
    public Profiles() {

    }

    private static void readProfiles(Scanner in) {
        while (in.hasNextLine()) {
            String curLine = in.nextLine();
            Scanner line = new Scanner(curLine);
            list.add(readProfile(line));
            line.close();
        }
    }

    private static PlayerProfile readProfile(Scanner in) {
        int numWins = in.nextInt();
        int numLosses = in.nextInt();
        int numGames = in.nextInt();
        String name = in.next();
        PlayerProfile p1 = new PlayerProfile(name);
        p1.setNumOfWins(numWins);
        p1.setNumOfLosses(numLosses);
        p1.setNumOfGames(numGames);
        return p1;

    }

    /**
     * Reads a file containing a list of profiles
     * NOTE: The structure of the file should be written as:
     * "wins losses games name"
     *
     * @param filename String
     */
    public static void readProfiles(String filename) {
        File file = new File(filename);
        Scanner in = null;
        try {
            in = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find " + filename);
            System.exit(0);
        }
        readProfiles(in);
        in.close();
    }

    /**
     * Reads all PlayerProfiles on string and
     * NOTE: The structure of the file will be written as:
     * "wins losses games name"
     *
     * @param filename String
     */
    public static void writeToFile(String filename) {
        try {
            FileWriter file = new FileWriter(filename);
            for (int i = 0; i < list.size(); i++) {
                file.write(list.get(i).getNumOfWins() + " ");
                file.write(list.get(i).getNumOfLosses() + " ");
                file.write(list.get(i).getNumOfGames() + " ");
                file.write(list.get(i).getName() + "\n");
            }
            file.close();
        } catch (IOException e) {
            System.out.println("File '" + filename + "' could not be completed");
        }

    }

    /**
     * Add new player profile to list
     *
     * @param p PlayerProfile
     */
    public void addProfile(PlayerProfile p) {
        list.add(p);
        if (!list.isEmpty()) {
            list.sort(Comparator.comparing(PlayerProfile::getNumOfWins).reversed());
        }
    }

    /**
     * Remove player profile to list
     *
     * @param p PlayerProfile
     */
    public void removeProfile(PlayerProfile p) {
        list.remove(p);
    }

    /**
     * Finds a specific player profile in a list
     *
     * @param p PlayerProfile
     * @return PlayerProfile, null if PlayerProfile not found
     */
    public PlayerProfile getProfile(PlayerProfile p) {
        for (int i = 0; i < list.size(); i++) {
            if (p == list.get(i)) {
                return p;
            }
        }
        return null;
    }

    /**
     * Finds a specific player profile in a list using int
     *
     * @param i int
     * @return PlayerProfile, null if PlayerProfile not found
     */
    public PlayerProfile getProfile(int i) {
        try {
            return list.get(i);
        } catch (Exception e) {
            System.out.println("Cannot return value");
            return null;
        }
    }

    /**
     * Gets size of Profile array
     */
    public int getArraySize() {
        return list.size();
    }

}
