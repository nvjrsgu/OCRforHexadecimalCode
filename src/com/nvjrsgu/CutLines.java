package com.nvjrsgu;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.LinkedHashSet;

/**
 * Created by nvjrsgu on 6/14/2017.
 */
public class CutLines extends TextImage {

    private LinkedHashSet<BufferedImage> lines = new LinkedHashSet<>();
    BufferedImage image_original;
    private int averageBrightness;
    private int[] lineBrightness;
    private int[][] colorArray;

    CutLines(BufferedImage image){
        super(image);
        averageBrightness = getAverageBrightness();
        lineBrightness = getLinesAverageBrightness();
        colorArray = getImageColorArray();
        image_original = getOriginalImage();
    }

    private int[] getStartEndTextPositions(){
        int startEndText[] = new int[height/3];

        boolean switcher = false;
        int counter = 0;
        for(int y = 0; y < height; y++){
            for (int x = 0; x < width; x++){
                if(averageBrightness < lineBrightness[y]) {
                    if(switcher){
                        startEndText[counter] = y;
                        counter++;
                    }
                    switcher = false;
                }else{
                    if(!switcher){
                        startEndText[counter] = y;
                        counter++;
                    }
                    switcher = true;
                }
            }
        }
        return startEndText;
    }



    public LinkedHashSet<BufferedImage> cropLines(){
        int edge = -1;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        int codeStart = -1;
        int codeEnd = 0;

        boolean ended = false;
        boolean started = true;
        int gg[] = getStartEndTextPositions();
       // System.out.println(Arrays.toString(gg));

        if(gg[0] == 0){
            edge = (int) Math.ceil((gg[2]-gg[1])*0.065);

        }else{
            edge = (int) Math.ceil((gg[1]-gg[0])*0.065);
        }

        boolean white = gg[0] == 0;


        int counter = 0;
        for(int y = 0; y < height; y++){
            for (int x = 0; x < width; x++){


                if(gg[counter+1]-edge==y && white){
                    white = !white;
                    counter++;
                }else if(gg[counter+1]+edge==y && !white){
                    white = !white;
                    counter++;
                }
                if(white){
                    image.setRGB(x, y, 0xFDFF2C);
                }else {
                    image.setRGB(x, y, colorArray[y][x]);
                }
            }
            if(white){
                if(ended) {
                    codeEnd = y;
                    if (codeStart != -1 && codeEnd > codeStart) {
                        //System.out.println("Start: " + codeStart + " End: " + codeEnd);
                        BufferedImage oneLineImage = image_original.getSubimage(0, codeStart, width, codeEnd - codeStart);

                        lines.add(deleteWhiteBorders(oneLineImage));
                    }
                    ended = false;
                }
                started = true;
            }else {
                if(started) {
                    codeStart = y;
                    started = false;
                }
                ended = true;
            }
        }
        return lines;
    }

    public BufferedImage deleteWhiteBorders(BufferedImage image){
        TextImage textImage = new TextImage(image);
        int averageBrightness = textImage.getAverageBrightness()/2;
        int[] averageColumnsBrightness = textImage.getColumnsAverageBrightness();
        int xPos[] = new int[2];

        for(int i = 0; i < averageColumnsBrightness.length; i++){
            if(averageColumnsBrightness[i] >= averageBrightness){
                xPos[0] = i;
                System.out.println(i);
                break;
            }
        }

        for(int i = averageColumnsBrightness.length-1; i > 0; i--){
            if(averageColumnsBrightness[i] >= averageBrightness){
                System.out.println(i);
                xPos[1] = i;
                break;
            }
        }

        image = image.getSubimage(xPos[0]-(int)Math.ceil(image.getHeight()*0.12), 0, xPos[1]-xPos[0]+(int)Math.ceil(image.getHeight()*0.12)+(int)Math.ceil(image.getHeight()*0.12),image.getHeight());
        return image;
    }
}
