package com.nvjrsgu;

import java.awt.image.BufferedImage;

/**
 * Created by nvjrsgu on 6/19/2017.
 * https://stackoverflow.com/questions/16068663/scheme-how-to-calculate-energy-of-a-pixel-seam-carving
 */
public class ImageEnergy {

    public BufferedImage getImageEnergy(BufferedImage bi){

        int height = bi.getHeight();
        int width = bi.getWidth();

        BufferedImage bi2 = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        int sum = 0;

        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                if(x == 0 || y == 0 || y == height-1 || x == width-1) {
                    bi2.setRGB(x, y, bi.getRGB(x, y)&0x00FFFFFF);
                }else{

                    sum = 0;
                    sum +=  Math.abs(bi.getRGB(x, y) &0x00FFFFFF - bi.getRGB(x+1, y) &0x00FFFFFF);

                    sum +=  Math.abs(bi.getRGB(x, y) &0x00FFFFFF - bi.getRGB(x, y+1) &0x00FFFFFF);

                  //  sum +=  Math.abs((int)bi.getRGB(x, y)&0x00FFFFFF - (int)bi.getRGB(x-1, y)&0x00FFFFFF);

                   // sum +=  Math.abs((int)bi.getRGB(x, y)&0x00FFFFFF - (int)bi.getRGB(x, y-1)&0x00FFFFFF);

                    bi2.setRGB(x,y, Math.abs(sum));
                }
            }
        }

        return bi2;
    }


}
