package main.object;

import entity.Entity;
import main.GamePanel;

public class Obj_shield_wood extends Entity {

    public Obj_shield_wood(GamePanel gp) {
        super(gp);
        
        type = type_shield;
        name = "Hickory Shield";
        down1 = setup("/res/objects/hshield1");
        defenseValue = 2;
        description = "(" + name + ")\nHickory buckler.";
    }
    
}