package main.object;

import entity.Entity;
import main.GamePanel;

public class Obj_sword_wood extends Entity {

    public Obj_sword_wood(GamePanel gp) {
        super(gp);
        
        type = type_sword;
        name = "Wooden Sword";
        down1 = setup("/res/objects/hsword1");
        attackValue = 2;
        description = "(" + name + ")\nOak shortsword.";
    }
    
}
