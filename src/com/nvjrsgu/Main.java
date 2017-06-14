package com.nvjrsgu;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.LinkedHashSet;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedImage code1 = ImageIO.read(new FileInputStream("./data/codeExamples/1.jpg"));
        BufferedImage code2 = ImageIO.read(new FileInputStream("./data/codeExamples/2.jpg"));
        //ImageIO.write(code1,"png", new File("./data/codeExamples/1_1.jpg"));
        CutLines cutLines = new CutLines(code1);
        CutLines cutLines2 = new CutLines(code2);

        code1 = cutLines.cropLines();
        code2 = cutLines2.cropLines();
        ImageIO.write(code1,"png", new File("./data/codeExamples/1_2.jpg"));
        ImageIO.write(code2,"png", new File("./data/codeExamples/2_2.jpg"));

        LinkedHashSet<BufferedImage> images = cutLines.getLines();
        int counter = 1;
        for(BufferedImage b: images){
            ImageIO.write(b,"png", new File("./data/codeExamples/1_3_"+counter+".jpg"));
            counter++;
        }

         images = cutLines2.getLines();
         counter = 1;
        for(BufferedImage b: images){
            ImageIO.write(b,"png", new File("./data/codeExamples/2_3_"+counter+".jpg"));
            counter++;
        }
    }
}
