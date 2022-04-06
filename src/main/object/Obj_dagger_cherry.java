package main.object;

import entity.Entity;
import main.GamePanel;

public class Obj_dagger_cherry extends Entity {

    public Obj_dagger_cherry(GamePanel gp) {
        super(gp);
        
        type = type_sword;
        name = "Cherry Dagger";
        down1 = setup("/res/objects/cdagger1");
        attackValue = 1;
        description = "(" + name + ")\nCherry dagger.";
        

    }
    
}
