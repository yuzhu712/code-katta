package com.ashishpaliwal.codekatta.fun;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 *
 */
public class GCCleaner {

    public static void main(String[] args) throws Exception {
        File file = new File(args[0]);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

        String line = null;
        boolean inGCLog = false;
        while((line = bufferedReader.readLine()) != null) {
            if(!inGCLog) {
                if(line.contains("[Full GC")) {
                    inGCLog = true;
                }
            } else if(inGCLog & line.startsWith("}")) {
                System.out.println(line);
                inGCLog = false;
            }

            if(inGCLog) {
                System.out.println(line);
            }
        }
        bufferedReader.close();
    }

}
