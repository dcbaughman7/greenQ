package main;

//import java.awt.Rectangle;


public class EventHandler {

    GamePanel gp;
    EventRect eventRect[][];

    int previousEventX, previousEventY;
    boolean canTouchEvent = true;
    int coinGet = 0;

    public EventHandler(GamePanel gp) {
        this.gp = gp;
        eventRect = new EventRect[gp.maxWorldCol][gp.maxWorldRow];

        int col = 0;
        int row = 0;
        while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
            
            eventRect[col][row] = new EventRect();
            eventRect[col][row].x = 23;
            eventRect[col][row].y = 23;
            eventRect[col][row].width = 2;
            eventRect[col][row].height = 2;
            eventRect[col][row].eventRectDefaultX = eventRect[col][row].x;
            eventRect[col][row].eventRectDefaultY = eventRect[col][row].y;
        
            col++;
            if(col == gp.maxWorldCol) {
                col = 0;
                row++;
            }
        }
        
    }
    public void checkEvent() {

        //check if player is one tile away

        int xDistance = Math.abs(gp.player.worldX - previousEventX);
        int yDistance = Math.abs(gp.player.worldY - previousEventY);
        int distance = Math.max(xDistance, yDistance);
        if(distance > gp.tileSize) {
            canTouchEvent = true;
        }
        if(canTouchEvent == true) {
        if(hit(20, 20, "right") == true) {
                damagePit(20, 20, gp.dialogueState);
        }
    
        if(hit(38, 39, "up") == true) {
                healingPool(38, 39, gp.dialogueState);
        }
        if(hit(25, 26, "up") == true) {
                chestOpen(25, 26, gp.dialogueState);
        }
    }

}
    public boolean hit(int col, int row, String reqDirection) {

        boolean hit = false;

        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x; 
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y; 
        eventRect[col][row].x = col*gp.tileSize + eventRect[col][row].x;
        eventRect[col][row].y = row*gp.tileSize + eventRect[col][row].y;

        if(gp.player.solidArea.intersects(eventRect[col][row]) && eventRect[col][row].eventDone == false) {
            if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                hit = true;

                previousEventX = gp.player.worldX;
                previousEventY = gp.player.worldY;
            }
        }

        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        eventRect[col][row].x = eventRect[col][row].eventRectDefaultX;
        eventRect[col][row].y = eventRect[col][row].eventRectDefaultY;

        return hit;
    }
    public void damagePit(int col, int row, int gameState) {

        gp.gameState = gameState;
        gp.ui.currentDialogue = "You fall into a pit!";
        gp.player.life -= 1; 
        //eventRect[col][row].eventDone = true;
        canTouchEvent = false;

        
    }
    public void healingPool(int col, int row, int gameState) {

        System.out.println("Healing!");
        
        if(gp.keyH.enterPressed == true) {
            gp.gameState = gameState;
            gp.ui.currentDialogue = "You drink from the well.";
            gp.playSE(7);
            gp.player.life = gp.player.maxLife;
        }
    }
    public void chestOpen(int col, int row, int gameState) {
        
        if(gp.keyH.enterPressed == true);
            if(coinGet == 1) {
                System.out.println("coin: " + coinGet);
                gp.gameState = gameState;
            gp.ui.currentDialogue = "You already opened this.";

        }
        
        else if(gp.keyH.enterPressed == true) {
            gp.gameState = gameState;
            gp.ui.currentDialogue = "You open the chest.";
            gp.playSE(3);
            gp.player.coin = gp.player.coin + 7;
            coinGet++;
            System.out.println("coin: " + coinGet);

    }

}

}
