package entity;


import java.util.Random;

import main.GamePanel;


public class NPC_sage extends Entity {
    

    public NPC_sage(GamePanel gp) {
        super(gp);
        
        direction = "down";
        speed = 2;

        getImage();
        setDialogue();

    }
    public void getImage() {

        up2 = setup("/res/npc/Ural1_over1");
        up1 = setup("/res/npc/Ural1_over2"); 
        down2 = setup("/res/npc/Ural_over_back1");
        down1 = setup("/res/npc/Ural_over_back2");
        left1 = setup("/res/npc/Ural1_over_sidel1");
        left2 = setup("/res/npc/Ural1_over_sidel2");
        right1 = setup("/res/npc/Ural1_over_sider1");
        right2 = setup("/res/npc/Ural1_over_sider2");
        
    


    }
    public void setDialogue() {
        
        dialogues[0] = "What would it be?";
        dialogues[1] = "What brings you to Yanao Island?";
        dialogues[2] = "I was involved in the conflict \nin my younger days.";
        dialogues[3] = "Will that be all?";
        dialogues[4] = "What would it be?";
        dialogues[5] = "Yes?";
        dialogues[6] = "As you were.";
    
    }
    public void setAction() {

        actionLockCounter ++;

        if(actionLockCounter == 170) {

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
