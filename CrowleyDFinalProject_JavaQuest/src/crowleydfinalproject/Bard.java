/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crowleydfinalproject;

/**
 *
 * @author S535385
 */
public class Bard extends Player{
    //INSTANCE VARIABLES
    private int attackDamage;
    private String specialMove;
    private String finishingMoveDescription;
    private String finishingMoveName;
    
    //CONSTRUCTOR
    public Bard(int healthPointsIn, int scoreIn, String nameIn){
        super(healthPointsIn, scoreIn, nameIn);
        this.attackDamage = 2;
        this.specialMove = "Play Music to calm opponent, causing your opponent to leave";
        this.finishingMoveDescription = "You speack, and suddenly your enemy is vulnerable to your every command. You command them to kill themselves";
        this.finishingMoveName = "Mass Suggestion";
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
