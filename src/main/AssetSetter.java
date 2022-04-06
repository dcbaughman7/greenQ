package main;

import entity.NPC_merchant;
import entity.NPC_rey;
import entity.NPC_sage;
import main.object.Obj_chest;
import main.object.Obj_key;
import main.object.Obj_potion;
import main.object.Obj_shield_cherry;
import main.object.Obj_shield_wood;
import main.object.Obj_sword_bronze;
import main.object.Obj_sword_cherry;
import main.object.Obj_door;

//import main.object.Obj_boots;

public class AssetSetter {
    
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        //permanent objects

        gp.pobj[0] = new Obj_door(gp);
        gp.pobj[0].worldX = gp.tileSize*28;
        gp.pobj[0].worldY = gp.tileSize*29;

        gp.pobj[1] = new Obj_chest(gp);
        gp.pobj[1].worldX = gp.tileSize*25;
        gp.pobj[1].worldY = gp.tileSize*25;

        // pickupable
        
        gp.obj[2] = new Obj_key(gp);
        gp.obj[2].worldX = gp.tileSize*12;
        gp.obj[2].worldY = gp.tileSize*14;

        gp.obj[3] = new Obj_sword_cherry(gp);
        gp.obj[3].worldX = gp.tileSize*34;
        gp.obj[3].worldY = gp.tileSize*14;

        gp.obj[4] = new Obj_shield_cherry(gp);
        gp.obj[4].worldX = gp.tileSize*40;
        gp.obj[4].worldY = gp.tileSize*14;

        gp.obj[5] = new Obj_sword_bronze(gp);
        gp.obj[5].worldX = gp.tileSize*30;
        gp.obj[5].worldY = gp.tileSize*18;

        gp.obj[7] = new Obj_potion(gp);
        gp.obj[7].worldX = gp.tileSize*10;
        gp.obj[7].worldY = gp.tileSize*28;

        gp.obj[15] = new Obj_potion(gp);
        gp.obj[15].worldX = gp.tileSize*10;
        gp.obj[15].worldY = gp.tileSize*40;

        gp.obj[16] = new Obj_potion(gp);
        gp.obj[16].worldX = gp.tileSize*17;
        gp.obj[16].worldY = gp.tileSize*27;

        gp.obj[17] = new Obj_potion(gp);
        gp.obj[17].worldX = gp.tileSize*15;
        gp.obj[17].worldY = gp.tileSize*29;

        gp.obj[18] = new Obj_potion(gp);
        gp.obj[18].worldX = gp.tileSize*30;
        gp.obj[18].worldY = gp.tileSize*29;

        gp.obj[19] = new Obj_shield_wood(gp);
        gp.obj[19].worldX = gp.tileSize*24;
        gp.obj[19].worldY = gp.tileSize*29;
    
    }
    

    public void setNPC() {

        gp.npc[0] = new NPC_sage(gp);
        gp.npc[0].worldX = gp.tileSize*35;
        gp.npc[0].worldY = gp.tileSize*21;
    
        gp.npc[1] = new NPC_sage(gp);
        gp.npc[1].worldX = gp.tileSize*15;
        gp.npc[1].worldY = gp.tileSize*15;

        gp.npc[2] = new NPC_merchant(gp);
        gp.npc[2].worldX = gp.tileSize*15;
        gp.npc[2].worldY = gp.tileSize*15;

        gp.npc[3] = new NPC_rey(gp);
        gp.npc[3].worldX = gp.tileSize*15;
        gp.npc[3].worldY = gp.tileSize*15;
    }


}

