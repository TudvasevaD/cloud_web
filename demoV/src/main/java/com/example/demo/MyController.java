package com.example.demo;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.LinkedList;
import java.util.List;

@Controller
public class MyController {

    S3 s3Client;
    Vision vision;

    @RequestMapping("/")
    public String home(Model model) throws IOException {
        s3Client = new S3();
        List<String> list = s3Client.getListBuckets();
        model.addAttribute("buckets", list);

        model.addAttribute("nameBucket", "cloud-web-s4");

        list = s3Client.getFileListFromBucket("cloud-web-s4");
        model.addAttribute("images", list);

        LinkedList<String> response = new Vision().detectOnImage("apvap.jpeg");
        //response = getResponse(response);
        model.addAttribute("responses", response);

        return "index";
    }

    public String getResponse(String response){
        String s = "";
        s = response.substring(response.indexOf("label"));
        return s;
    }
}
