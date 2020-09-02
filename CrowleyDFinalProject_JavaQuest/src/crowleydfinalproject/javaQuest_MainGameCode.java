/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crowleydfinalproject;
import java.util.*;

/**
 *
 * @author Daniel J. Crowley
 */
public class javaQuest_MainGameCode {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //START GUI
        new javaQuest_GUI();
        
        //START MUSIC
        String filepath = "Music and Sound Effects//deja_vu_8-bit.WAV";
        Sound musicObject = new Sound();
        musicObject.playMusic(filepath);
    }
    
}
