package main.object;

import entity.Entity;
import main.GamePanel;

public class Obj_boots extends Entity {
    

    
    public Obj_boots(GamePanel gp) {
        super(gp);

        name = "Boots";
        down1 = setup("/res/objects/boots1");
        
    }
}
