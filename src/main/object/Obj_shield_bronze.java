package main.object;

import entity.Entity;
import main.GamePanel;

public class Obj_shield_bronze extends Entity {

    public Obj_shield_bronze(GamePanel gp) {
        super(gp);
        
        type = type_shield;
        name = "Bronze Shield";
        down1 = setup("/res/objects/bshield1");
        defenseValue = 3;
        description = "(" + name + ")\nBronze buckler.";
    }
    
}