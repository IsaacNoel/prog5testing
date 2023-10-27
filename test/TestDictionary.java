import assignment.*;
import java.io.*;
import java.util.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestDictionary {
    /*
     * Test if calls are made to methods before dictionary is initialized
     */
 //   @Test 
    public void loadItDictionaryTest(){
        GameDictionary dict = new GameDictionary();
        String dictionaryTxt = "/Users/isaacnoel/Desktop/Fa23/314H/prog5_1/words.txt";
        try{
            dict.loadDictionary(dictionaryTxt);
        }
        catch(IOException io){
            assertEquals(1, 2);
        }

        TreeNode root = dict.getRoot();
        assertNotEquals(root, null);
        assertEquals(root.getChar(), '-');
        assertEquals(root.getNumChildren(), 26);
        Iterator<String> it = dict.iterator();
        assertEquals(it.hasNext(), true);
        assertEquals(dict.contains("zymurgy"), true);

        BufferedReader br;

        // use it.next()
        try{
            br = new BufferedReader(new FileReader(dictionaryTxt));
            while(it.hasNext()){
                try{
                    assertEquals(it.next(), br.readLine());
                }
                catch(IOException io){
                    assertEquals(0, 1);
                }
            }
        }
        catch(FileNotFoundException fe){
            assertEquals(0, 1);
        }

        // test with a foreach
        try{
            br = new BufferedReader(new FileReader(dictionaryTxt));
            try{
                for(String s: dict) {
                    assertEquals(s, br.readLine());
                }
            }
            catch(IOException io){
                assertEquals(0, 1);
            }
            
        }
        catch(FileNotFoundException fe){
            assertEquals(0, 1);
        }

        // test case where iterator breaks
        Iterator<String> newIt = dict.iterator();
        try{
            while(true){
                assertNotEquals(newIt.next(), null);
                assertNotEquals(newIt.next(), "");
                if(newIt.next() == "-1"){
                    break;
                }
            }
            assertEquals(0, 1);
        }catch(NoSuchElementException nsee){
            assertEquals(1, 1);
        }
    }

 //   @Test 
    public void testPrefixAndContains(){
        GameDictionary dict = new GameDictionary();
        String dictionaryTxt = "/Users/isaacnoel/Desktop/Fa23/314H/prog5_1/words.txt";
        try{
            dict.loadDictionary(dictionaryTxt);
        }
        catch(IOException io){
            assertEquals(1, 2);
        }

        Iterator<String> it = dict.iterator();
        while(it.hasNext()){
            String word = it.next();
            assertFalse(dict.contains("qqqqqqqqqq"));
            assertFalse(dict.contains("jackwangisbadathearthstone"));
            assertFalse(dict.contains("ohmygoodnessohmygoodnessohmygoodness"));
            assertFalse(dict.contains("a"));
            assertFalse(dict.isPrefix("qqqqqqqqqq"));
            assertFalse(dict.contains(""));
            assertTrue(dict.contains(word));
            assertTrue(dict.isPrefix(word));
            while(word.length() > 0 && word.substring(0, word.length() - 1) != ""){
                word = word.substring(0, word.length() - 1);
                assertTrue(dict.isPrefix(word));
            }
        }
    }

//    @Test
    public void breakDictionary(){
        GameDictionary gd = new GameDictionary();
        assertFalse(gd.contains("Potato"));
        assertFalse(gd.isPrefix("Potato"));
        assertFalse(gd.contains(""));
        
        try{
            gd.loadDictionary(null);
        }
        catch(IOException io){
            assertEquals(1, 0);
        }

        try{
            gd.loadDictionary("/Users/isaacnoel/Desktop/Fa23/314H/prog5_1/words.txt");
        }
        catch(IOException io){
            assertEquals(1, 0);
        }

        try{
            gd.loadDictionary("/Users/isaacnoel/Desktop/Fa23/314H/prog5_1/words.txt");
        }
        catch(IOException io){
            assertEquals(1, 0);
        }
    }
}
