/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import lp_java04.Palindrome;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Kazuya
 */
public class PalindromeTest {
    
    public PalindromeTest() {
    }
    

     @Test
     public void onNullStringIsNotAPalindrome() {
         shouldNotBeAPalindrome(null);
     }
     
     @Test
     public void onEmptyStringShouldNotBeAPalindrome(){
         shouldNotBeAPalindrome("");
     }
     
     @Test
     public void onASingleCharacterTest(){
         shouldBeAPalindrome("a");
     }
     
     @Test
     public void onTwoCharacterTest(){
         shouldBeAPalindrome("aa");
         shouldNotBeAPalindrome("ab");
     }
     
     @Test
     public void onThreeCharacterTest(){
         shouldBeAPalindrome("aba");
         shouldNotBeAPalindrome("aab");
     }
     
     @Test
     public void onFourCharacterTest(){
         shouldBeAPalindrome("abba");
         shouldNotBeAPalindrome("aabb");
     }
     
     @Test
     public void accentedCharacterAreConsderedAsUnaccented(){
         shouldBeAPalindrome("étè");
     }
     
     @Test
     public void removedSpaceString(){
         shouldBeAPalindrome("ab a");
     }
     
     @Test
     public void apostropheShouldBeIgnored(){
         shouldBeAPalindrome("Tu l'as trop écrasé César, ce port salut !");
     }
     
     @Test
     public void withNonWordCharacter(){
         shouldBeAPalindrome("Tu l'as trop écrasé César ce port salut");
     }
     
     private void shouldBeAPalindrome(String str){
         assertTrue(Palindrome.isPalindrome(str));
     }
     
     private void shouldNotBeAPalindrome(String str){
         assertFalse(Palindrome.isPalindrome(str));
     }
}
