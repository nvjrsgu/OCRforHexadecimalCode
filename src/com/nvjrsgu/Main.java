package com.nvjrsgu;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedHashSet;

public class Main {

    public static void main(String[] args) throws IOException {

        cutLinesTest("./data/codeExamples/1", "jpg");
        cutLinesTest("./data/codeExamples/2", "jpg");
        cutLinesTest("./data/codeExamples/3", "jpg");

    }

    public static void cutLinesTest(String pathImage, String format){
        try {
            BufferedImage image = ImageIO.read(new FileInputStream(pathImage+"."+format));
            CutLines cutLines = new CutLines(image);
            LinkedHashSet<BufferedImage> images = cutLines.cropLines();
            int counter = 1;
            for (BufferedImage b : images) {
                ImageIO.write(b, "png", new File(pathImage + "_line_" + counter + ".png"));
                counter++;
            }
        }catch (IOException e){
            System.out.println(e);
        }

    }
}
