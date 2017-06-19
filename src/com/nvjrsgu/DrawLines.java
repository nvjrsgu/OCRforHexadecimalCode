package com.nvjrsgu;

import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.LinkedHashSet;

/**
 * Created by nvjrsgu on 6/15/2017.
 */
public class DrawLines {

    public BufferedImage drawLines(LinkedHashSet<Integer> whereDraw, BufferedImage image, int rgb, boolean vertical){
        int imageHeight = -1;
        int iamgeWidth = -1;
        if(!vertical) {
            imageHeight = image.getHeight();
            iamgeWidth = image.getWidth();
        }else{
            imageHeight = image.getWidth();
            iamgeWidth = image.getHeight();
        }
        Iterator<Integer> iterator = whereDraw.iterator();
        int drawIndex = -1;

        for(int y = 0; y < imageHeight; y++){
            if(iterator.hasNext()&&y>drawIndex) {
                drawIndex = iterator.next();
                //System.out.println(drawIndex);
            }
            for(int x = 0; x < iamgeWidth; x++){
                if(y == drawIndex){
                    if(!vertical)
                    image.setRGB(x,y,image.getRGB(x,y)+rgb);
                    else
                    image.setRGB(y,x,image.getRGB(y, x)+rgb);
                }
            }
        }
        return image;
    }
}
