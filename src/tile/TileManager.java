package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class TileManager {
    
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp) {

        this.gp = gp;
        
        tile = new Tile[75];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap();

    }

    public void getTileImage() {

        //placeholders
        setup(0, "grass1", false);
        setup(1, "water2", true);
        setup(2, "tree1", true);
        setup(3, "dirt1", false);
        setup(4, "swamp1", false);
        setup(5, "tree1", true);
        setup(6, "cactus_2", true);
        setup(7, "wall2", true);
        setup(8, "wall2", true);
        setup(9, "wall3", true);

        //actual map coords
        setup(10, "grass3", false);
        setup(11, "water2", true);
        setup(12, "tree1", true);
        setup(13, "wallcrush1", true);
        setup(14, "waterhor1", true);
        setup(15, "waterhor2", true);
        setup(16, "waterver1", true);
        setup(17, "waterver2", true);
        setup(18, "wall1", true);
        setup(19, "wall2", true);
        setup(20, "wall3", true);
        setup(21, "treetop1", true);
        setup(22, "treebot1", true);
        setup(23, "dirt1", false);
        setup(24, "water3", true);
        setup(25, "wall4", true);
        setup(26, "carpet", false);
        setup(27, "well1", true);
        setup(28, "grassflo1", false);
        setup(29, "grassflo2", false);
        setup(30, "grass3", false);
        setup(31, "water2", true);
        setup(32, "tree1", true);
        setup(33, "dirt1", false);
        setup(34, "swamp1", false);
        setup(35, "dirtpathr1", false);
        setup(36, "dirtpathl1", false);
        setup(37, "dirtpathb1", false);
        setup(38, "dirtpatht1", false);
        setup(39, "dirt2", false);
        setup(40, "dirt3", false);

            
    }

    public void setup(int index, String imageName, boolean collision) {

        UtilityTool uTool = new UtilityTool();

        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/" + imageName + ".png"));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;

        }catch(IOException e) {
            e.printStackTrace();
        }
    } 


    
    public void loadMap() {

        try {
            InputStream is = getClass().getResourceAsStream("/res/maps/world1.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            
            int col = 0;
            int row = 0;

            while(col < gp.maxWorldCol && row < gp.maxWorldRow){
                
                String line = br.readLine();

                while(col < gp.maxWorldCol) {

                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);
                
                    mapTileNum[col][row] = num;
                    col++;

                }
                if(col == gp.maxWorldCol){
                    col = 0;
                    row++;

                }
            }
            br.close();
        
        
        }catch(Exception e) {

        }



    }
    public void draw(Graphics2D g2) {

        int worldCol = 0;
        int worldRow = 0;
        

        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;
            
            //STOP MOVING THE CAMERA AT THE EDGE
            if(gp.player.screenX > gp.player.worldX ) {
                screenX = worldX;
            }
            if(gp.player.screenY > gp.player.worldY) {
                screenY = worldY;
            }
            int rightOffset = gp.screenWidth - gp.player.screenX;
            if(rightOffset > gp.worldWidth - gp.player.worldX) {
                screenX = gp.screenWidth - (gp.worldWidth - worldX);
            }
            int bottomOffset = gp.screenHeight - gp.player.screenY;
            if(bottomOffset > gp.worldHeight - gp.player.worldY) {
                screenY = gp.screenHeight - (gp.worldHeight - worldY);
            }
            if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && 
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

            
                g2.drawImage(tile[tileNum].image, screenX, screenY, null);
            }
            else if(gp.player.screenX > gp.player.worldX || 
            gp.player.screenY > gp.player.worldY || 
            rightOffset > gp.worldWidth - gp.player.worldX ||
            bottomOffset > gp.worldHeight - gp.player.worldY){
                g2.drawImage(tile[tileNum].image, screenX, screenY, null);
            }

                worldCol++;
            

            if(worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }

    }
}
