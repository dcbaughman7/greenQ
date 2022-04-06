package entity;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

//might be in trouble here double check later to import or not
import java.awt.Rectangle;
import java.awt.Graphics2D;

public class Entity {

    public static final String Obj_key = null;
    GamePanel gp;
    public int worldX, worldY;
    
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2, battle1, battle2;
    public BufferedImage world1, world2;
    public BufferedImage enemy1, enemy2, enemy3, enemy4;
    public String direction = "down"; 
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle solidArea = new Rectangle(0, 0, 32, 32);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
    public int actionLockCounter = 0;
    public String dialogues[] = new String[20];
    int dialogueIndex = 0;
    public BufferedImage image, image2, image3;
    public String name;
    public boolean collision = false;
    boolean alive = true;
    boolean dying = false;
    public String playerName;
    public String enemyName;

    //COUNTERS
    int dyingCounter = 0;
    
    //CHAR ATTRIB
    public int maxLife;
    public int life;
    public int hp;
    public int defense;
    public int magicdefense;
    public int attack;
    public int magicattack;
    public int speed;
    public int level;
    public int exp;
    public int nextLevelExp;
    public int coin;
    public int strength;
    public int dexterity;
    public Entity currentWeapon;
    public Entity currentShield;
    
    //ENEMY ATTRIBUTES

    public int eLife;
    public int eMaxLife;
    public int eAttack;
    public int eDefense;
    public int eCoin;

    //ITEM ATTRIBUTE
    public int attackValue;
    public int defenseValue;
    public int magicValue;
    public String description = "";

    //TYPE 
    public int type; //0 = player, 1 = NPC, 2 MONSTER
    public final int type_player = 0;
    public final int type_npc = 1;
    public final int type_monster = 2;
    public final int type_sword = 3;
    public final int type_shield = 4;
    public final int type_consumable = 5;
    public final int type_permObj = 6;

    public Entity(GamePanel gp) {
        this.gp = gp;

    }
    public void setAction() {

    }
    public void speak() {
        
        if(dialogues[dialogueIndex] == null) {
            dialogueIndex = 0;
        }
    gp.ui.currentDialogue = dialogues[dialogueIndex];
    dialogueIndex++;

    switch(gp.player.direction) {
        case "down":
            direction = "up";
            break;
        case "up":
            direction = "down";
            break;
        case "left":
            direction = "left";
            break;
        case "right":
            direction = "right";
            break;
    }

    }
    public void use(Entity entity) {
        
    }
    public void update() {
        
        setAction();

        collisionOn = false;
        gp.cChecker.checkTile(this, false);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkPerObject(this, false);
        gp.cChecker.checkPlayer(this);


        //IF COLLISION IS FALSE, PLAYER CAN MOVE
                
        if(collisionOn == false) {
                    
            switch(direction) {
            case "down": worldY -= speed; break;
            case "up": worldY += speed; break;
            case "left": worldX -= speed; break;
            case "right": worldX += speed; break;
            }
        }

        spriteCounter++;
        if(spriteCounter > 10) {
            if(spriteNum == 1) {
                spriteNum = 2;

            }
            else if(spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }

    }
    
    public void draw(Graphics2D g2) {

        BufferedImage image = null;
        

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
            
        if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && 
            worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
            worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
            worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

                switch(direction) {
                    case "up":
                        if(spriteNum == 1) { image = up1;
                        }
                        if(spriteNum == 2) { image = up2;
                        }
                        break;
                    case "down":
                        if(spriteNum == 1) { image = down1;
                        }
                        if(spriteNum == 2) { image = down2;
                        }
                        break;
                    case "left":
                        if(spriteNum == 1) { image = left1;
                        }
                        if(spriteNum == 2) { image = left2;
                        }
                        break;
                    case "right":
                        if(spriteNum == 1) { image = right1;
                        }
                        if(spriteNum == 2) { image = right2;
                        }
                        break;
                    }

                g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
                }
            }
            
    public BufferedImage setup(String imagePath) {

        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;
        BufferedImage image1 = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch(IOException e){
            e.printStackTrace();
        }
        return image;
    }
}
    




