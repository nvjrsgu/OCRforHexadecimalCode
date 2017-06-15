package com.nvjrsgu;

import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.LinkedHashSet;

/**
 * Created by nvjrsgu on 6/15/2017.
 */
public class DrawLines {

    public BufferedImage drawHorizontalLines(LinkedHashSet<Integer> whereDraw, BufferedImage image, int rgb){
        int imageHeight = image.getHeight();
        int iamgeWidth = image.getWidth();

        Iterator<Integer> iterator = whereDraw.iterator();
        int drawIndex = -1;

        for(int y = 0; y < imageHeight; y++){
            if(iterator.hasNext()&&y>drawIndex) {
                drawIndex = iterator.next();
                System.out.println(drawIndex);
            }
            for(int x = 0; x < iamgeWidth; x++){
                if(y == drawIndex){
                    image.setRGB(x,y,rgb);
                }
            }
        }
        return image;
    }

    public BufferedImage drawVerticalLines(LinkedHashSet<Integer> whereDraw, BufferedImage image, int rgb){
        int imageHeight = image.getHeight();
        int iamgeWidth = image.getWidth();

        Iterator<Integer> iterator = whereDraw.iterator();
        int drawIndex = -1;
        for(int x = 0; x < iamgeWidth; x++){
            if(iterator.hasNext()&&x>drawIndex) {
                drawIndex = iterator.next();
                System.out.println(drawIndex);
            }
            for(int y = 0; y < imageHeight; y++){
                if(x == drawIndex){
                    image.setRGB(x,y,rgb);
                }
            }
        }
        return image;
    }
}
