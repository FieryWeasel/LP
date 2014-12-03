/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lp_java03.CollectionFilter;
import lp_java03.Personne;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Kazuya
 */
public class CollectionFilterTest {

    List<Integer> list;
    List<Personne> personneList;
    Map<Integer, Integer> map;

    @Before
    public void setUp() {
        list = new ArrayList<>();
        map = new HashMap<>();
        personneList = new ArrayList<>();
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void removeDuplicateTest() {
        List<Integer> clearedList = new ArrayList(Arrays.asList(0, 5, 6, 8, 3, 4));

        list = new ArrayList(Arrays.asList(0, 0, 5, 6, 8, 3, 4, 5));
        CollectionFilter.removeDuplicate(list);
        assertEquals(clearedList, list);
    }

    @Test
    public void simpleRemoveDuplicatePersonneTest() {
     

        Personne p1 = new Personne("fn1", "ln1", "bd1");
        Personne p2 = new Personne("fn1", "ln1", "bd1");

        List<Personne> clearedList = new ArrayList<>();
        clearedList.add(p1);

        personneList.add(p1);
        personneList.add(p2);

        CollectionFilter.removeDuplicate(personneList);

        assertEquals(clearedList, personneList);

    }

    @Test
    public void complexRemoveDuplicatePersonneTest() {
      

        Personne p1 = new Personne("fn1", "ln1", "bd1");
        Personne p2 = new Personne("fn2", "ln1", "bd1");
        Personne p3 = new Personne("fn1", "ln3", "bd1");
        Personne p4 = new Personne("fn1", "ln1", "bd1");
        Personne p5 = new Personne("fn4", "ln4", "bd4");
        Personne p6 = new Personne("fn4", "ln4", "bd4");
        Personne p7 = new Personne("fn2", "ln1", "bd1");

        List<Personne> clearedList = new ArrayList<>();
        clearedList.add(p1);
        clearedList.add(p2);
        clearedList.add(p3);
        clearedList.add(p5);

        personneList.add(p1);
        personneList.add(p2);
        personneList.add(p3);
        personneList.add(p4);
        personneList.add(p5);
        personneList.add(p6);
        personneList.add(p7);

        CollectionFilter.removeDuplicate(personneList);

        assertEquals(clearedList, personneList);

    }

    @Test
    public void simpleCountDuplicate() {
  
        list = Arrays.asList(5, 5);
        map.put(5, 2);
        assertEquals(map, CollectionFilter.countDuplicate(list));

    }
    
    @Test
    public void complexCountDuplicate() {
  
        list = Arrays.asList(5, 8, 4, 8, 4, 5, 4, 5, 4, 5, 5, 4, 5, 4, 4, 5, 4);
        map.put(5, 7);
        map.put(8, 2);
        map.put(4, 8);
        assertEquals(map, CollectionFilter.countDuplicate(list));
    }

    @Test
    public void simpleCountDuplicatePersonneTest() {
  

        Personne p1 = new Personne("fn1", "ln1", "bd1");
        Personne p2 = new Personne("fn1", "ln1", "bd1");

        personneList.add(p1);
        personneList.add(p2);

        Map<Personne, Integer> personneMap = new HashMap<>();
        personneMap.put(p1, 2);

        assertEquals(personneMap, CollectionFilter.countDuplicate(personneList));

    }

    @Test
    public void complexCountDuplicatePersonneTest() {
     
        Personne p1 = new Personne("fn1", "ln1", "bd1");
        Personne p2 = new Personne("fn2", "ln1", "bd1");
        Personne p3 = new Personne("fn1", "ln1", "bd1");
        Personne p4 = new Personne("fn1", "ln1", "bd1");
        Personne p5 = new Personne("fn4", "ln4", "bd4");
        Personne p6 = new Personne("fn4", "ln4", "bd4");
        Personne p7 = new Personne("fn2", "ln1", "bd1");

        personneList.add(p1);
        personneList.add(p2);
        personneList.add(p3);
        personneList.add(p4);
        personneList.add(p5);
        personneList.add(p6);
        personneList.add(p7);

        Map<Personne, Integer> personneMap = new HashMap<>();
        personneMap.put(p1, 3);
        personneMap.put(p2, 2);
        personneMap.put(p5, 2);

        assertEquals(personneMap, CollectionFilter.countDuplicate(personneList));

    }
}
