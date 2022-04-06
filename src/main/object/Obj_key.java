package main.object;

import entity.Entity;
import main.GamePanel;

public class Obj_key extends Entity {
    
    GamePanel gp;
    int value = 5;

    
    public Obj_key(GamePanel gp) {
        super(gp);

        name = "Key";
        down1 = setup("/res/objects/key1");
        description = "(" + name + ")\nA brass key.\nIt opens doors.";
        
    }
    
    
}
