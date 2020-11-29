/*
 * Menus.FindMessage.java
 */
package controllers;

import constants.ErrorMsg;
import constants.Title;

import java.net.URL;
import java.util.Scanner;

/**
 * Opens the cswebcat website, decodes and outputs the message.
 *
 * @author Martin Samm
 * @version 1.0.0
 */
public class FindMessage {

    private static String message = null;

    /**
     * Retrieves the Message of the Day
     *
     * @return Message of the day
     */
    public static String getMessage() {
        if (message == null) {
            generateMessage();
        }
        return message;
    }

    /**
     * Returns the message of the day as a string
     *
     * @return String message
     */
    private static void generateMessage() {
        try {
            URL url = new URL("http://cswebcat.swansea.ac.uk/puzzle");
            Scanner in = new Scanner(url.openStream());
            String temp = in.next();
            in.close();

            String text = solve(temp);
            URL uri = new URL("http://cswebcat.swansea.ac.uk/message?solution=" + text);
            Scanner inurl = new Scanner(uri.openStream());
            message = "";
            while (inurl.hasNext()) {
                message += inurl.next() + " ";
            }
            inurl.close();
        } catch (Exception e) {
            StageController.showError(ErrorMsg.MESSGAGE_FAIL, Title.ERROR, false);
        }
    }

    /**
     * Solves the puzzle to help find the correct website
     *
     * @param text String
     *
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
     *
     * @param text String
     *
     * @return Decrypted text
     */
    private static String decrypt(String text) {
        char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        int move = 1;
        char[] arrayList = text.toCharArray();
        for (int i = 0; i < arrayList.length; i++) {
            for (int j = 0; j < alphabet.length; j++) {
                if ((i + 1) % 2 != 0 && arrayList[i] == alphabet[j]) {
                    if (j - move < 0) {
                        int temp = move - j;
                        arrayList[i] = alphabet[alphabet.length - temp];
                        break;
                    } else {
                        arrayList[i] = alphabet[j - move];
                        break;
                    }
                } else if ((i + 1) % 2 == 0 && arrayList[i] == alphabet[j]) {
                    if (j + move >= alphabet.length) {
                        int temp = j + move - alphabet.length;
                        arrayList[i] = alphabet[temp];
                        break;
                    } else {
                        arrayList[i] = alphabet[j + move];
                        break;
                    }
                }
            }
            move++;
        }
        return new String(arrayList);
    }

}