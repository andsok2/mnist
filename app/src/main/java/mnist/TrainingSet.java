/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mnist;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

/**
 *
 * @author andrzej
 */
public class TrainingSet {
    
    int magicNum = 0;
    int numOfItems = 0;
    int numOfRows = 0;
    int numOfCols = 0;
    int[][] aa;
    int[] lbl;
    int position;
    
    public void init() {
        String srcImg = "train-images-idx3-ubyte.gz";
        initImages(srcImg);
        
        String srcLbl = "train-labels-idx1-ubyte.gz";
        initLabels(srcLbl);
    }
    
    private void initLabels(String src){
        try {

            InputStream is = getClass().getClassLoader().getResourceAsStream(src);
            GZIPInputStream zis = new GZIPInputStream(is);

            byte[] b = zis.readNBytes(4);
            for (int i=0 ; i<b.length;i++) {
                magicNum = (magicNum << 8) + (b[i] & 0xFF);
            }
            b = zis.readNBytes(4);
            for (int i=0 ; i<b.length;i++) {
                numOfItems = (numOfItems << 8) + (b[i] & 0xFF);
            }
            lbl = new int[numOfItems];
            for(int j=0;j<numOfItems;j++) {
                b = zis.readNBytes(1);
                lbl[j] = b[0] & 0xFF;
            }
            
        } catch(IOException io) {
            System.out.println("cannot read " + src);
        }
    }
    public void initImages(String src) {
        try {

            InputStream is = getClass().getClassLoader().getResourceAsStream(src);
            GZIPInputStream zis = new GZIPInputStream(is);

            byte[] b = zis.readNBytes(4);
            for (int i=0 ; i<b.length;i++) {
                magicNum = (magicNum << 8) + (b[i] & 0xFF);
            }
            b = zis.readNBytes(4);
            for (int i=0 ; i<b.length;i++) {
                numOfItems = (numOfItems << 8) + (b[i] & 0xFF);
            }
            if(magicNum == 0x803) {
                b = zis.readNBytes(4);
                for (int i=0 ; i<b.length;i++) {
                    numOfRows = (numOfRows << 8) + (b[i] & 0xFF);
                }
                b = zis.readNBytes(4);
                for (int i=0 ; i<b.length;i++) {
                    numOfCols = (numOfCols << 8) + (b[i] & 0xFF);
                }
            }
            aa = new int[numOfItems][numOfRows*numOfCols];
            for(int j=0;j<numOfItems;j++) {
                b = zis.readNBytes(numOfRows*numOfCols);
                for(int i=0 ; i<b.length;i++) {
                    aa[j][i] = b[i] & 0xFF;
                }
            }
            
        } catch(IOException io) {
            System.out.println("cannot read " + src);
        }
    }
    
    public int getSize() {
        return aa.length;
    }
    
    public int[] getImage(){
        position = (int) (Math.random() * (aa.length-1));
        
        return aa[position];
    }
    
    public int getLabel(){
        return lbl[position];
    }
    
    public int getImageSize(){
        return aa[0].length;
    }
}
