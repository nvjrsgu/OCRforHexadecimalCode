package com.nvjrsgu;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedHashSet;

public class Main {

    public static void main(String[] args) throws IOException {

      /*  cutLinesTest("./data/codeExamples/1", "jpg");
        cutLinesTest("./data/codeExamples/2", "jpg");
        cutLinesTest("./data/codeExamples/3", "jpg");
*/
       /* cutSymbolsTest("./data/codeExamples/3_line_1", "png");
        cutSymbolsTest("./data/codeExamples/3_line_2", "png");
        cutSymbolsTest("./data/codeExamples/3_line_3", "png");
        cutSymbolsTest("./data/codeExamples/1_line_1", "png");
        cutSymbolsTest("./data/codeExamples/1_line_2", "png");
        cutSymbolsTest("./data/codeExamples/1_line_3", "png");
        cutSymbolsTest("./data/codeExamples/2_line_1", "png");
        cutSymbolsTest("./data/codeExamples/2_line_2", "png");
        cutSymbolsTest("./data/codeExamples/2_line_3", "png");
*/

       drawLineTest("./data/codeExamples/3", "jpg");

    }

    public static void drawLineTest(String pathImage, String format){
        DrawLines drawLines = new DrawLines();
        LinkedHashSet<Integer> link = new LinkedHashSet<>();
        try {
            BufferedImage image = ImageIO.read(new FileInputStream(pathImage + "." + format));
            link.add(image.getHeight() / 2);
            drawLines.drawLines(link, image, 0x4AA449, false);
            link.clear();
            link.add(image.getWidth()/2);
            drawLines.drawLines(link, image, 0x4AA449, true);
            ImageIO.write(image, "png", new File(pathImage + "_drewLines" + ".png"));
        }catch(IOException e){
            System.out.println(e);
        }
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

    public static void cutSymbolsTest(String pathImage, String format){
        try {
            BufferedImage image = ImageIO.read(new FileInputStream(pathImage + "." + format));
            CutSymbols cutSymbols = new CutSymbols(image);
            LinkedHashSet<Integer> whereCut = cutSymbols.getMinimums();
            DrawLines drawLines = new DrawLines();
            //drawLines.drawVerticalLines(whereCut, image, 0x34C6C2);
            //ImageIO.write(image, "png", new File(pathImage + "_draw2_" + ".png"));

            whereCut = cutSymbols.cleanMinimums(whereCut);
            drawLines.drawLines(whereCut, image, 0x4AA449, true);
            ImageIO.write(image, "png", new File(pathImage + "_draw2" + ".png"));


        }catch (IOException e){
            System.out.println(e);
        }
    }
}
