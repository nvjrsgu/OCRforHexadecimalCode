package com.nvjrsgu;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.LinkedHashSet;

/**
 * Created by nvjrsgu on 6/14/2017.
 */
public class TextImage {

    private BufferedImage originalImage;

    int height, width;

    TextImage(BufferedImage originalImage){
        this.originalImage = originalImage;

        height = originalImage.getHeight();
        width = originalImage.getWidth();
    }

    int[][] getImageColorArray(){
        int[][] imageColorArray = new int[height][width];
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                imageColorArray[y][x] = originalImage.getRGB(x,y);
            }
        }
        return imageColorArray;
    }

    int getAverageBrightness(){
        int[][] imageArray = getImageColorArray();
        long totalBrightness = 0;
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                totalBrightness += 0xFFFFFF-imageArray[y][x]&0x00FFFFFF;
            }
        }
        return (int) (totalBrightness/(height*width));
    }

    int[] getLinesAverageBrightness(){
        int[][] imageArray = getImageColorArray();
        long totalLineBrightness = 0;
        int[] lineBrightness = new int[height];
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                totalLineBrightness += 0xFFFFFF-imageArray[y][x]&0x00FFFFFF;
            }
            lineBrightness[y] =(int) (totalLineBrightness/width);
            totalLineBrightness = 0;
        }
        return lineBrightness;
    }

    int[] getColumnsAverageBrightness(){
        int[][] imageArray = getImageColorArray();
        long totalColumnBrightness = 0;
        int[] columnBrightness = new int[width];
        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                totalColumnBrightness += 0xFFFFFF-imageArray[y][x]&0x00FFFFFF;
            }
            columnBrightness[x] = (int) (totalColumnBrightness/height);
            totalColumnBrightness = 0;
        }
        return columnBrightness;
    }


}
