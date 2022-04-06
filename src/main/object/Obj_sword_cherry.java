package main.object;

import entity.Entity;
import main.GamePanel;

public class Obj_sword_cherry extends Entity {

    public Obj_sword_cherry(GamePanel gp) {
        super(gp);
        
        type = type_sword;
        name = "Cherry Sword";
        down1 = setup("/res/objects/csword1");
        attackValue = 1;
        description = "(" + name + ")\nCherry wood shortsword.";
    }
}
    