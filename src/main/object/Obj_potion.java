package main.object;

import entity.Entity;
import main.GamePanel;

public class Obj_potion extends Entity{

    GamePanel gp;
    int value = 5;
    public Obj_potion(GamePanel gp) {
        super(gp);

        this.gp = gp;

        type = type_consumable;
        name = "Potion";
        down1 = setup("/res/objects/potion1");
        description = "(" + name + ")\nA pure drink.";

    }
    public void use(Entity entity) {

        gp.gameState = gp.dialogueState;
        gp.ui.currentDialogue = "Your life has been recovered by " + value + "!";

        entity.life += value;
        if(gp.player.life > gp.player.maxLife) {
            gp.player.life = gp.player.maxLife;
        }
        gp.playSE(7);

    }
}
