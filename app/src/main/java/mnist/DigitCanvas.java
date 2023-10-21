/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mnist;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author andrzej
 */
public class DigitCanvas extends Canvas {
    
    int[] img = {};
    
    public void setImg(int[] img){
        this.img = img;
    }
               
    private void draw(Graphics g){
        int size = (int)Math.sqrt(img.length);
        int y = 0;
        while(y < size) {
            int x = 0;
            while(x < size) {
                int colorCode = 255 - img[size * y + x];
                g.setColor(new Color(colorCode, colorCode, colorCode));
                g.fillRect(x, y, 1, 1);
                x++;
            }
            y++;
        }
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        draw(g);
    }

}
