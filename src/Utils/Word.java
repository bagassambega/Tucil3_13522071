package Utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.*;
import java.util.HashSet;
import java.util.Set;

public class Word {
    public static Set<String> dictionary = new HashSet<>();

    public static void getDictionary(String path) {
        try (FileReader file = new FileReader(path)){
            BufferedReader reader = new BufferedReader(file);
            String line;
            while ((line = reader.readLine()) != null) {
                dictionary.add(line.toLowerCase());
            }
        } catch (IOException e) {
            throw new RuntimeException("File tidak valid!");
        }
    }

    public static boolean isWordExist(String word) {
        return dictionary.contains(word);
    }

    public static boolean isLengthSame(String first, String second) {
        return first.length() == second.length();
    }
}
