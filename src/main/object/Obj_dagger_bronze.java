package main.object;

import entity.Entity;
import main.GamePanel;

public class Obj_dagger_bronze extends Entity {

    public Obj_dagger_bronze(GamePanel gp) {
        super(gp);
        
        type = type_sword;
        name = "Bronze Dagger";
        down1 = setup("/res/objects/bdagger1");
        attackValue = 3;
        description = "(" + name + ")\nBronzen dagger.";
        

    }
    
}
