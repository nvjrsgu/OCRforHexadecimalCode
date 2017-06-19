package com.nvjrsgu;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedHashSet;

public class Main {

    public static int[] massSum() throws Exception {
        TextImage image = new TextImage(ImageIO.read(new FileInputStream("./data/codeExamples/2x4_energ.png")));
        int mass[][] = image.getImageColorArray();
        /*int mass[][] = {
                {1, 2, 3, 4, 5, 6},
                {3, 4, 3, 4, 3, 4},
                {5, 6, 7, 8, 7, 6},
                {1, 2, 3, 4, 5, 6},
                {3, 4, 3, 4, 3, 4},
                {5, 6, 7, 8, 7, 6}
        };*/

        int height = mass.length;
        int width = mass[0].length;

        for(int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                mass[y][x] &= 0x00FFFFFF;
            }
        }
        /*for(int[] i: mass){
            for(int j: i){
                System.out.print(j+", ");
            }
            System.out.println();
        }*/

        int mass2[][] = new int[height][width];
        // System.out.println(mass2.length+" "+mass.length);
        for(int x = 0; x < width; x++){
            mass2[0][x] = mass[0][x];
        }
        for(int x = 0; x < height; x++){
            mass2[x][0] = Integer.MAX_VALUE;
            mass2[x][width-1] = Integer.MAX_VALUE;
        }
        for(int y = 1; y < height; y++){
            for(int x = 1; x < width-1; x++){
                int x1 = mass2[y-1][x-1];
                int x2 = mass2[y-1][x];
                int x3 = mass2[y-1][x+1];

                if(x1 < x2){
                    if(x1 < x3){
                        mass2[y][x] = mass[y][x]+x1;
                    }else{
                        mass2[y][x] = mass[y][x]+x3;
                    }
                }else{
                    if(x2 < x3){
                        mass2[y][x] = mass[y][x]+x2;
                    }else{
                        mass2[y][x] = mass[y][x]+x3;
                    }
                }
            }
        }
        int t[] = new int[height];
        int min = Integer.MAX_VALUE;

            for(int f = 0; f < width; f++){
                if(mass2[height-1][f] < min){
                    t[height-1] = f;
                    min = mass2[height-1][f];
                }
            }
            min = Integer.MAX_VALUE;


        for(int i = height-2; i > 0; i--){
            for(int f = t[i+1]-1; f <= t[i+1]+1; f++){
                if(mass2[i][f] < min){
                    t[i] = f;
                    min = mass2[i][f];
                }
            }
            min = Integer.MAX_VALUE;
        }


        return t;
      /*  for(int x = 0; x < height; x++){
            mass2[x][0] = 0;
            mass2[x][width-1] = 0;
        }*/
        // System.out.println("________________________________________");
      /*  for(int[] i: mass2){
            for(int j: i){
                System.out.print(j+", ");
            }
            System.out.println();
        }*/
    }

    public static void main(String[] args) throws Exception {

        int[] k = massSum();

        BufferedImage image = ImageIO.read(new FileInputStream("./data/codeExamples/2x4.png"));
        int height = image.getHeight();
        int width = image.getWidth();

        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                image.setRGB(x,y,image.getRGB(x,y));
                if(x == k[y]){
                    image.setRGB(x,y,0xFF0000);
                }
            }
        }

        ImageIO.write(image, "png", new File("./data/codeExamples/2x4_.png"));
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

/*
      // drawLineTest("./data/codeExamples/3", "jpg");
        ImageEnergy energy = new ImageEnergy();
        ImageScaling scaling = new ImageScaling();
        BufferedImage image = ImageIO.read(new FileInputStream("./data/codeExamples/2x4.png"));
       image = energy.getImageEnergy(image);
        //image = scaling.imageX2(image, 4);
        ImageIO.write(image, "png", new File("./data/codeExamples/2x4_energ.png"));
*/

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
