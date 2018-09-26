package me.reider45.schoolify;

/**
 * Created by Reid.
 */
public class Utilities {

    // Change a string to Proper Case
    public static String properCase(String s){
        boolean properCase = true;
        String builder = "";
        for(char c : s.toCharArray()) {
            if(Character.isSpace(c)) {
                properCase = true;
            } else if(properCase) {
                c = Character.toUpperCase(c);
                properCase = false;
            }
            builder += c;
        }
        return builder;
    }
}
