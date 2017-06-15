package com.nvjrsgu;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;

/**
 * Created by nvjrsgu on 6/15/2017.
 */
public class CutSymbols extends TextImage {

    int averageBrightness;
    int[] columnsAverageBrightness;
    BufferedImage oneLineImage;
    int[][] imageArray;

    CutSymbols(BufferedImage image){
        super(image);
        averageBrightness = getAverageBrightness();
        columnsAverageBrightness = getColumnsAverageBrightness();
        oneLineImage = getOriginalImage();
        imageArray = getImageColorArray();
    }

    private int[] getAverageBrightnessForDiapason(int startDiapason, int endDiapason){
        return Arrays.copyOfRange(columnsAverageBrightness, startDiapason, endDiapason);
    }



    private int getIndexOfMinimumsForRange(int startDiapason, int endDiapason){
        int[] diapasonBrightness = getAverageBrightnessForDiapason(startDiapason, endDiapason);
        int minValue = Integer.MAX_VALUE;
        int minValueIndex = -1;
        for(int i = 0; i < diapasonBrightness.length; i++){
            if(diapasonBrightness[i] < minValue){
                minValue = diapasonBrightness[i];
                minValueIndex = i;
            }
            System.out.println(diapasonBrightness[i]);
        }
        System.out.println("Value: "+minValue+" Index: "+minValueIndex);
        return minValueIndex;
    }

    public LinkedHashSet<Integer> getMinimums(){
        int minDiapason = (int) (Math.ceil(0.3*oneLineImage.getHeight()));

        int startDiapason = 0;
        int endDiapason = startDiapason+minDiapason;
        int minimum;
        LinkedHashSet<Integer> minimums = new LinkedHashSet<>();
        while(endDiapason <= columnsAverageBrightness.length){
            minimum = getIndexOfMinimumsForRange(startDiapason, endDiapason);
            minimums.add(minimum+startDiapason);
            startDiapason += minimum+2;
            endDiapason = startDiapason+minDiapason;
        }
        System.out.println(minimums);
        return minimums;
    }
}
