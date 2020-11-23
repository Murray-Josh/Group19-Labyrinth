package menus;

import players.PlayerProfile;
import players.Profiles;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Sorts all the player profiles and displays them in descending order of wins.
 *
 * @author FungWah Westley & Joseph Omar
 * @version 2.0
 */
public class Scoreboard {
    private static ArrayList<PlayerProfile> profiles;

    public static void loadProfiles() {
        try {
            profiles = Profiles.get();
            Collections.sort(profiles);
            Collections.reverse(profiles);
        } catch (Exception e) {
            // exit scoreboard page and show error popup
        }
    }


}
