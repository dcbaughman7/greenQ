package entity;

import java.util.Random;

import main.GamePanel;


public class NPC_rey extends Entity {
    

    public NPC_rey(GamePanel gp) {
        super(gp);
        
        direction = "right";
        speed = 2;

        getImage();
        setDialogue();

    }
    public void getImage() {

        up2 = setup("/res/npc/Rey1_over1");
        up1 = setup("/res/npc/Rey1_over2"); 
        down2 = setup("/res/npc/Rey1_over_back1");
        down1 = setup("/res/npc/Rey1_over_back2");
        left1 = setup("/res/npc/Rey1_over_sidel1");
        left2 = setup("/res/npc/Rey1_over_sidel2");
        right1 = setup("/res/npc/Rey1_over_sider1");
        right2 = setup("/res/npc/Rey1_over_sider2");
        
    }
    public void setDialogue() {
        
        dialogues[0] = "I'm Rey.?";
        dialogues[1] = "You don't look like you're ready.";
        dialogues[2] = "I am a soldier here on the island.";
        dialogues[3] = "Hmph.";
        dialogues[4] = "Get with the program or get lost.";
        dialogues[5] = "Yes?";
        dialogues[6] = "As you were.";
    
    }
    public void setAction() {

        actionLockCounter ++;

        if(actionLockCounter == 140) {

        Random random = new Random();
        int i = random.nextInt(100)+1; //pick up 1 to 100

        if(i <= 25) {
            direction = "down";
        }
        if(i > 25 && i <= 50) {
            direction = "up";
        }
        if(i > 50 && i <= 75) {
            direction = "left";
        }
        if(i > 75 && i <= 100) {
            direction = "right";
        }
        actionLockCounter = 0; 
    }
}
    public void speak() {

        super.speak();
    }

}
    
