package main.object;

import entity.Entity;
import main.GamePanel;

public class Obj_shield_cherry extends Entity {

    public Obj_shield_cherry(GamePanel gp) {
        super(gp);
        
        type = type_shield;
        name = "Cherry Shield";
        down1 = setup("/res/objects/cshield1");
        defenseValue = 1;
        description = "(" + name + ")\nCherry wood buckler.";
    }
    
}