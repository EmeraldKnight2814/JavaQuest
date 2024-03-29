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
public class Thief extends Player{
    //INSTANCE VARIABLES
    private int attackDamage;
    private String specialMove;
    private String finishingMoveDescription;
    private String finishingMoveName;
    
    //CONSTRUCTOR
    public Thief(int healthPointsIn, int scoreIn, String nameIn){
        super(healthPointsIn, scoreIn, nameIn);
        this.attackDamage = 3;
        this.specialMove = "Sneak by enemy to run with 100% success rate";
        this.finishingMoveDescription = "You disappear from your enemy's sight, and from behind them deal the finishing strike";
        this.finishingMoveName = "Sneak Attack!";
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
        return "You snuck away. Congratulations!";
    }
    
    //toString METHODS
    @Override
    public String toString(){
        return super.toString() + "\nSpecial Move: " + this.specialMove + "\nDamage Dealt by Basic Attack: " + this.attackDamage + "\nFinishing Move: " + this.finishingMoveName;
    }
    
}
