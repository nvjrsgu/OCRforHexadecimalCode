package com.nvjrsgu;

import java.awt.image.BufferedImage;
import java.util.LinkedHashSet;

/**
 * Created by nvjrsgu on 6/14/2017.
 * Закидываем картинку, а на выходе должны получить коллекцию картинок строк
 */
public class CutLines {

    private BufferedImage originalImage;
    private LinkedHashSet<BufferedImage> lines;

    private int height, width;

    CutLines(BufferedImage originalImage){
        this.originalImage = originalImage;

        height = originalImage.getHeight();
        width = originalImage.getWidth();
    }

    private int[][] getImageColorArray(){
        int[][] imageColorArray = new int[height][width];
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                imageColorArray[y][x] = originalImage.getRGB(x,y);
            }
        }
        return imageColorArray;
    }

    private long getAverageBrightness(){
        int[][] imageArray = getImageColorArray();
        long totalBrightness = 0;
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                totalBrightness += 0xFFFFFF-imageArray[y][x]&0x00FFFFFF;
            }
        }
        return totalBrightness/(height*width);
    }


}
