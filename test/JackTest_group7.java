import assignment.*;

import java.io.*;
import java.util.*;
import java.awt.*;

import org.junit.jupiter.api.Test;

import assignment.BoggleGame.SearchTactic;

import static org.junit.jupiter.api.Assertions.*;

public class JackTest_group7 {
    String prefix = "/Users/isaacnoel/Desktop/Fa23/314H/prog5_1/";
    @Test
    public void nullinputs(){
        GameManager game = new GameManager();
        GameDictionary dict = new GameDictionary();
        try{
            dict.loadDictionary(null);
            game.newGame(4, 1, null, null);
            char[][] b = new char[4][4];
        }catch(IOException io){
            assertTrue(false);
        }
    }
    @Test
    public void longerCube(){
        GameManager game = new GameManager();
        GameDictionary dict = new GameDictionary();
        try{
            dict.loadDictionary(prefix + "words.txt");
            game.newGame(4,1,prefix + "text.txt",dict);
        }
        catch(IOException io){
            assertTrue(false);
        }
    }
    @Test
    public void shorterCube(){
        GameManager game = new GameManager();
        GameDictionary dict = new GameDictionary();
        try{
            dict.loadDictionary(prefix + "words.txt");
            game.newGame(4,1,"/Users/ylhy2/Desktop/prog5/shortercubes.txt",dict);
        }
        catch(IOException io){
            assertTrue(false);
        }
    }
    @Test
    public void chineseCube(){
        GameManager game = new GameManager();
        GameDictionary dict = new GameDictionary();
        try{
            dict.loadDictionary(prefix + "words.txt");
            game.newGame(4,1,"/Users/ylhy2/Desktop/prog5/chinesecubes.txt",dict);
        }
        catch(IOException io){
            assertTrue(false);
        }
    }
    @Test
    public void fewCube(){
        GameManager game = new GameManager();
        GameDictionary dict = new GameDictionary();
        try{
            dict.loadDictionary(prefix + "words.txt");
            game.newGame(4,1,"/Users/ylhy2/Desktop/prog5/fewcubes.txt",dict);
        }
        catch(IOException io){
            assertTrue(false);
        }
    }
    @Test
    public void fewCubeV2(){
        GameManager game = new GameManager();
        GameDictionary dict = new GameDictionary();
        try{
            dict.loadDictionary(prefix + "words.txt");
            game.newGame(10,1,"/Users/ylhy2/Desktop/prog5/fewcubes.txt",dict);
        }
        catch(IOException io){
            assertTrue(false);
        }
    }
    @Test
    public void setBoard(){
        GameManager game = new GameManager();
        GameDictionary dict = new GameDictionary();
        try{
            dict.loadDictionary(prefix + "words.txt");
            game.newGame(10,1,prefix + "cubes.txt",dict);
        }
        catch(IOException io){
            assertTrue(false);
        }
        char[][] aaaa = new char[4][4];
        for(int i=0;i<4;i++)
        {
            for(int j=0;j<4;j++)
            {
                aaaa[i][j]='a';
            }
        }
        game.setGame(aaaa);
        //game.setSearchTactic(SearchTactic.SEARCH_DEFAULT);
        System.out.println(game.getAllWords().size());

        for(String s : game.getAllWords())
        {
            System.out.println(s);
        }
    }

}