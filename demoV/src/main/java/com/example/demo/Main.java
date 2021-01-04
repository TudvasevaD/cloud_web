package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        System.out.println("Hi~");

        System.out.println("Request to Vision...");
        //String response = new Vision().detectOnImage("test.jpg");
        //System.out.println("Vision:" + response);

        /*S3 s3Client = new S3();
        List<String> list = s3Client.getListBuckets();
        System.out.println("All buckets:");
        System.out.println(list);

        s3Client.createBucket("cloud-web-s2");
        System.out.println("Create bucket cloud-web-s2.");
        list = s3Client.getListBuckets();
        System.out.println("All buckets:");
        System.out.println(list);

        System.out.println();
        s3Client.loadFileToBucket("cloud-web-s2", "1.png");
        System.out.println("Load file 1.png to cloud-web-s2.");
        list = s3Client.getFileListFromBucket("cloud-web-s2");
        System.out.println("All files from cloud-web-s2:");
        System.out.println(list);

        System.out.println();
        s3Client.deleteFile("cloud-web-s2", "1.png");
        System.out.println("Delete file 1.png to cloud-web-s2.");
        list = s3Client.getFileListFromBucket("cloud-web-s2");
        System.out.println("All files from cloud-web-s2:");
        System.out.println(list);

        System.out.println();
        s3Client.deleteBucket("cloud-web-s2");
        System.out.println("Delete bucket cloud-web-s2.");
        list = s3Client.getListBuckets();
        System.out.println("All buckets:");
        System.out.println(list);

        System.out.println();
        String response = new Vision().detectOnImage("1.png");
        System.out.println("Vision:" + response);*/

    }
}
