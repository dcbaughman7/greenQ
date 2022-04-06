package entity;

import main.GamePanel;

public class NPC_merchant extends Entity {
    

    public NPC_merchant(GamePanel gp) {
        super(gp);
        
        direction = "left";
        speed = 0;

        getImage();
        setDialogue();

    }
    public void getImage() {

        up1 = setup("/res/npc/merchant/Bob1_over1");
        up2 = setup("/res/npc/merchant/Bob1_over2"); 
        down1 = setup("/res/npc/merchant/Bob1_over_back1");
        down2 = setup("/res/npc/merchant/Bob1_over_back2");
        left1 = setup("/res/npc/merchant/Bob1_over_sidel1");
        left2 = setup("/res/npc/merchant/Bob1_over_sidel2");
        right1 = setup("/res/npc/merchant/Bob1_over_sider1");
        right2 = setup("/res/npc/merchant/Bob1_over_sider2");

    }
    public void setDialogue() {
        
        dialogues[0] = "I'm in the desert, right?";
        dialogues[1] = "How could it be I am alive?";
        dialogues[2] = "When did you arrive?";
        dialogues[3] = "O, it has been a day...";
        dialogues[4] = "That's about all I got for you.";
        dialogues[5] = "Yes?";
        dialogues[6] = "As you were.";
    
    }
}
