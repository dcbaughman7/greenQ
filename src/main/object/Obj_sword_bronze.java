package main.object;

import entity.Entity;
import main.GamePanel;

public class Obj_sword_bronze extends Entity {

    public Obj_sword_bronze(GamePanel gp) {
        super(gp);
        
        type = type_sword;
        name = "Bronze Sword";
        down1 = setup("/res/objects/bsword1");
        attackValue = 3;
        description = "(" + name + ")\nBronzen shortsword.";
        

    }
    
}
