package com.hortonworks.cbd.util;

import java.io.IOException;
import java.util.Map;

public class ScriptExecutor {

    public static void executeScript(String scriptLocation, Map<String, String> environmentVariable) {

        try {
            ProcessBuilder pb = new ProcessBuilder("/bin/bash", scriptLocation);
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
