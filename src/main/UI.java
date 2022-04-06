package main;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import entity.Entity;

import main.object.Obj_heart;



public class UI {

    
    GamePanel gp;
    Graphics2D g2;
    Font arial_40, arial_80B, arial_30, arial_20;
    BufferedImage lifeful1, lifehal1, lifeemp1;
    public boolean messageOn = false;
    ArrayList<String> message = new ArrayList<>();
    ArrayList<Integer> messageCounter = new ArrayList<>();
    public boolean gameFinished = false;
    public String currentDialogue = ""; 
    public int commandNum = 0;
    public int fightNum = 0;
    public int storyScreenState = 0;
    public int titleScreenState = 0;
    public int battleScreenState = 0; // the first screen 0, second 1, etc...
    public int slotCol = 0;
    public int slotRow = 0;
    public int monkToken = 0;
    public int battleLockCounter = 0;
    public String direction = "up";

    public UI(GamePanel gp) {
        this.gp = gp;
        

        arial_40 = new Font("Cambria", Font.PLAIN, 40);
        arial_80B = new Font("NSimSun", Font.BOLD, 80);
        arial_30 = new Font("Consolas", Font.PLAIN, 30);
        arial_20 = new Font("Consolas", Font.PLAIN, 24);
        //Obj_key key = new Obj_key(gp);
        //keyImage = key.image;
    

    //Create HUD OBJECTS
        Entity heart = new Obj_heart(gp);
        lifeful1 = heart.image;
        lifehal1 = heart.image2;
        lifeemp1 = heart.image3;

    }

public void addMessage(String text) {
    
    message.add(text);
    messageCounter.add(0);

}
public void draw(Graphics2D g2) {

    this.g2 = g2;

    g2.setFont(arial_40);
    g2.setColor(Color.white);

    //TITLE STATE
    if(gp.gameState == gp.titleState) {
        drawTitleScreen();
    }
    //PLAY STATE
    if(gp.gameState == gp.playState) {
        drawPlayerLife();
        drawMessage();
        // Do playState stuff later
    }
    //PAUSE STATE
    if(gp.gameState == gp.pauseState) {
        drawPauseScreen();
    }
    //DIALOGUE STATE
    if(gp.gameState == gp.dialogueState) {
        drawDialogueScreen();
    }

    //CHARACTER STATE
    if(gp.gameState == gp.characterState) {
        drawCharacterScreen();
        drawInventory();
    }
    //STORY STATE
    if(gp.gameState == gp.storyState) {
        drawStoryScreen();
    
    }
    if(gp.gameState == gp.battleState) {
        drawBattleScreen();
        drawChoiceFrame();
    }

}
public void drawPlayerLife() {

    int x = gp.tileSize/2;
    int y = gp.tileSize/2;
    int i = 0;

    // draw max life
    while (i < gp.player.maxLife/2) {
        g2.drawImage(lifeemp1, x, y, null);
        i++;
        x += gp.tileSize;
    }

    //RESET VALUES
    x = gp.tileSize/2;
    y = gp.tileSize/2;
    i = 0;

    //DRAW CURRENT VALUES
    while(i < gp.player.life) {
        g2.drawImage(lifehal1, x, y, null);
        i++;
        if(i < gp.player.life) {
            g2.drawImage(lifeful1, x, y, null);
        }
        i++;
        x += gp.tileSize;
    }
}
public void drawMessage() {
    int messageX = gp.tileSize;
    int messageY= gp.tileSize*4;
    g2.setFont(arial_20);

    for(int i = 0; i < message.size(); i++) {

        if(message.get(i) != null) {
            g2.setColor(Color.white);
            g2.drawString(message.get(i), messageX, messageY);
        
            int counter = messageCounter.get(i) + 1; //messageCounter ++
            messageCounter.set(i, counter);
            messageY += 50;

            if(messageCounter.get(i) > 1200) {
                message.remove(i);
                messageCounter.remove(i);
            }
        }
    }
}
public void drawTitleScreen() { 
    if(titleScreenState == 0) {

    g2.setColor(new Color(50, 105, 80));
    g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
    //TITLE NAME
    g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
    String text = "GREEN QUEST";
    int x = getXforCenteredText(text);
    int y = gp.tileSize*3;
    //shadow for text color
    g2.setColor(new Color(25, 145, 200));
    g2.drawString(text, x+5, y+5);
    //main color
    g2.setColor(Color.black);
    g2.drawString(text, x, y);

    //Green Quest Title image
    x = gp.screenWidth/2 - (gp.tileSize*2)/2;
    y += gp.tileSize*2;
    g2.drawImage(gp.player.up1, x, y, gp.tileSize*2, gp.tileSize*2, null);

    //MENU
    g2.setFont(g2.getFont().deriveFont(Font.BOLD, 34F));

    text = "New Game";
    x = getXforCenteredText(text);
    y += gp.tileSize*4;
    g2.drawString(text, x, y);
    if(commandNum == 0) {
        g2.drawString(">", x= gp.tileSize, y);

    }

    text = "Load Game";
    x = getXforCenteredText(text);
    y += gp.tileSize;
    g2.drawString(text, x, y);
    if(commandNum == 1) {
        g2.drawString(">", x= gp.tileSize, y);
        
    }

    text = "Quit";
    x = getXforCenteredText(text);
    y += gp.tileSize;
    g2.drawString(text, x, y);
    if(commandNum == 2) {
        g2.drawString(">", x= gp.tileSize, y);
        
    }
    }
    else if(titleScreenState == 1) {

        //INTRO GAME SCREEN CHAR
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(30F));

        String text = "Select your class.";
        int x = getXforCenteredText(text);
        int y = gp.tileSize*3;
        g2.drawString(text, x, y);

        text = "Warrior";
        x = getXforCenteredText(text);
        y += gp.tileSize*3;
        g2.drawString(text, x, y);
        if(commandNum == 0) {
            g2.drawString(">", x=gp.tileSize, y);
            
        }
        text = "Thief";
        x = getXforCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if(commandNum == 1) {
            g2.drawString(">", x=gp.tileSize, y);
            
        }
        text = "Monk";
        x = getXforCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if(commandNum == 2) {
            g2.drawString(">", x=gp.tileSize, y);
            
        }
        text = "Back";
        x = getXforCenteredText(text);
        y += gp.tileSize*2;
        g2.drawString(text, x, y);
        if(commandNum == 3) {
            g2.drawString(">", x=gp.tileSize, y);
        
        }
    }
}
public void drawPauseScreen() {

    String text = "PAUSED";
    int x = getXforCenteredText(text);
    int y = gp.screenHeight/2;
    
    g2.drawString(text, x, y);
}

public void drawDialogueScreen() {
    //window for text in dialogue
    int x = gp.tileSize*2;
    int y = gp.tileSize/3;
    int width = gp.screenWidth - (gp.tileSize*4);
    int height = gp.tileSize*3;
    drawSubWindow(x, y, width, height);

    g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28));
    x += gp.tileSize;
    y += gp.tileSize;
    
    for(String line : currentDialogue.split("\n")) {
        g2.drawString(line, x, y);
        y += 40;

    }
}
public void drawStoryScreen() {

    if(storyScreenState == 0) {
        int x = 25; int y = 25;
        g2.setColor(new Color(0, 5, 0));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        //TITLE NAME
        
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 30F));
        String text = "Now is all there is...\nIt's all we have ever known.\nThe days are long and bleak.\nI remember though what it was like...\nWhen it began.";
        y += 30;

        for(String line : text.split("\n")) {
            y += 30;
        
        //main color
        g2.setColor(Color.white);
        g2.drawString(line, x, y);

        }
    }
}

public void drawBattleScreen() {
    if(storyScreenState == 0) {
       
        int x = 50; int y = 50;
        g2.setColor(new Color(0, 5, 0));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        String text;
        String text1;

        //draw background for battle1
        x = 5;
        y = 5;
        
        g2.drawImage(gp.background.world1, x - 10, y - 10, gp.screenWidth + 5, gp.screenHeight + 5, null);
                
        //TESTING TEXT IN FIELD 
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 30F));
        text = "BATTLE TEST";
        text1 = "TEST SECOND LINE";

        //CREATE A FRAME FOR ENEMY 
    final int frameX = gp.tileSize;
    final int frameY = gp.tileSize * 8;
    final int frameWidth = gp.tileSize*7;
    final int frameHeight = gp.tileSize*3;
    //frame that shows enemy 
    drawSubWindow(frameX, frameY, frameWidth, frameHeight);
    //frame that shows character
    drawSubWindow(frameX + 350, frameY, frameWidth, frameHeight);
    
    //TEXT IN THE CHAR STATS WINDOW
    g2.setColor(Color.black);
    g2.setFont(arial_20);

    int textX = frameX + 20;
    int textY = frameY + gp.tileSize;
    //final int lineHeight = 35;
    //WILL USE WHEN WE DRAW MULTIPLE CHARACTERS (final int lineHeight)
    
    g2.drawString(gp.enemy.enemyName + " HP: " + gp.enemy.eLife + "/" + gp.enemy.eMaxLife, textX, textY);
    
    g2.drawString(gp.player.playerName + " HP: " + gp.player.life + "/" + gp.player.maxLife , textX + 350, textY);
        
    //main color
        g2.setColor(Color.white);
        g2.drawString(text, x + 25, y + 25);
        g2.drawString(text1, x + 25, y + 100);
        }

        //draw characters on the screen
        int x = 5;
        int y = 5;
        
        battleLockCounter ++;

        int i = 1;
        if(battleLockCounter == 7) {

        //Random random = new Random();
        //int i = random.nextInt(75)+1; //pick up 1 to 75
            
        if(i <= 7) {
            g2.drawImage(gp.player.battle1, x*100, y*50, gp.tileSize*2, gp.tileSize*2, null);
            g2.drawImage(gp.enemy.enemy1, x*35, y*50, gp.tileSize*2, gp.tileSize*2, null);
            battleLockCounter ++;
            i++;
            i++;
            
        }
        if(i > 1 && i < 3) {
            g2.drawImage(gp.player.battle2, x*100, y*50, gp.tileSize*2, gp.tileSize*2, null);
            g2.drawImage(gp.enemy.enemy3, x*35, y*50, gp.tileSize*2, gp.tileSize*2, null);
            
            i++;
        }
            battleLockCounter = 0;
            
        }  else {
            g2.drawImage(gp.player.battle2, x*100, y*50, gp.tileSize*2, gp.tileSize*2, null);
            g2.drawImage(gp.enemy.enemy4, x*35, y*50, gp.tileSize*2, gp.tileSize*2, null);
            
            i = 0;
        }
            
            
}
public void drawChoiceFrame() { 
    int frameX = gp.tileSize;
    int frameY = gp.tileSize * 8;
    int frameWidth = gp.tileSize*7;
    int frameHeight = gp.tileSize*3;
    
    //FOR POP-UP MENU
    String text1 = "";
    int x = getXforCenteredText(text1);
    int y = gp.tileSize*3;

    

    //frame that shows choice window
    if(gp.gameState == gp.battleState) {
        if(gp.keyH.enterPressed == true) {
        //draw the window for battle options FIG, DEF, RUN
        drawSubWindow(frameX + 250, frameY, frameWidth, frameHeight);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 24F));  
        
 
    text1 = "Fight";
    x = getXforAlignToRightText(text1, x);
    y += gp.tileSize*6;
    g2.drawString(text1, x, y);
    if(commandNum == 0) {
        g2.drawString(">", x-15, y);
        if(gp.keyH.enterPressed == true) {
            gp.ui.fightNum = 1; 
        }
        }

    text1 = "Defend";
    y += gp.tileSize - 6;
    g2.drawString(text1, x, y);
    if(commandNum == 1) {
        g2.drawString(">", x-15, y);
        gp.ui.fightNum = 0;
    //}
    }
    //if(fightNum == 0) {
    text1 = "Run";
    y += gp.tileSize;
    g2.drawString(text1, x, y);
    if(commandNum == 2) {
        g2.drawString(">", x -15, y);
        gp.ui.fightNum = 0;
    //}
    }

    

//frame that comes after selecting FIGHT
 System.out.println("FightNum = " + fightNum);

 if(fightNum == 1) {
    drawSubWindow(frameX + 250, frameY, frameWidth + 250, frameHeight);
    g2.setFont(g2.getFont().deriveFont(Font.BOLD, 24F));
    text1 = "Punch";
    y -= gp.tileSize*2;
    g2.drawString(text1, x, y);
    if(commandNum == 0) {
        g2.drawString(">", x -15, y);
        //gp.ui.fightNum = 2;
        if(gp.ui.fightNum == 2) { 
            
            gp.ui.fightNum = 0;
        }
}

}
}
}
//fightNum = 0;

}

//}
public void drawCharacterScreen() {
    //CREATE A FRAME
    final int frameX = gp.tileSize * 2;
    final int frameY = gp.tileSize;
    final int frameWidth = gp.tileSize*5;
    final int frameHeight = gp.tileSize*11;
    drawSubWindow(frameX, frameY, frameWidth, frameHeight);
    //TEXT IN THE CHAR STATS WINDOW
    g2.setColor(Color.black);
    g2.setFont(arial_20);

    int textX = frameX + 20;
    int textY = frameY + gp.tileSize;
    final int lineHeight = 35;
    
    //NAMES 
    g2.drawString("Level", textX, textY);
    textY += lineHeight;
    g2.drawString("Life", textX, textY);
    textY += lineHeight;
    g2.drawString("Strength", textX, textY);
    textY += lineHeight;
    g2.drawString("Dexterity", textX, textY);
    textY += lineHeight;
    g2.drawString("Attack", textX, textY);
    textY += lineHeight;
    g2.drawString("Defense", textX, textY);
    textY += lineHeight;
    g2.drawString("Speed", textX, textY);
    textY += lineHeight;
    g2.drawString("Exp", textX, textY);
    textY += lineHeight;
    g2.drawString("Next Level", textX, textY);
    textY += lineHeight;
    g2.drawString("Coin", textX, textY);
    textY += lineHeight + 20;
    g2.drawString("Weapon", textX, textY);
    textY += lineHeight + 5;
    g2.drawString("Shield", textX, textY);
    textY += lineHeight;
    
    //values 
    int tailX = frameX + frameWidth - 30;
    //Reset textY
    textY = frameY + gp.tileSize;
    String value;
    
    value = String.valueOf(gp.player.level);
    textX = getXforAlignToRightText(value, tailX);
    g2.drawString(value, textX, textY);
    textY += lineHeight;

    value = String.valueOf(gp.player.life + "/" + gp.player.maxLife); {
    
    }
    textX = getXforAlignToRightText(value, tailX);
    g2.drawString(value, textX, textY);
    textY += lineHeight;
    
    value = String.valueOf(gp.player.strength);
    textX = getXforAlignToRightText(value, tailX);
    g2.drawString(value, textX, textY);
    textY += lineHeight;

    value = String.valueOf(gp.player.dexterity);
    textX = getXforAlignToRightText(value, tailX);
    g2.drawString(value, textX, textY);
    textY += lineHeight;

    value = String.valueOf(gp.player.attack);
    textX = getXforAlignToRightText(value, tailX);
    g2.drawString(value, textX, textY);
    textY += lineHeight;

    value = String.valueOf(gp.player.defense);
    textX = getXforAlignToRightText(value, tailX);
    g2.drawString(value, textX, textY);
    textY += lineHeight;

    value = String.valueOf(gp.player.speed);
    textX = getXforAlignToRightText(value, tailX);
    g2.drawString(value, textX, textY);
    textY += lineHeight;

    value = String.valueOf(gp.player.exp);
    textX = getXforAlignToRightText(value, tailX);
    g2.drawString(value, textX, textY);
    textY += lineHeight;

    value = String.valueOf(gp.player.nextLevelExp);
    textX = getXforAlignToRightText(value, tailX);
    g2.drawString(value, textX, textY);
    textY += lineHeight;

    value = String.valueOf(gp.player.coin);
    textX = getXforAlignToRightText(value, tailX);
    g2.drawString(value, textX, textY);
    textY += lineHeight;

    g2.drawImage(gp.player.currentWeapon.down1, tailX - gp.tileSize, textY - 10, null);
    textY += gp.tileSize;
    g2.drawImage(gp.player.currentShield.down1, tailX - gp.tileSize, textY - 10, null);

}

public void drawInventory() {

    //FRAME
    int frameX = gp.tileSize*8;
    int frameY = gp.tileSize;
    int frameWidth = gp.tileSize*6;
    int frameHeight = gp.tileSize*5;
    drawSubWindow(frameX, frameY, frameWidth, frameHeight);
    //SLOT
    final int slotXStart = frameX + 20;
    final int slotYStart = frameY + 20;
    int slotX = slotXStart;
    int slotY = slotYStart;
    int slotSize = gp.tileSize+4;

    //DRAW PLAYER'S ITEMS
    for(int i = 0; i < gp.player.inventory.size(); i++) {

        //EQUIP CURSOR
        if(gp.player.inventory.get(i) == gp.player.currentWeapon || gp.player.inventory.get(i) == gp.player.currentShield) {

        g2.setColor(new Color(200, 190, 90));
        g2.fillRoundRect(slotX, slotY, gp.tileSize, gp.tileSize, 10, 10);
    }
        g2.drawImage(gp.player.inventory.get(i).down1, slotX + 3, slotY + 3, null);
        
        slotX += slotSize;

        if(i == 4 || i == 9 || i == 14) {
            slotX = slotXStart;
            slotY += slotSize;
        }
    }

    // CURSOR
    int cursorX = slotXStart + slotSize* slotCol;
    int cursorY = slotYStart + slotSize* slotRow;
    int cursorWidth = slotSize;
    int cursorHeight = slotSize;
    //DRAW CURSOR
    g2.setColor(Color.white);
    g2.setStroke(new BasicStroke(3));
    g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);
    //DESCRIPTION FRAME
    int dFrameX = frameX;
    int dFrameY = frameY + frameHeight;
    int dFrameWidth = frameWidth;
    int dFrameHeight = gp.tileSize*3;
    
    //DRAW DESCRIPTION TEXT
    int textX = dFrameX + 20;
    int textY = dFrameY + gp.tileSize;
    g2.setFont(arial_20);

    int itemIndex = getItemIndexOnSlot();

    if(itemIndex < gp.player.inventory.size()) {
        drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight);
        for(String line: gp.player.inventory.get(itemIndex).description.split("\n")) {
            g2.drawString(line, textX, textY);
            textY += 17;
        }
    }
}
public int getItemIndexOnSlot() {
    int itemIndex = slotCol + (slotRow*5);
        return itemIndex;

}

public void drawSubWindow(int x, int y, int width, int height) {

    Color e = new Color(66, 157, 49, 220);
    g2.setColor(e);
    g2.fillRoundRect(x, y, width, height, 10, 10);

    e = new Color(5, 5, 5);
    g2.setColor(e);
    g2.setStroke(new BasicStroke(5));
    g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
}
    
public int getXforCenteredText(String text) {

    int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
    int x = gp.screenWidth/2 - length/2;
    return x;
}
public int getXforAlignToRightText(String text, int tailX) {

    int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
    int x = tailX - length;
    return x;
}

}