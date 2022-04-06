package main;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JPanel;

import entity.Entity;
import entity.Player;
import tile.TileManager;
import entity.Background;
import entity.Enemy;

public class GamePanel extends JPanel implements Runnable{

    final int originalTileSize = 16;
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; //48x48
    
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12; 
    public final int screenWidth = tileSize * maxScreenCol; // 768 pix
    public final int screenHeight = tileSize * maxScreenRow; // 576 pix

    //WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;
    
    //FPS
    int FPS = 60;

    //SYSTEM
    TileManager tileM = new TileManager(this);
    public KeyHand keyH = new KeyHand(this);
    Sound music = new Sound();
    Sound se = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    public EventHandler eHandler = new EventHandler(this);
    Thread gameThread;

    //ENTITY AND OBJECT
    public Player player = new Player(this, keyH, ui);
    public Background background = new Background(this);
    public Enemy enemy = new Enemy(this);
    public Entity obj[] = new Entity[50];
    public Entity pobj[] = new Entity[50];
    public Entity npc[] = new Entity[50];
    ArrayList<Entity> entityList = new ArrayList<>();
    
    //GAME STATE
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int characterState = 4;
    public final int storyState = 5;
    public final int battleState = 6; 

    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.gray);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.addKeyListener(keyH);
    }

    public void setupGame() {  
        
        aSetter.setObject();
        aSetter.setNPC();
    
        playMusic(9);

        gameState = titleState;

    }

    public void startGameThread()   {
        
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000/FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;
   
        while(gameThread != null)   {

            update();

            //will update the char pos and draw the screen
            repaint();


            try{
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;

                if(remainingTime < 0){
                    remainingTime = 0;
                }
                
                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;
            
            }   catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
    
        if(gameState == playState) {
            
            //player
            player.update();
            //NPC
            for(int i = 0; i < npc.length; i++) {
                if(npc[i] != null) {
                    npc[i].update();
                }
            } 
        }
        if(gameState == pauseState) {
            //nothing come back later for introducing pause
        }
    
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        //DEBUG CALLS
        long drawStart = 0;
        if(keyH.checkDrawTime == true) {
            drawStart = System.nanoTime();
        }
        
        //TITLE SCREEN
        if(gameState == titleState) {
            ui.draw(g2);

        }
        //OTHERS
        else {
            //TILE DRAWING
            tileM.draw(g2);
        
            //ADD ENTITY TO LIST
            entityList.add(player);

            for(int i = 0; i < npc.length; i++) {
                if(npc[i] != null) {
                    entityList.add(npc[i]);
                }
            }
            for(int i = 0; i < obj.length; i++) {
                if(obj[i] != null) {
                    entityList.add(obj[i]);
                }
            }
            for(int i = 0; i < pobj.length; i++) {
                if(pobj[i] != null) {
                    entityList.add(pobj[i]);
                }
            }

            //SORT 
            Collections.sort(entityList, new Comparator<Entity>() {

                @Override
                public int compare(Entity e1, Entity e2) {
                    
                    int result = Integer.compare(e1.worldY, e2.worldY);
                    return result;
                }

            });
            //DRAW ENTITIES
            for(int i = 0; i < entityList.size(); i++){ 
                entityList.get(i).draw(g2);
            }
            //ENTITY EMPTY 
            for(int i = 0; i < entityList.size(); i++){ 
                entityList.remove(i);
            //UI elements
            ui.draw(g2);
        }
        
    

    }
        //DEBUG
        if(keyH.checkDrawTime == true) {
        long drawEnd = System.nanoTime();
        long passed = drawEnd - drawStart;
        g2.setFont(new Font("Consolas", Font.PLAIN, 20));
        g2.setColor(Color.white);
        
        System.out.println("Draw Time: "+passed);
        int x = 10;
        int y = 400;
        int lineHeight = 20;
        g2.drawString("Column" + (player.worldX + player.solidArea.x)/tileSize, x, y); y += lineHeight;
        g2.drawString("Row" + (player.worldY + player.solidArea.y)/tileSize, x, y); y += lineHeight;
        g2.drawString("Draw Time: " + passed, x, y);
    }

        g2.dispose();
    
    }
    public void playMusic(int i) {

        music.setFile(i);   
        music.play();
        music.loop();
        
    }
    public void stopMusic(int i) {
        
        music.stop(); 
    }
    public void playSE(int i) {

        se.setFile(i);
        se.play();
    }
    public void stopSE(int i) {

        se.stop();
    }
}