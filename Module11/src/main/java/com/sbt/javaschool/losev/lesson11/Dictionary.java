package com.sbt.javaschool.losev.lesson11;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Dictionary {

    /**
     * Reads from a file at path
     * @param path of text file
     * @return set of unique words from file
     */
    static Map<String, Integer> get(String path){
        Map<String, Integer> map = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null){
                String[] words = line.split("\\P{L}+");
                for (String word : words){
                    if (!map.containsKey(word)){
                        map.put(word, 0);
                    }
                    map.put(word, map.get(word) + 1);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }
}
