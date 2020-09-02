/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crowleydfinalproject;

/**
 *
 * @author Daniel J. Crowley
 */
public abstract class Player {
    //INSTANCE VARIABLES
    private int healthPoints;
    private int score;
    private String name;
    
    
    //CONSTRUCTOR
    public Player(int healthPointsIn, int scoreIn, String nameIn){
        this.healthPoints = healthPointsIn;
        this.score = scoreIn;
        this.name = nameIn;
    }
    
    //GETTERS AND SETTERS
    public int getHP(){
        return this.healthPoints;
    }
    public void setHP(int healthPointsIn){
        this.healthPoints = healthPointsIn;
    }
    
    public int getScore(){
        return this.score;
    }
    public void setScore(int scoreIn){
        this.score = scoreIn;
    }
    
    public String getName(){
        return this.name;
    }
    public void setName(String nameIn){
        this.name = nameIn;
    }
    
    //SPECIAL MOVE METHODS
    public abstract String getSpecialMove();
    public abstract String useSpecialMove();
    
    //TO STRING
    @Override
    public String toString(){
        return "Character name: " + this.name + "\nHealth Points: " + this.healthPoints + "\nCurrent Score: " + this.score;
    }
}
