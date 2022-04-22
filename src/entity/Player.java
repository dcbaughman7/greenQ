package entity;

import main.GamePanel;
import main.UI;
import main.KeyHand;
import main.Main;
import main.object.Obj_shield_cherry;
import main.object.Obj_sword_cherry;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Color;

import java.awt.image.BufferedImage;
import java.util.ArrayList;



public class Player extends Entity{

    KeyHand keyH;

    public final int screenX;
    public final int screenY;
    int counter2 = 0; 
    public ArrayList<Entity> inventory = new ArrayList<>();
    public final int maxInventorySize = 20;
    int hasKey = 0;
    
    
    

    public Player(GamePanel gp, KeyHand keyH, UI ui) {
        
        super(gp);

        this.keyH = keyH;
        

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        //Collision for char (x, y, width, height)
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;
        

        setDefaultValues();
        getPlayerImage();
        setItems();
        getPlayerImageBattle();
    
    }
    public void setDefaultValues() {

        worldX = gp.tileSize * 25;
        worldY = gp.tileSize * 23;
        speed = 3;
        direction = "up";
        playerName = "Rex";

        //Base Stats
        
        
        level = 1;
        maxLife = 6;
        life = maxLife;
        
        strength = 1; // the more STR the more DMG output
        dexterity = 1;  // the more DEX the less DMG input
        exp = 0;
        nextLevelExp = 5;
        coin = 0;
        currentWeapon = new Obj_sword_cherry(gp);
        currentShield = new Obj_shield_cherry(gp);
        attack = getAttack(); // the TOT ATT value is decided by strength and weapon
        defense = getDefense(); // the TOT DEF value is decided by dexterity and shieldpackage entity;
    }
    public void setItems() {

        inventory.add(currentWeapon);
        
    }
    public int getAttack() {
        return attack = strength * currentWeapon.attackValue;
    }

    public int getDefense() {
        return defense = dexterity * currentShield.defenseValue;
    }


    public void getPlayerImage() {
        
    up1 = setup("/res/sprite/Rex1_over_back1");
    up2 = setup("/res/sprite/Rex1_over_back2"); 
    down1 = setup("/res/sprite/Rex1_over1");
    down2 = setup("/res/sprite/Rex1_over2");
    left1 = setup("/res/sprite/Rex1_over_sidel1");
    left2 = setup("/res/sprite/Rex1_over_sidel2");
    right1 = setup("/res/sprite/Rex1_over_sider1");
    right2 = setup("/res/sprite/Rex1_over_sider2");
    

    }
    public void getPlayerImageM() {

    up1 = setup("/res/sprite/Rexm_over1");
    up2 = setup("/res/sprite/Rexm_over2"); 
    down1 = setup("/res/sprite/Rexm_over_back1");
    down2 = setup("/res/sprite/Rexm_over_back2");
    left1 = setup("/res/sprite/Rexm_over_sidel1");
    left2 = setup("/res/sprite/Rexm_over_sidel2");
    right1 = setup("/res/sprite/Rexm_over_sider1");
    right2 = setup("/res/sprite/Rexm_over_sider2");

    }

    public void getPlayerImageBattle() {
    
    battle1 = setup("/res/sprite/RexBat_stand");
    battle2 = setup("/res/sprite/RexBat_walk");
    }
    
    public void update(){

        if(keyH.upPressed == true || keyH.downPressed == true || 
        keyH.leftPressed == true || keyH.rightPressed == true) {
                
                if(keyH.downPressed == true)  {
                    direction = "down";
                }
                else if(keyH.upPressed == true) {
                    direction = "up";
                }
                else if(keyH.leftPressed == true) {
                    direction = "left";
                }
                else if(keyH.rightPressed == true) {
                    direction = "right";
                }
                
                //CHECK TILE COLLISION
            
                collisionOn = false; 
                gp.cChecker.checkTile(this, false);

                //CHECK OBJECT COLLISION
                int objIndex = gp.cChecker.checkObject(this, true);
                pickUpObject(objIndex);
                //CHECK PERMANENT OBJECT COLLISION
                int pObjIndex = gp.cChecker.checkPerObject(this, true);
                
                //WEIRD HAD TO WRITE THIS TO MAKE IT WORK!!
                //CHECK NPC COLLISION
                int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
                interactNPC(npcIndex);
        

                if(collisionOn == false) {
                    
                    switch(direction) {
                    case "down": worldY += speed; break;
                    case "up": worldY -= speed; break;
                    case "left": worldX -= speed; break;
                    case "right": worldX += speed; break;
                    }
                }

                spriteCounter++;
                if(spriteCounter > 12) {
                    if(spriteNum == 1) {
                        spriteNum = 2;
                    }
                    else if(spriteNum == 2) {
                        spriteNum = 1;
                    }
                    spriteCounter = 0;
                }
            
                        gp.eHandler.checkEvent();
                        gp.keyH.enterPressed = false;
                     //IF COLLISION IS FALSE PLAYER CAN MOVE
                    }
                }
            
    public void pickUpObject(int i) {
            
            if(i != 999) {
                String text;
                if(inventory.size() != maxInventorySize) {

                    inventory.add(gp.obj[i]);
                    gp.playSE(4);
                    text = "Got a " + gp.obj[i].name + "!";
                }
                else {
                    text = "Rucksack is full!";
                }
                gp.ui.addMessage(text);
                gp.obj[i] = null;
                gp.pobj[i] = null;
            }
        }
    
        
    public void interactNPC(int i) {

        if(i != 999) {

            if(gp.keyH.enterPressed == true) {
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak(); 
            }
        }
    }
           
    public void draw(Graphics2D g2) {
        
        BufferedImage image = null;

        switch(direction) {
        case "up":
            if(spriteNum == 1) {
                image = up1;
            }
            if(spriteNum == 2) {
                image = up2;
            }
            break;
        case "down":
            if(spriteNum == 1) {
                image = down1;
            }
            if(spriteNum == 2) {
                image = down2;
            }
            break;
        case "left":
            if(spriteNum == 1) {
                image = left1;
            }
            if(spriteNum == 2) {
                image = left2;
            }
            break;
        case "right":
            if(spriteNum == 1) {
                image = right1;
            }
            if(spriteNum == 2) {
                image = right2;
            }
            break;
        }

        BufferedImage image1 = null;

        switch(direction) {
        case "battle":
            if(spriteNum == 1) {
                image1 = battle1;
            }
            if(spriteNum == 2) {
                image1 = battle2;
            }
            break;
        }

        int x = screenX; 
        int y = screenY; 

        if(screenX > worldX) {
            x = worldX;
        }
        if(screenY > worldY) {
            y = worldY;
        }
        int rightOffset = gp.screenWidth - screenX;
            if(rightOffset > gp.worldWidth - worldX) {
                x = gp.screenWidth - (gp.worldWidth - worldX);
            }
            int bottomOffset = gp.screenHeight - screenY;
            if(bottomOffset > gp.worldHeight - worldY) {
                y = gp.screenHeight - (gp.worldHeight - worldY);
            }

        g2.drawImage(image, screenX, screenY, null);
        
        // testing square 
        //g2.setColor(Color.blue);
        //g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
    }     
    public void selectItem() {

        int itemIndex = gp.ui.getItemIndexOnSlot();

        if(itemIndex < inventory.size()) {
            Entity selectedItem = inventory.get(itemIndex);

            if(selectedItem.type == type_sword) {

                currentWeapon = selectedItem;
                attack = getAttack();
            }
            if(selectedItem.type == type_shield) {

                currentShield = selectedItem;
                defense = getDefense();
            }
            if(selectedItem.type == type_consumable) {

                selectedItem.use(this);
                inventory.remove(itemIndex);
                //later on
            }
        }
    }
}
