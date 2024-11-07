package org.example;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class RunnableGreyscale implements Runnable{
    // almacena la referencia que le pasamos al Runnable
    File image;

    public RunnableGreyscale(File img){
        this.image=img;
    }

    // método que se encarga de la conversión a escala de grises (copiadillo pero adaptado)
    public void convertImage(){
        BufferedImage img = null;
        File f = null;

        // read image
        try {

            f = image;
            img = ImageIO.read(f);

        } catch (IOException e) {
            System.out.println(e);
        }

        // get image's width and height
        int width = img.getWidth();
        int height = img.getHeight();
        int[] pixels = img.getRGB(0, 0, width, height, null, 0, width);
        // convert to grayscale
        for (int i = 0; i < pixels.length; i++) {

            // Here i denotes the index of array of pixels
            // for modifying the pixel value.
            int p = pixels[i];

            int a = (p >> 24) & 0xff;
            int r = (p >> 16) & 0xff;
            int g = (p >> 8) & 0xff;
            int b = p & 0xff;

            // calculate average
            int avg = (r + g + b) / 3;

            // replace RGB value with avg
            p = (a << 24) | (avg << 16) | (avg << 8) | avg;

            pixels[i] = p;
        }
        img.setRGB(0, 0, width, height, pixels, 0, width);

        // formateamos la salida con un substring
        String sb = image.getName();
        String nameShort = sb.substring(0, sb.lastIndexOf("."));
        String destiny = nameShort + "_grey.png";


        // write image  >> adaptamos la salida para que la guarde en result
        try {
            f = new File(
                    "src/main/resources/result/"+destiny);
            ImageIO.write(img, "png", f);
            } catch (IOException e) {
            System.out.println(e);
            }
     }

    @Override
    public void run() {
        convertImage();

    }
}
