package main.object;

import entity.Entity;
import main.GamePanel;

public class Obj_dagger_wood extends Entity {

    public Obj_dagger_wood(GamePanel gp) {
        super(gp);
        
        type = type_sword;
        name = "Hickory Dagger";
        down1 = setup("/res/objects/hdagger1");
        attackValue = 2;
        description = "(" + name + ")\nHickory dagger.";
        

    }
    
}
