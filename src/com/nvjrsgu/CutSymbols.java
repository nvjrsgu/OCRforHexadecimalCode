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
            //System.out.println(diapasonBrightness[i]);
        }
        //System.out.println("Value: "+minValue+" Index: "+minValueIndex);
        return minValueIndex;
    }

    public LinkedHashSet<Integer> getMinimums(){
        int minDiapason = (int) (Math.ceil(0.25*oneLineImage.getHeight()));
        int len = (int) Math.ceil(minDiapason*0.25);
        //System.out.println(len);
        int startDiapason = 0;
        int endDiapason = startDiapason+minDiapason;
        int minimum;
        LinkedHashSet<Integer> minimums = new LinkedHashSet<>();
        while(endDiapason <= columnsAverageBrightness.length){
            minimum = getIndexOfMinimumsForRange(startDiapason, endDiapason);
            minimums.add(minimum+startDiapason);
            startDiapason += minimum+len;
            endDiapason = startDiapason+minDiapason;
        }


        LinkedHashSet<Integer> minimums2 = new LinkedHashSet<>();
        Integer[] integ = new Integer[minimums.size()];
        minimums.toArray(integ);
        int minInd = -1;
        for(int i = 0; i < integ.length-2; i++){
            if(integ[i] == integ[i+1]-2){
                if(columnsAverageBrightness[integ[i]] < columnsAverageBrightness[integ[i]+1]){
                    minInd = integ[i];
                }else if(columnsAverageBrightness[integ[i]+1] < columnsAverageBrightness[integ[i]+2]){
                    minInd = integ[i]+1;
                }else{
                    minInd = integ[i]+2;
                }
                minimums2.add(minInd);
                i++;
            }else{
                minimums2.add(integ[i]);
            }

        }
        minimums2.add(integ[integ.length-2]);
        minimums2.add(integ[integ.length-1]);

        //System.out.println(minimums);
        //System.out.println(minimums2);
        return minimums;
    }

    public LinkedHashSet<Integer> cleanMinimums(LinkedHashSet<Integer> minimums){

        Integer[] integ = new Integer[minimums.size()];
        minimums.toArray(integ);
       // System.out.println(integ);
        //System.out.println(Arrays.toString(integ));

        LinkedHashSet<Integer> min2 = new LinkedHashSet<>();
        int minDiapason = (int) (Math.ceil(0.25*oneLineImage.getHeight()));
        int len = (int) Math.ceil(minDiapason*0.25);
        //len = (int) Math.ceil(len);
        for(Integer now : integ){
            if(now >= len && now <= columnsAverageBrightness.length-len) {
                int[] arr1 = Arrays.copyOfRange(columnsAverageBrightness, now-len, now+len);
                int max = -1;
                for(int gg: arr1){
                    if(gg > max){
                        max = gg;
                    }
                }
                if (columnsAverageBrightness[now] < averageBrightness && (max > averageBrightness/2 || max > averageBrightness/2)) {
               // if (columnsAverageBrightness[now] < averageBrightness) {
                    min2.add(now);
                    //System.out.print("+");
                }

            }else{
                if (columnsAverageBrightness[now] < averageBrightness) {
                    min2.add(now);
                }
            }
        }
        System.out.println(min2.size()+" "+minimums.size());
       // System.out.println();
        //System.out.println(min2);

        return min2;
    }
}

