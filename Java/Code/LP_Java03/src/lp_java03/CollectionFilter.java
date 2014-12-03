/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lp_java03;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Kazuya
 */
public class CollectionFilter {

    public static <T> void removeDuplicate(List<T> list) {
        
        Set<T> set = new LinkedHashSet<>(list);
        list.clear();
        list.addAll(set);
        
    }

    /**
     *
     * @param <T>
     * @param list
     * @return
     */
    public static <T> Map<T, Integer> countDuplicate(List<T> list) {
        Map<T, Integer> map = new HashMap<>();
            int count = 0;
        
        for(T temp : list){
            for(T ttemp : list){
                if(temp.equals(ttemp))
                    count++;
            }
            if(count>1){
                map.put(temp, count);
            }
            count = 0;
        }
        
        return map;
    }
}
