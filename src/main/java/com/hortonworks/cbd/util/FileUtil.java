package com.hortonworks.cbd.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

    public static List<String> readPropertyFile(String csvFileName) throws IOException {

        String line = null;
        BufferedReader stream = null;
        List<String> csvData = new ArrayList<>();
        try {
            stream = new BufferedReader(new FileReader(csvFileName));
            while ((line = stream.readLine()) != null) {
                String[] splitted = line.split(",");
                for (String data : splitted)
                    csvData.add(data);

            }
        } finally {
            if (stream != null)
                stream.close();
        }

        return csvData;

    }
}
