package com.nvjrsgu;

import java.awt.image.BufferedImage;

/**
 * Created by nvjrsgu on 6/19/2017.
 */
public class ImageScaling {
    public BufferedImage imageX2(BufferedImage bi, int zoom){
        int height = bi.getHeight();
        int width = bi.getWidth();

        BufferedImage biX2 = new BufferedImage(width*zoom, height*zoom, BufferedImage.TYPE_INT_RGB);

       // System.out.println("h="+height+"; w="+width);

        for(int x = 0; x < width*zoom; x++){
            for(int y = 0; y < height*zoom; y++){
           //     System.out.print("x="+x+"; y="+y);
                if(y%zoom==0){
               //     System.out.println(" y%2==0");
                    biX2.setRGB(x,y,bi.getRGB(x/zoom, y/zoom));
                }else{
                //    System.out.println(" y%2!=0");
                    biX2.setRGB(x,y,biX2.getRGB(x,y-1));
                }
            }
        }
        return biX2;
    }
}
