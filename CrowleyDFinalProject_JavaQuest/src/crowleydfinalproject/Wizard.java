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
public class Wizard extends Player {
    //INSTANCE VARIABLES
    private int attackDamage;
    private String specialMove;
    private int specialMoveDamage;
    private String finishingMoveDescription;
    private String finishingMoveName;
    
    //CONSTRUCTOR
    public Wizard(int healthPointsIn, int scoreIn, String nameIn){
        super(healthPointsIn, scoreIn, nameIn);
        this.attackDamage = 1;
        this.specialMove = "Cast a powerful attack spell";
        this.specialMoveDamage = 8;
        this.finishingMoveDescription = "You speak the Word of Power and send your enemy to the astral plane.";
        this.finishingMoveName = "Word of Power: Death";
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
        return "You cast a powerful and dealt " + this.specialMoveDamage + " points of damage.";
    }
    
    //toString METHODS
    @Override
    public String toString(){
        return super.toString() + "\nSpecial Move: " + this.specialMove + "\nDamage Dealt by Basic Attack: " + this.attackDamage + "\nDamage Dealt by Special Move: " + this.specialMoveDamage + "\nFinishing Move: " + this.finishingMoveName;
    }
    
}
