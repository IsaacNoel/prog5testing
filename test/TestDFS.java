import assignment.*;
import java.io.*;
import java.util.*;
import java.awt.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestDFS {
    String dictionaryfile = "/Users/isaacnoel/Desktop/Fa23/314H/prog5_1/words.txt";
    String cubefile = "/Users/isaacnoel/Desktop/Fa23/314H/prog5_1/cubes.txt";
    /*
     * Test if calls are made to methods before game is initialized
     */

      /*
     * Probabalistic testing for game board?
     * How do we know if we've found every word?
     */

     /*
      * Edge case: upper case file is passed
      */

      /*
       * Set game edge cases: incorrect board size, incorrect characters are passed
       */ 

       /*
        * Testing report: random number generators are not random
        */

        /*
         * Test enums (we got points off for bearing)
         * Try iscontains for a long word
         * AIRTIGHT STRING METHODS
         * 
         * iterator:
         *  what happens when next is called if empty is true
         * 
         * input formats
         *  unexpected whitespace 
         *  (whitespace at end of line, empty line)
         * 
         */



  //  @Test 
    public void testOnBoardAB(){ 
        GameManager game = new GameManager();
        GameDictionary dict = new GameDictionary();
        try{
            dict.loadDictionary(dictionaryfile);
            game.newGame(4, 1, cubefile, dict);
        }catch(IOException io){
            assertTrue(false);
        }
        char[][] ABboard = new char[4][4];
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                ABboard[i][j] = (char) ((int)'a' + (i + j) % 2);
            }
        }
        game.setGame(ABboard);
        GameDictionary validWordsOnBoard = game.getWordsOnBoardDict();
        HashMap<String, LinkedList<Point>> hmap = game.getWordToPoint();
        //System.out.print(outContent.toString());
        assertTrue(validWordsOnBoard.contains("aa"));
        assertTrue(validWordsOnBoard.contains("ba"));
        assertFalse(validWordsOnBoard.contains("bat"));
        assertTrue(validWordsOnBoard.isPrefix("a"));
        assertTrue(validWordsOnBoard.isPrefix("ba"));
        Iterator<String> it = dict.iterator();

        char board[][] = game.getBoard();
        while(it.hasNext()){
            String curr = it.next();
            boolean onBoard = true;
            for(int i = 0; i < curr.length(); i++){
                if(curr.charAt(i) != 'a' && curr.charAt(i) != 'b'){
                    onBoard = false;
                }
            }
            assertEquals(validWordsOnBoard.contains(curr), onBoard);
            assertEquals(hmap.get(curr) == null, !onBoard);

            // test points to make sure they're the actual character on the board
            if(onBoard){
                LinkedList<Point> points = hmap.get(curr);
                assertEquals(points.size(), curr.length());
                Iterator<Point> iteri = points.iterator();
                for(int i = 0; i < points.size(); i++){
                    Point pointi = iteri.next();
                    System.out.println(curr + " " + pointi.getX() + " " + pointi.getY());
                    assertEquals(board[(int) pointi.getX()][(int) pointi.getY()], curr.charAt(i));

                    Iterator<Point> iterj = points.iterator();
                    for(int j = 0; j < points.size(); j++){
                        Point pointj = iterj.next();
                        if(i != j){
                            assertFalse(pointi.equals(pointj));
                        }
                    }
                }
            }
        }
    }

    /*
     * Test that all words on the board have points that actually correspond to their word.
     */
 //   @Test
    public void testPoints(){
        GameManager game = new GameManager();
        GameDictionary dict = new GameDictionary();
        try{
            dict.loadDictionary(dictionaryfile);
            game.newGame(4, 1, cubefile, dict);
        }catch(IOException io){
            assertTrue(false);
        }
        GameDictionary validWordsOnBoard = game.getWordsOnBoardDict();
        HashMap<String, LinkedList<Point>> hmap = game.getWordToPoint();

        Iterator<String> wordsOnBoardIt = validWordsOnBoard.iterator();

        char board[][] = game.getBoard(); 

        while(wordsOnBoardIt.hasNext()){
            String nextWord = wordsOnBoardIt.next();
            LinkedList<Point> pointList = hmap.get(nextWord);
            assertNotEquals(pointList, null);
            Iterator<Point> pointIt = pointList.iterator();
            int stringIndex = 0;
            while(pointIt.hasNext()){
                Point next = pointIt.next();
                assertEquals(nextWord.charAt(stringIndex++), board[(int) next.getX()][(int) next.getY()]);
            }
        }
    }
    
  //  @Test 
    public void matchProgCase(){
        GameManager bg = new GameManager();
        BoggleDictionary bd = new GameDictionary();
        try{
            bd.loadDictionary(dictionaryfile);
            bg.newGame(4, 1, cubefile, bd);
        }catch(IOException io){
            assertEquals(1, 0);
        }
        char[][] board = {
            {'e', 'e', 'c', 'a'},
            {'a', 'l', 'e', 'p'},
            {'h', 'n', 'b', 'o'},
            {'q', 't', 't', 'y'}
        };
        bg.setGame(board);
        BoggleDictionary wordsOnBoard = bg.getWordsOnBoardDict();
        for(String s : wordsOnBoard){
            if(s.length() < 4){
                continue;
            }
            System.out.println(s);
            bg.addWord(s, 1);
        }
        assertTrue(bd.contains("toecap"));
        assertTrue(wordsOnBoard.contains("toecap"));
        assertTrue(bd.contains("lento"));
        assertEquals(bg.getScores()[1], 62);
        assertEquals(bg.getAllWords().size(), 42);
    }
  //  @Test
    public void breakBoggle(){
        BoggleGame bg = new GameManager();
        bg.addWord(null, 1);
        bg.getAllWords();
        bg.setGame(null);
        bg.getLastAddedWord();
        bg.getAllWords();
        bg.getScores();
        assertEquals(1, 1);
        try{   
            bg.newGame(0, 0, null, null);
        }catch(IOException io){
            assertEquals(1, 0);
        }
        bg.addWord("Oregon", 1);
        bg.getAllWords();
        bg.setGame(null);
        bg.getLastAddedWord();
        bg.getAllWords();
        bg.getScores();
        assertEquals(1, 1);
    }
}
