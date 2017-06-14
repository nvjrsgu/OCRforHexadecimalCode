package com.nvjrsgu;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedImage code1 = ImageIO.read(new FileInputStream("./data/codeExamples/1.jpg"));
        //ImageIO.write(code1,"png", new File("./data/codeExamples/1_1.jpg"));
        CutLines cutLines = new CutLines(code1);

        code1 = cutLines.cropLines();
        ImageIO.write(code1,"png", new File("./data/codeExamples/1_2.jpg"));
    }
}
