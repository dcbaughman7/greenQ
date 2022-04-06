package entity;

import main.GamePanel;

public class Enemy extends Entity {

    public Enemy(GamePanel gp) {
        super(gp);
        getEnemyImage();

        enemyName = "Blue Slime";

        eMaxLife = 3;
        eLife = eMaxLife;
        
        
    }
    public void getEnemyImage() {
        
        enemy1 = setup("/res/enemy/enemy1");
        enemy2 = setup("/res/enemy/enemy1b"); 
        enemy3 = setup("/res/enemy/enemy1c"); 
        enemy4 = setup("/res/enemy/enemy1d"); 
        
        
    }
}
