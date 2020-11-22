import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Class for the Scoreboard in game
 *
 * @author FungWah Westley
 * @version 1.0
 */
public class Scoreboard{


    public static void drawScoreboard(){

        Collections.sort(GameSave.loadProfiles());
    }

    /**
     * Orders the profiles by the amount of wins
     *
     * @return The list of profiles in the right order
     * @param profileList Profiles
     */
    public Profiles orderProfiles(Profiles profileList){

        quickSort(profileList,0,
                profileList.getArraySize()-1);

        return profileList;

    }

    /**
     * places the least amount of wins profiles before the pivot and the
     * most amount of wins profiles after the pivot.
     *
     * @return The list of profiles in the right order
     * @param profileList Profiles
     * @param profileFromLeftIndex the profile at the start of the list
     * @param profileFromRightIndex the profile at the end of the list
     */
    private int partition(Profiles profileList, int profileFromLeftIndex, int profileFromRightIndex){
        PlayerProfile pivot = profileList.getProfile(profileFromRightIndex);
        int indexOfLeastWins = profileFromLeftIndex - 1;
        PlayerProfile temp = null;

        for (int i = profileFromLeftIndex; i < profileFromRightIndex; i++) {
            if(profileList.getProfile(i).getNumOfWins() <= pivot.getNumOfWins()){
                indexOfLeastWins++;

                temp = profileList.getProfile(indexOfLeastWins);
                profileList.getProfile(indexOfLeastWins) = profileList.getProfile(i);
                profileList.getProfile(i) = temp;
            }

        }

        temp = profileList.getProfile(indexOfLeastWins + 1);
        profileList.getProfile(indexOfLeastWins + 1) = profileList.getProfile(profileFromLeftIndex);
        profileList.getProfile(profileFromRightIndex) = temp;

        return indexOfLeastWins+1;

    }

    /**
     * implements the quicksort 
     *
     * @param profileList Profiles
     * @param profileFromLeftIndex the profile at the start of the list
     * @param profileFromRightIndex the profile at the end of the list
     */
    private void quickSort(Profiles profileList, int profileFromLeftIndex, int profileFromRightIndex){

        if(profileFromLeftIndex < profileFromRightIndex){
            int partitionIndex = partition(profileList, profileFromLeftIndex, profileFromRightIndex);

            quickSort(profileList, profileFromLeftIndex,partitionIndex-1);
            quickSort(profileList, partitionIndex+1, profileFromRightIndex);
        }

    }

    /**
     * calculates the win percentage for a single profile
     *
     * @return the percentage
     * @param profileList Profiles
     * @param index the profile at the index
     */
    public int calculateWinPercentage(Profiles profileList, int index){
       int percentage = 0;

        for (int i = 0; i < profileList.getArraySize(); i++) {
            if(profileList.getProfile(i).equals(profileList.getProfile(index))){
                percentage = profileList.getProfile(i).getNumOfWins()/ profileList.getProfile(i).getNumOfGames();
            }
        }
        return percentage;


    }
Collections.sort(profiles);


}
