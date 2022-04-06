package entity;

import main.GamePanel;

public class Background extends Entity{

    public Background(GamePanel gp) {
        super(gp);
        getBackgroundImage();
    }
    public void getBackgroundImage() {
        
        world1 = setup("/res/battleground/world1");
        world2 = setup("/res/battleground/sunnyhills"); 
        
        
}
}
