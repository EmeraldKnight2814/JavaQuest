/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crowleydfinalproject;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

/**
 *
 * @author Daniel J. Crowley
 * 
 * THIS IS THE GUI FOR THE JAVAQUEST GAME
 * 
 * 
 */


public class javaQuest_GUI implements ActionListener{
    //CREATE NECESSARY OBJECTS
    JFrame GUIWindow, endFrame;
    Container con;
    JPanel titleNamePanel, startButtonPanel, mainTextPanel, choiceButtonPanel, statusButtonPanel, quitButtonPanel, npcButtonPanel, itemButtonPanel, endTextPanel;
    JLabel titleNameLabel, endStatusLabel;
    Font titleFont = new Font("CASTELLAR", Font.PLAIN, 88);
    JButton startButton, choice1, choice2, choice3, choice4, choice5, statusReport, quit, npcButton, itemButton;
    Font normalFont = new Font("Constantia", Font.PLAIN, 30);
    Font buttonFont = new Font("Constantia", Font.BOLD, 28);
    Font smallerFont = new Font("Constantia", Font.PLAIN, 20);
    Sound buttonSound = new Sound();
    Sound battleSound = new Sound();
    Sound bossBattleSound = new Sound();
    Sound playerKilledSound = new Sound();
    
    JTextArea mainTextArea, endTextArea;
    Random rand = new Random();
    int typeChoice, enemyChoice, bossChoice, travelChoice, ballChoice, enemyHP, bossHP, npcIdentifier, mainIdentifier, mainHeal;
    boolean enemyAttack, mainAttack, mainEscape;
    
    
    Sound finishHim = new Sound();
    
    //TEXT VARIABLES
    String mainMessage, choice1message, choice2message, choice3message, choice4message, choice5message, statusMessage, npcStatus, endMessage, startButtonText;
    
    //FILEPATHS
    String finishFilepath = "Music and Sound Effects//finish_him.wav";
    String wilhelmFilepath = "Music and Sound Effects//WilhelmScream.wav";
    
    //CREATE AND INITIALIZE ENEMY OBJECTS
    NPC zombie = new NPC("Zombie", "An undead being created through the reanimation of a corpse. It is uncertain who created the zombies, but many suspect it was the demilich Acererak", 10, 2);
    NPC bandit = new NPC("Bandit", "A humanoid devoted to pillaging and stealing from others.", 18, 3);
    NPC lobbyist = new NPC("Lobbyist", "A person who takes part in an organized attempt to influence legislators.", 15, 3);
    NPC giantSpider = new NPC("Giant Spider", "A mutated Cobalt Blue Tarantula that has grown to 4 times their normal size.", 20, 5);
    NPC chimera = new NPC("Chimera", "A monster with the head of a Lion, the body of a Goat, and the tail of a Serpent!", 22, 5);
    NPC elemental = new NPC("Elemental", "One of the purest living forms of the four basic elements — air, earth, fire, and water.", 25, 5);
    NPC goblin = new NPC("Goblin", "Goblins are small, black-hearted humanoids that typically lair in despoiled dungeons and other dismal settings. Individually weak, they tend gather in large numbers to torment other creatures.", 10, 3);
    NPC griffon = new NPC("Griffon", "A ferocious avian carnivore with the muscular body of a Lion and the head, forelegs, and wings of an Eagle.", 20, 5);
    NPC werewolf = new NPC("Werewolf", "A savage predator that can appear as a humanoid, as a wolf, or in a terrifying hybrid form.", 18, 4);
    NPC gelatinousCube = new NPC("Gelationous Cube", "", 30, 5);
    
    //CREATE AND INITIALIZE BOSS OBJECTS
    NPC dragonBoss = new NPC("Dragon", "A great red scaley reptile with four powerful legs and two giant leathery wings.", 75, 7);
    NPC hydraBoss = new NPC("Hydra", "A serpentine monster with many heads! Cut off one head, two more shall grow in it's place.", 75, 7);
    NPC krakenBoss = new NPC("Kraken", "The legendary cephalopod-like sea monster of gigantic size depicted in Scandinavian folklore.", 75, 7);
    NPC lichBoss = new NPC("Lich", "A powerful demilich who is known and feared by many in the multiverse.", 75, 7);

    
    //CREATE PLAYER OBJECT (WILL BE INITIALIZED IN HANDELER)
    Player mainCharacter;


    //CONSTRUCTOR
    public javaQuest_GUI(){
        //INITIALZE FRAME
        GUIWindow = new JFrame();
        
        //SET WINDOW SIZE
        GUIWindow.setSize(800, 600);
        
        //CREATE "CLOSE" BUTTON
        GUIWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //SET BACKGROUND COLOR
        GUIWindow.getContentPane().setBackground(Color.black);
        
        //ALLOW FOR CUSTOM LAYOUT
        GUIWindow.setLayout(null);
        
        con = GUIWindow.getContentPane();
        
        
        //CREATE TITLE
        titleNamePanel = new JPanel();
        titleNamePanel.setBounds(100, 100, 600, 150);
        titleNamePanel.setBackground(Color.black);
        titleNameLabel = new JLabel("JAVA QUEST");
        titleNameLabel.setForeground(Color.green);
        titleNamePanel.add(titleNameLabel);
        con.add(titleNamePanel);
        
        //SET TITLE FONT
        titleNameLabel.setFont(titleFont);
        
        //CREATE START BUTTON PANEL
        startButtonPanel = new JPanel();
        startButtonPanel.setBounds(300, 400, 200, 100);
        startButtonPanel.setBackground(Color.black);
        
        
        //CREATE START BUTTON
        startButton = new JButton();
        startButtonText = "Start";
        startButton.setText(startButtonText);
        startButton.setBackground(Color.black);
        startButton.setForeground(Color.green);
        startButton.setFont(buttonFont);
        startButton.setFocusPainted(false);
        
        //PLACE BUTTON ON PANEL
        startButtonPanel.add(startButton);
        
        //MAKE START BUTTON WORK
        startButton.addActionListener(this);
        
        con.add(startButtonPanel);
        
        
        
        //MAKES GUI VISIBLE
        GUIWindow.setVisible(true);
    }
    
    

    
    //MAIN GAME SCREEN
    public void createGameScreen(){
        //TURN OFF TITLE SCREEN
        titleNamePanel.setVisible(false);
        startButtonPanel.setVisible(false);
        
        //CREATE AREA FOR MAIN TEXT
        mainTextPanel = new JPanel();
        mainTextPanel.setBounds(100, 100, 600, 250);
        mainTextPanel.setBackground(Color.black);
        con.add(mainTextPanel);
        
        //SET MESSAGE VARIABLES
        mainMessage = "Welcome to JAVA QUEST! \n\nWhen you are ready to create your character, \nchoose one of 5 character classes listed below!";
        choice1message = "Knight";
        choice2message = "Healer";
        choice3message = "Thief";
        choice4message = "Wizard";
        choice5message = "Bard";
        
        //CREATE MAIN TEXT (COLOR AND ACTUAL MESSAGE)
        mainTextArea = new JTextArea();
        mainTextArea.setBounds(100, 100, 600, 250);
        mainTextArea.setBackground(Color.black);
        mainTextArea.setForeground(Color.green);
        mainTextArea.setFont(normalFont);
        mainTextArea.setLineWrap(true);
        mainTextPanel.add(mainTextArea);
        mainTextArea.setText(mainMessage);
        
        //CREATE THE CHOICE BUTTON AREA
        choiceButtonPanel = new JPanel();
        choiceButtonPanel.setBounds(250, 350, 300, 150);
        choiceButtonPanel.setBackground(Color.black);
        choiceButtonPanel.setLayout(new GridLayout(5,1));
        con.add(choiceButtonPanel);
        
        //CREATE STATUS AND QUIT BUTTON AREAS
        statusButtonPanel = new JPanel();
        statusButtonPanel.setBounds(500, 0, 300, 50);
        statusButtonPanel.setBackground(Color.black);
        statusButtonPanel.setLayout(new GridLayout(1,1));
        con.add(statusButtonPanel);
        
        quitButtonPanel = new JPanel();
        quitButtonPanel.setBounds(0, 0, 200, 50);
        quitButtonPanel.setBackground(Color.black);
        quitButtonPanel.setLayout(new GridLayout(1,1));
        con.add(quitButtonPanel);
        
        //CREATE BUTTONS AND ADD THEM TO THE AREA
        
        //BUTTON 1
        choice1 = new JButton();
        choice1.setText(choice1message);
        choice1.setBackground(Color.black);
        choice1.setForeground(Color.yellow);
        choice1.setFont(buttonFont);
        choice1.setFocusPainted(false);
        choice1.addActionListener(this);
        choiceButtonPanel.add(choice1);
        
        //BUTTON 2
        choice2 = new JButton();
        choice2.setText(choice2message);
        choice2.setBackground(Color.black);
        choice2.setForeground(Color.blue);
        choice2.setFont(buttonFont);
        choice2.setFocusPainted(false);
        choice2.addActionListener(this);
        choiceButtonPanel.add(choice2);
        
        //BUTTON 3
        choice3 = new JButton();
        choice3.setText(choice3message);
        choice3.setBackground(Color.black);
        choice3.setForeground(Color.magenta);
        choice3.setFont(buttonFont);
        choice3.setFocusPainted(false);
        choice3.addActionListener(this);
        choiceButtonPanel.add(choice3);
        
        //BUTTON 4
        choice4 = new JButton();
        choice4.setText(choice4message);
        choice4.setBackground(Color.black);
        choice4.setForeground(Color.white);
        choice4.setFont(buttonFont);
        choice4.setFocusPainted(false);
        choice4.addActionListener(this);
        choiceButtonPanel.add(choice4);
        
        //BUTTON 5
        choice5 = new JButton();
        choice5.setText(choice5message);
        choice5.setBackground(Color.black);
        choice5.setForeground(Color.green);
        choice5.setFont(buttonFont);
        choice5.setFocusPainted(false);
        choice5.addActionListener(this);
        choiceButtonPanel.add(choice5);
        
        //STATUS BUTTON
        statusReport = new JButton("STATUS REPORT");
        statusReport.setBackground(Color.black);
        statusReport.setForeground(Color.PINK);
        statusReport.setFont(buttonFont);
        statusReport.setFocusPainted(false);
        statusReport.addActionListener(this);
        statusMessage = "NO CHARACTER YET SELECTED!";
        statusButtonPanel.add(statusReport);
        
        //QUIT BUTTON
        quit = new JButton("QUIT");
        quit.setBackground(Color.black);
        quit.setForeground(Color.red);
        quit.setFont(buttonFont);
        quit.setFocusPainted(false);
        quit.addActionListener(this);
        quitButtonPanel.add(quit);
        
        //NPC STATUS BUTTON
        npcButtonPanel = new JPanel();
        npcButtonPanel.setBounds(0, 525, 275, 50);
        npcButtonPanel.setBackground(Color.black);
        npcButtonPanel.setLayout(new GridLayout(1,1));
        con.add(npcButtonPanel);
        
        npcButton = new JButton("ENEMY STATUS");
        npcButton.setBackground(Color.black);
        npcButton.setForeground(Color.gray);
        npcButton.setFont(buttonFont);
        npcButton.setFocusPainted(false);
        npcButton.addActionListener(this);
        npcStatus = "NO CURRENT ENEMY";
        npcButtonPanel.add(npcButton);
    }

    



    //BUTTON LISTENER METHODS
    @Override
    public void actionPerformed(ActionEvent event){
        //PLAY BUTTON SOUND
        String buttonFilepath = "Music and Sound Effects//minecraft_click.wav";
        buttonSound = new Sound();
        buttonSound.playSound(buttonFilepath);
        //DETERMINE WHICH BUTTON WAS CLICKED AND WHAT TO DO
        if(event.getSource() == startButton){
            if("Start".equals(startButtonText)){
                createGameScreen();
            }
            else{
                reCreateCharacterScreen();
            }
        }
        
        if(event.getSource() == choice1){
            //CHARACTER CREATION CHOICE
            if("Knight".equals(choice1message)){
                //PROMPT USER FOR NAME
                String s = (String)JOptionPane.showInputDialog("Please enter your character's name!");
                
                //CREATE MAIN CHARACTER AND SET STATUS
                mainCharacter = new Knight(10, 0, s);
                statusMessage = mainCharacter.toString();
                mainIdentifier = 1;
                
                //CHANGE MESSAGES
                createTravelScreen("Welcome " + mainCharacter.getName() + "\n\nYou awaken in a field with no recollection of how you got there.\nWhat do you do?");
            }
            
            //MOVING SCREEN CHOICE
            else if("Go North".equals(choice1message)){
                generateRandomEvent();
                mainCharacter.setScore(mainCharacter.getScore() + 1);
                statusMessage = mainCharacter.toString();
            }
            
            //ATTACK SCREEN CHOICE
            else if("Attack".equals(choice1message)){
                switch(npcIdentifier){
                    //default
                    default:
                        break;
                        
                    //case 1 (zombie)
                    case 1:
                        makeAttackMessage(zombie);
                        break;
                        
                    //case 2 (bandit)
                    case 2:
                        makeAttackMessage(bandit);
                        break;
                        
                    //case 3 (lobbyist)
                    case 3:
                        makeAttackMessage(lobbyist);
                        break;
                        
                    //case 4 (giant spider)
                    case 4:
                        makeAttackMessage(giantSpider);
                        break;
                        
                    //case 5 (chimera)
                    case 5:
                        makeAttackMessage(chimera);
                        break;
                        
                    //case 6 (elemental)
                    case 6:
                        makeAttackMessage(elemental);
                        break;
                        
                    //case 7 (goblin)
                    case 7:
                        makeAttackMessage(goblin);
                        break;
                        
                    //case 8 (griffon)
                    case 8:
                        makeAttackMessage(griffon);
                        break;
                        
                    //case 9 (werewolf)
                    case 9:
                        makeAttackMessage(werewolf);
                        break;
                        
                    //case 10 (gelatinous cube)
                    case 10:
                        makeAttackMessage(gelatinousCube);
                        break;
                        
                    //case 11 (dragon)
                    case 11:
                        makeAttackMessage(dragonBoss);
                        break;
                        
                    //case 12 (hydra)
                    case 12:
                        makeAttackMessage(hydraBoss);
                        break;
                        
                    //case 13 (kraken)
                    case 13:
                        makeAttackMessage(krakenBoss);
                        break;
                        
                    //case 14 (lich)
                    case 14:
                        makeAttackMessage(lichBoss);
                        break;
                }
                
                
            }
        }
        
        if(event.getSource() == choice2){
            //CHARACTER CREATION CHOICE
            if("Healer".equals(choice2message)){
                //PROMPT USER FOR NAME
                String s = (String)JOptionPane.showInputDialog("Please enter your character's name!");
                
                //CREATE MAIN CHARACTER AND SET STATUS
                mainCharacter = new Healer(10, 0, s);
                statusMessage = mainCharacter.toString();
                mainIdentifier = 2;
                
                //CHANGE MESSSAGES
                createTravelScreen("Welcome " + mainCharacter.getName() + "\n\nYou awaken in a field with no recollection of how you got there.\nWhat do you do?");
            }
            
            //MOVING SCREEN CHOICE
            else if("Go South".equals(choice2message)){
                generateRandomEvent();
                mainCharacter.setScore(mainCharacter.getScore() + 1);
                statusMessage = mainCharacter.toString();
            }
            
            //ATTACK SCREEN CHOICE
            else if("Special Move".equals(choice2message)){
                switch(npcIdentifier){
                    //default
                    default:
                        break;
                        
                    //case 1 (zombie)
                    case 1:
                        makeSpecialMove(zombie);
                        break;
                        
                    //case 2 (bandit)
                    case 2:
                        makeSpecialMove(bandit);
                        break;
                        
                    //case 3 (lobbyist)
                    case 3:
                        makeSpecialMove(lobbyist);
                        break;
                        
                    //case 4 (giant spider)
                    case 4:
                        makeSpecialMove(giantSpider);
                        break;
                        
                    //case 5 (chimera)
                    case 5:
                        makeSpecialMove(chimera);
                        break;
                        
                    //case 6 (elemental)
                    case 6:
                        makeSpecialMove(elemental);
                        break;
                        
                    //case 7 (goblin)
                    case 7:
                        makeSpecialMove(goblin);
                        break;
                        
                    //case 8 (griffon)
                    case 8:
                        makeSpecialMove(griffon);
                        break;
                        
                    //case 9 (werewolf)
                    case 9:
                        makeSpecialMove(werewolf);
                        break;
                        
                    //case 10 (gelatinous cube)
                    case 10:
                        makeSpecialMove(gelatinousCube);
                        break;
                        
                    //case 11 (dragon)
                    case 11:
                        makeSpecialMove(dragonBoss);
                        break;
                        
                    //case 12 (hydra)
                    case 12:
                        makeSpecialMove(hydraBoss);
                        break;
                        
                    //case 13 (kraken)
                    case 13:
                        makeSpecialMove(krakenBoss);
                        break;
                        
                    //case 14 (lich)
                    case 14:
                        makeSpecialMove(lichBoss);
                        break;
                }
            }
        }
        
        if(event.getSource() == choice3){
            //CHARACTER CREATION CHOICE
            if("Thief".equals(choice3message)){
                //PROMPT USER FOR NAME
                String s = (String)JOptionPane.showInputDialog("Please enter your character's name!");
                
                //CREATE MAIN CHARACTER AND STATUS
                mainCharacter = new Thief(10, 0, s);
                statusMessage = mainCharacter.toString();
                mainIdentifier = 3;
                
                //CHANGE MESSAGES
                createTravelScreen("Welcome " + mainCharacter.getName() + "\n\nYou awaken in a field with no recollection of how you got there.\nWhat do you do?");
            }
            
            //MOVING SCREEN CHOICE
            else if("Go East".equals(choice3message)){
                generateRandomEvent();
                mainCharacter.setScore(mainCharacter.getScore() + 1);
                statusMessage = mainCharacter.toString();
            }
            
            //ATTACK SCREEN CHOICE
            else if("Finishing Move".equals(choice3message)){
               switch(npcIdentifier){
                    //default
                    default:
                        break;
                        
                    //case 1 (zombie)
                    case 1:
                        makeFinishingMoveMessage(zombie);
                        break;
                        
                    //case 2 (bandit)
                    case 2:
                        makeFinishingMoveMessage(bandit);
                        break;
                        
                    //case 3 (lobbyist)
                    case 3:
                        makeFinishingMoveMessage(lobbyist);
                        break;
                        
                    //case 4 (giant spider)
                    case 4:
                        makeFinishingMoveMessage(giantSpider);
                        break;
                        
                    //case 5 (chimera)
                    case 5:
                        makeFinishingMoveMessage(chimera);
                        break;
                        
                    //case 6 (elemental)
                    case 6:
                        makeFinishingMoveMessage(elemental);
                        break;
                        
                    //case 7 (goblin)
                    case 7:
                        makeFinishingMoveMessage(goblin);
                        break;
                        
                    //case 8 (griffon)
                    case 8:
                        makeFinishingMoveMessage(griffon);
                        break;
                        
                    //case 9 (werewolf)
                    case 9:
                        makeFinishingMoveMessage(werewolf);
                        break;
                        
                    //case 10 (gelatinous cube)
                    case 10:
                        makeFinishingMoveMessage(gelatinousCube);
                        break;
                        
                    //case 11 (dragon)
                    case 11:
                        makeFinishingMoveMessage(dragonBoss);
                        break;
                        
                    //case 12 (hydra)
                    case 12:
                        makeFinishingMoveMessage(hydraBoss);
                        break;
                        
                    //case 13 (kraken)
                    case 13:
                        makeFinishingMoveMessage(krakenBoss);
                        break;
                        
                    //case 14 (lich)
                    case 14:
                        makeFinishingMoveMessage(lichBoss);
                        break;
                }
            }
        }
        
        if(event.getSource() == choice4){
            //CHARACTER CREATION CHOICE
            if("Wizard".equals(choice4message)){
                //PROMPT USER FOR NAME
                String s = (String)JOptionPane.showInputDialog("Please enter your character's name!");
                
                //CREATE MAIN CHARACTER AND STATUS
                mainCharacter = new Wizard(10, 0, s);
                statusMessage = mainCharacter.toString();
                mainIdentifier = 4;
                
                //CHANGE MESSAGES
                createTravelScreen("Welcome " + mainCharacter.getName() + "\n\nYou awaken in a field with no recollection of how you got there.\nWhat do you do?");
            }
            
            //MOVING SCREEN CHOICE
            else if("Go West".equals(choice4message)){
                generateRandomEvent();
                mainCharacter.setScore(mainCharacter.getScore() + 1);
                statusMessage = mainCharacter.toString();
            }
            
            //ATTACK SCREEN CHOICE
            else if("Use Healing Item".equals(choice4message)){
                switch(npcIdentifier){
                    //default
                    default:
                        break;
                        
                    //case 1 (zombie)
                    case 1:
                        makeHealingMessage(zombie);
                        break;
                        
                    //case 2 (bandit)
                    case 2:
                        makeHealingMessage(bandit);
                        break;
                        
                    //case 3 (lobbyist)
                    case 3:
                        makeHealingMessage(lobbyist);
                        break;
                        
                    //case 4 (giant spider)
                    case 4:
                        makeHealingMessage(giantSpider);
                        break;
                        
                    //case 5 (chimera)
                    case 5:
                        makeHealingMessage(chimera);
                        break;
                        
                    //case 6 (elemental)
                    case 6:
                        makeHealingMessage(elemental);
                        break;
                        
                    //case 7 (goblin)
                    case 7:
                        makeHealingMessage(goblin);
                        break;
                        
                    //case 8 (griffon)
                    case 8:
                        makeHealingMessage(griffon);
                        break;
                        
                    //case 9 (werewolf)
                    case 9:
                        makeHealingMessage(werewolf);
                        break;
                        
                    //case 10 (gelatinous cube)
                    case 10:
                        makeHealingMessage(gelatinousCube);
                        break;
                        
                    //case 11 (dragon)
                    case 11:
                        makeHealingMessage(dragonBoss);
                        break;
                        
                    //case 12 (hydra)
                    case 12:
                        makeHealingMessage(hydraBoss);
                        break;
                        
                    //case 13 (kraken)
                    case 13:
                        makeHealingMessage(krakenBoss);
                        break;
                        
                    //case 14 (lich)
                    case 14:
                        makeHealingMessage(lichBoss);
                        break;
                }
            }
        }
        
        if(event.getSource() == choice5){
            //CHARACTER CREATION CHOICE
            if("Bard".equals(choice5message)){
                //PROMPT USER FOR NAME
                String s = (String)JOptionPane.showInputDialog("Please enter your character's name!");
                
                //CREATE MAIN CHARACTER AND STATUS
                mainCharacter = new Bard(10, 0, s);
                statusMessage = mainCharacter.toString();
                mainIdentifier = 5;
                
                //CHANGE MESSAGES
                createTravelScreen("Welcome " + mainCharacter.getName() + "\n\nYou awaken in a field with no recollection of how you got there.\nWhat do you do?");
            }
            
            //MOVING SCREEN CHOICE
            else if("Curl up in a ball".equals(choice5message)){
                mainCharacter.setScore(mainCharacter.getScore() + 1);
                statusMessage = mainCharacter.toString();
                ballChoice = rand.nextInt(5);
                switch (ballChoice){
                    case 0:
                        mainMessage = "You curl up in a ball and cry yourself to sleep. \n\nWhen you awaken, you find nothing around \nyou has changed. \nWhat do you do?";
                        mainTextArea.setText(mainMessage);
                        break;
                        
                    case 1:
                        mainMessage = "As you curl up into a ball, you start rolling \ndown a nearby hill. You ram into a wall and \ntake 1 point of damage\n\nWhat do you do?";
                        mainTextArea.setText(mainMessage);
                        mainCharacter.setHP(mainCharacter.getHP() - 1);
                        statusMessage = mainCharacter.toString();
                        break;
                        
                    case 2:
                        mainMessage = "You curl up into a ball and cry yourself to \nsleep.\nWhen you awaken, you find that the area around you is scorched. \n\nWhat do you do?";
                        mainTextArea.setText(mainMessage);
                        break;
                    
                    case 3:
                        mainMessage = "You curl up into a ball. When you do, a turtleshell spontaneously appears on your back. \nYour HP is increased by 5 \nWhat do you do?";
                        mainTextArea.setText(mainMessage);
                        mainCharacter.setHP(mainCharacter.getHP() + 5);
                        statusMessage = mainCharacter.toString();
                        break;
                    case 4:
                        mainMessage = "As you curl up into a ball, the ground around you begins to shake. \n\nWhat do you do?";
                        mainTextArea.setText(mainMessage);
                        break;
                    default:
                        mainMessage = "This message should not show up. Please make another selection";
                        mainTextArea.setText(mainMessage);
                        break;
                }
            }
            
            //ATTACK SCREEN CHOICE
            else if("Run".equals(choice5message)){
                switch(npcIdentifier){
                    //default
                    default:
                        break;
                        
                    //case 1 (zombie)
                    case 1:
                        makeRunMessage(zombie);
                        break;
                        
                    //case 2 (bandit)
                    case 2:
                        makeRunMessage(bandit);
                        break;
                        
                    //case 3 (lobbyist)
                    case 3:
                        makeRunMessage(lobbyist);
                        break;
                        
                    //case 4 (giant spider)
                    case 4:
                        makeRunMessage(giantSpider);
                        break;
                        
                    //case 5 (chimera)
                    case 5:
                        makeRunMessage(chimera);
                        break;
                        
                    //case 6 (elemental)
                    case 6:
                        makeRunMessage(elemental);
                        break;
                        
                    //case 7 (goblin)
                    case 7:
                        makeRunMessage(goblin);
                        break;
                        
                    //case 8 (griffon)
                    case 8:
                        makeRunMessage(griffon);
                        break;
                        
                    //case 9 (werewolf)
                    case 9:
                        makeRunMessage(werewolf);
                        break;
                        
                    //case 10 (gelatinous cube)
                    case 10:
                        makeRunMessage(gelatinousCube);
                        break;
                        
                    //case 11 (dragon)
                    case 11:
                        makeRunMessage(dragonBoss);
                        break;
                        
                    //case 12 (hydra)
                    case 12:
                        makeRunMessage(hydraBoss);
                        break;
                        
                    //case 13 (kraken)
                    case 13:
                        makeRunMessage(krakenBoss);
                        break;
                        
                    //case 14 (lich)
                    case 14:
                        makeRunMessage(lichBoss);
                        break;
                }
            }
        }
        
        if(event.getSource() == quit){
            createEndScreen("");
        }
        
        if(event.getSource() == statusReport){
            JOptionPane.showMessageDialog(null, statusMessage);
        }
        
        if(event.getSource() == npcButton){
            JOptionPane.showMessageDialog(null, npcStatus);
        }
    } 
    
    
    
    
    
    //ATTACK SCREEN
    public void createAttackScreen(String mainIn, NPC enemyIn){
        //PLAY SOUND
        String battleFilepath = "Music and Sound Effects//Exclamation_Point.wav";
        battleSound = new Sound();
        buttonSound.playSound(battleFilepath);

        //CREATE BUTTON AND MAIN MESSAGES
        mainMessage = mainIn;
        choice1message = "Attack";
        choice2message = "Special Move";
        choice3message = "Finishing Move";
        choice4message = "Use Healing Item";
        choice5message = "Run";
        
        //SET BUTTON AND MAIN MESSAGES
        choice1.setText(choice1message);
        choice2.setText(choice2message);
        choice3.setText(choice3message);
        choice4.setText(choice4message);
        choice5.setText(choice5message);
        mainTextArea.setText(mainMessage);
        
        //SET NPC STATUS
        npcStatus = enemyIn.toString();
        
        //KEEP TRACK OF ENEMY HP
        enemyHP = enemyIn.getHitPoints();
    }
    
    
    
    public void createBossScreen(String mainIn, NPC bossIn){
        //PLAY BATTLE SOUND
        String bossFilepath = "Music and Sound Effects//inceptionbutton.wav";
        bossBattleSound = new Sound();
        buttonSound.playSound(bossFilepath);
        
        //CREATE BUTTON AND MAIN MESSAGES
        mainMessage = mainIn;
        choice1message = "Attack";
        choice2message = "Special Move";
        choice3message = "Finishing Move";
        choice4message = "Use Healing Item";
        choice5message = "Run";
        
        //SET BUTTON AND MAIN MESSAGES
        choice1.setText(choice1message);
        choice2.setText(choice2message);
        choice3.setText(choice3message);
        choice4.setText(choice4message);
        choice5.setText(choice5message);
        mainTextArea.setText(mainMessage);
        
        //SET NPC STATUS
        npcStatus = bossIn.toString();
        
        //KEEP TRACK OF ENEMY HP
        bossHP = bossIn.getHitPoints();
    }
    
    
    
    
    //TRAVEL SCREEN
    public void createTravelScreen(String mainIn){
        //CREATE BUTTON AND MAIN MESSAGES
        mainMessage = mainIn;
        choice1message = "Go North";
        choice2message = "Go South";
        choice3message = "Go East";
        choice4message = "Go West";
        choice5message = "Curl up in a ball";
        
        //SET BUTTON AND MAIN MESSAGES
        choice1.setText(choice1message);
        choice2.setText(choice2message);
        choice3.setText(choice3message);
        choice4.setText(choice4message);
        choice5.setText(choice5message);
        mainTextArea.setText(mainMessage);
        
        //SET/RESET NPC STATUS TO NO ENEMY
        npcStatus = "NO CURRENT ENEMY";
    }
    
    
    
    
    
    
    //END SCREEN
    public void createEndScreen(String messageIn){
        //TURN PREVIOUS SCREEN OFF
        choiceButtonPanel.setVisible(false);
        quitButtonPanel.setVisible(false);
        statusButtonPanel.setVisible(false);
        npcButtonPanel.setVisible(false);
        
        //MAKE START BUTTON VISIBLE AND CHANGE MESSAGE TO "AGAIN?"
        startButtonPanel.setVisible(true);
        startButtonText = "Again?";
        startButton.setText(startButtonText);
        
        //CREATE END MESSAGE
        endMessage = messageIn + "END GAME! THANKS FOR PLAYING!\n\nFINAL STATUS:\n" + statusMessage + "";
        mainTextArea.setText(endMessage);
        mainTextArea.setFont(smallerFont);
        
        //CREATE "DO YOU WANT TO QUIT THE GAME" MESSAGE
        endFrame = new JFrame("Exit");
        if (JOptionPane.showConfirmDialog(endFrame,"confirm if you Want to Exit","Name of the Application or Title",
            JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
            System.exit(0);
        }
    }
    
    
    
    
    
    
    
    //RECREATE SELECT CHARACTER SCREEN AFTER END SCREEN "AGAIN" IS CLICKED
    public void reCreateCharacterScreen(){
        //MAKE BUTTONS VISIBLE
        choiceButtonPanel.setVisible(true);
        quitButtonPanel.setVisible(true);
        statusButtonPanel.setVisible(true);
        npcButtonPanel.setVisible(true);
        
        //MAKE START BUTTON INVISIBLE
        startButtonPanel.setVisible(false);
        
        //WRITE BUTTON AND MAIN MESSAGES
        mainMessage = "Welcome back to JAVA QUEST! \n\nWhen you are ready to create your character, \nchoose one of 5 character classes listed below!";
        choice1message = "Knight";
        choice2message = "Healer";
        choice3message = "Thief";
        choice4message = "Wizard";
        choice5message = "Bard";
        
        //CHANGE BUTTON AND MAIN MESSAGES
        mainTextArea.setText(mainMessage);
        choice1.setText(choice1message);
        choice2.setText(choice2message);
        choice3.setText(choice3message);
        choice4.setText(choice4message);
        choice5.setText(choice5message);
        mainTextArea.setFont(normalFont);
    }
    
    
    
    
    //CREATE ATTACK MESSAGE
    public void makeAttackMessage(NPC enemyIn){
        switch(mainIdentifier){
            //default
            default:
                break;
            //case 1 (Knight)
            case 1:
                enemyAttack = rand.nextBoolean();
                mainAttack = rand.nextBoolean();
                if(mainAttack == true){
                    if(enemyAttack == true){
                        //DISPLAY MESSAGE AND DEAL DAMAGE
                        mainMessage = "You attack the " + enemyIn.getName() + " and deal 4 points of damage.\n The " + enemyIn.getName() + " attacks you and deals " + enemyIn.getDamage() + " points of damage.\n\nWhat do you do?";
                        mainTextArea.setText(mainMessage);
                        mainCharacter.setHP(mainCharacter.getHP() - enemyIn.getDamage());
                        enemyIn.setHitPoints(enemyIn.getHitPoints() - 4);
                        
                        //DETERMINE IF PLAYER/ENEMY DIED
                        if(mainCharacter.getHP() <= 0 ){
                            createEndScreen("YOU DIED! ");
                            playerKilledSound.playSound(wilhelmFilepath);
                        }
                        if(enemyIn.getHitPoints() <= 0){
                            createTravelScreen("You killed the " + enemyIn.getName() + ". \nCongratulations! you get 2 points.\n\nWhat would you like to do?");
                            mainCharacter.setScore(mainCharacter.getScore() + 2);
                            statusMessage = mainCharacter.toString();
                        }
                        
                        //CHANGE STATUS MESSAGE
                        npcStatus = enemyIn.toString();
                        statusMessage = mainCharacter.toString();
                    }
                    else{
                        //DISPLAY MESSAGE AND DEAL DAMAGE
                        mainMessage = "You attack the " + enemyIn.getName() + " and deal 4 points of damage.\n The " + enemyIn.getName() + " tries to attack you and misses.\n\nWhat do you do?";
                        mainTextArea.setText(mainMessage);
                        enemyIn.setHitPoints(enemyIn.getHitPoints() - 4);
                        npcStatus = enemyIn.toString();
                        
                        //DETERMINE IF ENEMY IS DEAD
                        if(enemyIn.getHitPoints() <= 0){
                            createTravelScreen("You killed the " + enemyIn.getName() + ". \nCongratulations! you get 2 points.\n\nWhat would you like to do?");
                            mainCharacter.setScore(mainCharacter.getScore() + 2);
                            statusMessage = mainCharacter.toString();
                        }
                    }
                }
                else{
                    if(enemyAttack == true){
                        //DISPLAY MESSAGE AND DEAL MESSAGE
                        mainMessage = "You try to attack the " + enemyIn.getName() + " but miss.\n The " + enemyIn.getName() + " attacks you and deals " + enemyIn.getDamage() + " points of damage.\n\nWhat do you do?";
                        mainTextArea.setText(mainMessage);
                        mainCharacter.setHP(mainCharacter.getHP() - enemyIn.getDamage());
                        statusMessage = mainCharacter.toString();
                        
                        //DETERMINE IF PLAYER IS DEAD
                        if(mainCharacter.getHP() <= 0 ){
                            createEndScreen("YOU DIED! ");
                            playerKilledSound.playSound(wilhelmFilepath);
                        }
                    }
                    else{
                        //DISPLAY MESSAGE
                        mainMessage = "You try to attack the " + enemyIn.getName() + " but miss.\n The " + enemyIn.getName() + " tries to attack you and misses.\n\nWhat do you do?";
                        mainTextArea.setText(mainMessage);
                    }
                }
                break;
                
            //case 2 (Healer)
            case 2:
                enemyAttack = rand.nextBoolean();
                mainAttack = rand.nextBoolean();
                if(mainAttack == true){
                    if(enemyAttack == true){
                        //DISPLAY MESSAGE AND DEAL DAMAGE
                        mainMessage = "You attack the " + enemyIn.getName() + " and deal 2 points of damage.\n The " + enemyIn.getName() + " attacks you and deals " + enemyIn.getDamage() + " points of damage.\n\nWhat do you do?";
                        mainTextArea.setText(mainMessage);
                        mainCharacter.setHP(mainCharacter.getHP() - enemyIn.getDamage());
                        enemyIn.setHitPoints(enemyIn.getHitPoints() - 2);
                        
                        //DETERMINE IF ENEMY/PLAYER ARE DEAD
                        if(mainCharacter.getHP() <= 0 ){
                            createEndScreen("YOU DIED! ");
                            playerKilledSound.playSound(wilhelmFilepath);
                        }
                        if(enemyIn.getHitPoints() <= 0){
                            createTravelScreen("You killed the " + enemyIn.getName() + ". \nCongratulations! you get 2 points.\n\nWhat would you like to do?");
                            mainCharacter.setScore(mainCharacter.getScore() + 2);
                            statusMessage = mainCharacter.toString();
                        }
                        
                        //SET STATUS MESSAGE
                        npcStatus = enemyIn.toString();
                        statusMessage = mainCharacter.toString();
                    }
                    else{
                        //DISPLAY MESSAGE AND DEAL DAMAGE
                        mainMessage = "You attack the " + enemyIn.getName() + " and deal 2 points of damage.\n The " + enemyIn.getName() + " tries to attack you and misses.\n\nWhat do you do?";
                        mainTextArea.setText(mainMessage);
                        enemyIn.setHitPoints(enemyIn.getHitPoints() - 2);
                        npcStatus = enemyIn.toString();
                        
                        //DETERMINE IF ENEMY IS DEAD
                        if(enemyIn.getHitPoints() <= 0){
                            createTravelScreen("You killed the " + enemyIn.getName() + ". \nCongratulations! you get 2 points.\n\nWhat would you like to do?");
                            mainCharacter.setScore(mainCharacter.getScore() + 2);
                            statusMessage = mainCharacter.toString();
                        }
                    }
                }
                else{
                    if(enemyAttack == true){
                        //DISPLAY MESSAGE AND DEAL DAMAGE
                        mainMessage = "You try to attack the " + enemyIn.getName() + " but miss.\n The " + enemyIn.getName() + " attacks you and deals " + enemyIn.getDamage() + " points of damage.\n\nWhat do you do?";
                        mainTextArea.setText(mainMessage);
                        mainCharacter.setHP(mainCharacter.getHP() - enemyIn.getDamage());
                        statusMessage = mainCharacter.toString();
                        
                        //DETERMINE IF PLAYER IS DEAD
                        if(mainCharacter.getHP() <= 0 ){
                            createEndScreen("YOU DIED! ");
                            playerKilledSound.playSound(wilhelmFilepath);
                        }
                    }
                    else{
                        //DISPLAY MESSAGE
                        mainMessage = "You try to attack the " + enemyIn.getName() + " but miss.\n The " + enemyIn.getName() + " tries to attack you and misses.\n\nWhat do you do?";
                        mainTextArea.setText(mainMessage);
                    }
                }
                break;
                
            //case 3 (Thief)
            case 3:
                enemyAttack = rand.nextBoolean();
                mainAttack = rand.nextBoolean();
                if(mainAttack == true){
                    if(enemyAttack == true){
                        //DISPLAY MESSAGE AND DEAL DAMAGE
                        mainMessage = "You attack the " + enemyIn.getName() + " and deal 3 points of damage.\n The " + enemyIn.getName() + " attacks you and deals " + enemyIn.getDamage() + " points of damage.\n\nWhat do you do?";
                        mainTextArea.setText(mainMessage);
                        mainCharacter.setHP(mainCharacter.getHP() - enemyIn.getDamage());
                        enemyIn.setHitPoints(enemyIn.getHitPoints() - 3);
                        
                        //DETERMINE IF ENEMY/PLAYER ARE DEAD
                        if(mainCharacter.getHP() <= 0 ){
                            createEndScreen("YOU DIED! ");
                        }
                        if(enemyIn.getHitPoints() <= 0){
                            createTravelScreen("You killed the " + enemyIn.getName() + ". \nCongratulations! you get 2 points.\n\nWhat would you like to do?");
                            mainCharacter.setScore(mainCharacter.getScore() + 2);
                            statusMessage = mainCharacter.toString();
                        }
                        
                        //SET STATUS
                        npcStatus = enemyIn.toString();
                        statusMessage = mainCharacter.toString();
                    }
                    else{
                        //DISPLAY MESSAGE AND DEAL DAMAGE
                        mainMessage = "You attack the " + enemyIn.getName() + " and deal 3 points of damage.\n The " + enemyIn.getName() + " tries to attack you and misses.\n\nWhat do you do?";
                        mainTextArea.setText(mainMessage);
                        enemyIn.setHitPoints(enemyIn.getHitPoints() - 3);
                        npcStatus = enemyIn.toString();
                        
                        //DETERMINE IF ENEMY IS DEAD
                        if(enemyIn.getHitPoints() <= 0){
                            createTravelScreen("You killed the " + enemyIn.getName() + ". \nCongratulations! you get 2 points.\n\nWhat would you like to do?");
                            mainCharacter.setScore(mainCharacter.getScore() + 2);
                            statusMessage = mainCharacter.toString();
                        }
                    }
                }
                else{
                    if(enemyAttack == true){
                        //DISPLAY MESSAGE AND DEAL DAMAGE
                        mainMessage = "You try to attack the " + enemyIn.getName() + " but miss.\n The " + enemyIn.getName() + " attacks you and deals " + enemyIn.getDamage() + " points of damage.\n\nWhat do you do?";
                        mainTextArea.setText(mainMessage);
                        mainCharacter.setHP(mainCharacter.getHP() - enemyIn.getDamage());
                        statusMessage = mainCharacter.toString();
                        
                        //DETERMINE IF PLAYER IS DEAD
                        if(mainCharacter.getHP() <= 0 ){
                            createEndScreen("YOU DIED! ");
                            playerKilledSound.playSound(wilhelmFilepath);
                        }
                    }
                    else{
                        //DISPLAY MESSAGE
                        mainMessage = "You try to attack the " + enemyIn.getName() + " but miss.\n The " + enemyIn.getName() + " tries to attack you and misses.\n\nWhat do you do?";
                        mainTextArea.setText(mainMessage);
                    }
                }
                break;
                
            //case 4 (Wizard)
            case 4:
                enemyAttack = rand.nextBoolean();
                mainAttack = rand.nextBoolean();
                if(mainAttack == true){
                    if(enemyAttack == true){
                        //DISPLAY MESSAGE AND DEAL DAMAGE
                        mainMessage = "You attack the " + enemyIn.getName() + " and deal 1 point of damage.\n The " + enemyIn.getName() + " attacks you and deals " + enemyIn.getDamage() + " points of damage.\n\nWhat do you do?";
                        mainTextArea.setText(mainMessage);
                        mainCharacter.setHP(mainCharacter.getHP() - enemyIn.getDamage());
                        enemyIn.setHitPoints(enemyIn.getHitPoints() - 1);
                        
                        //DETERMINE IF ENEMY/PLAYER ARE DEAD
                        if(mainCharacter.getHP() <= 0 ){
                            createEndScreen("YOU DIED! ");
                            playerKilledSound.playSound(wilhelmFilepath);
                        }
                        if(enemyIn.getHitPoints() <= 0){
                            createTravelScreen("You killed the " + enemyIn.getName() + ". \nCongratulations! you get 2 points.\n\nWhat would you like to do?");
                            mainCharacter.setScore(mainCharacter.getScore() + 2);
                            statusMessage = mainCharacter.toString();
                        }
                        
                        //SET STATUS
                        npcStatus = enemyIn.toString();
                        statusMessage = mainCharacter.toString();
                    }
                    else{
                        //DISPLAY MESSAGE AND DEAL DAMAGE
                        mainMessage = "You attack the " + enemyIn.getName() + " and deal 1 point of damage.\n The " + enemyIn.getName() + " tries to attack you and misses.\n\nWhat do you do?";
                        mainTextArea.setText(mainMessage);
                        enemyIn.setHitPoints(enemyIn.getHitPoints() - 1);
                        npcStatus = enemyIn.toString();
                        
                        //DETERMINE IF ENEMY IS DEAD
                        if(enemyIn.getHitPoints() <= 0){
                            createTravelScreen("You killed the " + enemyIn.getName() + ". \nCongratulations! you get 2 points.\n\nWhat would you like to do?");
                            mainCharacter.setScore(mainCharacter.getScore() + 2);
                            statusMessage = mainCharacter.toString();
                        }
                    }
                }
                else{
                    if(enemyAttack == true){
                        //DISPLAY MESSAGE AND DEAL DAMAGE
                        mainMessage = "You try to attack the " + enemyIn.getName() + " but miss.\n The " + enemyIn.getName() + " attacks you and deals " + enemyIn.getDamage() + " points of damage.\n\nWhat do you do?";
                        mainTextArea.setText(mainMessage);
                        mainCharacter.setHP(mainCharacter.getHP() - enemyIn.getDamage());
                        statusMessage = mainCharacter.toString();
                        
                        //DETERMINE IF PLAYER IS DEAD
                        if(mainCharacter.getHP() <= 0 ){
                            createEndScreen("YOU DIED! ");
                            playerKilledSound.playSound(wilhelmFilepath);
                        }
                    }
                    else{
                        //DISPLAY MESSAGE
                        mainMessage = "You try to attack the " + enemyIn.getName() + " but miss.\n The " + enemyIn.getName() + " tries to attack you and misses.\n\nWhat do you do?";
                        mainTextArea.setText(mainMessage);
                    }
                }
                break;
                
            //case 5 (Bard)
            case 5:
                enemyAttack = rand.nextBoolean();
                mainAttack = rand.nextBoolean();
                if(mainAttack == true){
                    if(enemyAttack == true){
                        //DISPLAY MESSAGE AND DEAL DAMAGE
                        mainMessage = "You attack the " + enemyIn.getName() + " and deal 2 points of damage.\n The " + enemyIn.getName() + " attacks you and deals " + enemyIn.getDamage() + " points of damage.\n\nWhat do you do?";
                        mainTextArea.setText(mainMessage);
                        mainCharacter.setHP(mainCharacter.getHP() - enemyIn.getDamage());
                        enemyIn.setHitPoints(enemyIn.getHitPoints() - 2);
                        
                        //DETERMINE IF ENEMY/PLAYER ARE DEAD
                        if(mainCharacter.getHP() <= 0 ){
                            createEndScreen("YOU DIED! ");
                            playerKilledSound.playSound(wilhelmFilepath);
                        }
                        if(enemyIn.getHitPoints() <= 0){
                            createTravelScreen("You killed the " + enemyIn.getName() + ". \nCongratulations! you get 2 points.\n\nWhat would you like to do?");
                            mainCharacter.setScore(mainCharacter.getScore() + 2);
                            statusMessage = mainCharacter.toString();
                        }
                        
                        //SET STATUS
                        npcStatus = enemyIn.toString();
                        statusMessage = mainCharacter.toString();
                    }
                    else{
                        //DISPLAY MESSAGE AND DEAL DAMAGE
                        mainMessage = "You attack the " + enemyIn.getName() + " and deal 2 points of damage.\n The " + enemyIn.getName() + " tries to attack you and misses.\n\nWhat do you do?";
                        mainTextArea.setText(mainMessage);
                        enemyIn.setHitPoints(enemyIn.getHitPoints() - 2);
                        npcStatus = enemyIn.toString();
                        
                        //DETERMINE IF ENEMY IS DEAD
                        if(enemyIn.getHitPoints() <= 0){
                            createTravelScreen("You killed the " + enemyIn.getName() + ". \nCongratulations! you get 2 points.\n\nWhat would you like to do?");
                            mainCharacter.setScore(mainCharacter.getScore() + 2);
                            statusMessage = mainCharacter.toString();
                        }
                    }
                }
                else{
                    if(enemyAttack == true){
                        //DISPLAY MESSAGE AND DEAL DAMAGE
                        mainMessage = "You try to attack the " + enemyIn.getName() + " but miss.\n The " + enemyIn.getName() + " attacks you and deals " + enemyIn.getDamage() + " points of damage.\n\nWhat do you do?";
                        mainTextArea.setText(mainMessage);
                        mainCharacter.setHP(mainCharacter.getHP() - enemyIn.getDamage());
                        statusMessage = mainCharacter.toString();
                        
                        //DETERMINE IF PLAYER IS DEAD
                        if(mainCharacter.getHP() <= 0 ){
                            createEndScreen("YOU DIED! ");
                            playerKilledSound.playSound(wilhelmFilepath);
                        }
                    }
                    else{
                        //DISPLAY MESSAGE
                        mainMessage = "You try to attack the " + enemyIn.getName() + " but miss.\n The " + enemyIn.getName() + " tries to attack you and misses.\n\nWhat do you do?";
                        mainTextArea.setText(mainMessage);
                    }
                }
                break;
        }
    }
    
    
    //CREATE HEALING MESSAGE
    public void makeHealingMessage(NPC enemyIn){
        enemyAttack = rand.nextBoolean();
        if(enemyAttack == true){
            //DISPLAY MESSAGE, HEAL, AND DEAL DAMAGE
            mainMessage = "You heal yourself for 5 points of damage, however the " + enemyIn.getName() + " deals " + enemyIn.getDamage() + " points of damage.\nWhat do you do?";
            mainTextArea.setText(mainMessage);
            mainCharacter.setHP(mainCharacter.getHP() + 5 - enemyIn.getDamage());
            statusMessage = mainCharacter.toString();
            
            //DETERMINE IF PLAYER IS DEAD
            if(mainCharacter.getHP() <= 0 ){
                createEndScreen("YOU DIED! ");
                playerKilledSound.playSound(wilhelmFilepath);
            }
        }
        else{
            mainMessage = "You heal yourself for 5 points of damage.\n The " + enemyIn.getName() + " tries to attack but misses.\n\nWhat do you do?";
            mainTextArea.setText(mainMessage);
            mainCharacter.setHP(mainCharacter.getHP() + 5);
            statusMessage = mainCharacter.toString();
        }
    }
    
    
    //CREATE ITEM MESSAGE
    public void makeFinishingMoveMessage(NPC enemyIn){
        if(enemyIn.getHitPoints() > 7){
            mainMessage = "You cannot use a finishing move until the \nenemy is at 7 HP or below.\n\nPlease make another selection.";
            mainTextArea.setText(mainMessage);
        }
        else{
            switch(mainIdentifier){
                //default
                default:
                    break;
                //case 1 (Knight)
                case 1:
                    //PLAY SOUND
                    finishHim.playSound(finishFilepath);
                    
                    //CREATE MESSAGE
                    mainMessage = "The " + enemyIn.getName() + " cowers in fear before you. You look it dead in the eyes as you unscrew the pommel from your sword. Then, with a heafty throw and a mighty yell you throw the pommel at the " + enemyIn.getName() + ", killing it. You get 10 points for using a finishing move.";
                    createTravelScreen(mainMessage);
                    mainCharacter.setScore(mainCharacter.getScore() + 10);
                    statusMessage = mainCharacter.toString();
                    break;
                
                //case 2 (Healer)
                case 2:
                    //PLAY SOUND
                    finishHim.playSound(finishFilepath);
                    
                    //CREATE MESSAGE
                    mainMessage = "You gaze at the " + enemyIn.getName() + " before you. Badly wounded, it looks back with hatred in it's eyes. In response, you give a warming smile and approach the " + enemyIn.getName() + " with your arms outstretched. The " + enemyIn.getName() + "'s eyes widen with fear, and it runs away. You get 10 points for using a finishing move.";
                    createTravelScreen(mainMessage);
                    mainCharacter.setScore(mainCharacter.getScore() + 10);
                    statusMessage = mainCharacter.toString();
                    break;
                
                //case 3 (Thief)
                case 3:
                    //PLAY SOUND
                    finishHim.playSound(finishFilepath);
                    
                    //CREATE MESSAGE
                    mainMessage = "The " + enemyIn.getName() + " looks badly injured. You crack a smile. Throwing a smoke bomb to give you the chance to disappear, you sneak out of the " + enemyIn.getName() + "'s sight and swiftly deliver the finishing blow. You get 10 points for using a finishing move.";
                    createTravelScreen(mainMessage);
                    mainCharacter.setScore(mainCharacter.getScore() + 10);
                    statusMessage = mainCharacter.toString();
                    break;
                
                //case 4 (Wizard)
                case 4:
                    //PLAY SOUND
                    finishHim.playSound(finishFilepath);

                    //CREATE MESSAGE
                    mainMessage = "The Words of Power. Incredibly powerful spells only incredibly experienced spellcasters can master. As you gaze at the " + enemyIn.getName() + ", you prepare to utter one such word. Death. As you speak, the " + enemyIn.getName() + " dies instantly. You get 10 points for using a finishing move.";
                    createTravelScreen(mainMessage);
                    mainCharacter.setScore(mainCharacter.getScore() + 10);
                    statusMessage = mainCharacter.toString();
                    break;
                
                //case 5 (Bard)
                case 5:
                    //PLAY SOUND
                    finishHim.playSound(finishFilepath);
                    
                    //CREATE MESSAGE
                    mainMessage = "'Everybody clap your hands!' you shout. Suddenly and compusively the " + enemyIn.getName() + " begins to clap their appendages together. You get up real close, and with a smile on your face strike a chord on your lute. 'Die'. The " + enemyIn.getName() + " dies instantly. You get 10 points for using a finishing move.";
                    createTravelScreen(mainMessage);
                    mainCharacter.setScore(mainCharacter.getScore() + 10);
                    statusMessage = mainCharacter.toString();
                    break;
            }
        }
    }
    
    
    //CREATE RUN MESSAGE
    public void makeRunMessage(NPC enemyIn){
        mainEscape = rand.nextBoolean();
        if(mainEscape == true){
            mainMessage = "You successfully ran away from the fight.\nRetreat is not always cowardice.\n\nWhat do you do now?";
            createTravelScreen(mainMessage);
        }
        else{
            mainMessage = "You were unsuccessful in your run attempt!\n\nWhat do you do?";
            mainTextArea.setText(mainMessage);
        }
    }
    
    
    //CREATE SPECIAL MOVE MESSAGE
    public void makeSpecialMove(NPC enemyIn){
        switch(mainIdentifier){
            //default
            default:
                break;
            //case 1 (Knight)
            case 1:
                //DISPLAY MESSAGE AND DEAL DAMAGE
                mainMessage = "You swing your sword in a large downward arc and deal 6 points of damage against the " + enemyIn.getName() + "\nYou get 2 points for using your special move.\n\nWhat do you do?";
                mainTextArea.setText(mainMessage);
                enemyIn.setHitPoints(enemyIn.getHitPoints() - 6);
                npcStatus = enemyIn.toString();
                
                //TEST IF ENEMY IS DEAD
                if(enemyIn.getHitPoints() <= 0){
                    createTravelScreen("You killed the " + enemyIn.getName() + ". \nCongratulations! you get 4 points.\n\nWhat would you like to do?");
                    mainCharacter.setScore(mainCharacter.getScore() + 2);
                    statusMessage = mainCharacter.toString();
                }
                break;
                
            //case 2 (Healer)
            case 2:
                mainMessage = "You give the " + enemyIn.getName() + " some sleeping powder. It falls asleep and you are able to leave. \nYou get 2 points for using your special move.\n\nWhat would you like to do?";
                createTravelScreen(mainMessage);
                mainCharacter.setScore(mainCharacter.getScore() + 2);
                statusMessage = mainCharacter.toString();
                break;
                
            //case 3 (Thief)
            case 3:
                mainMessage = "You make the " + enemyIn.getName() + " fall victim to one of the classic blunders by yelling 'What's that?!' and pointing in the distance. When the " + enemyIn.getName() + " looks, you take the opprotunity to escape.\nYou get 2 points for using your special move.\nWhat do you do?";
                createTravelScreen(mainMessage);
                mainCharacter.setScore(mainCharacter.getScore() + 2);
                statusMessage = mainCharacter.toString();
                break;
                
            //case 4 (Wizard)
            case 4:
                //DISPLAY MESSAGE AND DEAL DAMAGE
                mainMessage = "You cast a powerful attack spell and deal 8 points of damage against the " + enemyIn.getName() + "\nYou get 2 points for using your special move.\n\nWhat do you do?";
                mainTextArea.setText(mainMessage);
                enemyIn.setHitPoints(enemyIn.getHitPoints() - 8);
                npcStatus = enemyIn.toString();
                mainCharacter.setScore(mainCharacter.getScore() + 2);
                statusMessage = mainCharacter.toString();
                
                //TEST IF ENEMY IS DEAD
                if(enemyIn.getHitPoints() <= 0){
                    createTravelScreen("You killed the " + enemyIn.getName() + ". \nCongratulations! you get 4 points.\n\nWhat would you like to do?");
                    mainCharacter.setScore(mainCharacter.getScore() + 2);
                    statusMessage = mainCharacter.toString();
                }
                break;
                
            //case 5 (Bard)
            case 5:
                mainMessage = "You begin to play a soothing tune on your lute. This has an effect on the " + enemyIn.getName() + " causing it to calm down, and walk away.\nYou get 2 points for using your special move.\nWhat do you do?";
                createTravelScreen(mainMessage);
                mainCharacter.setScore(mainCharacter.getScore() + 2);
                statusMessage = mainCharacter.toString();
                break;
        }
    }
    
    
    
    
    
    
    //GENERATE RANDOM EVENT
    public void generateRandomEvent(){
        typeChoice = rand.nextInt(2);
        
        switch(typeChoice){
            //ENEMY ENCOUNTER
            case 0:
                //GENERATE RANDOM NUMBER TO DETERMINE ENEMY
                enemyChoice = rand.nextInt(11);
                
                //SWITCH STATEMENT FOR EACH ENEMY TYPE
                switch(enemyChoice){
                    //zombie
                    case 0:
                        createAttackScreen("BATTLE\nA Zombie has appeared! \n\nWhat will you do?!", zombie);
                        npcIdentifier = 1;
                        break;
                    
                    //bandit
                    case 1:
                        createAttackScreen("BATTLE\nA Bandit has appeared! \n\n What will you do?!", bandit);
                        npcIdentifier = 2;
                        break;
                    
                    //lobbyist
                    case 2:
                        createAttackScreen("BATTLE\nA Lobbyist has appeared! \n\nWhat will you do?!", lobbyist);
                        npcIdentifier = 3;
                        break;
                        
                    //giant spider
                    case 3:
                        createAttackScreen("BATTLE\nA Giant Spider has appeared! \n\nWhat will you do?!", giantSpider);
                        npcIdentifier = 4;
                        break;
                        
                    //chimera
                    case 4:
                        createAttackScreen("BATTLE\nA Chimera has appeared! \n\nWhat will you do?!", chimera);
                        npcIdentifier = 5;
                        break;
                        
                    //elemental
                    case 5:
                        createAttackScreen("BATTLE\nAn Elemental has appeared! \n\nWhat will you do?!", elemental);
                        npcIdentifier = 6;
                        break;
                        
                    //goblin
                    case 6:
                        createAttackScreen("BATTLE\nA Goblin has appeared! \n\nWhat will you do?!", goblin);
                        npcIdentifier = 7;
                        break;
                        
                    //griffon
                    case 7:
                        createAttackScreen("BATTLE\nA Griffon has appeared! \n\n What will you do?!", griffon);
                        npcIdentifier = 8;
                        break;
                        
                    //werewolf
                    case 8:
                        createAttackScreen("BATTLE\nA Werewolf has appeared! \n\nWhat will you do?!", werewolf);
                        npcIdentifier = 9;
                        break;
                        
                    //gelatinous cube
                    case 9:
                        createAttackScreen("BATTLE\nA Gelatinous Cube has appeared! \n\nWhat will you do?!", gelatinousCube);
                        npcIdentifier = 10;
                        break;
                    
                    //boss
                    case 10:
                        bossChoice = rand.nextInt(4);
                        switch(bossChoice){
                            //dragon
                            case 0:
                                createBossScreen("BOSS BATTLE\nA mighty red DRAGON has appeared to confront you!\n\nWHAT WILL YOU DO?!", dragonBoss);
                                npcIdentifier = 11;
                                break;
                                
                            //hydra
                            case 1:
                                createBossScreen("BOSS BATTLE\nA powerful green HYDRA has appeared to \nconfront you!\n\nWHAT WILL YOU DO?!", hydraBoss);
                                npcIdentifier = 12;
                                break;
                                
                            //kraken
                            case 2:
                                createBossScreen("BOSS BATTLE\nYou have just stumbled upon the legendary KRAKEN! \n\nWHAT WILL YOU DO?!", krakenBoss);
                                npcIdentifier = 13;
                                break;
                                
                            //acererak
                            case 3:
                                createBossScreen("BOSS BATTLE\nBefore you lies a skull. As you approach, black smoke rises and takes the form of a powerful LICH!\n\nWHAT WILL YOU DO?!", lichBoss);
                                npcIdentifier = 14;
                                break;
                            //default
                            default:
                                mainMessage = "This message should not apppear. If this message appears, make another selection.";
                                choice1message = "Go North";
                                choice2message = "Go South";
                                choice3message = "Go East";
                                choice4message = "Go West";
                                choice5message = "Curl up in a ball";
                
                                mainTextArea.setText(mainMessage);
                                choice1.setText(choice1message);
                                choice2.setText(choice2message);
                                choice3.setText(choice3message);
                                choice4.setText(choice4message);
                                choice5.setText(choice5message);
                                break;
                        }
                        break;
                    
                    //default    
                    default:
                        mainMessage = "This message should not apppear. If this message appears, make another selection.";
                        choice1message = "Go North";
                        choice2message = "Go South";
                        choice3message = "Go East";
                        choice4message = "Go West";
                        choice5message = "Curl up in a ball";
                
                        mainTextArea.setText(mainMessage);
                        choice1.setText(choice1message);
                        choice2.setText(choice2message);
                        choice3.setText(choice3message);
                        choice4.setText(choice4message);
                        choice5.setText(choice5message);
                        break;
                }
                break;
            
                
            //NEW LOCATION
            case 1:
                travelChoice = rand.nextInt(11);
                switch(travelChoice){
                    //location 1
                    case 0:
                        createTravelScreen("There's nothing here...\n\nWhat do you do?");
                        break;
                        
                    //location 2
                    case 1:
                        createTravelScreen("There are some nice trees around here...\n\nWhat do you do?");
                        break;
                        
                    //location 3
                    case 2:
                        createTravelScreen("There is an interesting cottage in the distance\nWhen you investigate, you find some nice \nclothes...\n\nWhat do you do?");
                        break;
                        
                    //location 4
                    case 3:
                        createTravelScreen("Bathroom Break!\nYou take a break to do your business...\n\nWhat do you do?");
                        break;
                        
                    //location 5
                    case 4:
                        createTravelScreen("You find a peculiar building. Inside you find \nsome armor. You put it on...\nYour HP has been increased by 5, and your \nscore by 3\n\nWhat do you do?");
                        mainCharacter.setHP(mainCharacter.getHP() + 5);
                        mainCharacter.setScore(mainCharacter.getScore() + 3);
                        statusMessage = mainCharacter.toString();
                        break;
                        
                    //location 6
                    case 5:
                        createTravelScreen("As you travel you find a body lying near the \npath...\n\nWhat do you do?");
                        break;
                        
                    //location 7
                    case 6:
                        createTravelScreen("You feel like you have been walking for hours.\n\nWhat do you do");
                        break;
                        
                    //location 8
                    case 7:
                        createTravelScreen("You come close to being noticed by a Zombie,but you manage to steer clear before an \nencounter can begin...\n\nWhat do you do");
                        break;
                        
                    //location 9
                    case 8:
                        createTravelScreen("As you walk you hear a voice in your head say\n..." + mainCharacter.getName() + "...\nThey sound like they've been dead for a long \ntime...\nWhat do you do?");
                        break;

                    //location 10
                    case 9:
                        createTravelScreen("You realize that you have somehow ended up going in a complete circle and ending up in \nthe same place you started...\n\nWhat do you do?");
                        break;
                        
                    //location 11
                    case 10:
                        createTravelScreen("You hear a cry for help in the distance...\n\nWhat do you do?");
                        break;
                        
                    //default
                    default:
                        mainMessage = "This message should not apppear. If this message appears, make another selection.";
                        choice1message = "Go North";
                        choice2message = "Go South";
                        choice3message = "Go East";
                        choice4message = "Go West";
                        choice5message = "Curl up in a ball";
                
                        mainTextArea.setText(mainMessage);
                        choice1.setText(choice1message);
                        choice2.setText(choice2message);
                        choice3.setText(choice3message);
                        choice4.setText(choice4message);
                        choice5.setText(choice5message);
                        break;
                }
                break;
            
                
            //DEFAULT SO IT DOESN'T YELL AT ME    
            default:
                mainMessage = "This message should not apppear. If it appears, make another selection";
                choice1message = "Go North";
                choice2message = "Go South";
                choice3message = "Go East";
                choice4message = "Go West";
                choice5message = "Curl up in a ball";
                
                mainTextArea.setText(mainMessage);
                choice1.setText(choice1message);
                choice2.setText(choice2message);
                choice3.setText(choice3message);
                choice4.setText(choice4message);
                choice5.setText(choice5message);
                break;
        }
    }
}
