package main.object;

import entity.Entity;
import main.GamePanel;

public class Obj_chest extends Entity {
    

    public Obj_chest(GamePanel gp) {
        super(gp);

        type = type_permObj;
        name = "Chest";
        down1 = setup("/res/objects/chest1");
        collision = true;
       
        
    }
}

            
    