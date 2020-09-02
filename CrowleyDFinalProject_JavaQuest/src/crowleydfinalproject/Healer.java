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
public class Healer extends Player {
    //INSTANCE VARIABLES
    private int attackDamage;
    private String specialMove;
    private String finishingMoveDescription;
    private String finishingMoveName;
    
    //CONSTRUCTOR
    public Healer(int healthPointsIn, int scoreIn, String nameIn){
        super(healthPointsIn, scoreIn, nameIn);
        this.attackDamage = 2;
        this.specialMove = "Uses a sleeping powder to put the enemy to sleep";
        this.finishingMoveDescription = "You give your enemy a hug. They are so flabbergasted and embarrassed that they run away";
        this.finishingMoveName = "Kill them with kindness";
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
        return "You used your sleeping powder. The enemy will not attack for 2 turns";
    }
    
    //toString METHODS
    @Override
    public String toString(){
        return super.toString() + "\nSpecial Move: " + this.specialMove + "\nDamage Dealt by Basic Attack: " + this.attackDamage + "\nFinishing Move: " + this.finishingMoveName;
    }
    
}
