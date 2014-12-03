package lp_java04;

import java.text.Normalizer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kazuya
 */
public class Palindrome {
    
    
    public static boolean isPalindrome(String string){
        if(string == null)
            return false;
        if(string.equals(""))
            return false;
        string = processString(string);
        int i = 0, j = string.length()-1;
        while(i < j){
            if( string.charAt(i) != string.charAt(j) )
                return false;
            i++;
            j--;
        }
        
        return true;
    }

    private static String processString(String string) {
        string = removeSpaces(string);
        string = removeAccent(string);
        string = removeNonWordCharacter(string);
        return string.toLowerCase();
        
    }
    
    private static String removeAccent(String string){
        String normalizedString =  Normalizer.normalize(string, Normalizer.Form.NFKD);
        return normalizedString.replaceAll("[\\p{M}]", "");
    }
    
    private static String removeSpaces(String string){
        return string.replaceAll("\\s", "");
    }
    
    private static String removeNonWordCharacter(String string){
        return string.replaceAll("\\W", "");
    }
}
