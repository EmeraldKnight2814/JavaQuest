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
public class NPC {
    //CONSTRUCTOR
    private int hitPoints;
    private int damage;
    private String name;
    private String description;
    
    //CONSTRUCTOR
    public NPC (String nameIn, String descriptionIn, int hitPointsIn, int damageIn){
        this.hitPoints = hitPointsIn;
        this.damage = damageIn;
        this.name = nameIn;
        this.description = descriptionIn;
    }
    
    //GETTERS AND SETTERS
    public int getHitPoints(){
        return this.hitPoints;
    }
    public void setHitPoints(int hitPointsIn){
        this.hitPoints = hitPointsIn;
    }
    
    public int getDamage(){
        return this.damage;
    }
    public void setDamage(int damageIn){
        this.damage = damageIn;
    }
    
    public String getName(){
        return this.name;
    }
    public void setName(String nameIn){
        this.name = nameIn;
    }
    
    public String getDescription(){
        return this.description;
    }
    public void setDescription(String descriptionIn){
        this.description = descriptionIn;
    }
    
    //TO STRING
    @Override
    public String toString(){
        return "Enemy type: " + this.name + "\nDescription: " + this.description + "\nDamage it can deal: " + this.damage + "\nHealth Remaining: " + this.hitPoints;
    }
}
