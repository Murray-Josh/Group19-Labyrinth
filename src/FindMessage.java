/**
 * FindMessage.java
 * @version 1.0.0
 * @author Martin Samm
 */
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.Scanner;

/**
 * Opens the cswebcat website and diects the user to a message of the day.
 */
public class FindMessage {

	public static void main(String[] args) throws IOException {
		try {
//			String temp = "cab";
			URL url = new URL("http://cswebcat.swansea.ac.uk/puzzle");
			Scanner in = new Scanner(url.openStream());
			String temp = in.next();
			in.close();
			System.out.println(temp);
			String text = solve(temp);
//			System.out.println(text);
			URI uri = new URI("http://cswebcat.swansea.ac.uk/message?solution=" + text);
			System.out.println(uri.toString());
			java.awt.Desktop.getDesktop().browse(uri);
		} catch (Exception e){
			System.out.println("Cannot print");
		}

	}
	/**
	 * Solves the puzzle to help find the correct website
	 * @param text String
	 * @return Decrypted text
	 */
	private static String solve(String text) {
		text = decrypt(text.toUpperCase());
		text = "CS-230" + text;
		text += text.length();
		return text;
	}
	/**
	 * Decrypt the original text into a valid string
	 * @param text String
	 * @return Decrypted text
	 */
	private static String decrypt(String text) {
		char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
		int move = 1;
		char[] arrayList = text.toCharArray();
		for(int i = 0; i < arrayList.length; i++) {
			for (int j = 0; j < alphabet.length; j++) {
				if ((i+1) % 2 != 0 && arrayList[i] == alphabet[j]) {
					if (j-move < 0){
						int temp = move-j;
						arrayList[i] = alphabet[alphabet.length - temp];
						break;
					} else {
						arrayList[i] = alphabet[j-move];
						break;
					}
				} else if ((i+1) % 2 == 0 && arrayList[i] == alphabet[j]){
					if (j+move >= alphabet.length){
						int temp = j + move - alphabet.length;
						arrayList[i] = alphabet[0 + temp];
						break;
					} else {
						arrayList[i] = alphabet[j+move];
						break;
					}
				}
			}
		move++;
		}
		String newText = new String(arrayList);
		return newText;
	}

}