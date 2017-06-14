package com.nvjrsgu;

import java.awt.image.BufferedImage;
import java.util.LinkedHashSet;

/**
 * Created by nvjrsgu on 6/14/2017.
 * Закидываем картинку, а на выходе должны получить коллекцию картинок строк
 */
public class CutLines extends TextImage {

    private LinkedHashSet<BufferedImage> lines;

    CutLines(BufferedImage image){
        super(image);
    }

    public BufferedImage cropLines(){
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        int colorArray[][] = getImageColorArray();
        int lineBrightness[] = getLinesAverageBrightness();
        int averageBrightness = getAverageBrightness();

        for(int y = 0; y < height; y++){
            for (int x = 0; x < width; x++){
                if(averageBrightness < lineBrightness[y]) {
                    image.setRGB(x, y, colorArray[y][x]);
                }else{
                    image.setRGB(x, y, 0xFDFF2C);
                }
            }
        }
        return image;
    }
}
