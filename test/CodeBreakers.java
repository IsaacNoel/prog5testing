import assignment.*;
import java.io.*;
import java.util.*;
import java.awt.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CodeBreakers {
    String prefix = "/Users/isaacnoel/Desktop/Fa23/314H/prog5_1/";
    String dictionaryFiles[] = {
        "blank.txt",
        "cubes.txt",
        "duplicates.txt",
        "endl.txt",
        "singlechar.txt",
        "whitespace.txt",
        "words.txt"
    };

    @Test 
    public void nullStringDict(){
        BoggleDictionary bd = new GameDictionary();
        System.out.println(System.getProperty("user.dir"));
        try{
            bd.loadDictionary(null);
            assertFalse(bd.contains("asdfa"));
            assertFalse(bd.isPrefix("alsdkfj"));
            assertFalse(bd.contains(null));
        }
        catch(IOException io){
            assertEquals(1, 1);
        }
    }

    @Test 
    public void breakHelperFunctions(){
        BoggleDictionary bd = new GameDictionary();
        try{
            bd.loadDictionary("prefix" + "words.txt");
            assertFalse(bd.contains(null));
            assertFalse(bd.isPrefix(null));
            assertFalse(bd.contains("汉字"));
            assertFalse(bd.isPrefix("汉字"));
            assertFalse(bd.contains("00"));
            assertFalse(bd.isPrefix("00"));
        }
        catch(IOException io){
            assertEquals(1, 1);
        }
    }
    @Test
    public void blankDict(){
        BoggleDictionary bd = new GameDictionary();
        try{
            bd.loadDictionary(prefix + "blank.txt");
            assertFalse(bd.isPrefix("fortyfive"));
            assertFalse(bd.contains(prefix));
            assertFalse(bd.contains(""));
            Iterator<String> it = bd.iterator();
            assertFalse(it.hasNext());
            for(String s : bd){
                assertTrue(true);
            }
        }
        catch(IOException io){
            assertFalse(true);
        }
    }

    @Test 
    public void endlDict(){
        BoggleDictionary bd = new GameDictionary();
        try{
            bd.loadDictionary(prefix + "endl.txt");
            assertFalse(bd.isPrefix("fortyfive"));
            assertFalse(bd.contains(prefix));
            assertFalse(bd.contains("\n"));
            assertFalse(bd.isPrefix("\n"));
            assertTrue(bd.contains("forty"));
            assertTrue(bd.isPrefix("fort"));
            assertFalse(bd.isPrefix("fort\n"));
            assertFalse(bd.contains(" forty"));
            assertTrue(bd.contains("gronky"));
            Iterator<String> it = bd.iterator();
            while(it.hasNext()){
                it.next();
            }
        }
        catch(IOException io){
            assertFalse(true);
        }
    }

    @Test 
    public void singleCharDict(){
        BoggleDictionary bd = new GameDictionary();
        try{
            bd.loadDictionary(prefix + "singlechar.txt");
            assertFalse(bd.isPrefix("fortyfive"));
            assertFalse(bd.contains(prefix));
            assertFalse(bd.contains("\n"));
            assertFalse(bd.isPrefix("\n"));
            assertFalse(bd.contains("forty"));
            assertFalse(bd.isPrefix("fort"));
            assertFalse(bd.isPrefix("fort\n"));
            assertFalse(bd.contains(" forty"));
            Iterator<String> it = bd.iterator();
            assertTrue(it.hasNext());
            assertTrue(bd.contains("a"));
            assertTrue(bd.isPrefix("a"));
            assertFalse(bd.contains("b"));
            while(it.hasNext()){
                it.next();
            }
        }
        catch(IOException io){
            assertFalse(true);
        }
    }

    @Test
    public void duplicates(){
        BoggleDictionary bd = new GameDictionary();
        try{
            bd.loadDictionary(prefix + "duplicates.txt");
            assertTrue(bd.contains("wwwwww"));
            assertFalse(bd.contains("wwwwwww"));
            assertFalse(bd.contains("wwwww"));
            assertTrue(bd.isPrefix("ww"));
            assertFalse(bd.isPrefix("\n"));
        }
        catch(IOException io){

        }
        Iterator<String> it = bd.iterator();
        int count = 0;
        while(it.hasNext()){
            count++;
            it.next();
        }
        assertEquals(count, 1);
    }

    @Test
    public void whitespace(){
        BoggleDictionary bd = new GameDictionary();
        try{
            bd.loadDictionary(prefix + "whitespace.txt");
            assertFalse(bd.isPrefix(" "));
            assertTrue(bd.contains("asd"));
            assertTrue(bd.contains("fff"));
        }
        catch(IOException io){
            assertFalse(true);
        }
    }

    @Test
    public void abc(){
        BoggleDictionary bd = new GameDictionary();
        try{
            bd.loadDictionary(prefix + "abcd.txt");
            assertTrue(bd.contains("abcdefghijklmnopqrstuvwxyz"));
            for(String s : bd){
                System.out.println(s);
            }
        }
        catch(IOException io){
            assertEquals(0, 1);
        }
        
    }

    @Test
    public void nullDictionaryBreaker(){
        BoggleDictionary bd = new GameDictionary();
        BoggleDictionary bd2 = new GameDictionary();
        try{
            bd.loadDictionary("invaliddictionary.txt");
            //bd.loadDictionary("part two");
            //bd.loadDictionary("汉字");
            bd.loadDictionary("");
        }
        catch(IOException io){
            assertTrue(false);
        }
        Iterator<String> it = bd.iterator();
        assertFalse(it.hasNext());
        for(String s : bd){
            assertFalse(true);
        }
        assertFalse(bd.contains("汉字"));
        assertFalse(bd.isPrefix("汉字"));

        try{
            bd2.loadDictionary(prefix + "words.txt");
            bd.loadDictionary(prefix + "words.txt");
        }
        catch(IOException io){
            assertTrue(false);
        }

        Iterator<String> it1 = bd.iterator();
        Iterator<String> it2 = bd2.iterator();

        while(it1.hasNext()){
            assertTrue(it1.next().equals(it2.next()));
        }
        assertEquals(it1.hasNext(), it2.hasNext());
        assertTrue(bd.contains("bamboo"));
        assertFalse(bd.contains("汉字"));
    }

    @Test 
    public void nullBoggleGame(){
        BoggleGame bg = new GameManager();
        BoggleDictionary gd = new GameDictionary();
        try{
            bg.newGame(-1, 0, "", null);
        }
        catch(IOException io){
            assertFalse(true);
        }
        assertEquals(bg.getAllWords().size(), 0);
        bg.setSearchTactic(null);
        assertEquals(bg.getAllWords().size(), 0);
        bg.setGame(new char[4][4]);
        bg.setGame(null);
        assertEquals(bg.getAllWords().size(), 0);
        bg.addWord("word", 0);
        bg.addWord("word", 1);
        bg.addWord("word", -1);
        try{
            bg.newGame(4, 4, prefix + "cubes.txt", gd);
        }
        catch(IOException io){
            assertFalse(true);
        }
        bg.addWord("word", 1);
        bg.addWord("world", 5);
        assertEquals(bg.getLastAddedWord(), null);
    }


    @Test
    public void uninitializedGame(){
        BoggleGame game = new GameManager();
        BoggleDictionary dict = new GameDictionary();
        try{
           // dict.loadDictionary("-1-1-1-1");
            game.newGame(4, 1, prefix + "cubes.txt", null);
        }
        catch(IOException io){}
        game.getBoard();
        //assertEquals(game.getAllWords().size(), 0);
        game.addWord("-1-1-1", 1);
        game.addWord("a", 1);
        game.addWord("r", 3);
        game.setSearchTactic(null);
        assertEquals(game.getAllWords().size(), 0);
        game.getLastAddedWord();

        char[][] ghostBoard = new char[4][4];
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                ghostBoard[i][j] = 'a';
            }
        }
        BoggleGame game_noLoad = new GameManager();
        game_noLoad.getBoard();
        game_noLoad.addWord("-1-1-1-1", 1);
        game_noLoad.getLastAddedWord();
        game_noLoad.getAllWords();
        game_noLoad.getScores();
        game_noLoad.setGame(new char[4][4]);
    }

    @Test
    public void newGameInputs(){
        BoggleGame bg = new GameManager();
        BoggleDictionary bd = new GameDictionary();
        try{
            bd.loadDictionary(prefix + "words.txt");
            bg.newGame(-1, 1, prefix + "cubes.txt", bd);
            bg.newGame(0, 1, prefix + "cubes.txt", bd);
            bg.newGame(1, 1, prefix + "cubes.txt", bd);
            bg.newGame(5, 1, prefix + "cubes.txt", bd);
        }
        catch(IOException io) {

        }  
        
        bg.getAllWords();
    }

    @Test
    public void addingABadWord(){
        BoggleGame game = new GameManager();
        BoggleDictionary dict = new GameDictionary();
        try{
            dict.loadDictionary(prefix + "words.txt");
            game.newGame(4, 1, prefix + "cubes.txt", dict);
        }catch(IOException io){}
        game.addWord(prefix, 1);
        game.addWord("build", -1);
        game.addWord("build", 2);
        game.addWord("--000--", 1);
        game.addWord("cat", 1);
        game.addWord(null, 0);
    }

    @Test
    public void setGameTest(){
        BoggleGame bg = new GameManager();
        BoggleDictionary bd = new GameDictionary();
        //bg.setGame(null);
        char[][] a = new char[4][4];
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                a[i][j] = '-';
            }
        }
        bg.setGame(a);
        try{
            bd.loadDictionary(prefix + "words.txt");
            bg.newGame(5, 1, prefix + "cubes.txt", bd);
        }
        catch(IOException io){}
        bg.setGame(a);
        assertEquals(bg.getAllWords().size(), 0);
    }

    @Test
    public void setSearchTactic(){
        BoggleGame bg = new GameManager();
        bg.setSearchTactic(null);
    }
}
