package main.object;

import entity.Entity;
import main.GamePanel;


public class Obj_heart extends Entity {
    
    public Obj_heart(GamePanel gp) {

        super(gp);
        name = "Heart";
        image = setup("/res/objects/lifeful1");
        image2 = setup("/res/objects/lifehal1");
        image3 = setup("/res/objects/lifeemp1");
        
    }
}
