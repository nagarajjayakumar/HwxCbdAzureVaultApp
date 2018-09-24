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
            Process awk = pb.inheritIO().start();
            awk.waitFor();
            awk.destroy();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
