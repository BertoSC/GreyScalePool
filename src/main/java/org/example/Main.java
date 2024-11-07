package org.example;

import java.io.File;
import java.nio.file.Files;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static final int NUM_THREADS = 5;
    public static void main(String[] args) {
        // Accedemos al directorio e introducimos las IMG en un array de archivos

        //File root = new File("C:\\Users\\a23albertogc\\Desktop\\PSP\\GreyScalePool\\src\\main\\resources\\originals");
        File root = new File("src/main/resources/originals"); // USE RELATIVE
        File [] images = root.listFiles();

        // Inicializamos el pool
        ExecutorService pool = Executors.newFixedThreadPool(NUM_THREADS);

        // Recorremos el array de Files
        for (File f: images){
            Runnable r = new RunnableGreyscale(f);
            pool.execute(r);
        }

        pool.shutdown();

    }
}