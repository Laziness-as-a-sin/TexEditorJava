import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import javax.imageio.ImageIO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Evgeniy
 */
public class Picture implements Serializable{
    
    private int posX = 0, posY = 0;
    private int width = 100, high = 100;
    File fileS;
    
    public Picture(File file) throws IOException{
        fileS = file;
    }
    
    public void paint(Graphics g) throws IOException{
        g.drawImage(ImageIO.read(fileS), posX, posY, width, high, null);
    }
    
    public void changePos(int x, int y){
        posX = x;
        posY = y;
    }
}
