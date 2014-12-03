/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lp_java02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kazuya
 */
public class LP_Java02 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        HashMap<String, List<String>> map = new HashMap<>();
        try {
            // TODO code application logic here
            Scanner scan = new Scanner(new File("fr-unicode.txt"));

            while (scan.hasNext()) {
                String word = scan.next();
                String key = sort(word);
                
                if(key.length() > 6){
                    if(!map.containsKey(key)){
                        List<String> list = new ArrayList<>();
                        list.add(word);
                        map.put(key, list);
                    }else{
                        map.get(key).add(word);
                    }
                }

            }
            
            List<String> keys = new ArrayList(map.keySet());
            String line = "";
            int nb = 0;
            for(int i = 0 ; i < keys.size() ; i++){
                if(map.get(keys.get(i)).size()>1){
                    line = keys.get(i) + " " + map.get(keys.get(i)).size() + " : ";
                    nb += map.get(keys.get(i)).size();
                    for(String anagram : map.get(keys.get(i))){
                        line += anagram + " / ";
                    }
                        System.out.println(line);
                }
            }
            System.out.println(nb);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LP_Java02.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static String sort(String word) {
        char[] sorted = word.toCharArray();
        Arrays.sort(sorted);
        String newWord = "";
        for (char letter : sorted) {
            newWord += letter;
        }
        return newWord;
    }

}
