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
public class Knight extends Player {
    //INSTANCE VARIABLES
    private int attackDamage;
    private String specialMove;
    private int specialMoveDamage;
    private String finishingMoveDescription;
    private String finishingMoveName;
    
    //CONSTRUCTOR
    public Knight(int healthPointsIn, int scoreIn, String nameIn){
        super(healthPointsIn, scoreIn, nameIn);
        this.attackDamage = 4;
        this.specialMove = "Slash in a full downward arc with your mighty sword";
        this.specialMoveDamage = 6;
        this.finishingMoveDescription = "You unscrew the pommel of your sword, and with a mighty strength you throw the pommel, killing your enemy";
        this.finishingMoveName = "End them rightly";
    }
    
    //GETTERS AND SETTERS
    public int getAttackDamage(){
        return this.attackDamage;
    }
    public void setAttackDamage(int attackDamageIn){
        this.attackDamage = attackDamageIn;
    }
    
    //specialMove METHODS
    @Override
    public String getSpecialMove(){
        return this.specialMove;
    }
    @Override
    public String useSpecialMove(){
        return "You slashed your enemy with your sword and dealt " + this.specialMoveDamage + " points of damage.";
    }
    
    //toString METHODS
    @Override
    public String toString(){
        return super.toString() + "\nSpecial Move: " + this.specialMove + "\nDamage Dealt by Basic Attack: " + this.attackDamage + "\nDamage Dealt by Special Move: " + this.specialMoveDamage + "\nFinishing Move: " + this.finishingMoveName;
    }
}
