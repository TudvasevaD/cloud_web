package com.example.demo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Vision {

    public void runCommand(String commandLine){
        String str;
        Process proc;
        try{
            proc = Runtime.getRuntime().exec(commandLine);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            while((str = bufferedReader.readLine()) != null){
                System.out.println(str);
            }
            proc.waitFor();
            System.out.println("exit code: " + proc.exitValue());
            proc.destroy();
        }
        catch (Exception ignored){

        }
    }

    public LinkedList<String> detectOnImage(String imageFile) {
        String request = " ./req.sh";

        runCommand(request);
        LinkedList<String> response = new LinkedList<String>();
        String str;
        Process proc;
        try{
            proc = Runtime.getRuntime().exec(request);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            while((str = bufferedReader.readLine()) != null){
                response.add(str);
            }
            proc.waitFor();
            proc.destroy();
        }
        catch (Exception e){
        }
        return response;
    }

}
