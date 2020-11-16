/**
 * PlayerProfile.java
 * @version 1.0.0
 * @author Martin Samm
 */
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import org.j
/**
 * Stores all player's profile in an arraylist
 */
public class Profile {
	private static ArrayList<PlayerProfile> list = new ArrayList<>(); 
	/**
	 * Create new list of profiles
	 */
	public Profile() {
		
	}
	
	public static void main(String[] args) {
//		Profile list = new Profile();
//		PlayerProfile p1 = new PlayerProfile("Jeremy");
//		list.addProfile(p1);
//		list.removeProfile(p1);
//		System.out.println(list.getProfile(p1));
		readProfiles("Profiles.txt");
		System.out.println(list);
		writeToFile("listOfProfiles.txt");
	}
	/**
	 * Add new player profile to list
	 * @param p PlayerProfile
	 */
	public void addProfile(PlayerProfile p) {
		list.add(p);
	}
	/**
	 * Remove player profile to list
	 * @param p PlayerProfile
	 */
	public void removeProfile(PlayerProfile p) {
		list.remove(p);
	}
	/**
	 * Finds a specific player profile in a list
	 * @param p PlayerProfile
	 * @return PlayerProfile, null if PlayerProfile not found
	 */
	public PlayerProfile getProfile(PlayerProfile p) {
		for(int i = 0; i < list.size(); i++) {
			if (p == list.get(i)){
				return p;
			}
		}
		return null;
	}
	
	private static void readProfiles(Scanner in) {
		while(in.hasNextLine()) {
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
	
	public static void readProfiles(String filename) {
		File file = new File(filename);
		Scanner in = null;
		try
		{
			in = new Scanner(file);
		}
		catch (FileNotFoundException e) 
		{
     			System.out.println ("Cannot find " + filename);
     			System.exit (0);
		}
		readProfiles(in);
		in.close();
	}
	/**
	 * Reads all PlayerProfiles on string and
	 * NOTE: The structure of the file will be written as:
	 *  "wins losses games name"
	 * @param filename String
	 */
	public static void writeToFile(String filename) {
		try {
			FileWriter file = new FileWriter(filename);
			for(int i = 0; i < list.size(); i++) {
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

}
