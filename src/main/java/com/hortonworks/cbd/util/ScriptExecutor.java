package com.hortonworks.cbd.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

public class ScriptExecutor {

    private static Logger logger = LoggerFactory.getLogger(ScriptExecutor.class);

    public static void executeScript(String scriptLocation, Map<String,String> environmentVariable) {

        try {
            ProcessBuilder pb  = new ProcessBuilder("/bin/bash", scriptLocation);
            Map<String, String> env = pb.environment();
            env.putAll(environmentVariable);

            Process awk = pb.start();
            awk.waitFor();

            BufferedReader reader=new BufferedReader(new InputStreamReader(
                    awk.getInputStream()));
            String line;
            while((line = reader.readLine()) != null) {
                logger.debug(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
