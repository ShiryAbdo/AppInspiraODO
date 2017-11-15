package com.Inspira.odo.helper;

import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by shirya on 14/11/17.
 */

public class DetectSpecialHelper {
    public int getSpecialCharacterCount(String s) {
        if (s == null || s.trim().isEmpty()) {
            System.out.println("Incorrect format of string");
            return 0;
        }
        Pattern p = Pattern.compile("[^A-Za-z0-9]");
        Matcher m = p.matcher(s);
        // boolean b = m.matches();
        boolean b = m.find();
        if (b == true) {
            Toast.makeText(getApplicationContext(), "There is a special character in my string ", Toast.LENGTH_SHORT).show();
            System.out.println("There is a special character in my string ");
        } else {
            Toast.makeText(getApplicationContext(), "There is no special char.", Toast.LENGTH_SHORT).show();

            System.out.println("There is no special char.");
            return 1;

        }

        return 0;
    }

    public static boolean isValidName(String inputString) {

        String specialCharacters = " !#$%&'()*+,-./:;<=>?@[]^_`{|}~0123456789";
        String[] strlCharactersArray = new String[inputString.length()];
        for (int i = 0; i < inputString.length(); i++) {
            strlCharactersArray[i] = Character
                    .toString(inputString.charAt(i));
        }
        //now  strlCharactersArray[i]=[A, d, i, t, y, a]
        int count = 0;
        for (int i = 0; i <  strlCharactersArray.length; i++) {
            if (specialCharacters.contains( strlCharactersArray[i])) {
                count++;
            }

        }

        if (inputString != null && count == 0) {
            return true;
        } else {
            return false;
        }
    }


}
