package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class KeyHand implements KeyListener{

    GamePanel gp;
    UI ui;
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;
    //DEBUG
    boolean checkDrawTime = false;

    public KeyHand(GamePanel gp) {
        this.gp = gp;
        
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        
        int code = e.getKeyCode();
       
        //TITLE STATE 
        if(gp.gameState == gp.titleState) {
            titleState(code);
        }
        //PLAY STATE
        else if(gp.gameState == gp.playState) {
            playState(code);
        }
        //PAUSE STATE
        else if(gp.gameState == gp.pauseState) {
            pauseState(code);
    }
        //DIALOGUE STATE
        else if(gp.gameState == gp.dialogueState) {
            dialogueState(code);
    }
        //CHARACTER STATE
        else if(gp.gameState == gp.characterState) {
            characterState(code);
        }
        else if(gp.gameState == gp.storyState) {
            storyState(code);
        }
        else if(gp.gameState == gp.battleState) {
            battleState(code);
        }
    }

    public void titleState(int code) {

        if(gp.gameState == gp.titleState) {

        if(gp.ui.titleScreenState == 0) {
            if(code == KeyEvent.VK_W) {
                    gp.ui.commandNum--;
                    if(gp.ui.commandNum < 0) {
                        gp.ui.commandNum = 2;
                }
            }
        if(code == KeyEvent.VK_S) {
                    gp.ui.commandNum++;
                    if(gp.ui.commandNum > 2) {
                        gp.ui.commandNum = 0;
                }
            }
        if(code == KeyEvent.VK_ENTER) {
            if(gp.ui.commandNum == 0) {
                gp.ui.titleScreenState = 1;
                    if(gp.gameState == gp.playState) {
                        }
                    }
        if(gp.ui.commandNum == 1) {
                        // add later for load game
            }
        if(gp.ui.commandNum == 2) {
                        System.exit(0);
                    }
                }
            }
            //HERE IS A STORYSTATE WHICH IS A SUBSTATE OF TITLE
        else if(gp.ui.titleScreenState == 1) {
            if(code == KeyEvent.VK_W) {
                gp.ui.commandNum--;
                    if(gp.ui.commandNum < 0) {
                        gp.ui.commandNum = 3;
                    }
                }
            if(code == KeyEvent.VK_S) {
                gp.ui.commandNum++;
                    if(gp.ui.commandNum > 3) {
                        gp.ui.commandNum = 0;
                }
            }
            if(code == KeyEvent.VK_ENTER) {
                if(gp.ui.commandNum == 0) {
                        System.out.println("Fighter things.");
                        gp.gameState = gp.storyState;
                    
                    }
                    if(gp.ui.commandNum == 1) {
                        gp.ui.commandNum--;
                        System.out.println("Thief things.");
                        gp.gameState = gp.storyState;
                    
                    }
                    if(gp.ui.commandNum == 2) {
                        System.out.println("Monk things.");
                        gp.ui.commandNum--;
                        gp.gameState = gp.storyState;
                        
                    }
                    if(gp.ui.commandNum == 3) {
                        gp.ui.commandNum++;
                        gp.ui.titleScreenState = 0;
                    }
                }
            }
        }

}
    public void playState(int code) {

        if(code == KeyEvent.VK_W) {
            upPressed = true;
        }
        if(code == KeyEvent.VK_S) {
            downPressed = true;
        }
        if(code == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if(code == KeyEvent.VK_D) {
            rightPressed = true;
        }
        if(code == KeyEvent.VK_P) {
            gp.gameState = gp.pauseState;   
        }
        if(code == KeyEvent.VK_ENTER) {
            enterPressed = true;
            // IN-GAME MENU
        }
        if(code == KeyEvent.VK_C) {
            gp.gameState = gp.characterState;

            //BATTLE INIT
        }
        if(code == KeyEvent.VK_B) {
            gp.gameState = gp.battleState;
            if(gp.gameState == gp.battleState){
                gp.stopMusic(11);
                gp.stopMusic(13);
                gp.playMusic(13); 
            }
        }
              
        if(code == KeyEvent.VK_U) {
            if(checkDrawTime == false) {
                checkDrawTime = true;
            }
            else if(checkDrawTime == true) {
                checkDrawTime = false;
            }
        }
    }
    public void pauseState(int code) {

        if(code == KeyEvent.VK_P) {
            gp.gameState = gp.playState;
        }
    }
    public void dialogueState(int code) {

        if(code == KeyEvent.VK_ENTER) {
            gp.gameState = gp.playState;
        }
    }
    public void characterState(int code) {

        if(code == KeyEvent.VK_C) {
            gp.gameState = gp.playState;
        }
        if(code == KeyEvent.VK_W) {
            if(gp.ui.slotRow != 0) {
            gp.ui.slotRow--;
            gp.playSE(8);
            }
        }
        if(code == KeyEvent.VK_A) {
            if(gp.ui.slotCol != 0) {
            gp.ui.slotCol--;
            gp.playSE(8);
            }
        }
        if(code == KeyEvent.VK_S) {
            if(gp.ui.slotRow != 3) {
            gp.ui.slotRow++;
            gp.playSE(8);
            }
        }
        if(code == KeyEvent.VK_D) {
            if(gp.ui.slotCol != 4) {
            gp.ui.slotCol++;
            gp.playSE(8);
            }
        }
        if(code == KeyEvent.VK_ENTER) {
            gp.player.selectItem();
        }
    }
    public void storyState(int code) {
        if(code == KeyEvent.VK_SPACE) {
            

        }
        if(code == KeyEvent.VK_ENTER) {
            gp.gameState = gp.playState;
            gp.stopMusic(9);
            
            if(gp.gameState == gp.playState) {
                gp.playMusic(11);
    }
}

    }
    public void debugState(int code) {
    
        if(code == KeyEvent.VK_U) {
            if(checkDrawTime == false) {
                checkDrawTime = true;
            }
            else if(checkDrawTime == true) {
                checkDrawTime = false;
            }
        }
    }
    public void battleState(int code) {
        
        //change to battleState (battleScreen appears)
        if(gp.gameState == gp.battleState) {

            //puts cursor at base set (fight)
            if(code == KeyEvent.VK_W) {
                gp.ui.commandNum--;
                if(gp.ui.commandNum < 0) {
                gp.ui.commandNum = 2;
                        }
                    }
            if(code == KeyEvent.VK_S) {
                gp.ui.commandNum++;
                if(gp.ui.commandNum > 2) {
                gp.ui.commandNum = 0;
                        }
                    }
            if(code == KeyEvent.VK_ENTER) {
                    enterPressed = true;

                if(gp.ui.commandNum == 0) {
                    //FIGHT SEQ & PUNCH SEQ
                    //gp.ui.fightNum = 1;
                    gp.ui.battleScreenState = 1;
                }
                if(gp.ui.commandNum == 1) {
                    // DEFEND SEQ & 

                    }
                if(gp.ui.commandNum == 2) {
                    //BACK TO WORLD
                    gp.gameState = gp.playState;
                    
                            }
                        }
                    }
                    
                if(code == KeyEvent.VK_ENTER) {
                        if(gp.ui.commandNum == 0) {
                            //reference the class that makes battle things 
                            gp.ui.commandNum--;
                                System.out.println("FIGHT THINGS");
                
                                System.out.println(gp.ui.fightNum);
                                
                        }
                    if(gp.ui.commandNum == 1) {
                                gp.ui.commandNum--;
                                System.out.println("DEFEND THINGS");
                                
                        }
                    if(gp.ui.commandNum == 2) {
                                System.out.println("RUN THINGS");
                                gp.ui.commandNum--;
                                            
                        }
                    //if(gp.ui.commandNum == 3) {
                                //gp.ui.commandNum++;
                                
                }
            }
    
    @Override
    public void keyReleased(KeyEvent e) {
              
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if(code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if(code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if(code == KeyEvent.VK_D) {
            rightPressed = false;
        }
        if(code == KeyEvent.VK_ENTER) {
            //enterPressed = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }
}
