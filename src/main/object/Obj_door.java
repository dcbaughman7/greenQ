package main.object;

import entity.Entity;
import main.GamePanel;

public class Obj_door extends Entity {
    

    
    public Obj_door(GamePanel gp) {
        super(gp);

        name = "Door";
        down1 = setup("/res/objects/door1");
        collision = true; 
        
    }
}
